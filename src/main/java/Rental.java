public class Rental {

    private Movie movie;
    private int daysRented;

    public Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    public int getDaysRented() {
        return daysRented;
    }

    public String getTitle() {
        return movie.getTitle();
    }

    public double getRentalCost() {
        return movie.calculatePrice(daysRented);
    }

    public int calculateFrequentRenterPoints() {
        return movie.getFrequentRenterPoints();
    }

}