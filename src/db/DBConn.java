package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn {
    private static DBConn instance;
    private Connection conn;

    private static final String DRIVER_NAME = "org.mariadb.jdbc.Driver";
    private String url = "jdbc:mariadb://192.168.0.250/ddmjsp";
    private String username = "root";
    private String password = "1234";

    private DBConn() {
        try {
            Class.forName(DRIVER_NAME);
            this.conn = DriverManager.getConnection(url, username, password);
            System.out.println("드라이버에 연결 되었습니다");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static DBConn getInstance() {
        if (instance == null) {
            instance = new DBConn();
        }
        return instance;
    }

    public Connection getConnection() {
        return this.conn;
    }
}
