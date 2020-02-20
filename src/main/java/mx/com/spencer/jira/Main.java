package mx.com.spencer.jira;

import java.text.ParseException;

import com.fasterxml.jackson.core.JsonProcessingException;

public class Main {
	public static void main(String[] args) throws JsonProcessingException, ParseException {
		new MyJiraClient().createIssues(new ReadFile().getDatos(args[0].toString()),args[1]);
		//m.createEpicas(args[1]);
	}
}
