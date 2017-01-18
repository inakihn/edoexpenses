package edoexpenses.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edoexpenses.model.Expense;
import edoexpenses.repository.ExpenseRepository;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {
	
  @Autowired
  private ExpenseRepository expenseRepository;
  
  @RequestMapping(method = RequestMethod.GET)
  public List<Expense> findExpenses() {
    return expenseRepository.findAll();
  }
  
  @RequestMapping(method = RequestMethod.POST)
  public Expense addExpense(@RequestBody Expense item) {
    item.setId(null);
    return expenseRepository.saveAndFlush(item);
  }
  
  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  public Expense updateExpense(@RequestBody Expense updatedExpense, @PathVariable Integer id) {
    updatedExpense.setId(id);
    return expenseRepository.saveAndFlush(updatedExpense);
  }
  
  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public void deleteExpense(@PathVariable Integer id) {
    expenseRepository.delete(id);
  }
}
