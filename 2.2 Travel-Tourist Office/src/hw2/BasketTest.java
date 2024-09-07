package hw2;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

class BasketTest {

    private Basket basket = new Basket();
    private Reservation reservation1 = new HotelReservation("John Doe", new Hotel("Test Hotel", new Room[]{new Room("Double")}), "Double", 2)
    , reservation2 = new FlightReservation("John Doe", new Airport(0, 0, 100), new Airport(100, 100, 200));	
    
    @Test
    public void testAdd() {
        assertEquals("Basket should initially be empty", 0, basket.getNumOfReservations());
        basket.add(reservation1);
        assertEquals("Basket should contain 1 reservation after add", 1, basket.getNumOfReservations());
    }
    
    @Test
    public void testRemove() {
        basket.add(reservation1);
        assertTrue("Remove should return true when removing existing reservation", basket.remove(reservation1));
        assertEquals("Basket should be empty after removal", 0, basket.getNumOfReservations());
        assertFalse("Remove should return false when removing non-existent reservation", basket.remove(reservation2));
    }
    
    @Test
    public void testGetProducts() {
        basket.add(reservation1);
        basket.add(reservation2);
        Reservation[] reservations = basket.getProducts();
        assertEquals("GetProducts should return an array with 2 reservations", 2, reservations.length);
        //assertEquals("The reservation returned by GetProducts should match the added reservation", reservation1, reservations[0]);
    }
    
    @Test
    public void testGetTotalCost() {
        basket.add(reservation1);
        basket.add(reservation2);
        int expectedCost = reservation1.getCost() + reservation2.getCost();
        assertEquals("Total cost should be the sum of all reservations in the basket", expectedCost, basket.getTotalCost());
    }
    
    @Test
    public void testClear() {
        basket.add(reservation1);
        basket.add(reservation2);
        System.out.println(basket.toString());
        basket.clear();
        assertEquals("Basket should be empty after clear", 0, basket.getNumOfReservations());
        System.out.println(basket.toString());
    }

}
