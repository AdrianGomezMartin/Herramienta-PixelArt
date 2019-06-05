package model;

import java.awt.Color;
import java.io.Serializable;
import java.util.Arrays;


import javax.swing.JButton;

public class Cuadrado extends JButton implements Serializable{

	private static final long serialVersionUID = 1L;
	private String nombre;
	private int posX, posY;
	private float[] rgb = new float[3];
	private Color color;
	
	public Cuadrado() {
		super();
	}

	public Cuadrado(String nombre, int posX, int posY, float[] rgb) {
		super();
		this.nombre = nombre;
		this.posX = posX;
		this.posY = posY;
		this.rgb = rgb;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public float[] getRgb() {
		return rgb;
	}

	public void setRgb(float[] rgb) {
		this.rgb = rgb;
	}
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	@Override
	public String toString() {
		return "Cuadrado [nombre=" + nombre + ", posX=" + posX + ", posY=" + posY + ", rgb=" + Arrays.toString(rgb)
				+ "]\n";
	}
}
