package org.serratec.trabalhoindividual.repository;

import org.serratec.trabalhoindividual.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {

    List<Cliente> findByCpf(String cpf);

    List<Cliente> findByNome(String nome);
}
