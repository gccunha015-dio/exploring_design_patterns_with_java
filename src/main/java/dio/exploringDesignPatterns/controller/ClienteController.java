package dio.exploringDesignPatterns.controller;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dio.exploringDesignPatterns.exception.InvalidCepException;
import dio.exploringDesignPatterns.model.Cliente;
import dio.exploringDesignPatterns.service.IClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;

@Tags(value = {
    @Tag(name = "Clientes")
})
@RestController()
@RequestMapping("/clientes")
public class ClienteController {
  @Autowired
  private IClienteService _clienteService;

  @PostMapping
  @Operation(summary = "Create new Cliente")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "New Cliente created", content = @Content),
      @ApiResponse(responseCode = "400", description = "Invalid CEP", content = @Content)
  })
  public ResponseEntity<Cliente> create(@RequestBody Cliente cliente) {
    try {
      Cliente novoCliente = _clienteService.create(cliente);
      return new ResponseEntity<>(novoCliente, HttpStatus.CREATED);
    } catch (InvalidCepException __) {
      return ResponseEntity.badRequest().build();
    }
  }

  @GetMapping
  @Operation(summary = "Read all")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "List of Clientes", content = @Content)
  })
  public ResponseEntity<Iterable<Cliente>> readAll() {
    return ResponseEntity.ok(_clienteService.readAll());
  }

  @GetMapping("/{id}")
  @Operation(summary = "Read by id")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Cliente found", content = @Content),
      @ApiResponse(responseCode = "404", description = "Cliente not found", content = @Content)
  })
  public ResponseEntity<Cliente> readById(@PathVariable("id") Long id) {
    try {
      Cliente cliente = _clienteService.readById(id);
      return ResponseEntity.ok(cliente);
    } catch (NoSuchElementException __) {
      return ResponseEntity.notFound().build();
    }
  }

  @PutMapping("/{id}")
  @Operation(summary = "Update Cliente")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Cliente updated", content = @Content),
      @ApiResponse(responseCode = "404", description = "Cliente not found", content = @Content),
      @ApiResponse(responseCode = "400", description = "Invalid CEP", content = @Content)
  })
  public ResponseEntity<Cliente> update(@PathVariable("id") Long id, @RequestBody Cliente cliente) {
    try {
      Cliente clienteAtualizado = _clienteService.update(id, cliente);
      return ResponseEntity.ok(clienteAtualizado);
    } catch (NoSuchElementException __) {
      return ResponseEntity.notFound().build();
    } catch (InvalidCepException __) {
      return ResponseEntity.badRequest().build();
    }
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete Cliente")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Cliente deleted", content = @Content),
      @ApiResponse(responseCode = "404", description = "Cliente not found", content = @Content)
  })
  public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
    try {
      _clienteService.delete(id);
      return ResponseEntity.ok().build();
    } catch (EmptyResultDataAccessException __) {
      return ResponseEntity.notFound().build();
    }
  }
}
