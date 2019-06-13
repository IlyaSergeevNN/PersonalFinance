package com.sergeev.finance.controller;

import com.sergeev.finance.domain.Category;
import com.sergeev.finance.domain.Message;
import com.sergeev.finance.domain.User;
import com.sergeev.finance.repos.CategoryRepo;
import com.sergeev.finance.repos.MessageRepo;
import com.sergeev.finance.repos.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private TransactionRepo transactionRepo;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        Iterable<Message> messages = messageRepo.findAll();
        Iterable<Category> categories = categoryRepo.findAll();

        model.put("messages", messages);
        model.put("categories", categories);

        return "main";
    }

    @PostMapping("/main")
    public String add(@RequestParam String text, @RequestParam String tag, Map<String, Object> model) {
        Message message = new Message(text, tag);

        messageRepo.save(message);

        Iterable<Message> messages = messageRepo.findAll();

        model.put("messages", messages);

        return "main";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        Iterable<Message> messages;

        if (filter != null && !filter.isEmpty()) {
            messages = messageRepo.findByTag(filter);
        } else {
            messages = messageRepo.findAll();
        }

        model.put("messages", messages);

        return "main";
    }

    @PostMapping("addCategory")
    public String addCategory(@RequestParam String nameCategory,
                              @RequestParam String type,
                              @RequestParam int priority,
                              @AuthenticationPrincipal User user, Map<String, Object> model) {

        Category category = new Category(nameCategory, type, priority, user);

        categoryRepo.save(category);

        Iterable<Category> categories = categoryRepo.findAll();

        model.put("categories", categories);

        return "main";
    }

    @PostMapping("showCategories")
    public String showCategories(Map<String, Object> model){
        Iterable<Category> categories = categoryRepo.findAllByNameCategory("Покупки");

        model.put("categories", categories);

        return "main";
    }

    @PostMapping("addTransaction")
    public String addTransaction(@RequestParam String nameCategory,
                              @RequestParam String type,
                              @RequestParam int priority, Map<String, Object> model) {

        Category category = new Category(nameCategory, type, priority);

        categoryRepo.save(category);

        Iterable<Category> categories = categoryRepo.findAll();

        model.put("categories", categories);

        return "main";
    }

}
