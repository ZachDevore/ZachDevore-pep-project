package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

    
    // Retrieve a message by id


    // Delete a message by id


    // Update a message text by id


    // Retrieve all messages written by a particular user
   
}
