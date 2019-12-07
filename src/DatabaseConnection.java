import java.util.Vector;
import java.sql.*;

/**
 * Class for performing database transactions.
 * 
 * @author Kevin and Jakub
 *
 */
public class DatabaseConnection {
	private Connection myConnection;
	private Statement myStatement;
	ResultSet result;
	ResultSet resultTransactions;
	int updateResult;

	private String albumName, artist, tracklist, genre;
	private int yearOfRelease, stockCD, stockVinyl, albumId, transactionId;
	private double price;
	private Date transactionDate;

	private Vector<Album> albumList = new Vector<Album>();
	private Vector<Transaction> transactionList = new Vector<Transaction>();

	public Vector<Album> getAlbumList() {
		return albumList;
	}

	public void setAlbumList(Vector<Album> albumList) {
		this.albumList = albumList;
	}

	public Vector<Transaction> getTransactionList() {
		return transactionList;
	}

	public void setTransactionList(Vector<Transaction> transactionList) {
		this.transactionList = transactionList;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	

	public DatabaseConnection() {
		try {
			/*
			 * Assumes the name of the database is 'musicvendingmachine' while the username
			 * is 'root' and the password is 'pass'
			 */
			myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/musicvendingmachine", "root",
					"pass");

			myStatement = myConnection.createStatement();

			result = myStatement.executeQuery("select * from album");

			resultTransactions = myStatement.executeQuery("select * from transactiontable");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Display and Sorting methods
	
	public void getAlbums() {	//sort by album
		try {
			String sqlQuery = "select * from album order by albumName";
			result = myStatement.executeQuery(sqlQuery);

			while (result.next()) { // testing the connection to the database
				albumName = result.getString("albumName");
				artist = result.getString("artist");
				genre = result.getString("genre");
				yearOfRelease = result.getInt("yearOfRelease");
				price = result.getDouble("price");
				stockCD = result.getInt("stockCD");
				stockVinyl = result.getInt("stockVinyl");
				albumId = result.getInt("albumId");
				tracklist = result.getString("tracklist");
				albumList();

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				result.close();
				myStatement.close();
				myConnection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for (int count = 0; count < albumList.size(); count++) {
			Album album = albumList.elementAt(count);

			System.out.println(album.getAlbumId() + " " + album.getStockCD() + " " + album.getStockVinyl() + " "
					+ album.getYearOfRelease() + "\n\n" + album.getAlbumName() + ", By " + album.getArtist()
					+ ". Price: " + album.getPrice() + "\n\n--Tracklist--\n\n" + album.getTracklist());

		}
	}


	public void sortByArtist() {
		try {
			String sqlQuery = "select * from album order by artist";
			result = myStatement.executeQuery(sqlQuery);

			while (result.next()) { // testing the connection to the database
				albumName = result.getString("albumName");
				artist = result.getString("artist");
				genre = result.getString("genre");
				yearOfRelease = result.getInt("yearOfRelease");
				price = result.getDouble("price");
				stockCD = result.getInt("stockCD");
				stockVinyl = result.getInt("stockVinyl");
				albumId = result.getInt("albumId");
				tracklist = result.getString("tracklist");
				albumList();

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				result.close();
				myStatement.close();
				myConnection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for (int count = 0; count < albumList.size(); count++) {
			Album album = albumList.elementAt(count);

			System.out.println(album.getAlbumId() + " " + album.getStockCD() + " " + album.getStockVinyl() + " "
					+ album.getYearOfRelease() + "\n\n" + album.getAlbumName() + ", By " + album.getArtist()
					+ ". Price: " + album.getPrice() + "\n\n--Tracklist--\n\n" + album.getTracklist());

		}
	}
	
	public void sortByPrice() {
		try {
			String sqlQuery = "select * from album order by price";
			result = myStatement.executeQuery(sqlQuery);
	
			while (result.next()) { // testing the connection to the database
				albumName = result.getString("albumName");
				artist = result.getString("artist");
				genre = result.getString("genre");
				yearOfRelease = result.getInt("yearOfRelease");
				price = result.getDouble("price");
				stockCD = result.getInt("stockCD");
				stockVinyl = result.getInt("stockVinyl");
				albumId = result.getInt("albumId");
				tracklist = result.getString("tracklist");
				albumList();

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				result.close();
				myStatement.close();
				myConnection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for (int count = 0; count < albumList.size(); count++) {
			Album album = albumList.elementAt(count);

			System.out.println(album.getAlbumId() + " " + album.getStockCD() + " " + album.getStockVinyl() + " "
					+ album.getYearOfRelease() + "\n\n" + album.getAlbumName() + ", By " + album.getArtist()
					+ ". Price: " + album.getPrice() + "\n\n--Tracklist--\n\n" + album.getTracklist());

		}
	}
	
	public void sortByYear() {
		try {
			String sqlQuery = "select * from album order by yearOfRelease";
			result = myStatement.executeQuery(sqlQuery);
	
			while (result.next()) { // testing the connection to the database
				albumName = result.getString("albumName");
				artist = result.getString("artist");
				genre = result.getString("genre");
				yearOfRelease = result.getInt("yearOfRelease");
				price = result.getDouble("price");
				stockCD = result.getInt("stockCD");
				stockVinyl = result.getInt("stockVinyl");
				albumId = result.getInt("albumId");
				tracklist = result.getString("tracklist");
				albumList();

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				result.close();
				myStatement.close();
				myConnection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for (int count = 0; count < albumList.size(); count++) {
			Album album = albumList.elementAt(count);

			System.out.println(album.getAlbumId() + " " + album.getStockCD() + " " + album.getStockVinyl() + " "
					+ album.getYearOfRelease() + "\n\n" + album.getAlbumName() + ", By " + album.getArtist()
					+ ". Price: " + album.getPrice() + "\n\n--Tracklist--\n\n" + album.getTracklist());

		}
	}
	
	
	public void sortByGenre() {
		try {
			String sqlQuery = "select * from album order by genre";
			result = myStatement.executeQuery(sqlQuery);

			while (result.next()) { // testing the connection to the database
				albumName = result.getString("albumName");
				artist = result.getString("artist");
				genre = result.getString("genre");
				yearOfRelease = result.getInt("yearOfRelease");
				price = result.getDouble("price");
				stockCD = result.getInt("stockCD");
				stockVinyl = result.getInt("stockVinyl");
				albumId = result.getInt("albumId");
				tracklist = result.getString("tracklist");
				albumList();

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				result.close();
				myStatement.close();
				myConnection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for (int count = 0; count < albumList.size(); count++) {
			Album album = albumList.elementAt(count);

			System.out.println(album.getAlbumId() + " " + album.getStockCD() + " " + album.getStockVinyl() + " "
					+ album.getYearOfRelease() + "\n\n" + album.getAlbumName() + ", By " + album.getArtist()
					+ ". Price: " + album.getPrice() + "\n\n--Tracklist--\n\n" + album.getTracklist());

		}
	}
	
	///////////////////////////////////
	/// display transactions ///
	///////////////////////////////////

	public void getTransactions() {
		System.out.println("test transactionbutton");
		try {
			String sqlQuery = "select * from TransactionTable";
			result = myStatement.executeQuery(sqlQuery);

			while (result.next()) { // testing the connection to the database
				transactionId = result.getInt("transactionId");
				albumId = result.getInt("albumId");
				transactionDate = result.getDate("transactionDate");

				transactionList();

			}
			for (int count = 0; count < transactionList.size(); count++) {
				Transaction transaction = transactionList.elementAt(count);

				System.out.println(transaction.getAlbumId() + " " + transaction.getAlbumName());

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				result.close();
				myStatement.close();
				myConnection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

	}

	public void newTransaction(int id) {

		try {
			String sqlQuery = "insert into TransactionTable (albumId) values" + "(" + id + ")";
			updateResult = myStatement.executeUpdate(sqlQuery);

		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	public Vector<Album> albumList() {
		Album album = new Album(albumName, artist, yearOfRelease, stockCD, stockVinyl, albumId, price, tracklist, genre);
		albumList.addElement(album);
		return albumList;
	}

	public Vector<Transaction> transactionList() {
		Transaction transaction = new Transaction(transactionId, albumId, transactionDate);
		transactionList.addElement(transaction);
		return transactionList;
	}

	
	public String getAlbumName(int id) {

		try {
			String sqlQuery = "select * from album where albumId = " + id;
			result = myStatement.executeQuery(sqlQuery);
			System.out.println("TESTING DATABASE");
			while (result.next()) { // testing the connection to the database

				albumName = result.getString("albumName");

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				result.close();
				myStatement.close();
				myConnection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return albumName;

	}

	public String getTrackList(int id) {

		try {
			String sqlQuery = "select * from album where albumId = " + id;
			result = myStatement.executeQuery(sqlQuery);
	
			while (result.next()) { 

				tracklist = result.getString("tracklist");

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				result.close();
				myStatement.close();
				myConnection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return tracklist;
	}
	public int getStockCD(int id) {
		try {
			String sqlQuery = "select * from album where albumId = " + id;
			result = myStatement.executeQuery(sqlQuery);

			while (result.next()) { 
				stockCD = result.getInt("stockCD");
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				result.close();
				myStatement.close();
				myConnection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return stockCD;
		
	}

	public void setStockCD(int stockCD) {
		this.stockCD = stockCD;
	}

	public int getStockVinyl(int id) {
		try {
			String sqlQuery = "select * from album where albumId = " + id;
			result = myStatement.executeQuery(sqlQuery);
	
			while (result.next()) { 

				stockVinyl = result.getInt("stockVinyl");

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				result.close();
				myStatement.close();
				myConnection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return stockVinyl;
	}

	public void setStockVinyl(int stockVinyl) {
		this.stockVinyl = stockVinyl;
	}

	
	
	// Create

	public void createEntry(int yearOfRelease, String albumName, double price, int stockCD, int stockVinyl,
			String genre, String artist, String tracklist) {
		try {
			String sqlQuery = "insert into Album (yearOfRelease,albumName,price,stockCD,stockVinyl,genre,artist,tracklist) "
					+ "values ( " + yearOfRelease + ",'" +albumName +"'," + price + "," + stockCD + "," + stockVinyl + ",'" + genre + "'," + "'" +artist
					+"'," +"'" + tracklist + "')";
			updateResult = myStatement.executeUpdate(sqlQuery);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				myStatement.close();
				myConnection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	// Update

	public void restock() {
		try {
			String sqlQuery = "update album set stockCd = 5, stockVinyl = 2";
			updateResult = myStatement.executeUpdate(sqlQuery);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				myStatement.close();
				myConnection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	
	public boolean checkStock(int id, String preferredFormat) {
		boolean inStock = false;
		int anyStock = 0; 
		try {
			String sqlQuery = "select * from album where albumId = " + id;
			result = myStatement.executeQuery(sqlQuery);

			while (result.next()) { // testing the connection to the database

				anyStock = result.getInt(preferredFormat);

			}
			if (anyStock >0) {
				inStock = true;
			}else {
				inStock = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				result.close();
				myStatement.close();
				myConnection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return inStock;
	}
	
	/**
	 * Takes the id, and the format from the BuyButton class, and performs an update
	 * on the database, based on these values.
	 * 
	 * @param id
	 *            - The id taken from the BuyButton class, specifying the product.
	 * @param formatStock
	 *            - The String specifying the format's stock to be decremented.
	 */
	public void decrementStock(int id, String formatStock) {
		try {
			String sqlQuery = "update album set " + formatStock + " = " + formatStock + " - 1 where albumId = " + id;
			updateResult = myStatement.executeUpdate(sqlQuery);
			System.out.println("Stock should have been decremented");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				myStatement.close();
				myConnection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	
	
	// Delete

	public void deleteEntry(int id) {
		try {
			String sqlQuery = "delete from album where albumId = " + id;
			updateResult = myStatement.executeUpdate(sqlQuery);

		} catch (Exception e) {
			e.printStackTrace();
		}try {
			myStatement.close();
			myConnection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	

}
