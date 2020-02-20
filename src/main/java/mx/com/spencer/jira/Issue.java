package mx.com.spencer.jira;

import java.util.List;

public class Issue {

	public List<String> getUpdate() {
		return update;
	}
	public void setUpdate(List<String> update) {
		this.update = update;
	}
	private List<String> update;
	private Fields fields;
	private String duedate;
	
	public String getDuedate() {
		return duedate;
	}
	public void setDuedate(String duedate) {
		this.duedate = duedate;
	}
	public Fields getFields() {
		return fields;
	}
	public void setFields(Fields fields) {
		this.fields = fields;
	}
	
}
