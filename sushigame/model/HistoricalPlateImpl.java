package sushigame.model;

import comp401.sushi.Plate;
import comp401.sushi.Sushi;

public class HistoricalPlateImpl implements HistoricalPlate {

	private Customer consumer;
	private Plate plate;
	
	public HistoricalPlateImpl(Plate p, Customer c) {
		plate = p;
		consumer = c;
	}
	
	@Override
	public Sushi getContents() {
		return plate.getContents();
	}

	@Override
	public double getPrice() {
		return plate.getPrice();
	}

	@Override
	public Color getColor() {
		return plate.getColor();
	}

	@Override
	public double getProfit() {
		return plate.getProfit();
	}

	@Override
	public Chef getChef() {
		return plate.getChef();
	}

	@Override
	public boolean wasSpoiled() {
		return (consumer == null);
	}

	@Override
	public Customer getConsumer() {
		return consumer;
	}

}
