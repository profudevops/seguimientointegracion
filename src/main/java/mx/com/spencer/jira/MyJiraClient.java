package mx.com.spencer.jira;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

public class MyJiraClient {
	
	public void createIssues(List<String> datos, String ambiente) throws JsonProcessingException, ParseException{
		ObjectMapper objectMapper = new ObjectMapper();
		MapaEpic mapa = new MapaEpic();
		for (String registro : datos) {
			String[] registroArray = registro.split(",");
			List<Historia> historias = calculaEtiquetas(registroArray);
			Issue i = new Issue();
			Fields f = new Fields();
			
			Project pro = new Project();
			if(ambiente.equals("prod")) {
				pro.setKey("INPROD");
			}else {
				pro.setKey("INQA");
			}
			f.setProject(pro);
					
			List<String> labels = new ArrayList<String>();
			
			for (Historia historia : historias) {
				for (int j = 0; j < Integer.valueOf(historia.getNumero()).intValue(); j++) {
					f.setSummary(historia.getNombre()+j);
					labels.add(historia.getEtiqueta());
					f.setLabels(labels);
					labels = new ArrayList<String>();
					Issuetype ist=new Issuetype();
					ist.setId("10001");
					f.setIssuetype(ist);
					//f.setDuedate(historia.getFecha());
					i.setFields(f);
					//i.setDuedate("2020-12-01");
					System.out.println("______________"+objectMapper.writeValueAsString(i));
					HttpResponse<JsonNode> response = Unirest.post("https://profuturo-jira.atlassian.net/rest/api/3/issue")
					  .basicAuth("isc.spencer@gmail.com", "eXz2IZaUN1OL1T4HRYND7FA6")
					  .header("Accept", "application/json")
					  .header("Content-Type", "application/json")
					  .body(objectMapper.writeValueAsString(i))
					  .asJson(); 
					System.out.println("primera respuesta :___"+response.getBody());
					List<String> cadenasRespuesta = new ArrayList<String>();
					cadenasRespuesta.add(response.getBody().getObject().getString("id"));
					HttpResponse<JsonNode> responsenode = Unirest.post("https://profuturo-jira.atlassian.net/rest/agile/1.0/epic/"+mapa.getEpic(registroArray[1])+"/issue")
							  .basicAuth("isc.spencer@gmail.com", "eXz2IZaUN1OL1T4HRYND7FA6")
							  .header("Accept", "application/json")
							  .header("Content-Type", "application/json")
							  .body("{\"issues\": [\""+response.getBody().getObject().getString("id")+"\"]}")
							  .asJson();
					System.out.println(responsenode.getBody());
					HttpResponse<JsonNode> responsedate = Unirest.put("https://profuturo-jira.atlassian.net/rest/api/3/issue/"+response.getBody().getObject().getString("id"))
							  .basicAuth("isc.spencer@gmail.com", "eXz2IZaUN1OL1T4HRYND7FA6")
							  .header("Accept", "application/json")
							  .header("Content-Type", "application/json")
							  .body("{ \"fields\": {\"customfield_12615\": \""+historia.getFecha()+"\"}}'")
							  .asJson();
					System.out.println(responsedate);
				}
			}
		}
		
	}

