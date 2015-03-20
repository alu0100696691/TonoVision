package noe;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import noe.MatrizRGB;


public class MatrizRGB {

	private ArrayList<ArrayList<Color>> vrgb = new ArrayList<ArrayList<Color>>();
	private int width, height;

	public MatrizRGB(BufferedImage vimg){

		width = vimg.getWidth();
		height = vimg.getHeight();

		for (int i=0; i<width; i++){
			vrgb.add(new ArrayList<Color>());
			for (int j=0; j<height; j++){
				vrgb.get(i).add(new Color(vimg.getRGB(i, j)));
			}
		}

	}



	// M�todos de pixel ------------------------------------------------

	public Color pixelagris (Color pixelin){

		int gris;

		gris = (int) (pixelin.getRed()*0.222 + pixelin.getGreen()*0.707 + pixelin.getBlue()*0.071);

		Color pixelout;
		pixelout = new Color(gris, gris, gris);

		return pixelout;

	}

	public Color pixelmedia (Color pixel1, Color pixel2){

		int media;

		media = (int) (pixel1.getRed() + pixel2.getRed());
		media = media/2;

		Color pixelout;
		pixelout = new Color(media, media, media);

		return pixelout;

	}

	public Color pixelmedia (Color pixel1, Color pixel2, Color pixel3){

		int media;

		media = (int) (pixel1.getRed() + pixel2.getRed() + pixel3.getRed());
		media = media/3;

		Color pixelout;
		pixelout = new Color(media, media, media);

		return pixelout;

	}


	public Color pixeltransform(ArrayList<Integer> vout, Color pixelin){

		Color pixelout;

		int r = vout.get(pixelin.getRed());

		pixelout = new Color(r, r, r);

		return pixelout;

	}


	public Color pixeldiferenciaum(int umbral, Color pixelin1, Color pixelin2){

		Color pixelout;

		int r = Math.abs(pixelin1.getRed()-pixelin2.getRed());
		int g = r;
		int b = r;

		if (r > umbral){ 
			r = 255;
			g = 51;
			b = 0;

			pixelout = new Color(r, g, b);

			return pixelout;
		}

		else
			return pixelin1;

	}


	public Color pixeldiferencia(Color pixelin1, Color pixelin2){

		Color pixelout;

		int r = Math.abs(pixelin1.getRed()-pixelin2.getRed());
		int g = r;
		int b = r;

		pixelout = new Color(r, g, b);

		return pixelout;

	}


	// Comprobar si una imagen est� en escala de grises ---------------------------------

	public boolean pixelisgris(Color pixelin){

		boolean gris = false;

		if ((pixelin.getRed()==pixelin.getBlue()) && (pixelin.getRed()==pixelin.getGreen()))
			gris = true;

		return gris;

	}


	public boolean isgris(){

		boolean gris = true;

		for (int i=0; i<width; i++){
			for (int j=0; j<height; j++){
				if (!(pixelisgris(vrgb.get(i).get(j))))
					gris = false;
			}
		}
		return gris;
	}




	// Metodos de transformacion

	public BufferedImage matrizagris(){

		BufferedImage vimgout = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		MatrizRGB matrizout = new MatrizRGB(vimgout);

		for (int i=0; i<width; i++){
			matrizout.vrgb.add(new ArrayList<Color>());

			for (int j=0; j<height; j++){
				matrizout.vrgb.get(i).add(new Color(0,0,0));
				matrizout.vrgb.get(i).set(j, pixelagris(vrgb.get(i).get(j)));
				vimgout.setRGB(i, j, matrizout.vrgb.get(i).get(j).getRGB());
			}
		}	

		return vimgout;

	}		


