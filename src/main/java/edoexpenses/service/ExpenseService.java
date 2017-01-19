package edoexpenses.service;

import java.util.List;

import edoexpenses.model.Expense;

public interface ExpenseService {

	List<Expense> findAll();

	Expense findById(Integer id);

	Expense createExpense(Expense expense);

	Expense updateExpense(Expense expense, Integer id);

	void deleteExpenseById(Integer id);

	void deleteAllExpenses();


}