	private List<Historia> calculaEtiquetas(String[] registroArray) throws ParseException {
		List<Historia> historias = new ArrayList<Historia>();
		Historia historia = new Historia ();
		for (int i = 2; i < registroArray.length; i++) {
			String valor = registroArray[i];
			System.out.println(valor +" i:"+i);
			if(valor !=null && !"".equals(valor) && i ==2) {
				String label = "APK";
				historia.setNombre(registroArray[0]+ label);
				historia.setEtiqueta(label);
				historia.setNumero(valor);
				historia.setFecha(getFechaFormato(registroArray[22]).toString());
				historias.add(historia);
				historia = new Historia ();
			}
			if(valor !=null && !"".equals(valor) && i ==3) {
				String label = "WAS";
				historia.setNombre(registroArray[0]+ label);
				historia.setEtiqueta(label);
				historia.setNumero(valor);
				historia.setFecha(getFechaFormato(registroArray[22]).toString());
				historias.add(historia);
				historia = new Historia ();
			}
			if(valor !=null && !"".equals(valor) && i ==4) {
				String label = "BUS";
				historia.setNombre(registroArray[0]+ label);
				historia.setEtiqueta(label);
				historia.setNumero(valor);
				historia.setFecha(getFechaFormato(registroArray[22]).toString());
				historias.add(historia);
				historia = new Historia ();
			}
			if(valor !=null && !"".equals(valor) && i ==5) {
				String label = "ETL";
				historia.setNombre(registroArray[0]+ label);
				historia.setEtiqueta(label);
				historia.setNumero(valor);
				historia.setFecha(getFechaFormato(registroArray[22]).toString());
				historias.add(historia);
				historia = new Historia ();
			}
			if(valor !=null && !"".equals(valor) && i ==6) {
				String label = "ODM";
				historia.setNombre(registroArray[0]+ label);
				historia.setEtiqueta(label);
				historia.setNumero(valor);
				historia.setFecha(getFechaFormato(registroArray[22]).toString());

				historias.add(historia);
				historia = new Historia ();
			}
			if(valor !=null && !"".equals(valor) && i ==7) {
				String label = "BPM";
				historia.setNombre(registroArray[0]+ label);
				historia.setEtiqueta(label);
				historia.setNumero(valor);
				historia.setFecha(getFechaFormato(registroArray[22]).toString());

				historias.add(historia);
				historia = new Historia ();
			}
			if(valor !=null && !"".equals(valor) && i ==8) {
				String label = "BROKER";
				historia.setNombre(registroArray[0]+ label);
				historia.setEtiqueta(label);
				historia.setNumero(valor);
				historia.setFecha(getFechaFormato(registroArray[22]).toString());

				historias.add(historia);
				historia = new Historia ();
			}
			if(valor !=null && !"".equals(valor) && i ==9) {
				String label = "COGNOS";
				historia.setNombre(registroArray[0]+ label);
				historia.setEtiqueta(label);
				historia.setNumero(valor);
				historia.setFecha(getFechaFormato(registroArray[22]).toString());

				historias.add(historia);
				historia = new Historia ();
			}
			if(valor !=null && !"".equals(valor) && i ==10) {
				String label = "FILENET(ZIP)";
				historia.setNombre(registroArray[0]+ label);
				historia.setEtiqueta(label);
				historia.setNumero(valor);
				historia.setFecha(getFechaFormato(registroArray[22]).toString());

				historias.add(historia);
				historia = new Historia ();
			}
			if(valor !=null && !"".equals(valor) && i ==11) {
				String label = "PROCESS";
				historia.setNombre(registroArray[0]+ label);
				historia.setEtiqueta(label);
				historia.setNumero(valor);
				historia.setFecha(getFechaFormato(registroArray[22]).toString());

				historias.add(historia);
				historia = new Historia ();
			}
			if(valor !=null && !"".equals(valor) && i ==12) {
				String label = "PORTAL";
				historia.setNombre(registroArray[0]+ label);
				historia.setEtiqueta(label);
				historia.setNumero(valor);
				historia.setFecha(getFechaFormato(registroArray[22]).toString());

				historias.add(historia);
				historia = new Historia ();
			}
			if(valor !=null && !"".equals(valor) && i ==13) {
				String label = "MIDDLEWARE";
				historia.setNombre(registroArray[0]+ label);
				historia.setEtiqueta(label);
				historia.setNumero(valor);
				historia.setFecha(getFechaFormato(registroArray[22]).toString());

				historias.add(historia);
				historia = new Historia ();
			}
			if(valor !=null && !"".equals(valor) && i ==14) {
				String label = "CHATBOT(ZIP)";
				historia.setNombre(registroArray[0]+ label);
				historia.setEtiqueta(label);
				historia.setNumero(valor);
				historia.setFecha(getFechaFormato(registroArray[22]).toString());

				historias.add(historia);
				historia = new Historia ();
			}
			if(valor !=null && !"".equals(valor) && i ==15) {
				String label = "BD";
				historia.setNombre(registroArray[0]+ label);
				historia.setEtiqueta(label);
				historia.setNumero(valor);
				historia.setFecha(getFechaFormato(registroArray[22]).toString());

				historias.add(historia);
				historia = new Historia ();
			}
			if(valor !=null && !"".equals(valor) && i ==16) {
				String label = "DATAPOWER";
				historia.setNombre(registroArray[0]+ label);
				historia.setEtiqueta(label);
				System.out.println(historia.getNombre());
				historia.setFecha(getFechaFormato(registroArray[22]).toString());

				historia.setNumero(valor);
				historias.add(historia);
				historia = new Historia ();
			}
			if(valor !=null && !"".equals(valor) && i ==17) {
				String label = ".NET";
				historia.setNombre(registroArray[0]+ label);
				historia.setEtiqueta(label);
				historia.setNumero(valor);
				historia.setFecha(getFechaFormato(registroArray[22]).toString());

				historias.add(historia);
				historia = new Historia ();
			}
			if(valor !=null && !"".equals(valor) && i ==18) {
				String label = "KETLES";
				historia.setNombre(registroArray[0]+ label);
				historia.setEtiqueta(label);
				historia.setNumero(valor);
				historia.setFecha(getFechaFormato(registroArray[22]).toString());

				historias.add(historia);
				historia = new Historia ();
			}
			if(valor !=null && !"".equals(valor) && i ==19) {
				String label = "PEOPLESOFT91";
				historia.setNombre(registroArray[0]+ label);
				historia.setEtiqueta(label);
				historia.setNumero(valor);
				historia.setFecha(getFechaFormato(registroArray[22]).toString());

				historias.add(historia);
				historia = new Historia ();
			}
			if(valor !=null && !"".equals(valor) && i ==20) {
				String label = "PHP";
				historia.setNombre(registroArray[0]+ label);
				historia.setEtiqueta(label);
				historia.setNumero(valor);
				historia.setFecha(getFechaFormato(registroArray[22]).toString());

				historias.add(historia);
				historia = new Historia ();
			}
			if(valor !=null && !"".equals(valor) && i ==21) {
				String label = "VISUALBASIC";
				historia.setNombre(registroArray[0]+ label);
				historia.setEtiqueta(label);
				historia.setNumero(valor);
				historia.setFecha(getFechaFormato(registroArray[22]).toString());

				historias.add(historia);
				historia = new Historia ();
			}
		}
		return historias;
	}
	
