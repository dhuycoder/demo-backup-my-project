package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import DAO.BuildingDAO;
import DAO.PhongDAO;
import DAO.ServiceDAO;
import DAO.TaikhoanDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Service;
import model.Taikhoan;
import model.phong;


public class DichVuController implements Initializable {
	@FXML
	private Label usernameTextField;
	@FXML 
	private TableView<Service> table;
	@FXML
	private TableColumn<Service,String> buildingNumberColumn;
	@FXML
	private TableColumn<Service,String> serviceNumberColumn;
	@FXML
	private TableColumn<Service,String> serviceNameColumn;
	@FXML
	private TableColumn<Service,Double> servicePriceColumn;
	@FXML
	private TextField buildingNumberTextField,serviceNumberTextField,serviceNameTextField,servicePriceTextField;
	@FXML
	private ChoiceBox<String> buildingNumberChoiceBox;
	@FXML
	private ObservableList<Service> serviceList;
	@FXML
	private AnchorPane AnchorPane2;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ArrayList<String> builderList = new ArrayList<String>();
		builderList.addAll(BuildingDAO.getInstance().selectBuildingNumber());
		System.out.println(Arrays.toString(builderList.toArray()));
		buildingNumberChoiceBox.getItems().addAll(builderList);
		usernameTextField.setText(TaikhoanDAO.getInstance().selectbyId(new Taikhoan(LoginController.getID())).getUser());
		serviceList = FXCollections.observableArrayList();
		buildingNumberColumn.setCellValueFactory(new PropertyValueFactory<Service, String>("builldingNumber"));
		serviceNumberColumn.setCellValueFactory(new PropertyValueFactory<Service, String>("serviceNumber"));
		serviceNameColumn.setCellValueFactory(new PropertyValueFactory<Service, String>("serviceName"));
		servicePriceColumn.setCellValueFactory(new PropertyValueFactory<Service, Double>("servicePrice"));
		table.setItems(serviceList);
		buildingNumberChoiceBox.setOnAction(this::showService);
	}
	public void showServiceList(ActionEvent event) {
		
	}
	public void showAddRoom(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("ThemPhong.fxml"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root,javafx.scene.paint.Color.BLACK);
		scene.getStylesheets().add(getClass().getResource("HomeDesigner.css").toExternalForm());
		stage.setScene(scene);
		stage.setResizable(false);
		stage.centerOnScreen();
		stage.show();
	}
	public void showHome(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root,javafx.scene.paint.Color.BLACK);
		scene.getStylesheets().add(getClass().getResource("HomeDesigner.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}
	public void showService(ActionEvent event) {
		serviceList.clear();
		AnchorPane2.setVisible(true);
		serviceList.addAll(ServiceDAO.getInstance().selectbyCondition("MaT = '"+buildingNumberChoiceBox.getValue()+"'"));
		table.setItems(serviceList);
		buildingNumberTextField.setPromptText(buildingNumberChoiceBox.getValue());
	}
	public void addService(ActionEvent event) {
		if(serviceNumberTextField.getText().equals("") || buildingNumberTextField.getPromptText().equals("")) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Lỗi chức năng");
			alert.setContentText("Vui lòng chọn dịch vụ cần xóa!");
			alert.showAndWait();
		}
		else {
			Service tmp = new Service();
			tmp.setBuilldingNumber(buildingNumberChoiceBox.getValue());
			tmp.setServiceNumber(serviceNumberTextField.getText());
			tmp.setServiceName(serviceNameTextField.getText());
			tmp.setServicePrice(servicePriceTextField.getText());
			ServiceDAO.getInstance().insert(tmp);
			serviceList.add(tmp);
		}
	}
	public void delService(ActionEvent event) {
		Service selection = table.getSelectionModel().getSelectedItem();
		ServiceDAO.getInstance().delete(selection);
		serviceList.remove(selection);
	}
	public void updateService(ActionEvent event) {
		try {
			Service selection = table.getSelectionModel().getSelectedItem();
			Service temp = new Service();
			temp.setBuilldingNumber(buildingNumberChoiceBox.getValue());
			temp.setServicePrice(servicePriceTextField.getText());
			temp.setServiceNumber(serviceNumberTextField.getText());
			temp.setServiceName(serviceNameTextField.getText());
			if(selection.equals(temp)) { // kiểm tra mã phòng
				ServiceDAO.getInstance().update(temp);
				serviceList.clear();
				serviceList.addAll(ServiceDAO.getInstance().selectbyCondition("MaT = '"+buildingNumberChoiceBox.getValue()+"'"));
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Accept");
				alert.setHeaderText("Thành công");
				alert.setContentText("Bạn đã cập nhật thông tin phòng thành công");
				alert.showAndWait();
			}
			else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("Lỗi chức năng");
				alert.setContentText("Bạn không thể thay đổi mã dịch vụ");
				alert.showAndWait();
			}
			
		}
		
		catch (NumberFormatException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Lỗi chức năng");
			alert.setContentText("Vui lòng điền đầy đủ thông tin");
			alert.showAndWait();
		}
		catch (NullPointerException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Lỗi chức năng");
			alert.setContentText("Vui lòng chọn dịch vụ cần thay đổi");
			alert.showAndWait();
		}
	}
	public void logout(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("DangNhap.fxml"));
		Stage stage =(Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root,javafx.scene.paint.Color.BLACK);
		scene.getStylesheets().add(getClass().getResource("HomeDesigner.css").toExternalForm());
		stage.setScene(scene);
		stage.setResizable(false);
		stage.centerOnScreen();
		stage.show();
	}
	public void showCalculate(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("calculateBill.fxml"));
		Stage stage =(Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root,javafx.scene.paint.Color.BLACK);
		scene.getStylesheets().add(getClass().getResource("HomeDesigner.css").toExternalForm());
		stage.setScene(scene);
		stage.setResizable(false);
		stage.centerOnScreen();
		stage.show();
	}
}
