package sushigame.model;

import java.awt.event.ItemEvent;

public class NumIngredsInRollChangedEvent extends BeltEvent{

	public NumIngredsInRollChangedEvent(ItemEvent e){
		super(BeltEvent.EventType.NUM_ROLL_INGREDS_CHANGED, e);
	}
}
