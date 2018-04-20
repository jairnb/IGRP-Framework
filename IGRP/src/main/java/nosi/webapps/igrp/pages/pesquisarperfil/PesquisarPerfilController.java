
package nosi.webapps.igrp.pages.pesquisarperfil;

import nosi.core.webapp.Controller;
import java.io.IOException;
import nosi.core.webapp.Core;
import nosi.core.webapp.Response;
/*----#start-code(packages_import)----*/
import nosi.core.webapp.Controller;
import nosi.core.webapp.Igrp;
import nosi.core.webapp.Response;
import nosi.webapps.igrp.dao.ProfileType;
import java.io.IOException;
import java.util.ArrayList;
import nosi.core.webapp.Core;
/*----#end-code----*/


public class PesquisarPerfilController extends Controller {		

	public Response actionIndex() throws IOException, IllegalArgumentException, IllegalAccessException{
		
		PesquisarPerfil model = new PesquisarPerfil();
		model.load();
		PesquisarPerfilView view = new PesquisarPerfilView();
		view.id.setParam(true);
		/*----#gen-example
		  This is an example of how you can implement your code:
		  In a .query(null,... change 'null' to your db connection name added in application builder.
		
		model.loadTable_1(Core.query(null,"SELECT 'organica' as organica,'estado' as estado,'descricao' as descricao,'codigo' as codigo,'id' as id "));
		
		
		----#gen-example */
		/*----#start-code(index)----*/
	
		ArrayList<PesquisarPerfil.Table_1> lista = new ArrayList<>();
		ProfileType profile_db = new ProfileType();			
		int idOrg = Core.getParamInt("id_org");
		int idApp = Core.getParamInt("id_app");
		
		//Preenchendo a tabela
		for(ProfileType p:profile_db.find().andWhere("application", "=",idApp!=0?idApp:-1).andWhere("organization", "=",idOrg!=0?idOrg:-1).all()){
			PesquisarPerfil.Table_1 table1 = new PesquisarPerfil.Table_1();
			table1.setCodigo(p.getCode());
			table1.setDescricao(p.getDescr());
			table1.setEstado(p.getStatus());
          	table1.setEstado_check(1);
			if(p.getOrganization()!=null){
				table1.setOrganica(p.getOrganization().getName());
			}
			table1.setId(""+p.getId());
			lista.add(table1);
		}
	     
		view.table_1.addData(lista);
        view.btn_eliminar.setVisible(false);
       view.btn_novo.setLink("igrp","NovoPerfil","index&p_aplicacao="+idApp+"&p_organica="+idOrg);
		/*----#end-code----*/
		view.setModel(model);
		return this.renderView(view);	
	}
	
	public Response actionNovo() throws IOException, IllegalArgumentException, IllegalAccessException{
		
		PesquisarPerfil model = new PesquisarPerfil();
		model.load();
		/*----#gen-example
		  This is an example of how you can implement your code:
		  In a .query(null,... change 'null' to your db connection name added in application builder.
		
		 this.addQueryString("p_id","12"); //to send a query string in the URL
		 return this.forward("igrp","NovoPerfil","index", this.queryString()); //if submit, loads the values
		
		----#gen-example */
		/*----#start-code(novo)----*/
      
      //NOT IN USE. setlink() in actionIndex set because getParam dosent work for a variable and param of other page
      //this.addQueryString("p_aplicacao",Core.getParam("id_app"));
      //this.addQueryString("p_organica",Core.getParam("id_org"));
		/*----#end-code----*/
		return this.redirect("igrp","NovoPerfil","index", this.queryString());	
	}
	
	public Response actionEditar() throws IOException, IllegalArgumentException, IllegalAccessException{
		
		PesquisarPerfil model = new PesquisarPerfil();
		model.load();
		/*----#gen-example
		  This is an example of how you can implement your code:
		  In a .query(null,... change 'null' to your db connection name added in application builder.
		
		 this.addQueryString("p_id","12"); //to send a query string in the URL
		 return this.forward("igrp","NovoPerfil","index", this.queryString()); //if submit, loads the values
		
		----#gen-example */
		/*----#start-code(editar)----*/
      	//String id = Core.getParam("p_id");
		//if(id!=null && !id.equals("")){
 			return this.forward("igrp","NovoPerfil","editar", this.queryString());
		//}
		//return this.redirectError();
		/*----#end-code----*/
			
	}
	
