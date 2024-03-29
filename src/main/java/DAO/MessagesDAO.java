package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Model.Message;
import Util.ConnectionUtil;
public class MessagesDAO {
    
    // Create a new message
    public Message createMessage(Message message) {
        Connection con = ConnectionUtil.getConnection();

        try {
            String sql = "INSERT INTO message (posted_by, message_text, time_posted_epoch) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, message.getPosted_by());
            ps.setString(2, message.getMessage_text());
            ps.setLong(3, message.getTime_posted_epoch());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                int generated_message_id= rs.getInt("message_id");
                return new Message(generated_message_id, message.getPosted_by(), message.getMessage_text(), message.getTime_posted_epoch());
            } 

        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    // Retrieve all messages
    public List<Message> getAllMessages() {
        Connection con = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();
        try {
            String sql = "SELECT * FROM message;";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                messages.add(new Message(
                    rs.getInt("message_id"),
                    rs.getInt("posted_by"),
                    rs.getString("message_text"),
                    rs.getLong("time_posted_epoch")
                ));
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return messages;
    }

    
    // Retrieve a message by id
    public Message getMessageById(int id) {
        Connection con = ConnectionUtil.getConnection();
        

        try{
            String sql="SELECT * FROM message WHERE message_id = ?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            
            if(rs.next()) {
                Message message = new Message(
                    rs.getInt("message_id"),
                    rs.getInt("posted_by"),
                    rs.getString("message_text"),
                    rs.getLong("time_posted_epoch")
                );
                return message;
            } 
            
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    // Delete a message by id
    public Message deleteMessageById(int id) {
        Connection con = ConnectionUtil.getConnection();

        Message messageToBeDeleted = getMessageById(id);

        try {
            String sql = "DELETE FROM message WHERE message_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return messageToBeDeleted;
    }

    // Update a message text by id
    public Message updateMessageById(int id, Message message) {
        Connection con = ConnectionUtil.getConnection();

        try {
            String sql = "UPDATE message SET message_text = ? WHERE message_id = ?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, message.getMessage_text());
            ps.setInt(2, id);

            ps.executeUpdate();
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return getMessageById(id);
    }


    // Retrieve all messages written by a particular user
   public List<Message> getAllMessagesFromAccount(int accountId) {
    List<Message> messages = new ArrayList<>();
    Connection con = ConnectionUtil.getConnection();

    try{
        String sql = "SELECT * FROM message WHERE posted_by = ?;";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, accountId);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
         messages.add(new Message(
                    rs.getInt("message_id"),
                    rs.getInt("posted_by"),
                    rs.getString("message_text"),
                    rs.getLong("time_posted_epoch")
                ));   
        }
    } catch(SQLException e) {
        System.out.println(e.getMessage());
    }
    return messages;
   }

}
