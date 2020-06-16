package br.com.tokiomarine.seguradora.avaliacao.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tokiomarine.seguradora.avaliacao.entidade.Estudante;
import br.com.tokiomarine.seguradora.avaliacao.repository.EstudanteRepository;

@Service
public class EstudanteServiceImpl implements EstudandeService {

	@Autowired
	private EstudanteRepository repository;

	@Override
	public void cadastrarEstudante(@Valid Estudante estudante){
		
		try {
			repository.saveAndFlush(estudante);
			
		} catch (Exception e) {
			throw e;
		}
				
	}

	@Override
	public void atualizarEstudante(@Valid Estudante estudante) {
		
		try {
			
			Optional<Estudante> dadosEstudante = repository.findById(estudante.getId());
			
			if(dadosEstudante.isPresent()) {
				
				Estudante dadosAtualizados = dadosEstudante.get();
				
				dadosAtualizados.setEmail(estudante.getEmail());
				dadosAtualizados.setTelefone(estudante.getTelefone());
				dadosAtualizados.setNome(estudante.getNome());
				dadosAtualizados.setMatricula(estudante.getMatricula());
				dadosAtualizados.setCurso(estudante.getCurso());
				repository.saveAndFlush(dadosAtualizados);
				
			}
			
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<Estudante> buscarEstudantes() {
		
		List<Estudante> estudante = new ArrayList<>();
				
		try {
			estudante = repository.findAll();

		} catch (Exception e) {
			throw e;
		}
		
		return estudante;
	}

	@Override
	public Estudante buscarEstudante(long id) {
		throw new IllegalArgumentException("Identificador inválido:" + id);
	}

	@Override
	public void excluirEstudante(@Valid Long id) {
		
		try {
			Optional<Estudante> dadosEstudante = repository.findById(id);
			
			if(dadosEstudante.isPresent()) {
				repository.delete(dadosEstudante.get());
			}
			
		} catch (Exception e) {
			throw e;
		}
	}

}
