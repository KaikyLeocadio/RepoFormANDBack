package com.paces.dto;

import java.text.SimpleDateFormat;

import com.paces.domain.Usuario;

public class UsuarioDTO {

	  	private Integer id;
	    private int status = 1; // para saber se o usuario esta ativo ou inativo
	    private String nomecompleto;
	    private String nomeocial;
	    private String genero;
	    private String datadenascimento;
	    private String tipodedocumento;
	    private String documento;
	    private String email;
	    private String senha;
    
    public UsuarioDTO() {
	}
    
    public UsuarioDTO(Usuario domain) { // isso é um construtor que nao pode configurar o retorno, o retorno do construtor é a própria classe(retornar o objeto da classe construida)
    	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
    	this.id = domain.getId();
    	this.status = domain.getStatus();
    	this.nomecompleto = domain.getNomecompleto();
    	this.nomeocial = domain.getNomeocial();
    	this.genero = domain.getGenero();
    	this.tipodedocumento = domain.getTipodedocumento();
    	this.documento = domain.getDocumento();
    	this.email = domain.getEmail();
    	this.senha = domain.getSenha();
    	try {
			this.datadenascimento = (sf.format(domain.getDatadenascimento()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getNomecompleto() {
		return nomecompleto;
	}

	public void setNomecompleto(String nomecompleto) {
		this.nomecompleto = nomecompleto;
	}

	public String getNomeocial() {
		return nomeocial;
	}

	public void setNomeocial(String nomeocial) {
		this.nomeocial = nomeocial;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getDatadenascimento() {
		return datadenascimento;
	}

	public void setDatadenascimento(String datadenascimento) {
		this.datadenascimento = datadenascimento;
	}

	public String getTipodedocumento() {
		return tipodedocumento;
	}

	public void setTipodedocumento(String tipodedocumento) {
		this.tipodedocumento = tipodedocumento;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
    
    
}
