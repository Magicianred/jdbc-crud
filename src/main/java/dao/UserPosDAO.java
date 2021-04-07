package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexaojdbc.SingleConnection;
import model.BeanUserPhone;
import model.Telephone;
import model.Userposjava;

public class UserPosDAO {
    private Connection connection;


    public UserPosDAO() {
        connection = SingleConnection.getConnection();
    }

    public void save(Userposjava userposjava) {
        try {   
            String sql = "INSERT INTO userposjava (nome, email) VALUES (?, ?)";
            PreparedStatement insert = connection.prepareStatement(sql);
            insert.setString(1, userposjava.getName()); // Insere na posição 2 (nome) o getNome do objeto
            insert.setString(2, userposjava.getEmail());
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

    public void saveTelephone(Telephone telephone) {
        try {
            String sql = "INSERT INTO telefoneuser(numero, tipo, usuariopessoa) VALUES (?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, telephone.getNumber());
            statement.setString(2, telephone.getType());
            statement.setLong(3, telephone.getUser());
            statement.execute();
            connection.commit();

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

    // Retorna lista de usarios com número de telefone
    public List<BeanUserPhone> listUserFone (Long idUser) {
        List<BeanUserPhone> beanUserPhone = new ArrayList<BeanUserPhone>();

        String sql = "SELECT * FROM telefoneuser AS fone ";
        sql += " INNER JOIN userposjava AS userp ";
        sql += " ON fone.usuariopessoa  = userp.id ";
        sql += " WHERE userp.id = " + idUser;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            // Objeto ResultSet guarda os dados vindos do DB
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                BeanUserPhone userPhone = new BeanUserPhone();

                // Seta o objeto com os dados vindos do DB
                userPhone.setEmail(result.getString("email"));
                userPhone.setName(result.getString("nome"));
                userPhone.setNumber(result.getString("numero"));

                beanUserPhone.add(userPhone); // Adiciona o objeto à lista
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return beanUserPhone;
    }

    public void update(Userposjava userposjava) {
        try {
            String sql = "UPDATE userposjava SET nome = ? WHERE id = " + userposjava.getId();

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, userposjava.getName());
            statement.execute();

            connection.commit();

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

            e.printStackTrace();
        }
    }

    public void delete(Long id) {
        try {
            String sql = "DELETE FROM userposjava WHERE id = " + id;

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.execute();

            connection.commit();

        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

            e.printStackTrace();
        }
    }

    public void deleteUserPhone(Long idUser) {
        String sqlPhone = "DELETE FROM telefoneuser WHERE usuariopessoa = " + idUser; 
        String sqlUser = "DELETE FROM userposjava WHERE id = " + idUser;
    
        try {
            // Deleta primeiro a tabela filho de telefone
            PreparedStatement statement = connection.prepareStatement(sqlPhone);
            statement.executeUpdate();
            connection.commit();

            // Em seguida deleta a tabela de usuário
            statement = connection.prepareStatement(sqlUser);
            statement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();

            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }
}