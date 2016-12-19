package com.cp.cs.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "patients")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Patient {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int hospitalNumber;

    private String firstName;
    private String lastName;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    public int getHospitalNumber() {
        return hospitalNumber;
    }

    public void setHospitalNo(int hospitalNumber) {
        this.hospitalNumber = hospitalNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
}
