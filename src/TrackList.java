import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

public class TrackList extends JButton implements ActionListener {
	private int albumId;
	public TrackList(int albumId) {
		this.albumId = albumId;
		addActionListener(this);
		setBorder(new EtchedBorder(1) );
	}
	
	public void actionPerformed(ActionEvent e) {
		DatabaseConnection connect = new DatabaseConnection();
		MusicVendingMachineGUI.TracklistPopupWindow(connect.getTrackList(albumId));
		
	}

}
