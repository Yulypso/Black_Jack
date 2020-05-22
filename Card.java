public class Card extends Object {

	private Value value;
	private Color color;

	//constructor
	public Card(Value value, Color color) {
		this.value = value;
		this.color = color;
	}

	//methods
	@Override
	public String toString() {
		return value.getSymbole() + color.getSymbole();
	}

	public String getColorSymbole() {
		return color.getSymbole();
	}

	public String getColorName() {
		return color.name();
	}

	public String getValueSymbole() {
		return value.getSymbole();
	}

	public int getPoints() {
		return value.getPoints();
	}

}