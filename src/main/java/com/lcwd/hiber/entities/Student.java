package com.lcwd.hiber.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

import com.lcwd.hiber.entities.Certificate; 

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String college;
    private String phone;
    private boolean active;
    private String about;
    private String fatherName;

    // One-to-Many relationship between Student and Certificate
    // mappedBy = "student" → The "student" field in Certificate owns the relationship (foreign key)
    // fetch = FetchType.LAZY → Certificates will be loaded only when accessed (not automatically)
    // cascade = CascadeType.ALL → Any operation (save, update, delete) on Student also applies to Certificates
    // orphanRemoval = true → If a Certificate is removed from the Student's list, it will also be deleted from the DB
    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Certificate> certificates = new ArrayList<>();


    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public List<Certificate> getCertificates() {
        return certificates;
    }

   

    public void setCertificates(List<Certificate> certificates) {
        this.certificates = certificates;
    }

    // helper method to add certificate
 // helper method to add certificate
    public void addCertificate(Certificate certificate) {
        certificates.add(certificate);     
        certificate.setStudent(this);        
    }

    // helper method to remove certificate
    public void removeCertificate(Certificate certificate) {
        certificates.remove(certificate);     
        certificate.setStudent(null);       
    }

 
}
