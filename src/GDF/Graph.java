package GDF;

import java.util.ArrayList;
import javafx.scene.layout.Pane;

public class Graph {

    // Attributes
    private Pane displayPane;
    private ArrayList<Vertex> vertexList;
    private ArrayList<Edge> edgeList;


    // constructor
    public Graph(Pane displayPane, int n) {
        this.displayPane = displayPane;
        vertexList = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            vertexList.add(new Vertex(i));
        }
        edgeList = new ArrayList<>() ;
    }

    public Graph(Pane displayPane) {
        this(displayPane,0);
    }

    public int numberVertex() {
        return this.vertexList.size();
    }

    public Vertex getVertex(int i) {
        return vertexList.get(i);
    }

    public ArrayList<Vertex> getVertexList() {
        return vertexList;
    }

    public ArrayList<Edge> getEdgeList() {
        return edgeList;
    }

    //Create Edge
    void createEdge(Vertex u, Vertex v) {
        Edge line = new Edge(u.getLayoutX(), u.getLayoutY(), v.getLayoutX(), v.getLayoutY());
        line.x1Property().bind(u.GetShape().layoutXProperty());
        line.y1Property().bind(u.GetShape().layoutYProperty());
        line.x2Property().bind(v.GetShape().layoutXProperty());
        line.y2Property().bind(v.GetShape().layoutYProperty());

        line.setStart(u.getID());
        line.setEnd(v.getID());
        edgeList.add(line);
        u.addAdjacentVertex(v);
        System.out.println("Edge from " + u.getID() + " to " + v.getID() + " is created!");
        displayPane.getChildren().add(line);
    }

    void createEdge(int u, int v) {
        Vertex t1 = null,
               t2 = null;
        for (Vertex i : vertexList) {
            if (i.getID() == u) {
                t1 = i;
            } else if (i.getID() == v) {
                t2 = i;
            }
        }
        createEdge(t1,t2);
    }

    //Create Vertex and add to Adjacent List
    public void addVertex(Vertex v) {
        vertexList.add(v);
    }

    public void addVertex(double x, double y) {
        vertexList.add(new Vertex(numberVertex(), x, y));
    }

    public ArrayList<Vertex> getAdjacentVertices(int i){
        return vertexList.get(i).getAdjacentVertices();
    }

    @Override
    public String toString() {
        String str = "";
        for(int i=0; i<vertexList.size(); i++) {
            str += i + ": [";
            for(Vertex v: vertexList.get(i).getAdjacentVertices()) {
                str += v.getID() + "  ";
            }
            str += "]\n";
        }
        for(int i = 0; i < edgeList.size(); i++) {
            str += edgeList.get(i).getStart() + " -> " + edgeList.get(i).getEnd() + "\n";
        }
        return str;
    }

    public void setDisable(boolean status) {
        displayPane.setDisable(status);
    }

	public void reset() {
		for(int i = 0; i < vertexList.size(); i++) {
            vertexList.get(i).GetShape().setStyle("    -fx-font-size: 15;\n" +
                                  "    -fx-background-color: white;\n" +
                                  "    -fx-background-radius: 25;\n" +
                                  "    -fx-effect: dropshadow(three-pass-box, #000000, 6, 0.2, 0, 1);\n"
            );
            vertexList.get(i).GetShape().setOpacity(1);
            vertexList.get(i).GetShape().setPrefSize(50, 50);
		}

        for(int i = 0; i < edgeList.size(); i++) {
            edgeList.get(i).setOpacity(1);
        }
	}
}
