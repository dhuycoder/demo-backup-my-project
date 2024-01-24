package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import DAO.BuildingDAO;
import DAO.CustomerDAO;
import DAO.MemberDAO;
import DAO.PhongDAO;
import DAO.TaikhoanDAO;
import database.JDBCUtil;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Building;
import model.Customer;
import model.Member;
import model.Taikhoan;
import model.phong;

public class AddRoomController implements Initializable {
	@FXML
	private Button AddButton, DelButton, viewButton, updateButton, addCustomerButton, searchRoomNumberButton,
			viewCustomerButton, updateCustomerButton, delCustomerButton, saveCustomerButton, saveMemBerButton;
	@FXML
	private TableColumn<phong, String> buidingNumberColumn;

	@FXML
	private TextField buildingNumberTextField;

	@FXML
	private TableColumn<phong, String> priceColumn;

	@FXML
	private TextField priceTextField;

	@FXML
	private TableColumn<phong, String> roomNumberColumn;

	@FXML
	private TextField roomNumberTextField;

	@FXML
	private TableColumn<phong, String> roomTypeColumn;

	@FXML
	private TextField roomTypeTextField;
	@FXML
	private TableColumn<phong, String> roomStateColumn;
	@FXML
	private TableView<phong> table;
	@FXML
	private ObservableList<phong> roomList;
	@FXML
	private Label usernameTextField, roomNumberLabel, priceLabel, roomTypeLabel, buildingNumberLabel;
	@FXML
	private TextField buildingNumber1TextField, searchRoomNumberTextField, customerNumberTextField,
			customerNameTextField, customerPhoneNumberTextField, customerIdCardTextField, customerHomeTownTextField,
			memBerNumberTextField, memberNameTextField, memberIdCardTextField, memberAddressTextField,
			memberPhoneNumberTextField;
	@FXML
	private DatePicker customerBirthDayDatePicker, customerArrivalDateDatePicker, memberBirthDayDatePicker;
	@FXML
	private RadioButton genderCustomerRadioButton1, genderCustomerRadioButton2, genderMemberRadioButton1,
			genderMemberRadioButton2;
	@FXML
	private TextField addressBuildingTextField;
	@FXML
	private ChoiceBox<String> buildingNumberChoiceBox;
	@FXML
	private Label addressBuildingLabel;
	@FXML
	private AnchorPane customerAnchorPane, customerAnchorPane2, AnchorPane2, memberAnchorPane;
	@FXML
	private FontAwesomeIcon searchFontawesome;
	@FXML
	private TableView<Member> memberTable;
	@FXML
	private TableColumn<Member, Integer> memberNumberColumn;
	@FXML
	private TableColumn<Member, String> memberNameColumn;
	@FXML
	private TableColumn<Member, String> memberGenderColumn;
	@FXML
	private TableColumn<Member, LocalDate> memberBirthDayColumn;
	@FXML
	private TableColumn<Member, String> memberHomeTownColumn;
	@FXML
	private TableColumn<Member, String> memberIdCardColumn;
	@FXML
	private TableColumn<Member, String> memberPhoneNumberColumn;
	@FXML
	private ObservableList<Member> memberList;

	private ArrayList<String> buildingNumberlst = new ArrayList<String>();
	String getCustomerNumber;
	private String genderMember;
	ArrayList<phong> lst = new ArrayList<phong>();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		usernameTextField
				.setText(TaikhoanDAO.getInstance().selectbyId(new Taikhoan(LoginController.getID())).getUser());
		lst.addAll(PhongDAO.getInstance().selectbyCondition("MaT ='" + buildingNumberChoiceBox.getValue() + "'"));
		roomList = FXCollections.observableArrayList();
		memberList = FXCollections.observableArrayList();
		for (Building tmp : BuildingDAO.getInstance().selectALL()) {
			buildingNumberlst.add(tmp.getBuidingNumber());
		}
		buildingNumberChoiceBox.getItems().addAll(buildingNumberlst);
		roomNumberColumn.setCellValueFactory(new PropertyValueFactory<phong, String>("roomNumber"));
		buidingNumberColumn.setCellValueFactory(new PropertyValueFactory<phong, String>("buildingNumber"));
		priceColumn.setCellValueFactory(new PropertyValueFactory<phong, String>("price"));
		roomTypeColumn.setCellValueFactory(new PropertyValueFactory<phong, String>("roomType"));
		roomStateColumn.setCellValueFactory(new PropertyValueFactory<phong, String>("roomState"));
		buildingNumberChoiceBox.setOnAction(this::showRoomList);
		// table member
		//

