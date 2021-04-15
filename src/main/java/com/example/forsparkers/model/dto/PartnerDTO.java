package com.example.forsparkers.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PartnerDTO {

    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String reference;
    @NotNull
    private String locale;
    @NotNull
    private String expirationTime;

    public PartnerDTO(Long id, String name, String reference, String locale, String expirationTime) {
        this.id = id;
        this.name = name;
        this.reference = reference;
        this.locale = locale;
        this.expirationTime = expirationTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(String expirationTime) {
        this.expirationTime = expirationTime;
    }
}
