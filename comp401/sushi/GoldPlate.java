package comp401.sushi;

import sushigame.model.Chef;

public class GoldPlate extends PlateImpl {

	public GoldPlate(Chef chef, Sushi s, double price) throws PlatePriceException {
		super(chef, s, check_price(price), Plate.Color.GOLD);
	}
	
	private static double check_price(double price) {
		if (price < 5.0) {
			throw new IllegalArgumentException();
		}
		return price;
	}
}
