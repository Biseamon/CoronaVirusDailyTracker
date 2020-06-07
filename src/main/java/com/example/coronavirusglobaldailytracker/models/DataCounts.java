package com.example.coronavirusglobaldailytracker.models;

public class DataCounts {

    private String state;
    private String country;
    private int totalCases;

    public int getDiffFromPrevDay() {
        return diffFromPrevDay;
    }

    public void setDiffFromPrevDay(int diffFromPrevDay) {
        this.diffFromPrevDay = diffFromPrevDay;
    }

    private int diffFromPrevDay;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getTotalCases() {
        return totalCases;
    }

    public void setTotalCases(int totalCases) {
        this.totalCases = totalCases;
    }

    @Override
    public String toString() {
        return "DataCounts{" +
                "state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", totalCases=" + totalCases +
                '}';
    }
}
