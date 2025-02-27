package br.edu.ifba.inf008.shell.controllers;

import br.edu.ifba.inf008.shell.models.BookModel;
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

        card.setMinWidth(250); // Largura mínima
        card.setMaxWidth(250); // Largura máxima
        card.setMinHeight(180); // Altura mínima
        card.setMaxHeight(180); // Altura máxima

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

        card.setMinWidth(750); // Largura mínima
        card.setMaxWidth(750); // Largura máxima
        card.setMinHeight(60); // Altura mínima
        card.setMaxHeight(60); // Altura máxima

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

        Button button1 = new Button("Register a new book");
        Button button2 = new Button("All registered books");
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
            registerBookScreen(primaryStage);
        });

        button2.setOnAction(e -> {
            allRegisteredBooksScreen(primaryStage);
        });

        button3.setOnAction(e -> {
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
        TextField field1 = new TextField();
        grid.add(label1, 0, 0);
        grid.add(field1, 1, 0);

        Label label2 = new Label("Title:");
        label2.setStyle("-fx-font-size: 16px;");
        TextField field2 = new TextField();
        grid.add(label2, 0, 1);
        grid.add(field2, 1, 1);

        Label label3 = new Label("Author(s):");
        label3.setStyle("-fx-font-size: 16px;");
        TextField field3 = new TextField();
        grid.add(label3, 0, 2);
        grid.add(field3, 1, 2);

        Label label4 = new Label("Release Year");
        label4.setStyle("-fx-font-size: 16px;");
        TextField field4 = new TextField();
        grid.add(label4, 0, 3);
        grid.add(field4, 1, 3);

        Label label5 = new Label("Genre");
        label5.setStyle("-fx-font-size: 16px;");
        TextField field5 = new TextField();
        grid.add(label5, 0, 4);
        grid.add(field5, 1, 4);

        Button sendButton = new Button("Send");
        sendButton.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        sendButton.setOnAction(e -> {
            System.out.println("Dados enviados:");
            System.out.println("a: " + field1.getText());
            System.out.println("b: " + field2.getText());
            System.out.println("c: " + field3.getText());
            System.out.println("d: " + field4.getText());
            System.out.println("e: " + field5.getText());
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

        BookController.addNewBook("Nome1", "Nome2", "Nome3", 1000, "Nome4");
        BookController.addNewBook("Nome2", "Nome2", "Nome3", 1000, "Nome4");
        BookController.addNewBook("Nome3", "Nome2", "Nome3", 1000, "Nome4");
        BookController.addNewBook("Nome4", "Nome2", "Nome3", 1000, "Nome4");
        BookController.addNewBook("Nome1", "Nome2", "Nome3", 1000, "Nome4");
        BookController.addNewBook("Nome2", "Nome2", "Nome3", 1000, "Nome4");
        BookController.addNewBook("Nome3", "Nome2", "Nome3", 1000, "Nome4");
        BookController.addNewBook("Nome4", "Nome2", "Nome3", 1000, "Nome4");
        BookController.addNewBook("Nome1", "Nome2", "Nome3", 1000, "Nome4");
        BookController.addNewBook("Nome2", "Nome2", "Nome3", 1000, "Nome4");
        BookController.addNewBook("Nome3", "Nome2", "Nome3", 1000, "Nome4");
        BookController.addNewBook("Nome4", "Nome2", "Nome3", 1000, "Nome4");
        BookController.addNewBook("Nome1", "Nome2", "Nome3", 1000, "Nome4");
        BookController.addNewBook("Nome2", "Nome2", "Nome3", 1000, "Nome4");
        BookController.addNewBook("Nome3", "Nome2", "Nome3", 1000, "Nome4");
        BookController.addNewBook("Nome4", "Nome2", "Nome3", 1000, "Nome4");

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