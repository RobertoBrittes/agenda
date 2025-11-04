package br.edu.ifpr.agenda.model.dao;

import java.sql.Statement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;

import br.edu.ifpr.agenda.model.Contato;

public class ContatoDAO {
    //Create
    public void salvarSemSemEndereco(Contato contato){
        String sqlContato = "INSERT INTO contatos(nome, celular, email) VALUES(?,?,?)";
        Connection con = ConnectionFactory.getConnection();
        try {
            PreparedStatement psContato =  con.prepareStatement(sqlContato);
            psContato.setString(1, contato.getNome());
            psContato.setString(2, contato.getCelular());
            psContato.setString(3, contato.getEmail());
            psContato.executeUpdate();
            System.out.println("Contato inserido com sucesso");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void delete(int id) {
        Connection con = ConnectionFactory.getConnection();
        try {
            String sql = "DELETE FROM contatos WHERE id=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Contato excluido com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            // TODO: handle exception
        }
    }

    public void deleteBDA() {
        Connection con = ConnectionFactory.getConnection();
        try {
            String sql = "DELETE FROM contatos";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.executeUpdate();
            System.out.println("Contato excluido com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            // TODO: handle exception
        }
    }

    public void salvar(Contato contato){
        Connection con = ConnectionFactory.getConnection();
        String sqlEndereco = "INSERT INTO enderecos(rua, numero, cidade, estado) VALUES(?,?,?,?)";
        try {
            PreparedStatement psEndereco = con.prepareStatement(sqlEndereco,Statement.RETURN_GENERATED_KEYS);
            psEndereco.setString(1, contato.getEndereco().getRua());
            psEndereco.setString(2, contato.getEndereco().getNumero());
            psEndereco.setString(3, contato.getEndereco().getCidade());
            psEndereco.setString(4, contato.getEndereco().getEstado());
            psEndereco.executeUpdate();

            ResultSet rs = psEndereco.getGeneratedKeys();
            int idEndereco = 0;
            if(rs.next()) idEndereco=rs.getInt(1);

            String sqlContato = "INSERT INTO contatos(nome,celular,email,id_endereco) VALUES(?,?,?,?)";
            PreparedStatement psContato = con.prepareStatement(sqlContato);
            psContato.setString(1, contato.getNome());
            psContato.setString(2, contato.getCelular());
            psContato.setString(3, contato.getCelular());
            psContato.setInt(4, idEndereco);
            psContato.executeUpdate();
            System.out.println("Tudo certo parça");
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}
