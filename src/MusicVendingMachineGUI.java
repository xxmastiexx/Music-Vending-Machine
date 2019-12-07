import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Vector;

import javax.lang.model.type.NullType;
import javax.sound.sampled.Line;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.mysql.fabric.xmlrpc.base.Data;

/**
 * GUI for the music vending machine, along with functionality
 * 
 * @author Kevin and Jakub
 *
 */
public class MusicVendingMachineGUI extends JFrame implements ActionListener {
	double albumPrice;
	private static double money = 0;
	private JTextField moneyField = new JTextField(" ");
	private JPanel mainWindow;
	private JPanel productList = new JPanel();
	private int preferredYSize;

	int sortCase = BuyButton.getSortNumber();
	
	JTextArea createAlbumInfo = new JTextArea("");
	JTextArea restockInfo = new JTextArea();
	JTextField idToDelete = new JTextField();
	
	JTextField usernameField = new JTextField();
	JPasswordField passwordField = new JPasswordField();

	ButtonGroup formatButtons;

	// JTextFields for creating a new database entry
	JTextField newAlbumName, newAlbumArtist, newYearOfRelease, newTracklist, newGenre, newPrice, newStockCD,
			newStockVinyl;

	JFrame adminFrame, createFrame, loginFrame;
	Color lightBlue = new Color(50, 150, 200);
	Color lightBlueButton = new Color(70,175,230);
	Color greyButton = new Color(75,75,75);

	///////////////////////////
	/// Getters and Setters ///
	///////////////////////////

	public double getAlbumPrice() {
		return albumPrice;
	}

	public void setAlbumPrice(double albumPrice) {
		this.albumPrice = albumPrice;
	}

	public static double getMoney() {
		return money;
	}

	public static void setMoney(double money) {
		MusicVendingMachineGUI.money = money;

	}

	public JTextField getMoneyField() {
		return moneyField;
	}

	public void setMoneyField(JTextField moneyField) {
		this.moneyField = moneyField;
		moneyField.setText("€" + BuyButton.getMoneyInserted());
	}

	public JPanel getMainWindow() {
		return mainWindow;
	}

	public void setMainWindow(JPanel mainWindow) {
		this.mainWindow = mainWindow;
	}

	public JPanel getProductList() {
		return productList;
	}

	public void setProductList(JPanel productList) {
		this.productList = productList;
	}

	public int getPreferredYSize() {
		return preferredYSize;
	}

	public void setPreferredYSize(int preferredYSize) {
		this.preferredYSize = preferredYSize;
	}

	public Color getLightBlue() {
		return lightBlue;
	}

	public void setLightBlue(Color lightBlue) {
		this.lightBlue = lightBlue;
	}

