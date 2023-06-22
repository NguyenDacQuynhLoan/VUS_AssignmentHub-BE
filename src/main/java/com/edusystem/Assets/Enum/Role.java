package com.edusystem.Assets.Enum;

public enum Role {
    teacher("ROLE_TEACHER"), // 0 : Teacher
    student("ROLE_STUDENT"); // 1 : Student

    private final String name;
    Role(String value){ this.name = value; }

    public String getName() {
        return name;
    }

    public static Role getByIndex(int index) {
        return Role.values()[index];
    }
}
