package hw2;

import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;

class AirportTest {

    private Airport airport1, airport2;
    
    @Test
    public void testGetFees() {
    	airport1 = new Airport(0, 0, 100);
        assertEquals("Fee should be 100", 100, airport1.getFees());
    }
    
    @Test
    public void testGetDistance() {
    	airport1 = new Airport(0, 0, 100);
    	airport2 = new Airport(3, 4, 200);
        assertEquals("Distance between airports should be 5", 5, Airport.getDistance(airport1, airport2));
    }

}
