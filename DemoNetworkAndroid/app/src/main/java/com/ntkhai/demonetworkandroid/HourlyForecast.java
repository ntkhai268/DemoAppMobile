package com.ntkhai.demonetworkandroid;

public class HourlyForecast {
    private String time;
    private String temp;
    private String windOrVisibility;
    private String iconUrl;

    public HourlyForecast(String time, String temp, String windOrVisibility, String iconUrl) {
        this.time = time;
        this.temp = temp;
        this.windOrVisibility = windOrVisibility;
        this.iconUrl = iconUrl;
    }

    public String getTime() {
        return time;
    }

    public String getTemp() {
        return temp;
    }

    public String getWindOrVisibility() {
        return windOrVisibility;
    }

    public String getIconUrl() {
        return iconUrl;
    }
}
