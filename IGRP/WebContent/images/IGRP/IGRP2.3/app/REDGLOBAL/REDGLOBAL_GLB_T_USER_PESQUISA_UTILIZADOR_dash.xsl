<?xml version="1.0" encoding="utf-8"?><!-- DWXMLSource="xml/REDGLOBAL_GLB_T_USER_PESQUISA_UTILIZADOR_dash.xml" --><xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" encoding="ISO-8859-1" doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN" doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"/>

<xsl:template match="/">
<xsl:call-template name="home-top-inc"/>

		  <!-- GLB_T_USER GESTAO UTILIZADOR PESQUISA_UTILIZADOR PESQUISA UTILIZADORES E ASSINATURA -->

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


		  <!--START PAGE--><form action="#" method="post" id="formular_default" name="formular_default" class="default_filter"><!--FILTRO --><!--START FORM--><div class="box-content"><!--HIDDEN FIELDS--><xsl:apply-templates mode="form-hidden" select="rows/content/filter/value" /><label><span><xsl:value-of select="rows/content/filter/label/nome"/></span><input type="text" name="{rows/content/filter/value/nome/@name}" value="{rows/content/filter/value/nome}"  class="" maxlength="100" /></label><label><span><xsl:value-of select="rows/content/filter/label/e_mail"/></span><input type="text" name="{rows/content/filter/value/e_mail/@name}" value="{rows/content/filter/value/e_mail}"  class="" maxlength="100" /></label><label><span><xsl:value-of select="rows/content/filter/label/organica"/></span><select name="{rows/content/filter/list/organica/@name}" class=" IGRP_change"><xsl:for-each select="rows/content/filter/list/organica/option"><option value="{value}"><xsl:if test="@selected='true'"><xsl:attribute name="selected">selected</xsl:attribute></xsl:if><xsl:value-of select="text"/></option></xsl:for-each></select></label><label><span><xsl:value-of select="rows/content/filter/label/perfil"/></span><select name="{rows/content/filter/list/perfil/@name}" class=""><xsl:for-each select="rows/content/filter/list/perfil/option"><option value="{value}"><xsl:if test="@selected='true'"><xsl:attribute name="selected">selected</xsl:attribute></xsl:if><xsl:value-of select="text"/></option></xsl:for-each></select></label><!--TOOLBAR FILTER--><xsl:apply-templates mode="button-bar" select="rows/content/filter/tools-bar" /></div><!--END FORM--><!--TOOL BAR--><xsl:apply-templates mode="tools-bar" select="rows/content/table/tools-bar" /><!-- CONTEXT-MENU --><xsl:apply-templates mode="context-menu" select="rows/content/table/context-menu"/><!--INICIO DA TABELA--><div class="table"><table class="sortable"><!--CABECALHO DA TABELA--><thead><tr><xsl:if test="rows/content/table/label/nome"><th><xsl:value-of select="rows/content/table/label/nome"/></th></xsl:if><xsl:if test="rows/content/table/label/perfil"><th><xsl:value-of select="rows/content/table/label/perfil"/></th></xsl:if><xsl:if test="rows/content/table/label/assinatura"><th><xsl:value-of select="rows/content/table/label/assinatura"/></th></xsl:if></tr></thead><!--CORPO DA TABELA--><tbody><xsl:for-each select="rows/content/table/value/row"><xsl:variable name="pos" select="(position()+1) mod 2"/><tr class="IGRP_contextMenu"><xsl:apply-templates mode="context-param" select="context-menu" /><xsl:if test="nome"><td class="table_row{$pos}"><u class="IGRP_lookup" title="Passa Valor"><xsl:value-of select="nome" /></u></td></xsl:if><xsl:if test="perfil"><td class="table_row{$pos}"><xsl:value-of select="perfil" /></td></xsl:if><xsl:if test="assinatura"><td class="table_row{$pos}"><u class="IGRP_lookup" title="Passa Valor"><xsl:value-of select="assinatura" /></u></td></xsl:if></tr></xsl:for-each></tbody></table></div><!--FIM DA TABELA--></form><!--END PAGE-->


	  </div>


		  <!-- END YOUR CODE HERE -->



<xsl:call-template name="home-bottom-inc"/>
</xsl:template>
<!--TEMPLATE FOR HEAD-->
<xsl:include href="../../xsl/tmpl/IGRP-home-include.tmpl.xsl?v=16"/>



</xsl:stylesheet>