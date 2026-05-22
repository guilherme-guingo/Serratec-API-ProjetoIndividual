package org.serratec.trabalhoindividual.repository;

import org.serratec.trabalhoindividual.entity.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VeiculoRepository extends JpaRepository<Veiculo, UUID> {

    Optional<Veiculo> findByPlaca(String placa);

    List<Veiculo> findByMarcaContainingIgnoreCase(String marca);

    List<Veiculo> findByModeloContainingIgnoreCase(String modelo);

    boolean existsByPlaca(String placa);
}