package main;

public class Balas {
	
	private Main main;
	private int x;
	private int y;
	
	public Balas (Main main, int x, int y) {
		
		this.main = main;
		this.x = x;
		this.y = y;
		
	}
	
	public void pintar() {
		
		main.fill(0,0,0);
		main.ellipse(this.x, this.y, 20, 20);
		
		this.x += 5;
		
	}

}
