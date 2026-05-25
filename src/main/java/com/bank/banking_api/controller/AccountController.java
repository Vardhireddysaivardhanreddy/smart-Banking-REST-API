package com.bank.banking_api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.bank.banking_api.entity.Account;
import com.bank.banking_api.repository.AccountRepository;

import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @PostMapping
    public Account createAccount(@RequestBody Account account) {
        return accountRepository.save(account);
    }

    @GetMapping
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }
    
    @PutMapping("/{id}")
    public Account updateAccount(@PathVariable Long id,
                                 @RequestBody Account updatedAccount) {

        Account existingAccount =
                accountRepository.findById(id).orElse(null);

        if (existingAccount != null) {

            existingAccount.setName(updatedAccount.getName());
            existingAccount.setBalance(updatedAccount.getBalance());

            return accountRepository.save(existingAccount);
        }

        return null;
    }
    @DeleteMapping("/{id}")
    public String deleteAccount(@PathVariable Long id) {

        accountRepository.deleteById(id);

        return "Account Deleted Successfully";
    }

}