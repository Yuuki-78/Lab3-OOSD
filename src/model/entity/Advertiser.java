package model.entity;

/**
 * Đại diện cho Nhà quảng cáo / Nhà tài trợ (Advertiser).
 * 
 * @author ARENA Development Team
 * @version 1.1
 */
public class Advertiser extends User {
    private double accountBalance;
    private boolean wantsExclusiveSponsorship;

    public Advertiser(String id, String name, String email, double accountBalance, boolean wantsExclusiveSponsorship) {
        super(id, name, email);
        this.accountBalance = accountBalance;
        this.wantsExclusiveSponsorship = wantsExclusiveSponsorship;
    }

    public boolean isWantsExclusiveSponsorship() {
        return wantsExclusiveSponsorship;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    /**
     * Phản hồi yêu cầu tài trợ độc quyền có tích hợp Xác thực an toàn (Quality Requirement 1).
     * 
     * @param tournamentName Tên giải đấu
     * @param flatFee        Mức phí tài trợ cố định
     * @param authToken      Mã xác thực an toàn của nhà quảng cáo
     * @return true nếu xác thực thành công và đủ số dư
     */
    public boolean respondToSponsorshipRequest(String tournamentName, double flatFee, String authToken) {
        // Giả lập kiểm tra secure authentication
        boolean isAuthenticated = authToken != null && !authToken.isEmpty();
        if (!isAuthenticated) {
            System.out.println("   [Security Warning] Xác thực không hợp lệ cho " + getName());
            return false;
        }
        return this.accountBalance >= flatFee;
    }

    /**
     * Khấu trừ phí tài trợ khỏi tài khoản.
     */
    public void chargeAccount(double amount) {
        if (this.accountBalance >= amount) {
            this.accountBalance -= amount;
        } else {
            throw new IllegalStateException("Số dư tài khoản không đủ.");
        }
    }

    /**
     * Cho phép Nhà quảng cáo hủy hợp đồng tài trợ trong thời hạn quy định theo luật (Quality Requirement 2).
     * 
     * @param tournament Giải đấu đã tài trợ
     * @param refundAmount Số tiền hoàn lại
     */
    public void cancelSponsorship(Tournament tournament, double refundAmount) {
        this.accountBalance += refundAmount;
        tournament.setExclusiveSponsor(null);
        tournament.setSponsorshipType(SponsorshipType.RANDOM_PER_UNIT);
        System.out.println("[Pháp lý / Quality Requirement] Nhà tài trợ " + getName() 
                + " đã hủy hợp đồng tài trợ trong thời hạn hợp lệ. Đã hoàn lại $" + refundAmount);
    }
}