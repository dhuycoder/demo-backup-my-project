package application;

import java.awt.Button;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import DAO.BillDAO;
import DAO.BuildingDAO;
import DAO.PhongDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.Bill;
// Form này là giao diện tính tiền chính 
public class CalculateBillController implements Initializable {
	@FXML
	GridPane billGridPane; // form để thêm các bill vào
	@FXML
	DatePicker invoiceDatePicker;// chọn ngày xuất hóa đợn
	@FXML
	ChoiceBox<String> buildingChoiceBox,roomChoiceBox; // chọn nhà , chọn phòng
	int row = 0;
	// khai báo dùng cho toàn cục
	public static Stage secondStage = new Stage();
	public static ObservableList<Node> lst ;// danh sách các node ứng với GridPane 
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		addMenuPane();
		lst = billGridPane.getChildren(); // gán danh sách các node ứng với BillGridPane để tiện cho việc thêm,xóa các CalculateForm.
		buildingChoiceBox.getItems().addAll(BuildingDAO.getInstance().selectBuildingNumber()); // add nhà vào choicebox
		buildingChoiceBox.setOnAction(this:: setRoomId);
	}
	public void setRoomId(ActionEvent event) {
		roomChoiceBox.getItems().clear();
		roomChoiceBox.getItems().addAll(PhongDAO.getInstance().selectRoomIdByCondition("MaT = '"+buildingChoiceBox.getValue()+"' and TrangThaiP = 1"));// add phòng vào choicebox
	}
	
	public void showHome(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root, javafx.scene.paint.Color.BLACK);
		scene.getStylesheets().add(getClass().getResource("HomeDesigner.css").toExternalForm());
		stage.setScene(scene);
		stage.setResizable(false);
		stage.centerOnScreen();
		stage.show();
	}

	public void showAddRoom(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("ThemPhong.fxml"));
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root, javafx.scene.paint.Color.BLACK);
		scene.getStylesheets().add(getClass().getResource("HomeDesigner.css").toExternalForm());
		stage.setScene(scene);
		stage.setResizable(false);
		stage.centerOnScreen();
		stage.show();
	}

	public void showService(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("DichVu.fxml"));
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

	// ấn vào đây sẽ hiện lên form calculateBillForm.fxml
	public void calculate(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("calculateBillForm.fxml"));
	        Parent root = loader.load();
	        calculateBillFormController calculateBillForm = loader.getController();
			secondStage.setTitle("Tính hóa đơn");
			secondStage.setScene(new Scene(root));
			secondStage.showAndWait();
			calculateBillForm.addPane(billGridPane);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// hàm add các CalculateForm có sẵn từ cơ sở dữ liệu đã thêm vào trước đó
	public void addMenuPane() {
		try {
			ArrayList<Bill> billList = new ArrayList<>();
			billList = BillDAO.getInstance().selectALL();
			int row = 0;
			for(int q = 0; q <billList.size();q++) {
				
				FXMLLoader load = new FXMLLoader() ;
				load.setLocation(getClass().getResource("CalculateForm.fxml"));
				AnchorPane pane = load.load();
				CalculateFormController calculateForm = load.getController();
				calculateForm.setData(billList.get(q));
				billGridPane.add(pane, 0, row++);
				///
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	// hàm lọc theo 3 điều kiện ở builldingChoiceBox, roomChoiceBox, invoiceDatePicker
	public void fillter(ActionEvent event) throws IOException {
		ArrayList<Bill> lst = new ArrayList<Bill>();
		// kiểm tra các điều kiện lọc: 
		// nếu có điều kiện của cả 3
		if(buildingChoiceBox.getValue() != null && roomChoiceBox.getValue() !=null && invoiceDatePicker.getValue() != null) { // 
		 lst = BillDAO.getInstance().selectbyCondition("MaT = '"+buildingChoiceBox.getValue()+"' and MaP ='"+roomChoiceBox.getValue()+"' and NgayXuatHD = '"+invoiceDatePicker.getValue()+"'");
		}
		else if(buildingChoiceBox.getValue() != null && roomChoiceBox.getValue() !=null) { //nếu chỉ có điều kiện của tòa và phòng
		 lst = BillDAO.getInstance().selectbyCondition("MaT = '"+buildingChoiceBox.getValue()+"' and MaP ='"+roomChoiceBox.getValue()+"'");
		}
		else if(buildingChoiceBox.getValue() != null && invoiceDatePicker.getValue() != null) { // nếu chỉ có điều kiện của tòa với ngày xuất HD
			 lst = BillDAO.getInstance().selectbyCondition( "MaT ='"+buildingChoiceBox.getValue() +"' and NgayXuatHD = '"+invoiceDatePicker.getValue()+"'");
			}
		else if(buildingChoiceBox.getValue() != null) { // nếu chỉ có điều kiện của tòa
		 lst = BillDAO.getInstance().selectbyCondition("MaT = '"+buildingChoiceBox.getValue()+"'");
		 System.out.println("tham nhap thanh cong ");
		}
		else if(invoiceDatePicker.getValue() != null) {// nếu chỉ có điều kiện của ngày xuất hóa đơn
		 lst = BillDAO.getInstance().selectbyCondition("NgayXuatHD = '"+invoiceDatePicker.getValue()+"'");
		}
		
		int row = 0;
		billGridPane.getChildren().clear();
		try {
			for(int q = 0; q <lst.size();q++) {
				FXMLLoader load = new FXMLLoader() ;
				load.setLocation(getClass().getResource("CalculateForm.fxml"));
				AnchorPane pane = load.load();
				CalculateFormController calculateForm = load.getController();
				calculateForm.setData(lst.get(q));
				billGridPane.add(pane, 0, row++);
			}
		} catch (java.lang.NullPointerException e) {
			e.printStackTrace();
		}
	}
	

}
