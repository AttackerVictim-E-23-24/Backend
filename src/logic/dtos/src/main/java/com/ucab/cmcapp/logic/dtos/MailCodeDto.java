package com.ucab.cmcapp.logic.dtos;

public class MailCodeDto {

    private int code;

    private UserDto user;

    public MailCodeDto() {

    }

    public MailCodeDto(int code) {
        this.code = code;
    }

    public MailCodeDto(int code, UserDto user) {
        this.code = code;
        this.user = user;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}
