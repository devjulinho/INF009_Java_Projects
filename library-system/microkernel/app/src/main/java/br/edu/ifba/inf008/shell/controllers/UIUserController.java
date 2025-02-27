package br.edu.ifba.inf008.shell.controllers;

import br.edu.ifba.inf008.shell.models.BookModel;
import br.edu.ifba.inf008.shell.models.UserModel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UIUserController{
    
    private UIUserController(){
    }
    
    private static UIUserController instance;

    public static UIUserController getInstance(){
        if (instance == null)
            instance = new UIUserController();
        return instance;
    }

    private HBox createCard(UserModel user) {

        Label labelUser = new Label("ID: " + String.valueOf(user.getId()) + " - " + user.getName());
        labelUser.setFont(Font.font(16));
        labelUser.setTextFill(Color.DARKBLUE);

        Button accessUserButton = new Button("Access");
        accessUserButton.setStyle("-fx-font-size: 14px; -fx-font-weight:bold;");

        accessUserButton.setOnAction(e -> {
            Stage userStage = new Stage();
            userProfileScreen(user, userStage);
        });

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox card = new HBox(10, labelUser, spacer, accessUserButton);
        card.setAlignment(Pos.CENTER_LEFT);
        card.setPadding(new Insets(10));

        card.setMinWidth(850); // Largura mínima
        card.setMaxWidth(850); // Largura máxima
        card.setMinHeight(70); // Altura mínima
        card.setMaxHeight(70); // Altura máxima

        card.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        card.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));

        HBox.setHgrow(labelUser, Priority.ALWAYS);

        return card;
    }

    protected void userHomeScreen(Stage primaryStage){
        primaryStage.setTitle("Salvador's Library - Users");

        VBox vbox = new VBox(20);
        vbox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vbox, 1000, 600);

        Text title = new Text("Choose a option:");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        VBox.setMargin(title, new Insets(0, 0, 30, 0));

        Button button1 = new Button("Register a new user");
        Button button2 = new Button("All registered users");
        Button button3 = new Button("Back");

        button1.setMinSize(300, 120);
        button2.setMinSize(300, 120);
        button3.setMinSize(200, 50);

        button1.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        button2.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        HBox row1 = new HBox(10, button1, button2);
        row1.setAlignment(Pos.CENTER);
        VBox.setMargin(button3, new Insets(20, 0, 0, 0));
        vbox.getChildren().addAll(title, row1, button3);

        button1.setOnAction(e -> {
            registerUserScreen(primaryStage);
        });

        button2.setOnAction(e -> {
            allRegisteredUsersScreen(primaryStage);
        });

        button3.setOnAction(e -> {
            UIController.getInstance().start(primaryStage);
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void registerUserScreen(Stage primaryStage){
        VBox vbox = new VBox(20);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(30));

        Text secondTitle = new Text("Register a new user");
        secondTitle.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));

        Label label1 = new Label("Name:");
        label1.setStyle("-fx-font-size: 16px;");
        TextField field1 = new TextField();
        grid.add(label1, 0, 0);
        grid.add(field1, 1, 0);

        Button sendButton = new Button("Send");
        sendButton.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        sendButton.setOnAction(e -> {
            System.out.println("Dados enviados:");
            System.out.println("a: " + field1.getText());
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            userHomeScreen(primaryStage);
        });

        vbox.getChildren().addAll(secondTitle, grid, sendButton, backButton);

        Scene secondScene = new Scene(vbox, 1000, 600);

        primaryStage.setScene(secondScene);
        primaryStage.show();
    }

    private void allRegisteredUsersScreen(Stage primaryStage){
        primaryStage.setTitle("Salvador's Library - Users");

        Text title = new Text("All users registered");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        Button backButton = new Button("Back");
        backButton.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        backButton.setOnAction(e -> {
            userHomeScreen(primaryStage);
        });

        HBox header = new HBox();
        header.setAlignment(Pos.CENTER);
        header.setPadding(new Insets(10));

        Region centerSpacer = new Region();
        Region rightSpacer = new Region();
        HBox.setHgrow(centerSpacer, Priority.ALWAYS);
        HBox.setHgrow(rightSpacer, Priority.ALWAYS);
        header.getChildren().addAll(backButton, centerSpacer, title, rightSpacer);

        UserController.addUser("Nome1");
        UserController.addUser("Nome1");
        UserController.addUser("Nome1");
        UserController.addUser("Nome1");
        UserController.addUser("Nome1");
        UserController.addUser("Nome1");
        UserController.addUser("Nome1");
        UserController.addUser("Nome1");

        FlowPane flowPane = new FlowPane();
        flowPane.setPadding(new Insets(10));
        flowPane.setHgap(10);
        flowPane.setVgap(10);
        flowPane.setAlignment(Pos.CENTER);

        for (UserModel users : UserController.users) {
            HBox card = createCard(users);
            flowPane.getChildren().add(card);
        }

        ScrollPane scrollPane = new ScrollPane(flowPane);
        scrollPane.setFitToWidth(true); // Faz o ScrollPane ajustar a largura ao conteúdo
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Remove a barra de rolagem horizontal
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED); // Adiciona a barra de rolagem vertical quando necessário

        // Configurando o layout principal
        StackPane root = new StackPane(scrollPane);
        root.setPadding(new Insets(10));


        VBox vbox = new VBox(20);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(30));

        vbox.getChildren().addAll(header, root);

        // Criando a cena e exibindo a janela
        Scene scene = new Scene(vbox, 1000, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void userProfileScreen(UserModel user, Stage userStage){
        
        userStage.setTitle("Salvador's Library - Users");

        Label IdLabel = new Label("ID: " + user.getId());
        IdLabel.setStyle("-fx-font-size: 16px;");
        Label nameLabel = new Label("Name: " + user.getName());
        nameLabel.setStyle("-fx-font-size: 16px;");
        Label borrowedBooksLabel = new Label("\nBorrowed Books:\n");
        borrowedBooksLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        FlowPane flowPane = new FlowPane();
        flowPane.setPadding(new Insets(10));
        flowPane.setHgap(10);
        flowPane.setVgap(10);
        flowPane.setAlignment(Pos.CENTER_LEFT);

        for (BookModel book : user.getBorrowedBooks()) {
            VBox card = UIBookController.getInstance().createSmallCard(book);
            flowPane.getChildren().add(card);
        }

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> userStage.close());
        HBox backButtonBox = new HBox(10, backButton);
        backButtonBox.setAlignment(Pos.CENTER);

        VBox layout = new VBox(10, IdLabel, nameLabel, borrowedBooksLabel, flowPane, backButtonBox);
        layout.setPadding(new javafx.geometry.Insets(15));

        Scene scene = new Scene(layout, 800, 550);
        userStage.setTitle("User Details");
        userStage.setScene(scene);
        userStage.show();
    }
}