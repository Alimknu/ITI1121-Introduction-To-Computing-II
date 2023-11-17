import java.util.Comparator;

public class BookComparator implements Comparator<Book> {

    public int compare(Book one, Book two) {
        if (one == null || two == null){
            return -1;
        }
        else if ((one.getAuthor() == null && two.getAuthor() !=null)){
            return -1;
        }
        else if ((one.getAuthor() != null && two.getAuthor() == null)){
            return 1;
        }
        else if (one.getAuthor().compareTo(two.getAuthor()) < 0){
            return -1;
        }
        else if (one.getAuthor().compareTo(two.getAuthor()) > 0){
            return 1;
        }
        else{
            if ((one.getTitle() == null && two.getTitle() != null)){
                return -1;
            }
            else if ((one.getTitle() != null && two.getTitle() == null)){
                return 1;
            }
            else if (one.getTitle().compareTo(two.getTitle()) < 0){
                return -1;
            }
            else if (one.getTitle().compareTo(two.getTitle()) > 0) {
                return 1;
            }
            else{
                if (one.getYear() > two.getYear()){
                    return 1;
                }
                else if (one.getYear() < two.getYear()){
                    return -1;
                }
                return 0;
            }
        }
      }
}