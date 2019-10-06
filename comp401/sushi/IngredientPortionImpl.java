package comp401.sushi;

abstract public class IngredientPortionImpl implements IngredientPortion {

	private double amount;
	private Ingredient ingredient;

	protected IngredientPortionImpl(double amount, Ingredient ingredient) {
		if (amount <= 0.0) {
			throw new RuntimeException("Amount of ingredient portion must be greater than 0.0");
		}
		
		this.amount = amount;
		this.ingredient = ingredient;
	}
	
	@Override
	public Ingredient getIngredient() {
		return ingredient;
	}

	@Override
	public String getName() {
		return ingredient.getName();
	}

	@Override
	public double getAmount() {
		return amount;
	}

	@Override
	public double getCalories() {
		return amount * ingredient.getCaloriesPerOunce();
	}

	@Override
	public double getCost() {
		return amount * ingredient.getPricePerOunce();
	}

	@Override
	public boolean getIsVegetarian() {
		return ingredient.getIsVegetarian();
	}

	@Override
	public boolean getIsRice() {
		return ingredient.getIsRice();
	}

	@Override
	public boolean getIsShellfish() {
		return ingredient.getIsShellfish();
	}

	@Override
	abstract public IngredientPortion combine(IngredientPortion other);
}
