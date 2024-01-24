package application;
	

import java.awt.Image;
import java.awt.Polygon;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class Main extends Application {
	
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		Parent root = FXMLLoader.load(getClass().getResource("DangNhap.fxml"));
		Scene scene = new Scene(root,javafx.scene.paint.Color.BLACK);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setTitle("Quản lý chung cư mini");
		stage.setResizable(true);
		javafx.scene.image.Image icon = new javafx.scene.image.Image("Anh1.jpg");
		stage.getIcons().add(icon);
		stage.setScene(scene);
		stage.show();
		stage.setOnCloseRequest(event -> {
			event.consume();
			logout(stage);
		});
	}
	public void logout(Stage stage) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Logout");
		alert.setHeaderText("You're about logout!");
		alert.setContentText("Are u sure to exit?");
		if(alert.showAndWait().get() == ButtonType.OK) {
			System.out.println("you are succesful logged out");
			stage.close();
		}
	}
		
//		Text text = new Text();
//		text.setText("Nothing to show");
//		text.setX(50);
//		text.setY(50);
//		text.setFill(Color.WHITE);
//		text.setFont(Font.font("Arial",50));
//		Line line = new Line();
//		line.setStartX(200);
//		line.setStartY(200);
//		line.setEndX(500);
//		line.setEndY(200);
//		line.setStroke(Color.BLUE);
//		line.setStrokeWidth(5);
//		line.setRotate(45);
//		Rectangle rectangle = new Rectangle();
//		rectangle.setX(250);
//		rectangle.setY(250);
//		rectangle.setWidth(100);
//		rectangle.setHeight(100);
//		rectangle.setFill(Color.SKYBLUE);
//		rectangle.setStrokeWidth(5);
//		rectangle.setStroke(Color.RED);
//		javafx.scene.shape.Polygon Triangle = new javafx.scene.shape.Polygon();
//		Triangle.getPoints().setAll(200.0,200.0,
//									300.0,300.0,
//									200.0,300.0);
//		Triangle.setFill(Color.BEIGE);
//		Triangle.setStrokeWidth(10);
//		Triangle.setStroke(Color.BROWN);
//		
//		Circle circle = new Circle();
//		circle.setCenterX(400);
//		circle.setCenterY(400);
//		circle.setRadius(100);
//		circle.setFill(Color.ORANGE);
//		stage.getIcons().add(icon);
//		stage.setTitle("Javafx study =)))");
//		stage.setWidth(500);
//		stage.setHeight(500);
//		stage.setResizable(false);
//		stage.setX(200);
//		stage.setY(50);
//		stage.setFullScreen(true);
//		stage.setFullScreenExitHint("you don't press esc unless u click 'q' ");
//		stage.setFullScreenExitKeyCombination(KeyCombination.valueOf("q"));
//		root.getChildren().add(text);
//		root.getChildren().add(line);
//		root.getChildren().add(rectangle);
//		root.getChildren().add(Triangle);
//		root.getChildren().add(circle);

	
}
