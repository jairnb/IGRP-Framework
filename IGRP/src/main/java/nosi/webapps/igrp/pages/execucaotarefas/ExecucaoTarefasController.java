package nosi.webapps.igrp.pages.execucaotarefas;

import nosi.core.webapp.Controller;
import java.io.IOException;
import nosi.core.webapp.Core;
import nosi.core.webapp.Response;
/*----#start-code(packages_import)----*/
import nosi.core.webapp.activit.rest.TaskService;
import nosi.core.webapp.activit.rest.HistoricTaskService;
import nosi.core.webapp.activit.rest.ProcessDefinitionService;
import nosi.webapps.igrp.dao.Application;
import nosi.webapps.igrp.dao.ProfileType;
import nosi.webapps.igrp.pages.execucaotarefas.ExecucaoTarefas.Table_disponiveis;
import nosi.webapps.igrp.pages.execucaotarefas.ExecucaoTarefas.Table_gerir_tarefas;
import nosi.webapps.igrp.pages.execucaotarefas.ExecucaoTarefas.Table_minhas_tarefas;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import java.util.ArrayList;
import java.util.HashMap;
/*----#end-code----*/

public class ExecucaoTarefasController extends Controller {
	public Response actionIndex() throws IOException, IllegalArgumentException, IllegalAccessException {
		ExecucaoTarefas model = new ExecucaoTarefas();
		model.load();
		model.setView_estatistica_img("/IGRP/images/IGRP/IGRP2.3/assets/img/jon_doe.jpg");
		ExecucaoTarefasView view = new ExecucaoTarefasView();
		view.p_id_d.setParam(true);
		view.p_id_c.setParam(true);
		view.p_id_e.setParam(true);
		view.id.setParam(true);
		view.p_id_g.setParam(true);
		/*----#gen-example
		  EXAMPLES COPY/PASTE:
		  INFO: Core.query(null,... change 'null' to your db connection name, added in Application Builder.
		model.loadTable_disponiveis(Core.query(null,"SELECT 'Sit rem doloremque perspiciatis natus' as n_tarefa_d,'Aperiam anim ipsum unde lorem' as tarefas_tabela_disponiveis,'Adipiscing accusantium ut aper' as categorias_processo_tabela_disponiveis,'Rem accusantium consectetur vo' as data_entrada_tabela_disponiveis,'06-01-2010' as data_fim_d,'1' as p_id_d "));
		model.loadTable__colaboradores(Core.query(null,"SELECT 'Rem deserunt labore dolor aper' as nome_colab_tabela,'Dolor consectetur sed ut strac' as contacto_colab_tabela,'Amet sit stract rem consectetu' as n_tarefas_colab_tabela,'Natus adipiscing iste aliqua a' as n_atendimento_colab_tabela,'Lorem totam sit mollit labore' as media_tempo_colab_tabela,'Elit aperiam officia amet moll' as ranking_colab_tabela,'Omnis ipsum doloremque consect' as percentagem_colab_tabela,'Stract unde voluptatem aliqua' as foto_colab_tabela,'Lorem aliqua iste sit natus' as param_colab_tabela,'1' as p_id_c "));
		model.loadTable_estatistica(Core.query(null,"SELECT 'Doloremque elit rem anim deser' as n_processo_estat_tabela,'Perspiciatis elit rem omnis el' as tipo_estatistica_tabela,'Sit aliqua elit aliqua sed' as desc_tarefa_estat_tabela,'Dolor amet sed rem consectetur' as data_entrada_estat_tabela,'Dolor labore rem voluptatem ma' as data_conclusao_estat_tabela,'1' as p_id_e "));
		model.loadTable_minhas_tarefas(Core.query(null,"SELECT 'Aperiam lorem stract rem elit' as n_tarefa_m,'Magna totam aliqua mollit sed' as tipo_tabela_minhas_tarefas,'Dolor labore dolor adipiscing' as desc_tarefa_tabela_minhas_tarefas,'Dolor anim unde doloremque nat' as atribuido_por_tabela_minhas_tarefas,'Perspiciatis doloremque dolor' as data_entrada_tabela_minhas_tarefas,'03-08-2011' as data_fim_m,'Iste dolor accusantium dolor d' as espera_tabela_minhas_tarefas,'1' as id "));
		model.loadTable_gerir_tarefas(Core.query(null,"SELECT 'Mollit dolor sed amet doloremq' as numero_processo_tabela,'Labore sit rem sed aliqua' as n_tarefa_g,'Doloremque consectetur laudant' as tipo,'Unde aliqua adipiscing laudant' as desc_tarefa,'Natus totam amet officia ut' as atribuido_por,'Aperiam amet labore omnis dolo' as atribuido_a,'Officia anim consectetur totam' as data_entrada,'06-03-2010' as data_fim_g,'1' as p_id_g "));
		view.tipo_processo_form_disponiveis.setQuery(Core.query(null,"SELECT 'id' as ID,'name' as NAME "));
		view.prioridade_form_disponiveis.setQuery(Core.query(null,"SELECT 'id' as ID,'name' as NAME "));
		view.tipo_processo_minhas_tarefas.setQuery(Core.query(null,"SELECT 'id' as ID,'name' as NAME "));
		view.prioridade_minhas_tarefas.setQuery(Core.query(null,"SELECT 'id' as ID,'name' as NAME "));
		view.tipo_processo_gerir_tarefa.setQuery(Core.query(null,"SELECT 'id' as ID,'name' as NAME "));
		view.prioridade_gerir_tarefa.setQuery(Core.query(null,"SELECT 'id' as ID,'name' as NAME "));
		view.tipo_processo_colaborador.setQuery(Core.query(null,"SELECT 'id' as ID,'name' as NAME "));
		view.prioridade_colaborador.setQuery(Core.query(null,"SELECT 'id' as ID,'name' as NAME "));
		view.tipo_etapa_colaborador.setQuery(Core.query(null,"SELECT 'id' as ID,'name' as NAME "));
		view.tipo_processo_estatistica.setQuery(Core.query(null,"SELECT 'id' as ID,'name' as NAME "));
		view.prioridade_estatistica.setQuery(Core.query(null,"SELECT 'id' as ID,'name' as NAME "));
		view.estado_estatistica.setQuery(Core.query(null,"SELECT 'id' as ID,'name' as NAME "));
		  ----#gen-example */
		/*----#start-code(index)----*/

		this.showTabManage(view, false);// hide tab when user is not manager

		TaskService objTask = new TaskService();

		List<ExecucaoTarefas.Table_gerir_tarefas> taskManage = this.getTaskManage(model, view, objTask);

		List<ExecucaoTarefas.Table_minhas_tarefas> myTasks = this.getMyTasks(model, view, objTask);

		List<ExecucaoTarefas.Table_disponiveis> tasksDisponiveis = this.getAvailableTask(model, view, objTask);

		view.table_gerir_tarefas.addData(taskManage);
		view.table_disponiveis.addData(tasksDisponiveis);
		view.table_minhas_tarefas.addData(myTasks);
		view.prioridade_colaborador.setValue(listPrioridade);
		view.prioridade_estatistica.setValue(listPrioridade);
		view.prioridade_minhas_tarefas.setValue(listPrioridade);
		view.prioridade_form_disponiveis.setValue(listPrioridade);
		view.prioridade_gerir_tarefa.setValue(listPrioridade);
		view.tipo_processo_colaborador.setValue(listProc);
		view.tipo_processo_form_disponiveis.setValue(listProc);
		view.tipo_processo_estatistica.setValue(listProc);
		view.tipo_processo_gerir_tarefa.setValue(listProc);
		view.tipo_processo_minhas_tarefas.setValue(listProc);

		view.btn_pesquisar_button_disponiveis.addParameter("btn_search", AVAILABLE).setLink("index");
		view.btn_pesquisar_button_minhas_tarefas.addParameter("btn_search", MY_TASK).setLink("index");
		view.btn_pesquisar_colaborador.addParameter("btn_search", CONTRIBUTOR).setLink("index");
		view.btn_pesquisar_estatistica.addParameter("btn_search", STATISTIC).setLink("index");
		view.btn_pesquisar_tarefa.addParameter("btn_search", MANAGE_TASK).setLink("index");
		view.btn_pesquisar_tarefa.setTarget("submit_ajax");

		view.btn_detalhes_minha_tarefa.setVisible(false);
		view.btn_detalhes_tarefa.setVisible(false);
		view.numero_processo_tabela.setVisible(false);
		view.btn_alterar_prioridade_tarefa.setVisible(false);

		/*----#end-code----*/
		view.setModel(model);
		return this.renderView(view);
	}

