package org.example.GUI.Components.FormAuth;

import org.example.GUI.Application.Application;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class LoginForm extends JPanel {

    public LoginForm() {
        setLayout(new BorderLayout());

        JLabel label = new JLabel("Dummy Login - click to enter application", JLabel.CENTER);
        JButton loginButton = new JButton("Login");

        loginButton.addActionListener(e -> Application.login());

        add(label, BorderLayout.CENTER);
        add(loginButton, BorderLayout.SOUTH);
    }
}

