package edoexpenses;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class ExpenseController {

	private ExpenseRepository expenseRepo;

	@Autowired
	public ExpenseController(ExpenseRepository expenseRepo) {
		this.expenseRepo = expenseRepo;
	}

	@RequestMapping(method=RequestMethod.GET)
	public String home(Map<String,Object> model) {
		List<Expense> expenses = expenseRepo.findAll();
		model.put("expenses", expenses);
		return "home";
	}

	@RequestMapping(method=RequestMethod.POST)
	public String submit(Expense expense) {
		expenseRepo.save(expense);
		return "redirect:/";
	}
}