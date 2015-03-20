package noe;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JInternalFrame;


public class VentanaHistograma extends JInternalFrame  {

	private GraficoHistograma grafico;
	private Dimension dimension;
	private String nombrehistograma;
	private ArrayList<Integer> vhistograma;
	private ArrayList<Integer> vhistogramaacu;
	private int npixeles;
	private double contraste, brillo;
	private int maxcolor, mincolor;
	private MatrizRGB vrgb;



	public VentanaHistograma(MatrizRGB vrgbin, String vnombreimg, boolean acu) {

		vrgb = vrgbin;

		dimension = new Dimension(500,300);
		if (acu)
			nombrehistograma = vnombreimg+"-Histograma Acumulado";
		else
			nombrehistograma = vnombreimg+"-Histograma";

		vhistograma = new ArrayList<Integer>();
		vhistogramaacu = new ArrayList<Integer>();

		
		// Inicializar vhistograma
		for(int i=0; i<256; i++) 
			vhistograma.add(0);

		for(int i=0; i<vrgb.getWidth(); i++){ 
			for(int j=0; j<vrgb.getHeight(); j++){
				if (!((vrgb.getVrgb().get(i).get(j).getRed()==210) && // Comprobamos que el color no es 
						(vrgb.getVrgb().get(i).get(j).getGreen()==210) && // el gris de fondo para imagenes rotadas
						(vrgb.getVrgb().get(i).get(j).getBlue()==209))){
					vhistograma.set(vrgb.getVrgb().get(i).get(j).getRed(), 
							vhistograma.get(vrgb.getVrgb().get(i).get(j).getRed())+1);
				}
			}
		}

		// Inicializar vhistogramaacu
		int acum = 0; 
		for(int i=0; i<256; i++){ 
			acum = acum + vhistograma.get(i);
			vhistogramaacu.add(acum);
		}


		// Calcular npixeles
		npixeles = 0;
		for(int i=0; i<vhistograma.size(); i++)
			npixeles = npixeles + vhistograma.get(i);

		// Media y Desviación típica
		brillo = calcularmedia();
		contraste = calculardesv();

		// Color máximo y mínimo
		int imin=0;
		while (vhistograma.get(imin)==0){
			imin++;
		}
		mincolor = imin;

		int imax=255;
		while (vhistograma.get(imax)==0){
			imax--;
		}
		maxcolor = imax;

		// Elementos gráficos
		grafico = new GraficoHistograma(vhistograma, vhistogramaacu, vrgb, brillo, acu);
		add(grafico);
		pack();
		setTitle(nombrehistograma);
		setEnabled(true);
		setSize(grafico.getWidth(), grafico.getHeight());
		setMinimumSize(dimension);
		setResizable(true);
		setClosable(true);
		setVisible(true);		

	}


	// Media y desviación típica

	public double calcularmedia(){
		double med;
		double suma = 0.0;
		double np = npixeles;
		for (int i=0; i<vhistograma.size(); i++){
			suma = suma + vhistograma.get(i) * i;
		}
		med = (1.0/np) * suma;
		return med;
	}

	public double calculardesv(){
		double dt;
		double suma = 0.0;
		double np = npixeles;
		for (int i=0; i<vhistograma.size(); i++){
			suma = suma + (i-brillo)*(i-brillo) * vhistograma.get(i);
		}
		dt = Math.sqrt((1.0/np) * suma);
		return dt;
	}

	@Override public void doDefaultCloseAction() {
		setVisible(false);
	}

	public void recalcular(){
		// Normal
		for(int i=0; i<vrgb.getWidth(); i++){ 
			for(int j=0; j<vrgb.getHeight(); j++){
				vhistograma.set(vrgb.getVrgb().get(i).get(j).getRed(), 
						vhistograma.get(vrgb.getVrgb().get(i).get(j).getRed())+1);
			}
		}
		// Acumulado
		int acum = 0; 
		for(int i=0; i<256; i++){ 
			acum = acum + vhistograma.get(i);
			vhistogramaacu.add(acum);
		}

	}

	// Getters y setters

	public GraficoHistograma getGrafico() {
		return grafico;
	}


	public void setGrafico(GraficoHistograma grafico) {
		this.grafico = grafico;
	}


	public double getContraste() {
		return contraste;
	}


	public void setContraste(double contraste) {
		this.contraste = contraste;
	}


	public double getBrillo() {
		return brillo;
	}


	public void setBrillo(double brillo) {
		this.brillo = brillo;
	}


	public int getMaxcolor() {
		return maxcolor;
	}


	public void setMaxcolor(int maxcolor) {
		this.maxcolor = maxcolor;
	}


	public int getMincolor() {
		return mincolor;
	}


	public void setMincolor(int mincolor) {
		this.mincolor = mincolor;
	}


	public ArrayList<Integer> getVhistograma() {
		return vhistograma;
	}


	public void setVhistograma(ArrayList<Integer> vhistograma) {
		this.vhistograma = vhistograma;
	}


	public ArrayList<Integer> getVhistogramaacu() {
		return vhistogramaacu;
	}


	public void setVhistogramaacu(ArrayList<Integer> vhistogramaacu) {
		this.vhistogramaacu = vhistogramaacu;
	}




}
