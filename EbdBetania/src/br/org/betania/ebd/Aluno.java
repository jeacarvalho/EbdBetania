package br.org.betania.ebd;

public class Aluno {
	private String nome;
	private String email;
	private String dtNasc;
	private String telefone1;
	private String telefone2;
	private String isMembro;
	
	private Celula celulaAluno;
	private Classe classeAluno;
	
	
	public Celula getCelulaAluno() {
		return celulaAluno;
	}
	public void setCelulaAluno(Celula celulaAluno) {
		this.celulaAluno = celulaAluno;
	}
	public Classe getClasseAluno() {
		return classeAluno;
	}
	public void setClasseAluno(Classe classeAluno) {
		this.classeAluno = classeAluno;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = retiraCaracter(nome, '"');
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = retiraCaracter(email, '"');
	}
	public String getDtNasc() {
		return dtNasc;
	}
	public void setDtNasc(String dtNasc) {
		this.dtNasc = retiraCaracter(dtNasc, '"');
	}
	public String getTelefone1() {
		return telefone1;
	}
	public void setTelefone1(String telefone1) {
		this.telefone1 = retiraCaracter(telefone1, '"');
	}
	public String getTelefone2() {
		return telefone2;
	}
	public void setTelefone2(String telefone2) {
		this.telefone2 = retiraCaracter(telefone2, '"');
	}
	public String getIsMembro() {
		return isMembro;
	}
	public void setIsMembro(String isMembro) {
		this.isMembro = retiraCaracter(isMembro, '"');
	}
	@Override
	public String toString() {
		return this.getNome() + " " + this.getEmail() + " " + this.getDtNasc() + " " + this.getTelefone1()+ " " + this.getTelefone2() + " " + this.classeAluno.toString(); 
	}
	
	private String retiraCaracter(String campoARetirar, char caractereARetirar)
	{
		return campoARetirar.replace(caractereARetirar, ' ');
	}
	
}
