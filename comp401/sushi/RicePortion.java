package comp401.sushi;

public class RicePortion extends IngredientPortionImpl {

	private static final Ingredient RICE = new Rice();
	
	public RicePortion(double amount) {
		super(amount, RICE);
	}
	
	@Override
	public IngredientPortion combine(IngredientPortion other) {
		if (other == null) {
			return this;
		}
		if (!other.getIngredient().equals(RICE)) {
			throw new RuntimeException("Can not combine portions of different ingredients");
		}
		return new RicePortion(other.getAmount()+this.getAmount());
	}

}
