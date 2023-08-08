package com.example.bankhw.Controllers;

import com.example.bankhw.ApiResonse.ApiResponse;
import com.example.bankhw.Models.AccountModel;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/bank")
public class BankController {

    ArrayList<AccountModel> customers = new ArrayList<>();

    int id = 1;

    @GetMapping("/get")
    public ArrayList<AccountModel> getCustomers(){
        return customers;
    }

    @PostMapping("add")
    public ApiResponse addCustomer(@RequestBody @NotNull  AccountModel newAcc){
        newAcc.setId("" + id);
        customers.add(newAcc);
        id++;
        return new ApiResponse("cutromer added",201);
    }

    @PutMapping("/update/{id}")
    public ApiResponse updateCustomer(@PathVariable String id, @RequestBody AccountModel newAcc){

        for (AccountModel acc : customers) {
            if (acc.getId().equalsIgnoreCase(id)){
                acc.setUsername(newAcc.getUsername());
                acc.setBalance(newAcc.getBalance());

                return new ApiResponse("customer updated!",201);
            }
        }

        return new ApiResponse("sorry! there's no account with this ID " + id ,400);
    }


    @DeleteMapping("/delete/{id}")
    public ApiResponse deleteCustomer(@PathVariable String id){

        for(AccountModel elm : customers){
            if (elm.getId().equalsIgnoreCase(id)){
                customers.remove(elm);
                return new ApiResponse("customer deleted!",200);
            }
        }
        return new ApiResponse("sorry! there's no account with this ID " + id ,400);
    }

    @PutMapping("/deposit/{id}/{amount}")
    public ApiResponse depositMoney(@PathVariable String id,@PathVariable int amount){

        for(AccountModel elm : customers){
            if (elm.getId().equalsIgnoreCase(id)) {
                elm.setBalance(elm.getBalance() + amount);
                return new ApiResponse("deposit went successfully, your new balance is : " + elm.getBalance(),201);
            }
        }

        return new ApiResponse("sorry! there's no account with this ID " + id ,400);
    }


    @PutMapping("/withdraw/{id}/{amount}")
    public ApiResponse withdwarMoney(@PathVariable String id,@PathVariable int amount){

        for(AccountModel elm : customers){
            if (elm.getId().equalsIgnoreCase(id)){
                if (elm.getBalance() < amount){
                    return new ApiResponse("your balance less then the amount you went to withdraw" ,400);
                }else {
                    elm.setBalance(elm.getBalance() - amount);
                    return new ApiResponse("withdraw went successfully, you new balance is : " + elm.getBalance() ,200);

                }

            }
        }

        return new ApiResponse("sorry! there's no account with this ID " + id ,400);
    }






}
