package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ExcluirPessoa1 {

	public static void main(String[] args) throws SQLException {
		Scanner sc = new Scanner(System.in);

		Connection conexao = FabricaConexao.getConexao();
		
		String sqlSelect = "SELECT * FROM pessoas;";
		String sqlDelete = "DELETE FROM pessoas WHERE codigo = ?";

		System.out.println("Lista de usuários:");
		PreparedStatement stmt = conexao.prepareStatement(sqlSelect);
		ResultSet resultado = stmt.executeQuery();
		
		while (resultado.next()) {
			System.out.println("id:"+resultado.getInt("codigo"));
			System.out.println("nome:"+resultado.getString("nome"));
		}
		
		System.out.println("Deseja deletar usuário? s|n");
		
		System.out.println("Digite o id do usuário que deseja exluir");
	
		stmt.close();
		int codigo = sc.nextInt();
		stmt = conexao.prepareStatement(sqlDelete);

		stmt.setInt(1, codigo);
		stmt.execute();

		if (stmt.executeUpdate() >0) {
			System.out.println("Usuário deletado.");
			
		}else {
			System.out.println("Nada feito!");
		}	
		conexao.close();
		sc.close();
		
	}
}
