package dio.exploringDesignPatterns.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dio.exploringDesignPatterns.exception.InvalidCepException;
import dio.exploringDesignPatterns.model.Cliente;
import dio.exploringDesignPatterns.model.Endereco;
import dio.exploringDesignPatterns.repository.ClienteRepository;
import dio.exploringDesignPatterns.repository.EnderecoRepository;

/*
 * This class uses the Facade Design Pattern to simplify interaction
 */
@Service
public class ClienteService implements IClienteService {
  @Autowired
  private ClienteRepository _clienteRepository;

  @Autowired
  private EnderecoRepository _enderecoRepository;

  @Autowired
  private ViaCepService _viaCepService;

  @Override
  public Cliente create(Cliente cliente) {
    return _saveClienteWithEndereco(cliente);
  }

  @Override
  public Iterable<Cliente> readAll() {
    return _clienteRepository.findAll();
  }

  @Override
  public Cliente readById(Long id) {
    return _clienteRepository.findById(id).get();
  }

  @Override
  public Cliente update(Cliente cliente) {
    return _saveClienteWithEndereco(cliente);
  }

  @Override
  public void delete(Long id) {
    _clienteRepository.deleteById(id);
  }

  private Cliente _saveClienteWithEndereco(Cliente cliente) {
    String cep = cliente.getEndereco().getCep();
    Endereco endereco = _enderecoRepository.findById(cep).orElseGet(() -> {
      try {
        Endereco enderecoRead = _viaCepService.readByCep(cep);
        _enderecoRepository.save(enderecoRead);
        return enderecoRead;
      } catch (Exception __) {
        throw new InvalidCepException();
      }
    });
    cliente.setEndereco(endereco);
    return _clienteRepository.save(cliente);
  }
}
