package com.example.car_rental_project;

public class Car {
    private String carname,model,year,RPD,carnumber,imageID,description;

    public Car(String carname, String model, String year, String RPD, String carnumber, String imageID, String description) {
        this.carname = carname;
        this.model = model;
        this.year = year;
        this.RPD = RPD;
        this.carnumber = carnumber;
        this.imageID = imageID;
        this.description = description;
    }

    @Override
    public String toString() {
        return  "carname: " + carname + '\n' +
                "model: " + model + '\n' +
                "year: " + year + '\n' +
                "RPD: " + RPD + '\n';
    }

    public String getCarname() {
        return carname;
    }

    public void setCarname(String carname) {
        this.carname = carname;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRPD() {
        return RPD;
    }

    public void setRPD(String RPD) {
        this.RPD = RPD;
    }

    public String getCarnumber() {
        return carnumber;
    }

    public void setCarnumber(String carnumber) {
        this.carnumber = carnumber;
    }

    public String getImageID() {
        return imageID;
    }

    public void setImageID(String imageID) {
        this.imageID = imageID;
    }

    public String getDescreiption() {
        return description;
    }

    public void setDescreiption(String descreiption) {
        this.description = descreiption;
    }
}
