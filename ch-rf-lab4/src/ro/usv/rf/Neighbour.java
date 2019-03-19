package ro.usv.rf;

public class Neighbour {
	public Neighbour(int position, double value, double class_num)
	{
		this.position = position;
		this.value = value;
		this.class_num = class_num;
		
	}
	private int position = 0;
	private double value = 0;
	private double class_num = 0;
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public double getClass_num() {
		return class_num;
	}
	public void setClass_num(double class_num) {
		this.class_num = class_num;
	}
}
