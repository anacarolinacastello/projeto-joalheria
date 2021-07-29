/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.iff.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import br.edu.iff.conexao.ConexaoUtil;
import br.edu.iff.model.Produto;
import br.edu.iff.model.Venda;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

/**
 *
 * @author anaca
 */
public class VendaDao {

    public void cadastrarVenda(Venda venda) {

        try {

            Connection connection = ConexaoUtil.getInstance().getConnection();
            String sql = "INSERT INTO venda(dataVenda, totalVenda) VALUES(  ?, ?)";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, venda.getDataVenda());
            statement.setDouble(2, venda.getTotalVenda());

            statement.execute();
            connection.close();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Erro:" + e);
        }

    }

    public int retornaVenda() {

        try {
            int id = 0;
            Connection connection = ConexaoUtil.getInstance().getConnection();
            String sql = "select max(id)id from venda";

            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultset = statement.executeQuery();

            while (resultset.next()) {

                Venda obj = new Venda();

                obj.setId(resultset.getInt("id"));

                id = obj.getId();

            }

            return id;

        } catch (Exception e) {

            throw new RuntimeException(e);
        }

    }

    public List<Venda> listarVendaPorData(LocalDate dataInicio, LocalDate dataFim) {

        List<Venda> listaVendas = new ArrayList<>();
        try {
            Connection connection = ConexaoUtil.getInstance().getConnection();

            String sql = "SELECT id, datavenda,totalVenda, FROM venda  WHERE dataVenda BETWEEN ? AND ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, dataInicio.toString());
            statement.setString(2, dataFim.toString());

            ResultSet resultset = statement.executeQuery();

            while (resultset.next()) {

                Venda venda = new Venda();

                venda.setId(resultset.getInt("id"));
                venda.setDataVenda(resultset.getString("v.dataVenda"));
                venda.setTotalVenda(resultset.getDouble("v.totalVenda"));

                listaVendas.add(venda);
            }
            connection.close();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Erro:" + e);
        }

        return listaVendas;

    }

}
