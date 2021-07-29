package br.edu.iff.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.edu.iff.conexao.ConexaoUtil;
import br.edu.iff.model.Produto;
import javax.swing.JOptionPane;

public class ProdutoDao {

    public void inserir(Produto produto) {

        try {

            Connection connection = ConexaoUtil.getInstance().getConnection();
            String sql = "INSERT INTO produto(numeroPeca, tipo, modelo, material, preco, quantidade) VALUES(?, ?, ?, ?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, produto.getNumeroPeca());
            statement.setString(2, produto.getTipo());
            statement.setString(3, produto.getModelo());
            statement.setString(4, produto.getMaterial());
            statement.setDouble(5, produto.getPreco());
            statement.setInt(6, produto.getQuantidade());

            statement.execute();
            connection.close();

            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso");
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Erro:" + e);
        }

    }

    public void remover(int id) {

        try {

            Connection connection = ConexaoUtil.getInstance().getConnection();

            String sql = "DELETE FROM produto WHERE id = ?";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, id);

            statement.execute();
            statement.close();

            JOptionPane.showMessageDialog(null, "Removido com sucesso");

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Erro:" + e);
        }

    }

    public List<Produto> listarTodos() {

        List<Produto> listaProdutos = new ArrayList<Produto>();
        try {
            Connection connection = ConexaoUtil.getInstance().getConnection();

            String sql = "SELECT * FROM produto";

            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultset = statement.executeQuery();

            while (resultset.next()) {

                Produto produto = new Produto();

                produto.setId(resultset.getInt("id"));
                produto.setNumeroPeca(resultset.getInt("numeroPeca"));
                produto.setTipo(resultset.getString("tipo"));
                produto.setModelo(resultset.getString("modelo"));
                produto.setMaterial(resultset.getString("material"));
                produto.setPreco(resultset.getDouble("preco"));
                produto.setQuantidade(resultset.getInt("quantidade"));

                listaProdutos.add(produto);

            }
            connection.close();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Erro:" + e);
        }

        return listaProdutos;

    }

    public void atualizar(Produto produto) {

        try {

            Connection connection = ConexaoUtil.getInstance().getConnection();

            String sql = "UPDATE produto SET numeroPeca = ?, tipo = ?, modelo = ?, material = ?, preco = ?, quantidade = ? WHERE id = ?";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, produto.getNumeroPeca());
            statement.setString(2, produto.getTipo());
            statement.setString(3, produto.getModelo());
            statement.setString(4, produto.getMaterial());
            statement.setDouble(5, produto.getPreco());
            statement.setInt(6, produto.getQuantidade());
            statement.setInt(7, produto.getId());

            statement.execute();
            statement.close();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso");
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Erro:" + e);
        }

    }

    public static void main(String[] args) {

    }

    public List<Produto> buscarProduto(String modelo) {

        List<Produto> listaProdutos = new ArrayList<Produto>();
        try {
            Connection connection = ConexaoUtil.getInstance().getConnection();

            String sql = "SELECT * FROM produto WHERE modelo LIKE ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(3, modelo);
            ResultSet resultset = statement.executeQuery();

            while (resultset.next()) {

                Produto produto = new Produto();
                produto.setId(resultset.getInt("id"));
                produto.setNumeroPeca(resultset.getInt("numeroPeca"));
                produto.setTipo(resultset.getString("tipo"));
                produto.setModelo(resultset.getString("modelo"));
                produto.setMaterial(resultset.getString("material"));
                produto.setPreco(resultset.getDouble("preco"));
                produto.setQuantidade(resultset.getInt("quantidade"));
                listaProdutos.add(produto);

            }
            connection.close();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Erro:" + e);
        }

        return listaProdutos;

    }

    public Produto buscarProdutoId(int id) {

        try {
            Connection connection = ConexaoUtil.getInstance().getConnection();

            String sql = "SELECT * FROM produto WHERE id=?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultset = statement.executeQuery();
            Produto produto = new Produto();
            while (resultset.next()) {

                produto.setId(resultset.getInt("id"));
                produto.setTipo(resultset.getString("tipo"));
                produto.setQuantidade(resultset.getInt("quantidade"));
                produto.setPreco(resultset.getDouble("preco"));

            }
            return produto;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Produto não encontrado!");
            return null;
        }

    }

    public void baixaEstoque(int id, int qtdNova) {

        try {
            Connection connection = ConexaoUtil.getInstance().getConnection();
            String sql = "UPDATE produto SET quantidade=? where id=?";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, qtdNova);
            statement.setInt(2, id);
            statement.execute();
            statement.close();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Erro:" + e);

        }
    }

    public int retornaEstoque(int id) {

        try {

            Connection connection = ConexaoUtil.getInstance().getConnection();
            int quantidade = 0;
            String sql = "SELECT quantidade=? FROM produto WHERE id=?";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, id);
            ResultSet resultset = statement.executeQuery();
            if (resultset.next()) {

                quantidade = (resultset.getInt("quantidade"));

            }

            return quantidade;

        } catch (Exception e) {

            throw new RuntimeException(e);

        }

    }

}
