package com.example.bloodbank;

public class Data {

     String Fullname;
     String Address;
     String City;
     String PhoneNo;

     public Data(){}

    public Data(String fullname, String address, String city, String phoneNo) {
        Fullname = fullname;
        Address = address;
        City = city;
        PhoneNo = phoneNo;
    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String fullname) {
        Fullname = fullname;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        PhoneNo = phoneNo;
    }
}




