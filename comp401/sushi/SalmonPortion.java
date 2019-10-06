package comp401.sushi;

public class SalmonPortion extends IngredientPortionImpl {

	private static final Ingredient SALMON = new Salmon();
	
	public SalmonPortion(double amount) {
		super(amount, SALMON);
	}
	
	@Override
	public IngredientPortion combine(IngredientPortion other) {
		if (other == null) {
			return this;
		}
		if (!other.getIngredient().equals(SALMON)) {
			throw new RuntimeException("Can not combine portions of different ingredients");
		}
		return new SalmonPortion(other.getAmount()+this.getAmount());
	}

}
