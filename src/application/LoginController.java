package application;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import DAO.PhongDAO;
import DAO.TaikhoanDAO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Taikhoan;
import model.phong;

public class LoginController implements Initializable  {
	@FXML
	private TextField UsernameTextField;
	@FXML
	private PasswordField PasswordTextField;
	@FXML
	private TextField passWordTextField;
	@FXML
	private Button loginButton;
	@FXML
	private Label notificationLabel;
	@FXML
	private ImageView eyeOpenImageView,eyeCloseImageView;
	@FXML
	private AnchorPane LoginAnchorPane,forgotAnchorPane;
	
	Image eyeCloseImg = new Image(getClass().getResourceAsStream("eyeclose.png"));
	
	Image eyeOpenImg = new Image(getClass().getResourceAsStream("eyeopen.png"));
	
	private static int ID;
	
	String user;
	private BigDecimal price = new BigDecimal(0);
	private BigDecimal priceroom = new BigDecimal(0);
	public void DangNhap(ActionEvent event) throws IOException {
		boolean check = false;
		ArrayList<Taikhoan> lst = new ArrayList<Taikhoan>();
		lst.addAll(TaikhoanDAO.getInstance().selectALL());
		ArrayList<phong> roomLst = new ArrayList<phong>();
		roomLst.addAll(PhongDAO.getInstance().selectALL());
		for (phong phong : roomLst) {
			priceroom = new BigDecimal(phong.getPrice());
			price.add(priceroom);
		}
		price.toString();
		for (Taikhoan taikhoan : lst) {
			if(taikhoan.getUsername().equals(UsernameTextField.getText()) && taikhoan.getPassword().equals(PasswordTextField.getText())) {
				check = true;
				user = taikhoan.getUser();
				setID(taikhoan.getIdAccount());
				break;
			}
		}
		if(check) {
		ID = TaikhoanDAO.getInstance().selectID("TaiKhoan ='"+UsernameTextField.getText()+"' and MatKhau = '"+PasswordTextField.getText()+"'");
		FXMLLoader loader =  new FXMLLoader(getClass().getResource("Home.fxml"));
		Parent root= loader.load();
		HomeController homeController = loader.getController();
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root,javafx.scene.paint.Color.BLACK);
		scene.getStylesheets().add(getClass().getResource("HomeDesigner.css").toExternalForm());
		stage.setScene(scene);
		stage.setResizable(false);
		stage.centerOnScreen();
		stage.show();
		}
		else {
			notificationLabel.setText("Đăng nhập không thành công!Vui lòng đăng nhập lại");
			
		}
	}
	public static int getID() {
		return ID;
	}
	public static void setID(int iD) {
		ID = iD;
	}
	public void hidePassWord() {
		eyeCloseImageView.setVisible(true);
		eyeOpenImageView.setVisible(false);
		PasswordTextField.setVisible(true);
		passWordTextField.setVisible(false);
		PasswordTextField.setText(passWordTextField.getText());
	}
	public void showPassWord() {
		eyeCloseImageView.setVisible(false);
		eyeOpenImageView.setVisible(true);
		PasswordTextField.setVisible(false);
		passWordTextField.setVisible(true);
		passWordTextField.setText(PasswordTextField.getText());
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		eyeCloseImageView.setImage(eyeCloseImg);
		eyeOpenImageView.setImage(eyeOpenImg);
	}

	public void forgotPassWord(ActionEvent event) {
		LoginAnchorPane.setVisible(false);
		forgotAnchorPane.setVisible(true);
	}
	public void back(ActionEvent event) {
		LoginAnchorPane.setVisible(true);
		forgotAnchorPane.setVisible(false);
	}
}
