<?xml version="1.0" encoding="utf-8"?><!-- DWXMLSource="xml/C_CIDADAO_TESTE_EDIR_FORM_form.xml" --><xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" encoding="ISO-8859-1" doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN" doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"/>

<xsl:template match="/">
<xsl:call-template name="home-top-inc"/>

		  <!-- TESTE_EDIR TESTE_EDIR FORM FORM -->

		  <!-- START YOUR CODE HERE -->

			<!--PAGE TITLE-->
	  <div class="box-content">

		  <xsl:call-template name="page-title">
			 <xsl:with-param name="title" select="rows/content/title" />
		  </xsl:call-template>

		  <!--NOTIFICATION MESSAGES-->
		  <xsl:apply-templates mode="notif_messages" select="rows/content/messages" >
			 <xsl:with-param name="class" select="'notification'"/>
		  </xsl:apply-templates>


		  <!--START FORM--><!--TOOL BAR--><xsl:apply-templates mode="tools-bar" select="rows/content/form/tools-bar" /><div class="box-content"><form action="#" method="post" id="formular_default" name="formular_default" class="default_form" enctype="multipart/form-data"><!--HIDDEN FIELDS--><xsl:apply-templates mode="form-hidden" select="rows/content/form/value" /><xsl:if test="rows/content/form/label/nome"><label><span>(*) <xsl:value-of select="rows/content/form/label/nome"/></span><input type="text" name="{rows/content/form/value/nome/@name}" value="{rows/content/form/value/nome}"  class="required" maxlength="40" /></label></xsl:if><xsl:if test="rows/content/form/label/nome_mae"><label><span><xsl:value-of select="rows/content/form/label/nome_mae"/></span><input type="text" name="{rows/content/form/value/nome_mae/@name}" value="{rows/content/form/value/nome_mae}"  class="" maxlength="40" /></label></xsl:if><xsl:if test="rows/content/form/label/nome_pai"><label><span><xsl:value-of select="rows/content/form/label/nome_pai"/></span><input type="text" name="{rows/content/form/value/nome_pai/@name}" value="{rows/content/form/value/nome_pai}"  class="" maxlength="40" /></label></xsl:if><xsl:if test="rows/content/form/label/bi"><label><span>(*) <xsl:value-of select="rows/content/form/label/bi"/></span><input type="text" name="{rows/content/form/value/bi/@name}" value="{rows/content/form/value/bi}"  class="required" maxlength="22" /><xsl:call-template name="lookup-tool"><xsl:with-param name="page" select="'LOOKUP'" /><xsl:with-param name="action" select="'LOOKUP_BI'" /><xsl:with-param name="name" select="rows/content/form/value/bi/@name" /><xsl:with-param name="js_lookup" select="rows/content/form/lookup/bi" /></xsl:call-template></label></xsl:if><xsl:if test="rows/content/form/label/data_nascimento"><label><span><xsl:value-of select="rows/content/form/label/data_nascimento"/></span><input type="date" name="{rows/content/form/value/data_nascimento/@name}" value="{rows/content/form/value/data_nascimento}" class="IGRP_datepicker date" /></label></xsl:if><xsl:if test="rows/content/form/label/sexo"><label><span><xsl:value-of select="rows/content/form/label/sexo"/></span><xsl:for-each select="rows/content/form/list/sexo/option"><input type="radio" name="{../@name}" value="{value}" class=""><xsl:if test="@selected='true'"><xsl:attribute name="checked">checked</xsl:attribute></xsl:if></input><xsl:value-of select="text"/></xsl:for-each></label></xsl:if></form></div><!--END FORM-->


	  </div>


		  <!-- END YOUR CODE HERE -->



<xsl:call-template name="home-bottom-inc"/>
</xsl:template>
<!--TEMPLATE FOR HEAD-->
<xsl:include href="../../xsl/tmpl/IGRP-home-include.tmpl.xsl?v=201206119"/>



</xsl:stylesheet>