package com.sergeev.finance.controller;

import com.sergeev.finance.domain.Category;
import com.sergeev.finance.domain.CategoryType;
import com.sergeev.finance.domain.Transaction;
import com.sergeev.finance.domain.User;
import com.sergeev.finance.repos.CategoryRepo;
import com.sergeev.finance.repos.TransactionRepo;
import com.sergeev.finance.service.TransactionService;
import com.sergeev.finance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class MainController {
    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private TransactionRepo transactionRepo;

    @Autowired
    TransactionService transactionService;

    @Autowired
    UserService userService;

    Iterable<Category> categories;
    Iterable<Transaction> transactions;
    Iterable<CategoryType> categoryTypes;

    @GetMapping("/")
    public String greeting(Model model) {
        return "greeting";
    }


    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filterType,
                       @RequestParam(required = false, defaultValue = "") String filterCategory,
                       @RequestParam(required = false, defaultValue = "") Double filterMinAmount,
                       @RequestParam(required = false, defaultValue = "") Double filterMaxAmount,
                       @RequestParam(required = false, defaultValue = "") String minDate,
                       @RequestParam(required = false, defaultValue = "") String maxDate,
                       @AuthenticationPrincipal User user, Model model) {
        categories = categoryRepo.findAll();

        Timestamp ts = new Timestamp(System.currentTimeMillis());
        Timestamp ts1 = new Timestamp(System.currentTimeMillis());

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
            Date parsedDate = dateFormat.parse(minDate.replace('T', ' '));
            ts = new java.sql.Timestamp(parsedDate.getTime());
            parsedDate = dateFormat.parse(maxDate.replace('T', ' '));
            ts1 = new java.sql.Timestamp(parsedDate.getTime());
        } catch(Exception e) {
            System.out.println("Ooops");
        }

        //6 filters available
        if (filterType != null && !filterType.isEmpty()&&
                filterCategory != null && !filterCategory.isEmpty()&&
                filterMinAmount != null && filterMaxAmount != null &&
                minDate != null && !minDate.isEmpty()
                && maxDate != null && !maxDate.isEmpty()) {
            transactions = transactionRepo.findAllByUserIdAndCategoryTypeAndCategoryNameCategoryAndAmountBetweenAndTimestampBetween
                    (user.getId(), filterType, filterCategory, filterMinAmount, filterMaxAmount, ts, ts1);

            //5 filters available
        }else if(filterCategory != null && !filterCategory.isEmpty()&&
                filterMinAmount != null && filterMaxAmount != null &&
                minDate != null && !minDate.isEmpty()
                && maxDate != null && !maxDate.isEmpty()){
            transactions = transactionRepo.findAllByUserIdAndCategoryNameCategoryAndAmountBetweenAndTimestampBetween
                    (user.getId(), filterCategory, filterMinAmount, filterMaxAmount, ts, ts1);

        }else if(filterType != null && !filterType.isEmpty()&&
                filterMinAmount != null && filterMaxAmount != null &&
                minDate != null && !minDate.isEmpty()
                && maxDate != null && !maxDate.isEmpty()){
            transactions = transactionRepo.findAllByUserIdAndCategoryTypeAndAmountBetweenAndTimestampBetween
                    (user.getId(), filterType, filterMinAmount, filterMaxAmount, ts, ts1);

        }else if(filterType != null && !filterType.isEmpty()&&
                filterCategory != null && !filterCategory.isEmpty()&&
                filterMaxAmount != null &&
                minDate != null && !minDate.isEmpty()
                && maxDate != null && !maxDate.isEmpty()){
            transactions = transactionRepo.findAllByUserIdAndCategoryTypeAndCategoryNameCategoryAndAmountBeforeAndTimestampBetween
                    (user.getId(), filterType, filterCategory, filterMaxAmount, ts, ts1);

        }else if(filterType != null && !filterType.isEmpty()&&
                filterCategory != null && !filterCategory.isEmpty()&&
                filterMinAmount != null &&
                minDate != null && !minDate.isEmpty()
                && maxDate != null && !maxDate.isEmpty()){
            transactions = transactionRepo.findAllByUserIdAndCategoryTypeAndCategoryNameCategoryAndAmountAfterAndTimestampBetween
                    (user.getId(), filterType, filterCategory, filterMinAmount, ts, ts1);

        }else if(filterType != null && !filterType.isEmpty()&&
                filterCategory != null && !filterCategory.isEmpty()&&
                filterMinAmount != null && filterMaxAmount != null &&
                maxDate != null && !maxDate.isEmpty()){
            transactions = transactionRepo.findAllByUserIdAndCategoryTypeAndCategoryNameCategoryAndAmountBetweenAndTimestampBefore
                    (user.getId(), filterType, filterCategory, filterMinAmount, filterMaxAmount, ts1);

        }else if(filterType != null && !filterType.isEmpty()&&
                filterCategory != null && !filterCategory.isEmpty()&&
                filterMinAmount != null && filterMaxAmount != null &&
                minDate != null && !minDate.isEmpty()){
            transactions = transactionRepo.findAllByUserIdAndCategoryTypeAndCategoryNameCategoryAndAmountBetweenAndTimestampAfter
                    (user.getId(), filterType, filterCategory, filterMinAmount, filterMaxAmount, ts);

            //4 filters available, exclude 1-x
        }else if(filterMinAmount != null && filterMaxAmount != null &&
                minDate != null && !minDate.isEmpty()
                && maxDate != null && !maxDate.isEmpty()){
            transactions = transactionRepo.findAllByUserIdAndAmountBetweenAndTimestampBetween
                    (user.getId(), filterMinAmount, filterMaxAmount, ts, ts1);

        }else if(filterCategory != null && !filterCategory.isEmpty()&&
                filterMaxAmount != null &&
                minDate != null && !minDate.isEmpty()
                && maxDate != null && !maxDate.isEmpty()){
            transactions = transactionRepo.findAllByUserIdAndCategoryNameCategoryAndAmountBeforeAndTimestampBetween
                    (user.getId(), filterCategory, filterMaxAmount, ts, ts1);

        }else if(filterCategory != null && !filterCategory.isEmpty()&&
                filterMinAmount != null &&
                minDate != null && !minDate.isEmpty()
                && maxDate != null && !maxDate.isEmpty()){
            transactions = transactionRepo.findAllByUserIdAndCategoryNameCategoryAndAmountAfterAndTimestampBetween
                    (user.getId(), filterCategory, filterMinAmount, ts, ts1);

        }else if(filterCategory != null && !filterCategory.isEmpty()&&
                filterMinAmount != null && filterMaxAmount != null &&
                maxDate != null && !maxDate.isEmpty()){
            transactions = transactionRepo.findAllByUserIdAndCategoryNameCategoryAndAmountBetweenAndTimestampBefore
                    (user.getId(), filterCategory, filterMinAmount, filterMaxAmount, ts1);

        }else if(filterCategory != null && !filterCategory.isEmpty()&&
                filterMinAmount != null && filterMaxAmount != null &&
                minDate != null && !minDate.isEmpty()){
            transactions = transactionRepo.findAllByUserIdAndCategoryNameCategoryAndAmountBetweenAndTimestampAfter
                    (user.getId(), filterCategory, filterMinAmount, filterMaxAmount, ts);

            //4 filters available, exclude 2-x
        }else if(filterType != null && !filterType.isEmpty()&&
                filterMaxAmount != null &&
                minDate != null && !minDate.isEmpty()
                && maxDate != null && !maxDate.isEmpty()){
            transactions = transactionRepo.findAllByUserIdAndCategoryTypeAndAmountBeforeAndTimestampBetween
                    (user.getId(), filterType, filterMaxAmount, ts, ts1);

        }else if(filterType != null && !filterType.isEmpty()&&
                filterMinAmount != null &&
                minDate != null && !minDate.isEmpty()
                && maxDate != null && !maxDate.isEmpty()){
            transactions = transactionRepo.findAllByUserIdAndCategoryTypeAndAmountAfterAndTimestampBetween
                    (user.getId(), filterType, filterMinAmount, ts, ts1);

        }else if(filterType != null && !filterType.isEmpty()&&
                filterMinAmount != null && filterMaxAmount != null &&
                maxDate != null && !maxDate.isEmpty()){
            transactions = transactionRepo.findAllByUserIdAndCategoryTypeAndAmountBetweenAndTimestampBefore
                    (user.getId(), filterType, filterMinAmount, filterMaxAmount, ts1);

        }else if(filterType != null && !filterType.isEmpty()&&
                filterMinAmount != null && filterMaxAmount != null &&
                minDate != null && !minDate.isEmpty()){
            transactions = transactionRepo.findAllByUserIdAndCategoryTypeAndAmountBetweenAndTimestampAfter
                    (user.getId(), filterType, filterMinAmount, filterMaxAmount, ts);

            //4 filters available, exclude 3-x
        }else if(filterType != null && !filterType.isEmpty()&&
                filterCategory != null && !filterCategory.isEmpty()&&
                minDate != null && !minDate.isEmpty()
                && maxDate != null && !maxDate.isEmpty()){
            transactions = transactionRepo.findAllByUserIdAndCategoryTypeAndCategoryNameCategoryAndTimestampBetween
                    (user.getId(), filterType, filterCategory, ts, ts1);

        }else if(filterType != null && !filterType.isEmpty()&&
                filterCategory != null && !filterCategory.isEmpty()&&
                filterMaxAmount != null &&
                maxDate != null && !maxDate.isEmpty()){
            transactions = transactionRepo.findAllByUserIdAndCategoryTypeAndCategoryNameCategoryAndAmountBeforeAndTimestampBefore
                    (user.getId(), filterType, filterCategory, filterMaxAmount, ts1);

        }else if(filterType != null && !filterType.isEmpty()&&
                filterCategory != null && !filterCategory.isEmpty()&&
                filterMaxAmount != null &&
                minDate != null && !minDate.isEmpty()){
            transactions = transactionRepo.findAllByUserIdAndCategoryTypeAndCategoryNameCategoryAndAmountBeforeAndTimestampAfter
                    (user.getId(), filterType, filterCategory, filterMaxAmount, ts);

            //4 filters available, exclude 4-x
        }else if(filterType != null && !filterType.isEmpty()&&
                filterCategory != null && !filterCategory.isEmpty()&&
                filterMinAmount != null &&
                maxDate != null && !maxDate.isEmpty()){
            transactions = transactionRepo.findAllByUserIdAndCategoryTypeAndCategoryNameCategoryAndAmountAfterAndTimestampBefore
                    (user.getId(), filterType, filterCategory, filterMinAmount, ts1);

        }else if(filterType != null && !filterType.isEmpty()&&
                filterCategory != null && !filterCategory.isEmpty()&&
                filterMinAmount != null &&
                minDate != null && !minDate.isEmpty()){
            transactions = transactionRepo.findAllByUserIdAndCategoryTypeAndCategoryNameCategoryAndAmountAfterAndTimestampAfter
                    (user.getId(), filterType, filterCategory, filterMinAmount, ts);

        }


        else {
            transactions = transactionRepo.findAllByUserId(user.getId());
        }

        categoryTypes = Arrays.asList(CategoryType.values());

        model.addAttribute("transactions", transactions);
        //model.addAttribute("filter", filter);
        model.addAttribute("categories", categories);
        model.addAttribute("user", user);
        model.addAttribute("categoryTypes", categoryTypes);

        return "main";
    }

    @PostMapping("addCategory")
    public String addCategory(@RequestParam String nameCategory,
                              @RequestParam String categoryType,
                              @RequestParam int priority,
                              @AuthenticationPrincipal User user, Model model) {

        Category category = new Category(nameCategory, categoryType, priority, user);

        categoryRepo.save(category);

        categories = categoryRepo.findAll();
        transactions = transactionRepo.findAllByUserId(user.getId());

        model.addAttribute("categories", categories);
        model.addAttribute("user", user);
        model.addAttribute("transactions", transactions);

        return "redirect:/main";
    }

    @PostMapping("addTransaction")
    public String addTransaction(@RequestParam String nameCategory,
                                 @RequestParam double amount,
                                 @AuthenticationPrincipal User user, Model model) {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        Transaction transaction = new Transaction(user,
                categoryRepo.findByNameCategory(nameCategory).get(0), amount, timestamp);

        transactionRepo.save(transaction);

        transactions = transactionRepo.findAllByUserId(user.getId());
        categories = categoryRepo.findAll();

        userService.updateBalance(user);

        model.addAttribute("transactions", transactions);
        model.addAttribute("user", user);
        model.addAttribute("categories", categories);

        return "redirect:/main";

    }

    @PostMapping("deleteTransaction")
    public String deleteTransaction(@RequestParam String id,
                                 @AuthenticationPrincipal User user,
                                 Model model){

        Long idTrans = Long.parseLong(id);
        transactionRepo.deleteById(idTrans);

        transactions = transactionRepo.findAllByUserId(user.getId());
        userService.updateBalance(user);

        model.addAttribute("transactions", transactions);

        return "redirect:/main";
    }

    @PostMapping("editTransaction")
    public String editTransaction(@RequestParam String id,
                                  @RequestParam String nameCategory,
                                  @RequestParam Double amount,
                                  @AuthenticationPrincipal User user, Model model){

        Long idTrans = Long.parseLong(id);

        Transaction transaction = transactionService.findTransactionById(idTrans);
        transaction.setAmount(amount);
        transaction.setCategory(categoryRepo.findByNameCategory(nameCategory).get(0));

        transactionRepo.save(transaction);

        transactions = transactionRepo.findAllByUserId(user.getId());
        userService.updateBalance(user);

        model.addAttribute("transactions", transactions);
        return "redirect:/main";
    }

//    @PostMapping("showCategories")
//    public String showCategories(Model model){
//        categories = categoryRepo.findAllByNameCategory("Покупки");
//
//        model.addAttribute("categories", categories);
//
//        return "main";
//    }



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
