package model.entity;

import java.time.LocalDate;

/**
 * Realizes the Tournament Entity containing all details of a tournament.
 * 
 * @author ARENA Development Team
 * @version 1.0
 */
public class Tournament {
    private String name;
    private LocalDate applicationStartDate;
    private LocalDate applicationEndDate;
    private LocalDate startDate;
    private LocalDate endDate;
    private int maxPlayers;

    private String homePageUrl;
    private Advertiser exclusiveSponsor;
    private SponsorshipType sponsorshipType;

    /**
     * Khởi tạo thông tin cơ bản cho Giải đấu.
     * 
     * @param name                 Tên giải đấu
     * @param applicationStartDate Ngày bắt đầu mở phom đăng ký
     * @param applicationEndDate   Ngày kết thúc nhận đăng ký
     * @param startDate            Ngày bắt đầu khởi tranh
     * @param endDate              Ngày bế mạc
     * @param maxPlayers           Số lượng Player tối đa
     */
    public Tournament(String name, LocalDate applicationStartDate, LocalDate applicationEndDate,
                      LocalDate startDate, LocalDate endDate, int maxPlayers) {
        this.name = name;
        this.applicationStartDate = applicationStartDate;
        this.applicationEndDate = applicationEndDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.maxPlayers = maxPlayers;
    }

    public String getName() {
        return name;
    }

    public LocalDate getApplicationStartDate() {
        return applicationStartDate;
    }

    public LocalDate getApplicationEndDate() {
        return applicationEndDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public String getHomePageUrl() {
        return homePageUrl;
    }

    public void setHomePageUrl(String homePageUrl) {
        this.homePageUrl = homePageUrl;
    }

    public Advertiser getExclusiveSponsor() {
        return exclusiveSponsor;
    }

    public void setExclusiveSponsor(Advertiser exclusiveSponsor) {
        this.exclusiveSponsor = exclusiveSponsor;
    }

    public SponsorshipType getSponsorshipType() {
        return sponsorshipType;
    }

    public void setSponsorshipType(SponsorshipType sponsorshipType) {
        this.sponsorshipType = sponsorshipType;
    }
}