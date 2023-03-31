/**
 * 
 * A class representing a linked list of Movie objects.
 * 
 * 
 * /**
 * 
 * @author JULIAN CHAN PALOMO
 * @author RUTH BETZABE CASTRO ACOSTA
 * 
 *     Constructs an empty MovieList.
 */
/**
 * This class represents a linked list of Movie objects.
 */
public class MovieList {
    /**
     * The first movie in the list.
     */
    public Movie head;
    /**
     * The last movie in the list.
     */
    public Movie tail;
    /**
     * The number of movies in the list.
     */
    public int size;

    public MovieList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * 
     * Adds a Movie to the end of the list.
     * 
     * @param nRow           the row number of the Movie in a data source
     * @param id             the unique identifier of the Movie
     * @param original_title the original title of the Movie
     * @param overview       a brief overview of the Movie
     * @param popularity     the popularity of the Movie
     * @param vote_average   the average rating of the Movie
     * @param vote_count     the number of votes cast for the Movie
     */
    public void addMovie(int nRow, int id, String original_title, String overview, double popularity,
            double vote_average, int vote_count) {
        Movie movie = new Movie(nRow, id, original_title, overview, popularity, vote_average, vote_count);
        if (this.head == null) {
            this.head = movie;
            this.tail = movie;
        } else {
            this.tail.next = movie;
            movie.prev = this.tail;
            this.tail = movie;
        }
        this.size++;
    }

    /**
     * 
     * Clears the MovieList.
     */
    public void clear() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * 
     * Returns the first Movie in the list.
     * 
     * @return the first Movie in the list
     */
    public Movie getHead() {
        return this.head;
    }

    /**
     * 
     * Returns the last Movie in the list.
     * 
     * @return the last Movie in the list
     */
    public Movie getTail() {
        return this.tail;
    }

    /**
     * 
     * Returns the number of Movies in the list.
     * 
     * @return the number of Movies in the list
     */
    public int getSize() {
        return this.size;
    }

    /**
     * 
     * Inserts a Movie before a given Movie in the list.
     * 
     * @param movie the Movie to insert
     * @param left  the Movie to insert before
     */
    public void insertBefore(Movie movie, Movie left) {
        if (left == null) {
            this.head = movie;
        } else {
            left.prev.next = movie;
            movie.prev = left.prev;
        }
        left.prev = movie;
        movie.next = left;
        this.size++;
    }

    /**
     * 
     * Swaps the positions of two Movies in the list.
     * 
     * @param left  the first Movie to swap
     * @param right the second Movie to swap
     */
    public void swap(Movie left, Movie right) {
        if (left == right) {
            return;
        }
        if (left.next == right) {
            left.next = right.next;
            right.prev = left.prev;
            left.prev = right;
            right.next = left;
        } else if (right.next == left) {
            right.next = left.next;
            left.prev = right.prev;
            right.prev = left;
            left.next = right;
        } else {
            Movie temp = left.next;
            left.next = right.next;
            right.next = temp;
            temp = left.prev;
            left.prev = right.prev;
            right.prev = temp;
        }
        if (left.prev == null) {
            this.head = left;
        } else {
            left.prev.next = left;
        }
        if (left.next == null) {
            this.tail = left;
        } else {
            left.next.prev = left;
        }
        if (right.prev == null) {
            this.head = right;
        } else {
            right.prev.next = right;
        }
        if (right.next == null) {
            this.tail = right;
        } else {
            right.next.prev = right;
        }
    }

    /**
     * 
     * Removes a movie from the list.
     * 
     * @param movie the movie to remove
     */
    public void remove(Movie movie) {
        if (movie.prev != null) {
            movie.prev.next = movie.next;
        }
        if (movie.next != null) {
            movie.next.prev = movie.prev;
        }
        if (movie == this.head) {
            this.head = movie.next;
        }
        if (movie == this.tail) {
            this.tail = movie.prev;
        }
        this.size--;
    }

    /**
     * 
     * Inserts a movie after a given movie in the list.
     * 
     * @param movie the movie to insert
     * @param left  the movie to insert after
     */
    public void insertAfter(Movie movie, Movie left) {
        if (left == null) {
            this.head = movie;
        } else {
            left.next.prev = movie;
            movie.next = left.next;
        }
        left.next = movie;
        movie.prev = left;
        this.size++;
    }

    /**
     * 
     * Returns the index of a given movie in the list.
     * 
     * @param left the movie to find
     * @return the index of the movie in the list, or -1 if the movie is not in the
     *         list
     */
    public int indexOf(Movie left) {
        int index = 0;
        Movie current = this.head;
        while (current != null) {
            if (current == left) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }

    /**
     * 
     * Returns the movie at the given index in the list.
     * 
     * @param i the index of the movie to return
     * @return the movie at the given index, or null if the index is out of bounds
     */
    public Movie getMovie(int i) {
        int index = 0;
        Movie current = this.head;
        while (current != null) {
            if (index == i) {
                return current;
            }
            current = current.next;
            index++;
        }
        return null;
    }

    /**
     * 
     * Returns the middle Movie object between the left and right Movie objects.
     * 
     * @param left  the left Movie object
     * @param right the right Movie object
     * @return the middle Movie object
     */
    public Movie getMiddle(Movie left, Movie right) {
        int index = this.indexOf(left);
        int index2 = this.indexOf(right);
        int middle = (index + index2) / 2;
        return this.getMovie(middle);
    }

    /**
     * 
     * Replaces the Movie object at the specified index with the given Movie object.
     * 
     * @param j     the index of the Movie object to replace
     * @param movie the Movie object to replace with
     */
    public void set(int j, Movie movie) {
        int index = 0;
        Movie current = this.head;
        while (current != null) {
            if (index == j) {
                current = movie;
                return;
            }
            current = current.next;
            index++;
        }
    }

}
