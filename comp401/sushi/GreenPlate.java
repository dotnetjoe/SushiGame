package comp401.sushi;

import sushigame.model.Chef;

public class GreenPlate extends PlateImpl {

	public GreenPlate(Chef chef, Sushi s) throws PlatePriceException {
		super(chef, s, 2.0, Plate.Color.GREEN);
	}
}
