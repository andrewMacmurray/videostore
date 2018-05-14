public class RegularMovie extends Movie {

    public RegularMovie(String title) {
        super(title);
    }

    @Override
    public double calculatePrice(int daysRented) {
        return daysRented > 2 ? 2 + (daysRented - 2) * 1.5 : 2;
    }

    @Override
    public int getFrequentRenterPoints() {
        return 1;
    }

}
