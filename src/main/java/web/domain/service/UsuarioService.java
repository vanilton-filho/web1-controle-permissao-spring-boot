package web.domain.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.domain.exception.DataNotFoundException;
import web.domain.model.Recurso;
import web.domain.model.Usuario;
import web.domain.repository.RecursoRepository;
import web.domain.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private RecursoRepository recursoRepository;

	@Autowired
	private EntityManager manager;

	/**
	 * Obtendo uma lista de usuários da base de dados.
	 * 
	 * @return
	 */
	public List<Usuario> getAll() {
		return this.usuarioRepository.findAll();
	}

	/**
	 * Obtém uma lista de usuários ativos da base de dados.
	 * 
	 * @return
	 */
	public List<Usuario> getAllStatusAtivo() {
		return usuarioRepository.findByStatusTrue();
	}

	/**
	 * Obtendo um usuário específico da base de dados. Se o usuário
	 * não é retornado então lançamos uma exceção indicando falha
	 * @param id
	 * @return
	 */
	public Usuario getByIdOrFail(Long id) {
		Optional<Usuario> usuarioOptional = this.usuarioRepository.findById(id);

		if (usuarioOptional.isPresent()) {
			return usuarioOptional.get();
		}

		throw new DataNotFoundException(String.format("Não existe usuário de id %d", id));
	}

	/**
	 * Criar um usuário na base de dados. Ele garante que não exista
	 * usuários com o mesmo login ou email.
	 * 
	 * @param usuario
	 * @return
	 */
	@Transactional
	public Usuario create(Usuario usuario) {
		manager.detach(usuario);
		Optional<Usuario> usuarioExistente = usuarioRepository.findByEmailOrLogin(usuario.getEmail(),
				usuario.getLogin());

		// Esse equals usa o hash code pelo id EqualHashCode.Include
		if (usuarioExistente.isPresent() && !usuarioExistente.get().equals(usuario)) {
			throw new RuntimeException(String.format("Já existe um usuário com esse login %s ou e-mail %s",
					usuario.getLogin(), usuario.getEmail()));
		}
		return this.usuarioRepository.save(usuario);
	}

	/**
	 * Atualiza um usuário se ele existir.
	 * 
	 * @param id
	 * @param usuario
	 * @return
	 */
	@Transactional
	public Usuario update(Long id, Usuario usuario) {
		var usuarioEncontrado = getByIdOrFail(id);
		BeanUtils.copyProperties(usuario, usuarioEncontrado, "id", "recursos");
		return usuarioEncontrado;
	}

	@Transactional
	public void ativar(Long id) {
		var usuarioOptional = usuarioRepository.findById(id);

		if (usuarioOptional.isPresent()) {
			usuarioOptional.get().ativar();
		}
	}

	@Transactional
	public void inativar(Long id) {
		var usuarioOptional = usuarioRepository.findById(id);

		if (usuarioOptional.isPresent()) {
			usuarioOptional.get().inativar();
		}
	}

	@Transactional
	public void associar(Long usuarioId, Long recursoId) {
		Usuario usuario = getByIdOrFail(usuarioId);
		Optional<Recurso> recurso = recursoRepository.findById(recursoId);
		if (recurso.isPresent()) {
			usuario.associarRecurso(recurso.get());
		}
	}

	@Transactional
	public void desassociar(Long usuarioId, Long recursoId) {
		Usuario usuario = getByIdOrFail(usuarioId);
		Optional<Recurso> recurso = recursoRepository.findById(recursoId);
		if (recurso.isPresent()) {
			usuario.desassociarRecurso(recurso.get());
		}
	}

	@Transactional
	public boolean trocarSenha(Long id, String senhaAtual, String novaSenha) {
		Usuario usuario = getByIdOrFail(id);
		return usuario.trocarSenha(senhaAtual, novaSenha);
	}
}
