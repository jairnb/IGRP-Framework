
package nosi.webapps.igrp.pages.novoutilizador;

import nosi.core.webapp.Controller;
import java.io.IOException;
import nosi.core.webapp.Core;
import nosi.core.webapp.Response;
import nosi.core.config.Config;
/*----#start-code(packages_import)----*/
import nosi.core.exception.ServerErrorHttpException;
import nosi.core.ldap.LdapInfo;
import nosi.core.ldap.LdapPerson;
import nosi.core.ldap.NosiLdapAPI;
import nosi.core.mail.EmailMessage;
import nosi.core.webapp.Controller;
import nosi.core.webapp.Core;
import nosi.core.webapp.Igrp;
import nosi.core.webapp.RParam;
import nosi.core.webapp.Response;
import nosi.core.webapp.activit.rest.GroupService;
import nosi.core.webapp.activit.rest.UserService;
import nosi.webapps.igrp.dao.Application;
import nosi.webapps.igrp.dao.Organization;
import nosi.webapps.igrp.dao.Profile;
import nosi.webapps.igrp.dao.ProfileType;
import nosi.webapps.igrp.dao.User;
import nosi.webapps.igrp.dao.UserRole;
import service.client.WSO2UserStub;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.xml.bind.JAXB;

import org.wso2.carbon.um.ws.service.RemoteUserStoreManagerService;
import org.wso2.carbon.um.ws.service.dao.xsd.ClaimDTO;

import static nosi.core.i18n.Translator.gt;
/*----#end-code----*/


public class NovoUtilizadorController extends Controller {		

	public Response actionIndex() throws IOException, IllegalArgumentException, IllegalAccessException{
		
		NovoUtilizador model = new NovoUtilizador();
		model.load();
		NovoUtilizadorView view = new NovoUtilizadorView();
		/*----#gen-example
		  This is an example of how you can implement your code:
		  In a .query(null,... change 'null' to your db connection name added in application builder.
		
		
		view.aplicacao.setQuery(Core.query(null,"SELECT 'id' as ID,'name' as NAME "));
		view.organica.setQuery(Core.query(null,"SELECT 'id' as ID,'name' as NAME "));
		view.perfil.setQuery(Core.query(null,"SELECT 'id' as ID,'name' as NAME "));
		
		----#gen-example */
		/*----#start-code(index)----*/
		
		Integer id_prof = Core.getParamInt("p_id");
		String id = Core.getParam("id");
		if(id_prof!=0){
			ProfileType prof = new ProfileType().findOne(id_prof);
			if(prof!=null) {
			model.setAplicacao(prof.getApplication().getId());
			model.setOrganica(prof.getOrganization().getId());
			model.setPerfil(prof.getId());
			}
		}	
	
		view.aplicacao.setValue(new Application().getListApps());
		view.organica.setValue(new Organization().getListOrganizations(model.getAplicacao()));
		view.perfil.setValue(new ProfileType().getListProfiles(model.getAplicacao(), model.getOrganica()));
		
		if(Core.isNotNullOrZero(id) && !id.trim().isEmpty()){
			User u =  (User) new User().findIdentityById(Integer.parseInt(id));
			view.email.setValue(u.getEmail());
			view.setPageTitle("Convite - atualizar");
		}
		
		/*----#end-code----*/
		view.setModel(model);
		return this.renderView(view);	
	}
	
	public Response actionGravar() throws IOException, IllegalArgumentException, IllegalAccessException{
		
		NovoUtilizador model = new NovoUtilizador();
		model.load();
		/*----#gen-example
		  This is an example of how you can implement your code:
		  In a .query(null,... change 'null' to your db connection name added in application builder.
		
		 this.addQueryString("p_id","12"); //to send a query string in the URL
		 return this.forward("igrp","NovoUtilizador","index", this.queryString()); //if submit, loads the values
		
		----#gen-example */
		/*----#start-code(gravar)----*/
	
		if(Igrp.getMethod().equalsIgnoreCase("post")) {
          Boolean sucess;
			switch(this.getConfig().getAutenticationType()) {
				case "ldap":
					sucess=this.ldap(model);
				break;
				case "db":
				default: sucess=this.db(model);
				}
         	 this.addQueryString("p_aplicacao",  model.getAplicacao());
             this.addQueryString("p_organica",  model.getOrganica());
             this.addQueryString("p_perfil",  model.getPerfil());
          if(!sucess){            
             this.addQueryString("p_email",  model.getEmail());
          }
            
		}else
		throw new ServerErrorHttpException("Unsuported operation ...");

	/*----#end-code----*/
		return this.redirect("igrp","NovoUtilizador","index", this.queryString());	
	}
	
