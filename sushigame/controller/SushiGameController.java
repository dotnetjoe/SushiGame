package sushigame.controller;

import sushigame.model.Belt;
import sushigame.model.Chef;
import sushigame.model.SushiGameModel;
import sushigame.view.RotationRequestListener;
import sushigame.view.SushiGameView;

public class SushiGameController implements RotationRequestListener {

	private Belt belt;
	
	public SushiGameController(SushiGameModel game_model, SushiGameView game_view) {
		belt = game_model.getBelt();
		game_view.registerRotationRequestListener(this);
		
		Chef[] opponent_chefs = game_model.getOpponentChefs();
		
		for (Chef c: opponent_chefs) {
			double random_draw = Math.random();
			ChefController chef_controller = null;
			String name = createRandomName();
			
			if (random_draw < 0.333) {
				c.setName("Sashimi " + name);
				chef_controller = new SashimiChefController(c, belt.getSize());
			} else if (random_draw < 0.66666) {
				c.setName("Nigiri " + name);
				chef_controller = new NigiriChefController(c, belt.getSize());				
			} else {
				c.setName("Rollmaker " + name);
				chef_controller = new RollMakerChefController(c, belt.getSize());
			}
			belt.registerBeltObserver(chef_controller);
		}
		
		// Refresh the game view's scoreboard now that chef names may have been changed.
		game_view.refreshScoreboard();
		
		PlayerChefController player_chef_controller = new PlayerChefController(game_model.getPlayerChef(), game_view);
		game_view.registerPlayerChefListener(player_chef_controller);
	}

	@Override
	public void handleRotationRequest() {
		belt.rotate();
	}

	private String createRandomName() {
		String[] names = new String[] {
				"Bob", "Carol", "Alex", "Sejal", "Hussein", 
				"Ming", "Carlos", "Tom", "Lester", "Maya",
				"Malcolm", "Grace", "Darius", "Thor", "Keisha"};
		return names[(int) (Math.random()*names.length)];
	}
}
