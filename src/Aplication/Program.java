package Aplication;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import Entities.Aluno;
import JDBC.AlunoJDBC;
import JDBC.DB;

public class Program {

	public static void main(String[] args) throws IOException, SQLException {
		
		Connection con = DB.getConexao();
		
        try {
        	        	
            int opcao = 0;
            Scanner console = new Scanner(System.in);
            AlunoJDBC acao = new AlunoJDBC();
            
            do {
            	System.out.print("******** Menu *******"
                                 + "\n1- Cadastrar"
                                 + "\n2- Listar"
                                 + "\n3- Alterar"
                                 + "\n4- Excluir"
                                 + "\n5- Sair");
                System.out.print("\n\tOpção: ");
                opcao = Integer.parseInt(console.nextLine());
                                
                if (opcao == 1){
                    
                    Aluno a = new Aluno();
                    
                    System.out.print("\n*** Cadastrar Aluno ***\n\r");
                    
                    System.out.print("Nome: ");
                    a.setNome(console.nextLine());
                    
                    System.out.print("Sexo: ");
                    a.setSexo(console.nextLine());
                    
                    System.out.print("Data de nascimento: ");
                    a.setDt_nasc( (Date) new SimpleDateFormat("dd/MM/yyyy").parse( console.nextLine() ) );
                    
                    acao.salvar(a);
                    
                    console.nextLine();
                } 	
                if (opcao == 2){
                    
                	System.out.print("\n*** Lista de Aluno ***\n\r");
                    List<Aluno> alunos = acao.listar();
                    System.out.println("Id\tSexo\t\tDataNascimento\t\tNome");
                    for(Aluno a : alunos) {
                    	  System.out.println(a.getId() + "\t" + a.getSexo() + "\t" + a.getDt_nasc() + "\t\t" + a.getNome() );
                    }
                    console.nextLine();
                    
                }
               	
                if (opcao == 3){
                    
                    Aluno a = new Aluno();
                    
                    System.out.print("\n*** Alterar Aluno ***\n\r");
                    
                    System.out.print("Id: ");
                    a.setId(console.nextInt());
                    console.nextLine();
                    
                    System.out.print("Nome: ");
                    a.setNome(console.nextLine());
                    
                    System.out.print("Sexo: ");
                    a.setSexo(console.nextLine());
                    
                    System.out.print("Data de nascimento: ");
                    a.setDt_nasc( (Date) new SimpleDateFormat("dd/MM/yyyy").parse( console.nextLine() ) );
                    
                    acao.alterar(a);
                    
                    console.nextLine();
                }
                if (opcao == 4){
                    
                    System.out.print("\n*** Excluir Aluno ***\n\r");
                    
                    System.out.print("Digite o Id: ");
                    int id = console.nextInt();
                    console.nextLine();
                    
                    acao.apagar(id);
                    
                    console.nextLine();
                } 
            } while(opcao != 5);
            
        } catch (Exception e){
            System.out.println("Erro: " + e);
        }
        finally {
        	DB.fechaConexao();
        }
	}
}