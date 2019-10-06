package sushigame.view;

import java.util.Comparator;

import sushigame.model.Chef;

public class LowToHighSpoilageComparator implements Comparator<Chef>{
	public int compare(Chef a, Chef b) {
		return (int) (Math.round(a.getSpoiled()*100.0) - 
				Math.round(b.getSpoiled()*100));
	}		
}
