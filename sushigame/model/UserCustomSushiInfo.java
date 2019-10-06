package sushigame.model;

import java.util.Observable;

import java.util.Observer;

import javax.swing.JComboBox;

import sushigame.view.SushiMakerWindow;

public class UserCustomSushiInfo implements java.util.Observer {

	private String selected_sushi_type, selected_specific_sushi_type, num_of_roll_ingreds, roll_ingred_added,
			plate_type;
	
	public UserCustomSushiInfo(SushiMakerWindow sushiMakerWindow){
		sushiMakerWindow.addObserver(this);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		BeltEvent event = (BeltEvent) arg;
		JComboBox cb = (JComboBox) event.getItemEvent().getSource();
		
		if(event.getType() == BeltEvent.EventType.TYPE_OF_SUSHI_CHANGED){
			selected_sushi_type = (String) cb.getSelectedItem();
		}else if(event.getType() == BeltEvent.EventType.SPECIFIC_TYPE_OF_SUSHI_CHANGED){
			selected_specific_sushi_type = (String) cb.getSelectedItem();
		}else if(event.getType() == BeltEvent.EventType.NUM_ROLL_INGREDS_CHANGED){
			num_of_roll_ingreds = (String) cb.getSelectedItem();
		}else if(event.getType() == BeltEvent.EventType.ROLL_INGRED_ADDED){
			roll_ingred_added = (String) cb.getSelectedItem();
		}else if(event.getType() == BeltEvent.EventType.PLATE_TYPE_CHANGED){
			plate_type = (String) cb.getSelectedItem();
		}
	}
	
	public String getSelectedSushiType(){
		return selected_sushi_type;
	}
	
	public String getSelectedSpecificSushiType(){
		return selected_specific_sushi_type;
	}

	public String getNumOfRollIngreds(){
		return num_of_roll_ingreds;
	}
	
	public String getRollIngredAdded(){
		return roll_ingred_added;
	}
	
	public String getPlateType(){
		return plate_type;
	}

}
