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
        /**
         * If the username is not blank
         * The password length is at least 4 characters long
         * An account with the same username does not exist
         */
         if ( (account.getUsername()!= "") 
         && account.getUsername()!= null
         && (account.getPassword().length() >= 4)
         && (accountDAO.doesUserNameExist(account.getUsername()) == false) ){
             return accountDAO.createNewAccount(account);
         }
        return null; 
    }

    // User Log In 
    public Account logIn(Account account) {
        // if (accountDAO.logIn(account.getUsername(), account.getPassword()) != null) {
        //     return true;
        // } else {
        //     return false;
        // }
        return accountDAO.logIn(account.getUsername(), account.getPassword());
    }
}
