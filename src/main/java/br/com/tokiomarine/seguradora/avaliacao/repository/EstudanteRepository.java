package br.com.tokiomarine.seguradora.avaliacao.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.tokiomarine.seguradora.avaliacao.entidade.Estudante;

@Repository
public interface EstudanteRepository {

	List<Estudante> findByName(String name);

}
