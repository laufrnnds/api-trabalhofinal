package com.growdev.trabalhofinal.repositories;

import com.growdev.trabalhofinal.entities.Recado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecadoRepository extends JpaRepository<Recado, Long> {
}
