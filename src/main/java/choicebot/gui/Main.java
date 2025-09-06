package choicebot.gui;

import java.io.IOException;

import choicebot.ChoiceBot;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private final ChoiceBot choicebot = new ChoiceBot("data/tasks.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setChoiceBot(choicebot); // inject the ChoiceBot instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
