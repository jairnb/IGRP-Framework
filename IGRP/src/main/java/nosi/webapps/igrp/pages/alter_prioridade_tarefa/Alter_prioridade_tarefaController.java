package nosi.webapps.igrp.pages.alter_prioridade_tarefa;

import nosi.core.webapp.Controller;
import nosi.core.webapp.databse.helpers.ResultSet;
import nosi.core.webapp.databse.helpers.QueryInterface;
import java.io.IOException;
import nosi.core.webapp.Core;
import nosi.core.webapp.Response;
/* Start-Code-Block (import) */
/* End-Code-Block */
/*----#start-code(packages_import)----*/
import nosi.core.webapp.Igrp;
import java.util.LinkedHashMap;
import java.util.Map;
import nosi.core.webapp.activit.rest.business.TaskServiceIGRP;
import nosi.core.webapp.activit.rest.entities.TaskService;
import nosi.core.webapp.bpmn.BPMNConstants;

import static nosi.core.i18n.Translator.gt;
/*----#end-code----*/
		
public class Alter_prioridade_tarefaController extends Controller {
	public Response actionIndex() throws IOException, IllegalArgumentException, IllegalAccessException{
		Alter_prioridade_tarefa model = new Alter_prioridade_tarefa();
		model.load();
		Alter_prioridade_tarefaView view = new Alter_prioridade_tarefaView();
		/*----#gen-example
		  EXAMPLES COPY/PASTE:
		  INFO: Core.query(null,... change 'null' to your db connection name, added in Application Builder.
		view.nova_prioridade.setQuery(Core.query(null,"SELECT 'id' as ID,'name' as NAME "));
		  ----#gen-example */
		/*----#start-code(index)----*/
		Map<String,String> listPrioridade = new LinkedHashMap<String,String>();
		listPrioridade.put(null, gt("--- Escolher Prioridade ---"));
		listPrioridade.put("100", "Urgente");
      	listPrioridade.put("75", "Alta");
		listPrioridade.put("50", "Normal");
     	listPrioridade.put("25", "Baixa");
		listPrioridade.put("0", "Muito baixa");
		TaskServiceIGRP taskRest = new TaskServiceIGRP();
		String id = Core.getParam(BPMNConstants.PRM_TASK_ID);
		String type = Core.getParam("type");
		if(id!=null && !id.equals("")){
			TaskService task = taskRest.getTask(id); 
			if(task != null){
				model.setData_criacao_da_tarefa(task.getCreateTime().toString());
				model.setData_inicio_da_tarefa(task.getCreateTime().toString());
				model.setDescricao_da_tarefa(task.getDescription());
				model.setPrioridade_da_tarefa(listPrioridade.get(""+task.getPriority()).toString());
				model.setTarefa_atribuida_a(task.getAssignee());
				model.setTarefa_atribuida_por(task.getOwner());
				model.setTipo_da_tarefa(task.getCategory());
				model.setNumero_de_processo(task.getProcessDefinitionId());
				model.setTipo_de_processo(task.getName());
				model.setData_criacao_do_processo(task.getCreateTime().toString());
				model.setId(id);
			}
		}
		view.target = "_blank";
		view.nova_prioridade.setValue(listPrioridade);
		
		if(type!=null && type.equalsIgnoreCase("view")){
			view.btn_salvar.setTitle("Fechar");
			view.btn_salvar.setLink("");
			view.btn_salvar.setPage("");
			view.btn_salvar.setImg("danger|fa-close");
			view.btn_salvar.setTarget("_close");
			view.nova_prioridade.setVisible(false);
		}
		/*----#end-code----*/
		view.setModel(model);
		return this.renderView(view);	
	}
	
	public Response actionSalvar() throws IOException, IllegalArgumentException, IllegalAccessException{
		Alter_prioridade_tarefa model = new Alter_prioridade_tarefa();
		model.load();
		/*----#gen-example
		  EXAMPLES COPY/PASTE:
		  INFO: Core.query(null,... change 'null' to your db connection name, added in Application Builder.
		  this.addQueryString("p_id","12"); //to send a query string in the URL
		  return this.forward("igrp","Alter_prioridade_tarefa","index",this.queryString()); //if submit, loads the values
		  Use model.validate() to validate your model
		  ----#gen-example */
		/*----#start-code(salvar)----*/			
		TaskServiceIGRP taskRest = new TaskServiceIGRP();
		TaskService task = taskRest.getTask(model.getId());
		if(task != null){
			task.setPriority(Integer.parseInt(model.getNova_prioridade()));
			task = taskRest.getTaskServiceRest().changePriority(task);
			if(task != null) 
				Core.setMessageSuccess(gt("Prioridade da tarefa alterada com sucesso")); 
			else 
				Core.setMessageError(gt("Falha ao tentar efetuar esta operação")); 
		}else 
			Core.setMessageError(gt("Falha ao tentar efetuar esta operação")); 
		
		this.addQueryString(BPMNConstants.PRM_TASK_ID, model.getId()); 
		/*----#end-code----*/
		return this.redirect("igrp","Alter_prioridade_tarefa","index", this.queryString());	
	}
	
		
		
/*----#start-code(custom_actions)----*/


	/*----#end-code----*/
}
