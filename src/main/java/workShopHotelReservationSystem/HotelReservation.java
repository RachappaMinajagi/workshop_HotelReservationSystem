package workShopHotelReservationSystem;

/** 
 * UC1:- Ability to add Hotel in a Hotel Reservation System with Name and
 * rates for Regular Customer...
 * UC2:- Ability to find the cheapest Hotel for a given Date Range
 * UC3:- Ability to add weekday and weekend rates for each Hotel
 */

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HotelReservation {

	private List<Hotel> hotels;

	public HotelReservation() {
		this.hotels = new ArrayList<Hotel>();
	}

	/**
	 * take the size of an array list for testing process
	 */
	public void add(Hotel hotel) {
		this.hotels.add(hotel);
	}

	public List<Hotel> getHotelList() {
		return this.hotels;
	}

	/*
	 * 
	 * @param start - start date
	 * 
	 * @param end- end date
	 * 
	 * @return -return to method created
	 * 
	 * @throws ParseException -throws exception
	 */

	public Map<Integer, Hotel> searchFor(String date1, String date2) {
		int totalDays = countTotalDays(date1, date2);
		int weekDays = countWeekDays(date1, date2);
		int weekendDays = totalDays - weekDays;
		return getCheapestHotels(weekDays, weekendDays);
	}

	/**
	 * method to find Cheapest Hotel by comparing from all the hotels
	 * 
	 * @return -return to method created
	 */

	public Map<Integer, Hotel> getCheapestHotels(int weekDays, int weekendDays) {
		Map<Integer, Hotel> hotelCosts = new HashMap<>();
		Map<Integer, Hotel> sortedHotelCosts = new HashMap<>();
		if (hotels.size() == 0)
			return null;
		this.hotels.stream().forEach(
				n -> hotelCosts.put(n.getRegularWeekdayRate() * weekDays + n.getRegularWeekendRate() * weekendDays, n));
		Integer cheap = hotelCosts.keySet().stream().min(Integer::compare).get();
		hotelCosts.forEach((k, v) -> {
			if (k == cheap)
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

	public int countWeekDays(String date1, String date2) {

		LocalDate startDate = toLocalDate(date1);
		LocalDate endDate = toLocalDate(date2);

		DayOfWeek startDay = startDate.getDayOfWeek();
		DayOfWeek endDay = endDate.getDayOfWeek();

		long days = ChronoUnit.DAYS.between(startDate, endDate) + 1;
		long daysWithoutWeekends = days - 2 * ((days + startDay.getValue()) / 7);
		int totalWeekDays = (int) daysWithoutWeekends + (startDay == DayOfWeek.SUNDAY ? 1 : 0)
				+ (endDay == DayOfWeek.SUNDAY ? 1 : 0);

		return totalWeekDays;
	}

	/*
	 * 
	 * @param start - start date
	 * 
	 * @param end- end date
	 * 
	 * @return -return to method created
	 * 
	 * @throws ParseException -throws exception
	 */
	public LocalDate toLocalDate(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMMyyyy");
		LocalDate localDate = LocalDate.parse(date, formatter);
		return localDate;
	}
}