package sushigame.model;

import java.awt.event.ItemEvent;

public class RollIngredAddedEvent extends BeltEvent {

	public RollIngredAddedEvent(ItemEvent e){
		super(BeltEvent.EventType.ROLL_INGRED_ADDED, e);
	}
}
