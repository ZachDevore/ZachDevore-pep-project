package Controller;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;
import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    AccountService accountService;
    MessageService messageService;

    public SocialMediaController() {
        this.accountService = new AccountService();
        this.messageService = new MessageService();
    }


    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.post("/register", this::postCreateAccountHandler);
        app.post("/login", this::postLoginHandler);
        app.post("/messages", this::postCreateMessageHandler);
        app.get("/messages", this::getAllMessagesHandler);
        app.get("/messages/{message_id}", this::getMessageByIdHandler);
        app.delete("/messages/{message_id}", this::deleteMessageById);

    
        return app;
    }

    
    
    //Create an Account
    public void postCreateAccountHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(), Account.class);
        Account addedAccount = accountService.addNewAccount(account);
        if (addedAccount != null) {
             ctx.json(mapper.writeValueAsString(addedAccount));
            ctx.status(200);
        } else {
            ctx.status(400);
        }
    }

    // Login 
    /**
     * @param ctx
     * @throws JsonProcessingException
     */
    public void postLoginHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(), Account.class);

        
        
        Account login = accountService.logIn(account);
        
        if (login != null) {
            ctx.json(mapper.writeValueAsString(login));
            ctx.status(200);
        } else {
            ctx.status(401);
        }

        
    }

    // Create a Message
    public void postCreateMessageHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(ctx.body(), Message.class);

        Message addedMessage = messageService.createNewMessage(message);

        if (addedMessage != null) {
            ctx.json(mapper.writeValueAsString(addedMessage));
            ctx.status(200);
        } else {
            ctx.status(400);
        }

        
    }

    // Retrieve all messages
    public void getAllMessagesHandler(Context ctx) throws JsonProcessingException {
        List<Message> messages = messageService.getAllMessages();
        ctx.json(messages);
        ctx.status(200);
    }

    // Retrieve a message by id
    public void getMessageByIdHandler(Context ctx) throws JsonProcessingException {
        int message_id = Integer.parseInt(ctx.pathParam("message_id"));

        Message targetMessage = messageService.getMessageById(message_id);
        
        if (targetMessage != null) {
            ctx.json(targetMessage);
            ctx.status(200);
        } else {
            ctx.status(200);
        }

    }


    // Delete message by id
    public void deleteMessageById(Context ctx) throws JsonProcessingException {
        int message_id = Integer.parseInt(ctx.pathParam("message_id"));

        Message targetMessage = messageService.deleteMessageById(message_id);

         if (targetMessage != null) {
            ctx.json(targetMessage);
            ctx.status(200);
        } else {
            ctx.status(200);
        }
    }

   

    

}
