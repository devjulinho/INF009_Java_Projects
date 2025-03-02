package br.edu.ifba.inf008.interfaces;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;

public interface IUIController
{
    public abstract MenuItem createMenuItem(String menuText, String menuItemText);
    public abstract boolean createTab(String tabText, Node contents);
    public abstract Button createButton(String buttonName, Runnable action);
}
