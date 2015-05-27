package com.example.formtest;

public class Usuario{
	private String nome;
	private String password;
	private String cidade;
	private String telefone;
	private long id;
	private String passAdmSecurity = "123";
	
	public String getSecurityPassword()
	{
		return passAdmSecurity;
	}
	
	public void setId(long id)
	{this.id = id;}
	
	public long setId()
	{return this.id;}

	public void setPassword(String password)
	{this.password = password;}
	public String getPassword()
	{return this.password;}

	public void setNome(String nome)
	{this.nome = nome;}
	public String getNome()
	{return this.nome;}

	public void setCidade(String cidade)
	{this.cidade = cidade;}
	public String getCidade()
	{return this.cidade;}

	public void setTelefone(String telefone)
	{this.telefone = telefone;}
	public String getTelefone()
	{return this.telefone;}


}
