
/**

A class representing the main entry point of the program for sorting a list of movies.
@author JULIAN CHAN PALOMO
@author RUTH BETZABE CASTRO ACOSTA
*/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    /**
     * Sorts a doubly linked list of Movie objects using a chosen sorting algorithm
     * and outputs the sorted data to a CSV file.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        // read the CSV file
        String csvFile = "movieTest.csv";
        String line = "";
        String cvsSplitBy = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";

        // create the doubly linked list
        MovieList moviesList = new MovieList();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            // skip the header line
            br.readLine();

            while ((line = br.readLine()) != null) {
                // split the line by comma
                String[] movieData = line.split(cvsSplitBy, -1);

                // create a new Movie object with the data
                int nRow = Integer.parseInt(movieData[0]);
                int id = Integer.parseInt(movieData[1]);
                String original_title = movieData[2];
                String overview = movieData[3];
                double popularity = Double.parseDouble(movieData[4]);
                double vote_average = Double.parseDouble(movieData[5]);
                int vote_count = Integer.parseInt(movieData[6]);
                Movie movie = new Movie(nRow, id, original_title, overview, popularity, vote_average, vote_count);

                // add the Movie object to the doubly linked list
                moviesList.addMovie(nRow, id, original_title, overview.replaceAll("\"", ""), popularity, vote_average,
                        vote_count);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // ask user for sorting algorithm
        Scanner scanner = new Scanner(System.in);
        Sort sort = new Sort();
        System.out.println("Select sorting algorithm:");
        System.out.println("1. Radix Sort");
        System.out.println("2. Merge Sort");
        System.out.println("3. Quick Sort");
        System.out.println("4. BinaryInsertionSort");
        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();

        // sort the doubly linked list using the selected algorithm
        switch (choice) {
            case 1:
                sort.radixSort(moviesList);
                break;
            case 2:
                sort.mergeSort(moviesList);
                break;
            case 3:
                sort.quickSort(moviesList);
                break;
            case 4:
                sort.binaryInsertionSort(moviesList);
                break;
            default:
                System.out.println("Invalid choice.");
                System.exit(0);
        }

        // output sorted data to a new CSV file
        String outputFile = "movies_sorted.csv";
        try (PrintWriter writer = new PrintWriter(new FileWriter(outputFile))) {
            // write header line
            writer.println("nRow,id,original_title,overview,popularity,vote_average,vote_count");

            // write each movie's data to the file
            Movie current = moviesList.getHead();
            while (current != null) {
                writer.println(current.nRow + "," + current.id + "," + current.original_title + "," + current.overview
                        + "," + current.popularity + "," + current.vote_average + "," + current.vote_count);
                current = current.next;
            }

        } catch (IOException e) {
            System.out.println("Error writing to file.");
        }
    }
}