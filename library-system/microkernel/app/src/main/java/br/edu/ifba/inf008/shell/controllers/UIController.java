package br.edu.ifba.inf008.shell.controllers;

import br.edu.ifba.inf008.interfaces.ICore;
import br.edu.ifba.inf008.interfaces.IUIController;
import br.edu.ifba.inf008.shell.Core;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UIController extends Application implements IUIController
{
    private ICore core;
    private static UIController uiController;
    private boolean pluginFlag = false;
    private boolean deserializationFlag = false;

    public UIController() {
    }

    @Override
    public void init() {
        uiController = this;
    }

    public static UIController getInstance() {
        if (uiController == null)
            uiController = new UIController();
        return uiController;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Salvador's Library - Home");

        primaryStage.setOnCloseRequest(e ->{
            System.out.println("Fechando");
            IOController.getInstance().serialization();
            });

        VBox vbox = new VBox(20);
        vbox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vbox, 1000, 600);

        Text title = new Text("Welcome to Salvador's Library!");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        VBox.setMargin(title, new Insets(0, 0, 30, 0));
        
        Button booksButton = createButton("Books", () -> UIBookController.getInstance().bookHomeScreen(primaryStage));
        Button usersButton = createButton("Users", () -> UIUserController.getInstance().userHomeScreen(primaryStage));
        Button loanButton = createButton("Loan", () -> UILoanController.getInstance().loanHomeScreen(primaryStage));
        Button reportButton = createButton("Report", () -> UIReportsController.getInstance().reportsHomeScreen(primaryStage));
        Button button5 = new Button("Exit");

        button5.setMinSize(200, 50);

        HBox row1 = new HBox(10, booksButton, usersButton);
        row1.setAlignment(Pos.CENTER);
        HBox row2 = new HBox(10, loanButton, reportButton);
        row2.setAlignment(Pos.CENTER);
        VBox.setMargin(button5, new Insets(20, 0, 0, 0));
        vbox.getChildren().addAll(title, row1, row2, button5);

        button5.setOnAction(e -> {
            IOController.getInstance().serialization();
            Platform.exit();
        });

        if (pluginFlag == false){
            Core.getInstance().getPluginController().init();
            this.pluginFlag = true;
        }

        if (deserializationFlag == false){
            IOController.getInstance().deserialization();
            this.deserializationFlag = true;
        }

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public Button createButton(String buttonName, Runnable action){
        Button newButton = new Button(buttonName);
        newButton.setMinSize(300, 120);
        newButton.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        newButton.setOnAction(e -> action.run());

        return newButton;
    }
}
