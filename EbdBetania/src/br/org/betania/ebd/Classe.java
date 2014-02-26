package br.org.betania.ebd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	
	public boolean inserir(BancoMysql b, int idAluno){
		
		try{
			
			Class.forName("org.gjt.mm.mysql.Driver").newInstance();
			String sql;
			Connection conn;
			Statement st;
			int idClasse = verificaExistenciaClasse(b);
			
			if(idClasse == 0){
				
				conn = b.getConn();
				st = conn.createStatement();
				sql = ("INSERT INTO classe (nome, ano, semestre) VALUES (" +
	              	  "'"+this.getNomeClasse().trim()+"', " + "'"+this.getAno().trim()+"', " + "'"+this.getSemestre().trim()+"')") ;
		 		
			   	st.executeUpdate(sql);
			
				st.close();
			}
			//aqui ha uma chamada a mais, mas preciso pegar o idClasse se inseri no if
			
			idClasse = verificaExistenciaClasse(b);
			conn = b.getConn();
			st = conn.createStatement();
			sql = "INSERT INTO aluno_classe(idAluno, idClasse) VALUES (" + String.valueOf(idAluno) + ", " + String.valueOf(idClasse) + ")" ;
	 		
		   	st.executeUpdate(sql);
		
			st.close();
			
			
			} catch(Exception ex){
			ex.printStackTrace();
		
			}
			
		return true;
	}

	private int verificaExistenciaClasse(BancoMysql b){
		int retorno=0;
		ResultSet rs;
		try {
			Class.forName("org.gjt.mm.mysql.Driver").newInstance();
			Connection conn = b.getConn();
			Statement st = conn.createStatement();
			String sql = "SELECT idClasse FROM classe WHERE nome='" + this.getNomeClasse().trim() + "' and ano='" +  this.getAno().trim()+ "'" + " and semestre='" +  this.getSemestre().trim()+ "'";
			st.executeQuery(sql);
			rs = st.getResultSet();
			if(rs.next()){
			 retorno = rs.getInt("idClasse");	 
			}

		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retorno;
	}	
	
}
