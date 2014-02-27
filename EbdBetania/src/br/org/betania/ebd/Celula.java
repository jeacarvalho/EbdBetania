package br.org.betania.ebd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Celula {
	private String nomeLider;

	public String getNomeLider() {
		return nomeLider;
	}

	public void setNomeLider(String nomeLider) {
		this.nomeLider = retiraCaracter(nomeLider, '"');
	}
	
	private String retiraCaracter(String campoARetirar, char caractereARetirar)
	{
		return campoARetirar.replace(caractereARetirar, ' ');
	}
	
	public boolean inserir(BancoMysql b, int idAluno){
		
		if((!this.getNomeLider().trim().equalsIgnoreCase(""))&&
		  (!this.getNomeLider().trim().equalsIgnoreCase("Não"))&&
		  (!this.getNomeLider().trim().equalsIgnoreCase("Sim")))
		{
			try{
				
				Class.forName("org.gjt.mm.mysql.Driver").newInstance();
				String sql;
				Connection conn;
				Statement st;
				int idCelula = verificaExistenciaCelula(b);
				
				if(idCelula == 0){
					
					conn = b.getConn();
					st = conn.createStatement();
					sql = ("INSERT INTO celula (lider) VALUES (" +
		              	  "'"+this.getNomeLider().trim()+"')") ;
			 		
				   	st.executeUpdate(sql);
				   	System.out.println(sql);
					st.close();
					
					idCelula = verificaExistenciaCelula(b);
				}
				
				if(verificaExistenciaAlunoCelula(b, idAluno, idCelula)==0){
					conn = b.getConn();
					st = conn.createStatement();
					sql = "INSERT INTO aluno_celula(idaluno, idcelula) VALUES (" + String.valueOf(idAluno) + ", " + String.valueOf(idCelula) + ")" ;
			 		
				   	st.executeUpdate(sql);
				
					st.close();
				}
				
				} catch(Exception ex){
				ex.printStackTrace();
			
				}
		}
		return true;
	}

	private int verificaExistenciaCelula(BancoMysql b){
		int retorno=0;
		ResultSet rs;
		try {
			Class.forName("org.gjt.mm.mysql.Driver").newInstance();
			Connection conn = b.getConn();
			Statement st = conn.createStatement();
			String sql = "SELECT idcelula FROM celula WHERE lider='" + this.getNomeLider().trim() + "'";
			st.executeQuery(sql);
			rs = st.getResultSet();
			if(rs.next()){
			 retorno = rs.getInt("idcelula");	 
			}

		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retorno;
	}	

	private int verificaExistenciaAlunoCelula(BancoMysql b, int idAluno, int idCelula){
		int retorno=0;
		ResultSet rs;
		try {
			Class.forName("org.gjt.mm.mysql.Driver").newInstance();
			Connection conn = b.getConn();
			Statement st = conn.createStatement();
			String sql = "SELECT idCelula FROM aluno_celula WHERE idAluno=" + String.valueOf(idAluno) + " and idcelula=" +  String.valueOf(idCelula);
			st.executeQuery(sql);
			rs = st.getResultSet();
			if(rs.next()){
			 retorno = rs.getInt("idCelula");	 
			}

		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retorno;
	}
	
}
