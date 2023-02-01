package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AlterarNomePessoa1 {

	public static void main(String[] args) throws SQLException {
		Scanner sc = new Scanner(System.in);

		Connection conexao = FabricaConexao.getConexao();
		String sqlSelect = "SELECT * FROM pessoas;";

		PreparedStatement stmt = conexao.prepareStatement(sqlSelect);
		ResultSet resultadoSelect = stmt.executeQuery();
		

		while (resultadoSelect.next()) {
			System.out.printf("ID:" + resultadoSelect.getInt("codigo"));
			System.out.printf(" Nome:" + resultadoSelect.getString("nome"));
			System.out.println();
		}

		System.out.println("Deseja alterar nome? (s|n)");

		
			if (sc.nextLine().equalsIgnoreCase("s")) {

			String sqlUpdate = "UPDATE pessoas SET nome = ? where codigo = ?;";

			System.out.printf("Digite o novo nome:");
			String novoNome = sc.nextLine();
			System.out.printf("chave ID:");
			int id = sc.nextInt();

			stmt.close();
			
			stmt = conexao.prepareStatement(sqlUpdate);
			stmt.setString(1, novoNome);
			stmt.setInt(2, id);
			
			stmt.execute();

			System.out.println("Novo nome definido!");

			stmt.close();
			stmt = conexao.prepareStatement(sqlSelect);
			resultadoSelect = stmt.executeQuery();

//			stmt.close();
			while (resultadoSelect.next()) {
				System.out.println("id:"+resultadoSelect.getInt("codigo"));
				System.out.println("nome: "+resultadoSelect.getString("nome"));
			}

		}

//		stmt = conexao.createStatement();
			conexao.close();
			sc.close();
	}

}
