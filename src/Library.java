import java.util.ArrayList;
import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Library {

    private ArrayList<LibraryObject> objects;
    public Library(){
        objects = new ArrayList<>();
    }

    public void addObject(LibraryObject object){
        objects.add(object);
    }

    public void loadItemsFromFile(String filename) {
        File file = new File(filename);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");

                switch (data[0].toLowerCase()) {
                    case "movie":
                        addObject(new Movie(data[1].trim(), data[2].trim(),Integer.parseInt(data[3].trim()), 0.0, 0, 7, data[4].trim()));
                        break;
                    case "book":
                        addObject(new Book(data[1].trim(), data[2].trim(), Integer.parseInt(data[3].trim()), 0.0, 0, 28, data[4].trim(), Integer.parseInt(data[5].trim())));
                        break;
                    case "journal":
                        addObject(new Journal(data[1].trim(),data[2].trim(), Integer.parseInt(data[3].trim()), 0.0, 0, 14, Integer.parseInt(data[4].trim()), Integer.parseInt(data[5].trim())));
                        break;
                    default:
                        System.out.println("Unsupported item type: " + data[0]);
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
            e.printStackTrace();
        }
    }

   //Initial list of the library items
    public void displayAllItems() {

        System.out.println("Listing All Library Items: ");
        for (LibraryObject object : objects) {
            System.out.println("ID: " + object.getId() + ", Type: " + object.getClass().getSimpleName() + ", Title: " + object.getTitle());
        }
    }



    public void listAllObjects(){
        System.out.println("Listing All Library Items: ");
        for (LibraryObject object : objects) {
            System.out.println("Average Rating: " + object.getAverageRating() + ", Number of Reviewers: " + object.getNumberOfReviews() +  ", ID: " + object.getId() + ", Type: " + object.getClass().getSimpleName() + ", Title: " + object.getTitle());
        }
    }

    public void sortItems() {
        Collections.sort(objects);
    }

    public LibraryObject searchByID(String id){
        for(LibraryObject object : objects){
            if(object.getId().equals(id)){
                return object;
            }
        }
        return null;
    }

    public List<LibraryObject> searchByTitle(String phrase){
        List<LibraryObject> foundObjects = new ArrayList<>();
        for(LibraryObject object : objects){
            if(object.getTitle().toLowerCase().contains(phrase.toLowerCase())){
                foundObjects.add(object);
            }
        }
        return foundObjects;
    }


}
