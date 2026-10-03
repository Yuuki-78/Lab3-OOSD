package model.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Đại diện cho một League quản lý danh mục giải đấu.
 * 
 * @author ARENA Development Team
 * @version 1.0
 */
public class League {
    private String id;
    private String name;
    private int maxTournamentsLimit;
    private List<Tournament> tournaments;

    public League(String id, String name, int maxTournamentsLimit) {
        this.id = id;
        this.name = name;
        this.maxTournamentsLimit = maxTournamentsLimit;
        this.tournaments = new ArrayList<>();
    }

    /**
     * Kiểm tra xem League còn hạn ngạch tạo thêm giải đấu không (Bước 2).
     * 
     * @return true nếu số lượng hiện tại nhỏ hơn giới hạn tối đa
     */
    public boolean canAddTournament() {
        return tournaments.size() < maxTournamentsLimit;
    }

    public void addTournament(Tournament tournament) {
        this.tournaments.add(tournament);
    }

    public String getName() {
        return name;
    }

    public List<Tournament> getTournaments() {
        return tournaments;
    }
}