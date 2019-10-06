package comp401.sushi;

public class Roll implements Sushi {

	private String name;
	private IngredientPortion[] roll_ingredients;

	public Roll(String name, IngredientPortion[] roll_ingredients) {
		if (name == null) {
			throw new RuntimeException("Roll name is null");
		}
		this.name = name;
		if (roll_ingredients == null) {
			throw new RuntimeException("Roll ingredients is null");
		}
		for (int i=0; i<roll_ingredients.length; i++) {
			if (roll_ingredients[i] == null) {
				throw new RuntimeException("At least one roll ingredient is null");
			}
		}
		this.roll_ingredients = roll_ingredients.clone();
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public IngredientPortion[] getIngredients() {
		return roll_ingredients.clone();
	}

	@Override
	public int getCalories() {
		double calorie_sum = 0.0;
		for (int i=0; i<roll_ingredients.length; i++) {
			calorie_sum += roll_ingredients[i].getCalories();
		}
		
		return (int) (calorie_sum + 0.5);
	}

	@Override
	public double getCost() {
		double cost_sum = 0.0;
		for (int i=0; i<roll_ingredients.length; i++) {
			cost_sum += roll_ingredients[i].getCost();
		}
		
		return ((int) (cost_sum * 100.0 + 0.5))/100.0;
	}

	@Override
	public boolean getHasRice() {
		for (int i=0; i<roll_ingredients.length; i++) {
			if (roll_ingredients[i].getIsRice()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean getHasShellfish() {
		for (int i=0; i<roll_ingredients.length; i++) {
			if (roll_ingredients[i].getIsShellfish()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean getIsVegetarian() {
		for (int i=0; i<roll_ingredients.length; i++) {
			if (!roll_ingredients[i].getIsVegetarian()) {
				return false;
			}
		}
		return true;
	}

}
