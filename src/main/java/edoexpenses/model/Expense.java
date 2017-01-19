package edoexpenses.model;

import java.text.ParseException;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Expense {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String description;
	private Double value;
	
	private Date date;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = Double.parseDouble(value);
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) throws ParseException {
		this.date = date;
	}
	
	
}