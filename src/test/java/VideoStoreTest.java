import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VideoStoreTest {

    private CustomerStatement customerStatement;

    @Before
    public void setUp() {
        customerStatement = new CustomerStatement("Fred");
    }

    @Test
    public void testSingleNewReleaseStatement() {
        addRental(new NewReleaseMovie("The Cell"), 3);

        assertEquals("Rental Record for Fred\n\tThe Cell\t9.0\nYou owed 9.0\nYou earned 2 frequent renter points\n", customerStatement.statement());
    }

    @Test
    public void testDualNewReleaseStatement() {
        addRental(new NewReleaseMovie("The Cell"), 3);
        addRental(new NewReleaseMovie("The Tigger Movie"), 3);

        assertEquals("Rental Record for Fred\n\tThe Cell\t9.0\n\tThe Tigger Movie\t9.0\nYou owed 18.0\nYou earned 4 frequent renter points\n", customerStatement.statement());
    }

    @Test
    public void testSingleChildrensStatement() {
        addRental(new ChildrensMovie("The Tigger Movie"), 3);

        assertEquals("Rental Record for Fred\n\tThe Tigger Movie\t1.5\nYou owed 1.5\nYou earned 1 frequent renter points\n", customerStatement.statement());
    }

    @Test
    public void testMultipleRegularStatement() {
        addRental(new RegularMovie("Plan 9 from Outer Space"), 1);
        addRental(new RegularMovie("8 1/2"), 2);
        addRental(new RegularMovie("Eraserhead"), 3);

        assertEquals("Rental Record for Fred\n\tPlan 9 from Outer Space\t2.0\n\t8 1/2\t2.0\n\tEraserhead\t3.5\nYou owed 7.5\nYou earned 3 frequent renter points\n", customerStatement.statement());
    }

    private void addRental(Movie movie, int daysRented) {
        customerStatement.addRental(new Rental(movie, daysRented));
    }

}