	public Response actionPesquisar_button_disponiveis()
			throws IOException, IllegalArgumentException, IllegalAccessException {
		ExecucaoTarefas model = new ExecucaoTarefas();
		model.load();
		/*----#gen-example
		  EXAMPLES COPY/PASTE:
		  INFO: Core.query(null,... change 'null' to your db connection name, added in Application Builder.
		 this.addQueryString("p_id","12"); //to send a query string in the URL
		 this.addQueryString("p_p_id_d",Core.getParam("p_p_id_d"));
		 this.addQueryString("p_p_id_c",Core.getParam("p_p_id_c"));
		 this.addQueryString("p_p_id_e",Core.getParam("p_p_id_e"));
		 this.addQueryString("p_id",Core.getParam("p_id"));
		 this.addQueryString("p_p_id_g",Core.getParam("p_p_id_g"));
		 return this.forward("igrp","ExecucaoTarefas","index", this.queryString()); //if submit, loads the values  ----#gen-example */
		/*----#start-code(pesquisar_button_disponiveis)----*/

		/*----#end-code----*/
		return this.redirect("igrp", "ExecucaoTarefas", "index", this.queryString());
	}

	public Response actionPesquisar_button_minhas_tarefas()
			throws IOException, IllegalArgumentException, IllegalAccessException {
		ExecucaoTarefas model = new ExecucaoTarefas();
		model.load();
		/*----#gen-example
		  EXAMPLES COPY/PASTE:
		  INFO: Core.query(null,... change 'null' to your db connection name, added in Application Builder.
		 this.addQueryString("p_id","12"); //to send a query string in the URL
		 this.addQueryString("p_p_id_d",Core.getParam("p_p_id_d"));
		 this.addQueryString("p_p_id_c",Core.getParam("p_p_id_c"));
		 this.addQueryString("p_p_id_e",Core.getParam("p_p_id_e"));
		 this.addQueryString("p_id",Core.getParam("p_id"));
		 this.addQueryString("p_p_id_g",Core.getParam("p_p_id_g"));
		 return this.forward("igrp","ExecucaoTarefas","index", this.queryString()); //if submit, loads the values  ----#gen-example */
		/*----#start-code(pesquisar_button_minhas_tarefas)----*/

		/*----#end-code----*/
		return this.redirect("igrp", "ExecucaoTarefas", "index", this.queryString());
	}

