package nosi.core.webapp.activit.rest;
/**
 * Emanuel
 * 10 Jan 2018
 */
public class TaskVariablesQuery {
	private String name;
	private String operation;
	private String type;
	private Object value;
	
	public TaskVariablesQuery(){
		
	}
	
	public TaskVariablesQuery(String name, String operation, String type, Object value) {
		super();
		this.name = name;
		this.operation = operation;
		this.type = type;
		this.value = value;
	}	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	
	
}
