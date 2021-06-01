package GDF;

import java.util.ArrayList;

public class AllPath {
    public AllPath(Graph graph) {
        super();
        this.graph = graph;
    }

    private Graph graph;
    private boolean[] visited;
    private ArrayList<Integer> tempPath; // luu tam thoi
    private ArrayList<ArrayList<Integer>> Path = new ArrayList<>(); //luu tat ca duong di
    private int startVertex, endVertex;
    private ArrayList<String> path = new ArrayList<>();

    public ArrayList<ArrayList<Integer>> getPath() {
        return Path;
    }

    public void initialize() {
        visited = new boolean[graph.numberVertex()];
        tempPath = new ArrayList<>();
        for(int i = 0; i<graph.numberVertex(); i++)
            visited[i] = false;
        visited[startVertex] = true;
        tempPath.add(startVertex);
    }

    public void setPath(ArrayList<ArrayList<Integer>> path) {
        Path = path;
    }

    public void PathFound() {
        if(tempPath.size() > 1 && tempPath.get(tempPath.size() - 1) == endVertex) {
            Path.add(tempPath);

            StringBuffer temppath = new StringBuffer();
            //temppath.append("[").append(Path.size()).append("]: ");
            for (Integer i : tempPath) {
                temppath.append(i).append(" -> ");
            }
            temppath.delete(temppath.length() - 4, temppath.length());
            path.add(String.valueOf(temppath));
        }
    }



    public ArrayList<String> getPathLog() {
        return path;
    }

    public void setPathLog() {
        this.path = new ArrayList<>();
    }

    // duyet ta ca duong di
    public void TRY(int soCanh) {
        if (tempPath.get(soCanh-1) == endVertex || soCanh > graph.getEdgeList().size()) {
            PathFound();
        } else {
            if (graph.getVertex(tempPath.get(soCanh-1)).getAdjacentVertices().size() == 0) {
                PathFound();
            } else {
                for (Vertex v : graph.getVertex(tempPath.get(soCanh - 1)).getAdjacentVertices()) {
                    if (visited[v.getID()] == false) {
                        visited[v.getID()] = true;
                        tempPath.add(v.getID());
                        TRY(soCanh + 1);
                        visited[v.getID()] = false;
                        tempPath.remove(tempPath.size() - 1);
                    }
                }
            }
        }
    }

    public int getStartVertex() {
        return startVertex;
    }

    public void setStartVertex(int startVertex) {
        this.startVertex = startVertex;
    }

    public int getEndVertex() {
        return endVertex;
    }

    public void setEndVertex(int endVertex) {
        this.endVertex = endVertex;
    }


}
