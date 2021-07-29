package br.edu.iff.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import br.edu.iff.conexao.ConexaoUtil;
import br.edu.iff.model.Funcionario;
import br.edu.iff.view.formMenu;
import javax.swing.JOptionPane;

public class FuncionarioDao {

    public void inserir(Funcionario funcionario) {

        try {

            Connection connection = ConexaoUtil.getInstance().getConnection();
            String sql = "INSERT INTO funcionario(nome, cpf, dataNascimento, telefone, usuario, chave, nivelAcesso) VALUES(?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, funcionario.getNome());
            statement.setString(2, funcionario.getCpf());
            statement.setString(3, funcionario.getDataNascimento());
            statement.setString(4, funcionario.getTelefone());
            statement.setString(5, funcionario.getUsuario());
            statement.setInt(6, funcionario.getChave());
            statement.setString(7, funcionario.getNivelAcesso());

            statement.execute();
            connection.close();

            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso");
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Erro:" + e);
        }

    }

    public void remover(Funcionario funcionario) {

        try {

            Connection connection = ConexaoUtil.getInstance().getConnection();

            String sql = "DELETE FROM funcionario WHERE id = ?";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, funcionario.getId());

            statement.execute();
            statement.close();

            JOptionPane.showMessageDialog(null, "Removido com sucesso");

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Erro:" + e);
        }

    }

    public List<Funcionario> listarTodos() {

        List<Funcionario> listaFuncionarios = new ArrayList<Funcionario>();
        try {
            Connection connection = ConexaoUtil.getInstance().getConnection();

            String sql = "SELECT * FROM funcionario";

            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultset = statement.executeQuery();

            while (resultset.next()) {

                Funcionario funcionario = new Funcionario();
                funcionario.setId(resultset.getInt("id"));
                funcionario.setNome(resultset.getString("nome"));
                funcionario.setCpf(resultset.getString("cpf"));
                funcionario.setDataNascimento(resultset.getString("dataNascimento"));
                funcionario.setTelefone(resultset.getString("telefone"));
                funcionario.setUsuario(resultset.getString("usuario"));
                funcionario.setChave(resultset.getInt("chave"));
                funcionario.setNivelAcesso(resultset.getString("nivelAcesso"));

                listaFuncionarios.add(funcionario);

            }
            connection.close();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Erro:" + e);
        }

        return listaFuncionarios;

    }

    public void atualizar(Funcionario funcionario) {

        try {

            Connection connection = ConexaoUtil.getInstance().getConnection();

            String sql = "UPDATE funcionario SET nome = ?, cpf = ?, dataNascimento = ?, telefone = ?, usuario = ?, chave = ?, nivelAcesso = ? WHERE id = ?";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, funcionario.getNome());
            statement.setString(2, funcionario.getCpf());
            statement.setString(3, funcionario.getDataNascimento());
            statement.setString(4, funcionario.getTelefone());
            statement.setString(5, funcionario.getUsuario());
            statement.setInt(6, funcionario.getChave());
            statement.setString(7, funcionario.getNivelAcesso());
            statement.setInt(8, funcionario.getId());

            statement.execute();
            statement.close();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso");
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Erro:" + e);
        }

    }

    public List<Funcionario> buscarFuncionario(String nome) {

        List<Funcionario> listaFuncionarios = new ArrayList<Funcionario>();
        try {
            Connection connection = ConexaoUtil.getInstance().getConnection();

            String sql = "SELECT * FROM funcionario WHERE nome LIKE ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nome);
            ResultSet resultset = statement.executeQuery();

            while (resultset.next()) {

                Funcionario funcionario = new Funcionario();
                funcionario.setId(resultset.getInt("id"));
                funcionario.setNome(resultset.getString("nome"));
                funcionario.setCpf(resultset.getString("cpf"));
                funcionario.setDataNascimento(resultset.getString("dataNascimento"));
                funcionario.setTelefone(resultset.getString("telefone"));
                funcionario.setUsuario(resultset.getString("usuario"));
                funcionario.setChave(resultset.getInt("chave"));
                funcionario.setNivelAcesso(resultset.getString("nivelAcesso"));
                listaFuncionarios.add(funcionario);

            }
            connection.close();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Erro:" + e);
        }

        return listaFuncionarios;

    }

    public void login(String usuario, int chave) {

        try {

            Connection connection = ConexaoUtil.getInstance().getConnection();
            String sql = "SELECT * FROM funcionario WHERE usuario=? AND chave=?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, usuario);
            statement.setInt(2, chave);
            ResultSet resultset = statement.executeQuery();
            if (resultset.next()) {

                JOptionPane.showMessageDialog(null, "Login efetuado com sucesso!");
                formMenu tela = new formMenu();
                tela.setVisible(true);

            } else {

                JOptionPane.showMessageDialog(null, "Dados incorretos!");

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro:" + e);
        }

    }

    public static void main(String[] args) throws ParseException {

    }

}
