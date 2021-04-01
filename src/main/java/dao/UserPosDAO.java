package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexaojdbc.SingleConnection;
import model.Userposjava;

public class UserPosDAO {
    private Connection connection;


    public UserPosDAO() {
        connection = SingleConnection.getConnection();
    }

    public void save(Userposjava userposjava) {
        try {   
            String sql = "INSERT INTO userposjava (id, nome, email) VALUES (?, ?, ?)";
            PreparedStatement insert = connection.prepareStatement(sql);
            insert.setLong(1, userposjava.getId()); // Insere na posição 1 (id) o getId do objeto
            insert.setString(2, userposjava.getName()); // Insere na posição 2 (nome) o getNome do objeto
            insert.setString(3, userposjava.getEmail());
            insert.execute(); // Executa no banco
            connection.commit(); //Salva no banco
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    // Retorna todos os registros em uma lista
    public List<Userposjava> list() throws Exception {
        List<Userposjava> list = new ArrayList<Userposjava>();

        String sql = "SELECT * from userposjava";

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet result = statement.executeQuery();

        while (result.next()) { // Enquanto tiver dados, execute:
            Userposjava userposjava = new Userposjava();
            userposjava.setId(result.getLong("id"));
            userposjava.setName(result.getString("nome"));
            userposjava.setEmail(result.getString("email"));

            list.add(userposjava);
        }

        return list;
    }

    // Retorna apenas um registro
    public Userposjava search(Long id) throws Exception {
        Userposjava record = new Userposjava();

        String sql = "SELECT * from userposjava WHERE id = " + id;

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet result = statement.executeQuery();

        while (result.next()) { // Enquanto tiver dados, execute:
            record.setId(result.getLong("id"));
            record.setName(result.getString("nome"));
            record.setEmail(result.getString("email"));
        }

        return record;
    }
}