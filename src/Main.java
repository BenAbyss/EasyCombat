import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;

public class Main extends Application {
    private static Stage mainStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("NPCBuilder.fxml"));
        primaryStage.setTitle("Window");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        mainStage = primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
