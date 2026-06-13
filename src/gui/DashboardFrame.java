package gui;

import javax.swing.*;
import java.awt.*;

public class DashboardFrame extends JFrame {

    private JButton volunteerBtn;
    private JButton donationBtn;
    private JButton reportBtn;
    private JButton logoutBtn;

    public DashboardFrame() {

        setTitle("NayePankh Foundation Dashboard");

        setSize(500, 400);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel title =
                new JLabel(
                        "NayePankh Foundation Dashboard",
                        JLabel.CENTER
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        20
                )
        );

        add(title, BorderLayout.NORTH);

        JPanel panel =
                new JPanel(
                        new GridLayout(4,1,15,15)
                );

        volunteerBtn =
                new JButton(
                        "Volunteer Management"
                );

        donationBtn =
                new JButton(
                        "Donation Management"
                );

        reportBtn =
                new JButton(
                        "Reports"
                );

        logoutBtn =
                new JButton(
                        "Logout"
                );

        panel.add(volunteerBtn);
        panel.add(donationBtn);
        panel.add(reportBtn);
        panel.add(logoutBtn);

        add(panel, BorderLayout.CENTER);

        volunteerBtn.addActionListener(e ->
                new VolunteerForm()
                        .setVisible(true)
        );

        donationBtn.addActionListener(e ->
                new DonationForm()
                        .setVisible(true)
        );

        reportBtn.addActionListener(e ->
                new ReportFrame()
                        .setVisible(true)
        );

        logoutBtn.addActionListener(e -> {

            dispose();

            new LoginFrame()
                    .setVisible(true);

        });
    }
}