package view;

import javax.swing.*;
import java.awt.*;
import controller.AppController;

public class PaymentView extends JFrame {
    private AppController controller;

    public PaymentView(AppController controller) {
        this.controller = controller;
        setTitle("PaymentView");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
