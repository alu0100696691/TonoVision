package noe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.IconView;

import noe.VentanaImg;


public class Ventana extends JFrame{
	private JMenuBar barramenu;
	private JToolBar barraiconos;
	private JMenu menuarchivo, menueditar, menuayuda, menuver, menutrans, menulineales, menunolineales, menugeometricas;
	private JMenuItem itemabrir, itemsalir, itemguardarcomo, itemgris, itemhistogramaacu, itemgamma,
	itemhistograma, itembrillocont, itemmostrarinfo, itemtranslinetramos, itemecualizar, itemespeci,
	itemrecortar, itemcross, itemcrosscoor, itemsuavi, itemmuestreo, itemcuantizar, itemdiferencia,
	itemdiferenciaumbral, itemacercade, itemderivada, itemespejohori, itemespejover, itemtraspuesta, 
	itemrotar, itemescalar;
	private JButton botoncuadrado, botonlinea, botonnaranja, botonrosa, botonverde, botonazul,
	botonvioleta;
	private JDesktopPane escritorio;
	private UIManager UI;
	private ArrayList<VentanaImg> vimagenes;
	private int n_img;
	private int  cont_vent; 
	public static boolean cuadrado, linea;
	public static Color coloractual;

	// Colores

	private Color naranjafondo = new Color(253,245,230);
	private Color naranjamenu = new Color(248,223,175);

	private Color rosafondo = new Color(255,228,228);
	private Color rosamenu = new Color(255,187,187);

	private Color verdefondo = new Color(242,254,228);
	private Color verdemenu = new Color(215,247,181);
	
	private Color azulfondo = new Color(227,248,253);
	private Color azulmenu = new Color(165,212,223);
	
	private Color violetafondo = new Color(244,228,253);
	private Color violetamenu = new Color(205,170,227);


	public Ventana(){

		vimagenes = new ArrayList<VentanaImg>();
		n_img = -1;
		cont_vent=-30;

		escritorio = new JDesktopPane();

		cuadrado = true;
		linea = false;

		UI = new UIManager();

		barramenu = new JMenuBar();

		menuarchivo = new JMenu("Archivo");
		menueditar = new JMenu("Editar");		
		menuayuda = new JMenu("Ayuda");
		menuver = new JMenu("Ver");
		menutrans = new JMenu("Transformaciones");

		menulineales = new JMenu("Lineales               ");

		menunolineales = new JMenu("No lineales");
		
		menugeometricas = new JMenu("Geometricas");

		itemabrir = new JMenuItem("Abrir");

		itemsalir = new JMenuItem("Salir");

		itemguardarcomo = new JMenuItem("Guardar como...");

		itemgris = new JMenuItem("Escala de grises");

		itemhistograma = new JMenuItem("Histograma");

		itemhistogramaacu = new JMenuItem("Histograma acumulado");

		itembrillocont = new JMenuItem("Brillo y Contraste");

		itemmostrarinfo = new JMenuItem("Mostrar informacion de la imagen");

		itemtranslinetramos = new JMenuItem("Por tramos");

		itemecualizar = new JMenuItem("Ecualizacion del histograma");

		itemespeci = new JMenuItem("Especificacion del histograma");

		itemgamma = new JMenuItem("Correccion Gamma");

		itemrecortar = new JMenuItem("Recortar imagen");

		itemcross = new JMenuItem("Image-Cross Section");

		itemcrosscoor = new JMenuItem("Image-Cross Section con Coordenadas");

		itemsuavi = new JMenuItem("Suavizar");

		itemcuantizar = new JMenuItem("Cuantizar");

		itemmuestreo = new JMenuItem("Muestrear");

		itemdiferencia = new JMenuItem("Diferencia");

		itemdiferenciaumbral = new JMenuItem("Diferencia con umbral");
		
		itemacercade = new JMenuItem("Acerca de...");
		
		itemderivada = new JMenuItem("Derivada");
		
		itemespejohori = new JMenuItem("Espejo horizontal");
		
		itemespejover = new JMenuItem("Espejo vertical");
		
		itemtraspuesta  = new JMenuItem("Traspuesta");
		
		itemrotar  = new JMenuItem("Rotar");
		
		itemescalar  = new JMenuItem("Escalar imagen");

		barraiconos = new JToolBar();
		barraiconos.setFloatable(false);

		botoncuadrado = new JButton(new ImageIcon("iconos/cuadrado.png"));
		botoncuadrado.setToolTipText("Pintar cuadrado");
		barraiconos.add(botoncuadrado);

		botonlinea = new JButton(new ImageIcon("iconos/linea.png"));
		botonlinea.setToolTipText("Pintar linea");
		barraiconos.add(botonlinea);
		
		barraiconos.addSeparator();

		botonnaranja = new JButton(new ImageIcon("iconos/naranja.png"));
		botonnaranja.setToolTipText("Cambiar color: Naranja");
		barraiconos.add(botonnaranja);

		botonrosa = new JButton(new ImageIcon("iconos/rosa.png"));
		botonrosa.setToolTipText("Cambiar color: Rosa");
		barraiconos.add(botonrosa);
		
		botonvioleta = new JButton(new ImageIcon("iconos/violeta.png"));
		botonvioleta.setToolTipText("Cambiar color: Violeta");
		barraiconos.add(botonvioleta);
		
		botonazul = new JButton(new ImageIcon("iconos/azul.png"));
		botonazul.setToolTipText("Cambiar color: Azul");
		barraiconos.add(botonazul);

		botonverde = new JButton(new ImageIcon("iconos/verde.png"));
		botonverde.setToolTipText("Cambiar color: Verde");
		barraiconos.add(botonverde);
		
		
		
		

		init();
	}

