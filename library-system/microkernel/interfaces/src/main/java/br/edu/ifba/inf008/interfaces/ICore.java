package br.edu.ifba.inf008.interfaces;

public abstract class ICore
{
    public static ICore getInstance() {
        return instance;
    }

    public abstract IUIController getUIController();
    public abstract IIOController getIOController();
    public abstract IPluginController getPluginController();
    public abstract IUIReportsController getUIReportsController();
    public abstract IBookController getBookController();
    public abstract ILoanController getLoanController();

    protected static ICore instance = null;
}
