import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class SellerRegistrationService {
    public boolean registerSeller(String username) {
            // Register the user as a seller in the database
            // Return true if successful, false otherwise
    }

    public boolean addBookForSale(String username, String bookTitle, double price, int deliveryTime, BookCondition condition) {
        // Add a book for sale by the specified seller
        // Return true if successful, false otherwise
    }
}
