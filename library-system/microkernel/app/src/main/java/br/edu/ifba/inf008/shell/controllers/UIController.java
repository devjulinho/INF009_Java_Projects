package br.edu.ifba.inf008.shell.controllers;

import br.edu.ifba.inf008.interfaces.ICore;
import br.edu.ifba.inf008.interfaces.IUIController;
import br.edu.ifba.inf008.shell.models.BookModel;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UIController extends Application implements IUIController
{
    private ICore core;
    private MenuBar menuBar;
    private TabPane tabPane;
    private static UIController uiController;

    public UIController() {
    }

    @Override
    public void init() {
        uiController = this;
    }

    public static UIController getInstance() {
        return uiController;
    }

    private VBox createCard(BookModel book) {

        Label labelISBN = new Label(book.getIsbn());
        labelISBN.setFont(Font.font(16));
        labelISBN.setTextFill(Color.DARKBLUE);

        Label labelTitle = new Label(book.getTitle());
        labelTitle.setFont(Font.font(14));
        labelTitle.setTextFill(Color.DARKGREEN);

        Label labelAuthor = new Label(book.getAuthor());
        labelAuthor.setFont(Font.font(14));
        labelAuthor.setTextFill(Color.DARKGREEN);

        Label labelTitle = new Label(book.getAuthor());
        labelISBN.setFont(Font.font(14));
        labelISBN.setTextFill(Color.DARKGREEN);

        Label labelTitle = new Label(book.getAuthor());
        labelISBN.setFont(Font.font(14));
        labelISBN.setTextFill(Color.DARKGREEN);

        VBox card = new VBox(10, labelISBN, labelTitle);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(10));
        card.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        card.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));

        return card;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Salvador's Library - Home");

        VBox vbox = new VBox(20);
        vbox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vbox, 1000, 600);

        Text title = new Text("Welcome to Salvador's Library!");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        VBox.setMargin(title, new Insets(0, 0, 30, 0));

        Button button1 = new Button("Books");
        Button button2 = new Button("Users");
        Button button3 = new Button("Loans");
        Button button4 = new Button("Reports");
        Button button5 = new Button("Exit");

        button1.setMinSize(300, 120);
        button2.setMinSize(300, 120);
        button3.setMinSize(300, 120);
        button4.setMinSize(300, 120);
        button5.setMinSize(200, 50);

        button1.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        button2.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        button3.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        button4.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        HBox row1 = new HBox(10, button1, button2);
        row1.setAlignment(Pos.CENTER);
        HBox row2 = new HBox(10, button3, button4);
        row2.setAlignment(Pos.CENTER);
        VBox.setMargin(button5, new Insets(20, 0, 0, 0));
        vbox.getChildren().addAll(title, row1, row2, button5);

        button1.setOnAction(e -> {
            bookHomeScreen(primaryStage);
        });

        button5.setOnAction(e -> {
            Platform.exit();
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void bookHomeScreen(Stage primaryStage){
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
            start(primaryStage);
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
        primaryStage.setTitle("Tabela de BookModel");

        BookController.addNewBook("Nome1", "Nome2", "Nome3", 1000, "Nome4");
        BookController.addNewBook("Nome2", "Nome2", "Nome3", 1000, "Nome4");
        BookController.addNewBook("Nome3", "Nome2", "Nome3", 1000, "Nome4");
        BookController.addNewBook("Nome4", "Nome2", "Nome3", 1000, "Nome4");

        FlowPane flowPane = new FlowPane();
        flowPane.setPadding(new Insets(10));
        flowPane.setHgap(10); // Espaçamento horizontal entre os cards
        flowPane.setVgap(10); // Espaçamento vertical entre os cards
        flowPane.setAlignment(Pos.CENTER);

        // Adicionando cards para cada pessoa
        for (BookModel book : BookController.books) {
            VBox card = createCard(book);
            flowPane.getChildren().add(card);
        }

        // Configurando o layout principal
        StackPane root = new StackPane(flowPane);
        root.setPadding(new Insets(10));

        // Criando a cena e exibindo a janela
        Scene scene = new Scene(root, 1000, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    
    public MenuItem createMenuItem(String menuText, String menuItemText) {
        // Criar o menu caso ele nao exista
        Menu newMenu = null;
        for (Menu menu : menuBar.getMenus()) {
            if (menu.getText() == menuText) {
                newMenu = menu;
                break;
            }
        }
        if (newMenu == null) {
            newMenu = new Menu(menuText);
            menuBar.getMenus().add(newMenu);
        }

        // Criar o menu item neste menu
        MenuItem menuItem = new MenuItem(menuItemText);
        newMenu.getItems().add(menuItem);

        return menuItem;
    }

    public boolean createTab(String tabText, Node contents) {
        Tab tab = new Tab();
        tab.setText(tabText);
        tab.setContent(contents);
        tabPane.getTabs().add(tab);

        return true;
    }
}
