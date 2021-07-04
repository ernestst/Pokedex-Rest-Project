package pl.edu.pb.wi.user.dto;

public enum Role {
    USER("user"),
    ADMINISTRATOR("administrator");

    String value;

    Role(String value) {
        this.value = value;
    }


    public String getValue() {
        return value;
    }
}
