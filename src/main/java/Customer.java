import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Customer {

    private String name;
    private List<Rental> rentals = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public String getName() {
        return name;
    }

    public String statement() {
        return customerSummary() + allRentalsSummary() + statementSummary();
    }

    private String allRentalsSummary() {
        return rentalStream()
                .map(this::rentalSummary)
                .collect(Collectors.joining(""));
    }

    private int getTotalFrequentRenterPoints() {
        return rentalStream()
                .mapToInt(Rental::calculateFrequentRenterPoints)
                .sum();
    }

    private double getTotalRentalsCost() {
        return rentalStream()
                .mapToDouble(this::calculatePrice)
                .sum();
    }

    private Stream<Rental> rentalStream() {
        return rentals.stream();
    }

    private double calculatePrice(Rental rental) {
        int daysRented = rental.getDaysRented();
        int priceCode = rental.getMovie().getPriceCode();

        switch (priceCode) {
            case Movie.REGULAR:
                return daysRented > 2 ? 2 + (daysRented - 2) * 1.5 : 2;
            case Movie.NEW_RELEASE:
                return daysRented * 3;
            case Movie.CHILDRENS:
                return daysRented > 3 ? 1.5 + (daysRented - 3) * 1.5 : 1.5;
            default:
                return 0;
        }
    }

    private String customerSummary() {
        return String.format("Rental Record for %s\n", name);
    }

    private String rentalSummary(Rental rental) {
        return String.format("\t%s\t%.1f\n", rental.getMovie().getTitle(), calculatePrice(rental));
    }

    private String statementSummary() {
        return String.format("You owed %.1f\nYou earned %d frequent renter points\n", getTotalRentalsCost(), getTotalFrequentRenterPoints());
    }

}

