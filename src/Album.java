
public class Album {

	private String albumName, artist, tracklist, genre;
	private int yearOfRelease, stockCD, stockVinyl, albumId;
	private double price;
	
	
	public Album(String albumName, String artist, int yearOfRelease, int stockCD, int stockVinyl, int albumId,  double price, String tracklist, String genre) {
		this.setAlbumName(albumName);
		this.setArtist(artist);
		this.setYearOfRelease(yearOfRelease);
		this.setStockCD(stockCD);
		this.setStockVinyl(stockVinyl);
		this.setAlbumId(albumId);
		this.setTracklist(tracklist);
		this.setPrice(price);
		this.setGenre(genre);
	}
/*
 * Getter and setter methods
 */
	
	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getYearOfRelease() {
		return yearOfRelease;
	}

	public void setYearOfRelease(int yearOfRelease) {
		this.yearOfRelease = yearOfRelease;
	}

	public int getStockCD() {
		return stockCD;
	}

	public void setStockCD(int stockCD) {
		this.stockCD = stockCD;
	}

	public int getStockVinyl() {
		return stockVinyl;
	}

	public void setStockVinyl(int stockVinyl) {
		this.stockVinyl = stockVinyl;
	}

	public int getAlbumId() {
		return albumId;
	}

	public void setAlbumId(int albumId) {
		this.albumId = albumId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	public String getTracklist() {
		return tracklist;
	}
	public void setTracklist(String tracklist) {
		this.tracklist = tracklist;
	}
	
	
}
