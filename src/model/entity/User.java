package model.entity;

/**
 * Lớp cơ sở đại diện cho Người dùng chung trong hệ thống ARENA.
 * 
 * @author ARENA Development Team
 * @version 1.0
 */
public class User {
    private String id;
    private String name;
    private String email;

    /**
     * Khởi tạo một đối tượng người dùng mới.
     * 
     * @param id    Mã định danh người dùng
     * @param name  Họ và tên người dùng
     * @param email Địa chỉ email liên hệ
     */
    public User(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}