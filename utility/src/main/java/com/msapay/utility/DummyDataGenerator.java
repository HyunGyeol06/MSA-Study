package com.msapay.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class DummyDataGenerator {
    private static final String DB_URL = "jdbc:mysql://localhost:8090/msa_study?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";

    private static final String DB_USER = "mysqluser";

    private static final String DB_PASSWORD = "mysqlpw";

    private static final String[] ADDRESS = {"강남구", "관악구", "서초구"};

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            generateDummyDate(conn);

            conn.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void generateDummyDate(Connection conn) throws SQLException{
        String insertQuery = "INSERT INTO membership (membership_id, address, email, is_corp, is_valid, name) VALUES (?, ?, ?, ?, ?, ?)";
        Random random = new Random();

        PreparedStatement pstmt = conn.prepareStatement(insertQuery);

        int numberOfData = 1000;

        for (int i = 1; i <= numberOfData; i++) {
            pstmt.setLong(1, i);
            pstmt.setString(2, ADDRESS[random.nextInt(ADDRESS.length)]);
            pstmt.setString(3, "email_" + i + "@example.com");
            pstmt.setBoolean(4, random.nextBoolean());
            pstmt.setBoolean(5, random.nextBoolean());
            pstmt.setString(6, "user " + i);
            pstmt.executeUpdate();
        }

        pstmt.close();
    }

}