	public Response actionPesquisar_tarefa() throws IOException, IllegalArgumentException, IllegalAccessException {
		ExecucaoTarefas model = new ExecucaoTarefas();
		model.load();
		/*----#gen-example
		  EXAMPLES COPY/PASTE:
		  INFO: Core.query(null,... change 'null' to your db connection name, added in Application Builder.
		 this.addQueryString("p_id","12"); //to send a query string in the URL
		 this.addQueryString("p_p_id_d",Core.getParam("p_p_id_d"));
		 this.addQueryString("p_p_id_c",Core.getParam("p_p_id_c"));
		 this.addQueryString("p_p_id_e",Core.getParam("p_p_id_e"));
		 this.addQueryString("p_id",Core.getParam("p_id"));
		 this.addQueryString("p_p_id_g",Core.getParam("p_p_id_g"));
		 return this.forward("igrp","ExecucaoTarefas","index", this.queryString()); //if submit, loads the values  ----#gen-example */
		/*----#start-code(pesquisar_tarefa)----*/

		/*----#end-code----*/
		return this.redirect("igrp", "ExecucaoTarefas", "index", this.queryString());
	}

	public Response actionPesquisar_colaborador() throws IOException, IllegalArgumentException, IllegalAccessException {
		ExecucaoTarefas model = new ExecucaoTarefas();
		model.load();
		/*----#gen-example
		  EXAMPLES COPY/PASTE:
		  INFO: Core.query(null,... change 'null' to your db connection name, added in Application Builder.
		 this.addQueryString("p_id","12"); //to send a query string in the URL
		 this.addQueryString("p_p_id_d",Core.getParam("p_p_id_d"));
		 this.addQueryString("p_p_id_c",Core.getParam("p_p_id_c"));
		 this.addQueryString("p_p_id_e",Core.getParam("p_p_id_e"));
		 this.addQueryString("p_id",Core.getParam("p_id"));
		 this.addQueryString("p_p_id_g",Core.getParam("p_p_id_g"));
		 return this.forward("igrp","ExecucaoTarefas","index", this.queryString()); //if submit, loads the values  ----#gen-example */
		/*----#start-code(pesquisar_colaborador)----*/

		/*----#end-code----*/
		return this.redirect("igrp", "ExecucaoTarefas", "index", this.queryString());
	}

	public Response actionPesquisar_estatistica() throws IOException, IllegalArgumentException, IllegalAccessException {
		ExecucaoTarefas model = new ExecucaoTarefas();
		model.load();
		/*----#gen-example
		  EXAMPLES COPY/PASTE:
		  INFO: Core.query(null,... change 'null' to your db connection name, added in Application Builder.
		 this.addQueryString("p_id","12"); //to send a query string in the URL
		 this.addQueryString("p_p_id_d",Core.getParam("p_p_id_d"));
		 this.addQueryString("p_p_id_c",Core.getParam("p_p_id_c"));
		 this.addQueryString("p_p_id_e",Core.getParam("p_p_id_e"));
		 this.addQueryString("p_id",Core.getParam("p_id"));
		 this.addQueryString("p_p_id_g",Core.getParam("p_p_id_g"));
		 return this.forward("igrp","ExecucaoTarefas","index", this.queryString()); //if submit, loads the values  ----#gen-example */
		/*----#start-code(pesquisar_estatistica)----*/

		/*----#end-code----*/
		return this.redirect("igrp", "ExecucaoTarefas", "index", this.queryString());
	}

	public Response actionAssumir_button_tabela() throws IOException, IllegalArgumentException, IllegalAccessException {
		ExecucaoTarefas model = new ExecucaoTarefas();
		model.load();
		/*----#gen-example
		  EXAMPLES COPY/PASTE:
		  INFO: Core.query(null,... change 'null' to your db connection name, added in Application Builder.
		 this.addQueryString("p_id","12"); //to send a query string in the URL
		 this.addQueryString("p_p_id_d",Core.getParam("p_p_id_d"));
		 this.addQueryString("p_p_id_c",Core.getParam("p_p_id_c"));
		 this.addQueryString("p_p_id_e",Core.getParam("p_p_id_e"));
		 this.addQueryString("p_id",Core.getParam("p_id"));
		 this.addQueryString("p_p_id_g",Core.getParam("p_p_id_g"));
		 return this.forward("igrp","ExecucaoTarefas","index", this.queryString()); //if submit, loads the values  ----#gen-example */
		/*----#start-code(assumir_button_tabela)----*/
		String id = Core.getParam("p_p_id_d");
		if (Core.isNotNull(id) && new TaskService().claimTask(id, Core.getCurrentUser().getUser_name())) {
			Core.setMessageSuccess(Core.gt("Tarefa assumido com sucesso"));
		} else {
			Core.setMessageError();
		}
		/*----#end-code----*/
		return this.redirect("igrp", "ExecucaoTarefas", "index", this.queryString());
	}

