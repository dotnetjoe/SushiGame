package comp401.sushi;

public class SeaweedPortion extends IngredientPortionImpl {

	private static final Ingredient SEAWEED = new Seaweed();
	
	public SeaweedPortion(double amount) {
		super(amount, SEAWEED);
	}
	
	@Override
	public IngredientPortion combine(IngredientPortion other) {
		if (other == null) {
			return this;
		}
		if (!other.getIngredient().equals(SEAWEED)) {
			throw new RuntimeException("Can not combine portions of different ingredients");
		}
		return new SeaweedPortion(other.getAmount()+this.getAmount());
	}

}
