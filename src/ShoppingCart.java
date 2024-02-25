import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<String> likedBooks;
    private List<String> desiredBooks;

    public ShoppingCart() {
        likedBooks = new ArrayList<>();
        desiredBooks = new ArrayList<>();
    }

    public void addLikedBook(String bookTitle) {
        likedBooks.add(bookTitle);
    }

    public void addDesiredBook(String bookTitle) {
        desiredBooks.add(bookTitle);
    }

    public List<String> getLikedBooks() {
        return likedBooks;
    }

    public List<String> getDesiredBooks() {
        return desiredBooks;
    }
}
