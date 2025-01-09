package com.beymo.traffic.user.model;

public enum Rank {
    CONSTABLE("Constable"),
    SERGEANT("Sergeant"),
    SERGEANT_MAJOR("Sergeant Major"),
    ASSISTANT_INSPECTOR("Assistant Inspector"),
    INSPECTOR("Inspector"),
    CHIEF_INSPECTOR("Chief Inspector"),
    SUPERINTENDENT("Superintendent"),
    CHIEF_SUPERINTENDENT("Chief Superintendent"),
    ASSISTANT_COMMISSIONER("Assistant Commissioner"),
    COMMISSIONER("Commissioner"),
    DEPUTY_COMMISSIONER_GENERAL("Deputy Commissioner General"),
    COMMISSIONER_GENERAL("Commissioner General")
    ;
    private final String name;
    Rank(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Rank{" +
                "name='" + name + '\'' +
                '}';
    }
}
