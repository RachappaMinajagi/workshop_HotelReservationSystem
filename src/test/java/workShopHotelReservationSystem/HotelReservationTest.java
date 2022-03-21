package workShopHotelReservationSystem;

/**
 * import Assertions class
 * import test class
 * import java.util.Arrays;
 * import java.util.List;
 * import java.util.Map;
 * import org.junit.Test;
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Test;

/**
 * creating a class name as HotelReservationTest
 */
public class HotelReservationTest {
	/**
	 * UC1 When hotel is Added
	 */

	@Test
	public void whenHotelAddedToSystemShouldGetAdded() {
		Hotel hotel1 = new Hotel("Lakewood", 110);
		Hotel hotel2 = new Hotel("Bridgewood", 160);
		Hotel hotel3 = new Hotel("Ridgewood", 220);
		Hotel[] hotelList = { hotel1, hotel2, hotel3 };
		List<Hotel> hotels = Arrays.asList(hotelList);
		HotelReservation hotelReservation = new HotelReservation();
		hotelReservation.add(hotel1);
		hotelReservation.add(hotel2);
		hotelReservation.add(hotel3);
		List<Hotel> result = hotelReservation.getHotelList();
		assertEquals(hotels, result);
	}
	// Output :- True test case passed

	@Test
	public void whenGivenDateRangeShouldReturnCheapestHotel() {
		Hotel hotel1 = new Hotel("Lakewood", 110);
		Hotel hotel2 = new Hotel("Bridgewood", 160);
		Hotel hotel3 = new Hotel("Ridgewood", 220);
		HotelReservation hotelReservation = new HotelReservation();
		hotelReservation.add(hotel1);
		hotelReservation.add(hotel2);
		hotelReservation.add(hotel3);
		Map<Hotel, Integer> result;
		try {
			result = hotelReservation.getCheapestHotels("10Sep2020", "11Sep2020", "regular");
			result.forEach((k, v) -> System.out.println(k.getName() + " " + v));
			assertNotNull(result);
		} catch (InvalidCustomerException | InvalidDateRangeException e) {
			e.getMessage();
		}
	}

	// The cheapest hotel is Lakewood, Rating : 3, Total Rates = $220

	@Test
	public void whenGivenHotelAddedShouldAddWeekendPrices() {
		Hotel hotel1 = new Hotel("Lakewood", 110, 90);
		Hotel hotel2 = new Hotel("Bridgewood", 160, 60);
		Hotel hotel3 = new Hotel("Ridgewood", 220, 150);
		HotelReservation hotelReservation = new HotelReservation();
		hotelReservation.add(hotel1);
		hotelReservation.add(hotel2);
		hotelReservation.add(hotel3);
		List<Hotel> hotelList = hotelReservation.getHotelList();
		boolean result = hotelList.get(0).getRegularWeekendRate() == 90
				&& hotelList.get(1).getRegularWeekendRate() == 60 && hotelList.get(2).getRegularWeekendRate() == 150;
		assertTrue(result);
	}

	/**
	 * The cheapest hotel is Bridgewood, Rating 4, Total Rates = $200
	 */

	@Test
	public void whenGivenDateRangeShouldReturnCheapestHotels() {
		Hotel hotel1 = new Hotel("Lakewood", 110, 90);
		Hotel hotel2 = new Hotel("Bridgewood", 150, 50);
		Hotel hotel3 = new Hotel("Ridgewood", 220, 150);
		HotelReservation hotelReservation = new HotelReservation();
		hotelReservation.add(hotel1);
		hotelReservation.add(hotel2);
		hotelReservation.add(hotel3);
		Map<Hotel, Integer> result;
		try {
			result = hotelReservation.getCheapestHotels("11Sep2020", "12Sep2020", "regular");
			result.forEach((k, v) -> System.out.println(k.getName() + " " + v));
			assertNotNull(result);
		} catch (InvalidCustomerException | InvalidDateRangeException e) {
			e.getMessage();
		}

	}

	@Test
	public void whenHotelAddedToSystemRatingShouldGetAdded() {
		Hotel hotel1 = new Hotel("Lakewood", 110, 90, 3);
		Hotel hotel2 = new Hotel("Bridgewood", 160, 60, 4);
		Hotel hotel3 = new Hotel("Ridgewood", 220, 150, 5);
		HotelReservation hotelReservation = new HotelReservation();
		hotelReservation.add(hotel1);
		hotelReservation.add(hotel2);
		hotelReservation.add(hotel3);
		List<Hotel> hotelList = hotelReservation.getHotelList();
		boolean result = hotelList.get(0).getRating() == 3 && hotelList.get(1).getRating() == 4
				&& hotelList.get(2).getRating() == 5;
		assertTrue(result);
	}

	/**
	 * Ability to add special rates for reward customers as a part of Loyalty
	 * Program
	 */

