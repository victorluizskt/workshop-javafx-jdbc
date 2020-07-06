package gui;

import db.DbException;
import guii.util.Alerts;
import guii.util.Constraints;
import guii.util.Utils;
import guiii.listeners.DataChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;
import model.entities.Department;
import model.entities.Seller;
import model.exceptions.ValidationException;
import modell.services.DepartmentService;
import modell.services.SellerService;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class SellerFormController implements Initializable {

    private Seller entity;

    private SellerService service;

    private DepartmentService departmentService;

    private List<DataChangeListener> dataChangeListeners = new ArrayList<>();

    @FXML
    private TextField textId;

    @FXML
    private TextField textName;

    @FXML
    private TextField textEmail;

    @FXML
    private DatePicker datePickerBirthDate;

    @FXML
    private TextField textBaseSalary;
    @FXML
    private ComboBox<Department> comboBoxDepartment;

    @FXML
    private Label labelErrorName;

    @FXML
    private Label labelErrorEmail;

    @FXML
    private Label labelErrorBirthDate;

    @FXML
    private Label labelErrorBaseSalary;

    @FXML
    private Button btSave;

    @FXML
    private Button btCancel;

    private ObservableList<Department> obsList;

    public void setSeller(Seller entity){
        this.entity = entity;
    }

    public void setSellerServices(SellerService service, DepartmentService departmentService)
    {this.service = service;
    this.departmentService = departmentService;}

    public void subscribeDataChangeListener(DataChangeListener listener){
        dataChangeListeners.add(listener);
    }

    @FXML
    public void onBtSaveAction(ActionEvent event){
        if(entity == null){
            throw new IllegalStateException("Entity was null");
        }
        if(service == null) {
            throw new IllegalStateException("Service was null");
        }
        try {
            entity = getFormData();
            service.saveOrUpdate(entity);
            notifyDataChangeListeners();
            Utils.currentStage(event).close();
        } catch (DbException e){
            Alerts.showAlerts("Error saving object", null, e.getMessage(), Alert.AlertType.ERROR);
        } catch (ValidationException e){
            setErrorMessages(e.getErrors());
        }
    }

    private void notifyDataChangeListeners() {
        for(DataChangeListener listener : dataChangeListeners){
            listener.onDataChanged();
        }
    }

    private Seller getFormData() {
        Seller obj = new Seller();
        ValidationException exception = new ValidationException("Validation error.");
        obj.setId(Utils.tryParseToInt(textId.getText()));
        if(textName.getText() == null || textName.getText().trim().equals("")){
            exception.addError("name", "Field can't be empty");
        }
        obj.setName(textName.getText());
        if(textEmail.getText() == null || textEmail.getText().trim().equals("")){
            exception.addError("email", "Field can't be empty");
        }
        obj.setEmail(textEmail.getText());

        if(datePickerBirthDate.getValue() == null){
            exception.addError("birthDate", "Field can't be empty");
        } else {
            Instant instant = Instant.from(datePickerBirthDate.getValue().atStartOfDay(ZoneId.systemDefault()));
            obj.setBirthDate(Date.from(instant));
        }

        if(textBaseSalary.getText() == null || textBaseSalary.getText().trim().equals("")){
            exception.addError("baseSalary", "Field can't be empty");
        }

        obj.setBaseSalary(Utils.tryParseToDouble(textBaseSalary.getText()));
        obj.setDepartment(comboBoxDepartment.getValue());
        if(exception.getErrors().size() > 0){
            throw exception;
        }
        return obj;
    }

    @FXML
    public void onBtCancelAction(ActionEvent event){
        Utils.currentStage(event).close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeNodes();
    }

    private void initializeNodes(){
        Constraints.setTextFieldInteger(textId);
        Constraints.setTextFieldMaxLength(textName, 70);
        Constraints.setTextFieldDouble(textBaseSalary);
        Constraints.setTextFieldMaxLength(textEmail, 60);
        Utils.formatDatePicker(datePickerBirthDate, "dd/MM/yyyy");
        initializeComboBoxDepartment();
    }

    public void updateFormData(){
        if(entity == null){
            throw new IllegalStateException("Entity was null");
        }
        textId.setText(String.valueOf(entity.getId()));
        textName.setText(String.valueOf(entity.getName()));
        textEmail.setText(String.valueOf(entity.getEmail()));
        Locale.setDefault(Locale.US);
        textBaseSalary.setText(String.format("%.2f", entity.getBaseSalary()));
        if(entity.getBirthDate() != null) {
            datePickerBirthDate.setValue(LocalDate.ofInstant(entity.getBirthDate().toInstant(), ZoneId.systemDefault()));
        }
        if(entity.getDepartment() == null){
            comboBoxDepartment.getSelectionModel().selectFirst();
        } else {
            comboBoxDepartment.setValue(entity.getDepartment());
        }

    }

    public void loadAssociatedObjects(){
        if(departmentService == null){
            throw new IllegalStateException("Department service was null");
        }
        List<Department> list = departmentService.findAll();
        obsList = FXCollections.observableArrayList(list);
        comboBoxDepartment.setItems(obsList);
    }

    private void setErrorMessages(Map<String, String> errors){
        Set<String> fields = errors.keySet();
        labelErrorName.setText((fields.contains("name") ? errors.get("name") : ""));
        labelErrorEmail.setText((fields.contains("email") ? errors.get("email") : ""));
        labelErrorBirthDate.setText((fields.contains("birthDate") ? errors.get("birthDate") : ""));
        labelErrorBaseSalary.setText((fields.contains("baseSalary") ? errors.get("baseSalary") : ""));
    }

    private void initializeComboBoxDepartment() {
        Callback<ListView<Department>, ListCell<Department>> factory = lv -> new ListCell<Department>() {
            @Override
            protected void updateItem(Department item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getName());
            }
        };
        comboBoxDepartment.setCellFactory(factory);
        comboBoxDepartment.setButtonCell(factory.call(null));
    }
}
