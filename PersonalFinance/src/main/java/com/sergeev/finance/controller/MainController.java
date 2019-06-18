package com.sergeev.finance.controller;

import com.sergeev.finance.domain.Category;
import com.sergeev.finance.domain.Transaction;
import com.sergeev.finance.domain.User;
import com.sergeev.finance.repos.CategoryRepo;
import com.sergeev.finance.repos.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private TransactionRepo transactionRepo;

    @GetMapping("/")
    public String greeting(Model model) {
        return "greeting";
    }

    Iterable<Category> categories;

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter,
                       @AuthenticationPrincipal User user, Model model) {
        Iterable<Category> categories = categoryRepo.findAll();

        Iterable<Transaction> transactions = transactionRepo.findAllByUserId(user.getId());


//        if (filter != null && !filter.isEmpty()) {
//            messages = messageRepo.findByTag(filter);
//        } else {
//            messages = messageRepo.findAll();
//        }

        model.addAttribute("transactions", transactions);
        model.addAttribute("filter", filter);
        model.addAttribute("categories", categories);
        model.addAttribute("user", user);

        return "main";
    }

    @PostMapping("addCategory")
    public String addCategory(@RequestParam String nameCategory,
                              @RequestParam String categoryType,
                              @RequestParam int priority,
                              @AuthenticationPrincipal User user, Model model) {

        Category category = new Category(nameCategory, categoryType, priority, user);

        categoryRepo.save(category);

        Iterable<Category> categories = categoryRepo.findAll();
        Iterable<Transaction> transactions = transactionRepo.findAllByUserId(user.getId());

        model.addAttribute("categories", categories);
        model.addAttribute("user", user);
        model.addAttribute("transactions", transactions);

        return "main";
    }

    @PostMapping("showCategories")
    public String showCategories(Model model){
        Iterable<Category> categories = categoryRepo.findAllByNameCategory("Покупки");

        model.addAttribute("categories", categories);

        return "main";
    }

    @PostMapping("addTransaction")
    public String addTransaction(@RequestParam String nameCategory,
                                 @RequestParam double amount,
                                 @AuthenticationPrincipal User user, Model model) {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        Transaction transaction = new Transaction(user,
                categoryRepo.findByNameCategory(nameCategory).get(0), amount, timestamp);

        transactionRepo.save(transaction);

        Iterable<Transaction> transactions = transactionRepo.findAllByUserId(user.getId());
        Iterable<Category> categories = categoryRepo.findAll();

        model.addAttribute("transactions", transactions);
        model.addAttribute("user", user);
        model.addAttribute("categories", categories);

        return "main";
    }

//    @PostMapping("showTransactions")
//    public String showTransactions(@AuthenticationPrincipal User user, Model model){
//        Iterable<Transaction> transactions = transactionRepo.findAllByUserId(user.getId());
//
//        model.addAttribute("transactions", transactions);
//
//        return "main";
//    }

    //    @PostMapping("/main")
//    public String add(@RequestParam String text, @RequestParam String tag, Model model) {
//        Message message = new Message(text, tag);
//
//        messageRepo.save(message);
//
//        Iterable<Message> messages = messageRepo.findAll();
//
//        model.addAttribute("messages", messages);
//        model.addAttribute("filter", "");
//
//        return "main";
//    }

}
