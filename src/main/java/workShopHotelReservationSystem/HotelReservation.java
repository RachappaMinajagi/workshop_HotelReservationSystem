package workShopHotelReservationSystem;

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

	/*
	 * / add hotel.
	 */
	public void add(Hotel hotel) // add hotel.
	{
		this.hotels.add(hotel);
	}

	public List<Hotel> getHotelList() {
		return this.hotels;
	}
	/*
	 * Create a Map to key and Value Key Search for Integer And Value Search For
	 * String
	 */

	public Map<Integer, Hotel> searchFor(String date1, String date2) {
		int totalDays = countTotalDays(date1, date2);
		int weekDays = countWeekDays(date1, date2);
		int weekendDays = totalDays - weekDays;
		return getCheapestHotels(weekDays, weekendDays);
	}

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

	public LocalDate toLocalDate(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMMyyyy");
		LocalDate localDate = LocalDate.parse(date, formatter);
		return localDate;
	}
}