	public BufferedImage matrizmuestreo(String resol){

		BufferedImage vimgout = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		BufferedImage vimgredu;

		MatrizRGB matrizout = new MatrizRGB(vimgout);

		if (resol == "2x2"){

			vimgredu = new BufferedImage(width/2, height/2, BufferedImage.TYPE_INT_RGB);
			vimgredu = matrizescalar((double)100/2, (double)100/2, 1);

			for (int i=0; i<width; i++){
				matrizout.vrgb.add(new ArrayList<Color>());

				for (int j=0; j<height; j++){
					matrizout.vrgb.get(i).add(new Color(0,0,0));

					if (i/2 == vimgredu.getWidth() && j/2 != vimgredu.getHeight())
						matrizout.vrgb.get(i).set(j, new Color(vimgredu.getRGB(vimgredu.getWidth()-1, j/2)));

					else if (j/2 == vimgredu.getHeight() && i/2 != vimgredu.getWidth())
						matrizout.vrgb.get(i).set(j, new Color(vimgredu.getRGB(i/2, vimgredu.getHeight()-1)));

					else if (i/2 == vimgredu.getWidth() && j/2 == vimgredu.getHeight())
						matrizout.vrgb.get(i).set(j, new Color(vimgredu.getRGB(vimgredu.getWidth()-1, vimgredu.getHeight()-1)));

					else
						matrizout.vrgb.get(i).set(j, new Color(vimgredu.getRGB(i/2, j/2)));

					vimgout.setRGB(i, j, matrizout.vrgb.get(i).get(j).getRGB());
				}
			}	

		}

		if (resol == "3x3"){

			vimgredu = new BufferedImage(width/3, height/3, BufferedImage.TYPE_INT_RGB);
			vimgredu = matrizescalar((double)100/3, (double)100/3, 1);

			for (int i=0; i<width; i++){
				matrizout.vrgb.add(new ArrayList<Color>());

				for (int j=0; j<height; j++){
					matrizout.vrgb.get(i).add(new Color(0,0,0));

					if (i/3 == vimgredu.getWidth() && j/3 != vimgredu.getHeight())
						matrizout.vrgb.get(i).set(j, new Color(vimgredu.getRGB(vimgredu.getWidth()-1, j/3)));

					else if (j/3 == vimgredu.getHeight() && i/3 != vimgredu.getWidth())
						matrizout.vrgb.get(i).set(j, new Color(vimgredu.getRGB(i/3, vimgredu.getHeight()-1)));

					else if (i/3 == vimgredu.getWidth() && j/3 == vimgredu.getHeight())
						matrizout.vrgb.get(i).set(j, new Color(vimgredu.getRGB(vimgredu.getWidth()-1, vimgredu.getHeight()-1)));

					else
						matrizout.vrgb.get(i).set(j, new Color(vimgredu.getRGB(i/3, j/3)));

					vimgout.setRGB(i, j, matrizout.vrgb.get(i).get(j).getRGB());
				}
			}	

		}

		if (resol == "4x4"){

			vimgredu = new BufferedImage(width/4, height/4, BufferedImage.TYPE_INT_RGB);
			vimgredu = matrizescalar((double)100/4, (double)100/4, 1);

			for (int i=0; i<width; i++){
				matrizout.vrgb.add(new ArrayList<Color>());

				for (int j=0; j<height; j++){
					matrizout.vrgb.get(i).add(new Color(0,0,0));

					if (i/4 == vimgredu.getWidth() && j/4 != vimgredu.getHeight())
						matrizout.vrgb.get(i).set(j, new Color(vimgredu.getRGB(vimgredu.getWidth()-1, j/4)));

					else if (j/4 == vimgredu.getHeight() && i/4 != vimgredu.getWidth())
						matrizout.vrgb.get(i).set(j, new Color(vimgredu.getRGB(i/4, vimgredu.getHeight()-1)));

					else if (i/4 == vimgredu.getWidth() && j/4 == vimgredu.getHeight())
						matrizout.vrgb.get(i).set(j, new Color(vimgredu.getRGB(vimgredu.getWidth()-1, vimgredu.getHeight()-1)));

					else
						matrizout.vrgb.get(i).set(j, new Color(vimgredu.getRGB(i/4, j/4)));

					vimgout.setRGB(i, j, matrizout.vrgb.get(i).get(j).getRGB());
				}
			}	

		}

		if (resol == "5x5"){

			vimgredu = new BufferedImage(width/5, height/5, BufferedImage.TYPE_INT_RGB);
			vimgredu = matrizescalar((double)100/5, (double)100/5, 1);

			for (int i=0; i<width; i++){
				matrizout.vrgb.add(new ArrayList<Color>());

				for (int j=0; j<height; j++){
					matrizout.vrgb.get(i).add(new Color(0,0,0));

					if (i/5 == vimgredu.getWidth() && j/5 != vimgredu.getHeight())
						matrizout.vrgb.get(i).set(j, new Color(vimgredu.getRGB(vimgredu.getWidth()-1, j/5)));

					else if (j/5 == vimgredu.getHeight() && i/5 != vimgredu.getWidth())
						matrizout.vrgb.get(i).set(j, new Color(vimgredu.getRGB(i/5, vimgredu.getHeight()-1)));

					else if (i/5 == vimgredu.getWidth() && j/5 == vimgredu.getHeight())
						matrizout.vrgb.get(i).set(j, new Color(vimgredu.getRGB(vimgredu.getWidth()-1, vimgredu.getHeight()-1)));

					else
						matrizout.vrgb.get(i).set(j, new Color(vimgredu.getRGB(i/5, j/5)));

					vimgout.setRGB(i, j, matrizout.vrgb.get(i).get(j).getRGB());
				}
			}	

		}

		return vimgout;
	}

