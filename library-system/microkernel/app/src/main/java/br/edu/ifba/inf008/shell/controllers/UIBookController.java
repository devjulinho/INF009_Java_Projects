package br.edu.ifba.inf008.shell.controllers;

import java.time.LocalDate;

import br.edu.ifba.inf008.shell.models.BookModel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
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

public class UIBookController{

    private UIBookController(){
    }
    
    private static UIBookController instance;

    public static UIBookController getInstance(){
        if (instance == null)
            instance = new UIBookController();
        return instance;
    }

    public VBox createCard(BookModel book) {

        Label labelTitle = new Label("Title: " + book.getTitle());
        labelTitle.setFont(Font.font(16));
        labelTitle.setTextFill(Color.DARKBLUE);
        
        Label labelISBN = new Label("ISBN: " + book.getIsbn());
        labelISBN.setFont(Font.font(14));
        labelISBN.setTextFill(Color.BLACK);

        Label labelAuthor = new Label("Author(s): " + book.getAuthor());
        labelAuthor.setFont(Font.font(14));
        labelAuthor.setTextFill(Color.BLACK);

        Label labelReleaseYear = new Label("Release Year: " + String.valueOf(book.getReleaseYear()));
        labelReleaseYear.setFont(Font.font(14));
        labelReleaseYear.setTextFill(Color.BLACK);

        Label labelGenre = new Label("Genre: " + book.getGenre());
        labelGenre.setFont(Font.font(14));
        labelGenre.setTextFill(Color.BLACK);

        VBox card = new VBox(10, labelTitle, labelISBN, labelAuthor, labelGenre, labelReleaseYear);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(10));

        card.setMinWidth(250);
        card.setMaxWidth(250);
        card.setMinHeight(180);
        card.setMaxHeight(180);

