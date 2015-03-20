package noe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;

public class VentanaImg extends JInternalFrame implements MouseListener, MouseMotionListener{

	private MatrizRGB vrgb;
	private MatrizRGB vrgb_ori;
	private BufferedImage vimg;
	private String vnombreimg;
	private ImagePane imgpanel;
	private VentanaHistograma histograma;
	private VentanaHistograma histogramaacu;
	private VentanaCross cross;
	private VentanaDerivada derivada;
	private JLabel etiqueta;
	private double contraste, brillo;
	private int vx, vy;
	private int x1, y1, x2, y2; // Coordenadas del recorte

	public VentanaImg (MatrizRGB vrgbin, BufferedImage img, String nombreimg, int cont_vent){

		vimg = img;
		vnombreimg = nombreimg;
		imgpanel = new ImagePane(img);
		vrgb = new MatrizRGB(vimg);
		vrgb_ori = vrgbin;
		x1 = 1;
		y1 = 1;
		x2 = 0;
		y2 = 0;
		histograma = new VentanaHistograma(vrgb, vnombreimg, false);
		histogramaacu = new VentanaHistograma(vrgb, vnombreimg, true);
		cross = new VentanaCross(vrgb, vnombreimg, x1, y2, x2, y2);
		derivada = new VentanaDerivada(vrgb, vnombreimg, x1, y2, x2, y2);
		contraste = histograma.getContraste();
		brillo = histograma.getBrillo();

		etiqueta = new JLabel("x: " + "   " + "y: " + "R: "  + "G: " + "B: ");
		etiqueta.setBorder(new BevelBorder(BevelBorder.RAISED));
		add(etiqueta, BorderLayout.SOUTH);
		addMouseListener(this);
		addMouseMotionListener(this);

		add(imgpanel);
		pack();
		setTitle(nombreimg);
		setLocation(cont_vent, cont_vent);	
		setSize(vimg.getWidth()+10, vimg.getHeight()+etiqueta.getHeight()+52);
		setResizable(false);
		setClosable(true);
		setVisible(true);
		addMouseMotionListener(new MouseMotionAdapter(){
			public void mouseMoved(MouseEvent e){
				vx = e.getX()-5;
				vy = e.getY()-30;
				if ((vx>0) && (vx<vimg.getWidth()) && (vy>0) && (vy<vimg.getHeight())){
					etiqueta.setText("x: " + vx + "   " + "y: " + vy + "      "
							+ "R: " + vrgb.getVrgb().get(vx).get(vy).getRed() + "   " 
							+ "G: " + vrgb.getVrgb().get(vx).get(vy).getGreen() + "   "
							+ "B: " + vrgb.getVrgb().get(vx).get(vy).getBlue() + "      "
							+ vimg.getWidth() + "x" + vimg.getHeight());

				}
			}

		});


	}



	// Calculo de la entropia		

	public double entropia(){

		double npixeles = vimg.getHeight() * vimg.getWidth();
		double entropia = 0.0;
		ArrayList<Double> probabilidad = new ArrayList<Double>();


		for (int i=0; i<histograma.getGrafico().getVhistograma().size()-1; i++){				
			probabilidad.add(histograma.getGrafico().getVhistograma().get(i)/npixeles);
			if(probabilidad.get(i)!=0)
				entropia = entropia + probabilidad.get(i) * ((Math.log(probabilidad.get(i))/Math.log(2.0)));
		}	

		entropia = -entropia;
		return entropia;
	}

	// Metodos de raton

	@Override
	public void mousePressed(MouseEvent evt){
		repaint();
		x1 = evt.getX()-5;
		y1 = evt.getY()-30;
		
		if (x1 >= vimg.getWidth())
			x1 = vimg.getWidth()-1;
		if (y1 >= vimg.getHeight())
			y1 = vimg.getHeight()-1;
		
		if (y2==y1)
			y1 = y1 +1;
	}

	@Override
	public void mouseDragged(MouseEvent evt){
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent evt){

		x2 = evt.getX()-5;
		y2 = evt.getY()-30;

		if (x2 >= vimg.getWidth())
			x2 = vimg.getWidth()-1;
		if (y2 >= vimg.getHeight())
			y2 = vimg.getHeight()-1;

		Graphics2D g = (Graphics2D) imgpanel.getGraphics();

		g.setColor(Ventana.coloractual);

		if (Ventana.linea == false){
			g.drawRect(Math.min(x1, x2), Math.min(y1,  y2), Math.max(x1, x2) - 
					Math.min(x1, x2), 
					Math.max(y1, y2) - 
					Math.min(y1, y2));
		}
		else{
			g.drawLine(x1, y1, x2, y2);
			cross.getGrafico().setX1(x1);
			cross.getGrafico().setY1(y1);
			cross.getGrafico().setX2(x2);
			cross.getGrafico().setY2(y2);
			cross.getGrafico().setPendiente(x1, y1, x2, y2);
			cross.getGrafico().setN_puntos(x1, y1, x2, y2);
			
			derivada.getGrafico().setX1(x1);
			derivada.getGrafico().setY1(y1);
			derivada.getGrafico().setX2(x2);
			derivada.getGrafico().setY2(y2);
			derivada.getGrafico().setPendiente(x1, y1, x2, y2);
			derivada.getGrafico().setN_puntos(x1, y1, x2, y2);
			
		}
		

	}

	@Override
	public void mouseExited(MouseEvent evt){
	}

	@Override
	public void mouseClicked(MouseEvent evt){
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent evt){
	}

	@Override
	public void mouseEntered(MouseEvent evt){
	}
		

	// Setters y getters

	public String getVnombreimg() {
		return vnombreimg;
	}

	public void setVnombreimg(String vnombreimg) {
		this.vnombreimg = vnombreimg;
	}

	public MatrizRGB getVrgb() {
		return vrgb;
	}

	public void setVrgb(MatrizRGB vrgb) {
		this.vrgb = vrgb;
	}

	public VentanaHistograma getHistograma() {
		return histograma;
	}

	public void setHistograma(VentanaHistograma histograma) {
		this.histograma = histograma;
	}

	public VentanaHistograma getHistogramaacu() {
		return histogramaacu;
	}

	public void setHistogramaacu(VentanaHistograma histogramaacu) {
		this.histogramaacu = histogramaacu;
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


	public BufferedImage getVimg() {
		return vimg;
	}

	public void setVimg(BufferedImage vimg) {
		this.vimg = vimg;
	}

	public int getX1() {
		return x1;
	}

	public void setX1(int x1) {
		this.x1 = x1;
	}

	public int getY1() {
		return y1;
	}

	public void setY1(int y1) {
		this.y1 = y1;
	}

	public int getX2() {
		return x2;
	}

	public void setX2(int x2) {
		this.x2 = x2;
	}

	public int getY2() {
		return y2;
	}

	public void setY2(int y2) {
		this.y2 = y2;
	}


	public VentanaCross getCross() {
		return cross;
	}

	public void setCross(VentanaCross cross) {
		this.cross = cross;
	}


	public JLabel getEtiqueta() {
		return etiqueta;
	}


	public void setEtiqueta(JLabel etiqueta) {
		this.etiqueta = etiqueta;
	}



	public VentanaDerivada getDerivada() {
		return derivada;
	}


	public void setDerivada(VentanaDerivada derivada) {
		this.derivada = derivada;
	}
	
	
	



	
	
	





}