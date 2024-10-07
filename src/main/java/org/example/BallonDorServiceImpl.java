package org.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BallonDorServiceImpl implements Impl{
    private Connection conn;
    private int year;
    private String playerName;

    public void connect() {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "BigZ2301";

        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void getBallonDorWinners(int year) {
        setYear(year);
        String tableName = "Ballondor" + getYear();
        String sql = "SELECT Name FROM " + tableName;

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            System.out.println("Ballon d'Or top 3 for " + getYear() + ":");
            while (rs.next()) {
                playerName = rs.getString("Name");
                System.out.println(playerName);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void closeConnection() {
        try {
            if (conn != null) conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public int getYear() {
        return year;
    }


    public void setYear(int year) {
        this.year = year;
    }
}
