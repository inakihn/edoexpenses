package edoexpenses;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import edoexpenses.model.Expense;
import edoexpenses.repository.ExpenseRepository;

@Component
public class Dataloader implements ApplicationRunner {

    private ExpenseRepository expenseRepository;

    @Autowired
    public Dataloader(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Expense testExpense1 = new Expense();
		testExpense1.setDescription("Food");
		testExpense1.setValue("10.59");
//		testExpense1.setDate("15/01/2017");
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		testExpense1.setDate(formatter.parse("15/01/2017"));
		
		Expense testExpense2 = new Expense();
		testExpense2.setDescription("Clothing");
		testExpense2.setValue("49.59");
//		testExpense2.setDate("14/01/2017");
		testExpense2.setDate(formatter.parse("16/01/2017"));
		
		expenseRepository.save(testExpense1);
		expenseRepository.save(testExpense2);
	}
}