package GDF;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class DFS_BFS {

    private Graph graph;
    private boolean visited[], done = false;
    private Timeline visualizer;
    private boolean isRunning = false;
    private Stack<Integer> stack;
    private Queue<Integer> queue;

    public DFS_BFS(Graph g){
        graph = g;
    }

    public Timeline getVisualizer() {
        return visualizer;
    }

    public void setVisualizer(Timeline visualizer) {
        this.visualizer = visualizer;
    }

    public boolean IsRunning() {
        return isRunning;
    }

    public void runBFS(int startNode) {
        isRunning = true;
        Queue<Integer> queue = new LinkedList<>();
        visited = new boolean[graph.numberVertex()];
        graph.setDisable(true);

        queue.add(startNode);
        queue.add(startNode);

        KeyFrame bfsKeyFrame = new KeyFrame(Duration.seconds(1.5), e -> {
            if(queue.size() > 1){
                int n = (int) queue.poll();
                graph.getVertex(n).GetShape().setStyle("-fx-background-color: #ff6ebe; -fx-text-fill: white;");
                graph.getVertex(n).GetShape().setPrefSize(50,50);
                int currentNode = (int) queue.peek();
                visited[currentNode] = true;

                graph.getVertex(currentNode).GetShape().setStyle("-fx-background-color: #63ff8e; -fx-text-fill: white; -fx-background-radius: 30;");
                graph.getVertex(currentNode).GetShape().setPrefSize(60,60);

                for(Vertex v: graph.getAdjacentVertices(currentNode)){
                    if(!visited[v.getID()]){
                        visited[v.getID()] = true;
                        queue.add(v.getID());
                        v.GetShape().setStyle("-fx-background-color: gray");
                    }
                }
            }
            else{
                graph.getVertex((int) queue.peek()).GetShape().setStyle("-fx-background-color: #ff6ebe; -fx-text-fill: white;");
                graph.getVertex((int) queue.peek()).GetShape().setPrefSize(50,50);
                done();
                isRunning = false;
            }
        });
        visualizer = new Timeline(bfsKeyFrame);
        visualizer.setCycleCount(Animation.INDEFINITE);
        visualizer.setAutoReverse(false);
        visualizer.play();

    }

    public void runDFS(int startNode){
        isRunning = true;
        graph.reset();
        stack = new Stack<>();
        visited = new boolean[graph.numberVertex()];
        graph.setDisable(true);
        stack.push(startNode);

        KeyFrame dfsKeyFrame = new KeyFrame(Duration.seconds(1), e -> {
            if (!stack.isEmpty()) {
                int currentNode = stack.peek();
                visited[currentNode] = true;
                graph.getVertex(currentNode).GetShape().setPrefSize(60, 60);

                if (stack.size() > 1) {
                    graph.getVertex(stack.get(stack.size() - 2)).GetShape().setPrefSize(50,50);
                }
                int i;
                for (i = 0; i < graph.getAdjacentVertices(currentNode).size(); i++) {
                    Vertex v = graph.getAdjacentVertices(currentNode).get(i);
                    if (!visited[v.getID()]) {
                        stack.push(v.getID());
                        graph.getVertex(currentNode).GetShape().setStyle("-fx-background-color: #63ff8e; -fx-text-fill: white; -fx-background-radius: 30;");
                        break;
                    }
                }
                if (i == graph.getAdjacentVertices(currentNode).size()) {
                    int node = stack.pop();
                    graph.getVertex(node).GetShape().setStyle("-fx-background-color: #ff6ebe; -fx-text-fill: white;");
                    graph.getVertex(currentNode).GetShape().setPrefSize(50, 50);
                }
            } else {
                done();
            }
        });
        visualizer = new Timeline(dfsKeyFrame);
        visualizer.setCycleCount(Animation.INDEFINITE);
        visualizer.play();
    }

    void stop() {
        graph.setDisable(false);
        graph.reset();
        visualizer.stop();
        isRunning = false;
    }

    void done() {
        if(isRunning){
            graph.setDisable(false);
            isRunning = false;
            System.out.println("Done. See path on Path log.");
            visualizer.stop();
        }
    }
}
