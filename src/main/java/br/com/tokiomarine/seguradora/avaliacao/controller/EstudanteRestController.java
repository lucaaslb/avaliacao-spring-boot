package br.com.tokiomarine.seguradora.avaliacao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tokiomarine.seguradora.avaliacao.entidade.Estudante;
import br.com.tokiomarine.seguradora.avaliacao.service.EstudandeService;

// TODO não esquecer de usar as anotações para criação do restcontroller

@RestController
@RequestMapping("/estudantes")
public class EstudanteRestController {

	// TODO caso você não conheça THEMELEAF faça a implementação dos métodos em forma de RESTCONTROLLER (seguindo o padrão RESTFUL)
	@Autowired
	private EstudandeService estudanteService;

	// TODO IMPLEMENTAR CADASTRO DE ESTUDANTES (POST)
	@PostMapping(value = "/cadastrar")
	public ResponseEntity<?> cadastroEstudante(@RequestBody Estudante estudante){
		estudanteService.cadastrarEstudante(estudante);
		return new ResponseEntity("Estudante cadastrado com sucesso", HttpStatus.CREATED);
	}

	// TODO IMPLEMENTAR ATUALIZACAO DE ESTUDANTES (PUT)

	// TODO IMPLEMENTAR A LISTAGEM DE ESTUDANTES (GET)

	// TODO IMPLEMENTAR A EXCLUSÃO DE ESTUDANTES (DELETE)
	
}
