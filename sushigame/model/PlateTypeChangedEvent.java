package sushigame.model;

import java.awt.event.ItemEvent;

public class PlateTypeChangedEvent extends BeltEvent{

	public PlateTypeChangedEvent(ItemEvent e){
		super(BeltEvent.EventType.PLATE_TYPE_CHANGED, e);
	}
}