	public Response actionEliminar() throws IOException, IllegalArgumentException, IllegalAccessException{
		
		PesquisarPerfil model = new PesquisarPerfil();
		model.load();
		/*----#gen-example
		  This is an example of how you can implement your code:
		  In a .query(null,... change 'null' to your db connection name added in application builder.
		
		 this.addQueryString("p_id","12"); //to send a query string in the URL
		 return this.forward("igrp","PesquisarPerfil","index", this.queryString()); //if submit, loads the values
		
		----#gen-example */
		/*----#start-code(eliminar)----*/
		String id = Core.getParam("p_id");
        if(id!=null){
			ProfileType p = new ProfileType().findOne(Integer.parseInt(id));
            if(p!=null && p.delete(Integer.parseInt(id))){
              Core.setMessageSuccess();
              return this.redirect("igrp","PesquisarPerfil","index");
            }
        }
		//return this.redirectError();
		/*----#end-code----*/
		return this.redirect("igrp","PesquisarPerfil","index", this.queryString());	
	}
	
	public Response actionMenu() throws IOException, IllegalArgumentException, IllegalAccessException{
		
		PesquisarPerfil model = new PesquisarPerfil();
		model.load();
		/*----#gen-example
		  This is an example of how you can implement your code:
		  In a .query(null,... change 'null' to your db connection name added in application builder.
		
		 this.addQueryString("p_id","12"); //to send a query string in the URL
		 return this.forward("igrp","PesquisarPerfil","index", this.queryString()); //if submit, loads the values
		
		----#gen-example */
		/*----#start-code(menu)----*/
		String id = Core.getParam("p_id");
		 if(id!=null){
		return this.redirect("igrp", "MenuOrganica", "index","id="+id+"&type=perfil");		
		 }
		// return this.redirectError();		
		/*----#end-code----*/
		return this.redirect("igrp","PesquisarPerfil","index", this.queryString());	
	}
	
	public Response actionTransacao() throws IOException, IllegalArgumentException, IllegalAccessException{
		
		PesquisarPerfil model = new PesquisarPerfil();
		model.load();
		/*----#gen-example
		  This is an example of how you can implement your code:
		  In a .query(null,... change 'null' to your db connection name added in application builder.
		
		 this.addQueryString("p_id","12"); //to send a query string in the URL
		 return this.forward("igrp","TransacaoOrganica","index", this.queryString()); //if submit, loads the values
		
		----#gen-example */
		/*----#start-code(transacao)----*/
		//String id = Core.getParam("p_id");
        this.addQueryString("p_type","perfil");
      //don't need to add p_id because its declared view.id.setParam(true);
        
		return this.forward("igrp", "TransacaoOrganica", "index",this.queryString());		
		/*----#end-code----*/
			
	}
	
	public Response actionConvidar() throws IOException, IllegalArgumentException, IllegalAccessException{
		
		PesquisarPerfil model = new PesquisarPerfil();
		model.load();
		/*----#gen-example
		  This is an example of how you can implement your code:
		  In a .query(null,... change 'null' to your db connection name added in application builder.
		
		 this.addQueryString("p_id","12"); //to send a query string in the URL
		 return this.forward("igrp","NovoUtilizador","index", this.queryString()); //if submit, loads the values
		
		----#gen-example */
		/*----#start-code(convidar)----*/
		String id = Core.getParam("p_id");
        if(id!=null && !id.equals("")){
          return this.redirect("igrp", "NovoUtilizador", "index&p_id_prof="+id);
        }
        //return this.redirectError();
		/*----#end-code----*/
		return this.redirect("igrp","NovoUtilizador","index", this.queryString());	
	}
	
	/*----#start-code(custom_actions)----*/
	
	/*----#end-code----*/
	}
