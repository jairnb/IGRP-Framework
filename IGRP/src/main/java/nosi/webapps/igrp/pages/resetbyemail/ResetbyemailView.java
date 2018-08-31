package nosi.webapps.igrp.pages.resetbyemail;

import nosi.core.webapp.Model;
import nosi.core.webapp.View;
import nosi.core.gui.components.*;
import nosi.core.gui.fields.*;
import static nosi.core.i18n.Translator.gt;
import nosi.core.config.Config;
import nosi.core.gui.components.IGRPLink;
import nosi.core.webapp.Report;

public class ResetbyemailView extends View {

	public Field sectionheader_1_text;
	public Field form_1_email_1;
	public IGRPForm sectionheader_1;
	public IGRPForm form_1;

	public IGRPButton btn_enviar;

	public ResetbyemailView(){

		this.setPageTitle("ResetByEmail");
			
		sectionheader_1 = new IGRPForm("sectionheader_1","");

		form_1 = new IGRPForm("form_1","");

		sectionheader_1_text = new TextField(model,"sectionheader_1_text");
		sectionheader_1_text.setLabel(gt(""));
		sectionheader_1_text.setValue(gt("Recuperar Conta"));
		sectionheader_1_text.propertie().add("type","text").add("name","p_sectionheader_1_text").add("maxlength","4000");
		
		form_1_email_1 = new EmailField(model,"form_1_email_1");
		form_1_email_1.setLabel(gt("Email"));
		form_1_email_1.propertie().add("name","p_form_1_email_1").add("type","email").add("maxlength","250").add("required","true").add("readonly","false").add("disabled","false");
		


		btn_enviar = new IGRPButton("Enviar","igrp","Resetbyemail","enviar","submit_form","success|fa-send","","");
		btn_enviar.propertie.add("type","form").add("rel","enviar");

		
	}
		
	@Override
	public void render(){
		
		sectionheader_1.addField(sectionheader_1_text);

		form_1.addField(form_1_email_1);

		form_1.addButton(btn_enviar);
		this.addToPage(sectionheader_1);
		this.addToPage(form_1);
	}
		
	@Override
	public void setModel(Model model) {
		
		form_1_email_1.setValue(model);	

		}
}