	public Response actionVer_detalhes() throws IOException, IllegalArgumentException, IllegalAccessException {
		ExecucaoTarefas model = new ExecucaoTarefas();
		model.load();
		/*----#gen-example
		  EXAMPLES COPY/PASTE:
		  INFO: Core.query(null,... change 'null' to your db connection name, added in Application Builder.
		 this.addQueryString("p_id","12"); //to send a query string in the URL
		 this.addQueryString("p_p_id_d",Core.getParam("p_p_id_d"));
		 this.addQueryString("p_p_id_c",Core.getParam("p_p_id_c"));
		 this.addQueryString("p_p_id_e",Core.getParam("p_p_id_e"));
		 this.addQueryString("p_id",Core.getParam("p_id"));
		 this.addQueryString("p_p_id_g",Core.getParam("p_p_id_g"));
		 return this.forward("igrp","Transferir_tarefas","index", this.queryString()); //if submit, loads the values  ----#gen-example */
		/*----#start-code(ver_detalhes)----*/
		this.addQueryString("p_id", Core.getParam("p_id_g")).addQueryString("type", "view");
		/*----#end-code----*/
		return this.redirect("igrp", "Transferir_tarefas", "index", this.queryString());
	}

	public Response actionVer_estatistica() throws IOException, IllegalArgumentException, IllegalAccessException {
		ExecucaoTarefas model = new ExecucaoTarefas();
		model.load();
		/*----#gen-example
		  EXAMPLES COPY/PASTE:
		  INFO: Core.query(null,... change 'null' to your db connection name, added in Application Builder.
		 this.addQueryString("p_id","12"); //to send a query string in the URL
		 this.addQueryString("p_p_id_d",Core.getParam("p_p_id_d"));
		 this.addQueryString("p_p_id_c",Core.getParam("p_p_id_c"));
		 this.addQueryString("p_p_id_e",Core.getParam("p_p_id_e"));
		 this.addQueryString("p_id",Core.getParam("p_id"));
		 this.addQueryString("p_p_id_g",Core.getParam("p_p_id_g"));
		 return this.forward("igrp","Transferir_tarefas","index", this.queryString()); //if submit, loads the values  ----#gen-example */
		/*----#start-code(ver_estatistica)----*/
		this.addQueryString("p_id", Core.getParam("p_id_c"));
		/*----#end-code----*/
		return this.redirect("igrp", "Transferir_tarefas", "index", this.queryString());
	}

	public Response actionEnviar_msg() throws IOException, IllegalArgumentException, IllegalAccessException {
		ExecucaoTarefas model = new ExecucaoTarefas();
		model.load();
		/*----#gen-example
		  EXAMPLES COPY/PASTE:
		  INFO: Core.query(null,... change 'null' to your db connection name, added in Application Builder.
		 this.addQueryString("p_id","12"); //to send a query string in the URL
		 this.addQueryString("p_p_id_d",Core.getParam("p_p_id_d"));
		 this.addQueryString("p_p_id_c",Core.getParam("p_p_id_c"));
		 this.addQueryString("p_p_id_e",Core.getParam("p_p_id_e"));
		 this.addQueryString("p_id",Core.getParam("p_id"));
		 this.addQueryString("p_p_id_g",Core.getParam("p_p_id_g"));
		 return this.forward("igrp","Transferir_tarefas","index", this.queryString()); //if submit, loads the values  ----#gen-example */
		/*----#start-code(enviar_msg)----*/
		this.addQueryString("p_id", Core.getParam("p_id_g"));
		/*----#end-code----*/
		return this.redirect("igrp", "Transferir_tarefas", "index", this.queryString());
	}

