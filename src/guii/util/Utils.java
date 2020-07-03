package guii.util;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class Utils {

    // Aces of stage control button
    public static Stage currentStage(ActionEvent event){
        return (Stage) ((Node)event.getSource()).getScene().getWindow();
    }
}
