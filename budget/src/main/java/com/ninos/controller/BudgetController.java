package com.ninos.controller;

import com.ninos.domain.Budget;
import com.ninos.domain.User;
import com.ninos.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Controller
@RequestMapping("/budgets")
public class BudgetController {

    @Autowired
    private BudgetService budgetService;


    private void getBudgets(User user, ModelMap model) {
        TreeSet<Budget> budgets = budgetService.getBudgets(user);
        model.put("budgets", budgets);
    }


    @RequestMapping(value="", method= RequestMethod.GET)
    public String getBudget(@AuthenticationPrincipal User user, ModelMap model)
    {
        // this should fetch budgets from the database for the logged in user
        getBudgets(user, model);
        return "budgets";
    }




    @RequestMapping(value="", method= RequestMethod.POST)
    public @ResponseBody Budget postBudget(@AuthenticationPrincipal User user, ModelMap model)
    {
        Budget budget = new Budget();
        budget = budgetService.saveBudget(user,budget);

        getBudgets(user, model);

        return budget;
    }





}