		memberNumberColumn.setCellValueFactory(new PropertyValueFactory<Member, Integer>("memberNumber"));
		memberNameColumn.setCellValueFactory(new PropertyValueFactory<Member, String>("memberName"));
		memberGenderColumn.setCellValueFactory(new PropertyValueFactory<Member, String>("genderMember"));
		memberBirthDayColumn.setCellValueFactory(new PropertyValueFactory<Member, LocalDate>("memberBirthDay"));
		memberHomeTownColumn.setCellValueFactory(new PropertyValueFactory<Member, String>("memberHomeTown"));
		memberIdCardColumn.setCellValueFactory(new PropertyValueFactory<Member, String>("memberIdCard"));
		memberPhoneNumberColumn.setCellValueFactory(new PropertyValueFactory<Member, String>("memberPhoneNumber"));

	}

	public void add(ActionEvent event) throws ParseException {
		try {
			phong tmp = new phong();
			tmp.setRoomNumber(buildingNumberChoiceBox.getValue() + roomNumberTextField.getText());
			tmp.setPrice(priceTextField.getText());
			tmp.setBuildingNumber(buildingNumberChoiceBox.getValue());
			tmp.setRoomType(roomTypeTextField.getText());
			tmp.setRoomState("Còn trống");
			if (roomList.contains(tmp)) {// gọi hàm equal so sánh theo mã phòng
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("Lỗi chức năng");
				alert.setContentText("Mã phòng này đã tồn tại");
				alert.showAndWait();
			} else {
				roomList.add(tmp);
				PhongDAO.getInstance().insert(tmp);
			}
		} catch (java.lang.NumberFormatException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setTitle("Lỗi chức năng");
			alert.setContentText("Vui lòng nhập đầy đủ thông tin");
			alert.showAndWait();
		} catch (Exception e) {
			e.printStackTrace();
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setTitle("Lỗi chức năng");
			alert.setContentText("Vui lòng nhập đầy đủ thông tin");
			alert.showAndWait();
		}
	}

	public void delete(ActionEvent event) {
		phong selection = table.getSelectionModel().getSelectedItem();
		ArrayList<Customer> lst = new ArrayList<Customer>();
		lst.addAll(CustomerDAO.getInstance().selectbyCondition(
				"MaT ='" + buildingNumberChoiceBox.getValue() + "' and MaP ='" + selection.getRoomNumber() + "'"));
		System.out.println(lst.size());
		if (lst.size() > 0) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Lỗi chức năng");
			alert.setContentText("Không thể xóa phòng đang có khách!");
			alert.showAndWait();
		} else {
			try {

				PhongDAO.getInstance().delete(selection);
				roomList.remove(selection);
			} catch (NullPointerException e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("Lỗi chức năng");
				alert.setContentText("Vui lòng chọn phòng cần xóa");
				alert.showAndWait();
			}
		}
	}

	//
	public void view(ActionEvent event) {
		try {
			phong selection = table.getSelectionModel().getSelectedItem();
			roomNumberTextField.setText(selection.getRoomNumber());
			roomTypeTextField.setText(selection.getRoomType());
			priceTextField.setText(selection.getPrice());
			buildingNumberTextField.setPromptText(buildingNumberChoiceBox.getValue());
		} catch (NullPointerException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Lỗi chức năng");
			alert.setContentText("Vui lòng chọn phòng cần xem");
			alert.showAndWait();
		}
	}

	public void update(ActionEvent event) {
		try {
			phong selection = table.getSelectionModel().getSelectedItem();
			phong temp = new phong();
			temp.setRoomNumber(roomNumberTextField.getText());
			temp.setPrice(priceTextField.getText());
			temp.setBuildingNumber(buildingNumberChoiceBox.getValue());
			temp.setRoomType(roomTypeTextField.getText());
			temp.setRoomState(selection.getRoomState());
			if (selection.equals(temp)) { // kiểm tra mã phòng
				PhongDAO.getInstance().update(temp);
				roomList.clear();
				roomList.addAll(
						PhongDAO.getInstance().selectbyCondition("MaT = '" + buildingNumberChoiceBox.getValue() + "'"));
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Accept");
				alert.setHeaderText("Thành công");
				alert.setContentText("Bạn đã cập nhật thông tin phòng thành công");
				alert.showAndWait();
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("Lỗi chức năng");
				alert.setContentText("Bạn không thể thay đổi mã phòng");
				alert.showAndWait();
			}

		}

		catch (NumberFormatException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Lỗi chức năng");
			alert.setContentText("Vui lòng điền đầy đủ thông tin");
			alert.showAndWait();
		} catch (NullPointerException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Lỗi chức năng");
			alert.setContentText("Vui lòng chọn phòng cần thay đổi");
			alert.showAndWait();
		}
	}

	public void showAddRoom(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("ThemPhong.fxml"));
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root, javafx.scene.paint.Color.BLACK);
		scene.getStylesheets().add(getClass().getResource("HomeDesigner.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}

	public void showService(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("DichVu.fxml"));
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root, javafx.scene.paint.Color.BLACK);
		scene.getStylesheets().add(getClass().getResource("HomeDesigner.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}

	public void showHome(ActionEvent event) throws IOException {

//		Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
		Parent root = loader.load();
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root, javafx.scene.paint.Color.BLACK);
		scene.getStylesheets().add(getClass().getResource("HomeDesigner.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}

	public void displayUser(String user) {
		usernameTextField.setText(user);
	}

	public void AddBuilding(ActionEvent event) {
		Building tmp = new Building();
		tmp.setBuidingNumber(buildingNumber1TextField.getText());
		tmp.setAddress(addressBuildingTextField.getText());
		System.out.println(BuildingDAO.getInstance().insert(tmp));
		buildingNumberChoiceBox.getItems().add(tmp.getBuidingNumber());
		buildingNumberChoiceBox.setVisible(true);
	}

	public void showRoomList(ActionEvent event) {
		roomTypeLabel.setVisible(true);
		roomNumberLabel.setVisible(true);
		priceLabel.setVisible(true);
		roomNumberTextField.setVisible(true);
		priceTextField.setVisible(true);
		roomTypeTextField.setVisible(true);
		AddButton.setVisible(true);
		DelButton.setVisible(true);
		viewButton.setVisible(true);
		updateButton.setVisible(true);
		buildingNumberLabel.setVisible(true);
		buildingNumberTextField.setVisible(true);
		addCustomerButton.setVisible(true);
		table.setVisible(true);
		searchRoomNumberButton.setVisible(true);
		searchRoomNumberTextField.setVisible(true);
		viewCustomerButton.setVisible(true);
		if (buildingNumberChoiceBox.getItems().size() != 0) {
			addressBuildingLabel.setText("Địa chỉ: " + BuildingDAO.getInstance()
					.selectbyCondition("MaT = '" + buildingNumberChoiceBox.getValue() + "'").get(0).getAddress());
		}
		roomList.clear();
		lst.clear();
		lst.addAll(PhongDAO.getInstance().selectALL());
		for (phong tmp : lst) {
			if (tmp.getBuildingNumber().equals(buildingNumberChoiceBox.getValue())) {
				roomList.add(tmp);
				table.setItems(roomList);
			}
		}
	}

	public void delBuilding(ActionEvent event) {
		if (lst.size() > 0) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Lỗi chức năng");
			alert.setContentText("Không thể xóa tòa khi đang có phòng!");
			alert.showAndWait();
		} else if (buildingNumber1TextField.getText().equals("")) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Lỗi chức năng");
			alert.setContentText("Vui lòng chọn tòa cần xóa!");
			alert.showAndWait();
		} else {
			PhongDAO.getInstance().deleteByCondition("MaT = '" + buildingNumber1TextField.getText() + "'");
			buildingNumberChoiceBox.getItems().remove(buildingNumber1TextField.getText());
			Building tmp = new Building();
			tmp.setBuidingNumber(buildingNumber1TextField.getText());
			tmp.setAddress(addressBuildingTextField.getText());
			BuildingDAO.getInstance().delete(tmp);
		}
		if (buildingNumberChoiceBox.getItems().size() == 0) {
			roomTypeLabel.setVisible(false);
			roomNumberLabel.setVisible(false);
			priceLabel.setVisible(false);
			roomNumberTextField.setVisible(false);
			priceTextField.setVisible(false);
			roomTypeTextField.setVisible(false);
			AddButton.setVisible(false);
			DelButton.setVisible(false);
			viewButton.setVisible(false);
			updateButton.setVisible(false);
			buildingNumberLabel.setVisible(false);
			buildingNumberTextField.setVisible(false);
			addCustomerButton.setVisible(false);
			table.setVisible(false);
			searchRoomNumberButton.setVisible(false);
			searchRoomNumberTextField.setVisible(false);
			viewCustomerButton.setVisible(false);
		}
	}

	LocalDate birthDate, arrivalDate;

	public void getCustomerBirthDay(ActionEvent event) {
		birthDate = customerBirthDayDatePicker.getValue();
	}

	public void getCustomerArrivalDate(ActionEvent event) {
		arrivalDate = customerArrivalDateDatePicker.getValue();
	}

	String genDer;

	public void getGender(ActionEvent event) {
		if (genderCustomerRadioButton1.isSelected()) {
			genDer = genderCustomerRadioButton1.getText();
		} else if (genderCustomerRadioButton2.isSelected()) {
			genDer = genderCustomerRadioButton2.getText();
		}
	}

	String getRoomNumber, getBuildingNumber;

	public void addCustomer(ActionEvent event) {
		phong selection = table.getSelectionModel().getSelectedItem();
		if (selection == null) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Lỗi chức năng");
			alert.setContentText("Vui lòng chọn phòng cần thêm khách!");
			alert.showAndWait();
		} else if (selection.getRoomState().equals("Đã thuê")) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Lỗi chức năng");
			alert.setContentText("Phòng đã có khách thuê!");
			alert.showAndWait();
		} else {
			AnchorPane2.setVisible(false);
			customerAnchorPane.setVisible(true);
			delCustomerButton.setVisible(false);
			updateCustomerButton.setVisible(false);
			getRoomNumber = selection.getRoomNumber();
			getBuildingNumber = selection.getBuildingNumber();
		}

	}

	public void backToRoomHome(ActionEvent event) {
		customerAnchorPane.setVisible(false);
		AnchorPane2.setVisible(true);
		roomList.clear();
		memberList.clear();
		roomList.addAll(PhongDAO.getInstance().selectbyCondition("MaT = '" + buildingNumberChoiceBox.getValue() + "'"));
	}

	public void save(ActionEvent event) {
		Customer tmp;
		String customerNumber = customerNumberTextField.getText();
		String customerName = customerNameTextField.getText();
		String customerPhoneNumber = customerPhoneNumberTextField.getText();
		String customerIdCard = customerIdCardTextField.getText();
		LocalDate customerBirthDay = birthDate;
		String customerHomeTown = customerHomeTownTextField.getText();
		LocalDate customerArrivalDate = arrivalDate;
		tmp = new Customer("K" + buildingNumberChoiceBox.getValue() + getRoomNumber + customerNumber,
				buildingNumberChoiceBox.getValue(), getRoomNumber, customerName, genDer, customerIdCard,
				customerBirthDay, customerHomeTown, customerArrivalDate, customerPhoneNumber);
		ArrayList<String> lst = new ArrayList<String>();
		lst.add(CustomerDAO.getInstance().selectMaKByCondition(
				"MaT ='" + buildingNumberChoiceBox.getValue() + "' and MaP = '" + getRoomNumber + "'"));// lấy danh sách
																										// khóa mã khách
																										// và mã tòa đã
																										// thêm trong
																										// CSDL

		if (customerNumberTextField.getText().equals("") || customerNameTextField.getText().equals("")
				|| customerPhoneNumberTextField.getText().equals("") || customerIdCardTextField.getText().equals("")
				|| customerArrivalDateDatePicker.getValue() == null || customerBirthDayDatePicker.getValue() == null
				|| customerHomeTownTextField.getText().equals("") || genDer == null) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Lỗi chức năng");
			alert.setContentText("Vui lòng điền đầy đủ thông tin!");
			alert.showAndWait();
		} else if (!lst.get(0).equals("")) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Lỗi chức năng");
			alert.setContentText("Phòng đã có khách!");
			alert.showAndWait();
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Accept");
			alert.setHeaderText("Thành công");
			alert.setContentText("Bạn đã lưu thông tin khách thành công!");
			alert.showAndWait();
			CustomerDAO.getInstance().insert(tmp);
			memBerNumberTextField.setPromptText("1");
			phong roomtmp = new phong();
			Connection conn = JDBCUtil.getConnection();
			String sql = "select * from phong" + " where MaP =? and MaT =?";
			try {
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setString(1, getRoomNumber);
				pst.setString(2, getBuildingNumber);
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					String roomNumber = rs.getString("MaP");
					String buildingNumber = rs.getString("MaT");
					String roomType = rs.getString("LoaiP");
					String price = String.valueOf(rs.getDouble("GiaP")) ;
					int roomState = rs.getInt("TrangThaiP");
					roomtmp = new phong(roomNumber, price, roomType, buildingNumber, "Đã thuê");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			PhongDAO.getInstance().update2(roomtmp);
		}

	}

	public void delCustomer(ActionEvent event) {
		ArrayList<Member> lst = new ArrayList<Member>();
		lst.addAll(MemberDAO.getInstance().selectbyCondition("MaK = '" + getCustomerNumber + "'"));
		if (customerNumberTextField.getText().equals("")) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Lỗi chức năng");
			alert.setContentText("Vui lòng nhập mã khách!");
			alert.showAndWait();
		}
		if (lst.size() > 0) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Lỗi chức năng");
			alert.setContentText("Không thể xóa khách khi có thành viên!");
			alert.showAndWait();
		} else {
			CustomerDAO.getInstance().deleteByCondition(
					"MaK = '" + customerNumberTextField.getText() + "' and MaT = '" + getBuildingNumber + "'");
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Accept");
			alert.setHeaderText("Thành công");
			alert.setContentText("Bạn đã xóa khách thành công!");
			alert.showAndWait();
			// xóa khách thì cập nhật lại trạng thái phòng bằng 'trống'
			phong roomtmp = new phong();
			Connection conn = JDBCUtil.getConnection();
			String sql = "select * from phong" + " where MaP =? and MaT =?";
			try {
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setString(1, getRoomNumber);
				pst.setString(2, getBuildingNumber);
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					String roomNumber = rs.getString("MaP");
					String buildingNumber = rs.getString("MaT");
					String roomType = rs.getString("LoaiP");
					String price = String.valueOf(rs.getDouble("GiaP")) ;
					int roomState = rs.getInt("TrangThaiP");
					roomtmp = new phong(roomNumber, price, roomType, buildingNumber, "Còn trống");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			PhongDAO.getInstance().update2(roomtmp);
		}

	}

	public void updateCustomer(ActionEvent event) {
		boolean check = false;
		for (String temp : CustomerDAO.getInstance().selectMaK()) {
			if (customerNumberTextField.getText().equals(temp)) {
				check = false;
			} else {
				check = true;
			}
		}
		if (customerNumberTextField.getText().equals("")) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Lỗi chức năng");
			alert.setContentText("Vui lòng nhập mã khách!");
			alert.showAndWait();
		} else if (check) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Lỗi chức năng");
			alert.setContentText("Không thể thay đổi mã khách!");
			alert.showAndWait();
		} else {
			Customer tmp;
			String customerNumber = customerNumberTextField.getText();
			String customerName = customerNameTextField.getText();
			String customerPhoneNumber = customerPhoneNumberTextField.getText();
			String customerIdCard = customerIdCardTextField.getText();
			LocalDate customerBirthDay = birthDate;
			String customerHomeTown = customerHomeTownTextField.getText();
			LocalDate customerArrivalDate = arrivalDate;
			tmp = new Customer(customerNumber, buildingNumberChoiceBox.getValue(), getRoomNumber, customerName, genDer,
					customerIdCard, customerBirthDay, customerHomeTown, customerArrivalDate, customerPhoneNumber);
			CustomerDAO.getInstance().updateByCondition(tmp,
					"MaK = '" + customerNumberTextField.getText() + "' and MaT = '" + getBuildingNumber + "'");
		}
	}

	public void searchRoomNumber(ActionEvent event) {
		phong tmp = new phong();
		ArrayList<phong> lst = PhongDAO.getInstance()
				.selectbyCondition("MaT = '" + buildingNumberChoiceBox.getValue() + "'");
		System.out.println(buildingNumberChoiceBox.getValue());
		if (lst.contains(new phong(searchRoomNumberTextField.getText()))) {
			lst = PhongDAO.getInstance().selectbyCondition(" MaT = '" + buildingNumberChoiceBox.getValue()
					+ "' and MaP ='" + searchRoomNumberTextField.getText() + "'");
			roomList.clear();
			roomList.addAll(lst);
		}
		if (searchRoomNumberTextField.getText().equals("")) {
			roomList.clear();
			roomList.addAll(lst);
		}
	}

	public void viewCustomer(ActionEvent event) {
		phong selection = table.getSelectionModel().getSelectedItem();
		getCustomerNumber = CustomerDAO.getInstance().selectMaKByCondition(
				"MaT  ='" + buildingNumberChoiceBox.getValue() + "' and MaP = '" + getRoomNumber + "'");
		if (selection == null) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Lỗi chức năng");
			alert.setContentText("Vui lòng chọn phòng cần xem khách!");
			alert.showAndWait();
		} else if (selection.getRoomState().equals("Còn trống")) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Lỗi chức năng");
			alert.setContentText("Phòng chưa có khách thuê!");
			alert.showAndWait();
		} else {
			AnchorPane2.setVisible(false);
			customerAnchorPane.setVisible(true);
			updateCustomerButton.setVisible(true);
			delCustomerButton.setVisible(true);
			saveCustomerButton.setVisible(true);
			saveMemBerButton.setVisible(false);
			getRoomNumber = selection.getRoomNumber();
			getBuildingNumber = selection.getBuildingNumber();
			Customer tmp = new Customer();
			tmp = CustomerDAO.getInstance()
					.selectbyCondition1("MaT = '" + getBuildingNumber + "' and MaP = '" + getRoomNumber + "'");
			customerIdCardTextField.setText(tmp.getCustomerIdCard());
			customerNumberTextField.setText(tmp.getCustomerNumber());
			customerNameTextField.setText(tmp.getCustomerName());
			if (tmp.getCustomerGender().equals("Nam")) {
				genderCustomerRadioButton1.setSelected(true);
			} else {
				genderCustomerRadioButton2.setSelected(true);
			}
			customerPhoneNumberTextField.setText(tmp.getCustomerPhoneNumber());
			customerHomeTownTextField.setText(tmp.getCustomerHomeTown());
			customerBirthDayDatePicker.setValue(tmp.getCustomerBirthDay());
			customerArrivalDateDatePicker.setValue(tmp.getCustomerArrivalDate());
			memberList.addAll(MemberDAO.getInstance().selectbyCondition("MaK = '"
					+ CustomerDAO.getInstance().selectMaKByCondition(
							"MaT = '" + buildingNumberChoiceBox.getValue() + "' and MaP = '" + getRoomNumber + "'")
					+ "'"));
			memberTable.setItems(memberList);
		}

	}

	public void showInfoCustomer(ActionEvent event) {
		customerAnchorPane2.setVisible(true);
		saveMemBerButton.setVisible(false);
		saveCustomerButton.setVisible(true);
		memberAnchorPane.setVisible(false);
	}

	public void showInfoMember(ActionEvent event) {
		customerAnchorPane2.setVisible(false);
		saveCustomerButton.setVisible(false);
		saveMemBerButton.setVisible(true);
		memberAnchorPane.setVisible(true);
	}

	public void saveMember(ActionEvent event) {
		Member tmp = new Member();
		System.out.println("save Member");
		String getCustomerNumber = CustomerDAO.getInstance().selectMaKByCondition(
				"MaT = '" + buildingNumberChoiceBox.getValue() + "'" + " and MaP = '" + getRoomNumber + "'");
		String customerNumber = getCustomerNumber;
		String memberName = memberNameTextField.getText();
		LocalDate memberBirthDay = getMemberBirth;
		String memberIdCard = memberIdCardTextField.getText();
		String memberHomeTown = memberAddressTextField.getText();
		String memberGender = genderMember;
		String memberPhoneNumber = memberPhoneNumberTextField.getText();
		tmp = new Member(Integer.parseInt(memBerNumberTextField.getText()), customerNumber, memberGender,
				memberBirthDay, memberIdCard, memberPhoneNumber, memberHomeTown, memberName);
		MemberDAO.getInstance().insert(tmp);
		memberList.add(tmp);
	}

	LocalDate getMemberBirth;

	public void getMemberBirth(ActionEvent event) {
		getMemberBirth = memberBirthDayDatePicker.getValue();
	}

	public void getGenderMember(ActionEvent event) {
		if (genderMemberRadioButton1.isSelected()) {
			genderMember = genderMemberRadioButton1.getText();
		} else if (genderMemberRadioButton2.isSelected()) {
			genderMember = genderCustomerRadioButton2.getText();
		}
	}

	public void logout(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("DangNhap.fxml"));
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root, javafx.scene.paint.Color.BLACK);
		scene.getStylesheets().add(getClass().getResource("HomeDesigner.css").toExternalForm());
		stage.setScene(scene);
		stage.setResizable(false);
		stage.centerOnScreen();
		stage.show();
	}

	public void showCalculate(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("calculateBill.fxml"));
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root, javafx.scene.paint.Color.BLACK);
		scene.getStylesheets().add(getClass().getResource("HomeDesigner.css").toExternalForm());
		stage.setScene(scene);
		stage.setResizable(false);
		stage.centerOnScreen();
		stage.show();
	}
}
