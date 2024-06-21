public class Book extends LibraryObject {
    private String author;
    private int pages;

    public Book(String id, String title,int year, double averageRating, int numberOfReviews,int maxBorrowingTime, String author,int pages) {
        super(id, title, year,averageRating, numberOfReviews, maxBorrowingTime);
        this.author = author;
        this.pages = pages;
    }

    //Getter
    public String getAuthor() { return author;}
    public int getPages() { return pages;}



    @Override
    public void displayDetails() {
        System.out.println("Book Details:");
        System.out.println("ID: " + getId());
        System.out.println("Title: " + getTitle());
        System.out.println("Year: " + getYear());
        System.out.println("Author: " + getAuthor());
        System.out.println("Pages: " + getPages());
        System.out.println("Average Rating: " + getAverageRating());
        System.out.println("Number of Reviews: " + getNumberOfReviews());
        System.out.println("Status: " + getStatus());
        System.out.println("Max Borrowing Time: " + getMaxBorrowingTime() + " days");
        System.out.println("Due Date: " + getDueDate());
    }
}