	/*----#start-code(custom_actions)----*/
	
	private Boolean db(NovoUtilizador model) throws IllegalArgumentException, IllegalAccessException {
		
		Profile p = new Profile();
		p.setOrganization(new Organization().findOne(model.getOrganica()));
		p.setProfileType(new ProfileType().findOne(model.getPerfil()));
		p.setType("PROF");
		User u = new User().find().andWhere("email", "=", model.getEmail().trim()).one();
		if(Core.isNotNull(u)){
			p.setUser(u);
			p.setType_fk(model.getPerfil());         	
			p = p.insert();
			if(p!=null){
				p = new Profile();
				p.setUser(u);
				p.setOrganization(new Organization().findOne(model.getOrganica()));
				p.setProfileType(new ProfileType().findOne(model.getPerfil()));
				p.setType("ENV");
				p.setType_fk(model.getAplicacao());
				p = p.insert();
				if(p!=null){
					//Associa utilizador a grupo no Activiti 
					UserService userActiviti0 = new UserService();
					userActiviti0.setId(u.getUser_name());
					userActiviti0.setPassword("password.igrp");
					userActiviti0.setFirstName(u.getName());
					userActiviti0.setLastName("");
					userActiviti0.setEmail(u.getEmail());
					userActiviti0.create(userActiviti0);	
					new GroupService().addUser(p.getOrganization().getCode()+"."+p.getProfileType().getCode(),userActiviti0.getId());
					Core.setMessageSuccess();
                   return true;
				}else{
					Core.setMessageError();
				}
			}else{
				Core.setMessageError("Este utilizador está convidado para este perfil.");
			}
		}else{
			Core.setMessageError("Este e-mail não está adicionado. Primeiro adicionar este utilizador.");
		}
      return false;
	}
	