	public Response actionExecutar_button_minha_tarefas()
			throws IOException, IllegalArgumentException, IllegalAccessException {
		ExecucaoTarefas model = new ExecucaoTarefas();
		model.load();
		/*----#gen-example
		  EXAMPLES COPY/PASTE:
		  INFO: Core.query(null,... change 'null' to your db connection name, added in Application Builder.
		 this.addQueryString("p_id","12"); //to send a query string in the URL
		 this.addQueryString("p_p_id_d",Core.getParam("p_p_id_d"));
		 this.addQueryString("p_p_id_c",Core.getParam("p_p_id_c"));
		 this.addQueryString("p_p_id_e",Core.getParam("p_p_id_e"));
		 this.addQueryString("p_id",Core.getParam("p_id"));
		 this.addQueryString("p_p_id_g",Core.getParam("p_p_id_g"));
		 return this.forward("igrp","ExecucaoTarefas","index", this.queryString()); //if submit, loads the values  ----#gen-example */
		/*----#start-code(executar_button_minha_tarefas)----*/
		String id = Core.getParam("p_id");
		if (Core.isNotNull(id)) {
			TaskService task = new TaskService().getTask(id);
			if (task != null) {
				List<HistoricTaskService> hts = new HistoricTaskService()
						.getHistoryOfProccessInstanceId(task.getProcessInstanceId());
				hts = hts.stream().filter(h -> !h.getTaskDefinitionKey().equals(task.getTaskDefinitionKey()))
						.collect(Collectors.toList());
				String previewTask = (hts != null && hts.size() > 0) ? hts.get(hts.size() - 1).getTaskDefinitionKey()
						: "";
				String preiviewProcessDefinition = (hts != null && hts.size() > 0)
						? hts.get(hts.size() - 1).getProcessDefinitionId()
						: "";
				String preiviewApp = (hts != null && hts.size() > 0) ? hts.get(hts.size() - 1).getTenantId() : "";
				String previewTaskId = (hts != null && hts.size() > 0) ? hts.get(hts.size() - 1).getId() : "";
				Application app = new Application().findByDad(task.getTenantId());
				if (app != null) {
					this.addQueryString("taskId", id).addQueryString("appId", app.getId())
							.addQueryString("appDad", app.getDad()).addQueryString("formKey", task.getFormKey())
							.addQueryString("processDefinition", task.getProcessDefinitionKey())
							.addQueryString("processDefinitionId", task.getProcessDefinitionId())
							.addQueryString("taskDefinition", task.getTaskDefinitionKey())
							.addQueryString("previewTask", previewTask).addQueryString("preiviewApp", preiviewApp)
							.addQueryString("preiviewProcessDefinition", preiviewProcessDefinition)
							.addQueryString("showTimeLine", "true").addQueryString("previewTaskId", previewTaskId);
					return this.redirect(app.getDad().toLowerCase(),
							this.config.PREFIX_TASK_NAME + task.getTaskDefinitionKey(), "index", this.queryString());
				}
			}
			return this.redirect("igrp", "ExecucaoTarefas", "index");
		}
		// return this.redirect("igrp", "ErrorPage", "exception");
		/*----#end-code----*/
		return this.redirect("igrp", "ExecucaoTarefas", "index", this.queryString());
	}

	public Response actionDetalhes_minha_tarefa() throws IOException, IllegalArgumentException, IllegalAccessException {
		ExecucaoTarefas model = new ExecucaoTarefas();
		model.load();
		/*----#gen-example
		  EXAMPLES COPY/PASTE:
		  INFO: Core.query(null,... change 'null' to your db connection name, added in Application Builder.
		 this.addQueryString("p_id","12"); //to send a query string in the URL
		 this.addQueryString("p_p_id_d",Core.getParam("p_p_id_d"));
		 this.addQueryString("p_p_id_c",Core.getParam("p_p_id_c"));
		 this.addQueryString("p_p_id_e",Core.getParam("p_p_id_e"));
		 this.addQueryString("p_id",Core.getParam("p_id"));
		 this.addQueryString("p_p_id_g",Core.getParam("p_p_id_g"));
		 return this.forward("igrp","ExecucaoTarefas","index", this.queryString()); //if submit, loads the values  ----#gen-example */
		/*----#start-code(detalhes_minha_tarefa)----*/
		String id = Core.getParam("p_id");
		if (Core.isNotNull(id)) {
			this.addQueryString("taskId", id).addQueryString("target", "_blank");
			return this.redirect("igrp", "Detalhes_tarefas", "index", this.queryString());
		}
		/*----#end-code----*/
		return this.redirect("igrp", "ExecucaoTarefas", "index", this.queryString());
	}

	public Response actionDetalhes_processos_button_minha_tarefas()
			throws IOException, IllegalArgumentException, IllegalAccessException {
		ExecucaoTarefas model = new ExecucaoTarefas();
		model.load();
		/*----#gen-example
		  EXAMPLES COPY/PASTE:
		  INFO: Core.query(null,... change 'null' to your db connection name, added in Application Builder.
		 this.addQueryString("p_id","12"); //to send a query string in the URL
		 this.addQueryString("p_p_id_d",Core.getParam("p_p_id_d"));
		 this.addQueryString("p_p_id_c",Core.getParam("p_p_id_c"));
		 this.addQueryString("p_p_id_e",Core.getParam("p_p_id_e"));
		 this.addQueryString("p_id",Core.getParam("p_id"));
		 this.addQueryString("p_p_id_g",Core.getParam("p_p_id_g"));
		 return this.forward("igrp","DetalhesProcesso","index", this.queryString()); //if submit, loads the values  ----#gen-example */
		/*----#start-code(detalhes_processos_button_minha_tarefas)----*/
		this.addQueryString("taskId", Core.getParam("p_id")).addQueryString("target", "_blank");
		/*----#end-code----*/
		return this.redirect("igrp", "DetalhesProcesso", "index", this.queryString());
	}

