package datastructures.graphs.directed.sssp;

public class Node {
    private int value;
    private double distance;

    public Node(int value, double distance) {
        this.value = value;
        this.distance = distance;
    }

    public int getValue() {
        return value;
    }

    public double getDistance() {
        return distance;
    }
}