	public MusicVendingMachineGUI() {

		JPanel titlePanel = new JPanel();
		Icon titleBar = new ImageIcon("img/title.png");
		JLabel titleLabel = new JLabel(titleBar);
	
		titlePanel.setLayout(new BorderLayout());
		titlePanel.add(titleLabel, BorderLayout.CENTER);
		
		JButton sortByArtist = new JButton("Artist");
		sortByArtist.addActionListener(this);
		sortByArtist.setPreferredSize(new Dimension(160,30));
		JButton sortByAlbum = new JButton("Album");
		sortByAlbum.setPreferredSize(new Dimension(230,30));
		sortByAlbum.addActionListener(this);
		JButton sortByPrice = new JButton("Price");
		sortByPrice.setPreferredSize(new Dimension(75,30));
		sortByPrice.addActionListener(this);
		JButton sortByGenre = new JButton("Genre");
		sortByGenre.setPreferredSize(new Dimension(80,30));
		sortByGenre.addActionListener(this);
		JButton sortByYear = new JButton("Year of Release");
		sortByYear.setPreferredSize(new Dimension(130,30));
		sortByYear.addActionListener(this);

		// Format Buttons
		JRadioButton cdFormat = new JRadioButton("CD", true);
		cdFormat.setActionCommand("stockCD");
		JRadioButton vinylFormat = new JRadioButton("Vinyl", false);
		vinylFormat.setActionCommand("stockVinyl");

		formatButtons = new ButtonGroup();

		formatButtons.add(cdFormat);
		
		formatButtons.add(vinylFormat);


		JButton adminViewer = new JButton("admin"); // button to go to the admin window - will have a password
		titlePanel.add(adminViewer, BorderLayout.EAST);
		adminViewer.addActionListener(this);

		mainWindow = new JPanel();
		mainWindow.setLayout(new BorderLayout());
		JScrollPane productListScroll = new JScrollPane(productList);

		preferredYSize = 200;
		productList.setPreferredSize(new Dimension(700, preferredYSize));

		mainWindow.add(productListScroll);

		makeListEntry();

		JPanel moneyPanel = new JPanel();
		moneyPanel.setLayout(new GridLayout(1, 7));
		JPanel topBar = new JPanel();
	

		/////////////////////////////////
		/// designing the money panel ///
		/////////////////////////////////

		JButton oneEuro = new JButton("€1");
		JButton twoEuro = new JButton("€2");
		JButton fiveEuro = new JButton("€5");
		JButton tenEuro = new JButton("€10");
		JButton cancelButton = new JButton("X");

		cancelButton.setBackground(Color.RED);
		cancelButton.setForeground(Color.WHITE);

		JLabel instruct = new JLabel("Money In: ");

		moneyField.setEditable(false);
		moneyPanel.add(oneEuro);
		oneEuro.addActionListener(this);
		moneyPanel.add(twoEuro);
		twoEuro.addActionListener(this);
		moneyPanel.add(fiveEuro);
		fiveEuro.addActionListener(this);
		moneyPanel.add(tenEuro);
		tenEuro.addActionListener(this);
		moneyPanel.add(instruct);

		moneyPanel.add(moneyField);

		// cancelButton
		cancelButton.addActionListener(this);
		moneyPanel.add(cancelButton);

		setSize(840, 600);
		setLocation(200, 100);
		setVisible(true);
		setLayout(new BorderLayout());
		setResizable(false);

		//adding components to the top bar
		mainWindow.add(topBar, BorderLayout.NORTH);
		
		topBar.add(sortByAlbum);
		topBar.add(sortByArtist);
		topBar.add(sortByGenre);
		topBar.add(sortByYear);
		topBar.add(sortByPrice);
		
		topBar.add(cdFormat);

		topBar.add(vinylFormat);

		
		add(titlePanel, BorderLayout.NORTH);
		

		// titleLabel.setHorizontalAlignment(JLabel.CENTER);
		add(mainWindow, BorderLayout.CENTER);
		add(moneyPanel, BorderLayout.SOUTH);

	}

