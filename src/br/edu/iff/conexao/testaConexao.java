/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.iff.conexao;

import javax.swing.JOptionPane;

/**
 *
 * @author anaca
 */
public class testaConexao {

    public static void main(String[] args) {

        try {
            new ConexaoUtil().getConnection();
            JOptionPane.showMessageDialog(null, "Conectado com sucesso");
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Erro:" + e);

        }

    }

}
