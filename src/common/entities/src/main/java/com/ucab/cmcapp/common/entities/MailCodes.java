package com.ucab.cmcapp.common.entities;

import javax.persistence.*;

@Entity
@Table(name = "mail_codes")
public class MailCodes {

    public MailCodes() {

    }

    public MailCodes(MailCodes mailCodes) {
        code = mailCodes.code;
    }

    public MailCodes(int code) {
        this.code = code;
    }

    @Id
    @Column( name = "id" )
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long id;

    @Column( name = "code", nullable = false )
    private int code;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn( name = "user_id", nullable = false )
    private User user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
