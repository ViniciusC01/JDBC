package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsultarPessoas2 {

	public static void main(String[] args) throws SQLException {
		Scanner sc = new Scanner(System.in);

		System.out.println("Informe a letra: ");
		String letra = sc.nextLine();

		Connection conexao = FabricaConexao.getConexao();

		String sql = "select * from pessoas where nome like ?;";
		PreparedStatement stmt = conexao.prepareStatement(sql);

		stmt.setString(1, "%" + letra + "%");

		ResultSet resultado = stmt.executeQuery();

		List<Pessoa> pessoas = new ArrayList<>();

		while (resultado.next()) {
			System.out.println(resultado.getInt("codigo"));
			System.out.println(resultado.getString("nome"));
			pessoas.add(new Pessoa(resultado.getInt("codigo"), resultado.getString("nome")));
		}
		
		
		System.out.println("nomes com a letra ou frase "+letra+":");
		for (Pessoa pessoa : pessoas) {
			System.out.println("id =>"+pessoa.getCodigo()+" nome =>"+pessoa.getNome());
		}
		
		stmt.close();
		conexao.close();
		sc.close();

	}
}
