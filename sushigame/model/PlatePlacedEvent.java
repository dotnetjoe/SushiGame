package sushigame.model;

import comp401.sushi.Plate;

public class PlatePlacedEvent extends PlateEvent {

	public PlatePlacedEvent (Plate p, int position) {
		super(BeltEvent.EventType.PLATE_PLACED, p, position);
	}
}
