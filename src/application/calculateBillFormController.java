package application;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import DAO.BillDAO;
import DAO.BuildingDAO;
import DAO.CustomerDAO;
import DAO.PhongDAO;
import DAO.ServiceDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Bill;
import net.sf.jasperreports.engine.JasperReport;

// form này là phần mà ấn vào nút tính tiền thì nó sẽ hiện lên 
public class calculateBillFormController implements Initializable{
	
	@FXML 
	TextField electricNumberTextField;
	@FXML
	ChoiceBox<String> buildingChoiceBox,roomChoiceBox;
	@FXML
	DatePicker invoiceDatePicker;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		buildingChoiceBox.getItems().addAll(BuildingDAO.getInstance().selectBuildingNumber()); // add nhà vào choiceBox
		buildingChoiceBox.setOnAction(this:: setRoomId);
	}
	public void setRoomId(ActionEvent event) {
		roomChoiceBox.getItems().clear();
		roomChoiceBox.getItems().addAll(PhongDAO.getInstance().selectRoomIdByCondition("MaT = '"+buildingChoiceBox.getValue()+"' and TrangThaiP = 1")); // add phòng mà đã thuê vào choicebox
	}
	LocalDate getDate ;
	String price = null;
	public LocalDate getDate(ActionEvent event) {
		getDate = invoiceDatePicker.getValue();
		return getDate;
	}
	Bill tmp ;
	private String tinhtien() {
		// công thức tính tiền :  tiền tổng = tổng các dịch vụ (trừ tiền điện) + tiền điện * số điện + tiền phòng
		BigDecimal priceService = new BigDecimal(ServiceDAO.getInstance().selectSumPriceByCondition("MaT = '"+buildingChoiceBox.getValue()+"' and tenDV <> 'Điện'"));
		priceService = priceService.add(new BigDecimal(ServiceDAO.getInstance().selectSumPriceByCondition("tenDV ='Điện' and MaT ='"+buildingChoiceBox.getValue()+"'")).multiply(new BigDecimal(electricNumberTextField.getText())));
		BigDecimal priceRoom = new BigDecimal(PhongDAO.getInstance().selectSumPriceByCondition("MaT = '"+buildingChoiceBox.getValue()+"' and MaP = '"+roomChoiceBox.getValue()+"'"));
		BigDecimal result = new BigDecimal(0);
		result = result.add(priceRoom).add(priceService);
		return result.toString();
	}
	// nút tính trong form
	public void calculate(ActionEvent event) throws IOException {
		try {
			boolean check = true;
			price = tinhtien();
			String getcustomerID = CustomerDAO.getInstance().selectMaKByCondition("MaT = '"+buildingChoiceBox.getValue()+"' and MaP = '"+roomChoiceBox.getValue()+"'");
			tmp = new Bill(LoginController.getID(), buildingChoiceBox.getValue(), roomChoiceBox.getValue(), getDate, price,getcustomerID,Integer.parseInt(electricNumberTextField.getText()));
			ArrayList<Bill> lst = BillDAO.getInstance().selectALL();
			// kiểm tra xem phòng tại tòa nhà này đã có hóa đơn chưa?
			for (Bill bill : lst) {
				if(bill.getRoomNumber().equals(tmp.getRoomNumber()) && bill.getBuildingNumber().equals(tmp.getBuildingNumber())){
					check = false; // có rồi thì false
					break;
				}
			}
			if(check) { // chưa thì thêm
				BillDAO.getInstance().insert(tmp);
				CalculateBillController.secondStage.close();
			}
			else {// nếu có rồi thì thông báo
				tmp = null; //cho bill bằng rỗng để không bị thêm vào BillGridPane
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("lỗi chức năng");
				alert.setHeaderText("lỗi chức năng");
				alert.setContentText("Đã có hóa đơn tại phòng này");
				alert.showAndWait();
			}
		} catch (NumberFormatException e) { // bắt lỗi 
			tmp = null; //cho bill bằng rỗng để không bị thêm vào BillGridPane
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("lỗi chức năng");
			alert.setHeaderText("lỗi chức năng");
			alert.setContentText("Vui lòng nhập số điện ");
			alert.showAndWait();
		}
		catch (java.lang.NullPointerException e) {
			tmp = null;// cho bill bằng rỗng để không bị thêm vào BillGridPane
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("lỗi chức năng");
			alert.setHeaderText("lỗi chức năng");
			alert.setContentText("Vui lòng điền đầy đủ thông tin");
			alert.showAndWait();
		}
		
	
	}
	public void close(ActionEvent event) {
		tmp = null;
		CalculateBillController.secondStage.close();
	}
	// hàm thêm Form vào BillGridPane ở form CalCulateBillController 
	public void addPane(GridPane billGridPane) throws IOException {
		if(tmp != null) { // Điều kiện: Bill phải khác rỗng mới được thêm vào
		int row = BillDAO.getInstance().selectALL().size(); // lấy size 
		System.out.println(row);
		FXMLLoader loader = new FXMLLoader() ;
		loader.setLocation(getClass().getResource("CalculateForm.fxml"));
		AnchorPane pane = loader.load();
		CalculateFormController calculateForm = loader.getController();
		calculateForm.setData(tmp);
		billGridPane.add(pane, 0, row++);
		}
	}
	
	

}
