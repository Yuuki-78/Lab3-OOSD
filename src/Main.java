import boundary.AnnounceTournamentUI;
import control.AnnounceTournamentControl;
import model.entity.*;

/**
 * Lớp khởi chạy chương trình mô phỏng Use Case AnnounceTournament.
 * 
 * @author ARENA Development Team
 * @version 1.1
 */
public class Main {

    public static void main(String[] args) {
        // 1. Khởi tạo Arena hệ thống
        Arena arena = new Arena("Global ARENA", 10);
        League league = new League("LEG-01", "Pro Gamers League", 5);
        arena.addLeague(league);

        LeagueOwner owner = new LeagueOwner("LO-01", "Nguyen Van A", "owner@arena.com");

        // 2. Tạo các Advertiser
        Advertiser adv1 = new Advertiser("ADV-01", "RedBull", "contact@redbull.com", 15000.0, true);
        Advertiser adv2 = new Advertiser("ADV-02", "Logitech", "contact@logitech.com", 3000.0, true);
        Advertiser adv3 = new Advertiser("ADV-03", "Monster Energy", "contact@monster.com", 20000.0, false);

        arena.addAdvertiser(adv1);
        arena.addAdvertiser(adv2);
        arena.addAdvertiser(adv3);

        // 3. Tạo các nhóm đối tượng quan tâm (Players, Spectators, Advertisers) theo đúng Step 11
        InterestGroup playerGroup = new InterestGroup("Pro FPS Players Group", "Players");
        playerGroup.addMember(new Player("P-01", "PlayerOne", "player1@gmail.com"));

        InterestGroup spectatorGroup = new InterestGroup("Esports Fans Group", "Spectators");
        spectatorGroup.addMember(new Spectator("S-01", "ViewerX", "viewerx@gmail.com"));

        InterestGroup advertiserGroup = new InterestGroup("Sponsor Affiliates Group", "Advertisers");
        advertiserGroup.addMember(adv3);

        arena.addInterestGroup(playerGroup);
        arena.addInterestGroup(spectatorGroup);
        arena.addInterestGroup(advertiserGroup);

        // 4. Khởi chạy Use Case
        AnnounceTournamentUI ui = new AnnounceTournamentUI();
        AnnounceTournamentControl control = new AnnounceTournamentControl(arena, ui);

        control.processAnnounceTournament(owner, league);
    }
}