package br.edu.ifba.inf008.plugins;

import java.util.ArrayList;

import br.edu.ifba.inf008.interfaces.IBookModel;
import br.edu.ifba.inf008.interfaces.ICore;
import br.edu.ifba.inf008.interfaces.ILoanController;
import br.edu.ifba.inf008.interfaces.ILoanModel;
import br.edu.ifba.inf008.interfaces.IPlugin;
import br.edu.ifba.inf008.interfaces.IUIReportsController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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

public class OverdueBooks implements IPlugin{

    public ILoanController loanController = ICore.getInstance().getLoanController();

    public boolean init() {

        IUIReportsController uiReportsController = ICore.getInstance().getUIReportsController();
        Stage newStage = new Stage();
        Button newButton = uiReportsController.createButton("Overdue Books", () -> borrowedBooksScreen(newStage));

        return true;
    }

    public VBox createSmallCard(ILoanModel loan, IBookModel book) {
        Label labelTitle = new Label("Title: " + book.getTitle() + " (" + book.getReleaseYear() + ") - " + book.getAuthor());
        labelTitle.setFont(Font.font(16));
        labelTitle.setTextFill(Color.DARKBLUE);

        Label labelUser = new Label("User: " + loan.getUserName());
        labelUser.setFont(Font.font(14));
        labelUser.setTextFill(Color.DARKBLUE);

        Label labelDays = new Label("Days late: " + loan.amountOfLateDays() + "   |   Fine amount: $ " + String.format("%.2f", loan.getFineAmount()));
        labelDays.setFont(Font.font(14));
        labelDays.setTextFill(Color.DARKBLUE);

        VBox card = new VBox(10, labelTitle, labelUser, labelDays);
        card.setAlignment(Pos.CENTER_LEFT);
        card.setPadding(new Insets(10));

        card.setMinWidth(750);
        card.setMaxWidth(750);
        card.setMinHeight(100);
        card.setMaxHeight(100);

        card.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        card.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));

        return card;
    }

    private void borrowedBooksScreen(Stage newStage){
        ArrayList<ILoanModel> loans = loanController.getAllLoans();
        newStage.setTitle("Overdue Books");

        Text title = new Text("Overdue Books");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        Button backButton = new Button("Back");
        backButton.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        backButton.setOnAction(e -> {
            newStage.close();
        });

        HBox header = new HBox();
        header.setAlignment(Pos.CENTER);
        header.setPadding(new Insets(10));

        Region centerSpacer = new Region();
        Region rightSpacer = new Region();
        HBox.setHgrow(centerSpacer, Priority.ALWAYS);
        HBox.setHgrow(rightSpacer, Priority.ALWAYS);
        header.getChildren().addAll(backButton, centerSpacer, title, rightSpacer);

        FlowPane flowPane = new FlowPane();
        flowPane.setPadding(new Insets(10));
        flowPane.setHgap(10);
        flowPane.setVgap(10);
        flowPane.setAlignment(Pos.CENTER);

        ObservableList<ILoanModel> loanList = FXCollections.observableArrayList(loans);
        FilteredList<ILoanModel> filteredLoans = new FilteredList<>(loanList, l -> l.isLate() && l.getFinalDate() == null);

        for (ILoanModel loan : filteredLoans) {
            ArrayList<IBookModel> books = loan.getBorrowedBooks();

            for (IBookModel book : books){
                VBox card = createSmallCard(loan, book);
                flowPane.getChildren().add(card);
            }
        }

        ScrollPane scrollPane = new ScrollPane(flowPane);
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
        newStage.setScene(scene);
        newStage.show();
    }
}
