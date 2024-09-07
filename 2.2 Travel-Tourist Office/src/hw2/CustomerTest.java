package hw2;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

class CustomerTest {

    private Customer customer = new Customer("Alice Wonderland", 50000); // Starting with 500.00
    private Hotel hotel = new Hotel("Wonderland Inn", new Room[]{new Room("Queen"), new Room("King")});
    private Airport departure = new Airport(0, 0, 100), arrival = new Airport(100, 100, 200);
    private HotelReservation hotelRes = new HotelReservation("Alice Wonderland", hotel, "Queen", 2);
    private FlightReservation flightRes = new FlightReservation("Alice Wonderland", departure, arrival);
        
    @Test
    public void testAddFunds() {
        int newBalance = customer.addFunds(10000);
        assertEquals("Balance should be correctly updated after adding funds", 60000, newBalance);
    }
    
    @Test
    public void testAddFundsNegative() {
        customer.addFunds(-1000);
        assertEquals("Code should throw an exception", IllegalArgumentException.class, customer.addFunds(-1000));
    }
    
    @Test
    public void testAddToBasket() {
        customer.addToBasket(hotelRes);
        assertEquals("Basket should contain 1 reservation after add", 1, customer.getBasket().getNumOfReservations());
        customer.addToBasket(hotel, "King", 3, true);
        assertEquals("Basket should contain 2 reservation after add", 2, customer.getBasket().getNumOfReservations());
        customer.addToBasket(departure, arrival);
        assertEquals("Basket should contain 3 reservation after add", 3, customer.getBasket().getNumOfReservations());
    }
    
    @Test
    public void testAddToBasketMismatchName() {
        Reservation otherCustomerRes = new HotelReservation("Bob", hotel, "Queen", 2);
        assertEquals("Code should throw an exception", IllegalArgumentException.class, customer.addToBasket(otherCustomerRes));
    }
    
    @Test
    public void testRemoveFromBasket() {
        customer.addToBasket(hotelRes);
        assertTrue("Remove should return true for existing reservation", customer.removeFromBasket(hotelRes));
        assertEquals("Basket should be empty after removal", 0, customer.getBasket().getNumOfReservations());
        assertFalse("Remove should return false for non-existing reservation", customer.removeFromBasket(hotelRes));
    }
    
    @Test
    public void testCheckout() {
        customer.addToBasket(hotelRes);
        customer.addToBasket(flightRes);
        int balanceBefore = customer.getBalance();
        int totalCost = hotelRes.getCost() + flightRes.getCost();
        customer.checkOut();
        assertEquals("Balance after checkout should be reduced by the total cost of reservations", balanceBefore - totalCost, customer.getBalance());
    }
    
    @Test
    public void testCheckoutInsufficientFunds() {
        customer = new Customer("Alice Wonderland", 100); // Insufficient starting balance
        customer.addToBasket(hotelRes);
        customer.addToBasket(flightRes);
        assertEquals("Code should throw an exception", IllegalArgumentException.class, customer.checkOut());
    }
    
    @Test
    public void testCheckoutEmptyBasket() {
        assertEquals("Code should throw an exception", IllegalArgumentException.class, customer.checkOut());
    }

}
