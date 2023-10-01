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
        return messagesDAO.updateMessageById(id, message);
     }

    // Get All messages from account
    public List<Message> getAllMessagesFromAccount(int account_id) {
        return messagesDAO.getAllMessagesFromAccount(account_id);
    }
}
