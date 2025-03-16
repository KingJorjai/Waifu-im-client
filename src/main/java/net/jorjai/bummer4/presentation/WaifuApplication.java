package net.jorjai.bummer4.presentation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class WaifuApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(WaifuApplication.class.getResource("waifu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        WaifuController controller = fxmlLoader.getController();
        stage.setTitle("Waifu.im - Client");
        stage.setScene(scene);
        stage.setMinHeight(scene.getHeight());
        stage.setMinWidth(scene.getWidth());

        // Make the image fit the window
        ImageView imageView = controller.getImageView();
        stage.heightProperty().addListener((observable, oldValue, newValue) -> {
            imageView.setFitHeight(stage.getHeight());
            imageView.setFitWidth(stage.getWidth());
        });
        stage.widthProperty().addListener((observable, oldValue, newValue) -> {
            imageView.setFitHeight(stage.getHeight());
            imageView.setFitWidth(stage.getWidth());
        });

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}