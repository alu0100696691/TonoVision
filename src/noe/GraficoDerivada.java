package noe;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GraficoDerivada extends JPanel {

	public final static int SEPARACION = 100;		/* Separacion del eje de coordenadas respecto al frame */
	public final static int DISTLINEAS = 40;		/* Separacion entre las lineas de los ejes de coordenadas */
	public final static int NUM_HORIZONTAL_POSX = 30;      /* Posicion X del eje de coordenadas horizontal */
	public final static int NUM_HORIZONTAL_POSY = 15;      /* Posicion Y del eje de coordenadas horizontal */
	public final static int NUM_VERTICAL_POSX = 5;         /* Posicion X del eje de coordenadas vertical */
	public final static int NUM_VERTICAL_POSY = 35;        /* Posicion Y del eje de coordenadas vertical */

	private int x1, y1, x2, y2;
	private double pendiente;
	private int n_puntos;
	private MatrizRGB vrgb;


	public GraficoDerivada(MatrizRGB vrgbin, int x1in, int y1in, int x2in, int y2in){
		setSize(500,300);
		vrgb = vrgbin;
		x1 = x1in;
		y1 = y1in;
		x2 = x2in;
		y2 = y2in;
		pendiente = ((double)y2-(double)y1)/((double)x2-(double)x1);
		if(pendiente>-1 && pendiente<1)
			n_puntos = Math.abs(x2-x1);
		else
			n_puntos = Math.abs(y2-y1);
		if (n_puntos == 0)
			n_puntos = 1;
	}


	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(getForeground());

		int ejevertical = getHeight()-SEPARACION-30+40;
		int ejehorizontal = getWidth()-30-SEPARACION;
		Graphics2D g2d = (Graphics2D) g;

		// Variables para las lineas peque�as del eje horizontal	
		int xh = ejehorizontal/n_puntos;
		int zh = 0;	
		float yh = ejehorizontal/(float)n_puntos;	
		float difh = yh-xh;
		float errorh = 0;
		int cont=0;
		int n=0;
		int pos;


		// Variables para las lineas peque�as del eje vertical
		int xv = ejevertical/255;
		int zv = 0;
		float yv = ejevertical/255f;
		float difv = yv-xv;
		float errorv = 0;
		int tam_ejev = getHeight()-SEPARACION+40-30;

		g.setColor(Color.BLACK);
		g.drawLine(SEPARACION, getHeight()-SEPARACION+40-tam_ejev/2, getWidth()-30, getHeight()-SEPARACION+40-tam_ejev/2); // Linea horizontal del eje
		g.drawLine(SEPARACION, 30, SEPARACION, getHeight()-SEPARACION+40); // Linea vertical del eje
		g.drawString("-255", 70, getHeight()-57); // 0 del eje vertical
		g.drawString("255", 70, 37); // maximo del eje vertical


		g.drawString("(" + x1 + ", " + y1 + ")", 97, getHeight()-40); // punto inicial del eje horizontal
		g.drawString("Derivada", 24, getHeight()/2-12); // Frase "Derivada"
		g.drawString("Pixeles", getWidth()/2+10, getHeight()-25); // Frase "Pixeles"
		g.drawString("(" + x2 + ", " + y2 + ")", getWidth()-70, getHeight()-40); // punto final del eje horizontal


		// Lineas peque�as:

		g.drawLine(SEPARACION-2, 30, SEPARACION, 30); // Maximo del eje vertical
		g.drawLine(SEPARACION-2, getHeight()-SEPARACION+40, SEPARACION, getHeight()-SEPARACION+40); // Minimo del eje vertical
		g.drawLine(getWidth()-30, getHeight()-SEPARACION+40-tam_ejev/2, getWidth()-30, getHeight()-SEPARACION+2+40-tam_ejev/2); // Maximo del eje horizontal

		for(int i=1; i<256; i++){
			zv = zv+(int)(yv+errorv);
			errorv = errorv + difv;
			if (errorv>1)
				errorv = errorv-1;
			if ((cont%5) == 0){
				g.drawLine(SEPARACION-2, 30+zv, SEPARACION+2, 30+zv); // Eje vertical
			}
			cont++;
		}



		// Dibujar las barras del histograma

		int xp1, yp1, xp2, yp2, x_pre, y_pre;
		x_pre = 0;
		y_pre = 0;


		for(int i=0; i<n_puntos; i++){

			if(pendiente>-1 && pendiente<1){

				if (x2<x1)
					xp1 = x1 - i;
				else
					xp1 = x1 + i;

				if(x2==x1)
					x1++;

				yp1 = (y2 - y1) * (xp1 - x1) / (x2 - x1) + y1; 
			}

			else {

				if (y2<y1)
					yp1 = y1 - i;
				else
					yp1 = y1 + i;

				if(y2==y1)
					y1++;

				xp1 = (x2 - x1) * (yp1 - y1) / (y2 - y1) + x1; 

			}

			if(pendiente>-1 && pendiente<1){

				if (x2<x1)
					xp2 = x1 - i - 1;
				else
					xp2 = x1 + i + 1;

				if(x2==x1)
					x1++;

				yp2 = (y2 - y1) * (xp2 - x1) / (x2 - x1) + y1; 
			}

			else {

				if (y2<y1)
					yp2 = y1 - i - 1;
				else
					yp2 = y1 + i + 1;

				if(y2==y1)
					y1++;

				xp2 = (x2 - x1) * (yp2 - y1) / (y2 - y1) + x1; 

			}

			g.setColor(Color.BLACK);
			zh = zh+(int)(yh+errorh);
			errorh = errorh + difh;
			if (errorh>1)
				errorh = errorh-1;


			n = vrgb.getVrgb().get(xp2).get(yp2).getRed() - vrgb.getVrgb().get(xp1).get(yp1).getRed();
			if (n>255 || n<-255)
				System.out.println(n);
			pos =(n*(getHeight()-SEPARACION)/2)/255;


			if (i!=0){

				g.setColor(Color.GREEN);
				g2d.setStroke(new BasicStroke(2.5f));

				if (SEPARACION+zh < getWidth()-30)
					g.drawLine(x_pre, y_pre-tam_ejev/2, SEPARACION+zh, getHeight()-SEPARACION-pos+40-tam_ejev/2);


				else
					g.drawLine(x_pre, y_pre-tam_ejev/2, getWidth()-30, getHeight()-SEPARACION-pos+40-tam_ejev/2);


			}

			// Guardamos las coordenadas de la altura del pixel

			if (i==0)
				x_pre = SEPARACION;
			else
				x_pre = SEPARACION+zh;
			y_pre = getHeight()-SEPARACION-pos+40;
			g2d.setStroke(new BasicStroke(1.0f));
		}
	}




	// Setters y getters

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


	public double getPendiente() {
		return pendiente;
	}


	public void setPendiente(int pendiente) {
		this.pendiente = pendiente;
	}

	public void setPendiente(int x1, int y1, int x2, int y2) {
		pendiente = ((double)y2-(double)y1)/((double)x2-(double)x1);
	}


	public int getN_puntos() {
		return n_puntos;
	}


	public void setN_puntos(int n_puntos) {
		this.n_puntos = n_puntos;
	}


	public void setN_puntos(int x1, int y1, int x2, int y2) {
		if(pendiente>-1 && pendiente<1)
			n_puntos = Math.abs(x2-x1);
		else
			n_puntos = Math.abs(y2-y1);
		if (n_puntos == 0)
			n_puntos = 1;
	}





}




