import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        library.loadItemsFromFile("library.txt");
        library.displayAllItems();
        System.out.println();
        showMenu(library);
    }

    public static void showMenu(Library library) {
        Scanner scanner = new Scanner(System.in);
        String userChoice;

        do {
            System.out.println("Enter 'q' to quit, 'i' to search by ID, 's' to sort, or any other key to search by title:");
            userChoice = scanner.nextLine().trim().toLowerCase();

            switch (userChoice) {
                case "q":
                    System.out.println("Exiting the program.");
                    break;
                case "i":
                    searchByIDChoice(library, scanner);
                    break;
                case "s":
                    library.sortItems();
                    library.listAllObjects();
                    break;
                default:
                    searchByTitleChoice(library, scanner);
                    break;
            }
        } while (!userChoice.equals("q"));

        scanner.close();
    }

    private static void searchByIDChoice(Library library, Scanner scanner) {
        String secondChoice;
        do {
            System.out.println("Enter ID:");
            String id = scanner.nextLine();
            LibraryObject object = library.searchByID(id);
            if (object != null) {
                object.displayDetails();
                System.out.println("Enter 'i' to search another item by ID, or press any other key to select this item");
                secondChoice = scanner.nextLine().trim().toLowerCase();
                if (!secondChoice.equals("i")) {
                    performAction(object, scanner);
                }
            } else {
                System.out.println("Item not found. Press any button to try again or enter 'n' to stop.");
                secondChoice = scanner.nextLine().trim().toLowerCase();
                if(secondChoice.equals("n")){
                    break;
                }else{
                    secondChoice = "i";
                }
            }
        } while (secondChoice.equals("i"));
    }

    private static void searchByTitleChoice(Library library, Scanner scanner) {
        System.out.println("Enter title phrase:");
        String phrase = scanner.nextLine();
        List<LibraryObject> foundObjects = library.searchByTitle(phrase);
        if (foundObjects.isEmpty()) {
            System.out.println("No items found with the phrase: " + phrase);
        } else {
            for (int i = 0; i < foundObjects.size(); i++) {
                System.out.println((i + 1) + ": " + foundObjects.get(i).getTitle());
            }
            System.out.println("Select the number of the item you want to interact with, or enter '0' to cancel:");

            // Input validation
            int index = -1;
            while (true) {
                String input = scanner.nextLine().trim();
                try {
                    index = Integer.parseInt(input) - 1;
                    if (index >= 0 && index < foundObjects.size() || input.equals("0")) {
                        break;
                    } else {
                        System.out.println("Invalid input. Please enter a valid number.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                }
            }

            if (index >= 0) {
                LibraryObject selectedObject = foundObjects.get(index);
                selectedObject.displayDetails();
                performAction(selectedObject, scanner);
            }
        }
    }

    private static void performAction(LibraryObject object, Scanner scanner) {
        if(!object.isAvailable){
            System.out.println("This item is booked until: " + object.getDueDate());
            System.out.println("Enter 'r' to return the item, enter 'a' to rate the item, or enter any other key to restart");
            String action = scanner.nextLine().trim().toLowerCase();
            switch (action) {
                case "r":
                    object.returnItem();
                    break;
                case "a":
                    double rating = -1;
                    while(rating < 0 || rating > 10){
                        System.out.println("Enter your rating (0 to 10):");
                        try{
                            rating = Double.parseDouble(scanner.nextLine());
                            if(rating < 0 || rating > 10){
                                System.out.println("Invalid rating. Please enter a number between 0 and 10.");
                            }else{
                                object.rateItem(rating);
                            }
                        } catch (NumberFormatException e){
                            System.out.println("Invalid input. Returning to Main Menu.");
                            break;
                        }
                    }
                    break;
                default:
                    break;
            }
        }else{
            System.out.println("Enter 'b' to book the item, enter 'a' to rate the item, or enter any other key to restart:");
            String action = scanner.nextLine().trim().toLowerCase();
            switch (action) {
                case "b":
                    if (object.isAvailable()) {
                        object.borrowItem();
                    } else {
                        System.out.println("Item is currently on loan.");
                    }
                    break;
                case "a":
                    double rating = -1;
                    while(rating < 0 || rating > 10){
                        System.out.println("Enter your rating (0 to 10):");
                        try{
                            rating = Double.parseDouble(scanner.nextLine());
                            if(rating < 0 || rating > 10){
                                System.out.println("Invalid rating. Please enter a number between 0 and 10.");
                            }else{
                                object.rateItem(rating);
                            }
                        } catch (NumberFormatException e){
                            System.out.println("Invalid input. Returning to Main Menu.");
                            break;
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
