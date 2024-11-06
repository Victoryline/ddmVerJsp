package dao;

import dto.CommentDTO;
import db.DBConn;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO {
    private Connection conn;

    public CommentDAO() {
        this.conn = DBConn.getInstance().getConnection();
    }

    // 특정 게시글의 모든 댓글 조회
    public List<CommentDTO> getCommentsByBoardId(int bId) throws SQLException {
        List<CommentDTO> comments = new ArrayList<>();
        String query = "SELECT * FROM tbl_comment WHERE b_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, bId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    CommentDTO comment = new CommentDTO();
                    comment.setCId(rs.getInt("c_id"));
                    comment.setBId(rs.getInt("b_id"));
                    comment.setUId(rs.getString("u_id"));
                    comment.setContent(rs.getString("content"));
                    comment.setCreated(rs.getString("created"));
                    comments.add(comment);
                }
            }
        }
        return comments;
    }

    // 댓글 추가
    public boolean addComment(CommentDTO comment) throws SQLException {
        String query = "INSERT INTO tbl_comment (b_id, u_id, content, created) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, comment.getBId());
            pstmt.setString(2, comment.getUId());
            pstmt.setString(3, comment.getContent());
            pstmt.setString(4, comment.getCreated());
            return pstmt.executeUpdate() > 0;
        }
    }

    // 댓글 삭제
    public boolean deleteComment(int cId) throws SQLException {
        String query = "DELETE FROM tbl_comment WHERE c_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, cId);
            return pstmt.executeUpdate() > 0;
        }
    }
}
