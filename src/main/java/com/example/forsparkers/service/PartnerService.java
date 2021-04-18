package com.example.forsparkers.service;

import com.example.forsparkers.error.exception.BadRequestException;
import com.example.forsparkers.error.exception.NotFoundException;
import com.example.forsparkers.model.dto.PartnerDTO;
import com.example.forsparkers.model.entity.Partner;
import com.example.forsparkers.repository.PartnerRepository;
import com.example.forsparkers.util.ErrorMessage;
import com.example.forsparkers.util.PartnerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Validated
public class PartnerService {

    private final PartnerRepository partnerRepository;

    @Autowired
    public PartnerService(PartnerRepository partnerRepository) {
        this.partnerRepository = partnerRepository;
    }

    public List<PartnerDTO> getPartners(int from, int size) {
        Pageable pageable = PageRequest.of(from, size);

        return partnerRepository.findBy(pageable).getContent()
                .stream()
                .map(PartnerFactory::from)
                .collect(Collectors.toList());
    }

    public PartnerDTO getPartner(Long partnerId) {
        Optional<Partner> partnerFromDb = partnerRepository.findById(partnerId);
        if (partnerFromDb.isEmpty()) {
            throw new NotFoundException(String.format(ErrorMessage.PARTNER_NOT_FOUND_UNFORMATTED, partnerId));
        }
        return PartnerFactory.from(partnerFromDb.get());
    }

    public PartnerDTO addPartner(@Valid PartnerDTO dto) {
        dto.setId(null);
        try {
            return PartnerFactory.from(partnerRepository.save(PartnerFactory.from(dto)));
        } catch (ParseException e) {
            throw new BadRequestException(ErrorMessage.INVALID_DATE_FORMAT);
        }
    }

    public PartnerDTO editPartner(Long partnerId, @Valid PartnerDTO dto) {
        Optional<Partner> partnerInDb = partnerRepository.findById(partnerId);
        if (partnerInDb.isEmpty()) {
            throw new NotFoundException(String.format(ErrorMessage.PARTNER_NOT_FOUND_UNFORMATTED, partnerId));
        }
        try {
            BeanUtils.copyProperties(PartnerFactory.from(dto), partnerInDb.get(), "id");
            return PartnerFactory.from(partnerRepository.save(partnerInDb.get()));
        } catch (ParseException e) {
            throw new BadRequestException(ErrorMessage.INVALID_DATE_FORMAT);
        }
    }

    public void deletePartner(Long partnerId) {
        Optional<Partner> partnerInDb = partnerRepository.findById(partnerId);
        if (partnerInDb.isEmpty()) {
            throw new NotFoundException(String.format(ErrorMessage.PARTNER_NOT_FOUND_UNFORMATTED, partnerId));
        }
        partnerRepository.delete(partnerInDb.get());
    }
}
