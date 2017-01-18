package edoexpenses.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edoexpenses.model.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Integer> {

}
	