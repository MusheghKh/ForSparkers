package com.example.forsparkers.service;

import com.example.forsparkers.error.BadRequestException;
import com.example.forsparkers.error.NotFoundException;
import com.example.forsparkers.model.dto.PartnerDTO;
import com.example.forsparkers.model.entity.Partner;
import com.example.forsparkers.repository.PartnerRepository;
import com.example.forsparkers.util.PartnerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@Service
@Validated
public class PartnerService {

    private final PartnerRepository partnerRepository;

    @Autowired
    public PartnerService(PartnerRepository partnerRepository) {
        this.partnerRepository = partnerRepository;
    }

    public List<Partner> getPartners(Pageable pageable) {
        return partnerRepository.findAll(pageable).getContent();
    }

    public PartnerDTO getPartner(Long partnerId) {
        Optional<Partner> partnerFromDb = partnerRepository.findById(partnerId);
        if (partnerFromDb.isEmpty()) {
            throw new NotFoundException("Partner with id " + partnerId + " not found.");
        }
        return PartnerFactory.from(partnerFromDb.get());
    }

    public PartnerDTO addPartner(@Valid PartnerDTO dto) {
        dto.setId(null);
        try {
            return PartnerFactory.from(partnerRepository.save(PartnerFactory.from(dto)));
        } catch (ParseException e) {
            throw new BadRequestException("Invalid date format");
        }
    }

    public PartnerDTO editPartner(Long partnerId, @Valid PartnerDTO dto) {
        Optional<Partner> partnerInDb = partnerRepository.findById(partnerId);
        if (partnerInDb.isEmpty()) {
            throw new NotFoundException("Partner with id " + partnerId + " not found.");
        }
        try {
            BeanUtils.copyProperties(PartnerFactory.from(dto), partnerInDb.get(), "id");
            return PartnerFactory.from(partnerRepository.save(partnerInDb.get()));
        } catch (ParseException e) {
            throw new BadRequestException("Invalid date format");
        }
    }

    public void deletePartner(Long partnerId) {
        Optional<Partner> partnerInDb = partnerRepository.findById(partnerId);
        if (partnerInDb.isEmpty()) {
            throw new NotFoundException("Partner with id " + partnerId + " not found.");
        }
        partnerRepository.delete(partnerInDb.get());
    }
}
