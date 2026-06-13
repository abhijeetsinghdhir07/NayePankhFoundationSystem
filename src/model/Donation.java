package model;

public class Donation {

    private int id;
    private String donorName;
    private String email;
    private double amount;
    private String purpose;
    private String donationDate;

    public Donation() {
    }

    public Donation(int id,
                    String donorName,
                    String email,
                    double amount,
                    String purpose,
                    String donationDate) {

        this.id = id;
        this.donorName = donorName;
        this.email = email;
        this.amount = amount;
        this.purpose = purpose;
        this.donationDate = donationDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDonorName() {
        return donorName;
    }

    public void setDonorName(String donorName) {
        this.donorName = donorName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getDonationDate() {
        return donationDate;
    }

    public void setDonationDate(String donationDate) {
        this.donationDate = donationDate;
    }
}