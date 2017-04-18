<?xml version="1.0" encoding="utf-8"?><!-- DWXMLSource="xml/REDGLOBAL_REDWFGLB_T_ACTIVITY_CLASS_FORM_form.xml" --><xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" encoding="ISO-8859-1" doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN" doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"/>

<xsl:template match="/">
<xsl:call-template name="home-top-inc"/>

		  <!-- REDWF.GLB_T_ACTIVITY_CLASS CLASSIFICACAO ETAPA FORM FORM -->

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


		  <!--START FORM--><!--TOOL BAR--><xsl:apply-templates mode="tools-bar" select="rows/content/form/tools-bar" /><div class="box-content"><form action="#" method="post" id="formular_default" name="formular_default" class="default_form" enctype="multipart/form-data"><!--HIDDEN FIELDS--><xsl:apply-templates mode="form-hidden" select="rows/content/form/value" /><xsl:if test="rows/content/form/label/self_id"><label><span><xsl:value-of select="rows/content/form/label/self_id"/></span><select name="{rows/content/form/list/self_id/@name}" class=""><xsl:for-each select="rows/content/form/list/self_id/option"><option value="{value}"><xsl:if test="@selected='true'"><xsl:attribute name="selected">selected</xsl:attribute></xsl:if><xsl:value-of select="text"/></option></xsl:for-each></select></label></xsl:if><xsl:if test="rows/content/form/label/code"><label><span>(*) <xsl:value-of select="rows/content/form/label/code"/></span><input type="text" name="{rows/content/form/value/code/@name}" value="{rows/content/form/value/code}"  class="required" maxlength="20" /></label></xsl:if><xsl:if test="rows/content/form/label/name"><label><span>(*) <xsl:value-of select="rows/content/form/label/name"/></span><input type="text" name="{rows/content/form/value/name/@name}" value="{rows/content/form/value/name}"  class="required" maxlength="100" /></label></xsl:if><xsl:if test="rows/content/form/label/associacoes"><xsl:call-template name="page-title"><xsl:with-param name="title" select="rows/content/form/label/associacoes" /><xsl:with-param name="class" select="'subtitle'" /></xsl:call-template></xsl:if><xsl:if test="rows/content/form/label/associacoes"><xsl:call-template name="tools-bar-form-group"><xsl:with-param name="rel" select="'associacoes'" /></xsl:call-template></xsl:if><xsl:if test="rows/content/form/label/classificacao"><label><span><xsl:value-of select="rows/content/form/label/classificacao"/></span><select name="{rows/content/form/list/classificacao/@name}" rel="F_associacoes"  class=""><xsl:for-each select="rows/content/form/list/classificacao/option"><option value="{value}"><xsl:if test="@selected='true'"><xsl:attribute name="selected">selected</xsl:attribute></xsl:if><xsl:value-of select="text"/></option></xsl:for-each></select></label></xsl:if><xsl:if test="rows/content/form/label/associacoes"><!--INICIO DA TABELA--><div class="simple-table"><table rel="T_associacoes"><!--CABECALHO DA TABELA--><thead><tr><th><xsl:value-of select="rows/content/form/table/associacoes/label/classificacao"/></th><th align="center" width="10"></th><th align="center" width="10"></th></tr></thead><!--CORPO DA TABELA--><tbody><xsl:for-each select="rows/content/form/table/associacoes/value/row"><tr><td><xsl:value-of select="classificacao_desc" /><input type="hidden" name="p_associacoes_id" value="{associacoes_id}" /><input type="hidden" name="p_classificacao_fk" value="{classificacao}" /><input type="hidden" name="p_classificacao_fk_desc" value="{classificacao_desc}" /></td><td align="center" width="10"><img alt="apagar" src="{$path}/img/icon/ctx-delete.png" class="IGRP_delRow" rel="associacoes"/></td><td align="center" width="10"><img alt="editar" src="{$path}/img/icon/edit.png" class="IGRP_editRow" rel="associacoes"/></td></tr></xsl:for-each></tbody></table></div><!--FIM DA TABELA--></xsl:if></form></div><!--END FORM-->


	  </div>


		  <!-- END YOUR CODE HERE -->



<xsl:call-template name="home-bottom-inc"/>
</xsl:template>
<!--TEMPLATE FOR HEAD-->
<xsl:include href="../../xsl/tmpl/IGRP-home-include.tmpl.xsl?v=20120116"/>



</xsl:stylesheet>