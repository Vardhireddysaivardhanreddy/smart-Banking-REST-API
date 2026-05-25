package com.bank.banking_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.banking_api.entity.Account;
import com.bank.banking_api.repository.AccountRepository;

@Service
public class AccountService {

    @Autowired
    private AccountRepository repository;

    // CREATE
    public Account createAccount(Account account) {
        return repository.save(account);
    }

    // READ ALL
    public List<Account> getAllAccounts() {
        return repository.findAll();
    }

    // READ BY ID
    public Optional<Account> getAccountById(Long id) {
        return repository.findById(id);
    }

    // UPDATE
    public Account updateAccount(Long id, Account accountDetails) {

        Account account = repository.findById(id).orElse(null);

        if (account != null) {
            account.setName(accountDetails.getName());
            account.setBalance(accountDetails.getBalance());

            return repository.save(account);
        }

        return null;
    }

    // DELETE
    public String deleteAccount(Long id) {

        repository.deleteById(id);

        return "Account Deleted Successfully";
    }
}