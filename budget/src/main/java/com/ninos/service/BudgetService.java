package com.ninos.service;

import com.ninos.domain.Budget;
import com.ninos.domain.User;
import com.ninos.repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Service
public class BudgetService {

   @Autowired
   private BudgetRepository budgetRepo;

   public TreeSet<Budget> getBudgets(@AuthenticationPrincipal User user){
       Set<User> users = new HashSet<>();
       users.add(user);
       return budgetRepo.findByUsersIn(users);
   }

   public Budget saveBudget(User user, Budget budget){
       Set<User> users = new HashSet<>();
       Set<Budget> budgets = new HashSet<>();

       users.add(user);

       budgets.add(budget);

       long count = getBudgetCount(users);

       budget.setName("New Budget #" + ++count);
       budget.setUsers(users);

       user.setBudgets(budgets);

       return budgetRepo.save(budget);
   }

    private long getBudgetCount(Set<User> users) {
       return budgetRepo.countByUsersIn(users);
    }


}
