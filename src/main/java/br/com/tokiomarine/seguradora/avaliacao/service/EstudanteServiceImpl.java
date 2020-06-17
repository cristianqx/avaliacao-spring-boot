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
	public Optional<Estudante> atualizarEstudante(@Valid Long id, @Valid Estudante estudante) {
		
		Optional<Estudante> dadosEstudante = repository.findById(id);
		
		if(dadosEstudante.isPresent()) {
			
			Estudante dadosAtualizados = dadosEstudante.get();
			
			dadosAtualizados.setEmail(estudante.getEmail());
			dadosAtualizados.setTelefone(estudante.getTelefone());
			dadosAtualizados.setNome(estudante.getNome());
			dadosAtualizados.setMatricula(estudante.getMatricula());
			dadosAtualizados.setCurso(estudante.getCurso());
			return Optional.of(repository.saveAndFlush(dadosAtualizados));
		} else {
			return Optional.empty();
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
	public Optional<Estudante> buscarEstudante(Long id) {
		
		Optional<Estudante> dadosAtuais = repository.findById(id);
		
		if(dadosAtuais.isPresent()) {
			return dadosAtuais;
		} else {
			return Optional.empty();
		}
	}

	@Override
	public Optional<Estudante> excluirEstudante(@Valid Long id) {
		
		Optional<Estudante> dadosEstudante = repository.findById(id);
		
		if(dadosEstudante.isPresent()) {
			repository.delete(dadosEstudante.get());
			return dadosEstudante;
		} else {
			return Optional.empty();
		}
			
	}

}
