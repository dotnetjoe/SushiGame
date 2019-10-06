package comp401.sushi;

import sushigame.model.Chef;

public class BluePlate extends PlateImpl {

	public BluePlate(Chef chef, Sushi s) throws PlatePriceException {
		super(chef, s, 4.0, Plate.Color.BLUE);
	}
}
