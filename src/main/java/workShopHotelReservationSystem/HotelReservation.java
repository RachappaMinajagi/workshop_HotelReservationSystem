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
	private List<Hotel> hotels;

	/*
	 * Create Constructor For HotelReservation
	 */
	public HotelReservation() {
		this.hotels = new ArrayList<Hotel>();
	}

	public void add(Hotel hotel) {
		hotels.add(hotel);
	}

	public List<Hotel> getHotelList() {
		return this.hotels;
	}

	public Map<Hotel, Integer> searchFor(String date1, String date2) {
		int totalDays = countTotalDays(date1, date2);
		int weekDays = countWeekDays(date1, date2);
		int weekendDays = totalDays - weekDays;
		return getCheapestHotels(weekDays, weekendDays);
	}
	/*
	 * Create a ArrayList for Hotel
	 */

	public Map<Hotel, Integer> getCheapestHotels(int weekDays, int weekendDays) {
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

	/*
	 * Create Method for Total Days Start and End Date
	 */

	public int countTotalDays(String date1, String date2) {

		LocalDate startDate = toLocalDate(date1);
		LocalDate endDate = toLocalDate(date2);
		int totalDays = Period.between(startDate, endDate).getDays() + 1;
		return totalDays;
	}

	/*
	 * Create Method For WeekDays
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
	/*
	 * Create A array List For getCheapestAndBestRatedHotels key value For Hotel reservation system
	 */
	
	public Map<Hotel, Integer> getCheapestAndBestRatedHotels1(String date1, String date2) {
        Map<Hotel, Integer> bestHotels = new HashMap<Hotel, Integer>();
        Map<Hotel, Integer> cheapestHotels = searchFor(date1, date2);
        int highestRating = (cheapestHotels.keySet().stream().max(Comparator.comparingInt(Hotel::getRating)).get())
                .getRating();
        cheapestHotels.forEach((k, v) -> {
            if (k.getRating() == highestRating)
                bestHotels.put(k, v);
        });
        return bestHotels;
    }

	/*
	 * Create A array List For key value For Hotel reservation system
	 */
	public Map<Hotel, Integer> getCheapestAndBestRatedHotels(String date1, String date2) {
		Map<Hotel, Integer> bestHotels = new HashMap<Hotel, Integer>();
		Map<Hotel, Integer> cheapestHotels = searchFor(date1, date2);
		int highestRating = (cheapestHotels.keySet().stream().max(Comparator.comparingInt(Hotel::getRating)).get())
				.getRating();
		cheapestHotels.forEach((k, v) -> {
			if (k.getRating() == highestRating)
				bestHotels.put(k, v);
		});
		return bestHotels;
	}
	/*
	 * Create Local DateMethod
	 */

	public LocalDate toLocalDate(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMMyyyy");
		LocalDate localDate = LocalDate.parse(date, formatter);
		return localDate;
	}

}