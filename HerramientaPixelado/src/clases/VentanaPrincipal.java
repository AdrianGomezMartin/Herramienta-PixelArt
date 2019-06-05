package clases;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;


import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class VentanaPrincipal {
	JFrame ventana;
	JPanel panelGeneral, panelIzq, panelCentral, panelDer;
	JButton guardar, seleccionarColor;
	JColorChooser selectorColor;

	public final static Color COLOR_PRINCIPAL = new Color(66, 72, 128), COLOR_SECUNDARIO = new Color(209, 214, 255),
			COLOR_TERCIERO = new Color(133, 145, 255), COLOR_CUATERNARIO = new Color(105, 107, 128),
			COLOR_QUINTO = new Color(106, 115, 204);

	public VentanaPrincipal() {
		ventana = new JFrame("PixelArt V.0.0.1");
		ventana.setBounds(0, 0, 1920, 1080);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public void Inicializar() {
		ventana.setVisible(true);
		ventana.setBackground(COLOR_PRINCIPAL);
		ventana.setLayout(new GridBagLayout());
		panelGeneral = new JPanel(new GridBagLayout());
		panelIzq = new JPanel(new GridBagLayout());
		panelCentral = new JPanel(new GridBagLayout());
		panelDer = new JPanel(new GridBagLayout());
		colocarComponentes();
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
		opc.fill = GridBagConstraints.BOTH;
		panelIzq.setBackground(COLOR_SECUNDARIO);
		panelIzq.setBorder(BorderFactory.createLineBorder(COLOR_TERCIERO, 3));
		panelGeneral.add(panelIzq, opc);
		opc = new GridBagConstraints();
		opc.gridx = 1;
		opc.gridy = 0;
		opc.weightx = 2;
		opc.weighty = 1;
		opc.fill = GridBagConstraints.BOTH;
		panelCentral.setBackground(COLOR_SECUNDARIO);
		panelCentral.setBorder(BorderFactory.createLineBorder(COLOR_CUATERNARIO, 3));
		panelGeneral.add(panelCentral, opc);
		opc = new GridBagConstraints();
		opc.gridx = 2;
		opc.gridy = 0;
		opc.weightx = 1;
		opc.weighty = 1;
		opc.fill = GridBagConstraints.BOTH;
		panelDer.setBackground(COLOR_SECUNDARIO);
		panelDer.setBorder(BorderFactory.createLineBorder(COLOR_QUINTO, 3));
		panelGeneral.add(panelDer, opc);
		colocarCuadricula();
		selectorColor = new JColorChooser();
		panelIzq.add(selectorColor);
		guardar = new JButton("Guardar");
		panelDer.add(guardar);

	}

	private void colocarCuadricula() {
		GridBagConstraints opc;
		for (int i = 0; i < 64; i++) {
			for (int j = 0; j < 64; j++) {
				opc = new GridBagConstraints();
				opc.gridx = i;
				opc.gridy = j;
				opc.weightx = 1;
				opc.weighty = 1;
				opc.fill = GridBagConstraints.BOTH;
				JButton cuadrado = new JButton(" ");
				cuadrado.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
				cuadrado.setBackground(Color.WHITE);
				panelCentral.add(cuadrado, opc);
				cuadrado.addActionListener((e)->{
					cuadrado.setBackground(selectorColor.getColor());
				});
			}
		}
	}
}
