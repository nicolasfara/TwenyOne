package it.goff.np.mainScene;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class MainViewStageController {
    @FXML
    private TextArea list;

    @FXML
    private void click(ActionEvent event) {
        Button btn = (Button)event.getSource();
        list.setText(btn.getText());
    }
}