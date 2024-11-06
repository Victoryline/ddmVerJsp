package dao;

import dto.UserDTO;
import db.DBConn;
import java.sql.*;

public class UserDAO {
    private Connection conn;

    public UserDAO() {
        this.conn = DBConn.getInstance().getConnection();
    }

    // 사용자 정보 조회
    public UserDTO getUserById(String uId) throws SQLException {
        String query = "SELECT * FROM tbl_user WHERE u_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, uId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    UserDTO user = new UserDTO();
                    user.setUId(rs.getString("u_id"));
                    user.setName(rs.getString("name"));
                    user.setEmail(rs.getString("email"));
                    user.setPw(rs.getString("pw"));
                    user.setInstDt(rs.getString("inst_dt"));
                    return user;
                }
            }
        }
        return null;
    }

    // 사용자 등록
    public boolean addUser(UserDTO user) throws SQLException {
        String query = "INSERT INTO tbl_user (u_id, name, email, pw, inst_dt) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, user.getUId());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getPw());
            pstmt.setString(5, user.getInstDt());
            return pstmt.executeUpdate() > 0;
        }
    }

    // 사용자 삭제
    public boolean deleteUser(String uId) throws SQLException {
        String query = "DELETE FROM tbl_user WHERE u_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, uId);
            return pstmt.executeUpdate() > 0;
        }
    }
}
