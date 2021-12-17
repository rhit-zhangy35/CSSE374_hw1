import java.util.ArrayList;
import java.util.HashMap;

public class discountCode {
	public String code;
	public ArrayList<Integer>itemId;
	public ArrayList<Double> rate;
	public ArrayList<Integer> quantity;
	
	public discountCode(String code, ArrayList<Integer>itemId, ArrayList<Double> rate, ArrayList<Integer> quantity) {
		this.code = code;
		this.itemId = itemId;
		this.rate = rate;
		this.quantity = quantity;
	}
	
	public int hasId(int id) {
		for(int i = 0; i < this.itemId.size(); i++ ) {
			if(this.itemId.get(i).equals(id)){
				return 1;
			}
		}
		return 0;
	}
	public int getQuantity(int id) {
		for(int i = 0; i < this.itemId.size(); i++ ) {
			if(this.itemId.get(i).equals(id)){
				return this.quantity.get(i);
			}
		}
		return 0;
	}
	public double getRate(int id) {
		for(int i = 0; i < this.itemId.size(); i++ ) {
			if(this.itemId.get(i).equals(id)){
				return this.rate.get(i);
			}
		}
		return 0;
	}

}
