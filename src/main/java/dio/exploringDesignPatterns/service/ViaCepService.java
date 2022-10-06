package dio.exploringDesignPatterns.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import dio.exploringDesignPatterns.model.Endereco;

@FeignClient(name = "viaCep", url = "https://viacep.com.br/ws")
public interface ViaCepService {
  @GetMapping("/{cep}/json/")
  Endereco readByCep(@PathVariable("cep") String cep);
}
