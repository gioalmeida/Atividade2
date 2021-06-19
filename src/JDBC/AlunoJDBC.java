package JDBC;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entities.Aluno;

public class AlunoJDBC {
	
	public void salvar(Aluno a) throws IOException, SQLException {
		
		Connection con = DB.getConexao();
		String sql = "INSERT INTO aluno (nome, sexo, dt_nasc) VALUES ( ?,  ?, ?)";
		PreparedStatement pst = null;
		
		try {			
			pst = con.prepareStatement(sql);
			
			pst.setString(1, a.getNome());
			pst.setString(2, a.getSexo());
			pst.setDate(3, new Date( a.getDt_nasc().getTime() ));
			
			if ( pst.executeUpdate() >= 1 )		
				System.out.println("Aluno cadastrado com Sucesso!");
			else
				System.out.println("Não foi possível cadastrar o aluno !");
				
		}
		catch (SQLException e) {
			System.out.println(e);
		}
		finally{
			pst.close();
		}
	}
	
	public List<Aluno> listar() throws SQLException, IOException {
		
		List<Aluno> lista = new ArrayList<>();
		
		Connection con = DB.getConexao();
		String sql = "Select * from aluno";
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			pst = con.prepareStatement(sql);
			
			rs = pst.executeQuery();

			while (rs.next())
			{   
				Aluno a = new Aluno(rs.getInt("idaluno"), rs.getString("nome"), rs.getString("sexo"), rs.getDate("dt_nasc"));
				lista.add(a);
			}
		}
		catch (SQLException e) {
			System.out.println(e);
		}
		finally {
			rs.close();
			pst.close();
		}
		return lista;	
	}
	
	public void alterar(Aluno a) throws SQLException, IOException {
		
		Connection con = DB.getConexao();
		String sql = "UPDATE aluno SET nome = ?, sexo = ?, dt_nasc = ? WHERE idaluno = ?";
		PreparedStatement pst = null;
		
		try {
			pst = con.prepareStatement(sql);
			
			pst.setString(1, a.getNome());
			pst.setString(2, a.getSexo());
			Date dataSql = new Date(a.getDt_nasc().getTime());
			pst.setDate(3,dataSql);
			pst.setInt(4, a.getId());
			
			if ( pst.executeUpdate() >= 1 )		
				System.out.println("Aluno alterado com Sucesso!");
			else
				System.out.println("Não foi encontrado aluno com id " +  a.getId() + " !");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			pst.close();
		}
	}
	
	public void apagar(int id) throws IOException, SQLException {
		
		Connection con = DB.getConexao();
		String sql = "DELETE FROM aluno where idaluno = ?";
		PreparedStatement pst = null;
	
		try {
			pst = con.prepareStatement(sql);
			
			pst.setInt(1, id);
			
			if ( pst.executeUpdate() >= 1 )		
				System.out.println("Aluno Excluído com Sucesso!");
			else
				System.out.println("Não foi encontrado aluno com id " + id + " !");
		}
		catch (SQLException e) {
				e.printStackTrace();
		}
		finally {
			pst.close();
		}
	}
}
