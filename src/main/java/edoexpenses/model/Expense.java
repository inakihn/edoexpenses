package edoexpenses.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

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
//		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
//		formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
//		this.date = formatter.parse(date);
		this.date = date;
	}
	
	
}