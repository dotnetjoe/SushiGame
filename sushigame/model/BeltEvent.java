package sushigame.model;

import java.awt.event.ItemEvent;

abstract public class BeltEvent {
	public enum EventType {PLATE_PLACED, PLATE_CONSUMED, PLATE_SPOILED, ROTATE, TYPE_OF_SUSHI_CHANGED,
		SPECIFIC_TYPE_OF_SUSHI_CHANGED, NUM_ROLL_INGREDS_CHANGED, ROLL_INGRED_ADDED, PLATE_TYPE_CHANGED}

	private BeltEvent.EventType type;
	private ItemEvent e;

	public BeltEvent(EventType type) {
		this.type = type;
	}
	
	public BeltEvent(EventType type, ItemEvent e){
		this.type = type;
		this.e = e;
	}
	
	public BeltEvent.EventType getType() {
		return type;
	}
	
	public ItemEvent getItemEvent(){
		return e;
	}
}
