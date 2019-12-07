import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * class for making a buy button and it's functions
 * 
 * @author Kevin and Jakub
 *
 */
public class BuyButton extends JButton implements ActionListener {
	private int albumId;
	private double price;

	private static int sortNumber = 0;
	private static String preferredFormat;
	
	private static double moneyInserted;

	public BuyButton(int productId, double price) {
		/*
		 * getId from database
		 * 
		 */
		this.setText("Buy");
		addActionListener(this);
		this.price = price;
		this.albumId = productId;
	}
	
	

	public static void setMoneyInserted(double newMoney) {
		moneyInserted = newMoney;
		System.out.println("TOTAL MONEY IN TEST: " + moneyInserted);
	}


	public void actionPerformed(ActionEvent e) {
		if (moneyInserted >= price) {
			boolean inStock = new DatabaseConnection().checkStock(albumId, preferredFormat);
			if (inStock) {
				moneyInserted = moneyInserted - price;
				
				MusicVendingMachineGUI.ThankYouPopupWindow();
				MusicVendingMachineGUI.setMoney(0);	 // Update the money in both classes
				new DatabaseConnection().decrementStock(albumId, preferredFormat);
				new DatabaseConnection().newTransaction(albumId);
			}
			else {
				MusicVendingMachineGUI.NotInStock();
			}
			
		} else {
			System.out.println("Not enough money"); //testing
			MusicVendingMachineGUI.BadPopupWindow();
		}
		// Run this function when user clicks the buy button.
		// It should take the id, price and format selected
		// and it should decrement the corresponding stock for the format

	}

	public int getProductId() {
		return albumId;
	}

	public void setProductId(int productId) {
		this.albumId = productId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public static double getMoneyInserted() {
		return moneyInserted;
	}



	public static String getPreferredFormat() {
		return preferredFormat;
	}



	public static void setPreferredFormat(String preferredFormat) {
		BuyButton.preferredFormat = preferredFormat;
	}



	public int getAlbumId() {
		return albumId;
	}



	public void setAlbumId(int albumId) {
		this.albumId = albumId;
	}



	public static int getSortNumber() {
		return sortNumber;
	}



	public static void setSortNumber(int sortNumber) {
		BuyButton.sortNumber = sortNumber;
	}

}