	@Test
	public void whenGivenDateRangeShouldReturnCheapestBestRatedHotels() {
		Hotel hotel1 = new Hotel("Lakewood", 110, 90, 3);
		Hotel hotel2 = new Hotel("Bridgewood", 150, 50, 4);
		Hotel hotel3 = new Hotel("Ridgewood", 220, 150, 5);
		HotelReservation hotelReservation = new HotelReservation();
		hotelReservation.add(hotel1);
		hotelReservation.add(hotel2);
		hotelReservation.add(hotel3);
		Map<Hotel, Integer> result;
		try {
			result = hotelReservation.getCheapestAndBestRatedHotels("11Sep2020", "12Sep2020", "regular");
			result.forEach(
					(k, v) -> System.out.println(k.getName() + ", Rating : " + k.getRating() + " and Total Rate " + v));
			assertNotNull(result);
		} catch (InvalidCustomerException | InvalidDateRangeException e) {
			e.getMessage();
		}
	}

	/**
	 * Ability to find the cheapest best rated hotel Hotel for a given Date Range
	 * for a Reward Customer - Ability to validate the user inputs
	 */
	@Test
	public void whenGivenDateRangeShouldReturnBestRatedHotels() {
		Hotel hotel1 = new Hotel("Lakewood", 110, 90, 3);
		Hotel hotel2 = new Hotel("Bridgewood", 150, 50, 4);
		Hotel hotel3 = new Hotel("Ridgewood", 220, 150, 5);
		HotelReservation hotelReservation = new HotelReservation();
		hotelReservation.add(hotel1);
		hotelReservation.add(hotel2);
		hotelReservation.add(hotel3);
		Map<Hotel, Integer> result;
		try {
			result = hotelReservation.getBestRatedHotels("11Sep2020", "12Sep2020", "regular");
			result.forEach(
					(k, v) -> System.out.println(k.getName() + ", Rating : " + k.getRating() + " and Total Rate " + v));
			assertNotNull(result);
		} catch (InvalidCustomerException | InvalidDateRangeException e) {
			e.getMessage();
		}
	}

	/**
	 * Ability to add special rates for reward customers as a part of Loyalty
	 * Program
	 */

	@Test
	public void whenHotelsAddedShouldAddRewardsCustomerRate() {
		Hotel hotel1 = new Hotel("Lakewood", 110, 90, 3, 80, 80);
		Hotel hotel2 = new Hotel("Bridgewood", 150, 50, 4, 110, 50);
		Hotel hotel3 = new Hotel("Ridgewood", 220, 150, 5, 100, 40);
		HotelReservation hotelReservation = new HotelReservation();
		hotelReservation.add(hotel1);
		hotelReservation.add(hotel2);
		hotelReservation.add(hotel3);
		List<Hotel> hotelList = hotelReservation.getHotelList();
		boolean result = hotelList.get(0).getRewardsWeekendRate() == 80
				&& hotelList.get(0).getRewardsWeekdayRate() == 80 && hotelList.get(1).getRewardsWeekendRate() == 50
				&& hotelList.get(1).getRewardsWeekdayRate() == 110 && hotelList.get(2).getRewardsWeekendRate() == 40
				&& hotelList.get(2).getRewardsWeekdayRate() == 100;
		assertTrue(result);
	}

	@Test
	public void whenGivenDateRangeShouldReturnCheapestAndBestRatedHotelForRewardsCustomer() {
		Hotel hotel1 = new Hotel("Lakewood", 110, 90, 3, 80, 80);
		Hotel hotel2 = new Hotel("Bridgewood", 150, 50, 4, 110, 50);
		Hotel hotel3 = new Hotel("Ridgewood", 220, 150, 5, 100, 40);
		HotelReservation hotelReservation = new HotelReservation();
		hotelReservation.add(hotel1);
		hotelReservation.add(hotel2);
		hotelReservation.add(hotel3);
		Map<Hotel, Integer> result;
		try {
			result = hotelReservation.getCheapestAndBestRatedHotels("11Sep2020", "12Sep2020", "reward");
			result.forEach(
					(k, v) -> System.out.println(k.getName() + ", Rating : " + k.getRating() + " and Total Rate " + v));
			assertNotNull(result);
		} catch (InvalidCustomerException | InvalidDateRangeException e) {
			e.getMessage();
		}
	}

	/**
	 * cheapest rated hotel for regular customer.
	 */

	@Test
	public void whenGivenDateRangeShouldReturnCheapestAndBestRatedHotelForRegularCustomer()

	{
		Hotel hotel1 = new Hotel("Lakewood", 110, 90, 3, 80, 80);
		Hotel hotel2 = new Hotel("Bridgewood", 150, 50, 4, 110, 50);
		Hotel hotel3 = new Hotel("Ridgewood", 220, 150, 5, 100, 40);
		HotelReservation hotelReservation = new HotelReservation();
		hotelReservation.add(hotel1);
		hotelReservation.add(hotel2);
		hotelReservation.add(hotel3);
		Map<Hotel, Integer> result;
		try {
			result = hotelReservation.getCheapestAndBestRatedHotels("11Sep2020", "12Sep2020", "regular");
			result.forEach(
					(k, v) -> System.out.println(k.getName() + ", Rating : " + k.getRating() + " and Total Rate " + v));
			assertNotNull(result);
		} catch (InvalidCustomerException | InvalidDateRangeException e) {
			e.getMessage();
		}
	}
}