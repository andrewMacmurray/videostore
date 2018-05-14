public class ChildrensMovie extends Movie {

    public ChildrensMovie(String title) {
        super(title);
    }

    @Override
    public double calculatePrice(int daysRented) {
        return daysRented > 3 ? 1.5 + (daysRented - 3) * 1.5 : 1.5;
    }

    @Override
    public int getFrequentRenterPoints() {
        return 1;
    }

}
