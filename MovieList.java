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

    public void swap(Movie left, Movie right){
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

    public Movie getMiddle(Movie left, Movie right) {
        int index = this.indexOf(left);
        int index2 = this.indexOf(right);
        int middle = (index + index2) / 2;
        return this.getMovie(middle);
    }

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

