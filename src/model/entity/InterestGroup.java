package model.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Đại diện cho Nhóm đối tượng quan tâm (Interest Group) gồm Players, Spectators, Advertisers.
 * 
 * @author ARENA Development Team
 * @version 1.0
 */
public class InterestGroup {
    private String groupName;
    private String category; // "Players", "Spectators", "Advertisers"
    private List<User> members;

    /**
     * Khởi tạo một nhóm quan tâm.
     * 
     * @param groupName Tên nhóm (vd: "Pro FPS Players")
     * @param category  Phân loại nhóm
     */
    public InterestGroup(String groupName, String category) {
        this.groupName = groupName;
        this.category = category;
        this.members = new ArrayList<>();
    }

    /**
     * Thêm một thành viên vào nhóm.
     * 
     * @param user Đối tượng người dùng
     */
    public void addMember(User user) {
        this.members.add(user);
    }

    public String getGroupName() {
        return groupName;
    }

    public String getCategory() {
        return category;
    }

    public List<User> getMembers() {
        return members;
    }
}