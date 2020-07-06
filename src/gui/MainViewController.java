package gui;

import application.Main;
import guii.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import modell.services.DepartmentService;
import modell.services.ProductsService;
import modell.services.SellerService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class MainViewController implements Initializable {

    @FXML
    private Button button;

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
    public void onMenuItemSellerAction() {
        loadView("/gui/SellerList.fxml", (SellerListController controller) ->{
            controller.setSellerService(new SellerService());
            controller.updateTableView();
        });
    }

    @FXML
    public void onMenuItemDepartmentAction() {
        loadView("/gui/DepartmentList.fxml", (DepartmentListController controller) ->{
            controller.setDepartmentService(new DepartmentService());
            controller.updateTableView();
        });
    }

    @FXML
    public void onMenuItemAboutAction() {
        loadView("/gui/About.fxml", x -> {});
    }

    @FXML
    public void onMenuItemAboutProductsAction() {
        loadView("/gui/ProductsList.fxml", (ProductsListController controller) -> {
            controller.setProductsService(new ProductsService());
            controller.updateTableView();
        });
    }


    @FXML
    public void menuItemAboutTheCompanyAction() {
        loadView("/gui/AboutCompany.fxml", x -> {});
    }

    @FXML
    public void menuItemContactAction() {
        loadView("/gui/Contacts.fxml", x -> {});
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /*
    This function has the final reason to load other windows from the main window,
    we use VBox as new windows and not Scene or Pane
     */
    private synchronized <T> void loadView(String absoluteName, Consumer<T> initializingAction) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
            VBox newVBox = loader.load();

            Scene mainScene = Main.getMainScene();

            VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();
            Node mainMenu = mainVBox.getChildren().get(0);
            mainVBox.getChildren().clear();
            mainVBox.getChildren().add(mainMenu);
            mainVBox.getChildren().addAll(newVBox.getChildren());

            T controller = loader.getController();
            initializingAction.accept(controller);
        } catch (IOException e) {
            Alerts.showAlerts("IOException", "Error loader view", e.getMessage(), Alert.AlertType.ERROR);
        }
    }
}