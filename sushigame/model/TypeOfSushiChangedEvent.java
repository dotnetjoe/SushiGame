package sushigame.model;

import java.awt.event.ItemEvent;

public class TypeOfSushiChangedEvent extends BeltEvent {

	public TypeOfSushiChangedEvent(ItemEvent e){
		super(BeltEvent.EventType.TYPE_OF_SUSHI_CHANGED, e);
	}
}
