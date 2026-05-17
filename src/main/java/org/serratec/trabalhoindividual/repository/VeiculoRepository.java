package org.serratec.trabalhoindividual.repository;

import org.serratec.trabalhoindividual.entity.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface VeiculoRepository extends JpaRepository<Veiculo, UUID> {
    List<Veiculo> findByPlaca(String placa);

    List<Veiculo> findByMarcaIgnoreCase(String marca);

    List<Veiculo> findByModeloIgnoreCase(String modelo);
}