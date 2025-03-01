package br.edu.ifba.inf008.shell.controllers;

import java.time.LocalDate;
import java.time.Month;

import br.edu.ifba.inf008.shell.models.BookModel;
import br.edu.ifba.inf008.shell.models.LoanModel;
import br.edu.ifba.inf008.shell.models.UserModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class UILoanController{
    
    private UILoanController(){
    }
    
    private static UILoanController instance;

    public static UILoanController getInstance(){
        if (instance == null)
            instance = new UILoanController();
        return instance;
    }

    private HBox createCard(LoanModel loan) {

        Label labelLoanBook = new Label(loan.book.toString());
        labelLoanBook.setFont(Font.font(16));
        labelLoanBook.setTextFill(Color.DARKBLUE);

        Label labelLoanUser = new Label(loan.user.toString());
        labelLoanUser.setFont(Font.font(16));
        labelLoanUser.setTextFill(Color.DARKBLUE);

        Button accessUserButton = new Button("Return");
        accessUserButton.setStyle("-fx-font-size: 14px; -fx-font-weight:bold;");

        accessUserButton.setOnAction(e -> {
            System.out.println("Return book!");
        });

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        VBox bothLabel = new VBox(labelLoanBook, labelLoanUser);

        HBox card = new HBox(10, bothLabel, spacer, accessUserButton);
        card.setAlignment(Pos.CENTER_LEFT);
        card.setPadding(new Insets(10));

        card.setMinWidth(850);
        card.setMaxWidth(850);
        card.setMinHeight(70);
        card.setMaxHeight(70);

        card.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        card.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));

        HBox.setHgrow(bothLabel, Priority.ALWAYS);

        return card;
    }

    protected void loanHomeScreen(Stage primaryStage){
        primaryStage.setTitle("Salvador's Library - Loan");

        VBox vbox = new VBox(20);
        vbox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vbox, 1000, 600);

        Text title = new Text("Choose a option:");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        VBox.setMargin(title, new Insets(0, 0, 30, 0));

        Button newLoanButton = new Button("New Loan");
        Button loanReturnButton = new Button("Loan return");
        Button exitButton = new Button("Back");

        newLoanButton.setMinSize(300, 120);
        loanReturnButton.setMinSize(300, 120);
        exitButton.setMinSize(200, 50);

        newLoanButton.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        loanReturnButton.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        HBox row1 = new HBox(10, newLoanButton, loanReturnButton);
        row1.setAlignment(Pos.CENTER);
        VBox.setMargin(exitButton, new Insets(20, 0, 0, 0));
        vbox.getChildren().addAll(title, row1, exitButton);

        newLoanButton.setOnAction(e -> {
            newLoanScreen(primaryStage);
        });

        loanReturnButton.setOnAction(e -> {
            returnLoanScreen(primaryStage);
        });

        exitButton.setOnAction(e -> {
            UIController.getInstance().start(primaryStage);
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    protected void newLoanScreen(Stage primaryStage){
        primaryStage.setTitle("Salvador's Library - Loan");
        
        Label titleLabel = new Label("Select a book and a user:");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        TextField bookSearchField = new TextField();
        bookSearchField.setPromptText("Search for a book");
        
        TextField userSearchField = new TextField();
        userSearchField.setPromptText("Search for a user");
        
        ListView<BookModel> bookListView = new ListView<>();
        ListView<UserModel> userListView = new ListView<>();
        
        bookListView.getItems().addAll(BookController.books);
        userListView.getItems().addAll(UserController.users);
        bookListView.setPrefSize(850, 250);
        userListView.setPrefSize(850, 250);

        ObservableList<BookModel> bookList = FXCollections.observableArrayList(BookController.books);
        ObservableList<UserModel> userList = FXCollections.observableArrayList(UserController.users);

        FilteredList<BookModel> filteredBooks = new FilteredList<>(bookList, b -> b.available);
        FilteredList<UserModel> filteredUsers = new FilteredList<>(userList, u -> u.getBorrowedBooks().size() < 5);

        bookListView.setItems(filteredBooks);
        userListView.setItems(filteredUsers);

        bookSearchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredBooks.setPredicate(book ->
                newValue == null || newValue.isEmpty() || book.toString().toLowerCase().contains(newValue.toLowerCase())
            );
        });

        userSearchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredUsers.setPredicate(user -> 
                newValue == null || newValue.isEmpty() || user.toString().toLowerCase().contains(newValue.toLowerCase())
            );
        });
        
        Button loanButton = new Button("Finish");
        loanButton.setDisable(true);

        Button backButton = new Button("Back");
        
        bookListView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            loanButton.setDisable(bookListView.getSelectionModel().isEmpty() || userListView.getSelectionModel().isEmpty());
        });
        
        userListView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            loanButton.setDisable(bookListView.getSelectionModel().isEmpty() || userListView.getSelectionModel().isEmpty());
        });
        
        loanButton.setOnAction(e -> {
            BookModel selectedBook = bookListView.getSelectionModel().getSelectedItem();
            UserModel selectedUser = userListView.getSelectionModel().getSelectedItem();
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Empréstimo Realizado");
            alert.setHeaderText(null);
            alert.setContentText("O livro '" + selectedBook + "' foi emprestado para '" + selectedUser + "'.");
            alert.showAndWait();
        });

        backButton.setOnAction(e -> {
            loanHomeScreen(primaryStage);
        });

        VBox bookColumn = new VBox(10, bookSearchField, bookListView);
        VBox userColumn = new VBox(10, userSearchField, userListView);
        bookColumn.setAlignment(Pos.CENTER);
        userColumn.setAlignment(Pos.CENTER);
        
        HBox selectionBox = new HBox(20, bookColumn, userColumn);
        selectionBox.setAlignment(Pos.CENTER);
        
        VBox layout = new VBox(20, titleLabel, selectionBox, loanButton, backButton);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(layout, 1000, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    protected void returnLoanScreen(Stage primaryStage){
        primaryStage.setTitle("Salvador's Library - Loan");

        Text title = new Text("All loans");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        Button backButton = new Button("Back");
        backButton.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        backButton.setOnAction(e -> {
            loanHomeScreen(primaryStage);
        });

        HBox header = new HBox();
        header.setAlignment(Pos.CENTER);
        header.setPadding(new Insets(10));

        Region centerSpacer = new Region();
        Region rightSpacer = new Region();
        HBox.setHgrow(centerSpacer, Priority.ALWAYS);
        HBox.setHgrow(rightSpacer, Priority.ALWAYS);
        header.getChildren().addAll(backButton, centerSpacer, title, rightSpacer);

        LoanController.addNewLoan(new UserModel("NomeA"), new BookModel("NomeZ", "NomeZ", "Nome3", 1000, "Nome4"), LocalDate.of(2011, Month.JANUARY, 25));
        LoanController.addNewLoan(new UserModel("NomeB"), new BookModel("NomeX", "NomeX", "Nome3", 1000, "Nome4"), LocalDate.of(2011, Month.JANUARY, 25));
        LoanController.addNewLoan(new UserModel("NomeC"), new BookModel("NomeW", "NomeW", "Nome3", 1000, "Nome4"), LocalDate.of(2011, Month.JANUARY, 25));
        LoanController.addNewLoan(new UserModel("NomeD"), new BookModel("NomeY", "NomeY", "Nome3", 1000, "Nome4"), LocalDate.of(2011, Month.JANUARY, 25));
        LoanController.addNewLoan(new UserModel("NomeE"), new BookModel("NomeT", "NomeY", "Nome3", 1000, "Nome4"), LocalDate.of(2011, Month.JANUARY, 25));
        LoanController.addNewLoan(new UserModel("NomeF"), new BookModel("NomeU", "NomeU", "Nome3", 1000, "Nome4"), LocalDate.of(2011, Month.JANUARY, 25));
        
        TextField searchField = new TextField();
        searchField.setPromptText("Search loans...");
        searchField.setStyle("-fx-font-size: 14px;");

        ObservableList<LoanModel> loanList = FXCollections.observableArrayList(LoanController.loans);
        FilteredList<LoanModel> filteredLoans = new FilteredList<>(loanList, loan -> true);

        FlowPane flowPane = new FlowPane();
        flowPane.setPadding(new Insets(10));
        flowPane.setHgap(10);
        flowPane.setVgap(10);
        flowPane.setAlignment(Pos.CENTER);

        Runnable updateLoanView = () -> {
            flowPane.getChildren().clear();
            for (LoanModel loan : filteredLoans) {
                HBox card = createCard(loan);
                flowPane.getChildren().add(card);
            }
        };

        updateLoanView.run();

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredLoans.setPredicate(loan ->
                newValue == null || newValue.isEmpty() ||
                loan.user.getName().toLowerCase().contains(newValue.toLowerCase()) ||
                loan.book.getTitle().toLowerCase().contains(newValue.toLowerCase())
            );
            updateLoanView.run();
        });

        VBox contentBox = new VBox(10, searchField, flowPane);
        contentBox.setPadding(new Insets(10));

        ScrollPane scrollPane = new ScrollPane(contentBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        StackPane root = new StackPane(scrollPane);
        root.setPadding(new Insets(10));


        VBox vbox = new VBox(20);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(30));

        vbox.getChildren().addAll(header, root);

        Scene scene = new Scene(vbox, 1000, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}