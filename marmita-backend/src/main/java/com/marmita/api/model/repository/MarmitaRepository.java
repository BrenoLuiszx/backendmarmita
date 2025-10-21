package com.marmita.api.model.repository;

import com.marmita.api.model.entity.Marmita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MarmitaRepository extends JpaRepository<Marmita, Long> {
    List<Marmita> findByDisponivelTrue();
    List<Marmita> findByCategoriaId(Long categoriaId);
}