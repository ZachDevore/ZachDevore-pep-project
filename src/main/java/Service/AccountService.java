package Service;

import java.util.ArrayList;

import DAO.AccountDAO;
import Model.Account;

public class AccountService {
    private AccountDAO accountDAO;

    public AccountService() {
        accountDAO = new AccountDAO();
    }

    // Constructor for testing
    public AccountService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    // Create a new Account
    public Account addNewAccount(Account account) {
        // Account existingAccount = accountDAO.doesUserNameExist(account.get)
        // ArrayList<String> userNames = accountDAO.getAllUserNames();
        // boolean doesUserNameExist = accountDAO.doesUserNameExist(account.getUsername());

        
        /**
         * If the username is not blank
         * The username length is atleast 4 characters long
         * An account with the same username does not exist
         */
        //  if ( (account.getUsername()!= null) 
        //  && (account.getUsername().length() >= 4)
        //  &&   ) {
        //      return accountDAO.createNewAccount(account);
        //  }
        // return null;


        return accountDAO.createNewAccount(account);
    }

    // User Log In 
    public Account logIn(Account account) {
        return accountDAO.logIn(account.getUsername(), account.getPassword());
    }
}
