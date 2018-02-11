package it.goff.np.mainScene;

import it.goff.np.jsonParsingController.ButtonParser;
import it.goff.np.jsonParsingController.ButtonParserImpl;
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
    private ButtonParser parser;

    @FXML
    public void initialize() {
        parser = new ButtonParserImpl("src/main/resources/JSON/weapons.json");
        List<Button> btnList = Stream.generate(Button::new).limit(parser.size()).collect(Collectors.toList());
        List<String> str = parser.parseName().collect(Collectors.toList());
        for (int i = 0; i < parser.size(); i++) {
            btnList.get(i).setText(str.get(i));
        }
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