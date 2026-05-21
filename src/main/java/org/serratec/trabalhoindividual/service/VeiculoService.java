package org.serratec.trabalhoindividual.service;

import org.serratec.trabalhoindividual.entity.Cliente;
import org.serratec.trabalhoindividual.entity.Veiculo;
import org.serratec.trabalhoindividual.exception.NaoEncontradoException;
import org.serratec.trabalhoindividual.model.veiculo.VeiculoBuscaId;
import org.serratec.trabalhoindividual.model.veiculo.VeiculoCriar;
import org.serratec.trabalhoindividual.model.veiculo.VeiculoUpdateInput;
import org.serratec.trabalhoindividual.repository.ClienteRepository;
import org.serratec.trabalhoindividual.repository.VeiculoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;
    private final ClienteRepository clienteRepository;

    public VeiculoService(VeiculoRepository veiculoRepository, ClienteRepository clienteRepository) {
        this.veiculoRepository = veiculoRepository;
        this.clienteRepository = clienteRepository;
    }

    public void inserir(VeiculoCriar veiculoCriar) {
        Cliente cliente = this.clienteRepository.findById(veiculoCriar.getClienteId())
                .orElseThrow(() -> new NaoEncontradoException("Cliente não encontrado para o ID informado."));

        if (veiculoCriar.isVendido() && veiculoCriar.getValorVenda() == null) {
            throw new IllegalArgumentException("O valor de venda é obrigatório quando o veículo está marcado como vendido.");
        }

        if (!veiculoCriar.isVendido() && veiculoCriar.getValorVenda() != null) {
            throw new IllegalArgumentException("Não é permitido informar um valor de venda para um veículo não vendido.");
        }

        Veiculo veiculoInserir = new Veiculo(veiculoCriar, cliente);
        this.veiculoRepository.save(veiculoInserir);
    }

    public VeiculoBuscaId buscarPorId(UUID id) {
        Veiculo veiculo = this.veiculoRepository
                .findById(id)
                .orElseThrow(() -> new NaoEncontradoException("O veículo não foi encontrado pelo id: " + id));

        return new VeiculoBuscaId(veiculo);
    }

    public List<VeiculoBuscaId> buscar(String placa, String marca, String modelo) {
        List<Veiculo> veiculos = new ArrayList<>();

        if (placa != null && !placa.isBlank()) {
            veiculos = this.veiculoRepository.findByPlaca(placa);
        } else if (marca != null && !marca.isBlank()) {
            veiculos = this.veiculoRepository.findByMarcaIgnoreCase(marca);
        } else if (modelo != null && !modelo.isBlank()) {
            veiculos = this.veiculoRepository.findByModeloIgnoreCase(modelo);
        } else {
            veiculos = this.veiculoRepository.findAll();
        }

        if (veiculos.isEmpty()) {
            throw new NaoEncontradoException("Veículos não encontrados pelos parâmetros informados.");
        }

        return veiculos.stream()
                .map(veiculo -> new VeiculoBuscaId(veiculo))
                .toList();
    }

    public void atualizar(UUID id, VeiculoUpdateInput veiculoUpdate) {
        Veiculo veiculoExistente = this.veiculoRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Veículo não encontrado pelo id: " + id));

        if (veiculoUpdate.isVendido() && veiculoUpdate.getValorVenda() == null) {
            throw new IllegalArgumentException("O valor de venda é obrigatório quando o veículo está marcado como vendido.");
        }

        if (!veiculoUpdate.isVendido() && veiculoUpdate.getValorVenda() != null) {
            throw new IllegalArgumentException("Não é permitido informar um valor de venda para um veículo não vendido.");
        }

        veiculoExistente.setMarca(veiculoUpdate.getMarca());
        veiculoExistente.setModelo(veiculoUpdate.getModelo());
        veiculoExistente.setAno(veiculoUpdate.getAno());
        veiculoExistente.setValor(veiculoUpdate.getValor());
        veiculoExistente.setMaximoDesconto(veiculoUpdate.getMaximoDesconto());
        veiculoExistente.setVendido(veiculoUpdate.isVendido());
        veiculoExistente.setValorVenda(veiculoUpdate.getValorVenda());

        this.veiculoRepository.save(veiculoExistente);
    }

    public void deletar(UUID id) {
        Veiculo veiculo = this.veiculoRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("O veículo não foi encontrado pelo id: " + id));
        this.veiculoRepository.delete(veiculo);
    }
}