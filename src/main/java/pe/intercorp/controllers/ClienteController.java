package pe.intercorp.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import pe.intercorp.entities.Cliente;
import pe.intercorp.services.ClienteService;

@RestController
public class ClienteController {

	@Autowired
	ClienteService service;

	@PostMapping("/creacliente")
	public ResponseEntity<Cliente> create(@RequestBody Cliente cliente) {

		try {

			return new ResponseEntity<Cliente>(service.create(cliente), HttpStatus.OK);

		} catch (Exception e) {

			return new ResponseEntity<Cliente>(HttpStatus.BAD_REQUEST);

		}

	}

	@GetMapping("/listaclientes")
	public ResponseEntity<List<Cliente>> read() {

		try {

			return new ResponseEntity<List<Cliente>>(service.read(), HttpStatus.OK);

		} catch (Exception e) {

			return new ResponseEntity<List<Cliente>>(HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

	@PutMapping("/actualizacliente")
	public ResponseEntity<Cliente> update(@RequestBody Cliente cliente) {

		return new ResponseEntity<Cliente>(HttpStatus.NOT_IMPLEMENTED);

	}

	@DeleteMapping("/eliminacliente")
	public ResponseEntity<Cliente> delete(@RequestBody Cliente cliente) {

		return new ResponseEntity<Cliente>(HttpStatus.NOT_IMPLEMENTED);

	}

	@GetMapping("/kpideclientes")
	public ResponseEntity<Map<String, String>> kpi() {

		try {

			return new ResponseEntity<Map<String, String>>(service.kpi(), HttpStatus.OK);

		} catch (Exception e) {

			return new ResponseEntity<Map<String, String>>(HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

}
