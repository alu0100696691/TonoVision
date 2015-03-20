package noe;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JInternalFrame;

public class VentanaDerivada extends JInternalFrame {

	private GraficoDerivada grafico;
	private Dimension dimension;
	private String nombrecross;


	public VentanaDerivada(MatrizRGB vrgbin, String vnombreimg, int x1, int y1, int x2, int y2) {

		dimension = new Dimension(500,300);
		
		nombrecross = vnombreimg+"-Derivada";


		// Elementos gráficos
		grafico = new GraficoDerivada(vrgbin, x1, y1, x2, y2);
		add(grafico);
		pack();
		setTitle(nombrecross);
		setEnabled(true);
		setSize(grafico.getWidth(), grafico.getHeight());
		setMinimumSize(dimension);
		setResizable(true);
		setClosable(true);
		setVisible(true);		

	}

	@Override public void doDefaultCloseAction() {
		setVisible(false);
	}

	public GraficoDerivada getGrafico() {
		return grafico;
	}

	public void setGrafico(GraficoDerivada grafico) {
		this.grafico = grafico;
	}
	
	



}
