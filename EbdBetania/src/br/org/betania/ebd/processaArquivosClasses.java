package br.org.betania.ebd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class processaArquivosClasses {
	public static String CAMINHO_LOG = "/home/01553360702/Dropbox/Igreja/EBD/cadastro"; 
	
	public static void processaFiles(){
		File dir = new File(CAMINHO_LOG) ; 
		 
		File[] arquivos = dir.listFiles();
		ArrayList<Aluno> alunos = new ArrayList<Aluno>();
		for (int i = 0; i < arquivos.length; i++) {
			String arquivo = arquivos[i].getName();
			String classe = arquivo.substring(0, arquivo.indexOf("*"));
			arquivo =  arquivo.substring(arquivo.indexOf("*"), arquivo.length());
			String anoClasse = arquivo.substring(arquivo.indexOf("*") +1 ,arquivo.indexOf("-"));
			String semestreClasse = arquivo.substring(arquivo.indexOf("-") +1 , arquivo.indexOf("."));
			leUmFileCarregaBanco(arquivos[i].getPath(), alunos, classe, anoClasse, semestreClasse);
		}
		BancoMysql b = new BancoMysql();
		for (Aluno aluno : alunos) {
			aluno.inserir(b);
		}
		
		
		
	}
	
	public static void leUmFileCarregaBanco(String nomeArq, ArrayList<Aluno> alunos, String nomeClasse, String anoClasse, String semestreClasse ){
		 File e = new File(nomeArq);
		 String linha;
		 Classe classeAluno = new Classe();
		 classeAluno.setNomeClasse(nomeClasse);
		 classeAluno.setAno(anoClasse);
		 classeAluno.setSemestre(semestreClasse);
		 
		 BufferedReader in;
		try {
			in = new BufferedReader(new FileReader(e));
			
			if(in.ready()){
				linha = in.readLine();//retira cabecalho	
			}
			 
			while (in.ready()){
				 
				 linha = in.readLine();
				 Aluno aluno = new Aluno();
				 
				 String[] camposLinha = quebraLinhaCampos(linha, ',');
				 
				 aluno.setNome(camposLinha[1]);
				 aluno.setTelefone1(camposLinha[2]);
				 if(aluno.getTelefone1().indexOf("/") >=0){
					 //tenho dois telefones
					 aluno.setTelefone2(aluno.getTelefone1().substring(aluno.getTelefone1().indexOf("/") + 1, aluno.getTelefone1().length()));
					 aluno.setTelefone1(aluno.getTelefone1().substring(0,aluno.getTelefone1().indexOf("/")));
				 }
				 aluno.setEmail(camposLinha[3]);
				 aluno.setDtNasc(camposLinha[4]);
				 aluno.setIsMembro(camposLinha[6]);
				 Celula celulaAluno = new Celula();
				 celulaAluno.setNomeLider(((camposLinha[7].indexOf("-") == -1) ? camposLinha[7] : camposLinha[7].substring(camposLinha[7].indexOf("-") + 1 , camposLinha[7].length())));
				 aluno.setCelulaAluno(celulaAluno);
			 	 aluno.setClasseAluno(classeAluno);

			 	 aluno.setClasseAluno(classeAluno);
			 	 alunos.add(aluno);
				 
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

		public static String[] quebraLinhaCampos(String linha, char separador){
			String[] retorno = new String[8];
			int i = 0;
			while(i<=6){
				retorno[i] = linha.substring(0, linha.indexOf(separador));
				linha = linha.substring(linha.indexOf(separador) +1 , linha.length());
				i = i+1;
			}
			retorno[7] = linha;
			
			return retorno;
		}
	
	
}
