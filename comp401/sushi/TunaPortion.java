package comp401.sushi;

public class TunaPortion extends IngredientPortionImpl {

	private static final Ingredient TUNA = new Tuna();
	
	public TunaPortion(double amount) {
		super(amount, TUNA);
	}
	
	@Override
	public IngredientPortion combine(IngredientPortion other) {
		if (other == null) {
			return this;
		}
		if (!other.getIngredient().equals(TUNA)) {
			throw new RuntimeException("Can not combine portions of different ingredients");
		}
		return new TunaPortion(other.getAmount()+this.getAmount());
	}

}
