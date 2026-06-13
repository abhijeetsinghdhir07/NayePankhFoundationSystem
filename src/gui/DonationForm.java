package gui;

import dao.DonationDAO;
import model.Donation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class DonationForm extends JFrame {

    private JTextField txtDonorName;
    private JTextField txtEmail;
    private JTextField txtAmount;
    private JTextField txtPurpose;
    private JTextField txtDate;
    private JTextField txtSearch;

    private JButton btnSave;
    private JButton btnDelete;
    private JButton btnSearch;

    private JTable table;
    private DefaultTableModel model;

    private DonationDAO dao = new DonationDAO();

    public DonationForm() {

        setTitle("Donation Management");

        setSize(950, 500);

        setLocationRelativeTo(null);

        JPanel leftPanel =
                new JPanel(new GridLayout(6,2,5,5));

        txtDonorName = new JTextField();
        txtEmail = new JTextField();
        txtAmount = new JTextField();
        txtPurpose = new JTextField();
        txtDate = new JTextField();

        leftPanel.add(new JLabel("Donor Name"));
        leftPanel.add(txtDonorName);

        leftPanel.add(new JLabel("Email"));
        leftPanel.add(txtEmail);

        leftPanel.add(new JLabel("Amount"));
        leftPanel.add(txtAmount);

        leftPanel.add(new JLabel("Purpose"));
        leftPanel.add(txtPurpose);

        leftPanel.add(new JLabel("Date (YYYY-MM-DD)"));
        leftPanel.add(txtDate);

        btnSave =
                new JButton("Save Donation");

        leftPanel.add(btnSave);

        add(leftPanel, BorderLayout.WEST);

        model = new DefaultTableModel();

        model.addColumn("ID");
        model.addColumn("Donor Name");
        model.addColumn("Email");
        model.addColumn("Amount");
        model.addColumn("Purpose");
        model.addColumn("Date");

        table = new JTable(model);

        JScrollPane scrollPane =
                new JScrollPane(table);

        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel =
                new JPanel();

        txtSearch =
                new JTextField(15);

        btnSearch =
                new JButton("Search");

        btnDelete =
                new JButton("Delete Selected");

        bottomPanel.add(new JLabel("Search"));
        bottomPanel.add(txtSearch);
        bottomPanel.add(btnSearch);
        bottomPanel.add(btnDelete);

        add(bottomPanel, BorderLayout.SOUTH);

        btnSave.addActionListener(
                e -> saveDonation()
        );

        btnDelete.addActionListener(
                e -> deleteDonation()
        );

        btnSearch.addActionListener(
                e -> searchDonation()
        );

        loadDonations();
    }

    private void saveDonation() {

        try {

            Donation donation =
                    new Donation();

            donation.setDonorName(
                    txtDonorName.getText()
            );

            donation.setEmail(
                    txtEmail.getText()
            );

            donation.setAmount(
                    Double.parseDouble(
                            txtAmount.getText()
                    )
            );

            donation.setPurpose(
                    txtPurpose.getText()
            );

            donation.setDonationDate(
                    txtDate.getText()
            );

            if(dao.addDonation(donation)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Donation Added Successfully"
                );

                clearFields();

                loadDonations();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Failed to Add Donation"
                );
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid Amount"
            );
        }
    }

    private void loadDonations() {

        model.setRowCount(0);

        ArrayList<Donation> list =
                dao.getAllDonations();

        for(Donation d : list) {

            model.addRow(new Object[] {

                    d.getId(),
                    d.getDonorName(),
                    d.getEmail(),
                    d.getAmount(),
                    d.getPurpose(),
                    d.getDonationDate()

            });
        }
    }

    private void deleteDonation() {

        int row =
                table.getSelectedRow();

        if(row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Select a row first"
            );

            return;
        }

        int id =
                Integer.parseInt(
                        model.getValueAt(row,0)
                                .toString()
                );

        if(dao.deleteDonation(id)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Donation Deleted"
            );

            loadDonations();
        }
    }

    private void searchDonation() {

        String keyword =
                txtSearch.getText();

        model.setRowCount(0);

        ArrayList<Donation> list =
                dao.searchDonation(keyword);

        for(Donation d : list) {

            model.addRow(new Object[] {

                    d.getId(),
                    d.getDonorName(),
                    d.getEmail(),
                    d.getAmount(),
                    d.getPurpose(),
                    d.getDonationDate()

            });
        }
    }

    private void clearFields() {

        txtDonorName.setText("");
        txtEmail.setText("");
        txtAmount.setText("");
        txtPurpose.setText("");
        txtDate.setText("");
    }
}