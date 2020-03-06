// Owen Reid 100694494 March 5th 2020

package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        Pane pane = new Pane();

        Circle circ = new Circle(100, 100, 75); // intial circle
        pane.getChildren().add(circ);

        circ.setFill(Color.TRANSPARENT);
        circ.setStroke(Color.BLACK);

        Circle[] points = new Circle[3]; // array of points on circle
        Line[] lines = new Line[3]; // array of lines connected to points (creates angles)
        Text[] texts = new Text[3]; // array of the degrees that will be shown next to points

        for (int i = 0; i < points.length; i++) { // loops over created arrays to create the objects

            texts[i] = new Text();
            points[i] = new Circle(0, 0, 5); // intial points size

            points[i].setStroke(Color.BLACK);
            points[i].setFill(Color.RED);

            final int index = i; // the variable used in lambda expression below must be final

            points[i].setOnMouseDragged(e -> { // mouse event action to move the points around thus creating new angles

                double radian = Math.atan2(e.getY() - circ.getCenterY(), e.getX() - circ.getCenterX());
                double x = circ.getCenterX() + circ.getRadius() * Math.cos(radian);
                double y = circ.getCenterY() + circ.getRadius() * Math.sin(radian);
                points[index].setCenterX(x);
                points[index].setCenterY(y);
                moveThePoints(lines, points, texts);
            });

        }

        // this creates the angles at predetirmined spots on the circle,
        // uses equations to convert angles into proper degrees
        double angle = 180;
        double x = circ.getCenterX() + circ.getRadius() * Math.cos(Math.toRadians(angle));
        double y = circ.getCenterY() + circ.getRadius() * Math.sin(Math.toRadians(angle));

        double angle1 = 280;
        double x1 = circ.getCenterX() + circ.getRadius() * Math.cos(Math.toRadians(angle1));
        double y1 = circ.getCenterY() + circ.getRadius() * Math.sin(Math.toRadians(angle1));

        double angle2 = 20;
        double x2 = circ.getCenterX() + circ.getRadius() * Math.cos(Math.toRadians(angle2));
        double y2 = circ.getCenterY() + circ.getRadius() * Math.sin(Math.toRadians(angle2));

        // sets the points
        points[0].setCenterX(x);
        points[0].setCenterY(y);
        points[1].setCenterX(x1);
        points[1].setCenterY(y1);
        points[2].setCenterX(x2);
        points[2].setCenterY(y2);

        // creats the lines
        for (int i = 0; i < lines.length; i++) {
            int cIndex2 = (i + 1 >= points.length) ? 0 : i + 1;
            lines[i] = new Line(
                    points[i].getCenterX(), points[i].getCenterY(),
                    points[cIndex2].getCenterX(), points[cIndex2].getCenterY());

        }

        moveThePoints(lines, points, texts);
        pane.getChildren().addAll(lines);
        pane.getChildren().addAll(texts);
        pane.getChildren().addAll(points);
        primaryStage.setScene(new Scene(pane, 200, 200));
        primaryStage.setTitle("Question 3");
        primaryStage.show();
    }

    // This function allows for the points to be moved with the lines reacting to the point movement
    private void moveThePoints(Line[] line, Circle[] point, Text[] angles) {
        for (int i = 0; i < line.length; i++) {

            int cIndex2 = (i + 1 >= point.length) ? 0 : i + 1;
            line[i].setStartX(point[i].getCenterX());
            line[i].setStartY(point[i].getCenterY());
            line[i].setEndX(point[cIndex2].getCenterX());
            line[i].setEndY(point[cIndex2].getCenterY());
            angles[i].setX(point[i].getCenterX() + 5);
            angles[i].setY(point[i].getCenterY() - 5);

        }

        // uses equation given in question to display the angle on the point after being moved
        double a = Math.sqrt((line[0].getStartX() - line[0].getEndX()) * (line[0].getStartX() - line[0].getEndX()) +
                (line[0].getStartY() - line[0].getEndY()) * (line[0].getStartY() - line[0].getEndY()));

        double b = Math.sqrt((line[1].getStartX() - line[1].getEndX()) * (line[1].getStartX() - line[1].getEndX()) +
                (line[1].getStartY() - line[1].getEndY()) * (line[1].getStartY() - line[1].getEndY()));

        double c = Math.sqrt((line[2].getStartX() - line[2].getEndX()) * (line[2].getStartX() - line[2].getEndX()) +
                (line[2].getStartY() - line[2].getEndY()) * (line[2].getStartY() - line[2].getEndY()));

        double A = Math.toDegrees(Math.acos((a * a - b * b - c * c) / (-2 * b * c)));
        angles[0].setText(String.format("%.0f", A));

        double B = Math.toDegrees(Math.acos((b * b - a * a - c * c) / (-2 * a * c)));
        angles[1].setText(String.format("%.0f", B));

        double C = Math.toDegrees(Math.acos((c * c - b * b - a * a) / (-2 * a * b)));
        angles[2].setText(String.format("%.0f", C));

    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}