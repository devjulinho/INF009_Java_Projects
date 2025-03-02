package br.edu.ifba.inf008.shell.controllers;

import java.util.ArrayList;

import br.edu.ifba.inf008.interfaces.IUIReportsController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UIReportsController implements IUIReportsController{
    private static UIReportsController instance;
    private static ArrayList<Button> buttons = new ArrayList<>();
    private static UIReportsController uiReportsController;

    public UIReportsController() {
    }

    @Override
    public void init() {
        uiReportsController = this;
    }

    public static UIReportsController getInstance() {
        if (instance == null)
            instance = new UIReportsController();
        return instance;
    }

    protected void reportsHomeScreen(Stage primaryStage){
        primaryStage.setTitle("Salvador's Library - Books");

        VBox vbox = new VBox(20);
        vbox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vbox, 1000, 600);

        Text title = new Text("Choose a option:");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        VBox.setMargin(title, new Insets(0, 0, 30, 0));

        Button exitButton = new Button("Back");
        exitButton.setMinSize(200, 50);

        HBox row1 = new HBox();
        row1.setAlignment(Pos.CENTER);
        VBox.setMargin(exitButton, new Insets(20, 0, 0, 0));
        vbox.getChildren().addAll(title, row1, exitButton);

        for (Button button : UIReportsController.buttons){
            row1.getChildren().add(button);
        }

        exitButton.setOnAction(e -> {
            UIController.getInstance().start(primaryStage);
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public Button createButton(String buttonName, Runnable action){
        Button newButton = new Button(buttonName);
        newButton.setMinSize(300, 120);
        newButton.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        newButton.setOnAction(e -> action.run());

        buttons.add(newButton);

        return newButton;
    }

}