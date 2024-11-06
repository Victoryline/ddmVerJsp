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
        String query = "INSERT INTO tbl_recommend (u_id, b_id, rec_gbn, inst_dt) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, recommend.getUId());
            pstmt.setInt(2, recommend.getBId());
            pstmt.setString(3, recommend.getRec_gbn());
            pstmt.setString(4, recommend.getInstDt());
            return pstmt.executeUpdate() > 0;
        }
    }

    // 특정 게시글의 추천 수 조회
    // TODO 나중에 추비추 한번에 가져오기
    public int getRecommendationCountByBoardId(int bId, String rec_gbn) throws SQLException {
        String query = "SELECT COUNT(*) AS count FROM tbl_recommend WHERE b_id = ? AND rec_gbn = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, bId);
            pstmt.setString(2, rec_gbn);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("count");
                }
            }
        }
        return 0;
    }
}
