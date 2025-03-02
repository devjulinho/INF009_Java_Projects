package br.edu.ifba.inf008.interfaces;

import javafx.scene.control.Button;

public interface IUIController
{
    public abstract Button createButton(String buttonName, Runnable action);
}
