package view;

import javax.swing.*;
import java.awt.*;
import controller.AppController;

public class MenuView extends JFrame {
    private AppController controller;

    public MenuView(AppController controller) {
        this.controller = controller;
        setTitle("MenuView");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
