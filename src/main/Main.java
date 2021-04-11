package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;

import com.google.gson.Gson;

import processing.core.PApplet;

public class Main extends PApplet {

	int xBolita = -1000;
	int yBolita = -1000;
	int balaposx;
	int balaposy;

	private Avatar player1;
	private Avatar player2;

	private TCPConnectionP1 conexionJ1;
	private TCPConnectionP2 conexionJ2;
	
	private ArrayList<Balas> balasP1;
	private ArrayList<Balas> balasP2;

	public static void main(String[] args) {
		PApplet.main("main.Main");
	}

	public void settings() {
		size(500, 500);
	}

	public void setup() {
		conexionJ1 = new TCPConnectionP1();
		conexionJ1.setMain(this);
		conexionJ1.start();

		conexionJ2 = new TCPConnectionP2();
		conexionJ2.setMain(this);
		conexionJ2.start();

		player1 = new Avatar(this, 100, 100, color(255, 0, 0));
		player2 = new Avatar(this, 400, 400, color(0, 0, 255));
		balasP1 = new ArrayList<Balas>();
		balasP2 = new ArrayList<Balas>();
	}

	public void draw() {
		background(255, 255, 255);
		fill(255, 0, 0);
		noStroke();
		ellipse(xBolita, yBolita, 50, 50);

		player1.pintar();
		player2.pintar();
		
}

	public void notificar(Coordenada c, Object obj) {

		if (obj instanceof TCPConnectionP1) {
			
			System.out.println("JUGADOR 1:" + c.getAccion());
			
			switch (c.getAccion()) {

			case "DOWN":
				player1.activateDown();
				break;
			case "DOWNSTOP":
				player1.desactivateDown();
				break;
			case "UP":
				player1.activateUp();
				break;
			case "UPSTOP":
				player1.desactivateUp();
				break;
			case "LEFT":
				player1.activateLeft();
				break;
			case "LEFTSTOP":
				player1.desactivateLeft();
				break;
			case "RIGHT":
				player1.activateRight();
				break;
			case "RIGHTSTOP":
				player1.desactivateRight();
				break;

			}

		}

		else if (obj instanceof TCPConnectionP2) {
			
			System.out.println("JUGADOR 2:" + c.getAccion());
			
			switch (c.getAccion()) {

			case "DOWN":
				player2.activateDown();
				break;
			case "DOWNSTOP":
				player2.desactivateDown();
				break;
			case "UP":
				player2.activateUp();
				break;
			case "UPSTOP":
				player2.desactivateUp();
				break;
			case "LEFT":
				player2.activateLeft();
				break;
			case "LEFTSTOP":
				player2.desactivateLeft();
				break;
			case "RIGHT":
				player2.activateRight();
				break;
			case "RIGHTSTOP":
				player2.desactivateRight();
				break;

			}
		}

	}
	
	public void disparar(Disparo d, Object obj) {
		
		if (obj instanceof TCPConnectionP1) {
			
			System.out.println("JUGADOR 1:" + d.getDisparar());
			
			balaposx = player1.getX();
			balaposy = player1.getY();
			
			balasP1.add(new Balas(this, balaposx, balaposy));
			
		}
		
	}

}