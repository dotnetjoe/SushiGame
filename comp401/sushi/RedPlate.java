package comp401.sushi;

import sushigame.model.Chef;

public class RedPlate extends PlateImpl {

	public RedPlate(Chef chef, Sushi s) throws PlatePriceException {
		super(chef, s, 1.0, Plate.Color.RED);
	}
}
