package jarvis.ui.layout;

import java.util.Objects;

import jarvis.Jarvis;
import jarvis.ui.message.MessageBox;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller class for MainWindow.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox chatContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Jarvis jarvis;

    private final Image userImage = new Image(Objects.requireNonNull(this
            .getClass()
            .getResourceAsStream("/images/User.jpg")));
    private final Image jarvisImage = new Image(Objects.requireNonNull(this
            .getClass()
            .getResourceAsStream("/images/Jarvis.jpg")));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(chatContainer.heightProperty());
        sendButton.getStyleClass().add("button");
    }

    public void setJarvis(Jarvis jarvis) {
        this.jarvis = jarvis;

        this.userInput.setText("Hi");
        this.handleUserInput();
    }

    @FXML
    private void handleUserInput() {
        if (userInput.getText().isBlank() || jarvis == null) {
            return;
        }

        String inputText = userInput.getText();
        String jarvisText = jarvis.getResponse(inputText);
        chatContainer.getChildren().addAll(
                new MessageBox(inputText, userImage),
                new MessageBox(jarvisText, jarvisImage).flip()
        );
        userInput.clear();
    }
}
