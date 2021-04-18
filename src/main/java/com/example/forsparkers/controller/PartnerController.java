package com.example.forsparkers.controller;

import com.example.forsparkers.model.dto.PartnerDTO;
import com.example.forsparkers.service.PartnerService;
import com.example.forsparkers.util.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/partner")
public class PartnerController {

    private final PartnerService partnerService;

    public PartnerController(PartnerService partnerService) {
        this.partnerService = partnerService;
    }

    @GetMapping
    public List<PartnerDTO> getAllPartners(
            @RequestParam(value = "from", required = false, defaultValue = "0")
            @Min(value = 0, message = ErrorMessage.FROM_MIN) int from,
            @RequestParam(value = "size", required = false, defaultValue = "10")
            @Min(value = 1, message = ErrorMessage.SIZE_MIN) int size
    ) {
        return partnerService.getPartners(from, size);
    }

    @GetMapping("/{id}")
    public PartnerDTO getPartner(@PathVariable Long id) {
        return partnerService.getPartner(id);
    }

    @PostMapping
    public ResponseEntity<PartnerDTO> addPartner(@RequestBody PartnerDTO partner) {
        PartnerDTO saved = partnerService.addPartner(partner);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public PartnerDTO putPartner(@PathVariable Long id, @RequestBody PartnerDTO partner) {
        return partnerService.editPartner(id, partner);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePartner(@PathVariable Long id) {
        partnerService.deletePartner(id);
        return ResponseEntity.ok().build();
    }
}
