package Service;

import DAO.MessagesDAO;
import Model.Message;
import java.util.ArrayList;
import java.util.List;
public class MessageService {
    
    private MessagesDAO messagesDAO;
    
    // no args constructor
    public MessageService() {
        this.messagesDAO = new MessagesDAO();
    }

    // constructor for testing
    public MessageService(MessagesDAO messagesDAO) {
        this.messagesDAO = messagesDAO;
    }

    // Create a new message
    public Message createNewMessage(Message message) {
        /**
         * Message text is not blank
         * Message text under 255 characters long
         * Posted by a real existing user
         */
        if (message.getMessage_text() != ""
            && message.getMessage_text().length() < 255)
            {
                return messagesDAO.createMessage(message);
            }
       return null;
    }

    // Get all messages
    public List<Message> getAllMessages() {
        return messagesDAO.getAllMessages();
    }

    // Get message by id
    public Message getMessageById(int id) {
        return messagesDAO.getMessageById(id);
    }

    // Delete message by id
    public Message deleteMessageById(int id) {
        return messagesDAO.deleteMessageById(id);
    }

     // Update message by id
     public Message updateMessageById(int id, Message message) {
        /*
         * The update of a message should be successful if and only if the message id already exists 
         * and the new message_text is not blank 
         * and is not over 255 characters. If the update is successful, the response body should contain the full updated message (including message_id, posted_by, message_text, and time_posted_epoch), and the response status should be 200, which is the default. The message existing on the database should have the updated message_text.
         */
        if (messagesDAO.getMessageById(id) != null 
            && message.getMessage_text() != ""
            && message.getMessage_text().length() < 255) {
                return messagesDAO.updateMessageById(id, message);
            }
        return null;
     }

    // Get All messages from account
    public List<Message> getAllMessagesFromAccount(int account_id) {
        return messagesDAO.getAllMessagesFromAccount(account_id);
    }
}
