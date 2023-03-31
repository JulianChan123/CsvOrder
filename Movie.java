/**
 * The `Movie` class represents a movie with its relevant attributes such as id,
 * title, overview, popularity,
 * vote average, vote count, and its position in a linked list with the `prev`
 * and `next` attributes.
 * 
 * @author JULIAN CHAN PALOMO
 * @author RUTH BETZABE CASTRO ACOSTA
 */
public class Movie {
    /**
     * The row number of the movie in the CSV file.
     */
    public int nRow;
    /**
     * The unique identifier of the movie.
     */
    public int id;
    /**
     * The original title of the movie.
     */
    public String original_title;
    /**
     * The overview or summary of the movie.
     */
    public String overview;
    /**
     * The popularity score of the movie.
     */
    public double popularity;
    /**
     * The average rating of the movie.
     */
    public double vote_average;
    /**
     * The number of votes the movie has received.
     */
    public int vote_count;
    /**
     * The previous movie in the linked list.
     */
    public Movie prev;
    /**
     * The next movie in the linked list.
     */
    public Movie next; // Referencia a la película siguiente en la lista

    /**
     * Constructs a new `Movie` object with the given attributes.
     *
     * @param nRow           the row number of the movie in the CSV file.
     * @param id             the unique identifier of the movie.
     * @param original_title the original title of the movie.
     * @param overview       the overview or summary of the movie.
     * @param popularity     the popularity score of the movie.
     * @param vote_average   the average rating of the movie.
     * @param vote_count     the number of votes the movie has received.
     */

    public Movie(int nRow, int id, String original_title, String overview, double popularity, double vote_average,
            int vote_count) {
        this.nRow = nRow;
        this.id = id;
        this.original_title = original_title;
        this.overview = overview;
        this.popularity = popularity;
        this.vote_average = vote_average;
        this.vote_count = vote_count;
        this.prev = null;
        this.next = null;
    }

    /**
     * Returns a string representation of the `Movie` object.
     *
     * @return a string representation of the `Movie` object.
     */
    @Override
    public String toString() {
        return "Movie [nRow=" + nRow + ", id=" + id + ", original_title=" + original_title + ", overview=" + overview
                + ", popularity=" + popularity + ", vote_average=" + vote_average + ", vote_count=" + vote_count
                + ", prev=" + prev + ", next=" + next + "]";
    }

}
