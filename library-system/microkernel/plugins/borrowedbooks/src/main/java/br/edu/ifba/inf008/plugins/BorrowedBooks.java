package br.edu.ifba.inf008.plugins;

import br.edu.ifba.inf008.interfaces.ICore;
import br.edu.ifba.inf008.interfaces.IPlugin;
import br.edu.ifba.inf008.interfaces.IUIReportsController;
import javafx.scene.control.Button;

// import javafx.scene.control.MenuItem;
// import javafx.event.EventHandler;
// import javafx.event.ActionEvent;
// import javafx.scene.shape.Rectangle;
// import javafx.scene.paint.Color;

public class BorrowedBooks implements IPlugin
{
    public boolean init() {

        IUIReportsController uiReportsController = ICore.getInstance().getUIReportsController();

        Button newButton = uiReportsController.createButton("Teste", () -> System.out.println("Aeee"));

    //
    //     MenuItem menuItem = uiController.createMenuItem("Menu 1", "My Menu Item");
    //     menuItem.setOnAction(new EventHandler<ActionEvent>() {
    //         @Override
    //         public void handle(ActionEvent e) {
    //             System.out.println("I've been clicked!");
    //         }
    //     });
    //
    //     uiController.createTab("new tab", new Rectangle(200,200, Color.LIGHTSTEELBLUE));
    //
        return true;
    }
}
