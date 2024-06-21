import java.text.SimpleDateFormat;
import java.util.Calendar;

public abstract class LibraryObject implements Comparable<LibraryObject>{
    private String id;
    private String title;
    private int year;

    public boolean isAvailable = true;
    private double averageRating;
    private int numberOfReviews;
    private int maxBorrowingTime;
    private String dueDate;




    public LibraryObject(String id, String title, int year, double averageRating, int numberOfReviews, int maxBorrowingTime) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.averageRating = averageRating;
        this.numberOfReviews = numberOfReviews;
        this.maxBorrowingTime = maxBorrowingTime;
    }

    //Getters
    public String getId() {return id; }
    public String getTitle() {return title;}
    public int getYear() { return year;}
    public double getAverageRating() { return averageRating;}
    public int getNumberOfReviews() { return numberOfReviews;}
    public int getMaxBorrowingTime() {return maxBorrowingTime;}
    public String getDueDate() {return dueDate;}

    public boolean isAvailable() { return isAvailable;}


    public String getStatus() {
        return isAvailable ? "available" : "on loan";
    }

    public void borrowItem(){
        if(isAvailable){
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, getMaxBorrowingTime());
            dueDate = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
            isAvailable= false;
            System.out.println("Item borrowed. Due back on: " + dueDate);
        }else{
            System.out.println("Item is currently currently booked out");
        }
    }

    public void returnItem(){
        if(!isAvailable){
            isAvailable = true;
            dueDate = null;
            System.out.println("This item is returned");
        }else{
            System.out.println("This item was not booked out");
        }
    }

    public void rateItem(double rating){
        double totalRating = this.averageRating * this.numberOfReviews;
        this.numberOfReviews++;
        this.averageRating = (totalRating + rating) / this.numberOfReviews;
        this.averageRating = Math.round(this.averageRating * 100.0) / 100.0;
        System.out.println("New average rating: " + averageRating) ;
    }


    public abstract void displayDetails();

    @Override
    public int compareTo(LibraryObject other) {
        if (this.averageRating == other.averageRating) {
            return this.id.compareTo(other.id);
        }
        return Double.compare(other.averageRating, this.averageRating);
    }
}
