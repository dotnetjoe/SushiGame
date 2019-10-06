package sushigame.view;

import java.util.Comparator;

import sushigame.model.Chef;

public class HighToLowFoodSoldOrder implements Comparator<Chef> {
	public int compare(Chef a, Chef b) {
		return (int) (Math.round(b.getConsumed()*100.0) - 
				Math.round(a.getConsumed()*100));
	}
}
