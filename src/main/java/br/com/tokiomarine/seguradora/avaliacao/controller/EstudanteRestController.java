package br.com.tokiomarine.seguradora.avaliacao.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import br.com.tokiomarine.seguradora.avaliacao.entidade.Estudante;
import br.com.tokiomarine.seguradora.avaliacao.service.EstudanteServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value="API REST Estudantes")
@RequestMapping("/estudantes")
public class EstudanteRestController {
	
	@Autowired
	private EstudanteServiceImpl estudanteService;
	
	
	@PostMapping(path="/cadastrar-estudante")
	@ApiOperation(value="Cadastrar um novo estudantes")
	public ResponseEntity<?> cadastrarEstudante(@Valid @RequestBody Estudante estudante) {
		try {
			
			estudanteService.cadastrarEstudante(estudante);
			
			return ResponseEntity.ok().build();
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PutMapping(path="/atualizar-estudante/{id}")
	@ApiOperation(value="Atualiza dados de um estudante já cadastrado")
	public ResponseEntity<?> atualizarEstudante(@Valid @PathVariable Long id, @Valid @RequestBody Estudante estudante) {
		
		Optional<Estudante> estudanteRetorno = estudanteService.atualizarEstudante(id, estudante); 
		
		if(estudanteRetorno.isPresent()) {
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
		
	}
	
	@DeleteMapping(path="/deletar-estudante/{id}")
	@ApiOperation(value="Remove um estudante cadastrado")
	public ResponseEntity<?> deletarEstudante(@Valid @PathVariable Long id) {
			
		Optional<Estudante> estudanteRetorno = estudanteService.excluirEstudante(id); 
			
		if(estudanteRetorno.isPresent()) {
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	
	}
	
	@GetMapping(path="/buscar-estudante/{id}")
	@ApiOperation(value="Retorna dados de um estudante cadastrado")
	public ResponseEntity<Optional<Estudante>> obterDadosEstudante(@Valid @PathVariable Long id) {
		
		Optional<Estudante> estudanteRetorno = estudanteService.buscarEstudante(id);
		
		if(estudanteRetorno.isPresent()) {
			return ResponseEntity.ok().body(estudanteRetorno);
		} 
		
		return ResponseEntity.notFound().build();
	
	}
	
	@GetMapping(path="/buscar-estudantes")
	@ApiOperation(value="Retorna uma lista de estudantes cadastrados")
	public ResponseEntity<List<Estudante>> obterListaEstudantes() {
		
		try {
			final List<Estudante> estudantesRetornados = estudanteService.buscarEstudantes();
			
			return new ResponseEntity<List<Estudante>>(estudantesRetornados, HttpStatus.OK);
			
			
		} catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		}
	}
	
}