	public Response actionLeberar_tarefa_button_minha_tarefas()
			throws IOException, IllegalArgumentException, IllegalAccessException {
		ExecucaoTarefas model = new ExecucaoTarefas();
		model.load();
		/*----#gen-example
		  EXAMPLES COPY/PASTE:
		  INFO: Core.query(null,... change 'null' to your db connection name, added in Application Builder.
		 this.addQueryString("p_id","12"); //to send a query string in the URL
		 this.addQueryString("p_p_id_d",Core.getParam("p_p_id_d"));
		 this.addQueryString("p_p_id_c",Core.getParam("p_p_id_c"));
		 this.addQueryString("p_p_id_e",Core.getParam("p_p_id_e"));
		 this.addQueryString("p_id",Core.getParam("p_id"));
		 this.addQueryString("p_p_id_g",Core.getParam("p_p_id_g"));
		 return this.forward("igrp","ExecucaoTarefas","index", this.queryString()); //if submit, loads the values  ----#gen-example */
		/*----#start-code(leberar_tarefa_button_minha_tarefas)----*/
		String id = Core.getParam("p_id");
		if (Core.isNotNull(id) && new TaskService().freeTask(id)) {
			Core.setMessageSuccess(Core.gt("Tarefa liberada com sucesso"));
		} else {
			Core.setMessageError();
		}
		// return this.redirect("igrp","ExecucaoTarefas","index");
		/*----#end-code----*/
		return this.redirect("igrp", "ExecucaoTarefas", "index", this.queryString());
	}

	public Response actionTransferir_tarefa() throws IOException, IllegalArgumentException, IllegalAccessException {
		ExecucaoTarefas model = new ExecucaoTarefas();
		model.load();
		/*----#gen-example
		  EXAMPLES COPY/PASTE:
		  INFO: Core.query(null,... change 'null' to your db connection name, added in Application Builder.
		 this.addQueryString("p_id","12"); //to send a query string in the URL
		 this.addQueryString("p_p_id_d",Core.getParam("p_p_id_d"));
		 this.addQueryString("p_p_id_c",Core.getParam("p_p_id_c"));
		 this.addQueryString("p_p_id_e",Core.getParam("p_p_id_e"));
		 this.addQueryString("p_id",Core.getParam("p_id"));
		 this.addQueryString("p_p_id_g",Core.getParam("p_p_id_g"));
		 return this.forward("igrp","Transferir_tarefas","index", this.queryString()); //if submit, loads the values  ----#gen-example */
		/*----#start-code(transferir_tarefa)----*/
		this.addQueryString("p_id", Core.getParam("p_id_g"));
		/*----#end-code----*/
		return this.redirect("igrp", "Transferir_tarefas", "index", this.queryString());
	}

	public Response actionDetalhes_tarefa() throws IOException, IllegalArgumentException, IllegalAccessException {
		ExecucaoTarefas model = new ExecucaoTarefas();
		model.load();
		/*----#gen-example
		  EXAMPLES COPY/PASTE:
		  INFO: Core.query(null,... change 'null' to your db connection name, added in Application Builder.
		 this.addQueryString("p_id","12"); //to send a query string in the URL
		 this.addQueryString("p_p_id_d",Core.getParam("p_p_id_d"));
		 this.addQueryString("p_p_id_c",Core.getParam("p_p_id_c"));
		 this.addQueryString("p_p_id_e",Core.getParam("p_p_id_e"));
		 this.addQueryString("p_id",Core.getParam("p_id"));
		 this.addQueryString("p_p_id_g",Core.getParam("p_p_id_g"));
		 return this.forward("igrp","ExecucaoTarefas","index", this.queryString()); //if submit, loads the values  ----#gen-example */
		/*----#start-code(detalhes_tarefa)----*/
		String id = Core.getParam("p_p_id_g");
		if (Core.isNotNull(id)) {
			this.addQueryString("taskId", id).addQueryString("target", "_blank");
			return this.redirect("igrp", "Detalhes_tarefas", "index", this.queryString());
		}
		/*----#end-code----*/
		return this.redirect("igrp", "ExecucaoTarefas", "index", this.queryString());
	}

	public Response actionDetalhes_processo() throws IOException, IllegalArgumentException, IllegalAccessException {
		ExecucaoTarefas model = new ExecucaoTarefas();
		model.load();
		/*----#gen-example
		  EXAMPLES COPY/PASTE:
		  INFO: Core.query(null,... change 'null' to your db connection name, added in Application Builder.
		 this.addQueryString("p_id","12"); //to send a query string in the URL
		 this.addQueryString("p_p_id_d",Core.getParam("p_p_id_d"));
		 this.addQueryString("p_p_id_c",Core.getParam("p_p_id_c"));
		 this.addQueryString("p_p_id_e",Core.getParam("p_p_id_e"));
		 this.addQueryString("p_id",Core.getParam("p_id"));
		 this.addQueryString("p_p_id_g",Core.getParam("p_p_id_g"));
		 return this.forward("igrp","DetalhesProcesso","index", this.queryString()); //if submit, loads the values  ----#gen-example */
		/*----#start-code(detalhes_processo)----*/
		this.addQueryString("taskId", Core.getParam("p_p_id_g")).addQueryString("target", "_blank");
		/*----#end-code----*/
		return this.redirect("igrp", "DetalhesProcesso", "index", this.queryString());
	}

