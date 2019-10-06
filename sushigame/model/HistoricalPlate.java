package sushigame.model;

import comp401.sushi.Plate;

public interface HistoricalPlate extends Plate {

	boolean wasSpoiled();
	Customer getConsumer();
}
