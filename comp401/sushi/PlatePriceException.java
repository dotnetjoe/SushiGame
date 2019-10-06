package comp401.sushi;

public class PlatePriceException extends Exception {

	private Plate plate;
	private Sushi sushi;
	
	public PlatePriceException(Plate p, Sushi s) {
		super("Plate price is too low for sushi placed on it.");

		plate = p;
		sushi = s;
	}
}
