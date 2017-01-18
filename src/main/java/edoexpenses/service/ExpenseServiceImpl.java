package edoexpenses.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edoexpenses.model.Expense;
import edoexpenses.repository.ExpenseRepository;

@Service
@Transactional
public class ExpenseServiceImpl implements ExpenseService {
	
	@Autowired
	private ExpenseRepository expenseRepository;

	@Override
	public List<Expense> findAll() {
		return expenseRepository.findAll();
	}

	@Override
	public Expense findById(Integer id) {
		return expenseRepository.findOne(id);
	}

	@Override
	public Expense createExpense(Expense expense) {
	    expense.setId(null);
	    return expenseRepository.saveAndFlush(expense);
	}

	@Override
	public Expense updateExpense(Expense expense, Integer id) {
		expense.setId(id);
	    return expenseRepository.saveAndFlush(expense);
	}

	@Override
	public void deleteExpenseById(int id) {
		expenseRepository.delete(id);
	}

}
