package model.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Lớp thực thể hệ thống toàn cục Arena.
 * 
 * @author ARENA Development Team
 * @version 1.0
 */
public class Arena {
    private String name;
    private int maxTournamentsLimit;
    private List<League> leagues;
    private List<Advertiser> advertisers;
    private List<InterestGroup> interestGroups;

    public Arena(String name, int maxTournamentsLimit) {
        this.name = name;
        this.maxTournamentsLimit = maxTournamentsLimit;
        this.leagues = new ArrayList<>();
        this.advertisers = new ArrayList<>();
        this.interestGroups = new ArrayList<>();
    }

    /**
     * Tính tổng số giải đấu đang diễn ra/được tạo trên toàn Arena.
     * 
     * @return Tổng số giải đấu
     */
    public int getTotalTournamentsCount() {
        int count = 0;
        for (League league : leagues) {
            count += league.getTournaments().size();
        }
        return count;
    }

    /**
     * Kiểm tra hạn ngạch toàn Arena trước khi tạo giải đấu (Bước 2).
     * 
     * @return true nếu còn dung lượng tạo giải
     */
    public boolean canAddTournament() {
        return getTotalTournamentsCount() < maxTournamentsLimit;
    }

    public void addLeague(League league) {
        this.leagues.add(league);
    }

    public void addAdvertiser(Advertiser advertiser) {
        this.advertisers.add(advertiser);
    }

    public void addInterestGroup(InterestGroup group) {
        this.interestGroups.add(group);
    }

    public List<Advertiser> getAdvertisers() {
        return advertisers;
    }

    public List<InterestGroup> getInterestGroups() {
        return interestGroups;
    }
}