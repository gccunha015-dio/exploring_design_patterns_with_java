package dio.exploringDesignPatterns.service;

import dio.exploringDesignPatterns.model.Cliente;

/*
 * Strategy Design Pattern determines that every ClientService implements this strategy
 */
public interface IClienteService {
  Cliente create(Cliente cliente);

  Iterable<Cliente> readAll();

  Cliente readById(Long id);

  Cliente update(long id, Cliente cliente);

  void delete(Long id);
}
