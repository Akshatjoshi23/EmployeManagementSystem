package com.ems;

public class Manager extends Employee {
    private double bonus;

    public Manager(String name, int id, String department, double salary, double bonus) {
        super(name, id, "Manager", department, salary);
        this.bonus = bonus;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    @Override
    public String toString() {
        return String.format("%s, Bonus: %.2f", super.toString(), bonus);
    }
}
