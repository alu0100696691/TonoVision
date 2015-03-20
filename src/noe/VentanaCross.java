package noe;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JInternalFrame;

public class VentanaCross extends JInternalFrame {

	private GraficoCross grafico;
	private Dimension dimension;
	private String nombrecross;


	public VentanaCross(MatrizRGB vrgbin, String vnombreimg, int x1, int y1, int x2, int y2) {

		dimension = new Dimension(500,300);
		
		nombrecross = vnombreimg+"-CrossSection";


		// Elementos gráficos
		grafico = new GraficoCross(vrgbin, x1, y1, x2, y2);
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

	public GraficoCross getGrafico() {
		return grafico;
	}

	public void setGrafico(GraficoCross grafico) {
		this.grafico = grafico;
	}
	
	



}
