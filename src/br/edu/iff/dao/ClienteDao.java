package br.edu.iff.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import br.edu.iff.conexao.ConexaoUtil;
import br.edu.iff.model.Cliente;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ClienteDao {

    public void inserir(Cliente cliente) {

        try {

            Connection connection = ConexaoUtil.getInstance().getConnection();
            String sql = "INSERT INTO cliente(nome, cpf, dataNascimento, telefone, rg, reputacao) VALUES(?, ?, ?, ?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, cliente.getNome());
            statement.setString(2, cliente.getCpf());
            statement.setString(3, cliente.getDataNascimento());
            statement.setString(4, cliente.getTelefone());
            statement.setString(5, cliente.getRg());
            statement.setInt(6, cliente.getReputacao());

            statement.execute();
            connection.close();

            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso");
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Erro:" + e);
        }

    }

    public void remover(Cliente cliente) {

        try {

            Connection connection = ConexaoUtil.getInstance().getConnection();

            String sql = "DELETE FROM cliente WHERE id = ?";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, cliente.getId());

            statement.execute();
            statement.close();

            JOptionPane.showMessageDialog(null, "Removido com sucesso");

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Erro:" + e);
        }

    }

    public List<Cliente> listarTodos() {

        List<Cliente> listaClientes = new ArrayList<Cliente>();
        try {
            Connection connection = ConexaoUtil.getInstance().getConnection();

            String sql = "SELECT * FROM cliente";

            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultset = statement.executeQuery();

            while (resultset.next()) {

                Cliente cliente = new Cliente();
                cliente.setId(resultset.getInt("id"));
                cliente.setNome(resultset.getString("nome"));
                cliente.setCpf(resultset.getString("cpf"));
                cliente.setDataNascimento(resultset.getString("dataNascimento"));
                cliente.setTelefone(resultset.getString("telefone"));
                cliente.setRg(resultset.getString("rg"));
                cliente.setReputacao(resultset.getInt("reputacao"));

                listaClientes.add(cliente);

            }
            connection.close();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Erro:" + e);
        }

        return listaClientes;

    }

    public void atualizar(Cliente cliente) {

        try {

            Connection connection = ConexaoUtil.getInstance().getConnection();

            String sql = "UPDATE cliente SET nome = ?, cpf = ?, dataNascimento = ?, telefone = ?, rg = ?, reputacao = ? WHERE id = ?";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, cliente.getNome());
            statement.setString(2, cliente.getCpf());
            statement.setString(3, cliente.getDataNascimento());
            statement.setString(4, cliente.getTelefone());
            statement.setString(5, cliente.getRg());
            statement.setInt(6, cliente.getReputacao());
            statement.setInt(7, cliente.getId());

            statement.execute();
            statement.close();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso");
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Erro:" + e);
        }

    }

    public List<Cliente> buscarCliente(String nome) {

        List<Cliente> listaClientes = new ArrayList<Cliente>();
        try {
            Connection connection = ConexaoUtil.getInstance().getConnection();

            String sql = "SELECT * FROM cliente where nome like ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nome);
            ResultSet resultset = statement.executeQuery();

            while (resultset.next()) {

                Cliente cliente = new Cliente();
                cliente.setId(resultset.getInt("id"));
                cliente.setNome(resultset.getString("nome"));
                cliente.setCpf(resultset.getString("cpf"));
                cliente.setDataNascimento(resultset.getString("dataNascimento"));
                cliente.setTelefone(resultset.getString("telefone"));
                cliente.setRg(resultset.getString("rg"));
                cliente.setReputacao(resultset.getInt("reputacao"));

                listaClientes.add(cliente);

            }
            connection.close();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Erro:" + e);
        }

        return listaClientes;

    }

    public static void main(String[] args) throws ParseException {

    }

}
