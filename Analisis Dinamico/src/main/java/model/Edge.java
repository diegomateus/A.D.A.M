package model;

public class Edge {
    public String source;
    public String target;
    public String label;
    public String type;

    public Edge(String source, String target, String label, String type) {
        this.source = source;
        this.target = target;
        this.label = label;
        this.type = type;
    }
}
