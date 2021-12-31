package datastructures.graphs.directed.sssp;

public class StringEdge {
    private String from, to;
    private double cost;

    public StringEdge(String from, String to, double cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "from=" + from +
                ", to=" + to +
                ", cost=" + cost +
                '}';
    }
}
