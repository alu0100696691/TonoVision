package noe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CoordPane extends JOptionPane {
	int npuntos;
	JTextField vcx [];
	JTextField vcy [];
	JPanel vpanel [];
	JButton aceptar;

	public CoordPane(int tramos){

		npuntos = tramos+1;
		vcx = new JTextField[npuntos];
		vcy = new JTextField[npuntos];
		vpanel = new JPanel[npuntos];
		GridLayout mylayout = new GridLayout(npuntos+1,0);
		setLayout(mylayout);
		
		for(int i=0; i<npuntos; i++){
			vcx[i] = new JTextField(5);
			vcy[i] = new JTextField(5);
			vpanel[i] = new JPanel();
			vpanel[i].add(new JLabel ("Punto " + (i+1) +"  "));
			vpanel[i].add(new JLabel ("X:"));
			vpanel[i].add(vcx[i]);
			vpanel[i].add(new JLabel ("Y:"));
			vpanel[i].add(vcy[i]);
			add(vpanel[i]);
		}
		
		
		JPanel paceptar = new JPanel();
		paceptar.add(aceptar = new JButton("Aceptar"));
		add(paceptar);
		
		setVisible(true);


	}


	public JTextField getVcx(int i) {
		return vcx[i];
	}

	public void setVcx(int i, JTextField val) {
		this.vcx[i] = val;
	}

	public JTextField getVcy(int i) {
		return vcy[i];
	}

	public void setVcy(int i, JTextField val) {
		this.vcy[i] = val;
	}

	public JPanel getVpanel(int i) {
		return vpanel[i];
	}


	public JButton getAceptar() {
		return aceptar;
	}


	public void setAceptar(JButton aceptar) {
		this.aceptar = aceptar;
	}


	public JPanel[] getVpanel() {
		return vpanel;
	}


	public void setVpanel(JPanel[] vpanel) {
		this.vpanel = vpanel;
	}
	
	
	





}
