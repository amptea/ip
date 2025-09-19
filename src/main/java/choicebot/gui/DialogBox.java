package choicebot.gui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
    }

    /**
     * Creates dialog box for user, right aligned, green bubble, no avatar.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.setAlignment(Pos.TOP_RIGHT);
        db.displayPicture.setVisible(false);
        db.dialog.setStyle("-fx-background-color: #DCF8C6; -fx-background-radius: 15;"
                + "-fx-padding: 8 12 8 12; -fx-font-size: 14px; -fx-text-fill: #303030;");
        return db;
    }

    /**
     * Creates dialog box for bot, left aligned, white bubble with avatar.
     */
    public static DialogBox getChoiceBotDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.setAlignment(Pos.TOP_LEFT);
        db.dialog.setStyle("-fx-background-color: #FFFFFF; -fx-background-radius: 15;"
                + "-fx-padding: 8 12 8 12; -fx-font-size: 14px; -fx-text-fill: #303030;");
        return db;
    }
}
