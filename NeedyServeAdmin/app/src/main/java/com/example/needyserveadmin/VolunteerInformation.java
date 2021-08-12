package com.example.needyserveadmin;

public class VolunteerInformation {
    private String id, name, email, phone, gender, donor_status;

    public VolunteerInformation(String id, String name, String email, String phone, String gender, String donor_status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.donor_status = donor_status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDonor_status() {
        return donor_status;
    }

    public void setDonor_status(String donor_status) {
        this.donor_status = donor_status;
    }
}
