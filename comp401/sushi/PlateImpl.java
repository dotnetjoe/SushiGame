package comp401.sushi;

import sushigame.model.Chef;

abstract public class PlateImpl implements Plate {

	private Sushi contents;
	private double price;
	private Plate.Color color;
	private Chef chef;
		
	public PlateImpl(Chef chef, Sushi s, double price, Plate.Color color) throws PlatePriceException {
		if (s == null) {
			throw new IllegalArgumentException();
		}

		if (chef == null) {
			throw new IllegalArgumentException();
		}
		
		if (s.getCost() > price) {
			throw new PlatePriceException(this, s);
		}
		
		this.price = price;
		this.color = color;
		this.chef = chef;
		contents = s;
	}

	@Override
	public Sushi getContents() {
		return contents;
	}

	@Override
	public double getPrice() {
		return price;
	}

	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public double getProfit() {
		return getPrice() - contents.getCost();
	}
	
	@Override
	public Chef getChef() {
		return chef;
	}
}