	public BufferedImage matrizcuantizar(int resol){

		BufferedImage vimgout = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		MatrizRGB matrizout = new MatrizRGB(vimgout);

		int com = 0;
		int fin = 0;
		
		ArrayList<Integer> ind = new ArrayList<Integer>();
		ArrayList<Integer> col = new ArrayList<Integer>();
		
		
		// Rellenamos el vector ind
		
		ind.add(127); 
		ind.add(255);
		
		for (int i=0; i<4; i++)
			ind.add((int)Math.round((double)255/4*(i+1)));	
		
		for (int i=0; i<8; i++)
			ind.add((int)Math.round((double)255/8*(i+1)));
			
		for (int i=0; i<16; i++)
			ind.add((int)Math.round((double)255/16*(i+1)));

		for (int i=0; i<32; i++)
			ind.add((int)Math.round((double)255/32*(i+1)));

		for (int i=0; i<64; i++)
			ind.add((int)Math.round((double)255/64*(i+1)));
		
		for (int i=0; i<128; i++)
			ind.add((int)Math.round((double)255/128*(i+1)));

		
		// Rellenamos el vector col
		
		col.add(0);
		col.add(255);
		
		for (int i=0; i<4; i++)
			col.add((int)Math.round((double)255/3*i));

		for (int i=0; i<8; i++)
			col.add((int)Math.round((double)255/7*i));
		
		for (int i=0; i<16; i++)
			col.add((int)Math.round((double)255/15*i));
		
		for (int i=0; i<32; i++)
			col.add((int)Math.round((double)255/31*i));
		
		for (int i=0; i<64; i++)
			col.add((int)Math.round((double)255/63*i));
		
		for (int i=0; i<128; i++)
			col.add((int)Math.round((double)255/127*i));


		
		// Calculamos las variables com y fin para las iteraciones del for interno

		for (int l=1; l<resol; l++)
			com = com + (int)Math.pow(2, (double)l);

		fin = com + (int)Math.pow(2.0, (double)resol);
		
		
		// Recorremos los pixeles y aplicamos la operacion
	
		for (int i=0; i<width; i++){
			matrizout.vrgb.add(new ArrayList<Color>());

			for (int j=0; j<height; j++){
				matrizout.vrgb.get(i).add(new Color(0,0,0));

				for (int k=com; k<fin; k++){
					if (vrgb.get(i).get(j).getRed() <= ind.get(k)){
						matrizout.vrgb.get(i).set(j, new Color(col.get(k),col.get(k),col.get(k)));
						break;
					}
				}

				vimgout.setRGB(i, j, matrizout.vrgb.get(i).get(j).getRGB());
			}
		}	


		return vimgout;
	}



	public BufferedImage matrizescalar(double porcentajex, double porcentajey, int filtro){

		int nwidth = (int)(width*porcentajex/100);
		int nheight = (int)(height*porcentajey/100);
		double escalax = porcentajex/100;
		double escalay = porcentajey/100;

		if(escalax==0)
			escalax=1;
		if(escalay==0)
			escalay=1;

		double A, B, C, D, p, q, r;

		double aux1, aux2;

		BufferedImage vimgout = new BufferedImage(nwidth, nheight, BufferedImage.TYPE_INT_RGB);

		MatrizRGB matrizout = new MatrizRGB(vimgout);

		if (filtro==0){

			for (int i=0; i<nwidth; i++){
				matrizout.vrgb.add(new ArrayList<Color>());

				for (int j=0; j<nheight; j++){	

					matrizout.vrgb.get(i).add(new Color(0,0,0));
					matrizout.vrgb.get(i).set(j, vrgb.get((int)(i/escalax)).get((int)(j/escalay)));
					vimgout.setRGB(i, j, matrizout.vrgb.get(i).get(j).getRGB());
				}
			}	
		}

		else if (filtro==1){
			for (int i=0; i<nwidth; i++){
				matrizout.vrgb.add(new ArrayList<Color>());

				for (int j=0; j<nheight; j++){	
					if ((j==nheight-1) || (i==nwidth-1)){
						matrizout.vrgb.get(i).add(new Color(0,0,0));
						matrizout.vrgb.get(i).set(j, vrgb.get((int)(i/escalax)).get((int)(j/escalay)));
						vimgout.setRGB(i, j, matrizout.vrgb.get(i).get(j).getRGB());					
					}
					else{
						matrizout.vrgb.get(i).add(new Color(0,0,0));


						aux1=(i/escalax)+1;
						aux2=(j/escalay)+1;

						if(aux1>=width)
							aux1=width-1;
						if(aux2>=height)
							aux2=height-1;

						A = vrgb.get((int)(i/escalax)).get((int)aux2).getRed();
						B = vrgb.get((int)aux1).get((int)aux2).getRed();
						C = vrgb.get((int)(i/escalax)).get((int)(j/escalay)).getRed();
						D = vrgb.get((int)(aux1)).get((int)(j/escalay)).getRed();

						p = (i/escalax)-(int)(i/escalax);
						q = (j/escalay)-(int)(j/escalay);

						r = C + (D-C)*p + (A-C)*q + (B+C-A-D)*p*q;

						matrizout.vrgb.get(i).set(j, new Color((int)r,(int)r,(int)r));
						vimgout.setRGB(i, j, matrizout.vrgb.get(i).get(j).getRGB());
					}
				}

			}
		}

		return vimgout;

	}

