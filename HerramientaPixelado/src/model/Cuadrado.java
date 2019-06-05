package model;

import java.awt.Color;



public class Cuadrado {

	private String nombre;
	private int posX, posY;
	private Color color;

	public Cuadrado() {
		super();
	}


	public Cuadrado(String nombre, int posX, int posY, Color color) {
		super();
		this.nombre = nombre;
		this.posX = posX;
		this.posY = posY;
		this.color = color;
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


	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}


	@Override
	public String toString() {
		return "Cuadrado [nombre=" + nombre + ", posX=" + posX + ", posY=" + posY + ", color=" + color + "]";
	}


}
