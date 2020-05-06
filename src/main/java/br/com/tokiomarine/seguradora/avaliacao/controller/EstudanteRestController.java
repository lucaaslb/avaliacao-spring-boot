package br.com.tokiomarine.seguradora.avaliacao.controller;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.tokiomarine.seguradora.avaliacao.controller.dto.EstudanteDto;
import br.com.tokiomarine.seguradora.avaliacao.entidade.Estudante;
import br.com.tokiomarine.seguradora.avaliacao.repository.EstudanteRepository;

// TODO não esquecer de usar as anotações para criação do restcontroller

@RestController
@RequestMapping("/estudantes")
public class EstudanteRestController {

	// TODO caso você não conheça THEMELEAF faça a implementação dos métodos em
	// forma de RESTCONTROLLER (seguindo o padrão RESTFUL)
	@Autowired
	EstudanteRepository estudanteRepository;

	// TODO IMPLEMENTAR CADASTRO DE ESTUDANTES (POST)
	@PostMapping(value = "/cadastrar")
	@Transactional
	public ResponseEntity<?> cadastroEstudante(@RequestBody @Valid Estudante estudante,
			UriComponentsBuilder uriBuilder) {
		estudanteRepository.save(estudante);

		URI uri = uriBuilder.path("/estudantes/listar/{id}").buildAndExpand(estudante.getId()).toUri();
		return ResponseEntity.created(uri).body(estudante);
	}

	// TODO IMPLEMENTAR ATUALIZACAO DE ESTUDANTES (PUT)
	@PutMapping("/atualizar/{id}")
	public ResponseEntity<EstudanteDto> atualizarCadastro(@PathVariable Long id, @RequestBody Estudante estudante,
			UriComponentsBuilder uriBuilder) {

		return estudanteRepository.findById(id).map(dados -> {
			dados.setNome(estudante.getNome());
			dados.setEmail(estudante.getEmail());
			dados.setCurso(estudante.getCurso());
			dados.setMatricula(estudante.getMatricula());
			dados.setTelefone(estudante.getTelefone());

			Estudante estudanteAtualizado = estudanteRepository.save(dados);
			return ResponseEntity.ok().body(new EstudanteDto(estudanteAtualizado));
		}).orElse(ResponseEntity.notFound().build());

	}

	// TODO IMPLEMENTAR A LISTAGEM DE ESTUDANTES (GET)
	@GetMapping(value = "/listar")
	public List<Estudante> listarTodosEstudantes() {
		return estudanteRepository.findAll();
	}

	@GetMapping(value = "/listar/{id}")
	public ResponseEntity<EstudanteDto> detalharEstudante(@PathVariable Long id) {

		if (estudanteRepository.findById(id).isPresent()) {
			Estudante estudante = estudanteRepository.getOne(id);
			return ResponseEntity.ok(new EstudanteDto(estudante));
		}

		return ResponseEntity.notFound().build();
	}

	// TODO IMPLEMENTAR A EXCLUSÃO DE ESTUDANTES (DELETE)
	@DeleteMapping("/delete/{id}")
	@Transactional
	public ResponseEntity<?> deleteEstudante(@PathVariable Long id) {
		if (estudanteRepository.findById(id).isPresent()) {
			estudanteRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}

}
