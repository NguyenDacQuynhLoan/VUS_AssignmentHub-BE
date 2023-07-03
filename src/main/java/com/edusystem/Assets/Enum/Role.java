package com.edusystem.Assets.Enum;

public enum Role {
    TEACHER("ROLE_TEACHER"), // 0 : Teacher
    STUDENT("ROLE_STUDENT"); // 1 : Student

    private final String name;

    Role(String value) {
        this.name = "";
    }

    /**
     *  Get Role name
     */
    public String getRoleName(){
        return name;
    }

    /**
     *  Get Role index
     */
    public static Role getRoleIndex(int index) {
        return Role.values()[index];
    }

}