package workShopHotelReservationSystem;

public class Hotel {
	/*
	 * Create a Class Name is Hotel
	 */
	private String name;
	private int regularWeekdayRate;
	private int regularWeekendRate;
	private int rating;
	private int rewardsWeekdayRate;
	private int rewardsWeekendRate;

	/*
	 * Create Contractor same Name as Class Name
	 */
	public Hotel(String name, int regularWeekdayRate) {
		this.name = name;
		this.regularWeekdayRate = regularWeekdayRate;
	}

	public Hotel(String name, int regularWeekdayRate, int regularWeekendRate) {
		this.name = name;
		this.regularWeekdayRate = regularWeekdayRate;
		this.regularWeekendRate = regularWeekendRate;
	}

	public Hotel(String name, int regularWeekdayRate, int regularWeekendRate, int rating) {
		this.name = name;
		this.regularWeekdayRate = regularWeekdayRate;
		this.regularWeekendRate = regularWeekendRate;
		this.rating = rating;
	}

	/*
	 * Create Contractor to passing a parameter of the hotel rates this is used
	 * point the current object
	 */

	public Hotel(String name, int regularWeekdayRate, int regularWeekendRate, int rating, int rewardsWeekdayRate,
			int rewardsWeekendRate) {
		this.name = name;
		this.regularWeekdayRate = regularWeekdayRate;
		this.regularWeekendRate = regularWeekendRate;
		this.rating = rating;
		this.rewardsWeekdayRate = rewardsWeekdayRate;
		this.rewardsWeekendRate = rewardsWeekendRate;
	}
	/*
	 * Create Method getter and setter method to set the values and also set the
	 * Regular Weekday Rate and Weekend Rate for Regular Customer
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