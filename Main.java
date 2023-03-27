import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

    public static void radixSort(MovieList moviesList) {
        // find the maximum value of popularity in the list
        double maxPopularity = 0;
        Movie current = moviesList.getHead();
        while (current != null) {
            if (current.popularity > maxPopularity) {
                maxPopularity = current.popularity;
            }
            current = current.next;
        }
    
        // perform radix sort on the popularity field
        int exp = 1;
        while (maxPopularity / exp > 0) {
            Movie[] temp = new Movie[moviesList.getSize()];
            int[] count = new int[10];
    
            // count the number of movies with each popularity digit
            current = moviesList.getHead();
            while (current != null) {
                int bucketIndex = (int) ((current.popularity / exp) % 10);
                count[bucketIndex]++;
                current = current.next;
            }
    
            // compute the cumulative count of movies with each popularity digit
            for (int i = 1; i < 10; i++) {
                count[i] += count[i - 1];
            }
    
            // place each movie in the appropriate position in the temp array based on its popularity digit
            current = moviesList.getHead();
            while (current != null) {
                int bucketIndex = (int) ((current.popularity / exp) % 10);
                temp[count[bucketIndex] - 1] = current;
                count[bucketIndex]--;
                current = current.next;
            }
    
            // copy the sorted movies back into the original list
            current = moviesList.getHead();
            for (int i = 0; i < moviesList.getSize(); i++) {
                current = temp[i];
                if (i == 0) {
                    moviesList.head = current;
                }
                if (i == moviesList.getSize() - 1) {
                    moviesList.tail = current;
                }
                if (i > 0) {
                    temp[i - 1].next = current;
                    current.prev = temp[i - 1];
                }
            }
    
            exp *= 10;
        }
    }

    public static void mergeSort(MovieList moviesList) {
        if (moviesList.getSize() > 1) {
            MovieList leftList = new MovieList();
            MovieList rightList = new MovieList();
            int middleIndex = moviesList.getSize() / 2;
    
            // split the list into two halves
            Movie currentMovie = moviesList.getHead();
            int currentIndex = 0;
            while (currentMovie != null) {
                if (currentIndex < middleIndex) {
                    leftList.addMovie(currentMovie.nRow, currentMovie.id, currentMovie.original_title, currentMovie.overview, currentMovie.popularity, currentMovie.vote_average, currentMovie.vote_count);
                } else {
                    rightList.addMovie(currentMovie.nRow, currentMovie.id, currentMovie.original_title, currentMovie.overview, currentMovie.popularity, currentMovie.vote_average, currentMovie.vote_count);
                }
                currentMovie = currentMovie.next;
                currentIndex++;
            }
    
            // recursively sort each half
            mergeSort(leftList);
            mergeSort(rightList);
    
            // merge the two halves
            Movie leftMovie = leftList.getHead();
            Movie rightMovie = rightList.getHead();
            moviesList.clear();
            while (leftMovie != null && rightMovie != null) {
                if (leftMovie.popularity <= rightMovie.popularity) {
                    moviesList.addMovie(leftMovie.nRow, leftMovie.id, leftMovie.original_title, leftMovie.overview, leftMovie.popularity, leftMovie.vote_average, leftMovie.vote_count);
                    leftMovie = leftMovie.next;
                } else {
                    moviesList.addMovie(rightMovie.nRow, rightMovie.id, rightMovie.original_title, rightMovie.overview, rightMovie.popularity, rightMovie.vote_average, rightMovie.vote_count);
                    rightMovie = rightMovie.next;
                }
            }
            while (leftMovie != null) {
                moviesList.addMovie(leftMovie.nRow, leftMovie.id, leftMovie.original_title, leftMovie.overview, leftMovie.popularity, leftMovie.vote_average, leftMovie.vote_count);
                leftMovie = leftMovie.next;
            }
            while (rightMovie != null) {
                moviesList.addMovie(rightMovie.nRow, rightMovie.id, rightMovie.original_title, rightMovie.overview, rightMovie.popularity, rightMovie.vote_average, rightMovie.vote_count);
                rightMovie = rightMovie.next;
            }
        }
    }

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
                moviesList.addMovie(nRow, id, original_title, overview.replaceAll("\"", ""), popularity, vote_average, vote_count);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        // ask user for sorting algorithm
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select sorting algorithm:");
        System.out.println("1. Radix Sort");
        System.out.println("2. Merge Sort");
        System.out.println("3. Quick Sort");
        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();
    
        // sort the doubly linked list using the selected algorithm
        switch (choice) {
            case 1:
                radixSort(moviesList);
                break;
            case 2:
                mergeSort(moviesList);
                break;
            case 3:
                // implement Quick Sort algorithm
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
                writer.println(current.nRow + "," + current.id + "," + current.original_title + "," + current.overview + "," + current.popularity + "," + current.vote_average + "," + current.vote_count);
                current = current.next;
            }


        } catch (IOException e) {
            System.out.println("Error writing to file.");
        }
    }
}