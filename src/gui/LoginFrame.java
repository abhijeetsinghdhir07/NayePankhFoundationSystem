package gui;

import database.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginFrame extends JFrame {

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    public LoginFrame() {

        setTitle("NayePankh Foundation Login");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel title = new JLabel(
                "NayePankh Foundation Login",
                JLabel.CENTER
        );

        add(title, BorderLayout.NORTH);

        JPanel panel = new JPanel(
                new GridLayout(3, 2, 10, 10)
        );

        panel.add(new JLabel("Username"));

        txtUsername = new JTextField();
        panel.add(txtUsername);

        panel.add(new JLabel("Password"));

        txtPassword = new JPasswordField();
        panel.add(txtPassword);

        btnLogin = new JButton("Login");

        panel.add(new JLabel());
        panel.add(btnLogin);

        add(panel, BorderLayout.CENTER);

        btnLogin.addActionListener(e -> login());
    }

    private void login() {

        String username = txtUsername.getText();

        String password =
                String.valueOf(
                        txtPassword.getPassword()
                );

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
                    "SELECT * FROM users WHERE username=? AND password=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Login Successful"
                );

                dispose();

                new DashboardFrame()
                        .setVisible(true);

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Invalid Username or Password"
                );
            }

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage()
            );
        }
    }
}