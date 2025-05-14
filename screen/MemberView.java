package view;

import javax.swing.*;
import java.awt.*;
import controller.AppController;

public class MemberView extends JFrame {
    private AppController controller;

    public MemberView(AppController controller) {
        this.controller = controller;
        setTitle("MemberView");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