	public Response actionAlterar_prioridade_tarefa()
			throws IOException, IllegalArgumentException, IllegalAccessException {
		ExecucaoTarefas model = new ExecucaoTarefas();
		model.load();
		/*----#gen-example
		  EXAMPLES COPY/PASTE:
		  INFO: Core.query(null,... change 'null' to your db connection name, added in Application Builder.
		 this.addQueryString("p_id","12"); //to send a query string in the URL
		 this.addQueryString("p_p_id_d",Core.getParam("p_p_id_d"));
		 this.addQueryString("p_p_id_c",Core.getParam("p_p_id_c"));
		 this.addQueryString("p_p_id_e",Core.getParam("p_p_id_e"));
		 this.addQueryString("p_id",Core.getParam("p_id"));
		 this.addQueryString("p_p_id_g",Core.getParam("p_p_id_g"));
		 return this.forward("igrp","Alter_prioridade_tarefa","index", this.queryString()); //if submit, loads the values  ----#gen-example */
		/*----#start-code(alterar_prioridade_tarefa)----*/
		this.addQueryString("p_id", Core.getParam("p_id_g"));
		/*----#end-code----*/
		return this.redirect("igrp", "Alter_prioridade_tarefa", "index", this.queryString());
	}

	/*----#start-code(custom_actions)----*/

	public Response actionProcessTask() throws IOException, ServletException {
		String taskId = Core.getParam("p_prm_taskid");
		String processDefinitionId = Core.getParam("p_prm_definitionid");
		if (Core.isNotNull(taskId)) {
			return this.processTask(taskId);
		}
		if (Core.isNotNull(processDefinitionId)) {
			return this.startProccess(processDefinitionId);
		}
		return this.redirect("igrp", "ErrorPage", "exception");
	}

	/**
	 * Start proccess
	 * @param processDefinitionId
	 * @return
	 */
	private Response startProccess(String processDefinitionId) {
		this.addQueryString("processDefinitionId", processDefinitionId);
		processDefinitionId = processDefinitionId.substring(0,processDefinitionId.indexOf(":"));
		return this.call(Core.getCurrentDad(), "TaskStart"+processDefinitionId, "save", this.queryString());
	}

	/**
	 * Execute task
	 * @param taskId
	 * @return
	 */
	private Response processTask(String taskId) {
		TaskService task = new TaskService().getTask(taskId);
		this.addQueryString("taskId", taskId);
		Application app = new Application().findByDad(task.getTenantId());
		return this.call(app.getDad().toLowerCase(), task.getTaskDefinitionKey(), "save", this.queryString());
	}

	private void applyFiler(TaskService objTask, ExecucaoTarefas model) {
		String proc_tp = null, num_proc = null, status = null, data_inicio = null, data_fim = null, prioridade = null;
		int btn_search = Core.getParamInt("btn_search");
		switch (btn_search) {
		case AVAILABLE:
			proc_tp = model.getTipo_processo_form_disponiveis();
			num_proc = model.getNumero_processo_form_disponiveis();
			data_inicio = model.getData_inicio_form_disponiveis();
			data_fim = model.getData_fim_form_disponiveis();
			prioridade = model.getPrioridade_form_disponiveis();
			break;
		case CONTRIBUTOR:
			proc_tp = model.getTipo_processo_colaborador();
			num_proc = model.getNumero_processo_colaborador();
			data_inicio = model.getData_inicio_colaborador();
			data_fim = model.getData_fim_colaborador();
			prioridade = model.getPrioridade_colaborador();
			break;
		case MANAGE_TASK:
			proc_tp = model.getTipo_processo_gerir_tarefa();
			num_proc = model.getNumero_processo_gerir_tarefa();
			data_inicio = model.getData_inicio_gerir_tarefa();
			data_fim = model.getData_fim_gerir_tarefa();
			prioridade = model.getPrioridade_gerir_tarefa();
			break;
		case MY_TASK:
			proc_tp = model.getTipo_processo_minhas_tarefas();
			num_proc = model.getNumero_processo_minhas_tarefas();
			data_inicio = model.getData_inicio_minhas_tarefas();
			data_fim = model.getData_fim_minhas_tarefas();
			prioridade = model.getPrioridade_minhas_tarefas();
			break;
		case STATISTIC:
			status = model.getEstado_estatistica();
			proc_tp = model.getTipo_processo_estatistica();
			num_proc = model.getNumero_processo_estatistica();
			data_inicio = model.getData_inicio_estatistica();
			data_fim = model.getData_fim_estatistica();
			prioridade = model.getPrioridade_estatistica();
			break;
		}
		if (Core.isNotNull(proc_tp)) {
			objTask.addFilter("processDefinitionId", proc_tp);
		}
		if (Core.isNotNull(num_proc)) {
			objTask.addFilter("processInstanceId", num_proc);
		}
		if (Core.isNotNull(status)) {
			objTask.addFilter("finished", status);
		}
		if (Core.isNotNull(data_inicio)) {
			objTask.addFilter("taskCompletedAfter",
					Core.ToChar(Core.ToChar(data_inicio, "dd-MM-yyyy", "yyyy-MM-dd"), "yyyy-MM-dd'T'HH:mm:ss'Z'"));
		}
		if (Core.isNotNull(data_fim)) {
			objTask.addFilter("taskCompletedBefore",
					Core.ToChar(Core.ToChar(data_fim, "dd-MM-yyyy", "yyyy-MM-dd"), "yyyy-MM-dd'T'HH:mm:ss'Z'"));
		}
		if (Core.isNotNull(prioridade)) {
			objTask.addFilter("taskPriority", prioridade);
		}
	}

