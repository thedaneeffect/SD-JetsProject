package com.skilldistillery.app;

public abstract class Jet {

	private String model;
	private double speed; // in mph
	private double range; // in miles
	private int cost; // in 1 USD

	public Jet(String model, double speed, double range, int cost) {
		super();
		this.model = model;
		this.speed = speed;
		this.range = range;
		this.cost = cost;
	}

	public String getModel() {
		return model;
	}

	public double getSpeed() {
		return speed;
	}

	public double getSpeedMach() {
		return speed / 767.269;
	}

	public double getRange() {
		return range;
	}

	public double getRangeHours() {
		return range / speed;
	}

	public int getCost() {
		return cost;
	}

	public void fly() {
		System.out.println(String.format("A %s flies for %.3f hours (%.3f miles) and returns for more fuel.", model,
				getRangeHours(), range));
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(model);
		sb.append(": ");
		sb.append("Speed=").append(String.format("%.3f mph", speed));
		sb.append(", ");
		sb.append("Range=").append(String.format("%.3f miles (%.3f hours of flight)", range, getRangeHours()));
		sb.append(", ");
		sb.append("Price=").append(String.format("$%d", cost));
		return sb.toString();
	}

}
