package web.domain.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Usuario {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	private String login;

	@Column(nullable = false)
	private String senha;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false, columnDefinition = "date")
	private LocalDate dataNascimento;

	@Column(nullable = false, columnDefinition = "bit default b'1'")
	private Boolean status = Boolean.TRUE;

	@ManyToMany
	@JoinTable(name = "permissao_usuario", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "recurso_id"))
	private Set<Recurso> recursos = new HashSet<>();

	public boolean trocarSenha(String senhaAtual, String novaSenha) {
		if (getSenha().equals(senhaAtual)) {
			setSenha(novaSenha);
			return true;
		}
		return false;
	}

	public void ativar() {
		setStatus(true);
	}

	public void inativar() {
		setStatus(false);
	}

	public boolean associarRecurso(Recurso recurso) {
		return getRecursos().add(recurso);
	}

	public boolean desassociarRecurso(Recurso recurso) {
		return getRecursos().remove(recurso);
	}
}
