package com.example.forsparkers.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Locale;

@Entity
public class Partner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @NotNull
    private String companyName;
    @Column
    @NotNull
    private String ref;
    @Column
    @NotNull
    private Locale locale;
    @Column
    @NotNull
    private Date expires;

    public Partner() {
    }

    public Partner(Long id, String companyName, String ref, Locale locale, Date expires) {
        this.id = id;
        this.companyName = companyName;
        this.ref = ref;
        this.locale = locale;
        this.expires = expires;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public Date getExpires() {
        return expires;
    }

    public void setExpires(Date expires) {
        this.expires = expires;
    }
}
