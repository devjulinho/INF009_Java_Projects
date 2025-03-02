package br.edu.ifba.inf008.shell;

import br.edu.ifba.inf008.interfaces.IAuthenticationController;
import br.edu.ifba.inf008.interfaces.IBookController;
import br.edu.ifba.inf008.interfaces.ICore;
import br.edu.ifba.inf008.interfaces.IIOController;
import br.edu.ifba.inf008.interfaces.ILoanController;
import br.edu.ifba.inf008.interfaces.IPluginController;
import br.edu.ifba.inf008.interfaces.IUIController;
import br.edu.ifba.inf008.interfaces.IUIReportsController;
import br.edu.ifba.inf008.shell.controllers.AuthenticationController;
import br.edu.ifba.inf008.shell.controllers.BookController;
import br.edu.ifba.inf008.shell.controllers.IOController;
import br.edu.ifba.inf008.shell.controllers.LoanController;
import br.edu.ifba.inf008.shell.controllers.PluginController;
import br.edu.ifba.inf008.shell.controllers.UIController;
import br.edu.ifba.inf008.shell.controllers.UIReportsController;

public class Core extends ICore
{
    private Core() {}

    public static boolean init() {
        if (instance != null) {
            System.out.println("Fatal error: core is already initialized!");
            System.exit(-1);
        }

        instance = new Core();
        UIController.launch(UIController.class);

        return true;
    }

    public IUIController getUIController() {
        return UIController.getInstance();
    }
    public IAuthenticationController getAuthenticationController() {
        return authenticationController;
    }
    public IIOController getIOController() {
        return ioController;
    }
    public IPluginController getPluginController() {
        return pluginController;
    }

    public IUIReportsController getUIReportsController(){
        return uiReportsController;
    }

    public IBookController getBookController(){
        return bookController;
    }

    public ILoanController getLoanController(){
        return loanController;
    }

    private IAuthenticationController authenticationController = new AuthenticationController();
    private IIOController ioController = new IOController();
    private IPluginController pluginController = new PluginController();
    private IUIReportsController uiReportsController = new UIReportsController();
    private IBookController bookController = BookController.getInstance();
    private ILoanController loanController = LoanController.getInstance();
}
