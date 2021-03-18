package web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import web.controller.dto.UsuarioAtivoDTO;
import web.controller.dto.UsuarioDTO;
import web.controller.dto.request.SenhaDTORequestBody;
import web.controller.dto.request.UsuarioDTORequestBody;
import web.controller.unwrapper.UsuarioUnwrapper;
import web.controller.wrapper.UsuarioAtivoDTOWrapper;
import web.controller.wrapper.UsuarioDTOWrapper;
import web.domain.exception.DataNotFoundException;
import web.domain.model.Usuario;
import web.domain.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private UsuarioDTOWrapper usarioWrapper;

	@Autowired
	private UsuarioUnwrapper usuarioUnwrapper;

	@Autowired
	private UsuarioAtivoDTOWrapper usuarioAtivoDTOWrapper;

	@GetMapping
	public List<UsuarioDTO> getAll() {
		return usarioWrapper.toDTOCollection(usuarioService.getAll());
	}

	@GetMapping("/ativos")
	public List<UsuarioAtivoDTO> getAllStatusAtivo() {
		List<Usuario> usuariosAtivos = usuarioService.getAllStatusAtivo();
		return usuarioAtivoDTOWrapper.toDTOCollection(usuariosAtivos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDTO> getById(@PathVariable Long id) {
		try {
			var usuario = usuarioService.getByIdOrFail(id);
			var usuarioDTO = usarioWrapper.toDTOModel(usuario);
			return ResponseEntity.ok(usuarioDTO);
		} catch (DataNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UsuarioDTO create(@RequestBody UsuarioDTORequestBody usuarioDTORequestBody) {
		Usuario usuario = usuarioUnwrapper.toDomainModel(usuarioDTORequestBody);
		Usuario usuarioCriado = this.usuarioService.create(usuario);
		return usarioWrapper.toDTOModel(usuarioCriado);
	}

	@PutMapping("/{id}")
	public ResponseEntity<UsuarioDTO> update(@PathVariable Long id,
			@RequestBody UsuarioDTORequestBody usuarioDTORequestBody) {
		Usuario usuario = usuarioUnwrapper.toDomainModel(usuarioDTORequestBody);
		Usuario usuarioAtualizado = usuarioService.update(id, usuario);

		if (usuarioAtualizado != null) {
			UsuarioDTO usuarioResponse = usarioWrapper.toDTOModel(usuarioAtualizado);
			return ResponseEntity.ok(usuarioResponse);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/{id}/troca-senha")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> trocarSenha(@PathVariable Long id, @RequestBody SenhaDTORequestBody senhaDTORequestBody) {
		var ok = usuarioService.trocarSenha(id, senhaDTORequestBody.getSenhaAtual(), senhaDTORequestBody.getNovaSenha());
		if (!ok) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void ativar(@PathVariable Long id) {
		usuarioService.ativar(id);
	}

	@DeleteMapping("/{id}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void inativar(@PathVariable Long id) {
		usuarioService.inativar(id);
	}

}
