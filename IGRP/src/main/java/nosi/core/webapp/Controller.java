package nosi.core.webapp;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import nosi.core.config.Config;
import nosi.core.config.ConfigApp;
import nosi.core.exception.ServerErrorHttpException;
import nosi.core.gui.components.IGRPMessage;
import nosi.core.gui.components.IGRPView;
import nosi.core.gui.page.Page;
import nosi.core.webapp.activit.rest.TaskService;
import nosi.core.webapp.activit.rest.TaskServiceQuery;
import nosi.core.webapp.activit.rest.TaskVariables;
import nosi.core.webapp.bpmn.BPMNHelper;
import nosi.core.webapp.bpmn.DisplayDocmentType;
import nosi.core.webapp.bpmn.InterfaceBPMNTask;
import nosi.core.webapp.bpmn.ViewTaskDetails;
import nosi.core.webapp.helpers.EncrypDecrypt;
import nosi.core.webapp.helpers.Permission;
import nosi.core.webapp.helpers.Route;
import nosi.core.webapp.helpers.StringHelper;
import nosi.core.webapp.webservices.helpers.FileRest;
import nosi.core.xml.XMLExtractComponent;
import nosi.core.xml.XMLWritter;
import nosi.webapps.igrp.dao.Action;
import nosi.webapps.igrp.dao.ProfileType;
import nosi.webapps.igrp.dao.TipoDocumentoEtapa;
/**
 * Useful functions like addQueryString
 * 
 * @author Marcel Iekiny
 * Apr 15, 2017
 */
public class Controller{
	protected Config config = new Config();
	protected ConfigApp configApp = new ConfigApp();
	private QueryString<String,Object> queryString = new QueryString<>();
	private View view;
	
	protected String format = Response.FORMAT_XML;
	protected String encoding = Response.CHARSET_UTF_8;
	protected boolean isNoCached = false;

	private Response responseWrapper;
	private String qs = "";
	
	public Response getResponseWrapper() {
		return responseWrapper;
	}

	public void setResponseWrapper(Response responseWrapper) {
		this.responseWrapper = responseWrapper;
	}
	
	protected void restartQueryString() {
		queryString = new QueryString<>();
	}
	
	/**
	 * Get all the r Params, and in a forEach does this.addQueryString of them all
	 * 
	 * @return
	 */
	protected QueryString<String,Object> loadQueryString() {
		Core.getParameters().entrySet().stream().filter(p->!p.getKey().equals("r")).forEach(p->{
			this.addQueryString(p.getKey(),p.getValue()!=null?p.getValue()[0]:"");
		});
		return this.queryString;
	}
	
	public QueryString<String,Object> queryString() {
		return this.queryString;
	}
	
	public QueryString<String,Object> addQueryString(String name,Object value) {
		return this.queryString().addQueryString(name, value);
	}
	public QueryString<String,Object> removeQueryString(String key) {
		return this.queryString.removeQueryString(key);
	}
	
	protected String[] getQueryArray(String name) {
		return Core.getParamArray(name);
	}
	
	protected String getQueryString(String name) {
		return Core.getParam(name);
	}	

	protected Integer getQueryStringInteger(String name) {
		return Core.getParamInt(name);
	}	
	
	protected Long getQueryStringLong(String name) {
		return Core.getParamLong(name);
	}

	protected Short getQueryStringShort(String name) {
		return Core.getParamShort(name);
	}	
	protected Float getQueryStringFloat(String name) {
		return Core.getParamFloat(name);
	}	

	protected Double getQueryStringDouble(String name) {
		return Core.getParamDouble(name);
	}
	
	public Controller(){this.view = null;}
	
	protected final Response renderView(View view, boolean isRenderPartial) throws IOException{ // renderiza a view e aplica ou nao um layout 
		Response resp = new Response();
		this.view = view;
		view.setContext(this); // associa controller ao view
		this.view.render();
		String result = this.view.getPage().renderContent(!isRenderPartial);
		resp.setType(1);
		resp.setCharacterEncoding(Response.CHARSET_UTF_8);
		resp.setContentType(Response.FORMAT_XML);
		resp.setHttpStatus(HttpStatus.STATUS_200);
		if(this.isNoCached){
			resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
			resp.setHeader("Pragma", "no-cache"); // HTTP 1.0.
			resp.setDateHeader("Expires", 0); // Proxies.
		}
		resp.setContent(result);
	return resp;
	}
	
	public Response renderView(String app, String page, View v) throws IOException {
		return this.renderView(app, page, v,null);
	}
	
