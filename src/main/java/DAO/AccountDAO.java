package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.spi.DirStateFactory.Result;

import Model.Account;
import Util.ConnectionUtil;

public class AccountDAO {
    
    // Process New User Registrations
    public Account createNewAccount(Account account) {
        Connection con = ConnectionUtil.getConnection();

        try {
            String sql = "INSERT into account (username, password) VALUES ?, ?;";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, account.getUsername());
            ps.setString(2, account.getPassword());

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                int generated_account_id = (int) rs.getLong(1);
                return new Account(generated_account_id, account.getUsername(), account.getPassword());
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    // Process User Logins

    // Get accounts by id
    public Account getAccountById(Account account, int id) {
        Connection con = ConnectionUtil.getConnection();
        try {
            String sql = "SELECT * FROM account WHERE account_id LIKE ?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Account newAccount = new Account(
                    rs.getInt("account_id"),
                    rs.getString("username"),
                    rs.getString("password")
                );
                return newAccount;
            }

            

        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    return null;
    }
}
