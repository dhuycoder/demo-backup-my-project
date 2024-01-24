package application;

import java.awt.Desktop.Action;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import DAO.PhongDAO;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Taikhoan;
import model.phong;

public class HomeController implements Initializable {
	@FXML
	private Label usernameTextField;
	@FXML
	private Label availableRoomLabel;
	@FXML
	private Label priceSumLabel;
	@FXML
	private Label rentedRoomLabel;
	@FXML
	private TableView<phong> rentedRoomTable,availibleRoomTable;
	@FXML
	TableColumn<phong, String> roomNumberColumn,roomNumberColumn1;
	@FXML
	TableColumn<phong, String> buildingNumberColumn,buildingNumberColumn1;
	@FXML
	TableColumn<phong, String> roomTypeColumn,roomTypeColumn1;
	@FXML
	TableColumn<phong, Double> roomPriceColumn,roomPriceColumn1;
	@FXML
	TableColumn<phong, String> roomStateColumn,roomStateColumn1;
	private ObservableList<phong> roomRentedList;
	private ObservableList<phong> roomAvailibleList;
	public void showAddRoom(ActionEvent event) throws IOException {
		FXMLLoader loader =  new FXMLLoader(getClass().getResource("ThemPhong.fxml"));
		Parent root= loader.load();
		AddRoomController addRoomControlelr = loader.getController();
		Taikhoan Account = new Taikhoan(LoginController.getID());
		addRoomControlelr.displayUser(TaikhoanDAO.getInstance().selectbyId(Account).getUser());
//		Parent root = FXMLLoader.load(getClass().getResource("ThemPhong.fxml"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root,javafx.scene.paint.Color.BLACK);
		scene.getStylesheets().add(getClass().getResource("HomeDesigner.css").toExternalForm());
		stage.setScene(scene);
		stage.setResizable(false);
		stage.centerOnScreen();
		stage.show();
	}
	public void showService(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("DichVu.fxml"));
		Stage stage =(Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root,javafx.scene.paint.Color.BLACK);
		scene.getStylesheets().add(getClass().getResource("HomeDesigner.css").toExternalForm());
		stage.setScene(scene);
		stage.setResizable(false);
		stage.centerOnScreen();
		stage.show();
	}
	public void showHome (ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
		Stage stage =(Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root,javafx.scene.paint.Color.BLACK);
		scene.getStylesheets().add(getClass().getResource("HomeDesigner.css").toExternalForm());
		stage.setScene(scene);
		stage.setResizable(false);
		stage.centerOnScreen();
		stage.show();
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		usernameTextField.setText(TaikhoanDAO.getInstance().selectbyId(new Taikhoan(LoginController.getID())).getUser());
		ArrayList<String> lst = new ArrayList<String>();
		lst.addAll(PhongDAO.getInstance().selectRoomState("TrangThaiP = 1"));
		rentedRoomLabel.setText( Integer.toString(lst.size()));
		lst.clear();
		lst.addAll(PhongDAO.getInstance().selectRoomState("TrangThaiP = 0"));
		availableRoomLabel.setText( Integer.toString(lst.size()));
		roomRentedList = FXCollections.observableArrayList();
		roomAvailibleList = FXCollections.observableArrayList();
		roomNumberColumn.setCellValueFactory(new PropertyValueFactory<phong, String>("roomNumber"));
		buildingNumberColumn.setCellValueFactory(new PropertyValueFactory<phong, String>("buildingNumber"));
		roomTypeColumn.setCellValueFactory(new PropertyValueFactory<phong, String>("roomType"));
		roomPriceColumn.setCellValueFactory(new PropertyValueFactory<phong, Double>("price"));
		roomStateColumn.setCellValueFactory(new PropertyValueFactory<phong, String>("roomState"));
		//
		roomNumberColumn1.setCellValueFactory(new PropertyValueFactory<phong, String>("roomNumber"));
		buildingNumberColumn1.setCellValueFactory(new PropertyValueFactory<phong, String>("buildingNumber"));
		roomTypeColumn1.setCellValueFactory(new PropertyValueFactory<phong, String>("roomType"));
		roomPriceColumn1.setCellValueFactory(new PropertyValueFactory<phong, Double>("price"));
		roomStateColumn1.setCellValueFactory(new PropertyValueFactory<phong, String>("roomState"));
		//
		roomRentedList.addAll(PhongDAO.getInstance().selectbyCondition("TrangThaiP = 1"));
		rentedRoomTable.setItems(roomRentedList);
		roomAvailibleList.addAll(PhongDAO.getInstance().selectbyCondition("TrangThaiP = 0"));
		availibleRoomTable.setItems(roomAvailibleList);
	}
	public void showRentedRoomList(ActionEvent event) {
		rentedRoomTable.setVisible(true);
		availibleRoomTable.setVisible(false);
	}
	public void showAvailibleRoomList(ActionEvent event) {
		rentedRoomTable.setVisible(false);
		availibleRoomTable.setVisible(true);
	}
	public void showCalculate(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("calculateBill.fxml"));
		Stage stage =(Stage)((Node)event.getSource()).getScene().getWindow() ;
		Scene scene = new Scene(root,javafx.scene.paint.Color.BLACK);
		scene.getStylesheets().add(getClass().getResource("HomeDesigner.css").toExternalForm());
		stage.setScene(scene);
		stage.setResizable(false);
		stage.centerOnScreen();
		stage.show();
	}
	public void logout(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("DangNhap.fxml"));
		Stage stage =(Stage)((Node)event.getSource()).getScene().getWindow() ;
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("HomeDesigner.css").toExternalForm());
		stage.setScene(scene);
		stage.setResizable(false);
		stage.centerOnScreen();
		stage.show();
	}
	public void showStatistics(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("StatisticsForm.fxml"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root,javafx.scene.paint.Color.BLACK);
		scene.getStylesheets().add(getClass().getResource("HomeDesigner.css").toExternalForm());
		stage.setScene(scene);
		stage.setResizable(false);
		stage.centerOnScreen();
		stage.show();
	}
}
