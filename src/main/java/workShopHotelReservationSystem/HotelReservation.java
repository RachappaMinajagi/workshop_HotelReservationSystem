package workShopHotelReservationSystem;

import java.util.ArrayList;
import java.util.List;

/*
 * Create Hotel Reservation Class
 */
public class HotelReservation {
	 private List<Hotel> hotels;

	    public HotelReservation() {
	        this.hotels = new ArrayList<Hotel>();
	    }

	    public void add(Hotel hotel) {
	        this.hotels.add(hotel);
	    }

	    public List<Hotel> getHotelList() {
	        return this.hotels;
	    }

}