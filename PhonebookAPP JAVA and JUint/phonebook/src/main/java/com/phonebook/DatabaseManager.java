package com.phonebook;

import java.io.File;
import java.sql.*;
import java.util.regex.Pattern;

public class DatabaseManager {

    private static final String DATABASE_URL = "jdbc:sqlite:PhoneBook.db";
    private static final int MAX_NAME_LENGTH = 255;
    private static final int MAX_EMAIL_LENGTH = 254;

    public static void createTable() {
        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
                Statement stmt = conn.createStatement()) {

            String sql = """
                        CREATE TABLE IF NOT EXISTS PhoneBook (
                            id INTEGER PRIMARY KEY AUTOINCREMENT,
                            phoneNumber TEXT UNIQUE NOT NULL,
                            name TEXT NOT NULL,
                            email TEXT NOT NULL
                        )
                    """;
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean addPhoneNumber(String phoneNumber, String name, String email) {
        if (!isValidPhoneNumber(phoneNumber) || !isValidName(name) || !isValidEmail(email)) {
            return false;
        }
        if (isPhoneNumberExists(phoneNumber)) {
            return false;
        }

        String sql = "INSERT INTO PhoneBook (phoneNumber, name, email) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, phoneNumber);
            pstmt.setString(2, name);
            pstmt.setString(3, email);
            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updatePhoneNumber(String oldPhoneNumber, String newPhoneNumber, String newName,
            String newEmail) {
        if (!isValidPhoneNumber(newPhoneNumber) || !isValidName(newName) || !isValidEmail(newEmail)) {
            return false;
        }
        if (isPhoneNumberExists(newPhoneNumber)) {
            return false;
        }

        String sql = "UPDATE PhoneBook SET phoneNumber = ?, name = ?, email = ? WHERE phoneNumber = ?";

        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newPhoneNumber);
            pstmt.setString(2, newName);
            pstmt.setString(3, newEmail);
            pstmt.setString(4, oldPhoneNumber);
            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deletePhoneNumber(String phoneNumber) {
        String sql = "DELETE FROM PhoneBook WHERE phoneNumber = ?";

        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, phoneNumber);
            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String searchPhoneNumberByName(String name) {
        String sql = "SELECT phoneNumber FROM PhoneBook WHERE name = ?";

        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            return rs.next() ? rs.getString("phoneNumber") : null;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String searchPhoneNumberByEmail(String email) {
        String sql = "SELECT phoneNumber FROM PhoneBook WHERE email = ?";

        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            return rs.next() ? rs.getString("phoneNumber") : null;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void showAllPhoneNumbers() {
        String sql = "SELECT phoneNumber, name, email FROM PhoneBook";

        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.printf("Phone: %s, Name: %s, Email: %s%n",
                        rs.getString("phoneNumber"), rs.getString("name"), rs.getString("email"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean deleteDatabase() {
        try {
            Connection conn = DriverManager.getConnection(DATABASE_URL);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("DROP TABLE IF EXISTS PhoneBook");
            stmt.close();
            conn.close();
            File dbFile = new File("PhoneBook.db");
            return dbFile.delete();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber != null && phoneNumber.matches("^01[0-9]{9}$");
    }

    private static boolean isValidName(String name) {
        return name != null && name.length() <= MAX_NAME_LENGTH && name.matches("^[a-zA-Z ]+$");
    }

    private static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email != null && email.length() <= MAX_EMAIL_LENGTH && Pattern.matches(emailRegex, email);
    }

    public static boolean isDatabaseExists() {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL)) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet rs = metaData.getTables(null, null, "PhoneBook", null);
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    private static boolean isPhoneNumberExists(String phoneNumber) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL)) {
            String sql = "SELECT 1 FROM PhoneBook WHERE phoneNumber = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, phoneNumber);
                try (ResultSet resultSet = statement.executeQuery()) {
                    return resultSet.next();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
