package dao;

import database.DBConnection;
import model.Donation;

import java.sql.*;
import java.util.ArrayList;

public class DonationDAO {

    public boolean addDonation(Donation donation) {

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
                    "INSERT INTO donations(donor_name,email,amount,purpose,donation_date) VALUES(?,?,?,?,?)";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, donation.getDonorName());
            ps.setString(2, donation.getEmail());
            ps.setDouble(3, donation.getAmount());
            ps.setString(4, donation.getPurpose());
            ps.setString(5, donation.getDonationDate());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return false;
    }

    public ArrayList<Donation> getAllDonations() {

        ArrayList<Donation> list =
                new ArrayList<>();

        try {

            Connection con =
                    DBConnection.getConnection();

            Statement st =
                    con.createStatement();

            ResultSet rs =
                    st.executeQuery(
                            "SELECT * FROM donations"
                    );

            while(rs.next()) {

                Donation d =
                        new Donation();

                d.setId(rs.getInt("id"));
                d.setDonorName(rs.getString("donor_name"));
                d.setEmail(rs.getString("email"));
                d.setAmount(rs.getDouble("amount"));
                d.setPurpose(rs.getString("purpose"));
                d.setDonationDate(rs.getString("donation_date"));

                list.add(d);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }

    public boolean deleteDonation(int id) {

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
                    "DELETE FROM donations WHERE id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return false;
    }

    public ArrayList<Donation> searchDonation(String keyword) {

        ArrayList<Donation> list =
                new ArrayList<>();

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
                    "SELECT * FROM donations WHERE donor_name LIKE ?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, "%" + keyword + "%");

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()) {

                Donation d =
                        new Donation();

                d.setId(rs.getInt("id"));
                d.setDonorName(rs.getString("donor_name"));
                d.setEmail(rs.getString("email"));
                d.setAmount(rs.getDouble("amount"));
                d.setPurpose(rs.getString("purpose"));
                d.setDonationDate(rs.getString("donation_date"));

                list.add(d);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }
}