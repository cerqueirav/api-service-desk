package com.api.servicedesk.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.servicedesk.models.Usuario;
import com.api.servicedesk.models.input.UsuarioAtualizarInput;
import com.api.servicedesk.models.input.UsuarioNovoInput;
import com.api.servicedesk.models.output.UsuarioResumoModel;
import com.api.servicedesk.services.UsuarioService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/usuarios")
public class UsuarioController {
	@Autowired
	UsuarioService usuarioService;
	
	@GetMapping
	public ResponseEntity<List<UsuarioResumoModel>> listar(){
		List<UsuarioResumoModel> usuariosOut = new ArrayList<>();
		
		var usuarios = usuarioService.listar();
		
		for (int i=0; i<usuarios.size(); i++) 
			usuariosOut.add(UsuarioResumoModel.converterCliente(usuarios.get(i)));	
		
		if (usuarios.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); 
					
		return ResponseEntity.status(HttpStatus.OK).body(usuariosOut);
	}
	
	@GetMapping("/page/{pagina}")
    public Page<UsuarioResumoModel> listar(@PathVariable int pagina, @RequestParam(defaultValue = "3", required = false) int qtd) {
		return usuarioService.listar(pagina, qtd);
    }

	
	@GetMapping("/{usuarioEmail}")
	public ResponseEntity<Object> listarPorEmail(@PathVariable String usuarioEmail){
		var usuario = usuarioService.usuarioExistentePorEmail(usuarioEmail);
		
		if (usuario == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro desconhecido!");
		
		return ResponseEntity.status(HttpStatus.OK).body(usuario.get());
	}
	
	@PostMapping
	public ResponseEntity<Object> cadastrar(@RequestBody UsuarioNovoInput usuarioNovoInput) {
		var usuario = new Usuario();
		
		BeanUtils.copyProperties(usuarioNovoInput, usuario);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.salvar(usuario));
	}
	
	@PatchMapping("/{usuarioEmail}")
	public ResponseEntity<Object> atualizar(@PathVariable String usuarioEmail, @RequestBody @Valid UsuarioAtualizarInput usuarioAtualizarInput) {
		var usuarioAtual = usuarioService.usuarioExistentePorEmail(usuarioEmail);
		
		if (usuarioAtual == null) 
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro desconhecido");
		
		var usuarioAtualizado =	usuarioService.atualizar(usuarioAtualizarInput, usuarioAtual.get());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.salvar(usuarioAtualizado));
	}
	
	@DeleteMapping("/{usuarioEmail}")
	public ResponseEntity<Object> deletar(@PathVariable String usuarioEmail){
		var cliente = usuarioService.usuarioExistentePorEmail(usuarioEmail);
		
		if (cliente == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro desconhecido!"); 
		
		usuarioService.deletar(cliente.get());
		
		return ResponseEntity.status(HttpStatus.OK).body("Usuario excluido com sucesso!");
	}
}
