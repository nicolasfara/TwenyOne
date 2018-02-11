package it.goff.np.mainScene;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainViewStageController {

    @FXML private FlowPane flow;
    @FXML private TextArea list;

    @FXML
    public void initialize() {
        List<Button> btnList = Stream.generate(Button::new).limit(15).collect(Collectors.toList());
        btnList.forEach(btn -> btn.setText("Button"));
        flow.getChildren().addAll(btnList);
    }

    @FXML
    private void click(ActionEvent event) {
        Button btn = (Button)event.getSource();
        list.setText(btn.getText());
    }

    @FXML
    private void taxClick() {
        //TODO
    }



}