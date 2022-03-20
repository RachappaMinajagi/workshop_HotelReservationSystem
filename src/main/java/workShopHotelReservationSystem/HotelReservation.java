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
   UC10:-Ability to find the cheapest best rated hotel Hotel for a given Date Range for a 
         Reward Customer - Ability to validate the user inputs for Date range and customer type.
   UC11:-Ability to find the cheapest best rated hotel Hotel for a given Date Range for a Reward Customer
         using Java Streams - Use Regex Validation, Exceptions and Java 8 Date Feature
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

    public HotelReservation() {
        this.hotels = new ArrayList<Hotel>();
    }

    public void add(Hotel hotel) {
        hotels.add(hotel);
    }

    public List<Hotel> getHotelList() {
        return this.hotels;
    }

    public Map<Hotel, Integer> searchFor(String date1, String date2, String hotelType, String customerType) {

        int totalDays = countTotalDays(date1, date2);
        int weekDays = countWeekDays(date1, date2);
        int weekendDays = totalDays - weekDays;

        if (hotelType.equals("cheapest"))
            return searchForCheapestHotels(weekDays, weekendDays, customerType);

        if (hotelType.equals("best"))
            return searchForBestRatedHotels(weekDays, weekendDays, customerType);
        else
            return null;
    }


    public Map<Hotel, Integer> getCheapestHotels(String date1, String date2, String customerType)
            throws InvalidCustomerException, InvalidDateRangeException {
        validateCustomerType(customerType);
        validateDateRange(date1, date2);
        Map<Hotel, Integer> cheapestHotels = searchFor(date1, date2, "cheapest", customerType);
        return cheapestHotels;
    }

    public Map<Hotel, Integer> searchForCheapestHotels(int weekDays, int weekendDays, String customerType) {

        Map<Hotel, Integer> hotelCosts = new HashMap<>();
        Map<Hotel, Integer> sortedHotelCosts = new HashMap<>();

        if (hotels.size() == 0)
            return null;

        if (customerType.equalsIgnoreCase("regular"))
            hotels.stream().forEach(n -> hotelCosts.put(n,
                    n.getRegularWeekdayRate() * weekDays + n.getRegularWeekendRate() * weekendDays));
        else
            hotels.stream().forEach(n -> hotelCosts.put(n,
                    n.getRewardsWeekdayRate() * weekDays + n.getRewardsWeekendRate() * weekendDays));

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

    public Map<Hotel, Integer> getCheapestAndBestRatedHotels(String date1, String date2, String customerType)
            throws InvalidCustomerException, InvalidDateRangeException {
        validateCustomerType(customerType);
        validateDateRange(date1, date2);
        Map<Hotel, Integer> cheapestAndBestHotels = new HashMap<Hotel, Integer>();
        Map<Hotel, Integer> cheapestHotels = searchFor(date1, date2, "cheapest", customerType);
        int highestRating = (cheapestHotels.keySet().stream().max(Comparator.comparingInt(Hotel::getRating)).get())
                .getRating();
        cheapestHotels.forEach((k, v) -> {
            if (k.getRating() == highestRating)
                cheapestAndBestHotels.put(k, v);
        });
        return cheapestAndBestHotels;
    }

    public Map<Hotel, Integer> getBestRatedHotels(String date1, String date2, String customerType)
            throws InvalidCustomerException, InvalidDateRangeException {
        validateCustomerType(customerType);
        validateDateRange(date1, date2);
        Map<Hotel, Integer> bestHotels = searchFor(date1, date2, "best", customerType);
        return bestHotels;
    }

    public Map<Hotel, Integer> searchForBestRatedHotels(int weekDays, int weekendDays, String customerType) {
        Map<Hotel, Integer> bestHotels = new HashMap<Hotel, Integer>();
        int highestRating = (hotels.stream().max(Comparator.comparingInt(Hotel::getRating)).get()).getRating();

        if (customerType.equalsIgnoreCase("regular"))
            hotels.forEach(n -> {
                if (n.getRating() == highestRating)
                    bestHotels.put(n, n.getRegularWeekdayRate() * weekDays + n.getRegularWeekendRate() * weekendDays);
            });
        else
            hotels.forEach(n -> {
                if (n.getRating() == highestRating)
                    bestHotels.put(n, n.getRewardsWeekdayRate() * weekDays + n.getRewardsWeekendRate() * weekendDays);
            });
        return bestHotels;
    }

    public LocalDate toLocalDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMMyyyy");
        LocalDate localDate = LocalDate.parse(date, formatter);
        return localDate;
    }

    public void validateCustomerType(String customerType) throws InvalidCustomerException {
        if (!(customerType.toLowerCase().matches("^regular$") || customerType.toLowerCase().matches("^reward$")))
            throw new InvalidCustomerException("Invalid Customer Type !!!");
    }

    public void validateDateRange(String date1, String date2) throws InvalidDateRangeException {
        LocalDate startDate = toLocalDate(date1);
        LocalDate endDate = toLocalDate(date2);
        if (startDate.isAfter(endDate))
            throw new InvalidDateRangeException("Invalid Date Range !!!");
    }
}