public class Journal extends LibraryObject {
    private int volume;
    private int number;

    public Journal(String id, String title, int year, double averageRating, int numberOfReviews, int maxBorrowingTime, int volume, int number) {
        super(id, title, year, averageRating, numberOfReviews, maxBorrowingTime);
        this.volume = volume;
        this.number = number;
    }



    @Override
    public void displayDetails() {
        System.out.println("Journal Details:");
        System.out.println("ID: " + getId());
        System.out.println("Title: " + getTitle());
        System.out.println("Year: " + getYear());
        System.out.println("Volume: " + volume);
        System.out.println("Number: " + number);
        System.out.println("Average Rating: " + getAverageRating());
        System.out.println("Number of Reviews: " + getNumberOfReviews());
        System.out.println("Status: " + getStatus());
        System.out.println("Max Borrowing Time: " + getMaxBorrowingTime() + " days");
        System.out.println("Due Date: " + getDueDate());
    }
}