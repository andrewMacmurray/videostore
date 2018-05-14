import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VideoStoreTest {

    private CustomerStatement customerStatement;

    private final Movie theCell = new NewReleaseMovie("The Cell");
    private final Movie tiggerMovie = new NewReleaseMovie("The Tigger Movie");
    private final Movie plan9 = new RegularMovie("Plan 9 from Outer Space");
    private final Movie eraserHead = new RegularMovie("Eraserhead");
    private final Movie eightAndAHalf = new RegularMovie("8 1/2");

    @Before
    public void setUp() {
        customerStatement = new CustomerStatement("Fred");
    }

    @Test
    public void testSingleNewReleaseStatement() {
        addRental(theCell, 3);

        String expected = printedLines(
                "Rental Record for Fred",
                tabbed("The Cell", "9.0"),
                "You owed 9.0",
                "You earned 2 frequent renter points"
        );
        assertEquals(expected, customerStatement.statement());
    }

    @Test
    public void testDualNewReleaseStatement() {
        addRental(theCell, 3);
        addRental(tiggerMovie, 3);

        String expected = printedLines(
                "Rental Record for Fred",
                tabbed("The Cell", "9.0"),
                tabbed("The Tigger Movie", "9.0"),
                "You owed 18.0",
                "You earned 4 frequent renter points"
        );
        assertEquals(expected, customerStatement.statement());
    }

    @Test
    public void testSingleChildrensStatement() {
        addRental(tiggerMovie, 3);

        String expected = printedLines(
                "Rental Record for Fred",
                tabbed("The Tigger Movie", "1.5"),
                "You owed 1.5",
                "You earned 1 frequent renter points"
        );
        assertEquals(expected, customerStatement.statement());
    }

    @Test
    public void testMultipleRegularStatement() {
        addRental(plan9, 1);
        addRental(eightAndAHalf, 2);
        addRental(eraserHead, 3);

        String expected = printedLines(
                "Rental Record for Fred",
                tabbed("Plan 9 from Outer Space", "2.0"),
                tabbed("8 1/2", "2.0"),
                tabbed("Eraserhead", "3.5"),
                "You owed 7.5",
                "You earned 3 frequent renter points"
        );
        assertEquals(expected, customerStatement.statement());
    }

    private String tabbed(String... lines) {
        return "\t" + String.join("\t", lines);
    }

    private String printedLines(String... lines) {
        return String.join("\n", lines) + "\n";
    }

    private void addRental(Movie movie, int daysRented) {
        customerStatement.addRental(new Rental(movie, daysRented));
    }

}