
package nosi.webapps.igrp_studio.pages.partilhageral;

import nosi.core.webapp.Controller;
import java.io.IOException;
import nosi.core.webapp.Core;

import static nosi.core.i18n.Translator.gt;
import nosi.core.webapp.Response;
import nosi.core.webapp.databse.helpers.QueryHelper;

/*----#start-code(packages_import)----*/
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

import nosi.core.webapp.Igrp;

import nosi.webapps.igrp.dao.Action;
import nosi.webapps.igrp.dao.Application;		
/*----#end-code----*/
import nosi.webapps.igrp.dao.Share;



public class PartilhageralController extends Controller {		

	public Response actionIndex() throws IOException, IllegalArgumentException, IllegalAccessException{
		
		Partilhageral model = new Partilhageral();
		PartilhageralView view = new PartilhageralView();
		model.load();
		
		/*----#gen-example
		This is an example of how you can implement your code:
		
		model.loadTable_1( Core.query( "SELECT 'estado' as estado,'descricao' as descricao " ) );
		view.aplicacao_origem.setSqlQuery(null,"SELECT 'id' as ID,'name' as NAME ");
		view.elemento.setSqlQuery(null,"SELECT 'id' as ID,'name' as NAME ");
		view.aplicacao_destino.setSqlQuery(null,"SELECT 'id' as ID,'name' as NAME ");
		
		----#gen-example */
		
		/*----#start-code(index)----*/
		
		view.elemento.setSqlQuery(null,"(((((SELECT '' as ID,'-- Elemento --' as NAME) union all (SELECT 'PAGE' as ID,'PAGE' as NAME)) union all (SELECT 'WORKFLOW' as ID,'WORKFLOW' as NAME)) "
				+ "union all (SELECT 'SERVICE' as ID,'SERVICE' as NAME)) union all (SELECT 'REPORT' as ID,'REPORT' as NAME))");
		
		view.aplicacao_origem.setValue(new Application().getAllApps());
		view.aplicacao_destino.setSqlQuery(null,"SELECT '' as ID,'-- Selecionar --' as NAME ");
		
		Optional.of(model.getAplicacao_origem()).ifPresent(
				v -> {
					try {
						view.aplicacao_destino.setValue(new Application().getAllAppsByFilterId(Integer.parseInt((v))));
						}
					catch(Exception e) {}
					}
			);
		
		if(Igrp.getInstance().getRequest().getMethod().equalsIgnoreCase("post")) {
			List<Partilhageral.Table_1> t = new ArrayList<Partilhageral.Table_1>();
			
			Optional.of(model.getElemento()).ifPresent(e -> {
				switch(e){
				
					case "PAGE":
						
						List<Action> pages = new ArrayList<Action>();
						try {
							pages = new Action().find().andWhere("application.id", "=", Integer.parseInt(model.getAplicacao_origem())).all();
						}catch(Exception exception) {
							exception.printStackTrace();
						}
						
						List<Share> shares = new ArrayList<Share>();
						try {
							shares= new Share().getAllSharedResources(Integer.parseInt(model.getAplicacao_origem()), Integer.parseInt(model.getAplicacao_destino()), e);
						}catch(Exception exception) {}
						
						for(Action page : pages) {
							
							Partilhageral.Table_1 row = new Partilhageral.Table_1();
							
							row.setEstado(page.getId());
							row.setEstado_check(-1);
							
							for(Share share : shares) {
								//System.out.println((share.getType_fk() == page.getId() && share.getStatus() == 1)); 
								if(share.getType_fk() == page.getId() && share.getStatus() == 1) {
									//System.out.println(share);
									row.setEstado(page.getId()); 
									row.setEstado_check(page.getId());
								}
							}
							row.setDescricao(page.getPage_descr());
							t.add(row);
						}
					break;
					
					case "SERVICE":
					break;
					case "REPORT":
					break;
				}
			});
			
			view.table_1.addData(t);
			
			//return forward("igrp_studio", "Partilha_geral", "index");
		}
		
		/*----#end-code----*/
		
		view.setModel(model);
		
		return this.renderView(view);
	}
	
public Response actionPartilhar() throws IOException, IllegalArgumentException, IllegalAccessException{
		
		Partilhageral model = new Partilhageral();
		model.load();
		
		/*----#gen-example
		This is an example of how you can implement your code: 
		
		if(model.save(model)){
			Core.setMessageSuccess();
		 }else{
			Core.setMessageError();
		 return this.forward("igrp_studio","Partilha_geral","index");
		}
		 
		----#gen-example */
		
		/*----#start-code(partilhar)----*/
		
		if(Igrp.getInstance().getRequest().getMethod().equalsIgnoreCase("POST")) {
			//System.out.println("Entrado ... ");
			sharePage(model);
			return forward("igrp_studio","Partilhageral","index");
		}
		/*----#end-code----*/ 
		return this.redirect("igrp_studio","Partilhageral","index");
	}
	
	/*----#start-code(custom_actions)----*/
		
	private void sharePage(Partilhageral model) {
		List<Share> shares = new ArrayList<Share>();
		try {
			shares = new Share().find().andWhere("env.id", "=", Integer.parseInt(model.getAplicacao_destino()))
					.andWhere("owner.id", "=", Integer.parseInt(model.getAplicacao_origem()))
					.andWhere("type", "=", "PAGE").all();
		}catch(Exception e) {}
		
		List<Share> sharesRemoved = new ArrayList<Share>();
		try {
			sharesRemoved = new Share().find().andWhere("env.id", "=", Integer.parseInt(model.getAplicacao_destino()))
					.andWhere("owner.id", "=", Integer.parseInt(model.getAplicacao_origem()))
					.andWhere("type", "=", "PAGE").all();
		}catch(Exception e) {} 

		for(Share s : sharesRemoved) { // remove all 
			s.setStatus(0);
			s.update();
		}

		if(model.getEstado() != null && model.getEstado().length > 0) {
			boolean flag = false;
			for(String obj : model.getEstado()) {
			try {
			for(Share s : shares) {
				if(new String(s.getType_fk() + "").equals(obj)) {
					s.setStatus(1);
					s = s.update();
					flag = true;
					break;
				}
			}
		if(flag) { flag = false; continue;}

			Share share = new Share();
			Application app1 = new Application();
			app1.setId(Integer.parseInt(model.getAplicacao_origem()));
			Application app2 = new Application();
			app2.setId(Integer.parseInt(model.getAplicacao_destino()));
			share.setOwner(app1);
			share.setEnv(app2);
			share.setStatus(1);
			share.setType("PAGE");
			share.setType_fk(Integer.parseInt(obj));
			share = share.insert();
			
		}catch(Exception e) {
				Core.setMessageError();
				break;
			}
		}
			}
	}
	/*----#end-code----*/
	
}