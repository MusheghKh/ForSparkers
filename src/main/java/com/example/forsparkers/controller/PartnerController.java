package com.example.forsparkers.controller;

import com.example.forsparkers.model.dto.PartnerDTO;
import com.example.forsparkers.service.PartnerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/partner")
public class PartnerController {

    private final PartnerService partnerService;

    public PartnerController(PartnerService partnerService) {
        this.partnerService = partnerService;
    }

    @GetMapping
    public List<PartnerDTO> getAllPartners(
            @RequestParam(value = "from", required = false, defaultValue = "0") int from,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size
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
