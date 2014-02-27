package br.org.betania.ebd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Aluno {
	private String nome="";
	private String email="";
	private String dtNasc="";
	private String telefone1="";
	private String telefone2="";
	private String isMembro="";
	
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
	
	
	public boolean inserir(BancoMysql b){
		
		try{
			Class.forName("org.gjt.mm.mysql.Driver").newInstance();
			Connection conn = b.getConn();
			Statement st = conn.createStatement();
			
			String sql;
			int idAluno = verificaExistenciaAluno(b);
			if(idAluno == 0){
				sql = ("INSERT INTO Aluno(nome, dtNasc, telefone1, telefone2, email, iSmembro) VALUES (" +
	              	  "'"+this.getNome().trim()+"', " + "'"+this.getDtNasc().trim()+"', " + "'"+this.getTelefone1().trim()+"', " +
	              	  "'"+this.getTelefone2().trim()+"', " +"'"+this.getEmail().trim()+"', " + "'"+this.getIsMembro().trim()+"' " +")") ;

		 		
				System.out.println(sql);
				
			   	st.executeUpdate(sql);
			
				st.close();
				idAluno = verificaExistenciaAluno(b);
			}
			
			this.getClasseAluno().inserir(b, idAluno);
			this.getCelulaAluno().inserir(b, idAluno);
			
			
			} catch(Exception ex){
			ex.printStackTrace();
		
			}
			
		return true;
	}
	
	private int verificaExistenciaAluno(BancoMysql b){
		int retorno=0;
		ResultSet rs;
		try {
			Class.forName("org.gjt.mm.mysql.Driver").newInstance();
			Connection conn = b.getConn();
			Statement st = conn.createStatement();
			String sql = "SELECT idAluno FROM Aluno WHERE email='" + this.getEmail().trim() + "'";
			st.executeQuery(sql);
			rs = st.getResultSet();
			if(rs.next()){
			 retorno = rs.getInt("idAluno");	 
			}
				 
			sql = "SELECT idAluno FROM Aluno WHERE nome='" + this.getNome().trim() + "'";
			st.executeQuery(sql);
			rs = st.getResultSet();
			if(rs.next()){
			 retorno = rs.getInt("idAluno");	 
			}

			sql = "SELECT idAluno FROM Aluno WHERE telefone1='" + this.getTelefone1().trim() + "' and dtNasc='" +  this.getDtNasc().trim()+ "'";
			st.executeQuery(sql);
			rs = st.getResultSet();
			if(rs.next()){
			 retorno = rs.getInt("idAluno");	 
			}
			
			

		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retorno;
	}
}
