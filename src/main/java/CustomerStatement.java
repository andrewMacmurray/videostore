import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CustomerStatement {

    private String name;
    private List<Rental> rentals = new ArrayList<>();

    public CustomerStatement(String name) {
        this.name = name;
    }

    public void addRental(Rental rental) {
        rentals.add(rental);
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
                .mapToDouble(Rental::getRentalCost)
                .sum();
    }

    private Stream<Rental> rentalStream() {
        return rentals.stream();
    }

    private String customerSummary() {
        return String.format("Rental Record for %s\n", name);
    }

    private String rentalSummary(Rental rental) {
        return String.format(
                "\t%s\t%.1f\n",
                rental.getTitle(),
                rental.getRentalCost()
        );
    }

    private String statementSummary() {
        return String.format(
                "You owed %.1f\nYou earned %d frequent renter points\n",
                getTotalRentalsCost(),
                getTotalFrequentRenterPoints()
        );
    }

}

