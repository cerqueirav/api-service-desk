package com.api.servicedesk.services;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.api.servicedesk.models.Usuario;
import com.api.servicedesk.models.input.UsuarioAtualizarInput;
import com.api.servicedesk.models.output.UsuarioResumoModel;
import com.api.servicedesk.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Transactional
	public Usuario salvar(Usuario usuario) {
		 preparaUsuarioParaSalvar(usuario);
		 
		 return usuarioRepository.save(usuario);
	}
	
	@Transactional
	public Usuario atualizar(UsuarioAtualizarInput usuarioAtualizarInput, Usuario usuarioAtual) {
		preparaUsuarioParaAtualizar(usuarioAtualizarInput, usuarioAtual);
		
		return usuarioRepository.save(usuarioAtual);
	}
		
	@Transactional
	public void deletar(String usuarioId) {
		usuarioRepository.deleteById(usuarioId);
	}

	@Transactional
	public void deletar(Usuario usuario) {
		usuarioRepository.delete(usuario);
	}
	
	public List<Usuario> listar() {
		return usuarioRepository.findAll();
	} 
	
	public Page<UsuarioResumoModel> listar(int pagina, int qtd){
		Pageable pageable = PageRequest.of(pagina, qtd, Sort.Direction.ASC, "nome");
		return UsuarioResumoModel.converterLista(usuarioRepository.findAll(pageable));
	}
	
	public void preparaUsuarioParaSalvar(Usuario usuario) {
		usuario.setDataCadastro(OffsetDateTime.now());
	}

	// Refatorar depois ...
	private void preparaUsuarioParaAtualizar(UsuarioAtualizarInput usuarioAtualizarInput, Usuario usuario) {
		if (usuarioAtualizarInput.getNome() != null)
			usuario.setNome(usuarioAtualizarInput.getNome());
		
		if (usuarioAtualizarInput.getEmail() != null)
			usuario.setEmail(usuarioAtualizarInput.getEmail());
		
		if (usuarioAtualizarInput.getUrlImagem() != null)
			usuario.setUrlImagem(usuarioAtualizarInput.getUrlImagem());
	}
	
	public Optional<Usuario> usuarioExistentePorEmail(String email) {
		Optional<Usuario> usuarioExistentePorEmail = usuarioRepository.findByEmail(email);
		
		if (usuarioExistentePorEmail.isPresent())
			return usuarioExistentePorEmail;
		
		return null;
	}
}
