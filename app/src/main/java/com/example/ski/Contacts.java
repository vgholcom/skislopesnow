package com.example.ski;

public class Contacts {
    private	int	id;
    private	String resort;
    private	String status;
    private	String hours;
    private	String fullday;
    private	String halfday;
    private	String weather;
    private	String snow;

    public Contacts(String resort, String status, String hours, String fullday, String halfday, String weather, String snow) {
        this.resort = resort;
        this.status = status;
        this.status = hours;
        this.status = fullday;
        this.status = halfday;
        this.status = weather;
        this.status = snow;
    }

    public Contacts(int id, String resort, String status, String hours, String fullday, String halfday, String weather, String snow) {
        this.id = id;
        this.resort = resort;
        this.status = status;
        this.status = hours;
        this.status = fullday;
        this.status = halfday;
        this.status = weather;
        this.status = snow;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getResort() {
        return resort;
    }
    public void setResort(String resort) {
        this.resort = resort;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getHours() {
        return hours;
    }
    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getFullday() { return fullday; }
    public void setFullday(String fullday) { this.fullday = fullday; }

    public String getHalfday() { return halfday; }
    public void setHalfday(String halfday) { this.halfday = halfday; }

    public String getWeather() { return weather; }
    public void setWeather(String weather) { this.weather = weather; }

    public String getSnow() { return snow; }
    public void setSnow(String snow) { this.weather = snow; }
}
