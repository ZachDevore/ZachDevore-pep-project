package Service;

import DAO.MessagesDAO;
import Model.Message;
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
}
