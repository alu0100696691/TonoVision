package noe;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BriConPane {

	JTextField brillo = new JTextField(5);
	JTextField contraste = new JTextField(5);
	JPanel myPanel;

	public BriConPane(){
		myPanel = new JPanel();
		myPanel.add(new JLabel("Brillo:"));
		myPanel.add(brillo);
		myPanel.add(Box.createHorizontalStrut(15)); 
		myPanel.add(new JLabel("Contraste:"));
		myPanel.add(contraste);
	}

	public JTextField getBrillo() {
		return brillo;
	}

	public void setBrillo(JTextField brillo) {
		this.brillo = brillo;
	}

	public JTextField getContraste() {
		return contraste;
	}

	public void setContraste(JTextField contraste) {
		this.contraste = contraste;
	}

	public JPanel getMyPanel() {
		return myPanel;
	}

	public void setMyPanel(JPanel myPanel) {
		this.myPanel = myPanel;
	}

	
	
}
