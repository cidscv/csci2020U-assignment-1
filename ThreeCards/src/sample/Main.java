// Owen Reid 100694494 March 5th 2020

package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.Random;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {

        Pane pane = new HBox(10);
        pane.setPadding(new Insets(5, 5, 5, 5));

        Random rand = new Random(); // Random generator class

        int rand_int1 = rand.nextInt((54 - 1) + 1) + 1; // Generates random number between 1 and 54 (bounds)

        Image card1 = new Image("image/Cards/" + rand_int1 + ".png"); // Chooses card based on random number picked
        pane.getChildren().add(new ImageView(card1));

        int rand_int2 = rand.nextInt((54 - 1) + 1) + 1; // same as above

        ImageView card2 = new ImageView("image/Cards/" + rand_int2 + ".png"); // same as above
        pane.getChildren().add(card2);

        int rand_int3 = rand.nextInt((54 - 1) + 1) + 1; // same as above

        ImageView card3 = new ImageView("image/Cards/" + rand_int3 + ".png"); // same as above
        pane.getChildren().add(card3);


        Scene scene = new Scene(pane);
        primaryStage.setTitle("Question #1");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
