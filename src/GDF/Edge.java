package GDF;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.shape.Polyline;

public class Edge extends Group {
    private Polyline line = new Polyline();
    private Polyline head = new Polyline();
    private SimpleDoubleProperty x1 = new SimpleDoubleProperty();
    private SimpleDoubleProperty y1 = new SimpleDoubleProperty();
    private SimpleDoubleProperty x2 = new SimpleDoubleProperty();
    private SimpleDoubleProperty y2 = new SimpleDoubleProperty();
    private SimpleBooleanProperty headVisible = new SimpleBooleanProperty(true);
    private final double LINE_SCALAR = 40,
                         HEAD_ANGLE = Math.toRadians(40),
                         HEAD_LENGTH = 10;

    int start, end;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public Edge(double x1, double y1, double x2, double y2) {
        this.x1.set(x1);
        this.y1.set(y1);
        this.x2.set(x2);
        this.y2.set(y2);

        getChildren().addAll(line,head);

        for(SimpleDoubleProperty s: new SimpleDoubleProperty[]{this.x1,this.y1,this.x2,this.y2}) {
            s.addListener((l,o,n) -> update());
        }

        setUpStyleClassStructure();
        head.visibleProperty().bind(headVisible);
        update();
    }

    public void setUpStyleClassStructure() {
        line.getStyleClass().setAll("edge");
        head.getStyleClass().setAll("edge");
        head.getStyleClass().add("edge-direct");
    }

    private void update() {
        double[] start = scale(x1.get(), y1.get(), x2.get(), y2.get());
        double[] end = scale(x2.get(), y2.get(), x1.get(), y1.get());
        double x1 = start[0];
        double y1 = start[1];
        double x2 = end[0];
        double y2 = end[1];

        line.getPoints().setAll(x1,y1,x2,y2);

        double theta = Math.atan2(y2 - y1, x2 - x1);
        double x = x2 - Math.cos(theta - HEAD_ANGLE) * HEAD_LENGTH;
        double y = y2 - Math.sin(theta - HEAD_ANGLE) * HEAD_LENGTH;
        head.getPoints().setAll(x,y,x2,y2);
        x = x2 - Math.cos(theta + HEAD_ANGLE) * HEAD_LENGTH;
        y = y2 - Math.sin(theta + HEAD_ANGLE) * HEAD_LENGTH;
        head.getPoints().addAll(x,y);
    }

    private double[] scale(double x1, double y1, double x2, double y2) {
        double theta = Math.atan2(y2-y1, x2-x1);
        return new double[] {
                x1 + Math.cos(theta) * LINE_SCALAR,
                y1 + Math.sin(theta) * LINE_SCALAR
        };
    }

    public double getX1() {
        return x1.get();
    }

    public SimpleDoubleProperty x1Property() {
        return x1;
    }

    public void setX1(double x1) {
        this.x1.set(x1);
    }

    public double getY1() {
        return y1.get();
    }

    public SimpleDoubleProperty y1Property() {
        return y1;
    }

    public void setY1(double y1) {
        this.y1.set(y1);
    }

    public double getX2() {
        return x2.get();
    }

    public SimpleDoubleProperty x2Property() {
        return x2;
    }

    public void setX2(double x2) {
        this.x2.set(x2);
    }

    public double getY2() {
        return y2.get();
    }

    public SimpleDoubleProperty y2Property() {
        return y2;
    }

    public void setY2(double y2) {
        this.y2.set(y2);
    }

    public boolean isHeadVisible() {
        return headVisible.get();
    }

    public SimpleBooleanProperty headVisibleProperty() {
        return headVisible;
    }

    public void setHeadVisible(boolean headVisible) {
        this.headVisible.set(headVisible);
    }
}