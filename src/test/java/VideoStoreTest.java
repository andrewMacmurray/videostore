import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class VideoStoreTest {

    private CustomerStatement customerStatement;

    private final Movie theCell = new NewReleaseMovie("The Cell");
    private final Movie meanGirls = new NewReleaseMovie("Mean Girls");
    private final Movie tiggerMovie = new ChildrensMovie("The Tigger Movie");
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

        assertStatement("9.0", "2", movie("The Cell", "9.0"));
    }

    @Test
    public void testDualNewReleaseStatement() {
        addRental(theCell, 3);
        addRental(meanGirls, 3);

        assertStatement("18.0", "4",
                movie("The Cell", "9.0"),
                movie("Mean Girls", "9.0")
        );
    }

    @Test
    public void testSingleChildrensStatement() {
        addRental(tiggerMovie, 3);

        assertStatement("1.5", "1", movie("The Tigger Movie", "1.5"));
    }

    @Test
    public void testMultipleRegularStatement() {
        addRental(plan9, 1);
        addRental(eightAndAHalf, 2);
        addRental(eraserHead, 3);

        assertStatement("7.5", "3",
                movie("Plan 9 from Outer Space", "2.0"),
                movie("8 1/2", "2.0"),
                movie("Eraserhead", "3.5")
        );
    }

    private void addRental(Movie movie, int daysRented) {
        customerStatement.addRental(new Rental(movie, daysRented));
    }

    private void assertStatement(String totalAmount, String frequentPoints, String... movieLines) {
        String expected = newLines(
                "Rental Record for Fred",
                newLines(movieLines) + "You owed " + totalAmount,
                "You earned " + frequentPoints + " frequent renter points"
        );
        assertEquals(expected, customerStatement.statement());
    }

    private String movie(String title, String cost) {
        return tabbed(title, cost);
    }

    private String tabbed(String... lines) {
        return "\t" + String.join("\t", lines);
    }

    private String newLines(String... lines) {
        return String.join("\n", lines) + "\n";
    }

}