package edoexpenses.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edoexpenses.model.Expense;
import edoexpenses.service.ExpenseService;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {
	
  @Autowired
  private ExpenseService expenseService;
  
  @RequestMapping(method = RequestMethod.GET)
  public List<Expense> findExpenses() {
    return expenseService.findAll();
  }
  
  @RequestMapping(method = RequestMethod.POST)
  public Expense addExpense(@RequestBody Expense expense) {
	  return expenseService.createExpense(expense);
  }
  
  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  public Expense updateExpense(@RequestBody Expense expense, @PathVariable Integer id) {
	  return expenseService.updateExpense(expense, id);
  }
  
  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public void deleteExpense(@PathVariable Integer id) {
	  expenseService.deleteExpenseById(id);
  }
}
