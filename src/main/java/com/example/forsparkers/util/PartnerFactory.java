package com.example.forsparkers.util;

import com.example.forsparkers.model.dto.PartnerDTO;
import com.example.forsparkers.model.entity.Partner;
import org.springframework.util.StringUtils;

import java.text.ParseException;

public class PartnerFactory {

    public static Partner from(PartnerDTO dto) throws ParseException {
        return new Partner(
                dto.getId(),
                dto.getName(),
                dto.getReference(),
                StringUtils.parseLocaleString(dto.getLocale()),
                DateFormatter.stringToDate(dto.getExpirationTime())
        );
    }

    public static PartnerDTO from(Partner partner) {
        return new PartnerDTO(
                partner.getId(),
                partner.getCompanyName(),
                partner.getRef(),
                partner.getLocale().toString(),
                DateFormatter.dateToString(partner.getExpires())
        );
    }
}
