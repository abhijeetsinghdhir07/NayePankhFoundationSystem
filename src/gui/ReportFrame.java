package gui;

import database.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ReportFrame extends JFrame {

    private JLabel lblVolunteers;
    private JLabel lblDonations;
    private JLabel lblAmount;

    public ReportFrame() {

        setTitle("Reports");

        setSize(500,300);

        setLocationRelativeTo(null);

        setLayout(new GridLayout(3,1,10,10));

        lblVolunteers =
                new JLabel(
                        "Total Volunteers : 0",
                        JLabel.CENTER
                );

        lblDonations =
                new JLabel(
                        "Total Donations : 0",
                        JLabel.CENTER
                );

        lblAmount =
                new JLabel(
                        "Total Donation Amount : ₹0",
                        JLabel.CENTER
                );

        lblVolunteers.setFont(
                new Font("Arial",
                        Font.BOLD,
                        18)
        );

        lblDonations.setFont(
                new Font("Arial",
                        Font.BOLD,
                        18)
        );

        lblAmount.setFont(
                new Font("Arial",
                        Font.BOLD,
                        18)
        );

        add(lblVolunteers);
        add(lblDonations);
        add(lblAmount);

        loadReportData();
    }

    private void loadReportData() {

        try {

            Connection con =
                    DBConnection.getConnection();

            Statement st =
                    con.createStatement();

            ResultSet rs1 =
                    st.executeQuery(
                            "SELECT COUNT(*) FROM volunteers"
                    );

            if(rs1.next()) {

                lblVolunteers.setText(
                        "Total Volunteers : "
                                + rs1.getInt(1)
                );
            }

            ResultSet rs2 =
                    st.executeQuery(
                            "SELECT COUNT(*) FROM donations"
                    );

            if(rs2.next()) {

                lblDonations.setText(
                        "Total Donations : "
                                + rs2.getInt(1)
                );
            }

            ResultSet rs3 =
                    st.executeQuery(
                            "SELECT SUM(amount) FROM donations"
                    );

            if(rs3.next()) {

                double totalAmount =
                        rs3.getDouble(1);

                lblAmount.setText(
                        "Total Donation Amount : ₹"
                                + totalAmount
                );
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage()
            );
        }
    }
}