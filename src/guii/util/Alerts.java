package guii.util;

import javafx.scene.control.Alert;

public class Alerts {
    /*
    Class built with the intention of throwing errors
    in case there is a problem in the program.
     */
    public static void showAlerts(String title, String header, String content, Alert.AlertType alertType){
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();
    }
}
