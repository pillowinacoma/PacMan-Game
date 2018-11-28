package Model;

public class Point {
	private int i;
	private int j;
	public Point(int i, int j) {
		super();
		this.i = i;
		this.j = j;
	}
	public int getI() {
		return i;
	}
	public void setI(int i) {
		this.i = i;
	}
	public int getJ() {
		return j;
	}
	public void setJ(int j) {
		this.j = j;
	}
	public String toString() {
		return "("+i+","+j+")";
	}
}
