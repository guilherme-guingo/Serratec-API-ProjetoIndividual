package org.serratec.trabalhoindividual.repository;

import org.serratec.trabalhoindividual.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {

    Optional<Cliente> findByCpf(String cpf);

    List<Cliente> findByNomeContainingIgnoreCase(String nome);

    boolean existsByCpf(String cpf);
}