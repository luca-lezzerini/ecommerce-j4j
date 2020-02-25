package com.ai.ecommercej4j.repository;

import com.ai.ecommercej4j.model.AssociazioneTagliaColori;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociazioneTagliaColoriRepository extends JpaRepository<AssociazioneTagliaColori, Long> {

}
