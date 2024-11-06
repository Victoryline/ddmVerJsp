package dao;

import dto.CategoryDTO;
import db.DBConn;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    private Connection conn;

    public CategoryDAO() {
        this.conn = DBConn.getInstance().getConnection();
    }

    // 모든 카테고리 조회
    public List<CategoryDTO> getAllCategories() {
        List<CategoryDTO> categories = new ArrayList<>();
        String query = "SELECT * FROM tbl_category";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                CategoryDTO category = new CategoryDTO();
                category.setCateCd(rs.getInt("cate_cd"));
                category.setName(rs.getString("name"));
                categories.add(category);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categories;
    }

    // 카테고리 추가
    public boolean addCategory(CategoryDTO category) throws SQLException {
        String query = "INSERT INTO tbl_category (name) VALUES (?)";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, category.getName());
            return pstmt.executeUpdate() > 0;
        }
    }

    // 카테고리 삭제
    public boolean deleteCategory(int cateCd) throws SQLException {
        String query = "DELETE FROM tbl_category WHERE cate_cd = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, cateCd);
            return pstmt.executeUpdate() > 0;
        }
    }
}
