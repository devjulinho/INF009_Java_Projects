package br.edu.ifba.inf008.interfaces;

import javafx.scene.control.Button;

public interface IUIReportsController
{
    public abstract void init();
    public abstract Button createButton(String buttonName, Runnable action);
}