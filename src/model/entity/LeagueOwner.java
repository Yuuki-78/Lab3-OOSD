package model.entity;

/**
 * Đại diện cho Ban tổ chức / Chủ sở hữu League (LeagueOwner).
 * 
 * @author ARENA Development Team
 * @version 1.1
 */
public class LeagueOwner extends User {
    private boolean isLoggedIn;

    public LeagueOwner(String id, String name, String email) {
        super(id, name, email);
        this.isLoggedIn = true; // Mặc định đã đăng nhập thành công vào hệ thống ARENA
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.isLoggedIn = loggedIn;
    }
}