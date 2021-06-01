package GDF;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainSceneController implements Initializable {
    @FXML
    private VBox side_bar;

    boolean isExpanded = false;
    private Stage stage;
    private Scene scene;
    private Parent root;
    public static String filePath = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void toDraw(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Draw.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void toAboutandHelp(MouseEvent event) throws IOException {
        Stage popup = new Stage();
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.setTitle("Save as");
        Parent root = FXMLLoader.load(getClass().getResource("About.fxml"));
        Scene scene = new Scene(root, 1000, 600);
        popup.setScene(scene);
        popup.show();
    }

    public void open(MouseEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll( new FileChooser.ExtensionFilter("Graph Path Finder Files", "*.gph"),
                                                  new FileChooser.ExtensionFilter("Text Files", "*txt"),
                                                  new FileChooser.ExtensionFilter("All Files","*gph", "*.txt")
        );
        File file = fileChooser.showOpenDialog(stage);
        if (file == null) return;
        filePath = file.getAbsolutePath();
        toDraw(event);
    }

    public void openRandom(MouseEvent event) throws IOException {
        int randomNumber = -1;
        while(randomNumber <= 0 || randomNumber > 6) {
            randomNumber = (int)(Math.random()*6 + 1);
        }
        filePath = "src/inputLucky/" + randomNumber + ".gph";
        toDraw(event);
    }


    @FXML
    private void expand_sidebar(MouseEvent event) {
        if (isExpanded) {
            side_bar.setPrefWidth(60);
            isExpanded = false;
        } else {
            side_bar.setPrefWidth(200);
            isExpanded = true;
        }
    }
}