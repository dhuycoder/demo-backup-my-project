package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.collections.map.HashedMap;

import DAO.BillDAO;
import database.JDBCUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import model.Bill;
// form này là ấn tính tiền xong nó sẽ tự động điền form này vào 
public class CalculateFormController implements Initializable {
	@FXML
	AnchorPane anchorpane;
	@FXML
	GridPane formGridPane;
	@FXML
	Label billNumberLabel, // mã hóa đơn
	accountNumberLabel, // id tài khoản
	buildingNumberLabel, // mã Nhà
	roomNumberLabel, // mã phòng
	billPriceLabel; // tiền hóa đơn
	@FXML
	DatePicker invoiceDatePicker; // ngày xuất HD
	private Map<String, Object> map ;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	public void setData(Bill tmp) {
		buildingNumberLabel.setText(tmp.getBuildingNumber());
		roomNumberLabel.setText(tmp.getRoomNumber());
		invoiceDatePicker.setValue(tmp.getInvoiceDate());
		billPriceLabel.setText(tmp.getBillPrice());	
	}
	// hàm ấn nút xóa
	public void deleteBillForm(ActionEvent event) {
		int billNumber = BillDAO.getInstance().selectIdByCondition("MaT = '"+buildingNumberLabel.getText()+"' and MaP = '"+roomNumberLabel.getText()+"'");
		BillDAO.getInstance().delete(new Bill(billNumber));
		CalculateBillController.lst.remove(this.anchorpane);
		
	}
	// Hàm ấn nút in
	public void PrintPDF(ActionEvent event) {
		BillDAO.getInstance().deleteBillTmp();
		Bill billtmp = BillDAO.getInstance().selectBillbyCondition("MaT = '"+buildingNumberLabel.getText()+"' and MaP = '"+roomNumberLabel.getText()+"'");
		BillDAO.getInstance().insertBillTmp(billtmp);
		Connection connection = JDBCUtil.getConnection();
		map = new HashMap<String, Object>();
		Report.createReport(connection, map, BillDAO.getInstance().getReport("billtmp2_report", "report_jasper"));
		Report.showReport();
		
	}
	
}