	public Response renderView(String app, String page, View v,InterfaceBPMNTask bpmn) throws IOException {
		String taskId = Core.getParam("taskId",false);
		String p_processId = Core.getParam("p_processId");
		String taskDefinition = Core.getParam("taskDefinition",false);
		String processDefinition = Core.getParam("processDefinition",false);
		String saveButton = Core.getParam("saveButton");
		String backButton = Core.getParam("backButton");
		
		IGRPMessage msg = new IGRPMessage();
		String m = msg.toString();
		this.view = v;
		Action ac = new Action().find()
				.andWhere("application.dad", "=",app)
				.andWhere("page", "=",Page.resolvePageName(page))
				.one();
		Response resp = new Response();
		this.view.setContext(this); // associa controller ao view
		this.view.render();
		resp.setType(1);
		resp.setCharacterEncoding(Response.CHARSET_UTF_8);
		resp.setContentType(Response.FORMAT_XML);
		resp.setHttpStatus(HttpStatus.STATUS_200);
		if(this.isNoCached){
			resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
			resp.setHeader("Pragma", "no-cache"); // HTTP 1.0.
			resp.setDateHeader("Expires", 0); // Proxies.
		}		
		
		XMLExtractComponent comp = new XMLExtractComponent();
		String content = this.view.getPage().renderContent(false);
		content = comp.removeXMLButton(content);
		XMLWritter xml = new XMLWritter("rows",this.config.getLinkPageXsl(ac), "utf-8");
		xml.addXml(this.getConfig().getHeader(null));
		xml.startElement("content");
		xml.writeAttribute("type", "");
//		xml.addXml(new BPMNTimeLine().get().toString());
		if(Core.isNotNull(p_processId)) {
			xml.addXml(comp.generateButtonProcess(app,ac.getApplication().getId(),this.getConfig().PREFIX_TASK_NAME+taskDefinition,"save",p_processId).toString());
		}
		if(Core.isNull(saveButton) && Core.isNotNull(taskId)) {
			xml.addXml(comp.generateButtonTask(app,ac.getApplication().getId(),this.getConfig().PREFIX_TASK_NAME+taskDefinition,"save", taskId).toString());
		}
		if(Core.isNotNull(backButton) && Core.isNotNull(taskId)) {
			xml.addXml(comp.generateBackButtonTask("igrp","ExecucaoTarefas","executar_button_minha_tarefas",taskId).toString());
		}
		xml.addXml(this.getTaskViewDetails(taskId));
		xml.addXml(content);
		xml.addXml(this.getDocument(bpmn,processDefinition,taskDefinition,ac));
		if(m!=null){
			xml.addXml(m);
		}
		xml.endElement();
		resp.setContent(xml.toString());
		return resp;
	}

	private String getTaskViewDetails(String taskId) {
		Object obj = Core.getAttributeObject("taskObj", true);
		if(Core.isNotNull(obj)) {			
			TaskServiceQuery task = (TaskServiceQuery) obj;		
			List<TaskVariables.TaskVariableDetails> v = task.queryHistoryTaskVariables(task.getId());
			ProfileType prof = new ProfileType().findOne(Core.toInt(v.get(1).getPropertyValue()));
			IGRPView viewTD = ViewTaskDetails.get(task.getProcessDefinitionKey(), task.getName(),task.getProcessInstanceId(), taskId,task.getStartTime(), task.getEndTime(),prof.getOrganization().getName(),prof.getDescr(),v.get(2).getPropertyValue());
			return  viewTD.toString();
		}
		TaskService task = new TaskService().getTask(taskId);
		if(task!=null) {
			IGRPView viewTD = ViewTaskDetails.get(task.getProcessDefinitionKey(), task.getName(),task.getProcessInstanceId(), taskId);
			return  viewTD.toString();
		}
		return "";
	}

