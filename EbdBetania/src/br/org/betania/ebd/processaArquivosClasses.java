package br.org.betania.ebd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class processaArquivosClasses {
	public static String CAMINHO_LOG = "/home/01553360702/Dropbox/Igreja/EBD/cadastro"; 
	
	public static void processaFiles(){
		File dir = new File(CAMINHO_LOG) ; 
		 
		File[] arquivos = dir.listFiles();

		for (int i = 0; i < arquivos.length; i++) {
			leUmFileCarregaBanco(arquivos[i].getPath());
		}
	}
	
	public static void leUmFileCarregaBanco(String nomeArq){
		 File e = new File(nomeArq);
		 String linha;
		 
		 BufferedReader in;
		try {
			in = new BufferedReader(new FileReader(e));
			 while (in.ready()){
				 linha = in.readLine();
				 linha = in.readLine();//retira cabecalho
				 Aluno aluno = new Aluno();
				 linha = linha.substring(linha.indexOf(",") +2 , linha.length());//retira nr na planilha e primeiro aspas
				 aluno.setNome(linha.substring(0 , linha.indexOf('"')));
				 linha = linha.substring(linha.indexOf('"') +3 , linha.length());//retira nome  e virgula
				 aluno.setTelefone1(linha.substring(0 , linha.indexOf('"')));
				 if(aluno.getTelefone1().indexOf("/") >=0){
					 //tenho dois telefones
					 aluno.setTelefone2(aluno.getTelefone1().substring(aluno.getTelefone1().indexOf("/"), aluno.getTelefone1().indexOf('"')));
					 aluno.setTelefone1(aluno.getTelefone1().substring(0,aluno.getTelefone1().indexOf("/")));
				 }
				 linha = linha.substring(linha.indexOf('"') +3 , linha.length());//retira telefone e virgula
				 aluno.setEmail(linha.substring(0 , linha.indexOf('"')));
				 linha = linha.substring(linha.indexOf('"') +2 , linha.length());//retira email. Na data nascimento arquivo csv não coloca aspas
				 aluno.setDtNasc(linha.substring(0 , linha.indexOf(",")));
				 linha = linha.substring(linha.indexOf('"') +1 , linha.length());//retira data nascimento
				 
			 }

		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
	
/*	public boolean inserir(Chamada c,BancoMysql b){
		
		try{
			
			Class.forName("org.gjt.mm.mysql.Driver").newInstance();
			Connection conn = b.getConn();
			Statement st = conn.createStatement();
			
			String sql;
		    sql = ("INSERT into chamada(data,hora,entrada,saida,codigo_programa,cpf,codigo_transacao,logon, W0, WA, NAT, linhaArquivo, nomeArquivo) " +
	                	  "values ('"+c.getData()+"', '"+c.getHora()+"', '"+c.getEntrada()+"', '"+c.getSaida()+ "', '" + c.getPrograma().getNome()+"', '"+
	                	  c.getUsuario().getCpf()+"', '"+c.getTransacao().getCodigo()+"','" + c.getLogon()+ "','" + c.getW0() +"','" +c.getWA()+ "','" + 
	                	  c.getNAT() + "', '"+ c.getLinhaArquivo() +"', '"+ c.getNomeArquivo() + "')") ;
	 		
		   	st.executeUpdate(sql);
		
			st.close();
			
			} catch(Exception ex){
			ex.printStackTrace();
		
			}
			
			return true;
	}*/

	
	
}
