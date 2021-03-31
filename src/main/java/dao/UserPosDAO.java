package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}