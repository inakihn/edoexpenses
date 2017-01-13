package edoexpenses;

import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ExpenseRepository {

  private JdbcTemplate jdbc;
  
  @Autowired
  public ExpenseRepository(JdbcTemplate jdbc) {
    this.jdbc = jdbc;
  }

  public List<Expense> findAll() {
    return jdbc.query(
        "select id, user, description, value, date " +
        "from expenses order by date",
        new RowMapper<Expense>() {
          public Expense mapRow(ResultSet rs, int rowNum) throws SQLException {
            Expense expense = new Expense();
            expense.setId(rs.getLong(1));
            expense.setUser(rs.getString(2));
            expense.setDescription(rs.getString(3));
            expense.setValue(rs.getString(4));
            expense.setDate(rs.getString(5));
            return expense;
          }
        }
      );
  }

  public void save(Expense expense) {
    jdbc.update(
        "insert into expenses " +
        "(user, description, value, date) " +
        "values (?, ?, ?, ?)",
        expense.getUser(), expense.getDescription(),
        expense.getValue(), expense.getDate());
  }
  
}