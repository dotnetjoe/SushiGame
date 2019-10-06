package comp401.sushi;

public class Nigiri implements Sushi {

	public enum NigiriType {TUNA, SALMON, EEL, CRAB, SHRIMP}

	private static double NIGIRI_PORTION_AMOUNT = 0.75;
	private static double RICE_PORTION_AMOUNT = 0.5;
	
	private IngredientPortion seafood;
	private IngredientPortion rice;
	
	public Nigiri(NigiriType type) {
		rice = new RicePortion(RICE_PORTION_AMOUNT);

		switch(type) {
		case TUNA:
			seafood = new TunaPortion(NIGIRI_PORTION_AMOUNT);
			break;
		case SALMON:
			seafood = new SalmonPortion(NIGIRI_PORTION_AMOUNT);
			break;
		case EEL:
			seafood = new EelPortion(NIGIRI_PORTION_AMOUNT);
			break;
		case CRAB:
			seafood = new CrabPortion(NIGIRI_PORTION_AMOUNT);
			break;
		case SHRIMP:
			seafood = new ShrimpPortion(NIGIRI_PORTION_AMOUNT);
			break;			
		}
	}
	
	@Override
	public String getName() {
		return seafood.getName() + " nigiri";
	}

	@Override
	public IngredientPortion[] getIngredients() {
		return new IngredientPortion[] {seafood, rice};
	}

	@Override
	public int getCalories() {
		return (int) (seafood.getCalories() + rice.getCalories() + 0.5);
	}

	@Override
	public double getCost() {
		return ((int) ((seafood.getCost() + rice.getCost()) * 100.0 + 0.5)) / 100.0;
	}

	@Override
	public boolean getHasRice() {
		return true;
	}

	@Override
	public boolean getHasShellfish() {
		return seafood.getIsShellfish();
	}

	@Override
	public boolean getIsVegetarian() {
		return false;
	}

}
