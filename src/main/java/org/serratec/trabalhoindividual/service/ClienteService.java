package org.serratec.trabalhoindividual.service;

import org.serratec.trabalhoindividual.entity.Cliente;
import org.serratec.trabalhoindividual.exception.DadoInvalidoException;
import org.serratec.trabalhoindividual.exception.JaCadastradoException;
import org.serratec.trabalhoindividual.exception.NaoEncontradoException;
import org.serratec.trabalhoindividual.model.cliente.ClienteBuscaId;
import org.serratec.trabalhoindividual.model.cliente.ClienteCriar;
import org.serratec.trabalhoindividual.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public void inserir(ClienteCriar clienteCriar) {
        if(this.clienteRepository.existsByCpf(clienteCriar.getCpf())) {
            throw new JaCadastradoException("CPF já cadastrado no sistema.");
        }
        Cliente clienteInserir = new Cliente(clienteCriar);

        try {
            this.clienteRepository.save(clienteInserir);
        } catch (jakarta.validation.ConstraintViolationException ex) {
            String mensagens = ex.getConstraintViolations().stream()
                    .map(erro -> erro.getPropertyPath() + ": " + erro.getMessage())
                    .collect(java.util.stream.Collectors.joining(", "));
            throw new DadoInvalidoException(mensagens);
            }
    }

    public ClienteBuscaId buscarPorId(UUID id) {
        Cliente cliente = this.clienteRepository
                .findById(id)
                .orElseThrow(() -> new NaoEncontradoException("O cliente não foi encontrado pelo id: " + id));

        return new ClienteBuscaId(cliente);
    }

    public List<ClienteBuscaId> buscar(String cpf, String nome) {
        List<Cliente> clientes = new ArrayList<>();

        if (cpf != null && !cpf.isBlank()) {
            this.clienteRepository.findByCpf(cpf).ifPresent(clientes::add);
        } else if (nome != null && !nome.isBlank()) {
            clientes = this.clienteRepository.findByNomeContainingIgnoreCase(nome);
        } else {
            clientes = this.clienteRepository.findAll();
        }

        if (clientes.isEmpty()) {
            throw new NaoEncontradoException("Clientes não encontrados pelos parâmetros informados.");
        }

        return clientes.stream()
                .map(cliente -> new ClienteBuscaId(cliente))
                .toList();
    }

    public void deletar(UUID id) {
        Cliente cliente = this.clienteRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("O cliente não foi encontrado pelo id: " + id));
        this.clienteRepository.delete(cliente);
    }
}