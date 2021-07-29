/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.iff.dao;

import br.edu.iff.conexao.ConexaoUtil;
import br.edu.iff.model.ItemVenda;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

/**
 *
 * @author anaca
 */
public class ItemVendaDao {

    public void cadastrarItem(ItemVenda itemVenda) {

        try {

            Connection connection = ConexaoUtil.getInstance().getConnection();
            String sql = "INSERT into itemvenda(idVenda, idproduto,quantidade, totalVenda) VALUES(?, ?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, itemVenda.getIdVenda().getId());
            statement.setInt(2, itemVenda.getIdproduto().getId());
            statement.setInt(3, itemVenda.getQuantidade());
            statement.setDouble(4, itemVenda.getSubTotal());

            statement.execute();
            connection.close();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Erro:" + e);
        }

    }

}
