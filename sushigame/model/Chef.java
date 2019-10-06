package sushigame.model;

import comp401.sushi.Plate;


public interface Chef {

	String getName();
	void setName(String name);
	
	void makeAndPlacePlate(Plate plate, int position) 
			throws InsufficientBalanceException, BeltFullException, AlreadyPlacedThisRotationException;
		
	HistoricalPlate[] getPlateHistory(int max_history_length);
	HistoricalPlate[] getPlateHistory();
	
	double getBalance();
	double getConsumed();
	double getSpoiled();
	
	boolean alreadyPlacedThisRotation();
}

