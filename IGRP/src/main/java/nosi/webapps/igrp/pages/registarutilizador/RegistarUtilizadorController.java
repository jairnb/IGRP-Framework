
package nosi.webapps.igrp.pages.registarutilizador;

import nosi.core.webapp.Controller;
import java.io.IOException;
import nosi.core.webapp.Core;
import nosi.core.webapp.Response;
/*----#start-code(packages_import)----*/

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import nosi.core.webapp.Controller;
import nosi.core.webapp.Core;
import nosi.core.webapp.FlashMessage;
import nosi.core.webapp.Igrp;
import nosi.core.webapp.RParam;
import nosi.core.webapp.Response;
import nosi.webapps.igrp.dao.Application;
import nosi.webapps.igrp.dao.Organization;
import nosi.webapps.igrp.dao.Profile;
import nosi.webapps.igrp.dao.ProfileType;
import nosi.webapps.igrp.dao.User;
import nosi.webapps.igrp.dao.UserRole;
import static nosi.core.i18n.Translator.gt;
/*----#end-code----*/


public class RegistarUtilizadorController extends Controller {		

	public Response actionIndex() throws IOException, IllegalArgumentException, IllegalAccessException{
		
		RegistarUtilizador model = new RegistarUtilizador();
		model.load();
		RegistarUtilizadorView view = new RegistarUtilizadorView();
		/*----#start-code(index)----*/

		boolean isError = false;

		if(Igrp.getInstance().getRequest().getMethod().equals("POST")){			
			
			if(!model.getPassword().equals(model.getConfirmar_password())){
				Core.setMessageError("Password inconsistentes ... Tente de novo.");
				isError = true;
			}
				
			if(!isError){				
				User user = new User();				
				user.setName(model.getNome());
				user.setPass_hash(nosi.core.webapp.User.encryptToHash(model.getPassword(), "SHA-256"));
				user.setEmail(model.getEmail());
				user.setUser_name(model.getUsername());
				user.setStatus(1);
				user.setCreated_at(System.currentTimeMillis());
				user.setUpdated_at(System.currentTimeMillis());
				user.setAuth_key(nosi.core.webapp.User.generateAuthenticationKey());
				user.setActivation_key(nosi.core.webapp.User.generateActivationKey());
				user = user.insert();
				UserRole role = new UserRole();
				String role_name = Igrp.getInstance().getServlet().getInitParameter("role_name");
				role.setRole_name(role_name != null && !role_name.trim().isEmpty() ? role_name : "IGRP_ADMIN");
				role.setUser(user);
				role = role.insert();
				if(user!=null){
					Application app = new Application().find().andWhere("dad", "=", "tutorial").one();
					ProfileType prof = new ProfileType().find().andWhere("code","=","perfil.tutorial").andWhere("application", "=",app.getId()).one();
					Organization org = new Organization().find().andWhere("code","=","org.tutorial").andWhere("application", "=",app.getId()).one();
					//Atribui acesso a aplicação IGRP Tutorial
					Profile p1 = new Profile(app.getId(), "ENV",prof, user,org);
					p1.insert();
					Profile p2 = new Profile(prof.getId(), "PROF", prof, user, org);
					p2.insert();
					Core.setMessageSuccess(gt("Utilizador registado com sucesso."));
					Core.setMessageWarning("Deve convidar agora o utilizador.");
					return this.redirect("igrp", "RegistarUtilizador", "index");
				}
				else
					Igrp.getInstance().getFlashMessage().addMessage("error", gt("Error ao registar uilizador."));
			}			
		}	
	
		/*----#end-code----*/
		view.setModel(model);
		return this.renderView(view);	
	}
	
	public Response actionGuardar() throws IOException, IllegalArgumentException, IllegalAccessException{
		
		RegistarUtilizador model = new RegistarUtilizador();
		model.load();
		/*----#gen-example
		  This is an example of how you can implement your code:
		  In a .query(null,... change 'null' to your db connection name added in application builder.
		
		 this.addQueryString("p_id","12"); //to send a query string in the URL
		 return this.forward("igrp","RegistarUtilizador","index", this.queryString()); //if submit, loads the values
		
		----#gen-example */
		/*----#start-code(guardar)----*/

		/*----#end-code----*/
		return this.redirect("igrp","RegistarUtilizador","index", this.queryString());	
	}
	
	/*----#start-code(custom_actions)----*/
public Response actionEditar(@RParam(rParamName = "p_id") String idUser) throws IOException, IllegalArgumentException, IllegalAccessException{
		
		
		RegistarUtilizador model = new RegistarUtilizador();	
        model.load();
		User user = new User().findOne(Integer.parseInt(idUser));		
		model.setNome(user.getName());
		model.setUsername(user.getUser_name());
		model.setEmail(user.getEmail());		
		if(Igrp.getInstance().getRequest().getMethod().toUpperCase().equals("POST")){			
					
			boolean isError = false;
			if(!model.getPassword().equals(model.getConfirmar_password())){
				Core.setMessageError(gt("Password inconsistentes ... Tente de novo."));
				isError = true;
			}				
			if(!isError){
				user.setName(model.getNome());
				user.setPass_hash(nosi.core.webapp.User.encryptToHash(model.getPassword(), "SHA-256"));
				//user.setEmail(model.getEmail());
				//user.setUser_name(model.getUsername());
				user.setUpdated_at(System.currentTimeMillis());
				user = user.update();
				if(user !=null){
					Core.setMessageSuccess(gt("Utilizador atualizado com sucesso."));
					return this.redirect("igrp", "RegistarUtilizador", "editar", new String[]{"p_id"}, new String[]{user.getId() + ""});
				}
				else
					Core.setMessageError(gt("Error ao atualizar uilizador."));
			}			
		}		
		RegistarUtilizadorView view = new RegistarUtilizadorView();
		view.email.propertie().setProperty("readonly", "true");	
		view.username.propertie().setProperty("readonly", "true");	
		view.password.propertie().setProperty("required","false");
  		view.confirmar_password.propertie().setProperty("required","false");
		view.titulo_text.setValue("Atualizar utilizador");
		view.btn_guardar.setLink("editar&p_id=" + idUser);
 		view.setModel(model);
		return this.renderView(view);
	
	}
	/*----#end-code----*/
	}