	private void showTabManage(ExecucaoTarefasView view, boolean isVisible) {
		view.gerir_tarefas.setVisible(isVisible);
		;
		view.colaboradores.setVisible(isVisible);
	}

	private static Map<String, String> listPrioridade = new HashMap<String, String>();
	static {
		listPrioridade.put(null, Core.gt("-- Escolher Prioridade --"));
		listPrioridade.put("100", "Urgente");
		listPrioridade.put("50", "Médio");
		listPrioridade.put("0", "Normal");
	}

	// Get tasks for user manager
	private List<Table_gerir_tarefas> getTaskManage(ExecucaoTarefas model, ExecucaoTarefasView view,
			TaskService objTask) {
		objTask.setFilter("");// clean filter
		this.applyFiler(objTask, model); // apply new filter
		List<Table_gerir_tarefas> taskManage = new ArrayList<>();
		// Verifica se é perfil pai
		if (ProfileType.isPerfilPai()) {
			for (TaskService task : objTask.getMabageTasks()) {
				ExecucaoTarefas.Table_gerir_tarefas t = new ExecucaoTarefas.Table_gerir_tarefas();
				t.setAtribuido_a(task.getAssignee());
				t.setAtribuido_por(task.getOwner());
				t.setData_entrada(task.getCreateTime().toString());
				t.setDesc_tarefa(task.getDescription() != null ? task.getDescription() : task.getName());
				t.setNumero_processo_tabela(task.getProcessDefinitionId());
				t.setP_id_g(task.getId());
				t.setN_tarefa_g(task.getProcessInstanceId());
				t.setTipo(this.listProc.get(task.getProcessDefinitionId()));
				t.setData_fim_g(task.getDueDate() != null ? task.getDueDate().toString() : "");
				taskManage.add(t);
			}
			this.showTabManage(view, true);// show tab when user is manager
		}
		return taskManage;
	}

	// Get all tasks of current user
	private List<Table_minhas_tarefas> getMyTasks(ExecucaoTarefas model, ExecucaoTarefasView view,
			TaskService objTask) {
		objTask.setFilter("");// clean filter
		this.applyFiler(objTask, model); // apply new filter
		List<Table_minhas_tarefas> myTasks = new ArrayList<>();
		for (TaskService task : objTask.getMyTasks()) {
			ExecucaoTarefas.Table_minhas_tarefas t = new ExecucaoTarefas.Table_minhas_tarefas();
			t.setAtribuido_por_tabela_minhas_tarefas(task.getOwner());
			t.setData_entrada_tabela_minhas_tarefas(task.getCreateTime().toString());
			t.setDesc_tarefa_tabela_minhas_tarefas(
					task.getDescription() != null ? task.getDescription() : task.getName());
			t.setTipo_tabela_minhas_tarefas(listProc.get(task.getProcessDefinitionId()));
			t.setId(task.getId());
			t.setN_tarefa_m(task.getProcessInstanceId());
			t.setData_fim_m(task.getDueDate() != null ? task.getDueDate().toString() : "");
			myTasks.add(t);
		}
		return myTasks;
	}

	// Get all available task
	private List<Table_disponiveis> getAvailableTask(ExecucaoTarefas model, ExecucaoTarefasView view,
			TaskService objTask) {
		objTask.setFilter("");// clean filter
		this.applyFiler(objTask, model); // apply new filter
		List<Table_disponiveis> tasksDisponiveis = new ArrayList<>();
		for (TaskService task : objTask.getAvailableTasks()) {
			ExecucaoTarefas.Table_disponiveis t = new ExecucaoTarefas.Table_disponiveis();
			t.setCategorias_processo_tabela_disponiveis(task.getProcessDefinitionKey());
			t.setData_entrada_tabela_disponiveis(task.getCreateTime().toString());
			t.setP_id_d(task.getId());
			t.setN_tarefa_d(task.getProcessInstanceId());
			t.setData_fim_d(task.getDueDate() != null ? task.getDueDate().toString() : "");
			t.setTarefas_tabela_disponiveis(task.getDescription() != null ? task.getDescription() : task.getName());
			tasksDisponiveis.add(t);
		}
		return tasksDisponiveis;
	}

	private Map<String, String> listProc = new ProcessDefinitionService().mapToComboBox(Core.getCurrentDad());
	private static final int MANAGE_TASK = 0;
	private static final int CONTRIBUTOR = 1;
	private static final int STATISTIC = 2;
	private static final int MY_TASK = 3;
	private static final int AVAILABLE = 4;
	/*----#end-code----*/
}
