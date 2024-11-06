package dao;

import dto.RecommendDTO;
import db.DBConn;
import java.sql.*;

public class RecommendDAO {
    private Connection conn;

    public RecommendDAO() {
        this.conn = DBConn.getInstance().getConnection();
    }

    // 추천 추가
    public boolean addRecommendation(RecommendDTO recommend) throws SQLException {
        String query = "INSERT INTO tbl_recommend (u_id, b_id, inst_dt) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, recommend.getUId());
            pstmt.setInt(2, recommend.getBId());
            pstmt.setString(3, recommend.getInstDt());
            return pstmt.executeUpdate() > 0;
        }
    }

    // 특정 게시글의 추천 수 조회
    public int getRecommendationCountByBoardId(int bId) throws SQLException {
        String query = "SELECT COUNT(*) AS count FROM tbl_recommend WHERE b_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, bId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("count");
                }
            }
        }
        return 0;
    }
}
