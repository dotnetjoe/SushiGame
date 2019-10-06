package sushigame.model;


import comp401.sushi.Plate;

public interface TimedPlate extends Plate {
	int getInceptDate();
	Plate getOriginal();
}
