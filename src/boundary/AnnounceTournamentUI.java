package boundary;

import model.entity.Advertiser;
import model.entity.InterestGroup;
import model.entity.Tournament;

import java.time.LocalDate;
import java.util.List;

/**
 * Boundary Class giả lập màn hình tương tác với LeagueOwner trong Use Case AnnounceTournament.
 * 
 * @author ARENA Development Team
 * @version 1.0
 */
public class AnnounceTournamentUI {

    /**
     * Thông báo lỗi khi số lượng giải đấu vượt giới hạn hệ thống.
     */
    public void displayLimitExceededError() {
        System.out.println("[UI Error] Rất tiếc, số lượng giải đấu trong League hoặc Arena đã vượt quá giới hạn!");
    }

    /**
     * Hiển thị phom nhập liệu và thu thập thông tin tạo giải đấu (Bước 3).
     * 
     * @return Đối tượng Tournament chứa dữ liệu đăng ký
     */
    public Tournament promptTournamentDetails() {
        System.out.println("[UI Form] Đang hiển thị phom đăng ký thông tin giải đấu...");
        return new Tournament(
                "ARENA Championship 2026",
                LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(10),
                LocalDate.now().plusDays(15),
                LocalDate.now().plusDays(25),
                64
        );
    }

    /**
     * Hỏi LeagueOwner về nhu cầu tìm tài trợ độc quyền (Bước 4).
     * 
     * @return true nếu muốn tìm nhà tài trợ độc quyền
     */
    public boolean askSeekExclusiveSponsor() {
        System.out.println("[UI Prompt] Bạn có muốn tìm nhà tài trợ độc quyền cho giải đấu này không? (Có/Không)");
        return true; // Giả lập LeagueOwner chọn "Có"
    }

    /**
     * Cho phép LeagueOwner chọn danh sách các nhà quảng cáo để gửi lời mời (Bước 5).
     * 
     * @param eligibleAdvertisers Danh sách nhà quảng cáo tiềm năng
     * @return Danh sách nhà quảng cáo được chọn
     */
    public List<Advertiser> selectProposedSponsors(List<Advertiser> eligibleAdvertisers) {
        System.out.println("[UI Prompt] Danh sách Nhà quảng cáo đề xuất tài trợ độc quyền:");
        for (int i = 0; i < eligibleAdvertisers.size(); i++) {
            System.out.println("   " + (i + 1) + ". " + eligibleAdvertisers.get(i).getName());
        }
        System.out.println("[UI Selection] LeagueOwner chọn mời tất cả các nhà quảng cáo trên.");
        return eligibleAdvertisers;
    }

    /**
     * Hiển thị phản hồi từ các nhà tài trợ (Bước 7).
     * 
     * @param interestedAdvertisers Danh sách các đơn vị đồng ý
     */
    public void displaySponsorResponses(List<Advertiser> interestedAdvertisers) {
        System.out.println("[UI Info] Danh sách Nhà quảng cáo ĐỒNG Ý tài trợ độc quyền:");
        for (Advertiser adv : interestedAdvertisers) {
            System.out.println("   - " + adv.getName() + " (Số dư hiện tại: $" + adv.getAccountBalance() + ")");
        }
    }

    /**
     * Cho phép LeagueOwner chọn 1 nhà tài trợ chính thức (Bước 8).
     * 
     * @param interestedAdvertisers Danh sách đơn vị sẵn sàng
     * @return Nhà tài trợ được chốt hoặc null
     */
    public Advertiser selectFinalExclusiveSponsor(List<Advertiser> interestedAdvertisers) {
        if (interestedAdvertisers.isEmpty()) {
            System.out.println("[UI Selection] Không có nhà tài trợ nào phù hợp.");
            return null;
        }
        Advertiser selected = interestedAdvertisers.get(0);
        System.out.println("[UI Selection] LeagueOwner chốt chọn: " + selected.getName() + " làm tài trợ độc quyền!");
        return selected;
    }

    /**
     * Hiển thị danh sách các nhóm quan tâm để gửi thông báo (Bước 11 & 12).
     * 
     * @param availableGroups Danh sách các nhóm hiện có
     * @return Danh sách các nhóm được chọn gửi tin
     */
    public List<InterestGroup> selectGroupsToNotify(List<InterestGroup> availableGroups) {
        System.out.println("[UI Prompt] Chọn các nhóm người dùng để thông báo:");
        for (int i = 0; i < availableGroups.size(); i++) {
            System.out.println("   [" + (i + 1) + "] " + availableGroups.get(i).getGroupName() 
                    + " (" + availableGroups.get(i).getCategory() + ")");
        }
        System.out.println("[UI Selection] LeagueOwner chọn tất cả các nhóm trên.");
        return availableGroups;
    }

    /**
     * Hiển thị thông báo hoàn tất tạo giải đấu.
     * 
     * @param tournamentName Tên giải đấu
     * @param homePageUrl    URL trang chủ vừa tạo
     */
    public void displaySuccessMessage(String tournamentName, String homePageUrl) {
        System.out.println("\n[UI Success] Tạo thành công giải đấu: " + tournamentName);
        System.out.println("[UI Success] Trang chủ giải đấu: " + homePageUrl);
    }
}