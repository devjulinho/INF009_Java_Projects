package br.edu.ifba.inf008.plugins;

import br.edu.ifba.inf008.interfaces.IPlugin;

public class MyPlugin implements IPlugin
{
    public boolean init() {
        // IUIController uiController = ICore.getInstance().getUIController();
    
        // MenuItem menuItem = uiController.createMenuItem("Menu 1", "My Menu Item");
        // menuItem.setOnAction(new EventHandler<ActionEvent>() {
        //     @Override
        //     public void handle(ActionEvent e) {
        //         System.out.println("I've been clicked!");
        //     }
        // });
    
        // uiController.createTab("new tab", new Rectangle(200,200, Color.LIGHTSTEELBLUE));

        return true;
    }
}
