package com.mrjeff.cupondescuento.persistence.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mrjeff.cupondescuento.persistence.entities.CuponDescuentoEntity;

@Repository
public interface CuponDescuentoRepository extends JpaRepository<CuponDescuentoEntity, Long>{
	@Query("SELECT cupon.valor FROM CuponDescuentoEntity cupon WHERE cupon.codigo = :codigo")
	Optional<Double> findValorByCodigo(@Param("codigo") String codigo);
}
