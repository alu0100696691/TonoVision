package noe;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ZoomPane {
	JTextField zh = new JTextField(5);
	JTextField zv = new JTextField(5);
	JPanel myPanel;

	public ZoomPane(){
		myPanel = new JPanel();
		myPanel.add(new JLabel("Zoom Horizontal:"));
		myPanel.add(zh);
		myPanel.add(Box.createHorizontalStrut(15)); 
		myPanel.add(new JLabel("Zoom Vertical:"));
		myPanel.add(zv);
	}

	public JTextField getZh() {
		return zh;
	}

	public void setZh(JTextField zh) {
		this.zh = zh;
	}

	public JTextField getZv() {
		return zv;
	}

	public void setZv(JTextField zv) {
		this.zv = zv;
	}

	public JPanel getMyPanel() {
		return myPanel;
	}

	public void setMyPanel(JPanel myPanel) {
		this.myPanel = myPanel;
	}



}
