package sushigame.model;

import comp401.sushi.PlatePriceException;
import comp401.sushi.RedPlate;
import comp401.sushi.Sashimi;

public class SushiGameModel {

	private BeltImpl belt;
	private Customer[] customers;
	private Chef[] opponent_chefs;
	private Chef player_chef;

	private final double STARTING_BALANCE = 100.0;

	public SushiGameModel(int belt_size, int num_customers, int num_chef_opponents) {
		if (belt_size < 1) {
			throw new IllegalArgumentException("Belt must have size > 0");
		}

		if (belt_size < num_customers) {
			throw new IllegalArgumentException("Belt size must be greater then number of customers");
		}

		belt = new BeltImpl(belt_size);
		customers = new Customer[num_customers];
		opponent_chefs = new Chef[num_chef_opponents];

		int belt_idx = 0;
		for (int i=0; i<num_customers; i++) {
			customers[i] = new RandomCustomer(Math.random());
			belt.setCustomerAtPosition(customers[i], belt_idx);
			belt_idx += belt_size / num_customers;
		}

		for (int i=0; i<num_chef_opponents; i++) {
			opponent_chefs[i] = new ChefImpl("Opponent Chef " + i, STARTING_BALANCE, belt);
		}
		player_chef = new ChefImpl("Player", STARTING_BALANCE, belt);
	}

	public Chef getPlayerChef() {
		return player_chef;
	}

	public Chef[] getOpponentChefs() {
		return opponent_chefs.clone();
	}

	public Belt getBelt() {
		return belt;
	}
}
