package gui;

import dao.VolunteerDAO;
import model.Volunteer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class VolunteerForm extends JFrame {

    private JTextField txtName;
    private JTextField txtPhone;
    private JTextField txtEmail;
    private JTextField txtCity;
    private JTextField txtSkills;
    private JTextField txtSearch;

    private JButton btnSave;
    private JButton btnDelete;
    private JButton btnSearch;

    private JTable table;
    private DefaultTableModel model;

    private VolunteerDAO dao = new VolunteerDAO();

    public VolunteerForm() {

        setTitle("Volunteer Management");
        setSize(900, 500);
        setLocationRelativeTo(null);

        JPanel leftPanel = new JPanel(new GridLayout(6, 2, 5, 5));

        txtName = new JTextField();
        txtPhone = new JTextField();
        txtEmail = new JTextField();
        txtCity = new JTextField();
        txtSkills = new JTextField();

        leftPanel.add(new JLabel("Name"));
        leftPanel.add(txtName);

        leftPanel.add(new JLabel("Phone"));
        leftPanel.add(txtPhone);

        leftPanel.add(new JLabel("Email"));
        leftPanel.add(txtEmail);

        leftPanel.add(new JLabel("City"));
        leftPanel.add(txtCity);

        leftPanel.add(new JLabel("Skills"));
        leftPanel.add(txtSkills);

        btnSave = new JButton("Save Volunteer");
        leftPanel.add(btnSave);

        add(leftPanel, BorderLayout.WEST);

        model = new DefaultTableModel();

        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Phone");
        model.addColumn("Email");
        model.addColumn("City");
        model.addColumn("Skills");

        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();

        txtSearch = new JTextField(15);

        btnSearch = new JButton("Search");
        btnDelete = new JButton("Delete Selected");

        bottomPanel.add(new JLabel("Search"));
        bottomPanel.add(txtSearch);
        bottomPanel.add(btnSearch);
        bottomPanel.add(btnDelete);

        add(bottomPanel, BorderLayout.SOUTH);

        btnSave.addActionListener(e -> saveVolunteer());
        btnDelete.addActionListener(e -> deleteVolunteer());
        btnSearch.addActionListener(e -> searchVolunteer());

        loadVolunteers();
    }

    private void saveVolunteer() {

        Volunteer volunteer = new Volunteer();

        volunteer.setName(txtName.getText());
        volunteer.setPhone(txtPhone.getText());
        volunteer.setEmail(txtEmail.getText());
        volunteer.setCity(txtCity.getText());
        volunteer.setSkills(txtSkills.getText());

        if (dao.addVolunteer(volunteer)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Volunteer Added Successfully"
            );

            clearFields();
            loadVolunteers();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Failed to Add Volunteer"
            );
        }
    }

    private void loadVolunteers() {

        model.setRowCount(0);

        ArrayList<Volunteer> list =
                dao.getAllVolunteers();

        for (Volunteer v : list) {

            model.addRow(new Object[]{
                    v.getId(),
                    v.getName(),
                    v.getPhone(),
                    v.getEmail(),
                    v.getCity(),
                    v.getSkills()
            });
        }
    }

    private void deleteVolunteer() {

        int row = table.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Select a row first"
            );

            return;
        }

        int id = Integer.parseInt(
                model.getValueAt(row, 0).toString()
        );

        if (dao.deleteVolunteer(id)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Volunteer Deleted"
            );

            loadVolunteers();
        }
    }

    private void searchVolunteer() {

        String keyword = txtSearch.getText();

        model.setRowCount(0);

        ArrayList<Volunteer> list =
                dao.searchVolunteer(keyword);

        for (Volunteer v : list) {

            model.addRow(new Object[]{
                    v.getId(),
                    v.getName(),
                    v.getPhone(),
                    v.getEmail(),
                    v.getCity(),
                    v.getSkills()
            });
        }
    }

    private void clearFields() {

        txtName.setText("");
        txtPhone.setText("");
        txtEmail.setText("");
        txtCity.setText("");
        txtSkills.setText("");
    }
}