package gui;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Product;
import javafx.scene.control.TableColumn;
import java.net.URL;
import java.util.ResourceBundle;

public class ProductsListController implements Initializable {

    @FXML
    private TableView<Product> tableViewProduct;
    @FXML
    private TableColumn<Product, Integer> tableColumnId;

    @FXML
    private TableColumn<Product, String> tableColumnName;

    @FXML
    private TableColumn<Product, Double> tableColumnPrice;

    @FXML
    private TableColumn<Product, Integer> tableColumnStock;

    @FXML
    private Button btNew;

    @FXML
    public void onBtNewAction(){
        System.out.println("onBtNewAction");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeNodes();
    }

    private void initializeNodes() {
        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableColumnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        tableColumnStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        // Faz com que o tableView acompanhe o tamanho da janela.
        Stage stage = (Stage) Main.getMainScene().getWindow();
        tableViewProduct.prefHeightProperty().bind(stage.heightProperty());
    }
}
