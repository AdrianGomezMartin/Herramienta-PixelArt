package clases;

import java.awt.Color;
import java.awt.Event;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.config.EmbeddedConfiguration;
import model.Cuadrado;

public class VentanaPrincipal {
	JFrame ventana;
	JPanel panelGeneral, panelIzq, panelCentral, panelBotones;
	JColorChooser selectorColor;
	JMenu menuArchivo;
	JMenuItem guardar, cargar, salir;
	Cuadrado cuadrados[][] = new Cuadrado[32][32];
	JButton botones[][] = new JButton[32][32];
	JDialog dialogoCarga;
	JComboBox<String> archivos;
	public final static Color COLOR_PRINCIPAL = new Color(66, 72, 128), COLOR_SECUNDARIO = new Color(209, 214, 255),
			COLOR_TERCIERO = new Color(133, 145, 255), COLOR_CUATERNARIO = new Color(105, 107, 128),
			COLOR_QUINTO = new Color(106, 115, 204);
	private String nombreArchivo = "";
	int i, j;

	public VentanaPrincipal() {
		ventana = new JFrame("PixelArt V.0.0.1");
		ventana.setBounds(0, 0, 1920, 1080);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void Inicializar() {
		ventana.setVisible(true);
		ventana.setJMenuBar(new JMenuBar());
		menuArchivo = new JMenu("Archivo");
		guardar = new JMenuItem("Guardar");
		guardar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.CTRL_MASK));
		cargar = new JMenuItem("Cargar");
		cargar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, Event.CTRL_MASK));
		salir = new JMenuItem("Salir");
		salir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, Event.CTRL_MASK));
		menuArchivo.add(guardar);
		menuArchivo.add(cargar);
		menuArchivo.addSeparator();
		menuArchivo.add(salir);
		ventana.getJMenuBar().add(menuArchivo);
		ventana.setBackground(COLOR_PRINCIPAL);
		ventana.setLayout(new GridBagLayout());
		panelGeneral = new JPanel(new GridBagLayout());
		panelIzq = new JPanel(new GridBagLayout());
		panelCentral = new JPanel(new GridBagLayout());
		colocarComponentes();
		ventana.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

	private void colocarComponentes() {
		GridBagConstraints opc = new GridBagConstraints();
		opc.gridx = 0;
		opc.gridy = 0;
		opc.weightx = 1;
		opc.weighty = 1;
		opc.fill = GridBagConstraints.BOTH;
		panelGeneral.setBackground(COLOR_PRINCIPAL);
		ventana.add(panelGeneral, opc);

		opc = new GridBagConstraints();
		opc.gridx = 0;
		opc.gridy = 0;
		opc.weightx = 1;
		opc.weighty = 1;
		opc.insets = new Insets(20, 20, 20, 20);
		opc.fill = GridBagConstraints.BOTH;
		panelIzq.setBackground(COLOR_SECUNDARIO);
		panelIzq.setBorder(BorderFactory.createLineBorder(COLOR_TERCIERO, 3));
		panelGeneral.add(panelIzq, opc);

		opc = new GridBagConstraints();
		opc.gridx = 1;
		opc.gridy = 0;
		opc.weightx = 2;
		opc.weighty = 1;
		opc.insets = new Insets(20, 20, 20, 20);
		opc.fill = GridBagConstraints.BOTH;
		panelCentral.setBackground(COLOR_SECUNDARIO);
		panelCentral.setBorder(BorderFactory.createLineBorder(COLOR_CUATERNARIO, 3));
		panelGeneral.add(panelCentral, opc);
		selectorColor = new JColorChooser(Color.BLACK);
		colocarCuadricula();
		opc = new GridBagConstraints();
		opc.gridx = 0;
		opc.gridy = 0;
		opc.insets = new Insets(10, 10, 10, 10);
		opc.fill = GridBagConstraints.BOTH;
		panelIzq.add(selectorColor, opc);
		// InicializarListeners();
	}

	private void colocarCuadricula() {
		panelBotones = new JPanel(new GridLayout(32, 32));
		GridBagConstraints opc = new GridBagConstraints();
		opc.gridy = 0;
		opc.gridx = 0;
		opc.weightx = 1;
		opc.weighty = 1;
		opc.fill = GridBagConstraints.BOTH;
		opc.insets = new Insets(15, 15, 15, 15);
		panelCentral.add(panelBotones, opc);
		panelBotones.setBackground(null);
		setNombreArchivo(JOptionPane.showInputDialog(ventana, "Introduzca el nombre del archivo",
				"Por favor Inserte un nombre a este mapa de bits", JOptionPane.INFORMATION_MESSAGE));
		for (int filas = 0; filas < 32; filas++) {
			for (int columnas = 0; columnas < 32; columnas++) {
				cuadrados[filas][columnas] = new Cuadrado();
				cuadrados[filas][columnas].setNombre(nombreArchivo);
				cuadrados[filas][columnas].setPosY(filas);
				cuadrados[filas][columnas].setPosX(columnas);
				botones[filas][columnas] = new JButton(" ");
				botones[filas][columnas].setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
				botones[filas][columnas].setBackground(Color.WHITE);
				panelBotones.add(botones[filas][columnas]);
			}
		}
		InicializarListeners();
	}

	private void InicializarListeners() {
		cargar.addActionListener((e) -> {
			CrearDialogo();
			JOptionPane.showMessageDialog(null, "Opción no configurada", "Pruebe más adelante",
					JOptionPane.WARNING_MESSAGE);
		});
		guardar.addActionListener((e) -> {
			GuadarPixelArt(cuadrados);
		});
		salir.addActionListener((e) -> {
			System.exit(0);
		});
		for (i = 0; i < botones.length; i++) {
			for (j = 0; j < botones[i].length; j++) {
				botones[i][j].addActionListener(new ActionListener() {
					private int i_anonima, j_anonima;
					{
						this.i_anonima = i;
						this.j_anonima = j;
					}

					@Override
					public void actionPerformed(ActionEvent arg0) {
						botones[i_anonima][j_anonima].setBackground(selectorColor.getColor());
						botones[i_anonima][j_anonima]
								.setBorder(BorderFactory.createLineBorder(selectorColor.getColor()));
						cuadrados[i_anonima][j_anonima].setColor(selectorColor.getColor());
					}
				});
			}
		}

	}

	private ObjectContainer ConectarDb4o(String fichero) {
		EmbeddedConfiguration configuration = Db4oEmbedded.newConfiguration();
		configuration.common().objectClass(Cuadrado.class).cascadeOnUpdate(true);
		configuration.common().updateDepth(5);
		configuration.common().activationDepth(5);
		configuration.common().objectClass(Cuadrado.class).objectField("nombre").indexed(true);
		ObjectContainer db = Db4oEmbedded.openFile(configuration, fichero);
		return db;
	}

	private void GuadarPixelArt(Cuadrado[][] cuadrados) {
		ObjectContainer oc = ConectarDb4o("archivos.yap");
		for (int i = 0; i < cuadrados.length; i++) {
			for (int j = 0; j < cuadrados.length; j++) {
				if (cuadrados[i][j].getColor() != null) {
					oc.store(cuadrados[i][j]);
					oc.commit();
				}
			}
		}
		oc.close();
	}
	private void CrearDialogo() {
		dialogoCarga = new JDialog(ventana);
		dialogoCarga.setBounds(0, 0, 500, 500);
		dialogoCarga.setBackground(COLOR_SECUNDARIO);
		dialogoCarga.setLayout(new GridBagLayout());
		GridBagConstraints opc= new GridBagConstraints();
		opc.gridx = 0;
		opc.gridy = 0;
		opc.weightx = 1;
		opc.weighty = 1;
		opc.fill = GridBagConstraints.BOTH;
		archivos = new JComboBox<String>();
		dialogoCarga.add(archivos);
		ObjectContainer oc = ConectarDb4o("archivos.yap");
		ObjectSet<Cuadrado> osc = oc.queryByExample(new Cuadrado());
		while (osc.hasNext()) {
			for (int i = 0; i < archivos.getItemCount(); i++) {
				if (osc.next().getNombre()!= archivos.getItemAt(i))
					archivos.addItem(osc.next().getNombre());
			}
		}
		oc.close();
		dialogoCarga.setVisible(true);

	}
	private void CargarPixelArt() {
		ObjectContainer oc = ConectarDb4o("archivos.yap");
		ObjectSet<Cuadrado> osc = oc.queryByExample(new Cuadrado());
		dialogoCarga.setVisible(true);
		while (osc.hasNext()) {
			if (osc.next().getNombre()== archivos.getSelectedItem()) {
				
			}
		}
		oc.close();
	}
	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
}