	public void createEpicas(String ambiente) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		Issue i = new Issue();
		Fields f = new Fields();
		
		Project pro = new Project();
		if(ambiente.equals("prod")) {
			pro.setKey("INPROD");
		}else {
			pro.setKey("INQA");
		}
		
		for (String proyecto : getProyectos()) {
			f.setProject(pro);
			f.setSummary(proyecto);
			//f.setName(proyecto);
			Issuetype ist=new Issuetype();
			ist.setId("10000");
			f.setIssuetype(ist);
			i.setFields(f);
			System.out.println(objectMapper.writeValueAsString(i));
			HttpResponse<JsonNode> response = Unirest.post("https://profuturo-jira.atlassian.net/rest/api/3/issue")
			  .basicAuth("isc.spencer@gmail.com", "eXz2IZaUN1OL1T4HRYND7FA6")
			  .header("Accept", "application/json")
			  .header("Content-Type", "application/json")
			  .body(objectMapper.writeValueAsString(i))
			  .asJson(); 
			System.out.println(response.getBody());
		}
		
	}
	
	public List<String> getProyectos(){
		List<String> proyectos = new ArrayList<String>();
		String cadena = "AFORE MOVIL SP";
		proyectos.add(cadena);
		cadena = "";
		return proyectos;
	}
	
	public String getFechaFormato(String fechaInicial) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date fechaFinal = sdf.parse(fechaInicial);
		sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return sdf.format(fechaFinal).toString();
	}
	
}
