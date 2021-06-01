package GDF;

import java.net.URL;
import java.util.*;
import java.io.*;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

public class InformationSceneController implements Initializable {

    @FXML
    private TextArea helpTextArea,
                     aboutTextArea;

    public List<String> read(File file) {
        List<String> lines = new ArrayList<String>();
        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            br.close();
        } catch (IOException ex) {

        }

        return lines;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        helpTextArea.clear();
        aboutTextArea.clear();

        for (String line : read(new File("src/file/help.md")) ) {
            helpTextArea.appendText(line + "\n");
        }

        for (String line : read(new File("src/file/about.md")) ) {
            aboutTextArea.appendText(line + "\n");
        }

    }
}
