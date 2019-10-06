package sushigame.model;

import comp401.sushi.Plate;

interface ChefsBelt extends Belt {

	int setPlateNearestToPosition(Plate plate, int position) throws BeltFullException;

	
}
