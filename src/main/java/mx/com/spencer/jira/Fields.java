package mx.com.spencer.jira;

import java.util.List;

public class Fields {

	private String summary;
	private Parent parent;
	private Issuetype issuetype;
	private Project project;
	private List<String> labels;
	/*private String duedate;
	
	public String getDuedate() {
		return duedate;
	}
	public void setDuedate(String duedate) {
		this.duedate = duedate;
	}*/
	public List<String> getLabels() {
		return labels;
	}
	public void setLabels(List<String> labels) {
		this.labels = labels;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public Issuetype getIssuetype() {
		return issuetype;
	}
	public void setIssuetype(Issuetype issuetype) {
		this.issuetype = issuetype;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public Parent getParent() {
		return parent;
	}
	public void setParent(Parent parent) {
		this.parent = parent;
	}
}
