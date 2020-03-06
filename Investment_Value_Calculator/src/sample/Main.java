// Owen Reid 100694494 March 5th 2020
package sample;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {
    TextField invamt = new TextField(); // Set variables outside of functions to be able to use in both functions
    TextField yrs = new TextField();
    TextField air = new TextField();
    TextField futval = new TextField();

    @Override
    public void start(Stage primaryStage) {

        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        pane.setHgap(5.5);
        pane.setVgap(5.5);

        // Set lables and textfields with correct spacing
        pane.add(new Label("Investment Amount"), 0, 0);
        pane.add(invamt, 1, 0);
        pane.add(new Label("Years"), 0, 1);
        pane.add(yrs, 1, 1);
        pane.add(new Label("Annual Interest Rate"), 0, 2);
        pane.add(air, 1, 2);
        pane.add(new Label("Future value"), 0, 3);
        pane.add(futval, 1, 3);
        Button btAdd = new Button("Calculate");
        pane.add(btAdd, 1, 4);
        GridPane.setHalignment(btAdd, HPos.RIGHT);

        // Button event
        btAdd.setOnAction(e -> calcFutVal());


        Scene scene = new Scene(pane);
        primaryStage.setTitle("ShowGridPane");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // function to calcualte the future value of the investment using given equation
    private void calcFutVal() {
        double investmentAmount = Double.parseDouble(invamt.getText());
        int years = Integer.parseInt(yrs.getText());
        double monthlyInterestRate = Double.parseDouble(air.getText()) / 1200;
        futval.setText(String.format("%.2f", investmentAmount * Math.pow(1 + monthlyInterestRate, years * 12)));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
