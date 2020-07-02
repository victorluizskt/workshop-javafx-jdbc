package gui;

import application.Main;
import guii.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

    @FXML
    private MenuItem menuItemSeller;

    @FXML
    private MenuItem menuItemDepartment;

    @FXML
    private MenuItem menuItemAbout;

    @FXML
    private MenuItem menuItemProducts;

    @FXML
    private MenuItem menuItemAboutTheCompany;

    @FXML
    private MenuItem menuItemContact;

    @FXML
    public void onMenuItemSellerAction(){
        System.out.println("onMenuItemSellerAction");
    }

    @FXML
    public void onMenuItemDepartmentAction(){
        System.out.println("onMenuItemDepartmentAction");
    }

    @FXML
    public void onMenuItemAboutAction(){
        loadView("/gui/About.fxml");
    }

    @FXML
    public void onMenuItemAboutProductsAction(){
        System.out.println("onMenuItemAboutProductsAction");
    }

    @FXML
    public void menuItemAboutTheCompanyAction(){
        loadView("/gui/AboutCompany.fxml");
    }

    @FXML
    public void menuItemContactAction(){
        System.out.println("menuItemContact");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    private synchronized void loadView(String absoluteName){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
            VBox newVBox = loader.load();

            Scene mainScene = Main.getMainScene();

            VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();
            Node mainMenu = mainVBox.getChildren().get(0);
            mainVBox.getChildren().clear();
            mainVBox.getChildren().add(mainMenu);
            mainVBox.getChildren().addAll(newVBox.getChildren());

        } catch (IOException e){
            Alerts.showAlerts("IOException", "Error loader view", e.getMessage(), Alert.AlertType.ERROR);
        }
    }



}
