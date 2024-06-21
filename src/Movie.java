public class Movie extends LibraryObject {
    private String director;
    public Movie(String id, String title,int year, double averageRating, int numberOfReviews,int maxBorrowingTime, String director) {
        super(id, title, year, averageRating, numberOfReviews, maxBorrowingTime);
        this.director = director;
    }

    public String getDirector() { return director;}

    @Override
    public void displayDetails() {
        System.out.println("Movie Details:");
        System.out.println("ID: " + getId());
        System.out.println("Title: " + getTitle());
        System.out.println("Year: " + getYear());
        System.out.println("Director: " + getDirector());
        System.out.println("Average Rating: " + getAverageRating());
        System.out.println("Number of Reviews: " + getNumberOfReviews());
        System.out.println("Status: " + getStatus());
        System.out.println("Max Borrowing Time: " + getMaxBorrowingTime() + " days");
        System.out.println("Due Date: " + getDueDate());
    }
}