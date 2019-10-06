package sushigame.controller;

import comp401.sushi.AvocadoPortion;
import comp401.sushi.CrabPortion;
import comp401.sushi.EelPortion;
import comp401.sushi.GoldPlate;
import comp401.sushi.IngredientPortion;
import comp401.sushi.Plate;
import comp401.sushi.PlatePriceException;
import comp401.sushi.RicePortion;
import comp401.sushi.Roll;
import comp401.sushi.SalmonPortion;
import comp401.sushi.SeaweedPortion;
import comp401.sushi.ShrimpPortion;
import comp401.sushi.TunaPortion;
import sushigame.model.AlreadyPlacedThisRotationException;
import sushigame.model.BeltEvent;
import sushigame.model.BeltFullException;
import sushigame.model.Chef;
import sushigame.model.InsufficientBalanceException;

public class RollMakerChefController implements ChefController {

	private Chef chef;
	private double makeFrequency;
	private int belt_size;

	public RollMakerChefController(Chef c, int belt_size) {
		chef = c;
		makeFrequency = Math.random() * 0.5 + 0.25;
		this.belt_size = belt_size;
	}

	@Override
	public void handleBeltEvent(BeltEvent e) {

		if (e.getType() == BeltEvent.EventType.ROTATE) {
			if (Math.random() < makeFrequency) {
				Roll random_roll = makeRandomRoll();
				Plate plate = null;
				try {
					plate = new GoldPlate(chef, random_roll, Math.random()*3 + 5.0);
				}
				catch (PlatePriceException exc) {
					// Roll too expensive for price we chose.
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

	private Roll makeRandomRoll() {
		
		return new Roll("Random Roll",
				new IngredientPortion[] {
						new AvocadoPortion(Math.random()),
						new CrabPortion(Math.random()),
						new EelPortion(Math.random()),
						new RicePortion(Math.random()),
						new SalmonPortion(Math.random()),
						new SeaweedPortion(Math.random()),
						new ShrimpPortion(Math.random()),
						new TunaPortion(Math.random()),
		});
}}