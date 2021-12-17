import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Item {
	public Integer Id;
	public String Name;
	public String Description;
	public Double Price;
	public String Picture;
	public Double DiscountRate;
	public Integer Quantity;
	
	public Item(int Id, String Name, String Description, Double Price, String Picture, 
			Double DiscountRate) {
		this.Id = Id;
		this.Name = Name;
		this.Description = Description;
		this.Price = Price;
		this.Picture = Picture;
		this.DiscountRate = DiscountRate;
		this.Quantity = 1;
		
		
	}
	
	public double calculatePrice() {
		return this.Price*this.Quantity*this.DiscountRate;
	}
	
	public int getQuantity() {
		return this.Quantity;
	}
	
	public String printInfo() {
		return this.Id.toString() + ", " + this.Name + ", " + this.Description + ", " + this.Price.toString() + ", " + 
	this.Picture + ", " + this.DiscountRate.toString() + ", " + this.Quantity.toString();
	}

	public boolean checkIfInStock() throws NumberFormatException, IOException {
		String[] info = new String[10];
		String split = ",";
		BufferedReader br = new BufferedReader(new FileReader("C:/Users/zhangy35/eclipse-workspace/MA374_hw1/src/ItemDatabase.csv"));
		String line = br.readLine();
		while ((line = br.readLine()) != null) {
			info = line.split(split);
			Integer inputId = Integer.parseInt(info[0]);
			if(inputId.equals(this.Id)) {
				Integer quantity = Integer.parseInt(info[6]);
				if(quantity >= this.Quantity) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void setQuantity(Integer quantity) {
		this.Quantity = quantity;
	}
	
	public void setDiscountRate(double rate) {
		this.DiscountRate = rate;
	}
}
