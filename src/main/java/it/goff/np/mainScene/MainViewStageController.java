package it.goff.np.mainScene;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainViewStageController {
    @FXML
    private GridPane grid;

    @FXML
    private TextArea list;

    @FXML
    private void click(ActionEvent event) {
        Button btn = (Button)event.getSource();
        list.setText(btn.getText());
    }

    void startButton() {
        List<Button> btnList = Stream.generate(Button::new).limit(5).collect(Collectors.toList());
        btnList.forEach(f -> f.setText("Ciao"));
        grid.addRow(0, btnList.get(0));

    }
}