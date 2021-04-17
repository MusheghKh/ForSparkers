package com.example.forsparkers.model.dto;

import com.example.forsparkers.validation.ValidLocale;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PartnerDTO {

    private Long id;
    @NotBlank(message = "name must not be black")
    @Size(min = 2, max = 255, message = "name size must be between 2 and 255")
    private String name;
    @NotBlank(message = "reference must not be black")
    @Size(min = 2, max = 255, message = "reference size must be between 2 and 255")
    private String reference;
    @ValidLocale
    private String locale;
    @NotNull(message = "expirationTime must not be null")
    private String expirationTime;

    public PartnerDTO() {

    }

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
