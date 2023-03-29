public class Sort {
    public void radixSort(MovieList moviesList){
        Double max = 0.0;
        Movie currentMovie = moviesList.getHead();
        while (currentMovie != null) {
            if (currentMovie.popularity > max) {
                max = currentMovie.popularity;
            }
            currentMovie = currentMovie.next;
        }
        int maxDigit = 0;
        while (max > 0) {
            max /= 10;
            maxDigit++;
        }
        Double divisor = 1.0;
        for (int i = 0; i < maxDigit; i++) {
            int[] count = new int[10];
            currentMovie = moviesList.getHead();
            while (currentMovie != null) {
                count[(int) ((currentMovie.popularity / divisor) % 10)]++;
                currentMovie = currentMovie.next;
            }
            for (int j = 1; j < 10; j++) {
                count[j] += count[j - 1];
            }
            Movie[] temp = new Movie[moviesList.getSize()];
            currentMovie = moviesList.getTail();
            while (currentMovie != null) {
                temp[--count[(int) ((currentMovie.popularity / divisor) % 10)]] = currentMovie;
                currentMovie = currentMovie.prev;
            }
            for (int j = 0; j < moviesList.getSize(); j++) {
                moviesList.set(j, temp[j]);
            }
            divisor *= 10;
        }
    }

    public void mergeSort(MovieList moviesList) {
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

    public void quickSort(MovieList moviesList) {
        quickSort(moviesList, moviesList.getHead(), moviesList.getTail());
    }

    private void quickSort(MovieList moviesList, Movie left, Movie right) {
        if (left == null || right == null || left == right || left.prev == right) {
            return;
        }

        Movie pivot = partition(moviesList, left, right);

        quickSort(moviesList, left, pivot.prev);
        quickSort(moviesList, pivot.next, right);
    }

    private Movie partition(MovieList moviesList, Movie left, Movie right) {
        Movie pivot = choosePivot(left, right);
        double pivotValue = pivot.popularity;

        while (left != right) {
            while (left != right && left.popularity <= pivotValue) {
                left = left.next;
            }

            while (left != right && right.popularity > pivotValue) {
                right = right.prev;
            }

            if (left != right) {
                moviesList.swap(left, right);
            }
        }

        if (left.popularity > pivotValue) {
            moviesList.swap(pivot, left.prev);
            return left.prev;
        } else {
            moviesList.swap(pivot, left);
            return left;
        }
    }

    private Movie choosePivot(Movie left, Movie right) {
        // choose middle element as pivot
        Movie mid = left;
        int size = 0;
        while (mid != right) {
            size++;
            mid = mid.next;
        }

        int middleIndex = size / 2;
        mid = left;
        for (int i = 0; i < middleIndex; i++) {
            mid = mid.next;
        }

        return mid;
    }

    public void binaryInsertionSort(MovieList moviesList) {
        if (moviesList == null || moviesList.head == null) {
            return;
        }
        Movie currentMovie = moviesList.getHead();
        while (currentMovie != null) {
            Movie nextMovie = currentMovie.next;
            binaryInsertionSort(moviesList, currentMovie);
            currentMovie = nextMovie;
        }
    }

    private void binaryInsertionSort(MovieList moviesList, Movie movie) {
        if (movie.prev == null) {
            return;
        }

        Movie left = moviesList.getHead();
        if (left == null) {
            return;
        }
        
        Movie right = movie.prev;
        while (left != right) {
            Movie mid = left;
            int size = 0;
            while (mid != right) {
                size++;
                mid = mid.next;
            }

            int middleIndex = size / 2;
            mid = left;
            for (int i = 0; i < middleIndex; i++) {
                mid = mid.next;
            }

            if (movie.popularity < mid.popularity) {
                right = mid.prev;
            } else {
                left = mid.next;
            }
        }

        if (left != null && left.next != null && left.popularity > movie.popularity) {
            moviesList.swap(movie, left);
        } else {
            if (left.next != null) {
                moviesList.swap(movie, left.next);
            }
            
        }
    }
    
}
