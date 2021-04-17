package com.example.forsparkers;

import com.example.forsparkers.model.entity.Partner;
import com.example.forsparkers.repository.PartnerRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class AppRunner {

    private final PartnerRepository partnerRepository;

    public AppRunner(PartnerRepository partnerRepository) {
        this.partnerRepository = partnerRepository;
    }

    @Bean
    public ApplicationRunner runTestApp() {
        return args -> {
            seedPartnerTable();
        };
    }

    private void seedPartnerTable() {
        List<Partner> list = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            Partner partner = new Partner();
            partner.setCompanyName("Some company name " + i);
            partner.setRef("Some ref " + i);
            partner.setLocale(StringUtils.parseLocaleString("en_ES"));
            partner.setExpires(new Date());
            list.add(partner);
        }
        partnerRepository.saveAll(list);
    }
}
