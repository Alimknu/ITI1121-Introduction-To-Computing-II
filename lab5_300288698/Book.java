public class Book {

    String author;
    String title;
    int year;

    public Book (String author, String title, int year) {
        this.author = author;
        this.title = title;
        this.year = year;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public boolean equals(Object other) {
        if (this == other){ //they are both the same (including null)
            return true;
        }
        if (other == null){ //one is null situation
            return false;
        }
        if (getClass() != other.getClass()){//they aren't the same class
            return false;
        }

        Book otherBook = (Book) other; //Make the other a book so that we can actually check everything

        if (author == null){
            if (otherBook.author != null){
                return false;
            }
        }
        if (title == null){
            if (otherBook.title != null){
                return false;
            }
        }
        if (year != otherBook.year){
            return false;
        }

        return true;

    }

    public String toString() {
        String str = new String();
    	str = getAuthor() + ": " + getTitle() + " (" + getYear() + ")";
    	return  str;
    }
}