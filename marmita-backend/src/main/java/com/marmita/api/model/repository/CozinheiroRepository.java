package com.marmita.api.model.repository;

import com.marmita.api.model.entity.Cozinheiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CozinheiroRepository extends JpaRepository<Cozinheiro, Long> {
    List<Cozinheiro> findByAtivoTrue();
}