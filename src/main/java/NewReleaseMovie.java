public class NewReleaseMovie extends Movie {

    public NewReleaseMovie(String title) {
        super(title);
    }

    @Override
    public double calculatePrice(int daysRented) {
        return daysRented * 3;
    }

    @Override
    public int getFrequentRenterPoints() {
        return 2;
    }

}
