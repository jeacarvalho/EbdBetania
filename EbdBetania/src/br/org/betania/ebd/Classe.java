package br.org.betania.ebd;

public class Classe {
	private String nomeClasse;
	private String ano;
	private String semestre;
	public String getNomeClasse() {
		return nomeClasse;
	}
	public void setNomeClasse(String nomeClasse) {
		this.nomeClasse = nomeClasse;
	}
	public String getAno() {
		return ano;
	}
	public void setAno(String ano) {
		this.ano = ano;
	}
	public String getSemestre() {
		return semestre;
	}
	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}
	@Override
	public String toString() {
		
		return this.nomeClasse + " " + this.ano +" " + this.semestre;
	}
	
	
	
}
