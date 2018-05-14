public class Rental {

    public Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    public int getDaysRented() {
        return daysRented;
    }

    public Movie getMovie() {
        return movie;
    }

    public int calculateFrequentRenterPoints() {
        return movie.getPriceCode() == Movie.NEW_RELEASE && daysRented > 1 ? 2 : 1;
    }

    private Movie movie;
    private int daysRented;

}