	public void init(){

		setTitle("TonoVision");
		setJMenuBar(barramenu);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setLocationRelativeTo(null);
		setVisible(true);
		getContentPane().add(barraiconos, BorderLayout.NORTH);
		getContentPane().add(escritorio);
		cambiarcolor(naranjafondo, naranjamenu);

		barramenu.add(menuarchivo);
		menuarchivo.add(itemabrir);
		itemabrir.addActionListener(new ActionListener() { // Listener es la accion al clicar
			public void actionPerformed(ActionEvent e) {				
				abririmagen();
			}
		});


		menuarchivo.add(itemguardarcomo);
		itemguardarcomo.addActionListener(new ActionListener() { // Listener es la accion al clicar
			public void actionPerformed(ActionEvent e) {				
				guardaimagen();
			}
		});


		menuarchivo.add(itemsalir);
		itemsalir.addActionListener(new ActionListener() { // Listener es la accion al clicar
			public void actionPerformed(ActionEvent e) {				
				System.exit(0);
			}
		});


		barramenu.add(menueditar);
		menueditar.add(itemgris);
		itemgris.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				imggris();
			}
		});


		menueditar.add(itemrecortar);
		itemrecortar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				imgrecortar();
			}
		});


		barramenu.add(menuver);
		menuver.add(itemhistograma);
		itemhistograma.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				mostrarhistograma();
			}     
		});


		menuver.add(itemhistogramaacu);
		itemhistogramaacu.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				mostrarhistogramaacu();
			}     
		});


		menuver.add(itemmostrarinfo);
		itemmostrarinfo.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				mostrarinformacion();
			}     
		});


		barramenu.add(menutrans);
		menutrans.add(menulineales);
		menutrans.add(menunolineales);
		menutrans.add(menugeometricas);
		
		menulineales.add(itemtranslinetramos);
		itemtranslinetramos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imgtranslinetramos();
			}     
		});


		menulineales.add(itembrillocont);
		itembrillocont.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				imgbrillocontraste();
			}     
		});


		menunolineales.add(itemecualizar);
		itemecualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imgecual();
			}     
		});


		menunolineales.add(itemespeci);
		itemespeci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imgespecifi();
			}     
		});


		menunolineales.add(itemgamma);
		itemgamma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imggamma();
			}     
		});


		menunolineales.add(itemdiferencia);
		itemdiferencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imgdiferencia();
			}     
		});

		menunolineales.add(itemdiferenciaumbral);
		itemdiferenciaumbral.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imgdiferenciaum();
			}     
		});

		menunolineales.add(itemcross);
		itemcross.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imgcross();
			}     
		});


		menunolineales.add(itemcrosscoor);
		itemcrosscoor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imgcrosscoor();
			}     
		});
		
		
		menunolineales.add(itemderivada);
		itemderivada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imgderivada();
			}     
		});


		menunolineales.add(itemsuavi);
		itemsuavi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imgsuavi();
			}     
		});

		menunolineales.add(itemmuestreo);
		itemmuestreo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imgmuestreo();
			}     
		});

		menunolineales.add(itemcuantizar);
		itemcuantizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imgcuantizar();
			}     
		});
		
		menugeometricas.add(itemespejover);
		itemespejover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imgespejover();
			}     
		});
		
		menugeometricas.add(itemespejohori);
		itemespejohori.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imgespejohori();
			}     
		});
		
		menugeometricas.add(itemtraspuesta);
		itemtraspuesta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imgtraspuesta();
			}     
		});
		
		menugeometricas.add(itemrotar);
		itemrotar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imgrotar();
			}     
		});
		
		menugeometricas.add(itemescalar);
		itemescalar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imgescalar();
			}     
		});


		barramenu.add(menuayuda);
		
		menuayuda.add(itemacercade);
		itemacercade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				acercade();
			}     
		});

		botoncuadrado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cuadrado = true;
				linea = false;
			}     
		});

		botonlinea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cuadrado = false;
				linea = true;
			}     
		});

		botonnaranja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiarcolor(naranjafondo, naranjamenu);
			}     
		});

		botonrosa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiarcolor(rosafondo, rosamenu);
			}     
		});

		botonverde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiarcolor(verdefondo, verdemenu);
			}     
		});
		
		botonazul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiarcolor(azulfondo, azulmenu);
			}     
		});
		
		botonvioleta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiarcolor(violetafondo, violetamenu);
			}     
		});


	}



	public void cambiarcolor(Color colorfondo, Color colormenu){

		setColoractual(colormenu);
		
		escritorio.setBackground(colorfondo);
		
		for (int i=0; i<vimagenes.size(); i++){
			vimagenes.get(i).getEtiqueta().setOpaque(true);
			vimagenes.get(i).getEtiqueta().setBackground(colormenu);
			vimagenes.get(i).getHistograma().getGrafico().setBackground(colormenu);
			vimagenes.get(i).getCross().getGrafico().setBackground(colormenu);	
			vimagenes.get(i).getDerivada().getGrafico().setBackground(colormenu);	
		}
		
		UIManager.put("OptionPane.background", colormenu);
		UIManager.put("Panel.background", colormenu);

		barramenu.setBackground(colormenu);

		menulineales.setOpaque(true);
		menulineales.setBackground(colormenu);

		menunolineales.setOpaque(true);
		menunolineales.setBackground(colormenu);
		
		menugeometricas.setOpaque(true);
		menugeometricas.setBackground(colormenu);

		itemabrir.setOpaque(true);
		itemabrir.setBackground(colormenu);

		itemsalir.setOpaque(true);
		itemsalir.setBackground(colormenu);

		itemguardarcomo.setOpaque(true);
		itemguardarcomo.setBackground(colormenu);

		itemgris.setOpaque(true);
		itemgris.setBackground(colormenu);

		itemhistograma.setOpaque(true);
		itemhistograma.setBackground(colormenu);

		itemhistogramaacu.setOpaque(true);
		itemhistogramaacu.setBackground(colormenu);

		itembrillocont.setOpaque(true);
		itembrillocont.setBackground(colormenu);

		itemmostrarinfo.setOpaque(true);
		itemmostrarinfo.setBackground(colormenu);

		itemtranslinetramos.setOpaque(true);
		itemtranslinetramos.setBackground(colormenu);

		itemecualizar.setOpaque(true);
		itemecualizar.setBackground(colormenu);

		itemespeci.setOpaque(true);
		itemespeci.setBackground(colormenu);

		itemgamma.setOpaque(true);
		itemgamma.setBackground(colormenu);

		itemrecortar.setOpaque(true);
		itemrecortar.setBackground(colormenu);

		itemcross.setOpaque(true);
		itemcross.setBackground(colormenu);

		itemcrosscoor.setOpaque(true);
		itemcrosscoor.setBackground(colormenu);

		itemsuavi.setOpaque(true);
		itemsuavi.setBackground(colormenu);

		itemcuantizar.setOpaque(true);
		itemcuantizar.setBackground(colormenu);

		itemmuestreo.setOpaque(true);
		itemmuestreo.setBackground(colormenu);

		itemdiferencia.setOpaque(true);
		itemdiferencia.setBackground(colormenu);

		itemdiferenciaumbral.setOpaque(true);
		itemdiferenciaumbral.setBackground(colormenu);
		
		itemacercade.setOpaque(true);
		itemacercade.setBackground(colormenu);
		
		itemderivada.setOpaque(true);
		itemderivada.setBackground(colormenu);
		
		itemespejover.setOpaque(true);
		itemespejover.setBackground(colormenu);
		
		itemespejohori.setOpaque(true);
		itemespejohori.setBackground(colormenu);
		
		itemtraspuesta.setOpaque(true);
		itemtraspuesta.setBackground(colormenu);
		
		itemrotar.setOpaque(true);
		itemrotar.setBackground(colormenu);
		
		itemescalar.setOpaque(true);
		itemescalar.setBackground(colormenu);

		barraiconos.setBackground(colormenu);
		

	}

	public void cambiarcoloraleatorio(){

		Random rand = new Random();
		float rf = rand.nextFloat();
		float gf = rand.nextFloat();
		float bf = rand.nextFloat();
		Color colormenu = new Color(rf,gf,bf);
		Color colorfondo = new Color(253,245,230);

		escritorio.setBackground(colorfondo);

		UIManager.put("OptionPane.background", colormenu);
		UIManager.put("Panel.background", colormenu);

		barramenu.setBackground(colormenu);

		menulineales.setOpaque(true);
		menulineales.setBackground(colormenu);

		menunolineales.setOpaque(true);
		menunolineales.setBackground(colormenu);
		
		menugeometricas.setOpaque(true);
		menugeometricas.setBackground(colormenu);

		itemabrir.setOpaque(true);
		itemabrir.setBackground(colormenu);

		itemsalir.setOpaque(true);
		itemsalir.setBackground(colormenu);

		itemguardarcomo.setOpaque(true);
		itemguardarcomo.setBackground(colormenu);

		itemgris.setOpaque(true);
		itemgris.setBackground(colormenu);

		itemhistograma.setOpaque(true);
		itemhistograma.setBackground(colormenu);

		itemhistogramaacu.setOpaque(true);
		itemhistogramaacu.setBackground(colormenu);

		itembrillocont.setOpaque(true);
		itembrillocont.setBackground(colormenu);

		itemmostrarinfo.setOpaque(true);
		itemmostrarinfo.setBackground(colormenu);

		itemtranslinetramos.setOpaque(true);
		itemtranslinetramos.setBackground(colormenu);

		itemecualizar.setOpaque(true);
		itemecualizar.setBackground(colormenu);

		itemespeci.setOpaque(true);
		itemespeci.setBackground(colormenu);

		itemgamma.setOpaque(true);
		itemgamma.setBackground(colormenu);

		itemrecortar.setOpaque(true);
		itemrecortar.setBackground(colormenu);

		itemcross.setOpaque(true);
		itemcross.setBackground(colormenu);

		itemcrosscoor.setOpaque(true);
		itemcrosscoor.setBackground(colormenu);

		itemsuavi.setOpaque(true);
		itemsuavi.setBackground(colormenu);

		itemcuantizar.setOpaque(true);
		itemcuantizar.setBackground(colormenu);

		itemmuestreo.setOpaque(true);
		itemmuestreo.setBackground(colormenu);

		itemdiferencia.setOpaque(true);
		itemdiferencia.setBackground(colormenu);

		itemdiferenciaumbral.setOpaque(true);
		itemdiferenciaumbral.setBackground(colormenu);
		
		itemacercade.setOpaque(true);
		itemacercade.setBackground(colormenu);
		
		itemderivada.setOpaque(true);
		itemderivada.setBackground(colormenu);
		
		itemespejover.setOpaque(true);
		itemespejover.setBackground(colormenu);
		
		itemespejohori.setOpaque(true);
		itemespejohori.setBackground(colormenu);
		
		itemtraspuesta.setOpaque(true);
		itemtraspuesta.setBackground(colormenu);
		
		itemrotar.setOpaque(true);
		itemrotar.setBackground(colormenu);
		
		itemescalar.setOpaque(true);
		itemescalar.setBackground(colormenu);

		barraiconos.setBackground(colormenu);

	}


	public void abririmagen(){

		JFileChooser fc = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG", "png"); // Filtro PNG
		fc.setFileFilter(filter);
		File rutadefecto = new File("./");
		fc.setCurrentDirectory(rutadefecto);

		String rutaimg;

		String nombreimg;

		BufferedImage img = null;


		int opcion = fc.showOpenDialog(itemabrir);

		// Si abrimos un archivo:
		if (opcion == JFileChooser.APPROVE_OPTION) {
			rutaimg = fc.getSelectedFile().getPath();
			nombreimg = fc.getSelectedFile().getName();

			try {
				img = ImageIO.read(new File(rutaimg));
			}
			catch(IOException e) {
				e.getMessage();
			}

			aumentacontvent();

			MatrizRGB vrgb_ori = null;

			VentanaImg vimg = new VentanaImg(vrgb_ori,img, nombreimg, cont_vent);

			nuevaventana(vimg);

		}
	}

	public void guardaimagen(){

		for (int i=0; i<vimagenes.size(); i++){
			if (vimagenes.get(i).isSelected()) { 

				JFileChooser fc = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG", "png"); // Filtro PNG
				fc.setFileFilter(filter);
				File nombredefecto = new File(vimagenes.get(i).getVnombreimg());
				File rutadefecto = new File("./");
				fc.setCurrentDirectory(rutadefecto);
				fc.setSelectedFile(nombredefecto);

				int r = fc.showSaveDialog(null);

				if(r == JFileChooser.APPROVE_OPTION){

					File archivo = fc.getSelectedFile();

					if(new File(fc.getSelectedFile().getPath()).exists()) 
					{ 
						if(JOptionPane.OK_OPTION == JOptionPane.showConfirmDialog(this,"El fichero ya existe, ¿deseas reemplazarlo?","Aviso",JOptionPane.YES_NO_OPTION)) 

							try {
								ImageIO.write(vimagenes.get(i).getVimg(), "png", archivo);
							}
						catch(IOException e) {
							e.printStackTrace();
						}
					}

					else{

						try {
							ImageIO.write(vimagenes.get(i).getVimg(), "png", archivo);
						}
						catch(IOException e) {
							e.printStackTrace();
						}
					}


				}
			}
		}		
	}

	public void mostrarhistograma(){
		for (int i=0; i<vimagenes.size(); i++){
			if (vimagenes.get(i).isSelected()){
				vimagenes.get(i).getHistograma().setVisible(true);
				vimagenes.get(i).getHistograma().toFront();

			}			
		}
	}

	public void mostrarhistogramaacu(){
		for (int i=0; i<vimagenes.size(); i++){
			if (vimagenes.get(i).isSelected()){
				vimagenes.get(i).getHistogramaacu().setVisible(true);
				vimagenes.get(i).getHistogramaacu().toFront();			
			}
		}
	}


	public void nuevaventana(VentanaImg vimg){

		n_img++;
		vimagenes.add(vimg);
		escritorio.add(vimagenes.get(n_img));
		vimagenes.get(n_img).toFront();
		escritorio.add(vimg.getHistograma());
		vimg.getHistograma().setVisible(false);
		escritorio.add(vimg.getHistogramaacu());
		vimg.getHistogramaacu().setVisible(false);
		escritorio.add(vimg.getCross());
		vimg.getCross().setVisible(false);
		escritorio.add(vimg.getDerivada());
		vimg.getDerivada().setVisible(false);

	}

	public void imggris(){
		for (int i=0; i<vimagenes.size(); i++){  
			if (vimagenes.get(i).isSelected()) {				

				String nombreimgout = vimagenes.get(i).getVnombreimg().
						substring(0, vimagenes.get(i).getVnombreimg().lastIndexOf('.')) + "-Gris.png";

				BufferedImage vimgout = vimagenes.get(i).getVrgb().matrizagris();

				aumentacontvent();

				VentanaImg imgout = new VentanaImg(vimagenes.get(i).getVrgb(), vimgout, nombreimgout, cont_vent);

				nuevaventana(imgout);	

			}
		}		

	}


	public void imgtranslinetramos(){

		for (int i=0; i<vimagenes.size(); i++){
			if (vimagenes.get(i).isSelected()) { 

				if (vimagenes.get(i).getVrgb().isgris()){

					String nombreimgout = vimagenes.get(i).getVnombreimg().
							substring(0, vimagenes.get(i).getVnombreimg().lastIndexOf('.')) + "-TransLineTram.png";

					int tramos = -1;
					String elec;

					while ((tramos<0) || (tramos>9)){
						elec = JOptionPane.showInputDialog("Numero de tramos");
						if (elec==null)
							return;
						tramos = Integer.parseInt(elec);
					}

					CoordPane panelcp = new CoordPane(tramos);

					ArrayList<Punto> puntos = new ArrayList<Punto>();
					for (int j=0; j<tramos+1; j++)
						puntos.add(new Punto());

					int eleccion;

					boolean condicion = false;

					while (condicion == false){

						condicion = true;

						eleccion = JOptionPane.showConfirmDialog(null, panelcp.getVpanel(),
								"Coordenadas de los puntos", JOptionPane.OK_CANCEL_OPTION);

						if (eleccion == JOptionPane.OK_OPTION){

							puntos.get(0).setX(Integer.parseInt(panelcp.getVcx(0).getText()));
							puntos.get(0).setY(Integer.parseInt(panelcp.getVcy(0).getText()));

							for (int k=1; k<tramos+1; k++){
								puntos.get(k).setX(Integer.parseInt(panelcp.getVcx(k).getText()));
								puntos.get(k).setY(Integer.parseInt(panelcp.getVcy(k).getText()));
							}

							if (puntos.get(0).getX()<0 || puntos.get(0).getX()>255 || puntos.get(0).getY()<0 || puntos.get(0).getY()>255)
								condicion = false;


							for (int k=1; k<tramos+1; k++){
								if (puntos.get(k).getX() < puntos.get(k-1).getX())
									condicion = false;
							}
						}

						else if (eleccion == JOptionPane.CANCEL_OPTION || eleccion == JOptionPane.CLOSED_OPTION)
							return;
					}


					BufferedImage vimgout = 
							vimagenes.get(i).getVrgb().matriztransline(puntos);

					aumentacontvent();

					VentanaImg imgout = new VentanaImg(vimagenes.get(i).getVrgb(), vimgout, nombreimgout, cont_vent);

					nuevaventana(imgout);

				}

			}

		}	

	}

	public void imgrecortar(){

		for (int i=0; i<vimagenes.size(); i++){
			if (vimagenes.get(i).isSelected()) { 

				if (vimagenes.get(i).getVrgb().isgris()){

					String nombreimgout = vimagenes.get(i).getVnombreimg().
							substring(0, vimagenes.get(i).getVnombreimg().lastIndexOf('.')) + "-Recortada.png";

					BufferedImage vimgout = vimagenes.get(i).getVimg().
							getSubimage(Math.min(vimagenes.get(i).getX1(), vimagenes.get(i).getX2()), 
									Math.min(vimagenes.get(i).getY1(), vimagenes.get(i).getY2()), 
									Math.max(vimagenes.get(i).getX1(), vimagenes.get(i).getX2()) - 
									Math.min(vimagenes.get(i).getX1(), vimagenes.get(i).getX2()), 
									Math.max(vimagenes.get(i).getY1(), vimagenes.get(i).getY2()) - 
									Math.min(vimagenes.get(i).getY1(), vimagenes.get(i).getY2()));

					aumentacontvent();

					VentanaImg imgout = new VentanaImg(vimagenes.get(i).getVrgb(), vimgout, nombreimgout, cont_vent);

					nuevaventana(imgout);

				}

			}
		}	

	}

	public void imggamma(){

		for (int i=0; i<vimagenes.size(); i++){
			if (vimagenes.get(i).isSelected()) { 

				if (vimagenes.get(i).getVrgb().isgris()){

					Double gamma = -1.0;
					String elec;
					while ((gamma<0) || (gamma>100)){
						elec = JOptionPane.showInputDialog("Valor de Gamma");
						if (elec==null)
							return;
						gamma = Double.parseDouble(elec);
					}

					String nombreimgout = vimagenes.get(i).getVnombreimg().
							substring(0, vimagenes.get(i).getVnombreimg().lastIndexOf('.')) + "-Gamma" + "(" + gamma + ")" + ".png";


					BufferedImage vimgout = vimagenes.get(i).getVrgb().matrizgamma(gamma);

					aumentacontvent();

					VentanaImg imgout = new VentanaImg(vimagenes.get(i).getVrgb(), vimgout, nombreimgout, cont_vent);

					nuevaventana(imgout);
				}

			}
		}	

	}

	public void imgespecifi(){

		for (int i=0; i<vimagenes.size(); i++){
			if (vimagenes.get(i).isSelected()) { 

				if (vimagenes.get(i).getVrgb().isgris()){

					ArrayList<Object> vnombres = new ArrayList<Object>();
					int img2=0;

					for (int j=0; j<vimagenes.size(); j++)
						if ((!vimagenes.get(j).isClosed())
								&& (vimagenes.get(j).getVrgb().isgris())
								&& (j!=i))
							vnombres.add(vimagenes.get(j).getVnombreimg());

					if (vnombres.size()==0){
						JOptionPane.showMessageDialog(null, "No hay imagenes de las que\nextraer el histograma");
						return;
					}

					Object[] opciones = vnombres.toArray();


					Object seleccion = JOptionPane.showInputDialog(null, "Seleccione otra imagen \npara obtener el histograma", 
							"Selector de imagenes", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

					for (int k=0; k<vimagenes.size(); k++)
						if (vimagenes.get(k).getVnombreimg()==seleccion)
							img2=k;

					if (seleccion!=null){

						String nombreimgout = vimagenes.get(i).getVnombreimg().
								substring(0, vimagenes.get(i).getVnombreimg().lastIndexOf('.')) + "Especif.Histograma.png";

						BufferedImage vimgout = 
								vimagenes.get(i).getVrgb().matrizespecifi(vimagenes.get(i).getHistogramaacu().getVhistogramaacu(), 
										vimagenes.get(img2).getHistogramaacu().getVhistogramaacu());

						aumentacontvent();

						VentanaImg imgout = new VentanaImg(vimagenes.get(i).getVrgb(), vimgout, nombreimgout, cont_vent);

						nuevaventana(imgout);	
					}

				}
			}

		}
	}

	public void imgecual(){

		for (int i=0; i<vimagenes.size(); i++){
			if (vimagenes.get(i).isSelected()) { 

				if (vimagenes.get(i).getVrgb().isgris()){

					String nombreimgout = vimagenes.get(i).getVnombreimg().
							substring(0, vimagenes.get(i).getVnombreimg().lastIndexOf('.')) + "-Ecualizar.png";

					BufferedImage vimgout = 
							vimagenes.get(i).getVrgb().
							matrizecualizar(vimagenes.get(i).getHistograma().getVhistogramaacu());

					aumentacontvent();

					VentanaImg imgout = new VentanaImg(vimagenes.get(i).getVrgb(), vimgout, nombreimgout, cont_vent);

					nuevaventana(imgout);

				}

			}
		}	

	}

	public void imgsuavi(){

		for (int i=0; i<vimagenes.size(); i++){
			if (vimagenes.get(i).isSelected()) { 

				if (vimagenes.get(i).getVrgb().isgris()){

					String nombreimgout = vimagenes.get(i).getVnombreimg().
							substring(0, vimagenes.get(i).getVnombreimg().lastIndexOf('.')) + "-Suavizar.png";

					BufferedImage vimgout = 
							vimagenes.get(i).getVrgb().
							matrizsuavizar();

					aumentacontvent();

					VentanaImg imgout = new VentanaImg(vimagenes.get(i).getVrgb(), vimgout, nombreimgout, cont_vent);

					nuevaventana(imgout);

				}

			}
		}	

	}

	public void imgbrillocontraste(){

		for (int i=0; i<vimagenes.size(); i++){
			if (vimagenes.get(i).isSelected()) { 

				if (vimagenes.get(i).getVrgb().isgris()){

					String nombreimgout = vimagenes.get(i).getVnombreimg().
							substring(0, vimagenes.get(i).getVnombreimg().lastIndexOf('.')) + "-Brillo.png";

					int brillo = -1;
					int contraste = -1;
					int eleccion;

					BriConPane panelbc = new BriConPane();
					escritorio.add(panelbc.getMyPanel());

					while ( (brillo<0) || (brillo>255) || (contraste<0) || (contraste>255)){
						eleccion = JOptionPane.showConfirmDialog(null, panelbc.getMyPanel(), 
								"Cambiar brillo y contraste", JOptionPane.OK_CANCEL_OPTION);
						if (eleccion == JOptionPane.OK_OPTION) {
							brillo = Integer.parseInt(panelbc.getBrillo().getText());
							contraste = Integer.parseInt(panelbc.getContraste().getText());
						}

						else if (eleccion == JOptionPane.CANCEL_OPTION || eleccion == JOptionPane.CLOSED_OPTION)
							return;
					}				

					BufferedImage vimgout = 
							vimagenes.get(i).getVrgb().matrizbrillocont(vimagenes.get(i).getBrillo(), brillo,
									vimagenes.get(i).getContraste(), contraste);

					aumentacontvent();

					VentanaImg imgout = new VentanaImg(vimagenes.get(i).getVrgb(), vimgout, nombreimgout, cont_vent);

					nuevaventana(imgout);	

				}

			}
		}	

	}

	public void imgdiferencia(){

		for (int i=0; i<vimagenes.size(); i++){
			if (vimagenes.get(i).isSelected()) { 

				if (vimagenes.get(i).getVrgb().isgris()){

					ArrayList<Object> vnombres = new ArrayList<Object>();
					int img2=0;

					for (int j=0; j<vimagenes.size(); j++)
						if ((!vimagenes.get(j).isClosed())
								&& (vimagenes.get(j).getVrgb().isgris())
								&& (vimagenes.get(j).getVrgb().getVrgb().size()==vimagenes.get(i).getVrgb().getVrgb().size())
								&& (j!=i))
							vnombres.add(vimagenes.get(j).getVnombreimg());

					if (vnombres.size()==0){
						JOptionPane.showMessageDialog(null, "No hay imagenes con las que\nobtener la diferencia");
						return;
					}

					Object[] opciones = vnombres.toArray();


					Object seleccion = JOptionPane.showInputDialog(null, "Seleccione otra imagen \npara obtener la diferencia", 
							"Selector de im�genes", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

					for (int k=0; k<vimagenes.size(); k++)
						if (vimagenes.get(k).getVnombreimg()==seleccion)
							img2=k;

					if (seleccion!=null){

						String nombreimgout = vimagenes.get(i).getVnombreimg().
								substring(0, vimagenes.get(i).getVnombreimg().lastIndexOf('.')) + "Diferencia.png";

						BufferedImage vimgout = 
								vimagenes.get(i).getVrgb().matrizdiferencia(vimagenes.get(img2).getVrgb().getVrgb());

						aumentacontvent();

						VentanaImg imgout = new VentanaImg(vimagenes.get(i).getVrgb(), vimgout, nombreimgout, cont_vent);

						nuevaventana(imgout);	
					}

				}
			}

		}
	}	

	public void imgmuestreo(){

		for (int i=0; i<vimagenes.size(); i++){
			if (vimagenes.get(i).isSelected()) { 

				if (vimagenes.get(i).getVrgb().isgris()){

					ArrayList<Object> vopciones = new ArrayList<Object>();
					vopciones.add("2x2");
					vopciones.add("3x3");
					vopciones.add("4x4");
					vopciones.add("5x5");


					Object[] opciones = vopciones.toArray();


					Object seleccion = JOptionPane.showInputDialog(null, "Seleccione el nivel de muestreo", 
							"Selector de nivel de muestreo", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

					String resol = seleccion.toString();

					if (seleccion!=null){

						String nombreimgout = vimagenes.get(i).getVnombreimg().
								substring(0, vimagenes.get(i).getVnombreimg().lastIndexOf('.')) + "Muestreo" + resol + ".png";

						BufferedImage vimgout = 
								vimagenes.get(i).getVrgb().matrizmuestreo(resol);

						aumentacontvent();

						VentanaImg imgout = new VentanaImg(vimagenes.get(i).getVrgb(), vimgout, nombreimgout, cont_vent);

						nuevaventana(imgout);	
					}

				}
			}

		}
	}	

	public void imgcuantizar(){

		for (int i=0; i<vimagenes.size(); i++){
			if (vimagenes.get(i).isSelected()) { 

				if (vimagenes.get(i).getVrgb().isgris()){

					ArrayList<Object> vopciones = new ArrayList<Object>();
					vopciones.add("128 niveles (7 bits)");
					vopciones.add(" 64 niveles (6 bits)");
					vopciones.add(" 32 niveles (5 bits)");
					vopciones.add(" 16 niveles (4 bits)");
					vopciones.add("  8 niveles (3 bits)");
					vopciones.add("  4 niveles (2 bits)");
					vopciones.add("  2 niveles (1 bits)");


					Object[] opciones = vopciones.toArray();


					Object seleccion = JOptionPane.showInputDialog(null, "Seleccione el nivel de cuantizacion", 
							"Selector de nivel de cuantizacion", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

					int resol = Integer.parseInt(seleccion.toString().substring(13, 14));

					if (seleccion!=null){

						String nombreimgout = vimagenes.get(i).getVnombreimg().
								substring(0, vimagenes.get(i).getVnombreimg().lastIndexOf('.')) + "Cuantizar" + resol + "bits.png";

						BufferedImage vimgout = 
								vimagenes.get(i).getVrgb().matrizcuantizar(resol);

						aumentacontvent();

						VentanaImg imgout = new VentanaImg(vimagenes.get(i).getVrgb(), vimgout, nombreimgout, cont_vent);

						nuevaventana(imgout);	
					}

				}
			}

		}
	}	


	public void imgdiferenciaum(){

		for (int i=0; i<vimagenes.size(); i++){
			if (vimagenes.get(i).isSelected()) { 

				if (vimagenes.get(i).getVrgb().isgris()){

					ArrayList<Object> vnombres = new ArrayList<Object>();
					int img2=0;

					for (int j=0; j<vimagenes.size(); j++)
						if ((!vimagenes.get(j).isClosed())
								&& (vimagenes.get(j).getVrgb().isgris())
								&& (vimagenes.get(j).getVrgb().getVrgb().size()==vimagenes.get(i).getVrgb().getVrgb().size())
								&& (j!=i))
							vnombres.add(vimagenes.get(j).getVnombreimg());

					if (vnombres.size()==0){
						JOptionPane.showMessageDialog(null, "No hay imagenes con las que\nobtener la diferencia");
						return;
					}

					Object[] opciones = vnombres.toArray();

					Object seleccion = JOptionPane.showInputDialog(null, "Seleccione otra imagen \npara obtener la diferencia", 
							"Selector de imagenes", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

					for (int k=0; k<vimagenes.size(); k++)
						if (vimagenes.get(k).getVnombreimg()==seleccion)
							img2=k;

					int umbral = -1;
					String elec;
					while ((umbral<0) || (umbral>254)){
						elec = JOptionPane.showInputDialog("Valor del umbral");
						if (elec==null)
							return;
						umbral = Integer.parseInt(elec);
					}

					if (seleccion!=null){

						String nombreimgout = vimagenes.get(i).getVnombreimg().
								substring(0, vimagenes.get(i).getVnombreimg().lastIndexOf('.')) + "Diferencia.png";

						BufferedImage vimgout = 
								vimagenes.get(i).getVrgb().matrizdiferenciaum(umbral, vimagenes.get(img2).getVrgb().getVrgb());

						aumentacontvent();

						VentanaImg imgout = new VentanaImg(vimagenes.get(i).getVrgb(), vimgout, nombreimgout, cont_vent);

						nuevaventana(imgout);	
					}

				}
			}

		}
	}	

	public void imgcross(){
		for (int i=0; i<vimagenes.size(); i++){
			if (vimagenes.get(i).isSelected()){
				vimagenes.get(i).getCross().setVisible(true);
				vimagenes.get(i).getCross().toFront();			
			}
		}
	}

	public void imgcrosscoor(){
		for (int i=0; i<vimagenes.size(); i++){
			if (vimagenes.get(i).isSelected()){
				CoordPane panelcp = new CoordPane(1);

				int x1 = 0;
				int y1 = 0;
				int x2 = 0;
				int y2 = 0;

				int eleccion;

				boolean condicion = false;

				while (condicion == false){

					condicion = true;

					eleccion = JOptionPane.showConfirmDialog(null, panelcp.getVpanel(),
							"Coordenadas de los puntos", JOptionPane.OK_CANCEL_OPTION);

					if (eleccion == JOptionPane.OK_OPTION){

						x1 = Integer.parseInt(panelcp.getVcx(0).getText());
						y1 = Integer.parseInt(panelcp.getVcy(0).getText());


						x2 = Integer.parseInt(panelcp.getVcx(1).getText());
						y2 = Integer.parseInt(panelcp.getVcy(1).getText());


						if (x1<0 || x1>vimagenes.get(i).getWidth() || y1<0 || y1>vimagenes.get(i).getHeight() ||
								x2<0 || x2>vimagenes.get(i).getWidth() || y2<0 || y2>vimagenes.get(i).getHeight())
							condicion = false;

					}

					else if (eleccion == JOptionPane.CANCEL_OPTION || eleccion == JOptionPane.CLOSED_OPTION)
						return;
				}
				vimagenes.get(i).getCross().getGrafico().setX1(x1);
				vimagenes.get(i).getCross().getGrafico().setY1(y1);
				vimagenes.get(i).getCross().getGrafico().setX2(x2);
				vimagenes.get(i).getCross().getGrafico().setY2(y2);
				vimagenes.get(i).getCross().getGrafico().setPendiente(x1, y1, x2, y2);
				vimagenes.get(i).getCross().getGrafico().setN_puntos(x1, y1, x2, y2);

				vimagenes.get(i).getCross().setVisible(true);
				vimagenes.get(i).getCross().toFront();			
			}
		}
	}
	
	
	public void imgderivada(){
		for (int i=0; i<vimagenes.size(); i++){
			if (vimagenes.get(i).isSelected()){
				vimagenes.get(i).getDerivada().setVisible(true);
				vimagenes.get(i).getDerivada().toFront();			
			}
		}
	}
	
	
	public void imgespejohori(){

		for (int i=0; i<vimagenes.size(); i++){
			if (vimagenes.get(i).isSelected()) { 

				String nombreimgout = vimagenes.get(i).getVnombreimg().
				substring(0, vimagenes.get(i).getVnombreimg().lastIndexOf('.')) + "-EspejoHorizontal.png";

				BufferedImage vimgout = 
					vimagenes.get(i).getVrgb().
					matrizespejohori();

				aumentacontvent();

				VentanaImg imgout = new VentanaImg(vimagenes.get(i).getVrgb(), vimgout, nombreimgout, cont_vent);

				nuevaventana(imgout);

			}
		}	

	}

	public void imgespejover(){

		for (int i=0; i<vimagenes.size(); i++){
			if (vimagenes.get(i).isSelected()) { 

				String nombreimgout = vimagenes.get(i).getVnombreimg().
				substring(0, vimagenes.get(i).getVnombreimg().lastIndexOf('.')) + "-EspejoVertical.png";

				BufferedImage vimgout = 
					vimagenes.get(i).getVrgb().
					matrizespejover();

				aumentacontvent();

				VentanaImg imgout = new VentanaImg(vimagenes.get(i).getVrgb(), vimgout, nombreimgout, cont_vent);

				nuevaventana(imgout);

			}
		}	

	}

	public void imgtraspuesta(){

		for (int i=0; i<vimagenes.size(); i++){
			if (vimagenes.get(i).isSelected()) { 

				String nombreimgout = vimagenes.get(i).getVnombreimg().
				substring(0, vimagenes.get(i).getVnombreimg().lastIndexOf('.')) + "-Traspuesta.png";

				BufferedImage vimgout = 
					vimagenes.get(i).getVrgb().
					matriztraspuesta();

				aumentacontvent();

				VentanaImg imgout = new VentanaImg(vimagenes.get(i).getVrgb(), vimgout, nombreimgout, cont_vent);

				nuevaventana(imgout);

			}
		}	

	}



	public void imgrotar(){

		for (int i=0; i<vimagenes.size(); i++){
			if (vimagenes.get(i).isSelected()) { 

				int mapeo = JOptionPane.showOptionDialog(null, "Seleccione un tipo:", "Tipo de mapeo", JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, new Object[] { "Mapeo Directo", "Mapeo Indirecto", "Cancelar" }, "Mapeo Directo");

				if (mapeo==2)
					return;

				int filtro = 0;

				if (mapeo == 1){
					filtro = JOptionPane.showOptionDialog(null, "Seleccione un tipo:", "Tipo de filtro", JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, new Object[] { "VMP", "Bilineal", "Cancelar" }, "VMP");
					if (filtro==2)
						return;

				}

				Double grados = 361.0;

				while ((grados<0) || (grados>360)){
					grados = Double.parseDouble(JOptionPane.showInputDialog("Grados de rotacion [0,360]"));
				}

				String nombreimgout = vimagenes.get(i).getVnombreimg().
				substring(0, vimagenes.get(i).getVnombreimg().lastIndexOf('.')) + "-Rotar" + Math.round(grados) + ".png";

				BufferedImage vimgout = null;

				if (grados==90.0){
					vimgout = 
						vimagenes.get(i).getVrgb().
						matrizrotar90();
				}

				else if (grados==180.0){
					vimgout = 
						vimagenes.get(i).getVrgb().
						matrizrotar180();

				}

				else if (grados==270.0){
					vimgout = 
						vimagenes.get(i).getVrgb().
						matrizrotar270();
				}

				else{
					vimgout = vimagenes.get(i).getVrgb().matrizrotarperso(grados, mapeo, filtro);

				}


				aumentacontvent();

				VentanaImg imgout = new VentanaImg(vimagenes.get(i).getVrgb(), vimgout, nombreimgout, cont_vent);

				nuevaventana(imgout);

			}
		}	

	}


	public void imgescalar(){

		for (int i=0; i<vimagenes.size(); i++){
			if (vimagenes.get(i).isSelected()) { 

				int filtro = JOptionPane.showOptionDialog(null, "Seleccione un tipo:", "Tipo de filtro", JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, new Object[] { "VMP", "Bilineal", "Cancelar" }, "VMP");
				if (filtro==2)
					return;

				ZoomPane panelzp = new ZoomPane();
				escritorio.add(panelzp.getMyPanel());

				int eleccion;
				double porcentajeh = 401;
				double porcentajev = 401;


				while ( porcentajeh<0 || porcentajeh>400 || 
						porcentajev<0 || porcentajev>400){
					eleccion = JOptionPane.showConfirmDialog(null, panelzp.getMyPanel(), 
							"Porcentajes de zoom", JOptionPane.OK_CANCEL_OPTION);
					if (eleccion == JOptionPane.OK_OPTION) {
						porcentajeh = Integer.parseInt(panelzp.getZh().getText());
						porcentajev = Integer.parseInt(panelzp.getZv().getText());
					}

					else if (eleccion == JOptionPane.CANCEL_OPTION)
						return;
				}	


				String nombreimgout = vimagenes.get(i).getVnombreimg().
				substring(0, vimagenes.get(i).getVnombreimg().lastIndexOf('.')) + "-Escalada.png";

				BufferedImage vimgout = 
					vimagenes.get(i).getVrgb().
					matrizescalar(porcentajeh, porcentajev, filtro);

				aumentacontvent();

				VentanaImg imgout = new VentanaImg(vimagenes.get(i).getVrgb(), vimgout, nombreimgout, cont_vent);

				nuevaventana(imgout);


			}
		}	

	}

	//Mostrar informacion de la imagen

	public void mostrarinformacion(){


		DecimalFormat df = new DecimalFormat("0.00"); 
		for (int i=0; i<vimagenes.size(); i++){
			if (vimagenes.get(i).isSelected()){
				JOptionPane.showMessageDialog(null, "Formato: png" + "\n"
						+ "Dimensiones: " + vimagenes.get(i).getVimg().getWidth() + "x" 
						+ vimagenes.get(i).getVimg().getHeight() + "\n"
						+ "Rango de valores: " + "[" + vimagenes.get(i).getHistograma().getMincolor() + ","
						+ vimagenes.get(i).getHistograma().getMaxcolor() + "]" + "\n"
						+ "Brillo: " + df.format(vimagenes.get(i).getBrillo()) + "\n"
						+ "Contraste: " + df.format(vimagenes.get(i).getContraste()) + "\n"
						+ "Entropia: " + df.format(vimagenes.get(i).entropia()) + "\n");

			}
		}
	}
	
	public void acercade(){
		JOptionPane.showMessageDialog(null, "<html><h2>TonoVision</h2></html>\n\nNoelia Rodriguez Martin\nJose Antonio Rodriguez Leandro\n\n" +
		"\nEl nombre de nuestra aplicacion proviene de la primera silaba de nuestros nombres, Tony y Noelia.");
	}


	public void aumentacontvent(){
		setCont_vent(getCont_vent()+30);
		if (getCont_vent()>300)
			setCont_vent(0);
	}

	public int getCont_vent() {
		return cont_vent;
	}

	public void setCont_vent(int cont_vent) {
		this.cont_vent = cont_vent;
	}

	public boolean isCuadrado() {
		return cuadrado;
	}

	public void setCuadrado(boolean cuadrado) {
		Ventana.cuadrado = cuadrado;
	}

	public boolean isLinea() {
		return linea;
	}

	public void setLinea(boolean linea) {
		Ventana.linea = linea;
	}

	public static Color getColoractual() {
		return coloractual;
	}

	public static void setColoractual(Color coloractual) {
		Ventana.coloractual = coloractual;
	}
	
	







}