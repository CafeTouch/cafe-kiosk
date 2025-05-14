package view;

import javax.swing.*;
import java.awt.*;
import controller.AppController;

public class StartView extends JFrame {
    private AppController controller;

    public StartView(AppController controller) {
        this.controller = controller;
        setTitle("StartView");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
