package dao;

import db.DBConn;
import dto.FileDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FileDAO {

    private Connection conn;

    public FileDAO() {
        this.conn = DBConn.getInstance().getConnection();
    }

    // 파일 정보를 데이터베이스에 삽입하는 메서드
    public boolean insertFile(FileDTO file) {
        String sql = "INSERT INTO tbl_file (b_id, file_name, file_path, file_size) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, file.getBId());
            pstmt.setString(2, file.getFileName());
            pstmt.setString(3, file.getFilePath());
            pstmt.setLong(4, file.getFileSize());

            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 특정 게시글에 속한 파일 목록을 조회하는 메서드
    public List<FileDTO> getFilesByPostId(int bId) {
        List<FileDTO> files = new ArrayList<>();
        String sql = "SELECT file_id, b_id, file_name, file_path, file_size, inst_dt FROM tbl_file WHERE b_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, bId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    FileDTO file = new FileDTO();
                    file.setF_id(rs.getInt("file_id"));
                    file.setBId(rs.getInt("b_id"));
                    file.setFileName(rs.getString("file_name"));
                    file.setFilePath(rs.getString("file_path"));
                    file.setFileSize(rs.getLong("file_size"));
                    file.setInstDt(rs.getTimestamp("inst_dt"));
                    files.add(file);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return files;
    }

    // 특정 파일을 삭제하는 메서드
    public boolean deleteFile(int f_id) {
        String sql = "DELETE FROM tbl_file WHERE file_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, f_id);

            int rowsDeleted = pstmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
