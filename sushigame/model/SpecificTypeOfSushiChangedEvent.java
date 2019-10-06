package sushigame.model;

import java.awt.event.ItemEvent;

public class SpecificTypeOfSushiChangedEvent extends BeltEvent {

	public SpecificTypeOfSushiChangedEvent(ItemEvent e){
		super(BeltEvent.EventType.SPECIFIC_TYPE_OF_SUSHI_CHANGED, e);
	}
}
