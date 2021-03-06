package com.example.forsparkers.repository;

import com.example.forsparkers.model.entity.Partner;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long> {

    Slice<Partner> findBy(Pageable pageable);
}