	public BufferedImage matrizsuavizar(){

		BufferedImage vimgout = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		MatrizRGB matrizout = new MatrizRGB(vimgout);

		for (int i=0; i<width; i++){
			matrizout.vrgb.add(new ArrayList<Color>());

			for (int j=0; j<height; j++){
				matrizout.vrgb.get(i).add(new Color(0,0,0));
				if(i==0){
					matrizout.vrgb.get(i).set(j, pixelmedia(vrgb.get(i).get(j), vrgb.get(i+1).get(j)));
				}
				else if (i==width-1){
					matrizout.vrgb.get(i).set(j, pixelmedia(vrgb.get(i).get(j), vrgb.get(i-1).get(j)));
				}
				else{
					matrizout.vrgb.get(i).set(j, pixelmedia(vrgb.get(i-1).get(j), vrgb.get(i).get(j), vrgb.get(i+1).get(j)));
				}

				vimgout.setRGB(i, j, matrizout.vrgb.get(i).get(j).getRGB());
			}
		}	

		return vimgout;

	}	

	public BufferedImage matrizdiferencia(ArrayList<ArrayList<Color>> vrgb2){

		BufferedImage vimgout = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		MatrizRGB matrizout = new MatrizRGB(vimgout);

		for (int i=0; i<width; i++){
			matrizout.vrgb.add(new ArrayList<Color>());

			for (int j=0; j<height; j++){
				matrizout.vrgb.get(i).add(new Color(0,0,0));
				matrizout.vrgb.get(i).set(j, pixeldiferencia(vrgb.get(i).get(j), vrgb2.get(i).get(j)));
				vimgout.setRGB(i, j, matrizout.vrgb.get(i).get(j).getRGB());
			}
		}	

		return vimgout;

	}

	public BufferedImage matrizdiferenciaum(int umbral, ArrayList<ArrayList<Color>> vrgb2){

		BufferedImage vimgout = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		MatrizRGB matrizout = new MatrizRGB(vimgout);

		for (int i=0; i<width; i++){
			matrizout.vrgb.add(new ArrayList<Color>());

			for (int j=0; j<height; j++){
				matrizout.vrgb.get(i).add(new Color(0,0,0));
				matrizout.vrgb.get(i).set(j, pixeldiferenciaum(umbral, vrgb.get(i).get(j), vrgb2.get(i).get(j)));
				vimgout.setRGB(i, j, matrizout.vrgb.get(i).get(j).getRGB());
			}
		}	

		return vimgout;

	}


	public BufferedImage matrizbrillocont(double brilloin, double brillo, double contrastein, double contraste){

		double a, b;
		int aux;
		a = contraste / contrastein;
		b = brillo - a*brilloin; 

		ArrayList<Integer> vout = new ArrayList<Integer>();

		for (int i=0; i<256; i++){
			aux = (int)(a*i+b);
			if (aux>255)
				aux=255;
			if (aux<0)
				aux=0;
			vout.add(aux);
		}

		BufferedImage vimgout = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		MatrizRGB matrizout = new MatrizRGB(vimgout);

		for (int i=0; i<width; i++){
			matrizout.vrgb.add(new ArrayList<Color>());

			for (int j=0; j<height; j++){
				matrizout.vrgb.get(i).add(new Color(0,0,0));
				matrizout.vrgb.get(i).set(j, pixeltransform(vout, vrgb.get(i).get(j)));
				vimgout.setRGB(i, j, matrizout.vrgb.get(i).get(j).getRGB());
			}
		}	

		return vimgout;

	}

