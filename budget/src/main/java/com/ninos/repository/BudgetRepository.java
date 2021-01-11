package com.ninos.repository;

import java.util.Set;
import java.util.TreeSet;

import com.ninos.domain.Budget;
import com.ninos.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BudgetRepository extends JpaRepository<Budget, Long>
{

  TreeSet<Budget> findByUsersIn(Set<User> users);

  long countByUsersIn(Set<User> users);

}
