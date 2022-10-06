package dio.exploringDesignPatterns.service;

import dio.exploringDesignPatterns.model.Cliente;

public interface IClienteService {
  Cliente create(Cliente cliente);

  Iterable<Cliente> readAll();

  Cliente readById(Long id);

  Cliente update(Cliente cliente);

  void delete(Long id);
}