	public BufferedImage matrizecualizar(ArrayList<Integer> vhistogramaacu){

		int aux;
		double imsize = width*height; 
		double hsize = vhistogramaacu.size();
		double var = hsize / imsize;

		BufferedImage vimgout = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		MatrizRGB matrizout = new MatrizRGB(vimgout);

		ArrayList<Integer> vout = new ArrayList<Integer>();


		for (int i=0; i<256; i++){
			aux = (int) Math.round((var*(double)vhistogramaacu.get(i))-1);
			if (aux>256)
				aux=256;
			if (aux<0)
				aux=0;
			vout.add(aux);
		}

		for (int i=0; i<width; i++){
			matrizout.vrgb.add(new ArrayList<Color>());

			for (int j=0; j<height; j++){
				matrizout.vrgb.get(i).add(new Color(0,0,0));
				matrizout.vrgb.get(i).set(j, pixeltransform(vout, vrgb.get(i).get(j)));
				vimgout.setRGB(i, j, matrizout.vrgb.get(i).get(j).getRGB());
			}
		}	

		return vimgout;

	}

	public BufferedImage matrizespecifi(ArrayList<Integer> vha1, ArrayList<Integer> vha2){

		int k;
		ArrayList<Double> vha1nor = new ArrayList<Double>();
		ArrayList<Double> vha2nor = new ArrayList<Double>();
		ArrayList<Integer> vout = new ArrayList<Integer>();

		for (int i=0; i<256; i++){
			vha1nor.add((double)vha1.get(i)/vha1.get(255));
			vha2nor.add((double)vha2.get(i)/vha2.get(255));
			vout.add(0);
		}

		BufferedImage vimgout = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		MatrizRGB matrizout = new MatrizRGB(vimgout);



		for (int i=0; i<256; i++){
			k = 256;
			do{
				vout.set(i,k);
				k--;
			}
			while ((k>=0) && (vha1nor.get(i) <= vha2nor.get(k)));
		}

		for (int i=0; i<width; i++){
			matrizout.vrgb.add(new ArrayList<Color>());

			for (int j=0; j<height; j++){
				matrizout.vrgb.get(i).add(new Color(0,0,0));
				matrizout.vrgb.get(i).set(j, pixeltransform(vout, vrgb.get(i).get(j)));
				vimgout.setRGB(i, j, matrizout.vrgb.get(i).get(j).getRGB());
			}
		}	

		return vimgout;

	}

	public BufferedImage matrizgamma(double gamma){

		int aux;
		double a, b, inred;

		BufferedImage vimgout = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		MatrizRGB matrizout = new MatrizRGB(vimgout);

		ArrayList<Integer> vout = new ArrayList<Integer>();

		for (int i=0; i<256; i++){
			inred = i;
			if (i==0)
				inred=1;
			a = inred/255;
			b = Math.pow(a, gamma);
			aux = (int)Math.round(b*256);
			if (aux>255)
				aux=255;
			if (aux<0)
				aux=0;
			vout.add(aux);
		}

		for (int i=0; i<width; i++){
			matrizout.vrgb.add(new ArrayList<Color>());

			for (int j=0; j<height; j++){
				matrizout.vrgb.get(i).add(new Color(0,0,0));
				matrizout.vrgb.get(i).set(j, pixeltransform(vout, vrgb.get(i).get(j)));
				vimgout.setRGB(i, j, matrizout.vrgb.get(i).get(j).getRGB());
			}
		}	

		return vimgout;

	}

