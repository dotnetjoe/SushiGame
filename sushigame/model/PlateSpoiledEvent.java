package sushigame.model;

import comp401.sushi.Plate;

public class PlateSpoiledEvent extends PlateEvent {

	public PlateSpoiledEvent (Plate p, int position) {
		super(BeltEvent.EventType.PLATE_SPOILED, p, position);
	}
}
