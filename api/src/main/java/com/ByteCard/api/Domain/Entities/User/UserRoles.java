package com.ByteCard.api.Domain.Entities.User;

public enum UserRoles {
    ADMIN("admin"),
    USER("user");

    private final String value;

    UserRoles(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
