package com.example.bloodbank;

public class DonorData {
    public String Name,Address,City,PhoneNo,UID;


    public DonorData(){

    }

    public DonorData(String Name, String Address, String City, String PhoneNo, String UID){

        this.Name = Name;
        this.Address = Address;
        this.City = City;
        this.PhoneNo = PhoneNo;
        this.UID = UID;

    }

    public String getUID(){
        return UID;
    }

    public void setUID(String UID){
        this.UID = UID;
    }

    public String getName() {
        return Name;
    }

    public void setDonorName(String donorName) {
        this.Name = Name;
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

    public void setPhoneNo(String Phoneno) {
        PhoneNo = Phoneno;
    }


}
