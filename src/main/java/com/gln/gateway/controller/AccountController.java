package com.gln.gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gln.gateway.dto.LoginDto;
import com.gln.gateway.dto.LoginVo;
import com.gln.gateway.model.Account;
import com.gln.gateway.service.AccountService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(value = "*")
@RestController
@RequestMapping(value = "/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        log.info("[LOGIN] Login attempt: username={}", loginDto.getUsername());
        LoginVo result = accountService.loginAccount(loginDto.getUsername(), loginDto.getPassword());
        if (result.getAccount() != null) {
            log.info("[LOGIN] Login success: username={}", loginDto.getUsername());
        } else {
            log.warn("[LOGIN] Login failed: username={}", loginDto.getUsername());
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Account account) {
        log.info("[REGISTER] Register attempt: username={}", account.getUsername());
        accountService.save(account.getUsername(), account.getPassword());
        log.info("[REGISTER] Register success: username={}", account.getUsername());
        return ResponseEntity.ok().build();
    }
}
