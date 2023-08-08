package com.example.bankhw.Models;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountModel {
    private String id;
    private String username;
    private int balance;
}
