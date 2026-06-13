package dao;

import database.DBConnection;
import model.Volunteer;

import java.sql.*;
import java.util.ArrayList;

public class VolunteerDAO {

    public boolean addVolunteer(Volunteer volunteer) {

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
                    "INSERT INTO volunteers(name,phone,email,city,skills) VALUES(?,?,?,?,?)";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, volunteer.getName());
            ps.setString(2, volunteer.getPhone());
            ps.setString(3, volunteer.getEmail());
            ps.setString(4, volunteer.getCity());
            ps.setString(5, volunteer.getSkills());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return false;
    }

    public ArrayList<Volunteer> getAllVolunteers() {

        ArrayList<Volunteer> list =
                new ArrayList<>();

        try {

            Connection con =
                    DBConnection.getConnection();

            Statement st =
                    con.createStatement();

            ResultSet rs =
                    st.executeQuery(
                            "SELECT * FROM volunteers"
                    );

            while(rs.next()) {

                Volunteer v =
                        new Volunteer();

                v.setId(rs.getInt("id"));
                v.setName(rs.getString("name"));
                v.setPhone(rs.getString("phone"));
                v.setEmail(rs.getString("email"));
                v.setCity(rs.getString("city"));
                v.setSkills(rs.getString("skills"));

                list.add(v);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }

    public boolean deleteVolunteer(int id) {

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
                    "DELETE FROM volunteers WHERE id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return false;
    }

    public ArrayList<Volunteer> searchVolunteer(String keyword) {

        ArrayList<Volunteer> list =
                new ArrayList<>();

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
                    "SELECT * FROM volunteers WHERE name LIKE ?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, "%" + keyword + "%");

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()) {

                Volunteer v =
                        new Volunteer();

                v.setId(rs.getInt("id"));
                v.setName(rs.getString("name"));
                v.setPhone(rs.getString("phone"));
                v.setEmail(rs.getString("email"));
                v.setCity(rs.getString("city"));
                v.setSkills(rs.getString("skills"));

                list.add(v);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }
}