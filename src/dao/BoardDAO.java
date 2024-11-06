package dao;

import db.DBConn;
import dto.BoardDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {
    private Connection conn;

    public BoardDAO() {
        this.conn = DBConn.getInstance().getConnection();
    }

    // 게시글 전체 조회
    public List<BoardDTO> getAllBoards() throws SQLException {
        List<BoardDTO> boards = new ArrayList<>();
        String query = "SELECT * FROM tbl_borad";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                BoardDTO board = new BoardDTO();
                board.setBId(rs.getInt("b_id"));
                board.setTitle(rs.getString("title"));
                board.setContent(rs.getString("content"));
                board.setUId(rs.getInt("u_id"));
                board.setCateCd(rs.getInt("cate_cd"));
                board.setInstDt(rs.getString("inst_dt"));
                boards.add(board);
            }
        }
        return boards;
    }

    public BoardDTO getBoardById(int bId) throws SQLException {
        BoardDTO board = new BoardDTO();
        String query = "SELECT * FROM tbl_borad WHERE b_id=?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, bId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    board.setBId(rs.getInt("b_id"));
                    board.setTitle(rs.getString("title"));
                    board.setContent(rs.getString("content"));
                    board.setUId(rs.getInt("u_id"));
                    board.setCateCd(rs.getInt("cate_cd"));
                    board.setInstDt(rs.getString("inst_dt"));
                }
            }
        }
        return board;
    }

    // 게시글 추가
    public boolean addBoard(BoardDTO board) throws SQLException {
        String query = "INSERT INTO tbl_borad (title, content, u_id, cate_cd, inst_dt) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, board.getTitle());
            pstmt.setString(2, board.getContent());
            pstmt.setInt(3, board.getUId());
            pstmt.setInt(4, board.getCateCd());
            pstmt.setString(5, board.getInstDt());
            return pstmt.executeUpdate() > 0;
        }
    }

    // 게시글 업데이트
    public boolean updateBoard(BoardDTO board) throws SQLException {
        String query = "UPDATE tbl_borad SET title = ?, content = ?, u_id = ?, cate_cd = ? WHERE b_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, board.getTitle());
            pstmt.setString(2, board.getContent());
            pstmt.setInt(3, board.getUId());
            pstmt.setInt(4, board.getCateCd());
            pstmt.setInt(5, board.getBId());
            return pstmt.executeUpdate() > 0;
        }
    }

    // 게시글 삭제
    public boolean deleteBoard(int bId) throws SQLException {
        String query = "DELETE FROM tbl_borad WHERE b_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, bId);
            return pstmt.executeUpdate() > 0;
        }
    }

    /**
     * 게시글 최신 조회 10개
     */
    public List<BoardDTO> getTenBoards() throws SQLException {
        List<BoardDTO> boards = new ArrayList<>();
        String query = "SELECT * FROM tbl_board ORDER BY inst_dt DESC LIMIT 10";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                BoardDTO board = new BoardDTO();
                board.setBId(rs.getInt("b_id"));
                board.setTitle(rs.getString("title"));
                board.setContent(rs.getString("content"));
                board.setUId(rs.getInt("u_id"));
                board.setCateCd(rs.getInt("cate_cd"));
                board.setInstDt(rs.getString("inst_dt"));
                boards.add(board);
            }
        }
        return boards;
    }

    /**
     * 추천순 게시글 조회
     */
    public List<BoardDTO> getTopRecommendedBoards() throws SQLException {
        List<BoardDTO> boards = new ArrayList<>();
        String query =
                "SELECT b.*, COUNT(r.b_id) AS recommend_count " +
                        "FROM tbl_borad b " +
                        "LEFT JOIN tbl_recommend r ON b.b_id = r.b_id " +
                        "GROUP BY b.b_id " +
                        "ORDER BY recommend_count DESC " +
                        "LIMIT 10";

        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                BoardDTO board = new BoardDTO();
                board.setBId(rs.getInt("b_id"));
                board.setTitle(rs.getString("title"));
                board.setContent(rs.getString("content"));
                board.setUId(rs.getInt("u_id"));
                board.setCateCd(rs.getInt("cate_cd"));
                board.setInstDt(rs.getString("inst_dt"));
                // Assuming BoardDTO has a recommendCount field and setter
                board.setRecommendCount(rs.getInt("recommend_count"));
                boards.add(board);
            }
        }
        return boards;
    }

}
