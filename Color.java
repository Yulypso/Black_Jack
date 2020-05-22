//import java.math.*;

public enum Color { 
	HEART("♡"), SPADE("♠"), CLUB("♣"), DIAMOND("♢");

	private String symbole;

	//constructor
	private Color(String symbole) {
		this.symbole = symbole;
	}

	public String getSymbole() {
		return symbole;
	}
}