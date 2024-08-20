package entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Menu {

    private String bear = "Ligth";
    private String brendi = "Cool";

    public String getBear() {
        return bear;
    }

    public String getBrendi() {
        return brendi;
    }

}