	public BufferedImage matriztransline(ArrayList<Punto> puntos){

		ArrayList<Integer> vout = new ArrayList<Integer>();

		double aux = 0;
		double X1, X2, Y1, Y2;

		for (int i=0; i<256; i++){

			aux = 0;

			for (int j=0; j<puntos.size(); j++){

				if (j==0){
					X1 = puntos.get(j).getX();
					X2 = puntos.get(j+1).getX();
					Y1 = puntos.get(j).getY();
					Y2 = puntos.get(j+1).getY();
					if (i<X1)
						aux = Y1;
					if (i==X1)
						aux = Y1;
					if (i>X1 && i<X2)
						aux = Y1 + ((Y2-Y1)*((i-X1)/(X2-X1)));
				}

				if (j>0 && j<puntos.size()-1){
					X1 = puntos.get(j).getX();
					X2 = puntos.get(j+1).getX();
					Y1 = puntos.get(j).getY();
					Y2 = puntos.get(j+1).getY();
					if (i==X1)
						aux = Y1;
					if (i>X1 && i<X2)
						aux = Y1 + ((Y2-Y1)*((i-X1)/(X2-X1)));
				}

				else if (j==puntos.size()-1){
					X1 = puntos.get(j).getX();
					Y1 = puntos.get(j).getY();
					if (i>=X1)
						aux = Y1;
				}
			}

			if ((aux - (int)aux) > 0.5)
				aux = aux+1;


			vout.add((int)aux);
		}

		BufferedImage vimgout = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		MatrizRGB matrizout = new MatrizRGB(vimgout);

		for (int i=0; i<width; i++){
			matrizout.vrgb.add(new ArrayList<Color>());

			for (int j=0; j<height; j++){
				matrizout.vrgb.get(i).add(new Color(0,0,0));
				matrizout.vrgb.get(i).set(j, pixeltransform(vout, vrgb.get(i).get(j)));
				vimgout.setRGB(i, j, matrizout.vrgb.get(i).get(j).getRGB());
			}
		}	

		return vimgout;

	}
	
	public BufferedImage matrizespejohori(){

		BufferedImage vimgout = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		MatrizRGB matrizout = new MatrizRGB(vimgout);

		for (int i=0; i<width; i++){
			matrizout.vrgb.add(new ArrayList<Color>());

			for (int j=0; j<height; j++){
				matrizout.vrgb.get(i).add(new Color(0,0,0));
				matrizout.vrgb.get(i).set(j, vrgb.get(width-1-i).get(j));
				vimgout.setRGB(i, j, matrizout.vrgb.get(i).get(j).getRGB());
			}
		}	

		return vimgout;

	}


	public BufferedImage matrizespejover(){

		BufferedImage vimgout = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		MatrizRGB matrizout = new MatrizRGB(vimgout);

		for (int i=0; i<width; i++){
			matrizout.vrgb.add(new ArrayList<Color>());

			for (int j=0; j<height; j++){
				matrizout.vrgb.get(i).add(new Color(0,0,0));
				matrizout.vrgb.get(i).set(j, vrgb.get(i).get(height-1-j));
				vimgout.setRGB(i, j, matrizout.vrgb.get(i).get(j).getRGB());
			}
		}	

		return vimgout;

	}


	public BufferedImage matriztraspuesta(){

		BufferedImage vimgout = new BufferedImage(height, width, BufferedImage.TYPE_INT_RGB);

		MatrizRGB matrizout = new MatrizRGB(vimgout);

		for (int i=0; i<width; i++){
			matrizout.vrgb.add(new ArrayList<Color>());

			for (int j=0; j<height; j++){
				matrizout.vrgb.get(i).add(new Color(0,0,0));
				matrizout.vrgb.get(j).set(i, vrgb.get(i).get(j));
				vimgout.setRGB(j, i, matrizout.vrgb.get(j).get(i).getRGB());
			}
		}	

		return vimgout;

	}


	public BufferedImage matrizrotar90(){

		BufferedImage vimgout = new BufferedImage(height, width, BufferedImage.TYPE_INT_RGB);

		MatrizRGB matrizout = new MatrizRGB(vimgout);

		for (int i=0; i<width; i++){
			matrizout.vrgb.add(new ArrayList<Color>());

			for (int j=0; j<height; j++){
				matrizout.vrgb.get(i).add(new Color(0,0,0));
				matrizout.vrgb.get(j).set(i, vrgb.get(i).get(height-1-j));
				vimgout.setRGB(j, i, matrizout.vrgb.get(j).get(i).getRGB());
			}
		}	

		return vimgout;

	}


	public BufferedImage matrizrotar180(){

		BufferedImage vimgout = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		MatrizRGB matrizout = new MatrizRGB(vimgout);

		for (int i=0; i<width; i++){
			matrizout.vrgb.add(new ArrayList<Color>());

			for (int j=0; j<height; j++){
				matrizout.vrgb.get(i).add(new Color(0,0,0));
				matrizout.vrgb.get(i).set(j, vrgb.get(width-1-i).get(height-1-j));
				vimgout.setRGB(i, j, matrizout.vrgb.get(i).get(j).getRGB());
			}
		}	

		return vimgout;

	}


