package workShopHotelReservationSystem;

public class Hotel {
	/*
	 * Create Class For Hotel
	 */
	private String name;
	private int regularWeekdayRate;
	private int regularWeekendRate;
	private int rewardsWeekdayRate;
	private int rewardsWeekendRate;
	private int rating;

	/*
	 * Create a Constructor
	 */

	public Hotel(String name, int regularWeekdayRate, int regularWeekendRate, int rewardsWeekdayRate,
			int rewardsWeekendRate, int rating) {
		this.name = name;
		this.regularWeekdayRate = regularWeekdayRate;
		this.regularWeekendRate = regularWeekendRate;
		this.rewardsWeekdayRate = rewardsWeekdayRate;
		this.rewardsWeekendRate = rewardsWeekendRate;
		this.rating = rating;
	}

	/*
	 * getter and setter Method used For Hotel Reservation system
	 */

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRegularWeekdayRate() {
		return regularWeekdayRate;
	}

	public void setRegularWeekdayRate(int regularWeekdayRate) {
		this.regularWeekdayRate = regularWeekdayRate;
	}

	public int getRegularWeekendRate() {
		return regularWeekendRate;
	}

	public void setRegularWeekendRate(int regularWeekendRate) {
		this.regularWeekendRate = regularWeekendRate;
	}

	public int getRewardsWeekdayRate() {
		return rewardsWeekdayRate;
	}

	public void setRewardsWeekdayRate(int rewardsWeekdayRate) {
		this.rewardsWeekdayRate = rewardsWeekdayRate;
	}

	public int getRewardsWeekendRate() {
		return rewardsWeekendRate;
	}

	public void setRewardsWeekendRate(int rewardsWeekendRate) {
		this.rewardsWeekendRate = rewardsWeekendRate;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

}