package gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
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
        System.out.println("onMenuItemAboutAction");
    }

    @FXML
    public void onMenuItemAboutProductsAction(){
        System.out.println("onMenuItemAboutProductsAction");
    }

    @FXML
    public void menuItemAboutTheCompanyAction(){
        System.out.println("menuItemAboutTheCompanyAction");
    }

    @FXML
    public void menuItemContactAction(){
        System.out.println("menuItemContact");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
