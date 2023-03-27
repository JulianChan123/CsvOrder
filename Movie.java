public class Movie {
    public int nRow;
    public int id;
    public String original_title;
    public String overview;
    public double popularity;
    public double vote_average;
    public int vote_count;
    public Movie prev;
    public Movie next;
    
    public Movie(int nRow, int id, String original_title, String overview, double popularity, double vote_average, int vote_count) {
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

    @Override
    public String toString() {
        return "Movie [nRow=" + nRow + ", id=" + id + ", original_title=" + original_title + ", overview=" + overview
                + ", popularity=" + popularity + ", vote_average=" + vote_average + ", vote_count=" + vote_count
                + ", prev=" + prev + ", next=" + next + "]";
    }

    
}
   

