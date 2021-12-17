import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class shopSystem {
	public ArrayList<Cart> cartList = new ArrayList<Cart>();
	
	
	public shopSystem() {
		
		
	}
	
	public String viewCart(Integer cartId, String address) throws NumberFormatException, IOException {
		for(int i = 0; i < this.cartList.size(); i++) {
			if(cartId.equals(this.cartList.get(i).Id)) {
				return this.cartList.get(i).calculateTotal();
			}
		}
		return "";
	}
	
	public String addItem(Integer itemId, Integer cartId) throws FileNotFoundException, IOException {
		for(int i = 0; i < this.cartList.size(); i++) {
			if(cartId.equals(this.cartList.get(i).Id)) {
				if(this.cartList.get(i).addItem(itemId)) {
					return "item has been successfully added";
				}
			}
		}
		return "failed to add item";
	}
	
	public String applyDiscountCode(String code, Integer cartId) throws FileNotFoundException, IOException {
		for(int i = 0; i < this.cartList.size(); i++) {
			if(cartId.equals(this.cartList.get(i).Id)) {
				if(this.cartList.get(i).addDiscountCode(code)) {
					return "code has been successfully added";
				}
			}
		}
		return "failed to add code";
		
	}
	
	public String modifyItemNumber(Integer itemId, Integer quantity, Integer cartId) throws IOException {
		for(int i = 0; i < this.cartList.size(); i++) {
			if(cartId.equals(this.cartList.get(i).Id)) {
				if(this.cartList.get(i).modifyItemNumber(itemId, quantity)) {
					return "quantity has been successfully modified";
				}
			}
		}
		return "failed to modify quantity";
	}

}
