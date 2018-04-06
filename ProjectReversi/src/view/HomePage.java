package view;

import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import controller.ViewController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class HomePage implements Page{
	public String usernm = "";
	
	public GridPane createPage() {
		try {
			TextField text = new TextField(usernm);
			Label label = new Label("Username: ");
			Button b1 = new Button("TicTacToe");
			Button b2 = new Button("Reversi");
			
			GridPane root = new GridPane();
			root.setAlignment(Pos.CENTER);
			root.add(label, 0, 0);
			root.add(text, 1, 0);
			root.add(b1, 0, 1);
			root.add(b2, 1, 1);
			
			b1.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent ae) {
					usernm = text.getText();
					try {
						ViewController.toGame('t');
					}
					catch(Exception ex) {
						text.setText(ex.getMessage());
					}
					
					//scene.setRoot(new GridFrame('t').createGrid());
					/*Main.mode = 't';
					System.out.println("b1: " + Main.usernm);
					stage.getScene().setRoot(Tictactoe.setup());*/
				}
			});
			b2.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent ae) {
					usernm = text.getText();
					try {
						ViewController.toGame('r');
					}
					catch(Exception ex) {
						text.setText(ex.getMessage());
					}
					//scene.setRoot(new GridFrame('r').createGrid());
					/*Main.mode = 'r';
					System.out.println("b2: " + Main.usernm);
					stage.getScene().setRoot(Reversi.setup());*/
				}
			});
			return root;
			//return scene = new Scene(root, 600, 600);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public String getUsernm() {
		return usernm;
	}/*
	public Scene getScene() {
		return scene;
	}*/
}