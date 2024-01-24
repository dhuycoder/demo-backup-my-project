package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import DAO.BillDAO;
import DAO.ServiceDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
// form thống kê
public class StatisticsController implements Initializable {
	@FXML
	LineChart<String , Number> monthlyRevenueLineChart;
	@FXML
	BarChart<String, Number> monthlyRevenueBarChart;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		XYChart.Series<String, Number> series = new XYChart.Series<>();
		XYChart.Data<String, Number> jan = new XYChart.Data<>("jan",Double.parseDouble(BillDAO.getInstance().SelectPricePerMonth(1)));
		XYChart.Data<String, Number> feb = new XYChart.Data<>("feb",Double.parseDouble(BillDAO.getInstance().SelectPricePerMonth(2)));
		XYChart.Data<String, Number> mar = new XYChart.Data<>("mar",Double.parseDouble(BillDAO.getInstance().SelectPricePerMonth(3)));
		XYChart.Data<String, Number> apr = new XYChart.Data<>("apr",Double.parseDouble(BillDAO.getInstance().SelectPricePerMonth(4)));
		XYChart.Data<String, Number> may = new XYChart.Data<>("may",Double.parseDouble(BillDAO.getInstance().SelectPricePerMonth(5)));
		XYChart.Data<String, Number> jun = new XYChart.Data<>("jun",Double.parseDouble(BillDAO.getInstance().SelectPricePerMonth(6)));
		XYChart.Data<String, Number> jul = new XYChart.Data<>("jul",Double.parseDouble(BillDAO.getInstance().SelectPricePerMonth(7)));
		XYChart.Data<String, Number> aug = new XYChart.Data<>("aug",Double.parseDouble(BillDAO.getInstance().SelectPricePerMonth(8)));
		XYChart.Data<String, Number> sep = new XYChart.Data<>("sep",Double.parseDouble(BillDAO.getInstance().SelectPricePerMonth(9)));
		XYChart.Data<String, Number> oct = new XYChart.Data<>("oct",Double.parseDouble(BillDAO.getInstance().SelectPricePerMonth(10)));
		XYChart.Data<String, Number> nov = new XYChart.Data<>("nov",Double.parseDouble(BillDAO.getInstance().SelectPricePerMonth(11)));
		XYChart.Data<String, Number> dec = new XYChart.Data<>("dec",Double.parseDouble(BillDAO.getInstance().SelectPricePerMonth(12)));
		series.getData().addAll(jan,feb,mar,apr,may,jun,jul,aug,sep,oct,nov,dec);
		series.setName("doanh thu theo tháng");
		monthlyRevenueLineChart.getData().add(series);
		XYChart.Series<String,Number> Series = new XYChart.Series();
		Series.setName("doanh thu theo năm");
		Series.getData().add(new XYChart.Data<String, Number>("2020",Double.parseDouble(BillDAO.getInstance().SelectPricePerYear(2020))));
		Series.getData().add(new XYChart.Data<String, Number>("2021",Double.parseDouble(BillDAO.getInstance().SelectPricePerYear(2021))));
		Series.getData().add(new XYChart.Data<String, Number>("2022",Double.parseDouble(BillDAO.getInstance().SelectPricePerYear(2022))));
		Series.getData().add(new XYChart.Data<String, Number>("2023",Double.parseDouble(BillDAO.getInstance().SelectPricePerYear(2023))));
		Series.getData().add(new XYChart.Data<String, Number>("2024",Double.parseDouble(BillDAO.getInstance().SelectPricePerYear(2024))));
		Series.getData().add(new XYChart.Data<String, Number>("2025",Double.parseDouble(BillDAO.getInstance().SelectPricePerYear(2025))));
		Series.getData().add(new XYChart.Data<String, Number>("2026",Double.parseDouble(BillDAO.getInstance().SelectPricePerYear(2026))));
		Series.getData().add(new XYChart.Data<String, Number>("2027",Double.parseDouble(BillDAO.getInstance().SelectPricePerYear(2027))));
		Series.getData().add(new XYChart.Data<String, Number>("2028",Double.parseDouble(BillDAO.getInstance().SelectPricePerYear(2028))));
		Series.getData().add(new XYChart.Data<String, Number>("2029",Double.parseDouble(BillDAO.getInstance().SelectPricePerYear(2029))));
		Series.getData().add(new XYChart.Data<String, Number>("2030",Double.parseDouble(BillDAO.getInstance().SelectPricePerYear(2030))));
		monthlyRevenueBarChart.getData().addAll(Series);
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
		
	}
	public void showCalculate(ActionEvent event) {
		
	}
	public void showStatistics(ActionEvent event) {
		
	}
	public void logout(ActionEvent event) {
		
	}
	
	
	
	

}
