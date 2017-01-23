package com.loveholidays.tech.test.model;

/**
 * Created by chaitanyanaidu on 1/22/17.
 */
public class StreetAddress {

    private String streetName;
    private String streetAddress;

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public StreetAddress(String streetName, String streetAddress){
        this.streetName=streetName;
        this.streetAddress=streetAddress;
    }
    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

}
