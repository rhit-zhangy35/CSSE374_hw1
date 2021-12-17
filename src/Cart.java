import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Cart {
	public Integer Id;
	public double tax;
	public ArrayList<Item> itemList;
	public ArrayList<discountCode> codeList;

	public Cart(Integer Id) {
		// TODO Auto-generated method stub
		this.Id = Id;
		this.tax = 0.0;
		this.itemList = new ArrayList<Item>();
		this.codeList = new ArrayList<discountCode>();

	}
	
	public boolean addItem(int itemId) throws FileNotFoundException, IOException {
		String[] info = new String[10];
		String split = ",";
		BufferedReader br = new BufferedReader(new FileReader("C:/Users/zhangy35/eclipse-workspace/MA374_hw1/src/ItemDatabase.csv"));
		String line = br.readLine();
		while ((line = br.readLine()) != null) {
			info = line.split(split);
			Integer inputId = Integer.parseInt(info[0]);
			if(inputId.equals(itemId)) {
				Integer quantity = Integer.parseInt(info[6]);
				if(quantity >= 1) {
					Item item = new Item(inputId, info[1], info[2], Double.parseDouble(info[3]), info[4], 
							Double.parseDouble(info[5]));
					this.itemList.add(item);
					br.close();
					return true;
					
				}
			}
			
		}
		br.close();
		return false;
		
	}
	
	public boolean addDiscountCode(String code) throws FileNotFoundException, IOException {
		String[] info = new String[10];
		String split = ",";
		BufferedReader br = new BufferedReader(new FileReader("C:/Users/zhangy35/eclipse-workspace/MA374_hw1/src/DiscountDatabase.csv"));
		String line = br.readLine();
		while ((line = br.readLine()) != null) {
			info = line.split(split);
			if(code.equals(info[0])) {
				ArrayList<Integer>itemId = new ArrayList<Integer>();
				ArrayList<Integer>quantity = new ArrayList<Integer>();
				ArrayList<Double>rate = new ArrayList<Double>();
				for(int i = 1; i < info.length; i+=3) {
					itemId.add(Integer.parseInt(info[i]));
					rate.add(Double.parseDouble(info[i+1]));
					quantity.add(Integer.parseInt(info[i+2]));
				}
				this.codeList.add(new discountCode(code, itemId, rate, quantity));
				br.close();
				return true;
			}
			
		}
		br.close();
		return false;
		
	}
	
	public String calculateTotal() throws NumberFormatException, IOException {
		Double total = 0.0;
		String str = "";
		for(int i = 0; i < this.itemList.size(); i++) {
			if(this.itemList.get(i).checkIfInStock() == false) {
				return "item out of stock";
			}
			double price = this.itemList.get(i).calculatePrice();
			for(int j = 0; j < this.codeList.size(); j++) {
				if(this.codeList.get(j).hasId(this.itemList.get(i).Id) != 0){
					price = price * this.codeList.get(j).getRate(this.itemList.get(i).Id);
				}
			}
			str += this.itemList.get(i).printInfo();
			str += "\n";
			total += price;
		}
		total += total*this.tax;
		str += total.toString();
		return str;
		
	}
	
	public boolean modifyItemNumber(Integer itemId, Integer quantity) throws IOException {
		for(int i = 0; i < this.itemList.size(); i++) {
			if(itemId.equals(this.itemList.get(i).Id)) {
				String[] info = new String[10];
				String split = ",";
				BufferedReader br = new BufferedReader(new FileReader("C:/Users/zhangy35/eclipse-workspace/MA374_hw1/src/ItemDatabase.csv"));
				String line = br.readLine();
				while ((line = br.readLine()) != null) {
					info = line.split(split);
					Integer inputId = Integer.parseInt(info[0]);
					if(inputId.equals(itemId)) {
						Integer dataQuantity = Integer.parseInt(info[6]);
						if(dataQuantity >= quantity) {
							this.itemList.get(i).setQuantity(quantity);
							br.close();
							return true;
						}
						
					}
				}
			}
		}
		return false;
		
	}
	
	

}
