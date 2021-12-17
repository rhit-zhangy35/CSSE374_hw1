
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;


public class TestSystem {
	
	@Test
	public void test() throws NumberFormatException, IOException {
		Item item = new Item(1, "item1", "item1des", 10.0, "item1.jpg", 
				0.9);
		assertEquals(1, item.getQuantity());
		assertEquals(9.0, item.calculatePrice(), 0.01);
		assertEquals("1, item1, item1des, 10.0, item1.jpg, 0.9, 1", item.printInfo());
		assertTrue(item.checkIfInStock());
		item.setQuantity(2);
		assertEquals(2, item.getQuantity());
		
		ArrayList<Integer> itemId = new ArrayList<Integer>();
		itemId.add(1);
		ArrayList<Integer> quantity = new ArrayList<Integer>();
		quantity.add(5);
		ArrayList<Double> rate = new ArrayList<Double>();
		rate.add(0.5);
		discountCode code = new discountCode("C1", itemId, rate, quantity);
		assertEquals(1, code.hasId(1));
		assertEquals(5, code.getQuantity(1));
		assertEquals(0.5, code.getRate(1), 0.01);
		
		Cart cart = new Cart(1);
		assertTrue(cart.addItem(1));
		assertTrue(cart.addDiscountCode("C1"));
		assertEquals("1, item1, item1des, 1.0, item1.jpg, 0.9, 1\n0.81", cart.calculateTotal());
		assertTrue(cart.modifyItemNumber(1, 3));
		
		shopSystem system = new shopSystem();
		Cart cart2 = new Cart(2);
		system.cartList.add(cart2);
		assertEquals("item has been successfully added", system.addItem(1, 2));
		assertEquals("code has been successfully added", system.applyDiscountCode("C1", 2));
		assertEquals("1, item1, item1des, 1.0, item1.jpg, 0.9, 1\n0.81", system.viewCart(2, "default"));
		assertEquals("quantity has been successfully modified", system.modifyItemNumber(1, 3, 2));
		
		
	}

}
