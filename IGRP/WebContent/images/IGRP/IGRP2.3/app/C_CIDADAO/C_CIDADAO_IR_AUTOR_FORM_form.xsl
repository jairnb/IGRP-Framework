<?xml version="1.0" encoding="utf-8"?><!-- DWXMLSource="xml/C_CIDADAO_IR_AUTOR_FORM_form.xml" --><xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"><xsl:output method="html" encoding="ISO-8859-1" doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN" doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"/><xsl:template match="/"><xsl:call-template name="home-top-inc"/>

        <!-- IR_AUTOR TESTE IGRP FORM FORM -->

        <!-- START YOUR CODE HERE --><!--PAGE TITLE-->
     <div class="box-content">

   <xsl:call-template name="page-title">
          <xsl:with-param name="title" select="rows/content/title" />
        </xsl:call-template>

        <!--NOTIFICATION MESSAGES-->
        <xsl:apply-templates mode="notif_messages" select="rows/content/messages" >
          <xsl:with-param name="class" select="'notification'"/>
        </xsl:apply-templates>


        <!--START FORM--><!--TOOL BAR--><xsl:apply-templates mode="tools-bar" select="rows/content/form/tools-bar" /><div class="box-content"><form action="#" method="post" id="formular_default" name="formular_default" class="default_form" enctype="multipart/form-data"><!--HIDDEN FIELDS--><xsl:apply-templates mode="form-hidden" select="rows/content/form/value" /><xsl:if test="rows/content/form/label/nome"><label><span><xsl:value-of select="rows/content/form/label/nome"/></span><input type="text" name="{rows/content/form/value/nome/@name}" value="{rows/content/form/value/nome}"   maxlength="100"  class=""><xsl:call-template name="FIELD_validator"><xsl:with-param name="field" select="rows/content/form/label/nome" /></xsl:call-template></input></label></xsl:if><xsl:if test="rows/content/form/label/nr_bi"><label><span><xsl:value-of select="rows/content/form/label/nr_bi"/></span><input type="text" name="{rows/content/form/value/nr_bi/@name}" value="{rows/content/form/value/nr_bi}"   maxlength="22"  class=""><xsl:call-template name="FIELD_validator"><xsl:with-param name="field" select="rows/content/form/label/nr_bi" /></xsl:call-template></input><xsl:call-template name="lookup-tool"><xsl:with-param name="page" select="'LOOKUP'" /><xsl:with-param name="action" select="'LOOKUP_BI'" /><xsl:with-param name="name" select="rows/content/form/value/nr_bi/@name" /><xsl:with-param name="js_lookup" select="rows/content/form/lookup/nr_bi" /></xsl:call-template></label></xsl:if><xsl:if test="rows/content/form/label/nr_nif"><label><span><xsl:value-of select="rows/content/form/label/nr_nif"/></span><input type="text" name="{rows/content/form/value/nr_nif/@name}" value="{rows/content/form/value/nr_nif}"   maxlength="22"  class=""><xsl:call-template name="FIELD_validator"><xsl:with-param name="field" select="rows/content/form/label/nr_nif" /></xsl:call-template></input><xsl:call-template name="lookup-tool"><xsl:with-param name="page" select="'LOOKUP'" /><xsl:with-param name="action" select="'LOOKUP_NIF'" /><xsl:with-param name="name" select="rows/content/form/value/nr_nif/@name" /><xsl:with-param name="js_lookup" select="rows/content/form/lookup/nr_nif" /></xsl:call-template></label></xsl:if><xsl:if test="rows/content/form/label/dt_nasc"><label><span><xsl:value-of select="rows/content/form/label/dt_nasc"/></span><input type="text" name="{rows/content/form/value/dt_nasc/@name}" value="{rows/content/form/value/dt_nasc}"  class="IGRP_datepicker date"><xsl:call-template name="FIELD_validator"><xsl:with-param name="field" select="rows/content/form/label/dt_nasc" /></xsl:call-template></input></label></xsl:if><xsl:if test="rows/content/form/label/sexo"><label><span><xsl:value-of select="rows/content/form/label/sexo"/></span><xsl:for-each select="rows/content/form/list/sexo/option"><input type="radio" name="{../@name}" value="{value}" class=""><xsl:if test="@selected='true'"><xsl:attribute name="checked">checked</xsl:attribute></xsl:if></input><xsl:value-of select="text"/></xsl:for-each></label></xsl:if><xsl:if test="rows/content/form/label/nm_mae"><label><span><xsl:value-of select="rows/content/form/label/nm_mae"/></span><input type="text" name="{rows/content/form/value/nm_mae/@name}" value="{rows/content/form/value/nm_mae}"   maxlength="100"  class=""><xsl:call-template name="FIELD_validator"><xsl:with-param name="field" select="rows/content/form/label/nm_mae" /></xsl:call-template></input></label></xsl:if><xsl:if test="rows/content/form/label/nm_pai"><label><span><xsl:value-of select="rows/content/form/label/nm_pai"/></span><input type="text" name="{rows/content/form/value/nm_pai/@name}" value="{rows/content/form/value/nm_pai}"   maxlength="100"  class=""><xsl:call-template name="FIELD_validator"><xsl:with-param name="field" select="rows/content/form/label/nm_pai" /></xsl:call-template></input></label></xsl:if><xsl:if test="rows/content/form/label/morada"><label><span><xsl:value-of select="rows/content/form/label/morada"/></span><input type="text" name="{rows/content/form/value/morada/@name}" value="{rows/content/form/value/morada}"   maxlength="100"  class=""><xsl:call-template name="FIELD_validator"><xsl:with-param name="field" select="rows/content/form/label/morada" /></xsl:call-template></input></label></xsl:if></form></div><!--END FORM-->


     </div>


        <!-- END YOUR CODE HERE -->



<xsl:call-template name="home-bottom-inc"/>
</xsl:template>
<!--TEMPLATE FOR HEAD-->
<xsl:include href="../../xsl/tmpl/IGRP-home-include.tmpl.xsl?v=2012081311"/>

<xsl:include href="../../xsl/tmpl/IGRP-field-validator.tmpl.xsl?v=2012081311"/>

</xsl:stylesheet>