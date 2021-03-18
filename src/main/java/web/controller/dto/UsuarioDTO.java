package web.controller.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UsuarioDTO {

	private Boolean status;

	private Long id;

	private String nome;

	private String login;

	private String email;

	private LocalDate dataNascimento;

}
