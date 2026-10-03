package model.entity;

/**
 * Đại diện cho Khán giả (Spectator) theo dõi giải đấu.
 * 
 * @author ARENA Development Team
 * @version 1.0
 */
public class Spectator extends User {
    public Spectator(String id, String name, String email) {
        super(id, name, email);
    }
}