	public BufferedImage matrizrotar270(){

		BufferedImage vimgout = new BufferedImage(height, width, BufferedImage.TYPE_INT_RGB);

		MatrizRGB matrizout = new MatrizRGB(vimgout);

		for (int i=0; i<width; i++){
			matrizout.vrgb.add(new ArrayList<Color>());

			for (int j=0; j<height; j++){
				matrizout.vrgb.get(i).add(new Color(0,0,0));
				matrizout.vrgb.get(j).set(i, vrgb.get(width-1-i).get(j));
				vimgout.setRGB(j, i, matrizout.vrgb.get(j).get(i).getRGB());
			}
		}	

		return vimgout;

	}

	private int diferencia(int a, int b){
		int dife;

		if (((a<0) && (b<0)) || ((a>0) && (b>0)))
			dife = Math.abs(a-b);

		else
			dife = Math.abs(a) + Math.abs(b);

		return dife;		
	}

	public BufferedImage matrizrotarperso(double grados, int mapeo, int filtro){

		grados = Math.toRadians(grados);

		// Esquinas de la imagen original

		int ies_sup_iz_x = 0;
		int ies_sup_iz_y = 0;

		int ies_sup_de_x = width-1;
		int ies_sup_de_y = 0;

		int ies_inf_iz_x = 0;
		int ies_inf_iz_y = height-1;

		int ies_inf_de_x = width-1;
		int ies_inf_de_y = height-1;


		// Nuevas esquinas en el eje de la imagen original

		int es_sup_iz_x = (int)(ies_sup_iz_x*Math.cos(grados)) + (int)(ies_sup_iz_y*(-Math.sin(grados)));
		int es_sup_iz_y = (int)(ies_sup_iz_x*Math.sin(grados)) + (int)(ies_sup_iz_y*(Math.cos(grados)));

		int es_sup_de_x = (int)(ies_sup_de_x*Math.cos(grados)) + (int)(ies_sup_de_y*(-Math.sin(grados)));
		int es_sup_de_y = (int)(ies_sup_de_x*Math.sin(grados)) + (int)(ies_sup_de_y*(Math.cos(grados)));

		int es_inf_iz_x = (int)(ies_inf_iz_x*Math.cos(grados)) + (int)(ies_inf_iz_y*(-Math.sin(grados)));
		int es_inf_iz_y = (int)(ies_inf_iz_x*Math.sin(grados)) + (int)(ies_inf_iz_y*(Math.cos(grados)));

		int es_inf_de_x = (int)(ies_inf_de_x*Math.cos(grados)) + (int)(ies_inf_de_y*(-Math.sin(grados)));
		int es_inf_de_y = (int)(ies_inf_de_x*Math.sin(grados)) + (int)(ies_inf_de_y*(Math.cos(grados)));


		// Calculamos los maximos de x e y de las nuevas esquinas
		// para calcular las dimensiones de la nueva imagen

		int max_x = es_sup_iz_x;
		if (es_sup_de_x > max_x)
			max_x = es_sup_de_x;
		if (es_inf_iz_x > max_x)
			max_x = es_inf_iz_x;
		if (es_inf_de_x > max_x)
			max_x = es_inf_de_x;

		int min_x = es_sup_iz_x;
		if (es_sup_de_x < min_x)
			min_x = es_sup_de_x;
		if (es_inf_iz_x < min_x)
			min_x = es_inf_iz_x;
		if (es_inf_de_x < min_x)
			min_x = es_inf_de_x;

		int max_y = es_sup_iz_y;
		if (es_sup_de_y > max_y)
			max_y = es_sup_de_y;
		if (es_inf_iz_y > max_y)
			max_y = es_inf_iz_y;
		if (es_inf_de_y > max_y)
			max_y = es_inf_de_y;

		int min_y = es_sup_iz_y;
		if (es_sup_de_y < min_y)
			min_y = es_sup_de_y;
		if (es_inf_iz_y < min_y)
			min_y = es_inf_iz_y;
		if (es_inf_de_y < min_y)
			min_y = es_inf_de_y;


		// Calculamos el nuevo ancho y largo

		int nwidth = diferencia(max_x, min_x);
		int nheight = diferencia(max_y, min_y);	


		// Calculamos las esquinas de la nueva imagen (la grande)

		int nes_sup_iz_x = min_x; 
		int nes_sup_iz_y = min_y; 

		int oprio_x = ies_sup_iz_x - nes_sup_iz_x;
		int oprio_y = ies_sup_iz_y - nes_sup_iz_y;


		BufferedImage vimgout = new BufferedImage(nwidth, nheight, BufferedImage.TYPE_INT_RGB);

		MatrizRGB matrizout = new MatrizRGB(vimgout);

		double pix_x, pix_y, xmas,ymas;
		int rx, ry;
		double A, B, C, D, p, q, r;

		if (mapeo==1){

			if (filtro==1){

				for (int i=0; i<nwidth; i++){
					for (int j=0; j<nheight; j++){
						matrizout.vrgb.get(i).add(new Color(210,210,209));
						vimgout.setRGB(i, j, new Color(210,210,209).getRGB());
						rx = i-oprio_x;
						ry = j-oprio_y;
						pix_x = (rx*Math.cos(grados)) + (ry*Math.sin(grados));
						pix_y = (rx*(-Math.sin(grados))) + (ry*(Math.cos(grados)));

						ymas=pix_y+1;
						xmas=pix_x+1;

						if(ymas  >= height)
							ymas =height-1;
						if(xmas  >= width)
							xmas =width-1;
						if(ymas <0)
							ymas =0;
						if(xmas<0)
							xmas=0;

						if (pix_x<0 || pix_x >= width || pix_y < 0 || pix_y >= height){}
						else{
							A = vrgb.get((int)pix_x).get((int)(ymas)).getRed();
							B = vrgb.get((int)(xmas)).get((int)(ymas)).getRed();
							C = vrgb.get((int)pix_x).get((int)pix_y).getRed();
							D = vrgb.get((int)(xmas)).get((int)pix_y).getRed();

							p = pix_x-(int)pix_x;
							q = pix_y-(int)pix_y;

							r = C + (D-C)*p + (A-C)*q + (B+C-A-D)*p*q;
							matrizout.vrgb.get(i).set(j, new Color((int)r,(int)r,(int)r));
							vimgout.setRGB(i, j, matrizout.vrgb.get(i).get(j).getRGB());
						}


					}
				}
			}

			else if (filtro==0){
				for (int i=0; i<nwidth; i++){
					for (int j=0; j<nheight; j++){
						matrizout.vrgb.get(i).add(new Color(210,210,209));
						vimgout.setRGB(i, j, new Color(210,210,209).getRGB());
						rx = i-oprio_x;
						ry = j-oprio_y;
						pix_x = (int)(rx*Math.cos(grados)) + (int)(ry*Math.sin(grados));
						pix_y = (int)(rx*(-Math.sin(grados))) + (int)(ry*(Math.cos(grados)));
						if (pix_x<0 || pix_x >= width || pix_y < 0 || pix_y >= height){}
						else{
							matrizout.vrgb.get(i).set(j, vrgb.get((int)pix_x).get((int)pix_y));
							vimgout.setRGB(i, j, matrizout.vrgb.get(i).get(j).getRGB());
						}
					}
				}

			}

		}

		else if (mapeo==0){
			for (int i=0; i<nwidth; i++){
				for (int j=0; j<nheight; j++){
					matrizout.vrgb.get(i).add(new Color(210,210,209));
					vimgout.setRGB(i, j, new Color(210,210,209).getRGB());
				}
			}


			for (int i=0; i<width; i++){
				for (int j=0; j<height; j++){

					pix_x = (int)(i*Math.cos(grados)) + (int)(j*(-Math.sin(grados)));
					pix_y = (int)(i*Math.sin(grados)) + (int)(j*(Math.cos(grados)));

					pix_x = pix_x + oprio_x;
					pix_y = pix_y + oprio_y;

					if (pix_x<0 || pix_x >= nwidth || pix_y < 0 || pix_y >= nheight){}
					else{
						matrizout.vrgb.get((int)pix_x).set((int)pix_y, vrgb.get(i).get(j));
						vimgout.setRGB((int)pix_x, (int)pix_y, matrizout.vrgb.get((int)pix_x).get((int)pix_y).getRGB());
					}
				}
			}




		}

		return vimgout;


	}


	// Getters y setters ------------------------------------------------

	public int getWidth() {
		return width;
	}


	public void setWidth(int width) {
		this.width = width;
	}


	public int getHeight() {
		return height;
	}


	public void setHeight(int height) {
		this.height = height;
	}


	public ArrayList<ArrayList<Color>> getVrgb() {
		return vrgb;
	}


	public void setVrgb(ArrayList<ArrayList<Color>> vrgb) {
		this.vrgb = vrgb;
	}



}		