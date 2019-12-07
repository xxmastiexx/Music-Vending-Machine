import java.sql.Date;

public class Transaction {

	private int transactionId;
	private int albumId;
	private Date transactionDate;
	
	private String albumName;
	
	
	public Transaction(int transactionId,int albumId,Date transactionDate) {

		this.albumId=albumId;
		this.transactionId=transactionId;
		this.transactionDate=transactionDate;
		
	}
	
	
	
	

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public int getAlbumId() {
		return albumId;
	}

	public void setAlbumId(int albumId) {
		this.albumId = albumId;
	}
	
	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}
////////////////////////////////////////////////////////////////////////
	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	////////////////////////////////////////////////////////////////////////
	

	

}