	private User invite(String email, boolean viaIds, Object ...obj) {
		ArrayList<LdapPerson> personArray = new ArrayList<LdapPerson>();
		User userLdap = null;
	
		if(viaIds) {
			try {
				Properties settings = (Properties) obj[0];
				
				URL url =  new URL(settings.getProperty("RemoteUserStoreManagerService-wsdl-url"));
		        WSO2UserStub.disableSSL();
		        WSO2UserStub stub = new WSO2UserStub(new RemoteUserStoreManagerService(url));
		        stub.applyHttpBasicAuthentication(settings.getProperty("admin-usn"), settings.getProperty("admin-pwd"), 2);
		         
	           List<ClaimDTO> result = stub.getOperations().getUserClaimValues(email, "");
	           LdapPerson ldapPerson = new LdapPerson();
	           result.forEach(user -> {
	            	switch(user.getClaimUri().getValue()){
	            		case "http://wso2.org/claims/displayName": ldapPerson.setDisplayName(user.getValue().getValue()); break;
	            		case "http://wso2.org/claims/givenname": ldapPerson.setGivenName(user.getValue().getValue()); break;
	            		case "http://wso2.org/claims/emailaddress": 
	            			ldapPerson.setUid(user.getValue().getValue());
	            			ldapPerson.setMail(user.getValue().getValue()); 
	            		break;
	            		case "http://wso2.org/claims/fullname": ldapPerson.setFullName(user.getValue().getValue()); break;
	            		case "http://wso2.org/claims/lastname": ldapPerson.setLastName(user.getValue().getValue()); break;
	            	}
	               // System.out.println(obj.getClaimUri().getValue() + " = " + obj.getValue().getValue());
	            });
	            personArray.add(ldapPerson);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else {
			File file = new File(getClass().getClassLoader().getResource(new Config().getBasePathConfig() + File.separator + "ldap" + File.separator + "ldap.xml").getPath());
			LdapInfo ldapinfo = JAXB.unmarshal(file, LdapInfo.class);
			NosiLdapAPI ldap = new NosiLdapAPI(ldapinfo.getUrl(), ldapinfo.getUsername(), ldapinfo.getPassword(), ldapinfo.getBase(), ldapinfo.getAuthenticationFilter(), ldapinfo.getEntryDN());
			
			personArray = ldap.getUser(email);
		}
			
			if (personArray != null && personArray.size() > 0) {
				for (int i = 0; i < personArray.size(); i++) {
					userLdap = new User();
					LdapPerson person = personArray.get(i);
					//System.out.println(person);
					if(person.getName() != null && !person.getName().isEmpty())
						userLdap.setName(person.getName());
					else
						if(person.getDisplayName() != null && !person.getDisplayName().isEmpty())
							userLdap.setName(person.getDisplayName());
						else
							userLdap.setName(person.getFullName());
					try {
						//String aux = person.getMail().toLowerCase().split("@")[0];
						String aux = person.getMail().toLowerCase().trim();
						userLdap.setUser_name(aux);
					}catch(Exception e) {
						e.printStackTrace();
						userLdap.setUser_name(person.getMail().trim().toLowerCase());
						Igrp.getInstance().getFlashMessage().addMessage("warning",gt("Something is wrong from LDAP server side."));
					}
					userLdap.setEmail(person.getMail().trim().toLowerCase());
					userLdap.setStatus(0);
					userLdap.setCreated_at(System.currentTimeMillis());
					userLdap.setUpdated_at(System.currentTimeMillis());
					userLdap.setAuth_key(nosi.core.webapp.User.generateAuthenticationKey());
					userLdap.setActivation_key(nosi.core.webapp.User.generateActivationKey());
				}
		}else 
			Core.setMessageError("Este utilizador não existe.");
		
		return userLdap;
	}
	
	private void sendEmailToInvitedUser(User u, NovoUtilizador model) {
		String url_ = Igrp.getInstance().getRequest().getRequestURL() + "?r=igrp/login/login&activation_key=" + u.getActivation_key();
		//System.out.println(url_);
		Organization orgEmail = new Organization().findOne(model.getOrganica());
		String msg = ""
				+ "<p><b>Aplicação:</b> "  +  orgEmail.getApplication().getName() + "</p>" + 
				"			 <p><b>Organização:</b> " + orgEmail.getName() + "</p>" + 
				"			 <p><b>Link Activação:</b> <a href=\"" +  url_ + "\">" + url_ + "</a></p>" + 
				"			 <p><b>Utilizador:</b> " + u.getUser_name() + "</p>";
		try {
			EmailMessage.newInstance().setTo(u.getEmail()).setFrom("igrpframeworkjava@gmail.com").setSubject("IGRP - User activation")
			.setMsg(msg, "utf-8", "html").send();
		} catch (IOException e) {
			System.out.println("Email não foi enviado ..."); 
			e.printStackTrace();
		}
	}
	
	private Boolean ldap(NovoUtilizador model) throws IllegalArgumentException, IllegalAccessException {	
		
		Profile p = new Profile();
		p.setOrganization(new Organization().findOne(model.getOrganica()));
		p.setProfileType(new ProfileType().findOne(model.getPerfil()));
		p.setType("PROF");
		
		String email = model.getEmail().trim();
		
		Properties settings = loadIdentityServerSettings();
		User userLdap = null;
		userLdap = invite(email, (settings.getProperty("enabled") != null && settings.getProperty("enabled").equalsIgnoreCase("true")), settings);
		
			if(userLdap != null) {
				User u = new User().find().andWhere("email", "=", model.getEmail()).one();
				if(u == null) {
					u = userLdap.insert();
					UserRole role = new UserRole();
					String role_name = Igrp.getInstance().getServlet().getInitParameter("role_name");
					role.setRole_name(role_name != null && !role_name.trim().isEmpty() ? role_name : "IGRP_ADMIN");
					role.setUser(u);
					role = role.insert();
					
					if(u != null)
						sendEmailToInvitedUser(u, model);
				}
				p.setUser(u);
				p.setType_fk(model.getPerfil());
				p = p.insert();
				if(p!=null){
					p = new Profile();
					p.setUser(u);
					p.setOrganization(new Organization().findOne(model.getOrganica()));
					p.setProfileType(new ProfileType().findOne(model.getPerfil()));
					p.setType("ENV");
					p.setType_fk(model.getAplicacao());
					p = p.insert();
					if(p!=null){
						//Associa utilizador a grupo no Activiti
						UserService userActiviti0 = new UserService();
						userActiviti0.setId(u.getUser_name());
						userActiviti0.setPassword("password.igrp");
						userActiviti0.setFirstName(u.getName());
						userActiviti0.setLastName("");
						userActiviti0.setEmail(u.getEmail());
						userActiviti0.create(userActiviti0);	
						new GroupService().addUser(p.getOrganization().getCode()+"."+p.getProfileType().getCode(),userActiviti0.getId());
						Core.setMessageSuccess();
                        return true;
					}else{
						Core.setMessageError();
					}
				}else{
					Core.setMessageError();
				}				
			}else {
				Core.setMessageError("Este utilizador não existe no LDAP para convidar.");
			}
		return false;
		
	}

	private Properties loadIdentityServerSettings() {
		
		String path = new Config().getBasePathConfig() + File.separator + "ids";
		String fileName = "wso2-ids.xml";
		File file = new File(getClass().getClassLoader().getResource(path + File.separator + fileName).getPath().replaceAll("%20", " "));
		
		
		FileInputStream fis = null;
		Properties props = new Properties();
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			fis = null;	
		}
		try {
			props.loadFromXML(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return props;
	}

	public Response actionEditar(@RParam(rParamName = "p_id") String idProfile) throws IOException, IllegalArgumentException, IllegalAccessException{
		if(idProfile!=null){
			Profile p = new Profile().findOne(Integer.parseInt(idProfile));
			if(p!=null){
				NovoUtilizador model = new NovoUtilizador();
				model.setAplicacao(p.getProfileType().getApplication().getId());
				model.setOrganica(p.getOrganization().getId());
				model.setPerfil(p.getProfileType().getId());
				NovoUtilizadorView view = new NovoUtilizadorView();
				view.aplicacao.setValue(new Application().getListApps());
				view.organica.setValue(new Organization().getListOrganizations(model.getAplicacao()));
				view.perfil.setValue(new ProfileType().getListProfiles(model.getAplicacao(), model.getOrganica()));
				view.email.setValue(p.getUser().getEmail());
				view.btn_gravar.setLink("editarProfile&p_id="+idProfile);
             	view.setModel(model);
				return this.renderView(view);
			}
		}
		return this.redirectError();
	}
	
	public Response actionEditarProfile(@RParam(rParamName = "p_id") String id) throws IOException, IllegalArgumentException, IllegalAccessException{
		if(Igrp.getMethod().equalsIgnoreCase("post") && id!=null){
			NovoUtilizador model = new NovoUtilizador();
			model.load();
			Profile p = new Profile().findOne(Integer.parseInt(id));
			p.setOrganization(new Organization().findOne(model.getOrganica()));
			p.setProfileType(new ProfileType().findOne(model.getPerfil()));
			p.setType("PROF");
			User u = new User().find().andWhere("email", "=", model.getEmail()).one();
			if(u!=null){
				p.setUser(u);
				p.setType_fk(model.getPerfil());
				p = p.update();
				if(p!=null){
					Core.setMessageSuccess();
					return this.forward("igrp", "NovoUtilizador", "editar&p_id="+id);
				}else{
					Core.setMessageError();
				}
			}else{
				Core.setMessageError("Email inválido");
			}
		}
		return this.redirectError();
	}
	/*----#end-code----*/
	}