        card.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        card.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));

        return card;
    }

    public VBox createSmallCard(BookModel book) {

        Label labelText = new Label("Title: " + book.getTitle() + " (" + book.getReleaseYear() + ") - " + book.getAuthor());
        labelText.setFont(Font.font(16));
        labelText.setTextFill(Color.DARKBLUE);

        VBox card = new VBox(10, labelText);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(10));

        card.setMinWidth(750);
        card.setMaxWidth(750);
        card.setMinHeight(60);
        card.setMaxHeight(60);

        card.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        card.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));

        return card;
    }

    protected void bookHomeScreen(Stage primaryStage){
        primaryStage.setTitle("Salvador's Library - Books");

        VBox vbox = new VBox(20);
        vbox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vbox, 1000, 600);

        Text title = new Text("Choose a option:");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        VBox.setMargin(title, new Insets(0, 0, 30, 0));

        Button registerNewBookButton = new Button("Register a new book");
        Button allBooksButton = new Button("All registered books");
        Button exitButton = new Button("Back");

        registerNewBookButton.setMinSize(300, 120);
        allBooksButton.setMinSize(300, 120);
        exitButton.setMinSize(200, 50);

        registerNewBookButton.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        allBooksButton.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        HBox row1 = new HBox(10, registerNewBookButton, allBooksButton);
        row1.setAlignment(Pos.CENTER);
        VBox.setMargin(exitButton, new Insets(20, 0, 0, 0));
        vbox.getChildren().addAll(title, row1, exitButton);

        registerNewBookButton.setOnAction(e -> {
            registerBookScreen(primaryStage);
        });

        allBooksButton.setOnAction(e -> {
            allRegisteredBooksScreen(primaryStage);
        });

        exitButton.setOnAction(e -> {
            UIController.getInstance().start(primaryStage);
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void registerBookScreen(Stage primaryStage){
        VBox vbox = new VBox(20);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(30));

        Text secondTitle = new Text("Register a new book");
        secondTitle.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));

        Label label1 = new Label("ISBN:");
        label1.setStyle("-fx-font-size: 16px;");
        TextField isbnField = new TextField();
        grid.add(label1, 0, 0);
        grid.add(isbnField, 1, 0);

        Label label2 = new Label("Title:");
        label2.setStyle("-fx-font-size: 16px;");
        TextField titleField = new TextField();
        grid.add(label2, 0, 1);
        grid.add(titleField, 1, 1);

        Label label3 = new Label("Author(s):");
        label3.setStyle("-fx-font-size: 16px;");
        TextField authorField = new TextField();
        grid.add(label3, 0, 2);
        grid.add(authorField, 1, 2);

        Label label4 = new Label("Release Year:");
        label4.setStyle("-fx-font-size: 16px;");
        TextField yearField = new TextField();
        yearField.setTextFormatter(new TextFormatter<>(change -> 
            (change.getControlNewText().matches("\\d*")) ? change : null
            ));
        grid.add(label4, 0, 3);
        grid.add(yearField, 1, 3);

        Label label5 = new Label("Genre:");
        label5.setStyle("-fx-font-size: 16px;");
        TextField genreField = new TextField();
        grid.add(label5, 0, 4);
        grid.add(genreField, 1, 4);

        Button sendButton = new Button("Send");
        sendButton.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        sendButton.setOnAction(e -> {
            if(
                isbnField.getText().isBlank() || titleField.getText().isBlank() || authorField.getText().isBlank() ||
                yearField.getText().isBlank() || genreField.getText().isBlank()
            ){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Miss information!");
                alert.setHeaderText(null);
                alert.setContentText("There's some blank information.");
                alert.showAndWait();
            } else if (Integer.parseInt(yearField.getText()) < 0 || Integer.parseInt(yearField.getText()) > LocalDate.now().getYear()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Miss information!");
                alert.setHeaderText(null);
                alert.setContentText("The release year must be between 0 or " + LocalDate.now().getYear() + ".");
                alert.showAndWait();
            } 
            else {
                BookController.addNewBook(isbnField.getText(), titleField.getText(), authorField.getText(), Integer.parseInt(yearField.getText()), genreField.getText());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Successful registration");
                alert.setHeaderText(null);
                alert.setContentText("The book " + titleField.getText() + " was successfuly registrated.");
                alert.showAndWait();
            }
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            bookHomeScreen(primaryStage);
        });

        vbox.getChildren().addAll(secondTitle, grid, sendButton, backButton);

        Scene secondScene = new Scene(vbox, 1000, 600);

        primaryStage.setScene(secondScene);
        primaryStage.show();
    }

    private void allRegisteredBooksScreen(Stage primaryStage){
        primaryStage.setTitle("Salvador's Library - Books");

        Text title = new Text("All books registered");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        Button backButton = new Button("Back");
        backButton.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        // Configurando a ação do botão "Voltar"
        backButton.setOnAction(e -> {
            bookHomeScreen(primaryStage);
        });

        // Criando um HBox para organizar o título e o botão "Voltar"
        HBox header = new HBox(); // Espaçamento de 10 entre os elementos
        header.setAlignment(Pos.CENTER); // Alinha o título à esquerda e o botão à direita
        header.setPadding(new Insets(10)); // Adiciona um padding ao redor do HBox

        Region centerSpacer = new Region();
        Region rightSpacer = new Region();
        HBox.setHgrow(centerSpacer, Priority.ALWAYS);
        HBox.setHgrow(rightSpacer, Priority.ALWAYS);
        header.getChildren().addAll(backButton, centerSpacer, title, rightSpacer);

        // BookController.addNewBook("NomeDisp1", "NomeDisp1", "Nome3", 1000, "Nome4");
        // BookController.addNewBook("NomeDisp2", "NomeDisp2", "Nome3", 1000, "Nome4");
        // BookController.addNewBook("Nome3", "Nome2", "Nome3", 1000, "Nome4");
        // BookController.addNewBook("Nome4", "Nome2", "Nome3", 1000, "Nome4");
        // BookController.addNewBook("Nome1", "Nome2", "Nome3", 1000, "Nome4");
        // BookController.addNewBook("Nome2", "Nome2", "Nome3", 1000, "Nome4");
        // BookController.addNewBook("Nome3", "Nome2", "Nome3", 1000, "Nome4");
        // BookController.addNewBook("Nome4", "Nome2", "Nome3", 1000, "Nome4");
        // BookController.addNewBook("Nome1", "Nome2", "Nome3", 1000, "Nome4");
        // BookController.addNewBook("Nome2", "Nome2", "Nome3", 1000, "Nome4");
        // BookController.addNewBook("Nome3", "Nome2", "Nome3", 1000, "Nome4");
        // BookController.addNewBook("Nome4", "Nome2", "Nome3", 1000, "Nome4");
        // BookController.addNewBook("Nome1", "Nome2", "Nome3", 1000, "Nome4");
        // BookController.addNewBook("Nome2", "Nome2", "Nome3", 1000, "Nome4");
        // BookController.addNewBook("Nome3", "Nome2", "Nome3", 1000, "Nome4");
        // BookController.addNewBook("Nome4", "Nome2", "Nome3", 1000, "Nome4");

        FlowPane flowPane = new FlowPane();
        flowPane.setPadding(new Insets(10));
        flowPane.setHgap(10);
        flowPane.setVgap(10);
        flowPane.setAlignment(Pos.CENTER);

        for (BookModel book : BookController.books) {
            VBox card = createCard(book);
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
}