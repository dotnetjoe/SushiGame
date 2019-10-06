package comp401.sushi;

public class AvocadoPortion extends IngredientPortionImpl {

	private static final Ingredient AVOCADO = new Avocado();
	
	public AvocadoPortion(double amount) {
		super(amount, AVOCADO);
	}
	
	@Override
	public IngredientPortion combine(IngredientPortion other) {
		if (other == null) {
			return this;
		}
		if (!other.getIngredient().equals(AVOCADO)) {
			throw new RuntimeException("Can not combine portions of different ingredients");
		}
		return new AvocadoPortion(other.getAmount()+this.getAmount());
	}

}
