package workShopHotelReservationSystem;

/** 
 * UC1:- Ability to add Hotel in a Hotel Reservation System with Name and
 * rates for Regular Customer...
 * UC2:- Ability to find the cheapest Hotel for a given Date Range
 * UC3:- Ability to add weekday and weekend rates for each Hotel
 * UC4:- Ability to find the cheapest Hotel for a given Date Range based on weekday
   and weekend
   UC5:- Ability to add ratings to each Hotel - Lakewood is 3, Bridgewood is 4 and Ridgewood 5
   UC6:- Ability to find the cheapest best rated hotel Hotel for a given Date Range
        - I/P – 11Sep2020, 12Sep2020
        - O/P – Bridgewood, Rating: 4 and Total Rates:200
   UC7:-Ability to find getCheapest And BestRated Hotels  
      a given Date Range
      I/P – 11Sep2020, 12Sep2020
      O/P - Ridgewood & Total rate 370.
   UC9:-Ability to add special rates for reward customers as a part of Loyalty Program - For Lakewood for Reward Customer Weekday
      For Bridgewood $110 and $50
 */

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HotelReservation {
	/*
	 * Create a Array list for Hotel
	 */
	private List<Hotel> hotels;

	/*
	 * Create a constructor
	 */
	public HotelReservation() {
		this.hotels = new ArrayList<Hotel>();
	}

	/*
	 * Create method passing arguments and calling method
	 */
	public void add(Hotel hotel) {
		hotels.add(hotel);
	}

	public List<Hotel> getHotelList() {
		return this.hotels;
	}
	/*
	 * Create a Array list For Cheapest Customer
	 */

	public Map<Hotel, Integer> searchFor(String date1, String date2, String hotelType, String customerType) {

		int totalDays = countTotalDays(date1, date2);
		int weekDays = countWeekDays(date1, date2);
		int weekendDays = totalDays - weekDays;

		if (hotelType.equals("cheapest") && customerType.equals("regular"))
			return searchForCheapestHotels(weekDays, weekendDays, "regular");
		if (hotelType.equals("cheapest") && customerType.equals("rewards"))
			return searchForCheapestHotels(weekDays, weekendDays, "rewards");
		if (hotelType.equals("best") && customerType.equals("regular"))
			return searchForBestRatedHotels(weekDays, weekendDays);
		if (hotelType.equals("best") && customerType.equals("rewards"))
			return searchForBestRatedHotels(weekDays, weekendDays);
		else
			return null;
	}

	public Map<Hotel, Integer> searchFor(String date1, String date2, String type) {
		int totalDays = countTotalDays(date1, date2);
		int weekDays = countWeekDays(date1, date2);
		int weekendDays = totalDays - weekDays;
		if (type.equals("cheapest"))
			return getCheapestHotels(weekDays, weekendDays);
		else
			return searchForBestRatedHotels(weekDays, weekendDays);

	}

	private Map<Hotel, Integer> getCheapestHotels(int weekDays, int weekendDays) {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<Hotel, Integer> getCheapestHotels(String date1, String date2) {
		Map<Hotel, Integer> cheapestHotels = searchFor(date1, date2, "cheapest");
		return cheapestHotels;
	}

	public Map<Hotel, Integer> searchForCheapestHotels(int weekDays, int weekendDays, String string) {
		Map<Hotel, Integer> hotelCosts = new HashMap<>();
		Map<Hotel, Integer> sortedHotelCosts = new HashMap<>();
		if (hotels.size() == 0)
			return null;
		hotels.stream().forEach(
				n -> hotelCosts.put(n, n.getRegularWeekdayRate() * weekDays + n.getRegularWeekendRate() * weekendDays));
		Integer cheap = hotelCosts.values().stream().min(Integer::compare).get();
		hotelCosts.forEach((k, v) -> {
			if (v.equals(cheap))
				sortedHotelCosts.put(k, v);
		});
		return sortedHotelCosts;
	}

	public int countTotalDays(String date1, String date2) {

		LocalDate startDate = toLocalDate(date1);
		LocalDate endDate = toLocalDate(date2);
		int totalDays = Period.between(startDate, endDate).getDays() + 1;
		return totalDays;
	}
	/*
	 * create Method for countWeekDays
	 */

	public int countWeekDays(String date1, String date2) {

		LocalDate startDate = toLocalDate(date1);
		LocalDate endDate = toLocalDate(date2);
		Date startDay = Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date endDay = Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDay);

		int weekDays = 0;
		while (!calendar.getTime().after(endDay)) {
			int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
			if ((dayOfWeek > 1) && (dayOfWeek < 7)) {
				weekDays++;
			}
			calendar.add(Calendar.DAY_OF_MONTH, 1);
		}

		return weekDays;
	}

	public Map<Hotel, Integer> getCheapestAndBestRatedHotels(String date1, String date2) {
		Map<Hotel, Integer> cheapestAndBestHotels = new HashMap<Hotel, Integer>();
		Map<Hotel, Integer> cheapestHotels = getCheapestHotels(date1, date2);
		int highestRating = (cheapestHotels.keySet().stream().max(Comparator.comparingInt(Hotel::getRating)).get())
				.getRating();
		cheapestHotels.forEach((k, v) -> {
			if (k.getRating() == highestRating)
				cheapestAndBestHotels.put(k, v);
		});
		return cheapestAndBestHotels;
	}

	public Map<Hotel, Integer> getBestRatedHotels(String date1, String date2) {
		Map<Hotel, Integer> bestHotels = searchFor(date1, date2, "best");
		return bestHotels;
	}

	public Map<Hotel, Integer> searchForBestRatedHotels(int weekDays, int weekendDays) {
		Map<Hotel, Integer> bestHotels = new HashMap<Hotel, Integer>();
		int highestRating = (hotels.stream().max(Comparator.comparingInt(Hotel::getRating)).get()).getRating();

		hotels.forEach(n -> {
			if (n.getRating() == highestRating)
				bestHotels.put(n, n.getRegularWeekdayRate() * weekDays + n.getRegularWeekendRate() * weekendDays);
		});
		return bestHotels;
	}
	/*
	 * Create Method For Date and Create a object
	 */

	public LocalDate toLocalDate(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMMyyyy");
		LocalDate localDate = LocalDate.parse(date, formatter);
		return localDate;
	}
}