	private String getDocument(InterfaceBPMNTask bpmn, String processDefinition, String taskDefinition, Action action) {
		if(bpmn==null)
			return BPMNHelper.addFileSeparator(processDefinition,taskDefinition,action.getApplication().getId(),null);
		
		DisplayDocmentType display = new DisplayDocmentType();
		display.setListDocmentType(bpmn.getInputDocumentType());
		String previewTask = Core.getParam("previewTask",false);
		String isDetails = Core.getParam("isDetails");
		if(Core.isNotNull(isDetails))
			display.setShowInputFile(false);
		
		if(Core.isNotNull(previewTask)) {
			try {
				String packageName =  "nosi.webapps."+action.getApplication().getDad().toLowerCase()+".process."+processDefinition.toLowerCase();
				Class<?> c = Class.forName(packageName+"."+this.getConfig().PREFIX_TASK_NAME+previewTask+"Controller");
				Method method = c.getMethod("getOutputDocumentType");
				@SuppressWarnings("unchecked")
				List<TipoDocumentoEtapa> listDocOutput = (List<TipoDocumentoEtapa>) method.invoke(c.newInstance());
				display.addListDocumentType(listDocOutput);
			} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return display.display();
	}

	protected final Response renderView(View view) throws IOException{ // Overload ...
		return this.renderView(view, false);
	}
	
	protected final Response renderView(String content){
		Response resp = new Response();
		resp.setType(1);
		resp.setCharacterEncoding(this.encoding);
		resp.setContentType(this.format);
		resp.setContent(content);
		return resp;
	}
	
	private final Response redirect_(String url){
		Response resp = new Response();
		resp.setType(2);
		resp.setContentType(this.format);
		resp.setUrl(url);
		resp.setHttpStatus(HttpStatus.STATUS_200);
	return resp;
	}
	protected final Response redirect(String app, String page, String action,QueryString<String,Object> queryString) throws IOException{
		if(queryString.getValues("dad")==null && !action.contains("dad"))
			queryString.addQueryString("dad", Core.getParam("dad"));
		this.setQueryString(queryString);
		return this.redirect_(Route.toUrl(app, page, action, qs));
	}
	
	private void setQueryString(QueryString<String,Object> queryString) {
		qs = "";
		if(queryString!=null && !queryString.getQueryString().isEmpty()) {
			queryString.getQueryString().entrySet().stream().forEach(q->{
				q.getValue().stream().forEach(q1->{
					if(q1!=null)
						qs += "&"+q.getKey()+"="+q1.toString();
				});					
			});
		}
	}

	private void setQueryStringToAttributes(QueryString<String,Object> queryString) {
		if(queryString!=null && !queryString.getQueryString().isEmpty()) {
			queryString.getQueryString().entrySet().stream().forEach(qs->{
				Core.setAttribute(qs.getKey(), qs.getValue().toArray());
			});
		}
	}
	protected final Response redirect(String app, String page, String action, String qs) throws IOException{
		return this.redirect_(Route.toUrl(app, page, action, qs));
	}
	
	protected final Response redirect(String r, String qs) throws IOException{
		return this.redirect_(Route.toUrl(r, qs));
	}
	
	protected final Response redirect(String r){
		return this.redirect_(Route.toUrl(r));
	}
	
	protected final Response redirectError() throws IOException{
		return this.redirect_(Route.toUrl("igrp", "error-page", "exception"));
	}
	
	protected final Response redirect(String app, String page, String action) throws IOException{
		return this.redirect_(Route.toUrl(app, page,  this.addParamDad(action)));
	}
	
	protected final Response redirect(String app, String page, String action, String []paramNames, String []paramValues) throws IOException{
		return this.redirect_(Route.toUrl(app, page, action, paramNames, paramValues));
	}
	
	protected final Response redirectToUrl(String url){
		Response resp = new Response();
		resp.setType(2);
		resp.setUrl(url);
		resp.setHttpStatus(HttpStatus.STATUS_200);
		return resp;
	}
	
	protected final Response forward(String app, String page, String action) {
		Response resp = new Response();
		resp.setType(3);
		resp.setUrl(Route.toUrl(app, page, this.addParamDad(action)));
		return resp;
	}
	
	private String addParamDad(String action) {
		if(!action.contains("dad")) {
			action+="&dad="+Core.getParam("dad");
		}
		return action;
	}

	protected final Response sendFile(File file, String name, String contentType, boolean download) {
		byte []content = null;
		try {
			content = new byte[(int)file.length()];
			FileInputStream is = new FileInputStream(file);
			is.read(content);
			is.close();
			return this.xSend(content, name, contentType, download);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return this.xSend(content, name, contentType, download); // send it as stream ... binary file 
	}
	// send it as stream ... binary file 
	public final Response xSend(byte []file, String name, String contentType, boolean download) {
		if(file == null) throw new ServerErrorHttpException();
//		if(/*name.contains(".") && */contentType != null && !contentType.isEmpty()) throw new IllegalArgumentException("Please verify your fileName and contentType.");
		Response response = new Response();
		if(contentType == null || contentType.isEmpty()) {
			contentType = "application/octet-stream"; // default 
		}else {
			try {
				String extension = "." + contentType.split("/")[1];
				if(!name.contains("."))
					name = (name == null || name.isEmpty() ? "igrp-file" + extension : name + extension);
			}catch(Exception e) {
				contentType = "application/octet-stream";
				e.printStackTrace();
			}
		}
		Igrp.getInstance().getResponse().addHeader("Content-Description", "File Transfer");
		if(download)
			Igrp.getInstance().getResponse().addHeader("Content-Disposition", "attachment; filename=\"" + name + "\"");
		else
			Igrp.getInstance().getResponse().addHeader("Content-Disposition", "inline; filename=\"" + name + "\"");
		response.setType(1);
		response.setContentLength(file.length);
		response.setContentType(contentType);
		response.setStream(file);
		
		return response;
	}

	public View getView(){
		return this.view;
	}
	
	// ... main statics methods ...
	
	public void initControllerNRunAction() throws IOException{
		this.resolveRoute(); // (1)
		this.prepareResponse(); // (2)
	}
	
	private void prepareResponse() throws IOException{
		 Object obj = this.run();
		 if(obj != null && obj instanceof Response){
			 Igrp app = Igrp.getInstance();
			 
			 String appDad = app.getCurrentAppName();
			 String pageName = app.getCurrentPageName();
			 
			 if(app.getCurrentActionName() != null && appDad != null && pageName != null) {
				 if(!new Action().isPublicPage(appDad, pageName)) {
					 if(new Permission().isPermition(appDad, pageName, app.getCurrentActionName())) {
						 Response resp = (Response) obj;
						 Igrp.getInstance().getCurrentController().setResponseWrapper(resp);
					 }
				 }else {
					 Response resp = (Response) obj;
					 Igrp.getInstance().getCurrentController().setResponseWrapper(resp);
				 }
			 }
			 
		 }
	}
	
	private void resolveRoute() throws IOException{
		Igrp app = Igrp.getInstance();
		String r = Core.isNotNull(app.getRequest().getParameter("r"))?app.getRequest().getParameter("r").toString():"igrp/login/login";			
		
		r = new EncrypDecrypt().decrypt(r);	
		
		if(r != null){
			String auxPattern = this.config.PATTERN_CONTROLLER_NAME;
				if(r.matches(auxPattern + "/" + auxPattern + "/" + auxPattern)){
					String []aux = r.split("/");
					app.setCurrentAppName(aux[0]);
					app.setCurrentPageName(aux[1]);
					app.setCurrentActionName(aux[2]);
				}else		
					throw new ServerErrorHttpException("The route format is invalid");
		}
		String application = "Application: " + app.getCurrentAppName();
		String page = "Page: " + app.getCurrentPageName();
		String action = "Action: " + app.getCurrentActionName();
		String controllerName = "Controller: " + "nosi.webapps."  + app.getCurrentAppName().trim().toLowerCase() + ".pages." + app.getCurrentPageName() + "Controller.java";
		String viewName = "View: " + "nosi.webapps."  + app.getCurrentAppName().trim().toLowerCase() + ".pages." + app.getCurrentPageName() + "View.java";
		String modelName = "Model: " + "nosi.webapps."  + app.getCurrentAppName().trim().toLowerCase() + ".pages." + app.getCurrentPageName() + ".java";
		String xsl = "xsl: " + Igrp.getInstance().getServlet().getServletContext().getContextPath() + "/images/IGRP/IGRP2.3/app/" + app.getCurrentAppName().trim().toLowerCase() + "/" + app.getCurrentPageName().toLowerCase() + "/" + app.getCurrentPageName() + ".xsl";
		app.getLog().addMessage(application);
		app.getLog().addMessage(page);
		app.getLog().addMessage(action);
		app.getLog().addMessage(controllerName);
		app.getLog().addMessage(viewName);
		app.getLog().addMessage(modelName);
		app.getLog().addMessage(xsl);
	}
	
	protected Object run(){ 
		Igrp app = Igrp.getInstance();
		String auxAppName = "";
		String auxPageName = "";
		String  auxcontrollerPath="";
		String auxActionName = "";
		if(app!=null && app.getCurrentAppName()!=null && app.getCurrentActionName()!=null && app.getCurrentPageName()!=null ){
			for(String aux : app.getCurrentAppName().split("-"))
				auxAppName += aux.substring(0, 1).toUpperCase() + aux.substring(1);
			for(String aux : app.getCurrentActionName().split("-"))
				auxActionName += aux.substring(0, 1).toUpperCase() + aux.substring(1);
			for(String aux : app.getCurrentPageName().split("-")){
				auxPageName += aux.substring(0, 1).toUpperCase() + aux.substring(1);
			}
			auxActionName = "action" + auxActionName;
			auxcontrollerPath = this.config.getPackage(auxAppName,auxPageName,auxActionName);
		}else {
			auxActionName = "actionIndex";
			auxcontrollerPath = this.config.getPackage("igrp","Home",auxActionName);
		}
		
		return Page.loadPage(auxcontrollerPath, auxActionName); // :-)
	}
	
	protected Response call(String app, String page, String action) {
		return this.call(app, page, action, null);
	}
	
	public Response call(String app, String page, String action,QueryString<String,Object> queryString) {
		IGRPMessage msg = new IGRPMessage();
		String m = msg.toString();
		this.setQueryStringToAttributes(queryString);
		String auxcontrollerPath = this.config.getPackage(app,page,action);
		Igrp.getInstance().setCurrentAppName(app);
		Igrp.getInstance().setCurrentPageName(page);
		Igrp.getInstance().setCurrentActionName(action);
		Object obj = Page.loadPage(auxcontrollerPath, "action"+StringHelper.camelCaseFirst(action));
		Response resp = (Response) obj;
		String content = resp.getContent();		
		if(m!=null){
			content = content.replaceAll("<messages></messages>", m);
		}
		resp.setContent(content);
		return  resp ;
	}	
	
	protected Response forward(String app, String page, String action, QueryString<String,Object> queryString) {
		this.setQueryString(queryString);
		Response resp = new Response();
		resp.setType(3);
		resp.setUrl(Route.toUrl(app, page, action,qs));
		return resp;
	}

	protected final Response xSend(FileRest file, String name, String contentType, boolean download) {
		Response response = new Response();
		Igrp.getInstance().getResponse().addHeader("Content-Description", "File Transfer");
		if(download)
			Igrp.getInstance().getResponse().addHeader("Content-Disposition", "attachment; filename=\"" + name + "\"");
		else
			Igrp.getInstance().getResponse().addHeader("Content-Disposition", "inline; filename=\"" + name + "\"");
		response.setType(1);
		response.setFile(file);
		return response;
	}
	
	public void sendResponse() {
		Response responseWrapper = Igrp.getInstance().getCurrentController().getResponseWrapper();
		if(responseWrapper!=null) {
			try {
				switch(responseWrapper.getType()) {
				case 1: // render it 
						try {
							if(responseWrapper.getStream() != null && responseWrapper.getStream().length > 0) {
								Igrp.getInstance().getResponse().getOutputStream().write(responseWrapper.getStream());
								Igrp.getInstance().getResponse().getOutputStream().close();
							}else if(responseWrapper.getFile()!=null){
								 HttpServletResponse response = Igrp.getInstance().getResponse();
								 String name = responseWrapper.getFile().getFileName();
						         response.setContentType(responseWrapper.getFile().getContentType());						 
						         response.setHeader("Content-Disposition", "attachment; filename=\""+name+"\";");
						         response.setHeader("Cache-Control", "no-cache");  
						         response.setContentLength(responseWrapper.getFile().getSize());						  
						         try {
									 ServletOutputStream sos = response.getOutputStream();
									 BufferedInputStream bis = new BufferedInputStream(responseWrapper.getFile().getContent());
							         int data;
							         while((data = bis.read()) != -1) { 
							            sos.write(data);
							         } 
							         bis.close();
							         sos.close();
								} catch (IOException e) {
									// TODO Auto-generated catch block 
									e.printStackTrace();
								}
						         responseWrapper.getFile().getContent().close();
							}else {
								Igrp.getInstance().getResponse().getWriter().append(responseWrapper.getContent());
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
						break;
					case 2: // redirect 
						boolean isAbsolute = false;
						try {
							String url = responseWrapper.getUrl();
							try {
								java.net.URI uri = java.net.URI.create(url);
								isAbsolute = uri.isAbsolute() && uri.toURL().getProtocol().matches("(?i)(http|https)");
							} catch (MalformedURLException e) { // Ensure the url format is perfect ...
								isAbsolute = false;
							}
							Igrp.getInstance().getResponse().sendRedirect( isAbsolute == true ? url : "webapps" + url);
						} catch (IOException e) {
							e.printStackTrace();
						}
						break;
					case 3: // forward 
						String url = "app/webapps" + responseWrapper.getUrl().replaceAll("&&","&");
						try {
							Igrp.getInstance().getRequest().getRequestDispatcher(url).forward(Igrp.getInstance().getRequest(), Igrp.getInstance().getResponse());
						} catch (ServletException | IOException e) {
							e.printStackTrace();
						} 
						break;
					default:;
				}
			}catch(java.lang.NullPointerException e) {
				e.printStackTrace();
			}
		}
	}

	public Config getConfig() {
		return config;
	}

	public void setConfig(Config config) {
		this.config = config;
	}

	
	
	
	//... Others methods ...
	
	
}