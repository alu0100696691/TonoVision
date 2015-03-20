package noe;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class GraficoHistograma extends JPanel {

	public final static int SEPARACION = 100;		/* Separacion del eje de coordenadas respecto al frame */
	public final static int DISTLINEAS = 40;		/* Separacion entre las lineas de los ejes de coordenadas */
	public final static int NUM_HORIZONTAL_POSX = 30;      /* Posicion X del eje de coordenadas horizontal */
	public final static int NUM_HORIZONTAL_POSY = 15;      /* Posicion Y del eje de coordenadas horizontal */
	public final static int NUM_VERTICAL_POSX = 5;         /* Posicion X del eje de coordenadas vertical */
	public final static int NUM_VERTICAL_POSY = 35;        /* Posicion Y del eje de coordenadas vertical */
	private boolean acumulado;
	private ArrayList<Integer> vhistograma;
	private ArrayList<Integer> vhistogramaacu;
	private double media = 0;

	public GraficoHistograma(ArrayList<Integer> vhistogramain, ArrayList<Integer> vhistogramaacuin, MatrizRGB vrgb, double mediain, boolean acu){
		setSize(500,300);
		acumulado = acu;
		vhistograma = vhistogramain;
		vhistogramaacu = vhistogramaacuin;
		media = mediain;

	}

	public int MaximoHistograma(){
		int max=0;

		if (acumulado)
			max=vhistogramaacu.get(vhistogramaacu.size()-1);

		else{
			for(int i=0; i<vhistograma.size(); i++)
				if (vhistograma.get(i)>max)
					max=vhistograma.get(i);
		}
		return max;	
	}

	public void MostrarValoresHistograma(){

		for(int i=0; i<vhistogramaacu.size(); i++)
			System.out.println(i+": "+vhistogramaacu.get(i));
	}

	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(getForeground());
        
		int ejevertical = getHeight()-SEPARACION-30+40;
		int ejehorizontal = getWidth()-30-SEPARACION;

		// Variables para las lineas pequeñas del eje horizontal	
		int xh = ejehorizontal/255;
		int zh = 0;	
		float yh = ejehorizontal/255f;	
		float difh = yh-xh;
		float errorh = 0;
		int cont=0;
		int n=0;
		int pos;

		// Variables para la linea de la media

		Graphics2D g2d = (Graphics2D) g;

		// Variables para las lineas pequeñas del eje vertical
		int xv = ejevertical/7;
		int zv = 0;
		float yv = ejevertical/7f;
		float difv = yv-xv;
		float errorv = 0;

		String max = Integer.toString(MaximoHistograma());
		g.setColor(Color.BLACK);
		g.drawLine(SEPARACION, getHeight()-SEPARACION+40, getWidth()-30, getHeight()-SEPARACION+40 ); // Linea horizontal del eje
		g.drawLine(SEPARACION, 30, SEPARACION, getHeight()-SEPARACION+40); // Linea vertical del eje
		g.drawString("0", 80, getHeight()-57); // 0 del eje vertical
		if (acumulado)
			g.drawString(max, 43, 37); // maximo del eje vertical
		else
			g.drawString(max, 43, 37); // maximo del eje vertical


		g.drawString("0", 97, getHeight()-40); // 0 del eje horizontal
		g.drawString("Numero de", 20, getHeight()/2-15); // Frase "Numero de"
		g.drawString("    pixeles", 20, getHeight()/2); // Frase "pixeles"
		g.drawString("Tonos de grises", getWidth()/2-30, getHeight()-25); // Frase "Tonos de grises"
		g.drawString("255", getWidth()-40, getHeight()-40); // 255 del eje horizontal


		// Lineas pequeñas:

		g.drawLine(SEPARACION-2, 30, SEPARACION, 30); // Maximo del eje vertical
		g.drawLine(getWidth()-30, getHeight()-SEPARACION+40, getWidth()-30, getHeight()-SEPARACION+2+40); // Maximo del eje horizontal

		for(int i=1; i<7; i++){
			zv = zv+(int)(yv+errorv);
			errorv = errorv + difv;
			if (errorv>1)
				errorv = errorv-1;
			g.drawLine(SEPARACION-2, 30+zv, SEPARACION+2, 30+zv); // Eje vertical
		}



		// Dibujar las barras del histograma

		for(int i=1; i<256; i++){
			g.setColor(Color.BLACK);
			zh = zh+(int)(yh+errorh);
			errorh = errorh + difh;
			if (errorh>1)
				errorh = errorh-1;
			if ((cont%5) == 0){
				g.drawLine(SEPARACION+zh, getHeight()-SEPARACION+2+40, SEPARACION+zh, getHeight()-SEPARACION+40); // Eje horizontal
			}
			cont++;

			// Creamos la variable randomcolor que contiene un color aleatorio

			Random rand = new Random();
			float re = rand.nextFloat();
			float gr = rand.nextFloat();
			float bl = rand.nextFloat();
			Color randomcolor = new Color(re,gr,bl);

			if (acumulado){
				n=vhistogramaacu.get(i-1);
				pos =(n*(getHeight()-SEPARACION))/MaximoHistograma();	
				if (n != 0){
					g.setColor(randomcolor);
					if (pos > ejevertical){
						if (SEPARACION+zh < getWidth()-30)
							g.drawLine(SEPARACION+zh, getHeight()-SEPARACION-1+40, SEPARACION+zh, getHeight()-SEPARACION-ejevertical+40);
						else
							g.drawLine(getWidth()-30, getHeight()-SEPARACION-1+40, getWidth()-30, getHeight()-SEPARACION-ejevertical+40);
					}

					else{
						if (SEPARACION+zh < getWidth()-30)
							g.drawLine(SEPARACION+zh, getHeight()-SEPARACION-1+40, SEPARACION+zh, getHeight()-SEPARACION-pos+40);
						else
							g.drawLine(getWidth()-30, getHeight()-SEPARACION-1+40, getWidth()-30, getHeight()-SEPARACION-pos+40);
					}

				}

			}


			else{
				n = vhistograma.get(i-1);
				pos =(n*(getHeight()-SEPARACION))/MaximoHistograma();
				if (n != 0){
					g.setColor(randomcolor);
					if (pos > ejevertical){
						if (SEPARACION+zh < getWidth()-30)
							g.drawLine(SEPARACION+zh, getHeight()-SEPARACION-1+40, SEPARACION+zh, getHeight()-SEPARACION-ejevertical+40);

						else
							g.drawLine(getWidth()-30, getHeight()-SEPARACION-1+40, getWidth()-30, getHeight()-SEPARACION-ejevertical+40);						
					}

					else{
						if (SEPARACION+zh < getWidth()-30)
							g.drawLine(SEPARACION+zh, getHeight()-SEPARACION-1+40, SEPARACION+zh, getHeight()-SEPARACION-pos+40);

						else
							g.drawLine(getWidth()-30, getHeight()-SEPARACION-1+40, getWidth()-30, getHeight()-SEPARACION-pos+40);

					}

					if (i == (int)media){
							if (SEPARACION+zh < getWidth()-30){
								g2d.setStroke(new BasicStroke(5.0f));
								g2d.setColor(Color.GREEN);
								g.drawLine(SEPARACION+zh, getHeight()-SEPARACION-1+40, SEPARACION+zh, getHeight()-SEPARACION-ejevertical+40);
								g2d.setStroke(new BasicStroke(1.0f));
								g2d.setColor(randomcolor);
							}

							else{
								g2d.setStroke(new BasicStroke(5.0f));
								g2d.setColor(Color.GREEN);
								g.drawLine(getWidth()-30, getHeight()-SEPARACION-1+40, getWidth()-30, getHeight()-SEPARACION-ejevertical+40);
								g2d.setStroke(new BasicStroke(1.0f));
								g2d.setColor(randomcolor);
							}

					}

				}

			}
		}


	}



	// Setters y getters

	public ArrayList<Integer> getVhistograma() {
		return vhistograma;
	}

	public void setVhistograma(ArrayList<Integer> vhistograma) {
		this.vhistograma = vhistograma;
	}




}