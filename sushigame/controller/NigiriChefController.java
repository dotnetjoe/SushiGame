package sushigame.controller;

import comp401.sushi.BluePlate;
import comp401.sushi.GreenPlate;
import comp401.sushi.Nigiri;
import comp401.sushi.Plate;
import comp401.sushi.PlatePriceException;
import comp401.sushi.RedPlate;
import sushigame.model.AlreadyPlacedThisRotationException;
import sushigame.model.BeltEvent;
import sushigame.model.BeltFullException;
import sushigame.model.Chef;
import sushigame.model.InsufficientBalanceException;

public class NigiriChefController implements ChefController {

	private Chef chef;
	private double makeFrequency;
	private int belt_size;

	public NigiriChefController(Chef c, int belt_size) {
		chef = c;
		makeFrequency = Math.random() * 0.5 + 0.25;
		this.belt_size = belt_size;
	}

	@Override
	public void handleBeltEvent(BeltEvent e) {
		if (e.getType() == BeltEvent.EventType.ROTATE) {
			if (Math.random() < makeFrequency) {
				Nigiri.NigiriType type = pickType();
				Plate plate = null;
				try {
					switch(pickColor()) {
					case RED:
						plate = new RedPlate(chef, new Nigiri(type)); 
						break;
					case GREEN:
						plate = new GreenPlate(chef, new Nigiri(type)); 
						break;
					case BLUE:
						plate = new BluePlate(chef, new Nigiri(type)); 
						break;
					case GOLD:
						// This will never happen but need the case for 
						// the switch statement.
						return;
					}
				}
				catch (PlatePriceException exc) {
					// Nigiri too expensive for plate we chose.
					// Bail and do not try to place plate.
					return;
				}
				try {
					chef.makeAndPlacePlate(plate, (int) (Math.random()*belt_size));
				} catch (InsufficientBalanceException | BeltFullException | AlreadyPlacedThisRotationException exc) {
					// Too little money, belt too full, or already went this rotation.
					// Bail and do nothing.
					return;
				}
			}
		}
	}


	private Nigiri.NigiriType pickType() {
		Nigiri.NigiriType[] types = new Nigiri.NigiriType[] {
				Nigiri.NigiriType.CRAB,
				Nigiri.NigiriType.EEL, 
				Nigiri.NigiriType.SALMON, 
				Nigiri.NigiriType.TUNA, 
				Nigiri.NigiriType.SHRIMP
		};
		return types[(int) (Math.random()*types.length)];
	}

	private Plate.Color pickColor() {
		Plate.Color[] colors = new Plate.Color[] {
				Plate.Color.RED, Plate.Color.GREEN, Plate.Color.BLUE
		};

		return colors[(int) (Math.random()*colors.length)];
	}

}
