package gui;

import guii.util.Constraints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Department;

import java.net.URL;
import java.util.ResourceBundle;

public class DepartmentFormController implements Initializable {

    private Department entity;

    @FXML
    private TextField textId;

    @FXML
    private TextField textName;

    @FXML
    private Label labelErrorName;

    @FXML
    private Button btSave;

    @FXML
    private Button btCancel;

    public void setDepartment(Department entity){
        this.entity = entity;
    }

    @FXML
    public void onBtSaveAction(){
        System.out.println("onBtSaveAction");
    }

    @FXML
    public void onBtCancelAction(){
        System.out.println("onBtCancelAction");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeNodes();
    }

    private void initializeNodes(){
        Constraints.setTextFieldInteger(textId);
        Constraints.setTextFieldMaxLength(textName, 30);
    }

    public void updateFormData(){
        if(entity == null){
            throw new IllegalStateException("Entity was null");
        }
        textId.setText(String.valueOf(entity.getId()));
        textName.setText(String.valueOf(entity.getName()));
    }
}