	/**
	 * Display the list of all albums in the database.
	 */
	public void makeListEntry() {
		/////////////////////////////////////////////
		/// Passing the list into the GUI program ///
		/////////////////////////////////////////////
		DatabaseConnection connect = new DatabaseConnection();
		switch (sortCase) {
		case 0:
			connect.getAlbums();
			break;
		case 1:
			connect.sortByArtist();
			break;
		case 2:
			connect.sortByYear();
			break;
		case 3:
			connect.sortByGenre();
			break;
		case 4:
			connect.sortByPrice();
			break;
		}

		Vector<Album> list = new Vector<Album>();
		list = connect.getAlbumList(); // passing the album list into this class, using a vector

		for (int count = 0; count < list.size(); count++) { // for every element in the database, run this code...
			Album album = list.elementAt(count); // makes an album object for each album in the vector.
			preferredYSize = preferredYSize + 28; // increases the vertical size of the inner window.
			productList.setPreferredSize(new Dimension(700, preferredYSize));
			JPanel listEntry = new JPanel();
			int id = album.getAlbumId();

			///////////////////////////////////////
			/// Creating fields in product list ///
			///////////////////////////////////////

			// Album Name
			TrackList albumNameButton = new TrackList(id);
			albumNameButton.setText((count + 1) + ":  " + album.getAlbumName());
			albumNameButton.setPreferredSize(new Dimension(225, 30));
			albumNameButton.setHorizontalAlignment(SwingConstants.LEFT);

			// Artist Name
			JTextField artistName = new JTextField(album.getArtist());
			artistName.setEditable(false);
			artistName.setPreferredSize(new Dimension(155, 30));

			//Genre
			JTextField genreTextField = new JTextField("" + album.getGenre());
			genreTextField.setEditable(false);
			genreTextField.setPreferredSize(new Dimension(80,30));
			genreTextField.setBorder(null);
			
			//Year Of Release
			JTextField yearText = new JTextField("" + album.getYearOfRelease());
			yearText.setEditable(false);
			yearText.setPreferredSize(new Dimension(125,30));
			yearText.setBorder(null);
			
			// Price
			albumPrice = album.getPrice();
			JTextField price = new JTextField("           €" + albumPrice);
			price.setPreferredSize(new Dimension(65, 30));
			price.setEditable(false);
			price.setBorder(null);

			// Adding components to the gui
			listEntry.add(albumNameButton);
			listEntry.add(artistName);
			listEntry.add(genreTextField);
			listEntry.add(yearText);
			listEntry.add(price);

			BuyButton newBuyButton = new BuyButton(id, albumPrice);
			newBuyButton.addActionListener(this);
			newBuyButton.setPreferredSize(new Dimension(110, 30));
			listEntry.add(newBuyButton);

			//////////////////////////////////////////
			/// Setting the colours and design ///
			//////////////////////////////////////////

			albumNameButton.setBackground(greyButton);
			artistName.setBackground(Color.DARK_GRAY);
			listEntry.setBackground(Color.DARK_GRAY);
			price.setBackground(Color.DARK_GRAY);
			genreTextField.setBackground(Color.DARK_GRAY);
			yearText.setBackground(Color.DARK_GRAY);

			albumNameButton.setForeground(Color.WHITE);
			artistName.setForeground(Color.WHITE);
			artistName.setBorder(null);
			genreTextField.setForeground(Color.WHITE);
			yearText.setForeground(Color.WHITE);
			
			listEntry.setForeground(Color.WHITE);

			price.setForeground(Color.WHITE);

			if (count % 2 == 0) {

				albumNameButton.setBackground(lightBlueButton);
				artistName.setBackground(lightBlue);
				listEntry.setBackground(lightBlue);
				genreTextField.setBackground(lightBlue);
				yearText.setBackground(lightBlue);
				price.setBackground(lightBlue);

			}
			productList.add(listEntry);
			productList.setBackground(Color.WHITE);

		}

	}

	/////////////////////////
	/// GUI Popup windows ///
	/////////////////////////

	public static void ThankYouPopupWindow() {
		JOptionPane.showMessageDialog(null, "Thank you. Please take your change.","Thank You!", JOptionPane.INFORMATION_MESSAGE);
	}

	public static void BadPopupWindow() {
		JOptionPane.showMessageDialog(null, "That's not enough. Please insert more money please.","Error", JOptionPane.INFORMATION_MESSAGE);
	}
	public void WrongLogin() {
		JOptionPane.showMessageDialog(null, "Sorry, you have entered the wrong username or password.\nPlease try again.","Error", JOptionPane.INFORMATION_MESSAGE);
		
	}

	public static void NotInStock() {
		JOptionPane.showMessageDialog(null, "That album is not in stock in your selected format.","Error", JOptionPane.INFORMATION_MESSAGE);
	}

	public static void TracklistPopupWindow(String tracklist) {
		JOptionPane.showMessageDialog(null, tracklist,"Tracklist", JOptionPane.INFORMATION_MESSAGE);
	}

	public static void main(String[] args) {

		new MusicVendingMachineGUI();

	}

