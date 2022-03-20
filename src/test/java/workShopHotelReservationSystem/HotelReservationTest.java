package workShopHotelReservationSystem;

import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class HotelReservationTest {
	@Test
	public void whenHotelAddedToSystemShouldGetAdded() {
		Hotel hotel1 = new Hotel("Lakewood", 110, 90, 80, 80, 3);
		Hotel hotel2 = new Hotel("Bridgewood", 160, 60, 110, 50, 4);
		Hotel hotel3 = new Hotel("Ridgewood", 220, 150, 100, 40, 5);
		Hotel[] hotelList = { hotel1, hotel2, hotel3 };
		List<Hotel> hotels = Arrays.asList(hotelList);
		HotelReservation hotelReservation = new HotelReservation();
		hotelReservation.add(hotel1);
		hotelReservation.add(hotel2);
		hotelReservation.add(hotel3);
		List<Hotel> result = hotelReservation.getHotelList();
		assertEquals(hotels, result);
	}
	/*
	 * / given date range should return cheapest hotel.
	 */

	@Test
	public void whenGivenDateRangeShouldReturnCheapestHotel() {
		Hotel hotel1 = new Hotel("Lakewood", 110, 90, 80, 80, 3);
		Hotel hotel2 = new Hotel("Bridgewood", 160, 60, 110, 50, 4);
		Hotel hotel3 = new Hotel("Ridgewood", 220, 150, 100, 40, 5);
		HotelReservation hotelReservation = new HotelReservation();
		hotelReservation.add(hotel1);
		hotelReservation.add(hotel2);
		hotelReservation.add(hotel3);
		Map<Hotel, Integer> result = hotelReservation.searchFor("10Sep2020", "11Sep2020");
		result.forEach((k, v) -> System.out.println(k.getName() + " " + v));
		assertNotNull(result);
	}
	/*
	 * / given hotel should add weekend prices.
	 */

	@Test
	public void whenGivenHotelAddedShouldAddWeekendPrices() {
		Hotel hotel1 = new Hotel("Lakewood", 110, 90, 80, 80, 3);
		Hotel hotel2 = new Hotel("Bridgewood", 160, 60, 110, 50, 4);
		Hotel hotel3 = new Hotel("Ridgewood", 220, 150, 100, 40, 5);
		HotelReservation hotelReservation = new HotelReservation();
		hotelReservation.add(hotel1);
		hotelReservation.add(hotel2);
		hotelReservation.add(hotel3);
		List<Hotel> hotelList = hotelReservation.getHotelList();
		boolean result = hotelList.get(0).getRegularWeekendRate() == 90
				&& hotelList.get(1).getRegularWeekendRate() == 60 && hotelList.get(2).getRegularWeekendRate() == 150;
		assertTrue(result);
	}

	/*
	 * / given date range should return cheapest hotels.
	 */
	@Test
	public void whenGivenDateRangeShouldReturnCheapestHotels() {
		Hotel hotel1 = new Hotel("Lakewood", 110, 90, 80, 80, 3);
		Hotel hotel2 = new Hotel("Bridgewood", 150, 50, 110, 50, 4);
		Hotel hotel3 = new Hotel("Ridgewood", 220, 150, 100, 40, 5);
		HotelReservation hotelReservation = new HotelReservation();
		hotelReservation.add(hotel1);
		hotelReservation.add(hotel2);
		hotelReservation.add(hotel3);
		Map<Hotel, Integer> result = hotelReservation.searchFor("11Sep2020", "12Sep2020");
		result.forEach((k, v) -> System.out.println(k.getName() + " " + v));
		assertNotNull(result);
	}

	/*
	 * / rating to the hotel.
	 */

	@Test
	public void whenHotelAddedToSystemRatingShouldGetAdded() {
		Hotel hotel1 = new Hotel("Lakewood", 110, 90, 80, 80, 3);
		Hotel hotel2 = new Hotel("Bridgewood", 160, 60, 110, 50, 4);
		Hotel hotel3 = new Hotel("Ridgewood", 220, 150, 100, 40, 5);
		HotelReservation hotelReservation = new HotelReservation();
		hotelReservation.add(hotel1);
		hotelReservation.add(hotel2);
		hotelReservation.add(hotel3);
		List<Hotel> hotelList = hotelReservation.getHotelList();
		boolean result = hotelList.get(0).getRating() == 3 && hotelList.get(1).getRating() == 4
				&& hotelList.get(2).getRating() == 5;
		assertTrue(result);
	}

	/*
	 * / cheapest best rated hotels.
	 */
	@Test
	public void whenGivenDateRange_ShouldReturn_CheapestBestRatedHotels() {
		Hotel hotel1 = new Hotel("Lakewood", 110, 90, 80, 80, 3);
		Hotel hotel2 = new Hotel("Bridgewood", 150, 50, 110, 50, 4);
		Hotel hotel3 = new Hotel("Ridgewood", 220, 150, 100, 40, 5);
		HotelReservation hotelReservation = new HotelReservation();
		hotelReservation.add(hotel1);
		hotelReservation.add(hotel2);
		hotelReservation.add(hotel3);
		Map<Hotel, Integer> result = hotelReservation.getCheapestAndBestRatedHotels("11Sep2020", "12Sep2020");
		result.forEach(
				(k, v) -> System.out.println(k.getName() + ", Rating : " + k.getRating() + " and Total Rate " + v));
		assertNotNull(result);
	}
}