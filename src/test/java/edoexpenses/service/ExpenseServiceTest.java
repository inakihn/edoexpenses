package edoexpenses.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edoexpenses.Application;
import edoexpenses.model.Expense;
import edoexpenses.repository.ExpenseRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class ExpenseServiceTest {

	@Autowired
	@InjectMocks
	ExpenseService expenseService;

	@MockBean
	ExpenseRepository mockExpenseRepository;
	
	private Expense testExpense1 = null;
	private Expense testExpense2 = null;
	
	@Before
	public void setUp() throws ParseException {
		testExpense1 = new Expense();
		testExpense1.setDescription("Food");
		testExpense1.setValue("10.59");
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		testExpense1.setDate(formatter.parse("15/01/2017"));
		testExpense1.setId(1);
		
		testExpense2 = new Expense();
		testExpense2.setDescription("Clothing");
		testExpense2.setValue("49.59");
		testExpense2.setDate(formatter.parse("16/01/2017"));
		testExpense2.setId(2);
		
	}

	@Test
	public void findAll() {
		Mockito.when(mockExpenseRepository.findAll()).thenReturn(new ArrayList<Expense> ());
		assertEquals(0, expenseService.findAll().size());
	}

	@Test
	public void findById() {
		Mockito.when(mockExpenseRepository.findOne(any(Integer.class))).thenReturn(testExpense1);
		assertEquals("Food", expenseService.findById(1).getDescription());
	}

	@Test
	public void createExpense() throws ParseException {
		Mockito.when(mockExpenseRepository.saveAndFlush(any(Expense.class))).thenReturn(testExpense1);
		
		Expense resultExpense = new Expense();
		resultExpense.setId(1);

		Expense requestExpense = new Expense();
		requestExpense.setDescription("Videogames");
		requestExpense.setValue("59.99");
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		requestExpense.setDate(formatter.parse("19/01/2017"));

		assertEquals(resultExpense.getId(), expenseService.createExpense(requestExpense).getId());
		Mockito.verify(mockExpenseRepository, Mockito.times(1)).saveAndFlush(any(Expense.class));
	}

	@Test
	public void updateExpense() throws ParseException {
		Mockito.when(mockExpenseRepository.saveAndFlush(any(Expense.class))).thenReturn(testExpense1);
		
		Expense requestExpense = new Expense();
		requestExpense.setDescription("Videogames");
		requestExpense.setValue("59.99");
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		requestExpense.setDate(formatter.parse("19/01/2017"));
		
		assertEquals("Food", expenseService.updateExpense(requestExpense, 1).getDescription());
	}

	@Test
	public void deleteExpenseById() {
		Mockito.doNothing().when(mockExpenseRepository).delete(any(Integer.class));
//		Mockito.verify(mockExpenseRepository, Mockito.times(1)).delete(any(Integer.class));
		expenseService.deleteExpenseById(2);
		assertNull(expenseService.findById(2));
	}

	@Test
	public void deleteAllExpenses() {
		Mockito.doNothing().when(mockExpenseRepository).deleteAll();
//		Mockito.verify(mockExpenseRepository, Mockito.times(1)).deleteAll();
		expenseService.deleteAllExpenses();
	}

}