	///////////////////////////////////////////////////////
	//////// new window for testing transaction viewer ////
	///////////////////////////////////////////////////////

	public void adminLogin() {
		loginFrame = new JFrame();
		
		loginFrame.setVisible(true);
		loginFrame.setSize(400, 100);
		loginFrame.setLocation(200, 100);
		loginFrame.setResizable(false);
		loginFrame.setLayout(new BorderLayout());
		
		JPanel nameAndPasswordField = new JPanel();
		nameAndPasswordField.setLayout(new GridLayout(2,2));
		JLabel usernameLabel = new JLabel("Username");
		JLabel passwordLabel = new JLabel("Password");
		passwordField.setEchoChar('*');
		nameAndPasswordField.add(usernameLabel);
		nameAndPasswordField.add(usernameField);
		nameAndPasswordField.add(passwordLabel);
		nameAndPasswordField.add(passwordField);
		
		loginFrame.add(nameAndPasswordField, BorderLayout.CENTER);
		JButton loginButon = new JButton("Login");
		loginButon.addActionListener(this);
		
		loginFrame.add(loginButon, BorderLayout.EAST);
		
		
		
	}
	
	public void adminGUI() {
		adminFrame = new JFrame();

		adminFrame.setVisible(true);
		adminFrame.setSize(840, 600);
		adminFrame.setLocation(200, 100);
		adminFrame.setResizable(false);
		adminFrame.setLayout(new BorderLayout());

		JPanel adminPanel = new JPanel();
		adminPanel.setLayout(new GridLayout(2, 2));

		JButton closeFrame = new JButton("Done");
		adminFrame.add(closeFrame, BorderLayout.SOUTH);
		closeFrame.addActionListener(this);

		// Display a list of Transactions

		JTextArea transactionText = new JTextArea(
				"--Transactions--" + System.getProperty("line.separator") + System.getProperty("line.separator"));
		JScrollPane transactionScroll = new JScrollPane(transactionText);
		adminPanel.add(transactionScroll);

		// Admin buttons

		JPanel adminButtons = new JPanel();
		adminButtons.setLayout(new GridLayout(3, 2));
		JButton create = new JButton("Create a new entry");
		JButton restock = new JButton("Restock Vending Machine");
		JButton delete = new JButton("Delete an entry");
		create.addActionListener(this);
		restock.addActionListener(this);
		delete.addActionListener(this);

		createAlbumInfo = new JTextArea("Use this button to create" + System.getProperty("line.separator") + "a new album in the database");
		createAlbumInfo.setEditable(false);
		restockInfo = new JTextArea("This button restocks the database."+System.getProperty("line.separator")+"Default stock: 5 CDs & 2 Vinyls.");
		restockInfo.setEditable(false);
		idToDelete = new JTextField();

		adminButtons.add(create);
		adminButtons.add(createAlbumInfo);
		adminButtons.add(restock);
		adminButtons.add(restockInfo);
		adminButtons.add(delete);
		adminButtons.add(idToDelete);

		adminPanel.add(adminButtons);

		// Album ID list for Admin window

		JTextArea albumIdList = new JTextArea();
		JScrollPane albumIdListScroll = new JScrollPane(albumIdList);

		adminPanel.add(albumIdListScroll);

		adminFrame.add(adminPanel, BorderLayout.CENTER);

		// show the transactions as a list
		DatabaseConnection connect = new DatabaseConnection();
		connect.getTransactions();
		Vector<Transaction> list = new Vector<Transaction>();
		list = connect.getTransactionList(); // passing the transaction list into this class, using a vector

		for (int count = 0; count < list.size(); count++) {
			String albumBought = new DatabaseConnection().getAlbumName(list.elementAt(count).getAlbumId());
			if (albumBought != null) {
				transactionText.append("Transaction Id #" + list.elementAt(count).getTransactionId()
						+ ": Sold the album: " + albumBought + ", on " + list.elementAt(count).getTransactionDate()
						+ System.getProperty("line.separator"));
			} else {
				transactionText
						.append("Transaction Id #" + list.elementAt(count).getTransactionId() + ": Sold an album, on "
								+ list.elementAt(count).getTransactionDate() + System.getProperty("line.separator"));
			}

		}

		// show the albums with their IDs and stock levels for admins

		DatabaseConnection adminIDConnect = new DatabaseConnection();
		adminIDConnect.getAlbums();
		Vector<Album> albumList = new Vector<Album>();
		albumList = adminIDConnect.getAlbumList(); // passing the transaction list into this class, using a vector

		for (int count = 0; count < albumList.size(); count++) {

			albumIdList.append(count + 1 + ". Album name: " + albumList.elementAt(count).getAlbumName()
					+ " - Album ID: " + albumList.elementAt(count).getAlbumId() + System.getProperty("line.separator")
					+ "Stock CD: " + albumList.elementAt(count).getStockCD() + ", Stock vinyl: "
					+ albumList.elementAt(count).getStockVinyl() + System.getProperty("line.separator"));

		}

	}

