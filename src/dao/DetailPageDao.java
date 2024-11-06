package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DetailPageDao {
    /**
     * 특정 글 정보 끌어오기
     */
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    public void getPage(int b_id){
        String query = "SELECT b_id, title, content, u_id, cate_cd, inst_dt FROM tbl_borad WHERE b_id=?";
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, b_id);

            rs = pstmt.executeQuery();
            if(rs.next()){

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
