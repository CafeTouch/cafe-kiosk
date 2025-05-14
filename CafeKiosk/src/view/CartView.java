package view;

import javax.swing.*;
import java.awt.*;
import controller.AppController;

public class CartView extends JFrame {
    private AppController controller;

    public CartView(AppController controller) {
        this.controller = controller;
        setTitle("CartView");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
