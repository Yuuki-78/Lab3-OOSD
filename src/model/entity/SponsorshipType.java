package model.entity;

/**
 * Enumeration biểu diễn loại hình tài trợ/quảng cáo của giải đấu.
 * 
 * @author ARENA Development Team
 * @version 1.0
 */
public enum SponsorshipType {
    /**
     * Tài trợ độc quyền từ một nhà quảng cáo duy nhất (Flat fee).
     */
    EXCLUSIVE,

    /**
     * Quảng cáo ngẫu nhiên và tính phí theo lượt hiển thị (Per-unit fee).
     */
    RANDOM_PER_UNIT
}