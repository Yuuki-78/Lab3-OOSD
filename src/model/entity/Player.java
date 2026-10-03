package model.entity;

/**
 * Đại diện cho Người chơi (Player) đăng ký tham gia giải đấu.
 * 
 * @author ARENA Development Team
 * @version 1.0
 */
public class Player extends User {
    public Player(String id, String name, String email) {
        super(id, name, email);
    }
}