	public void createWindow() {
		createFrame = new JFrame();
		createFrame.setVisible(true);
		createFrame.setSize(400, 500);
		JButton commitCreate = new JButton("Create");
		commitCreate.addActionListener(this);

		createFrame.setLayout(new GridLayout(9, 2));

		JTextField newAlbumNameInfo = new JTextField("New album name");
		newAlbumNameInfo.setEditable(false);
		JTextField newAlbumArtistInfo = new JTextField("Artist");
		newAlbumArtistInfo.setEditable(false);
		JTextField newYearOfReleaseInfo = new JTextField("Year of Release");
		newYearOfReleaseInfo.setEditable(false);
		JTextArea newTracklistInfo = new JTextArea("Tracklist: separate each" + System.getProperty("line.separator")
				+ "song with a \\n and have no single" + System.getProperty("line.separator") + "quotes/apostrophes");
		newTracklistInfo.setEditable(false);
		JTextField newGenreInfo = new JTextField("Genre");
		newGenreInfo.setEditable(false);
		JTextField newPriceInfo = new JTextField("Price");
		newPriceInfo.setEditable(false);
		JTextField newStockCDInfo = new JTextField("Stock CD");
		newStockCDInfo.setEditable(false);
		JTextField newStockVinylInfo = new JTextField("Stock vinyl");
		newStockVinylInfo.setEditable(false);

		newAlbumName = new JTextField("");
		newAlbumArtist = new JTextField("");
		newYearOfRelease = new JTextField("");
		newTracklist = new JTextField("");
		newGenre = new JTextField("");
		newPrice = new JTextField("");
		newStockCD = new JTextField("");
		newStockVinyl = new JTextField("");

		JScrollPane newTracklistScroll = new JScrollPane(newTracklist);

		createFrame.add(newAlbumNameInfo);
		createFrame.add(newAlbumName);
		createFrame.add(newAlbumArtistInfo);
		createFrame.add(newAlbumArtist);
		createFrame.add(newYearOfReleaseInfo);
		createFrame.add(newYearOfRelease);
		createFrame.add(newTracklistInfo);
		createFrame.add(newTracklistScroll);
		createFrame.add(newGenreInfo);
		createFrame.add(newGenre);
		createFrame.add(newPriceInfo);
		createFrame.add(newPrice);
		createFrame.add(newStockCDInfo);
		createFrame.add(newStockCD);
		createFrame.add(newStockVinylInfo);
		createFrame.add(newStockVinyl);

		createFrame.add(commitCreate);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Sorting Buttons
		if (e.getActionCommand().equals("Artist")) {
			BuyButton.setSortNumber(1);
			this.dispose();
			new MusicVendingMachineGUI();
		}
		if (e.getActionCommand().equals("Album")) {
			BuyButton.setSortNumber(0);
			this.dispose();
			new MusicVendingMachineGUI();
	
		}
		if (e.getActionCommand().equals("Price")) {
			BuyButton.setSortNumber(4);
			this.dispose();
			new MusicVendingMachineGUI();
		
		}
		if (e.getActionCommand().equals("Genre")) {
			BuyButton.setSortNumber(3);
			this.dispose();
			new MusicVendingMachineGUI();
		
		}
		if (e.getActionCommand().equals("Year of Release")) {
			BuyButton.setSortNumber(2);
			this.dispose();
			new MusicVendingMachineGUI();
			
		}

		// Money buttons
		if (e.getActionCommand().equals("€1")) {
			money = money + 1;

		}
		if (e.getActionCommand().equals("€2")) {
			money = money + 2;

		}
		if (e.getActionCommand().equals("€5")) {
			money = money + 5;

		}
		if (e.getActionCommand().equals("€10")) {
			money = money + 10;

		}
		if (e.getActionCommand().equals("X")) {
			money = 0;

		}
		if (e.getActionCommand().equals("admin")) {

			adminLogin();
			//adminGUI();
		}
		if (e.getActionCommand().equals("Done")) {
			adminFrame.dispose();
			this.dispose();
			new MusicVendingMachineGUI();
		}

		// "Create a new entry"
		if (e.getActionCommand().equals("Create a new entry")) {
			createWindow();
		}
		if (e.getActionCommand().equals("Create")) {
			double newPriceValue = 15; // default values
			int newYearValue = 1990;
			int newStockCDValue = 5;
			int newStockVinylValue = 2;
			
			if(newAlbumName.getText().equals("") || newAlbumArtist.getText().equals("")|| newYearOfRelease.getText().equals("")|| newTracklist.getText().equals("")||newGenre.getText().equals("")|| newPrice.getText().equals("")|| newStockCD.getText().equals("")||	newStockVinyl.getText().equals("")) {
				
			}else {
				try { // try to get numbers from text fields
					newPriceValue = Double.parseDouble(newPrice.getText());
					newYearValue = Integer.parseInt(newYearOfRelease.getText());
					newStockCDValue = Integer.parseInt(newStockCD.getText());
					newStockVinylValue = Integer.parseInt(newStockVinyl.getText());
					// yearOfRelease,albumName,price,stockCD,stockVinyl,genre,artist,tracklist
					new DatabaseConnection().createEntry(newYearValue, newAlbumName.getText(), newPriceValue,
							newStockCDValue, newStockVinylValue, newGenre.getText(), newAlbumArtist.getText(),
							newTracklist.getText());
					
					createFrame.dispose();
					adminFrame.dispose();
					adminGUI();
				} catch (NumberFormatException ex) {
					
				}
			

			}

			

		}
		if (e.getActionCommand().equals("Restock Vending Machine")) {
			new DatabaseConnection().restock();
			adminFrame.dispose();
			adminGUI();
		}

		// Delete an entry
		if (e.getActionCommand().equals("Delete an entry")) {
			int idToDeleteInt = 0;
			try {
				idToDeleteInt = Integer.parseInt(idToDelete.getText());
			} catch (NumberFormatException ex) {

			} catch (Exception ex) {

			}
			new DatabaseConnection().deleteEntry(idToDeleteInt);
			adminFrame.dispose();
			adminGUI();
		}

		BuyButton.setMoneyInserted(money);
		money = BuyButton.getMoneyInserted();
		moneyField.setText("€" + BuyButton.getMoneyInserted());

		// reload the gui after a product has been bought

		if (e.getActionCommand().equals("Buy")) {
			BuyButton.setPreferredFormat(formatButtons.getSelection().getActionCommand());
			this.dispose();
			new MusicVendingMachineGUI();
		}
		
		
		if (e.getActionCommand().equals("Login")) {
			if (usernameField.getText().equals("admin")&&passwordField.getText().equals("pass")) {
				adminGUI();
				loginFrame.dispose();
			}else {
				WrongLogin();
			}
			
		}

	}

}
