package sushigame.model;

import java.io.IOException;

public interface BeltObserver {
	public void handleBeltEvent(BeltEvent e) throws IOException;
}
