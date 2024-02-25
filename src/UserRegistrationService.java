import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRegistrationService {

    public boolean registerUser(String username, String password) throws SQLException {
        Connection conn = DatabaseUtil.getConnection();
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean loginUser(String username, String password) throws SQLException {
        Connection conn = DatabaseUtil.getConnection();
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                System.out.println("User found.");
                return true;
            } else {
                System.out.println("User not found.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean registerUser(String username, String password) {
        // Register the user in the database
        // Return true if successful, false otherwise
    }

    public boolean registerSeller(String username) {
        // Register the user as a seller in the database
        // Return true if successful, false otherwise
    }

    public boolean addBookForSale(String username, String bookTitle, double price, int deliveryTime, BookCondition condition) {
        // Add a book for sale by the specified seller
        // Return true if successful, false otherwise
    }
}

