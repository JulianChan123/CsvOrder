public class MovieList {
    public Movie head;
    public Movie tail;
    public int size;
    
    public MovieList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }
    
    public void addMovie(int nRow, int id, String original_title, String overview, double popularity, double vote_average, int vote_count) {
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

    public void clear() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public Movie getHead() {
        return this.head;
    }

    public Movie getTail() {
        return this.tail;
    }

    public int getSize() {
        return this.size;
    }




}

