package datastructures.graphs;

import java.util.Objects;

public class Node {
    private final int index;
    private final int value;
    private final String name;

    public Node(int index, int value, String name) {
        this.index = index;
        this.value = value;
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node node)) return false;
        return getIndex() == node.getIndex() && getValue() == node.getValue() && getName().equals(node.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIndex(), getValue(), getName());
    }
}
