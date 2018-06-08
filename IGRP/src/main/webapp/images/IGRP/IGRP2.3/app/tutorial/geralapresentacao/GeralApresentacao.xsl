<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"><xsl:output method="html" omit-xml-declaration="yes" encoding="utf-8" indent="yes" doctype-system="about:legacy-compat"/><xsl:template match="/"><html><head><xsl:call-template name="IGRP-head"/><link rel="stylesheet" type="text/css" href="{$path}/plugins/tabs/igrp.tabs.css?v={$version}"/><link rel="stylesheet" type="text/css" href="{$path}/plugins/quickbuttonbox/quickbuttonbox.css?v={$version}"/><link rel="stylesheet" type="text/css" href="{$path}/core/igrp/table/datatable/dataTables.bootstrap.css?v={$version}"/><link rel="stylesheet" type="text/css" href="{$path}/core/igrp/table/igrp.tables.css?v={$version}"/><link rel="stylesheet" type="text/css" href="{$path}/plugins/statbox/statbox.css?v={$version}"/><style/></head><body class="{$bodyClass} sidebar-off"><xsl:call-template name="IGRP-topmenu"/><form method="POST" class="IGRP-form" name="formular_default" enctype="multipart/form-data"><div class="container-fluid"><div class="row"><xsl:call-template name="IGRP-sidebar"/><div class="col-sm-9 col-md-10 col-md-offset-2 col-sm-offset-3 main" id="igrp-contents"><div class="content"><div class="row " id="row-30a1bfc4"><div class="gen-column col-sm-12"><div class="gen-inner"><xsl:if test="rows/content/sectionheader_2"><section class="content-header gen-container-item " gen-class="" item-name="sectionheader_2"><h2><xsl:value-of select="rows/content/sectionheader_2/fields/sectionheader_2_text/value"/></h2></section></xsl:if><xsl:apply-templates mode="igrp-messages" select="rows/content/messages"/><div class="gen-tab-holder nav-tabs-custom nav-justified   gen-container-item " tab-template="boxed" gen-class="" item-name="tabcontent_1"><ul class="nav nav-tabs"><xsl:if test="rows/content/tabcontent_1/fields/welcome"><li item-name="welcome" class=" gen-fields-holder" rel="tab-tabcontent_1-welcome"><xsl:call-template name="get-active-tab"><xsl:with-param name="value" select="rows/content/tabcontent_1/fields/welcome/value"/></xsl:call-template><a active-text-color="primary" data-toggle="tab" aria-expanded="true" href="#tab-tabcontent_1-welcome"><i class="fa fa-child"/><span><xsl:value-of select="rows/content/tabcontent_1/fields/welcome/label"/></span></a></li></xsl:if><xsl:if test="rows/content/tabcontent_1/fields/videos_tutoriais"><li item-name="videos_tutoriais" class=" gen-fields-holder" rel="tab-tabcontent_1-videos_tutoriais"><xsl:call-template name="get-active-tab"><xsl:with-param name="value" select="rows/content/tabcontent_1/fields/videos_tutoriais/value"/></xsl:call-template><a active-text-color="primary" data-toggle="tab" aria-expanded="true" href="#tab-tabcontent_1-videos_tutoriais"><i class="fa fa-video-camera"/><span><xsl:value-of select="rows/content/tabcontent_1/fields/videos_tutoriais/label"/></span></a></li></xsl:if><xsl:if test="rows/content/tabcontent_1/fields/documentos_gerais"><li item-name="documentos_gerais" class=" gen-fields-holder" rel="tab-tabcontent_1-documentos_gerais"><xsl:call-template name="get-active-tab"><xsl:with-param name="value" select="rows/content/tabcontent_1/fields/documentos_gerais/value"/></xsl:call-template><a active-text-color="primary" data-toggle="tab" aria-expanded="true" href="#tab-tabcontent_1-documentos_gerais"><i class="fa fa-file-text"/><span><xsl:value-of select="rows/content/tabcontent_1/fields/documentos_gerais/label"/></span></a></li></xsl:if></ul><div class="tab-content"><xsl:if test="rows/content/tabcontent_1/fields/welcome"><div class="tab-pane gen-rows-holder " id="tab-tabcontent_1-welcome" rel="tab-tabcontent_1-welcome" item-name="welcome"><xsl:call-template name="get-active-tab"><xsl:with-param name="value" select="rows/content/tabcontent_1/fields/welcome/value"/><xsl:with-param name="class" select="'tab-pane'"/></xsl:call-template><div class="row " id="row-e282674b"><div class="gen-column col-sm-12"><div class="gen-inner"><xsl:if test="rows/content/sectionheader_1"><section class="content-header gen-container-item " gen-class="" item-name="sectionheader_1"><h2><xsl:value-of select="rows/content/sectionheader_1/fields/sectionheader_1_text/value"/></h2></section></xsl:if><xsl:if test="rows/content/paragraph_1"><div class="box gen-container-item " gen-class="" item-name="paragraph_1"><xsl:call-template name="box-header"><xsl:with-param name="title" select="rows/content/paragraph_1/@title"/><xsl:with-param name="collapsible" select="'true'"/><xsl:with-param name="collapsed" select="'false'"/></xsl:call-template><div class="box-body"><p><xsl:value-of select="rows/content/paragraph_1/fields/paragraph_1_text/value"/></p></div></div></xsl:if><xsl:if test="rows/content/video_2"><div class="gen-container-item " gen-class="" item-name="video_2"><xsl:call-template name="box-header"><xsl:with-param name="title" select="rows/content/video_2/@title"/><xsl:with-param name="collapsible" select="'false'"/><xsl:with-param name="collapsed" select="'false'"/></xsl:call-template><div class="box-body"><iframe style="height:500px" src="{rows/content/video_2/fields/video_2_text/value}" frameborder="0" allowfullscreen=""/></div></div></xsl:if></div></div></div></div></xsl:if><xsl:if test="rows/content/tabcontent_1/fields/videos_tutoriais"><div class="tab-pane gen-rows-holder " id="tab-tabcontent_1-videos_tutoriais" rel="tab-tabcontent_1-videos_tutoriais" item-name="videos_tutoriais"><xsl:call-template name="get-active-tab"><xsl:with-param name="value" select="rows/content/tabcontent_1/fields/videos_tutoriais/value"/><xsl:with-param name="class" select="'tab-pane'"/></xsl:call-template><div class="row " id="row-fc9e1814"><div class="gen-column col-sm-3"><div class="gen-inner"><xsl:if test="rows/content/quickbuttonbox_gestao"><div class="quick-button-box gen-container-item " gen-class="" item-name="quickbuttonbox_gestao"><xsl:apply-templates mode="form-hidden-fields" select="rows/content/quickbuttonbox_gestao/fields"/><a href="{rows/content/quickbuttonbox_gestao/fields/quickbuttonbox_gestao_url/value}" target="modal" class="quick-button metro {rows/content/quickbuttonbox_gestao/fields/quickbuttonbox_gestao_bg/value}"><i class="fa {rows/content/quickbuttonbox_gestao/fields/quickbuttonbox_gestao_icn/value}" aria-hidden="true"/><p><xsl:value-of select="rows/content/quickbuttonbox_gestao/fields/quickbuttonbox_gestao_tit/value"/></p><span class="badge"><xsl:value-of select="rows/content/quickbuttonbox_gestao/fields/quickbuttonbox_gestao_val/value"/></span></a></div></xsl:if></div></div><div class="gen-column col-sm-3"><div class="gen-inner"><xsl:if test="rows/content/quickbuttonbox_instalar"><div class="quick-button-box gen-container-item " gen-class="" item-name="quickbuttonbox_instalar"><xsl:apply-templates mode="form-hidden-fields" select="rows/content/quickbuttonbox_instalar/fields"/><a href="{rows/content/quickbuttonbox_instalar/fields/quickbuttonbox_instalar_url/value}" target="modal" class="quick-button metro {rows/content/quickbuttonbox_instalar/fields/quickbuttonbox_instalar_bg/value}"><i class="fa {rows/content/quickbuttonbox_instalar/fields/quickbuttonbox_instalar_icn/value}" aria-hidden="true"/><p><xsl:value-of select="rows/content/quickbuttonbox_instalar/fields/quickbuttonbox_instalar_tit/value"/></p><span class="badge"><xsl:value-of select="rows/content/quickbuttonbox_instalar/fields/quickbuttonbox_instalar_val/value"/></span></a></div></xsl:if></div></div><div class="gen-column col-sm-3"><div class="gen-inner"><xsl:if test="rows/content/quickbuttonbox_"><div class="quick-button-box gen-container-item " gen-class="" item-name="quickbuttonbox_"><xsl:apply-templates mode="form-hidden-fields" select="rows/content/quickbuttonbox_/fields"/><a href="{rows/content/quickbuttonbox_/fields/quickbuttonbox__url/value}" target="modal" class="quick-button metro {rows/content/quickbuttonbox_/fields/quickbuttonbox__bg/value}"><i class="fa {rows/content/quickbuttonbox_/fields/quickbuttonbox__icn/value}" aria-hidden="true"/><p><xsl:value-of select="rows/content/quickbuttonbox_/fields/quickbuttonbox__tit/value"/></p><span class="badge"><xsl:value-of select="rows/content/quickbuttonbox_/fields/quickbuttonbox__val/value"/></span></a></div></xsl:if></div></div><div class="gen-column col-sm-3"><div class="gen-inner"/></div></div></div></xsl:if><xsl:if test="rows/content/tabcontent_1/fields/documentos_gerais"><div class="tab-pane gen-rows-holder " id="tab-tabcontent_1-documentos_gerais" rel="tab-tabcontent_1-documentos_gerais" item-name="documentos_gerais"><xsl:call-template name="get-active-tab"><xsl:with-param name="value" select="rows/content/tabcontent_1/fields/documentos_gerais/value"/><xsl:with-param name="class" select="'tab-pane'"/></xsl:call-template><div class="row " id="row-9a61efcf"><div class="gen-column col-sm-3"><div class="gen-inner"><xsl:if test="rows/content/statbox_1"><div class="statbox_container gen-container-item " gen-class="" item-name="statbox_1"><xsl:apply-templates mode="form-hidden-fields" select="rows/content/statbox_1/fields"/><div class="statbox {rows/content/statbox_1/fields/statbox_1_bg/value}"><div class="boxchart"><canvas/></div><div class="number"><xsl:value-of select="rows/content/statbox_1/fields/statbox_1_val/value"/><i class="fa {rows/content/statbox_1/fields/statbox_1_icn/value}" aria-hidden="true"/></div><div class="title"><xsl:value-of select="rows/content/statbox_1/fields/statbox_1_tit/value"/></div><div class="footer"><a href="{rows/content/statbox_1/fields/statbox_1_url/value}" target="modal"><xsl:value-of select="rows/content/statbox_1/fields/statbox_1_txt/value"/></a></div></div></div></xsl:if></div></div><div class="gen-column col-sm-9"><div class="gen-inner"><xsl:if test="rows/content/paragraph_6"><div class="box gen-container-item " gen-class="" item-name="paragraph_6"><xsl:call-template name="box-header"><xsl:with-param name="title" select="rows/content/paragraph_6/@title"/><xsl:with-param name="collapsible" select="'true'"/><xsl:with-param name="collapsed" select="'false'"/></xsl:call-template><div class="box-body"><p><xsl:value-of select="rows/content/paragraph_6/fields/paragraph_6_text/value"/></p></div></div></xsl:if><xsl:if test="rows/content/table_1"><div class="box box-table-contents gen-container-item " gen-class="" item-name="table_1"><div class="box-body "><xsl:apply-templates mode="form-hidden-fields" select="rows/content/table_1/fields"/><div class="table-contents-head"><div class="table-contents-inner"></div></div><div class="table-box"><div class="table-box-inner"><table id="table_1" class="table table-striped   align" exports="null"><thead><tr><xsl:if test="rows/content/table_1/fields/documento"><th align="left" class=" gen-fields-holder"><span><xsl:value-of select="rows/content/table_1/fields/documento/label"/></span></th></xsl:if><xsl:if test="rows/content/table_1/fields/ver"><th align="left" class=" gen-fields-holder"/></xsl:if><xsl:if test="rows/content/table_1/table/context-menu/item"><th class="igrp-table-ctx-th"/></xsl:if></tr></thead><tbody><xsl:for-each select="rows/content/table_1/table/value/row[not(@total='yes')]"><tr><xsl:apply-templates mode="context-param" select="context-menu"/><xsl:if test="documento"><td align="left" data-order="{documento}" data-row="{position()}" data-title="{../../../fields/documento/label}" class="text" item-name="documento"><span class=""><xsl:value-of select="documento"/></span></td></xsl:if><xsl:if test="ver"><td align="left" data-row="{position()}" data-title="{../../label/ver}" class="link" item-name="ver"><xsl:choose><xsl:when test="ver != ''"><a href="{normalize-space(ver)}" class="link bClick btn btn-link " target-fields="" target="_newtab" request-fields="" name="ver"><i class="fa fa-link"/><span><xsl:value-of select="ver_desc"/></span></a></xsl:when><xsl:otherwise><span><xsl:value-of select="ver_desc"/></span></xsl:otherwise></xsl:choose></td></xsl:if><xsl:if test="//rows/content/table_1/table/context-menu/item"><td class="igrp-table-ctx-td"><xsl:apply-templates select="../../context-menu" mode="table-context-inline"><xsl:with-param name="row-params" select="context-menu"/></xsl:apply-templates></td></xsl:if></tr></xsl:for-each></tbody></table></div></div></div></div></xsl:if></div></div></div></div></xsl:if></div></div></div></div></div></div></div></div></div><xsl:call-template name="IGRP-bottom"/></form><script type="text/javascript" src="{$path}/plugins/tabs/igrp.tabs.js?v={$version}"/><script type="text/javascript" src="{$path}/core/igrp/form/igrp.forms.js?v={$version}"/><script type="text/javascript" src="{$path}/core/igrp/table/datatable/jquery.dataTables.min.js?v={$version}"/><script type="text/javascript" src="{$path}/core/igrp/table/datatable/dataTables.bootstrap.min.js?v={$version}"/><script type="text/javascript" src="{$path}/core/igrp/table/igrp.table.js?v={$version}"/></body></html></xsl:template><xsl:include href="../../../xsl/tmpl/IGRP-functions.tmpl.xsl?v=1528465314190"/><xsl:include href="../../../xsl/tmpl/IGRP-variables.tmpl.xsl?v=1528465314190"/><xsl:include href="../../../xsl/tmpl/IGRP-home-include.tmpl.xsl?v=1528465314190"/><xsl:include href="../../../xsl/tmpl/IGRP-utils.tmpl.xsl?v=1528465314190"/><xsl:include href="../../../xsl/tmpl/IGRP-table-utils.tmpl.xsl?v=1528465314190"/></xsl:stylesheet>
