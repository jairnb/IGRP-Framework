<?xml version="1.0" encoding="utf-8"?><!-- DWXMLSource="xml/CATALOGO_PARAMETRIZACOES_GESTAO_CONTRACTO_dash.xml" --><xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"><xsl:output method="html" encoding="ISO-8859-1" doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN" doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"/><xsl:template match="/"><!-- START HOME TOP -->
      <html>

        <!--HOME-HEADER-->
        <xsl:call-template name="home-header"/>
        <xsl:apply-templates mode="js_validation" select="rows/content/js_validation"/>

         <body>

          <!--HOME TOP-->
        <xsl:call-template name="home-top-main"/>

        <!--CENTER PANE-->
        <div class="general">
        <div id="content">


         <!--HOME SIDE
        <xsl:call-template name="home-side"/> -->

        <div class="ui-layout-center">

        <xsl:call-template name="dialog-modal"/>
        <!-- END HOME TOP --><!-- PARAMETRIZACOES PARAMETRIZACOES GESTAO_CONTRACTO GESTAO CONTRACTO -->

        <!-- START YOUR CODE HERE --><!--PAGE TITLE-->
     <div class="box-content">

   <xsl:call-template name="page-title">
          <xsl:with-param name="title" select="rows/content/title" />
        </xsl:call-template>

        <!--NOTIFICATION MESSAGES-->
        <xsl:apply-templates mode="notif_messages" select="rows/content/messages" >
          <xsl:with-param name="class" select="'notification'"/>
        </xsl:apply-templates>

        
        <!--START PAGE--><form action="#" method="post" id="formular_default" name="formular_default" class="default_filter"><!--FILTRO --><!--START FORM--><div class="box-content"><!--HIDDEN FIELDS--><xsl:apply-templates mode="form-hidden" select="rows/content/filter/value" /><xsl:if test="rows/content/filter/label/cliente"><label><span><xsl:value-of select="rows/content/filter/label/cliente"/></span><select name="{rows/content/filter/list/cliente/@name}" class=""><xsl:call-template name="FIELD_validator"><xsl:with-param name="field" select="rows/content/filter/label/cliente" /></xsl:call-template><xsl:for-each select="rows/content/filter/list/cliente/option"><option value="{value}"><xsl:if test="@selected='true'"><xsl:attribute name="selected">selected</xsl:attribute></xsl:if><xsl:value-of select="text"/></option></xsl:for-each></select></label></xsl:if><xsl:if test="rows/content/filter/label/data_inicio"><label><span><xsl:value-of select="rows/content/filter/label/data_inicio"/></span><input type="text" name="{rows/content/filter/value/data_inicio/@name}" value="{rows/content/filter/value/data_inicio}"  class="IGRP_datepicker date"><xsl:call-template name="FIELD_validator"><xsl:with-param name="field" select="rows/content/filter/label/data_inicio" /></xsl:call-template></input></label></xsl:if><xsl:if test="rows/content/filter/label/data_fim"><label><span><xsl:value-of select="rows/content/filter/label/data_fim"/></span><input type="text" name="{rows/content/filter/value/data_fim/@name}" value="{rows/content/filter/value/data_fim}"  class="IGRP_datepicker date"><xsl:call-template name="FIELD_validator"><xsl:with-param name="field" select="rows/content/filter/label/data_fim" /></xsl:call-template></input></label></xsl:if><!--TOOLBAR FILTER--><xsl:apply-templates mode="button-bar" select="rows/content/filter/tools-bar" /></div><!--END FORM--><!--TOOL BAR--><xsl:apply-templates mode="tools-bar" select="rows/content/table/tools-bar" /><!-- CONTEXT-MENU --><xsl:apply-templates mode="context-menu" select="rows/content/table/context-menu"/><!--INICIO DA TABELA  --><div class="table"><!--LEGENDA FINAL DA TABELA --><xsl:apply-templates mode="legend_color" select="rows/content/table/legend_color"/><table class="sortable IGRP_table"><!--CABECALHO DA TABELA --><thead><tr><xsl:if test="rows/content/table/label/cliente"><th  ><xsl:value-of select="rows/content/table/label/cliente"/></th></xsl:if><xsl:if test="rows/content/table/label/data_inicio"><th  ><xsl:value-of select="rows/content/table/label/data_inicio"/></th></xsl:if><xsl:if test="rows/content/table/label/data_fim"><th  ><xsl:value-of select="rows/content/table/label/data_fim"/></th></xsl:if><xsl:if test="rows/content/table/label/tipo_contrato"><th  ><xsl:value-of select="rows/content/table/label/tipo_contrato"/></th></xsl:if></tr></thead><!--CORPO DA TABELA--><tbody><xsl:for-each select="rows/content/table/value/row[not(@total='yes')]"><xsl:variable name="pos" select="(position()+1) mod 2"/><tr class="IGRP_contextMenu"><xsl:apply-templates mode="context-param" select="context-menu" /><xsl:if test="cliente"><td class="table_row{$pos}"><xsl:value-of select="cliente" /></td></xsl:if><xsl:if test="data_inicio"><td class="table_row{$pos}"><xsl:value-of select="data_inicio" /></td></xsl:if><xsl:if test="data_fim"><td class="table_row{$pos}"><xsl:value-of select="data_fim" /></td></xsl:if><xsl:if test="tipo_contrato"><td class="table_row{$pos}"><xsl:value-of select="tipo_contrato" /></td></xsl:if></tr></xsl:for-each></tbody></table><!--LEGENDA FINAL DA TABELA --><xsl:apply-templates mode="legend_color" select="rows/content/table/legend_color"/></div><!--FIM DA TABELA--></form><!--END PAGE-->


     </div>


        <!-- END YOUR CODE HERE --><!-- START HOME BOTTOM -->

      </div>
      <xsl:call-template name="home-side"/>
      </div></div>

        </body>

        </html>

        <!-- END HOME BOTTOM --></xsl:template>
<!--TEMPLATE FOR HEAD-->
<xsl:include href="../../xsl/tmpl/IGRP-home-include.tmpl.xsl?v=201311271"/>

<xsl:include href="../../xsl/tmpl/IGRP-JSvalidation.tmpl.xsl?v=201311271"/><xsl:include href="../../xsl/tmpl/IGRP-field-validator.tmpl.xsl?v=201311271"/>

</xsl:stylesheet>