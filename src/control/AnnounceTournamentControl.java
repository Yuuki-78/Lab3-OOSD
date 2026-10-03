package control;

import boundary.AnnounceTournamentUI;
import model.entity.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Control Class hiện thực hóa toàn bộ luồng nghiệp vụ 14 bước của Use Case AnnounceTournament.
 * 
 * @author ARENA Development Team
 * @version 1.1
 */
public class AnnounceTournamentControl {

    private final Arena arena;
    private final AnnounceTournamentUI ui;
    private static final double FLAT_SPONSORSHIP_FEE = 5000.0;

    public AnnounceTournamentControl(Arena arena, AnnounceTournamentUI ui) {
        this.arena = arena;
        this.ui = ui;
    }

    public void processAnnounceTournament(LeagueOwner leagueOwner, League league) {
        System.out.println("================================================================================");
        System.out.println("BẮT ĐẦU THỰC HIỆN USE CASE: AnnounceTournament");
        System.out.println("================================================================================");

        // Entry Condition Check
        if (!leagueOwner.isLoggedIn()) {
            System.out.println("[Entry Condition Failed] LeagueOwner chưa đăng nhập vào ARENA. Dừng Use Case.");
            return;
        }
        System.out.println("[Entry Condition Satisfied] LeagueOwner " + leagueOwner.getName() + " đã đăng nhập thành công.");

        // Flow Step 1: Request creation
        System.out.println("Bước 1: LeagueOwner yêu cầu tạo giải đấu mới trong League [" + league.getName() + "].");

        // Flow Step 2: Check limits
        System.out.println("Bước 2: Hệ thống kiểm tra giới hạn giải đấu trong League và Arena...");
        if (!arena.canAddTournament() || !league.canAddTournament()) {
            ui.displayLimitExceededError();
            return;
        }
        System.out.println(" -> Giới hạn hợp lệ. Hiển thị phom nhập liệu cho LeagueOwner.");

        // Flow Step 3: Specify tournament details
        Tournament tournament = ui.promptTournamentDetails();
        System.out.println("Bước 3: Đã nhận thông tin giải đấu: " + tournament.getName() 
                + " | Thời gian nộp hồ sơ: " + tournament.getApplicationStartDate() + " đến " + tournament.getApplicationEndDate());

        // Flow Step 4: Ask for exclusive sponsorship & list candidates
        boolean seekExclusive = ui.askSeekExclusiveSponsor();
        Advertiser selectedSponsor = null;

        if (seekExclusive) {
            System.out.println("Bước 4: Hệ thống tìm kiếm các Advertiser có mong muốn tài trợ độc quyền...");
            List<Advertiser> proposedAdvertisers = new ArrayList<>();
            for (Advertiser adv : arena.getAdvertisers()) {
                if (adv.isWantsExclusiveSponsorship()) {
                    proposedAdvertisers.add(adv);
                }
            }

            if (!proposedAdvertisers.isEmpty()) {
                // Flow Step 5: Select subset of proposed sponsors
                List<Advertiser> selectedCandidates = ui.selectProposedSponsors(proposedAdvertisers);

                // Flow Step 6: Securely notify selected sponsors about flat fee
                System.out.println("Bước 6: Gửi thông báo an toàn kèm phí tài trợ trọn gói ($" + FLAT_SPONSORSHIP_FEE + ") tới các Advertiser được chọn...");

                // Flow Step 7: Communicate answers to LeagueOwner (With Secure Auth)
                List<Advertiser> interestedAdvertisers = new ArrayList<>();
                for (Advertiser adv : selectedCandidates) {
                    String dummyAuthToken = "SECURE_AUTH_TOKEN_" + adv.getId(); // Quality Requirement 1
                    if (adv.respondToSponsorshipRequest(tournament.getName(), FLAT_SPONSORSHIP_FEE, dummyAuthToken)) {
                        interestedAdvertisers.add(adv);
                    }
                }
                ui.displaySponsorResponses(interestedAdvertisers);

                // Flow Step 8: Select one sponsor if available
                if (!interestedAdvertisers.isEmpty()) {
                    selectedSponsor = ui.selectFinalExclusiveSponsor(interestedAdvertisers);
                }
            }
        }

        // Flow Step 9 & 10: Settle Sponsorship
        if (selectedSponsor != null) {
            // Flow Step 9: Record exclusive sponsor & charge flat fee
            tournament.setExclusiveSponsor(selectedSponsor);
            tournament.setSponsorshipType(SponsorshipType.EXCLUSIVE);
            selectedSponsor.chargeAccount(FLAT_SPONSORSHIP_FEE);

            System.out.println("Bước 9: Ghi nhận nhà tài trợ độc quyền [" + selectedSponsor.getName() + "].");
            System.out.println("        Đã trừ $" + FLAT_SPONSORSHIP_FEE + " vào tài khoản Advertiser. Số dư còn lại: $" + selectedSponsor.getAccountBalance());
            System.out.println("        Tất cả banner quảng cáo của giải đấu sẽ do " + selectedSponsor.getName() + " độc quyền cung cấp.");
        } else {
            // Flow Step 10: Random banners & per-unit charges
            tournament.setSponsorshipType(SponsorshipType.RANDOM_PER_UNIT);
            System.out.println("Bước 10: Không chọn nhà tài trợ độc quyền.");
            System.out.println("         Các banner quảng cáo sẽ chọn ngẫu nhiên từ Advertising Pool và tính phí theo lượt hiển thị (per-unit).");
        }

        // Flow Step 11: Prompt groups of Players, Spectators, and Advertisers
        System.out.println("Bước 11: Vấn đề tài trợ hoàn tất. Hệ thống hiển thị danh sách các nhóm Players, Spectators, Advertisers quan tâm...");
        List<InterestGroup> availableGroups = arena.getInterestGroups();

        // Flow Step 12: Select groups to notify
        List<InterestGroup> selectedGroups = ui.selectGroupsToNotify(availableGroups);

        // Flow Step 13: Create home page in arena
        String homePageUrl = "https://arena.com/tournaments/" + tournament.getName().toLowerCase().replaceAll("\\s+", "-");
        tournament.setHomePageUrl(homePageUrl);
        league.addTournament(tournament);
        System.out.println("Bước 13: Đã tạo trang chủ giải đấu tại Arena: " + homePageUrl);

        ui.displaySuccessMessage(tournament.getName(), homePageUrl);

        // Flow Step 14: On application start date, send notifications
        System.out.println("\nBước 14: Đến ngày bắt đầu nhận đăng ký (" + tournament.getApplicationStartDate() + ")...");
        for (InterestGroup group : selectedGroups) {
            for (User user : group.getMembers()) {
                System.out.println("   [Thông báo gửi tới " + group.getCategory() + "] Link trang chủ: " 
                        + tournament.getHomePageUrl() + " -> Người nhận: " + user.getName() + " (" + user.getEmail() + ")");
            }
        }
        System.out.println("   -> Các Player có thể nộp đơn tham gia thông qua Use Case 'ApplyForTournament' từ bây giờ đến " + tournament.getApplicationEndDate() + ".");
        System.out.println("================================================================================");
    }
}