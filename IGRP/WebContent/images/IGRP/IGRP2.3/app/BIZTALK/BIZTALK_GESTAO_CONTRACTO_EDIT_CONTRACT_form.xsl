<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
  <xsl:output method="html" omit-xml-declaration="yes" encoding="ISO-8859-1" doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN" doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"/>
  <xsl:template match="/">
    <html><!--XSL Generator (RED / DIM )--><!--HOME-HEADER--><xsl:call-template name="home-header"/>
      <xsl:apply-templates mode="js_validation" select="rows/content/js_validation"/>
      <body><!--HOME TOP--><xsl:call-template name="home-top-main"/><!--CENTER PANE--><div class="general">
          <div id="content">
            <div class="ui-layout-center">
              <xsl:call-template name="dialog-modal"/><!-- START YOUR CODE HERE --><div class="box-content"><!--PAGE TITLE--><xsl:call-template name="page-title">
                  <xsl:with-param name="title" select="rows/content/title"/>
                </xsl:call-template><!--END PAGE TITLE--><!-- START NOTIFICATION MESSAGES--><xsl:apply-templates mode="notif_messages" select="rows/content/messages">
                  <xsl:with-param name="class" select="'notification'"/>
                </xsl:apply-templates><!-- END NOTIFICATION MESSAGES--><form action="#" method="post" id="formular_default" name="formular_default" class="default_form" enctype="multipart/form-data"><!-- START HIDDEN FILTER--><xsl:apply-templates mode="form-hidden" select="rows/content/filter/value"/><!-- END HIDDEN FILTER--><!-- START HIDDEN FORM--><xsl:apply-templates mode="form-hidden" select="rows/content/form/value"/><!-- END HIDDEN FORM--><!-- START FORM--><!--START TOOL BAR FROM--><xsl:apply-templates mode="tools-bar" select="rows/content/form/tools-bar"/><!--END TOOL BAR FORM--><div class="box-content">
                    <xsl:if test="rows/content/form/label/user_name"/>
                    <xsl:if test="rows/content/form/label/user_id"/>
                    <xsl:if test="rows/content/form/label/cliente">
                      <label>
                        <span>(*)<xsl:value-of select="rows/content/form/label/cliente"/>
                        </span>
                        <input type="LOOKUP" name="{rows/content/form/value/cliente/@name}" value="{rows/content/form/value/cliente}" class="LOOKUP required" maxlength="30">
                          <xsl:call-template name="FIELD_validator">
                            <xsl:with-param name="field" select="rows/content/form/label/cliente"/>
                          </xsl:call-template>
                        </input>
                        <xsl:call-template name="lookup-tool">
                          <xsl:with-param name="page" select="rows/page"/>
                          <xsl:with-param name="ad_hoc" select="'1'"/>
                          <xsl:with-param name="action" select="'LOOKUP'"/>
                          <xsl:with-param name="name" select="rows/content/form/value/cliente/@name"/>
                          <xsl:with-param name="js_lookup" select="rows/content/form/lookup/cliente"/>
                        </xsl:call-template>
                      </label>
                    </xsl:if>
                    <xsl:if test="rows/content/form/label/id_cliente"/>
                    <xsl:if test="rows/content/form/label/codigo">
                      <label>
                        <span>(*)<xsl:value-of select="rows/content/form/label/codigo"/>
                        </span>
                        <input type="text" name="{rows/content/form/value/codigo/@name}" value="{rows/content/form/value/codigo}" class="text required" maxlength="30">
                          <xsl:call-template name="FIELD_validator">
                            <xsl:with-param name="field" select="rows/content/form/label/codigo"/>
                          </xsl:call-template>
                        </input>
                      </label>
                    </xsl:if>
                    <xsl:if test="rows/content/form/label/dt_inicio">
                      <label>
                        <span>(*)<xsl:value-of select="rows/content/form/label/dt_inicio"/>
                        </span>
                        <input type="text" name="{rows/content/form/value/dt_inicio/@name}" value="{rows/content/form/value/dt_inicio}" class="date IGRP_datepicker required" maxlength="30">
                          <xsl:call-template name="FIELD_validator">
                            <xsl:with-param name="field" select="rows/content/form/label/dt_inicio"/>
                          </xsl:call-template>
                        </input>
                      </label>
                    </xsl:if>
                    <xsl:if test="rows/content/form/label/dt_fim">
                      <label>
                        <span>
                          <xsl:value-of select="rows/content/form/label/dt_fim"/>
                        </span>
                        <input type="text" name="{rows/content/form/value/dt_fim/@name}" value="{rows/content/form/value/dt_fim}" class="date IGRP_datepicker" maxlength="30">
                          <xsl:call-template name="FIELD_validator">
                            <xsl:with-param name="field" select="rows/content/form/label/dt_fim"/>
                          </xsl:call-template>
                        </input>
                      </label>
                    </xsl:if>
                    <xsl:if test="rows/content/form/label/notas">
                      <label>
                        <span>
                          <xsl:value-of select="rows/content/form/label/notas"/>
                        </span>
                        <textarea name="{rows/content/form/value/notas/@name}" class="textarea" maxlength="30">
                          <xsl:call-template name="FIELD_validator">
                            <xsl:with-param name="field" select="rows/content/form/label/notas"/>
                          </xsl:call-template>
                          <xsl:value-of select="rows/content/form/value/notas"/>
                        </textarea>
                      </label>
                    </xsl:if><!-- INICIO SEPARADOR documentos--><xsl:if test="rows/content/form/label/documentos">
                      <xsl:if test="rows/content/form/label/documentos">
                        <div rel="documentos" name="sep_documentos">
                          <div class="documentos">
                            <xsl:call-template name="page-title">
                              <xsl:with-param name="title" select="rows/content/form/label/documentos"/>
                              <xsl:with-param name="class" select="'subtitle'"/>
                            </xsl:call-template>
                          </div>
                          <xsl:call-template name="tools-bar-form-group">
                            <xsl:with-param name="rel" select="'documentos'"/>
                            <xsl:with-param name="class" select="'IGRP_addRow'"/>
                          </xsl:call-template>
                          <xsl:if test="rows/content/form/label/anexo">
                            <label>
                              <span>
                                <xsl:value-of select="rows/content/form/label/anexo"/>
                              </span>
                              <input type="text" name="{rows/content/form/value/anexo/@name}" value="{rows/content/form/value/anexo}" class="text" maxlength="30" rel="F_documentos">
                                <xsl:call-template name="FIELD_validator">
                                  <xsl:with-param name="field" select="rows/content/form/label/anexo"/>
                                </xsl:call-template>
                              </input>
                            </label>
                          </xsl:if>
                          <xsl:if test="rows/content/form/label/tipo_documento">
                            <label>
                              <span>(*)<xsl:value-of select="rows/content/form/label/tipo_documento"/>
                              </span>
                              <select name="{rows/content/form/list/tipo_documento/@name}" class="select required" maxlength="30" rel="F_documentos">
                                <xsl:call-template name="FIELD_validator">
                                  <xsl:with-param name="field" select="rows/content/form/label/tipo_documento"/>
                                </xsl:call-template>
                                <xsl:for-each select="rows/content/form/list/tipo_documento/option">
                                  <option value="{value}">
                                    <xsl:if test="@selected='true'">
                                      <xsl:attribute name="selected">selected</xsl:attribute>
                                    </xsl:if>
                                    <xsl:value-of select="text"/>
                                  </option>
                                </xsl:for-each>
                              </select>
                            </label>
                          </xsl:if>
                          <xsl:if test="rows/content/form/label/descricao">
                            <label>
                              <span>(*)<xsl:value-of select="rows/content/form/label/descricao"/>
                              </span>
                              <input type="text" name="{rows/content/form/value/descricao/@name}" value="{rows/content/form/value/descricao}" class="text required" maxlength="50" rel="F_documentos">
                                <xsl:call-template name="FIELD_validator">
                                  <xsl:with-param name="field" select="rows/content/form/label/descricao"/>
                                </xsl:call-template>
                              </input>
                            </label>
                          </xsl:if>
                          <xsl:if test="rows/content/form/label/ficheiro">
                            <label>
                              <span>(*)<xsl:value-of select="rows/content/form/label/ficheiro"/>
                              </span>
                              <input type="file" name="{rows/content/form/value/ficheiro/@name}" value="{rows/content/form/value/ficheiro}" class="file required" maxlength="30" rel="F_documentos">
                                <xsl:call-template name="FIELD_validator">
                                  <xsl:with-param name="field" select="rows/content/form/label/ficheiro"/>
                                </xsl:call-template>
                              </input>
                            </label>
                          </xsl:if><!-- START TABELA LIST--><div class="simple-table" rel="DIV_documentos">
                            <table rel="T_documentos">
                              <thead>
                                <tr>
                                  <xsl:if test="rows/content/form/table/documentos/label/anexo">
                                    <xsl:if test="not(rows/content/form/table/documentos/label/anexo/@visible)">
                                      <th>
                                        <xsl:value-of select="rows/content/form/table/documentos/label/anexo"/>
                                      </th>
                                    </xsl:if>
                                  </xsl:if>
                                  <xsl:if test="rows/content/form/table/documentos/label/tipo_documento">
                                    <xsl:if test="not(rows/content/form/table/documentos/label/tipo_documento/@visible)">
                                      <th>
                                        <xsl:value-of select="rows/content/form/table/documentos/label/tipo_documento"/>
                                      </th>
                                    </xsl:if>
                                  </xsl:if>
                                  <xsl:if test="rows/content/form/table/documentos/label/descricao">
                                    <xsl:if test="not(rows/content/form/table/documentos/label/descricao/@visible)">
                                      <th>
                                        <xsl:value-of select="rows/content/form/table/documentos/label/descricao"/>
                                      </th>
                                    </xsl:if>
                                  </xsl:if>
                                  <xsl:if test="rows/content/form/table/documentos/label/ficheiro">
                                    <xsl:if test="not(rows/content/form/table/documentos/label/ficheiro/@visible)">
                                      <th>
                                        <xsl:value-of select="rows/content/form/table/documentos/label/ficheiro"/>
                                      </th>
                                    </xsl:if>
                                  </xsl:if>
                                  <th align="center" width="10"/>
                                  <th align="center" width="10"/>
                                </tr>
                              </thead>
                              <tbody>
                                <xsl:for-each select="rows/content/form/table/documentos/value/row[not(@total='yes')]">
                                  <xsl:variable name="pos" select="(position()+1) mod 2"/>
                                  <tr>
                                    <xsl:if test="anexo">
                                      <xsl:if test="not(anexo/@visible)">
                                        <td class="table_row{$pos}">
                                          <xsl:value-of select="anexo_desc"/>
                                          <input type="hidden" name="p_anexo_fk" value="{anexo}"/>
                                          <input type="hidden" name="p_anexo_fk_desc" value="{anexo_desc}"/>
                                        </td>
                                      </xsl:if>
                                      <xsl:if test="anexo/@visible = 'false'">
                                        <input type="hidden" name="p_anexo_fk" value="{anexo}"/>
                                        <input type="hidden" name="p_anexo_fk_desc" value="{anexo_desc}"/>
                                      </xsl:if>
                                    </xsl:if>
                                    <xsl:if test="tipo_documento">
                                      <xsl:if test="not(tipo_documento/@visible)">
                                        <td class="table_row{$pos}">
                                          <xsl:value-of select="tipo_documento_desc"/>
                                          <input type="hidden" name="p_tipo_documento_fk" value="{tipo_documento}"/>
                                          <input type="hidden" name="p_tipo_documento_fk_desc" value="{tipo_documento_desc}"/>
                                        </td>
                                      </xsl:if>
                                      <xsl:if test="tipo_documento/@visible = 'false'">
                                        <input type="hidden" name="p_tipo_documento_fk" value="{tipo_documento}"/>
                                        <input type="hidden" name="p_tipo_documento_fk_desc" value="{tipo_documento_desc}"/>
                                      </xsl:if>
                                    </xsl:if>
                                    <xsl:if test="descricao">
                                      <xsl:if test="not(descricao/@visible)">
                                        <td class="table_row{$pos}">
                                          <xsl:value-of select="descricao_desc"/>
                                          <input type="hidden" name="p_descricao_fk" value="{descricao}"/>
                                          <input type="hidden" name="p_descricao_fk_desc" value="{descricao_desc}"/>
                                        </td>
                                      </xsl:if>
                                      <xsl:if test="descricao/@visible = 'false'">
                                        <input type="hidden" name="p_descricao_fk" value="{descricao}"/>
                                        <input type="hidden" name="p_descricao_fk_desc" value="{descricao_desc}"/>
                                      </xsl:if>
                                    </xsl:if>
                                    <xsl:if test="ficheiro">
                                      <xsl:if test="not(ficheiro/@visible)">
                                        <td class="table_row{$pos}">
                                          <xsl:choose>
                                            <xsl:when test="ficheiro != ''">
                                              <a href="{ficheiro}" class="_blank2" name="ficheiro">
                                                <xsl:value-of select="ficheiro_desc"/>
                                              </a>
                                            </xsl:when>
                                            <xsl:otherwise>
                                              <xsl:value-of select="ficheiro_desc"/>
                                            </xsl:otherwise>
                                          </xsl:choose>
                                          <input type="hidden" name="p_ficheiro_fk" value="{ficheiro}"/>
                                          <input type="hidden" name="p_ficheiro_fk_desc" value="{ficheiro_desc}"/>
                                        </td>
                                      </xsl:if>
                                      <xsl:if test="ficheiro/@visible = 'false'">
                                        <input type="hidden" name="p_ficheiro_fk" value="{ficheiro}"/>
                                        <input type="hidden" name="p_ficheiro_fk_desc" value="{ficheiro_desc}"/>
                                      </xsl:if>
                                    </xsl:if>
                                    <td align="center" width="10">
                                      <xsl:if test="not(@nodelete)">
                                        <img alt="apagar" src="{$path_tmpl}/img/icon/ctx-delete.png" name="btn_documentos_del" rel="documentos" class="IGRP_delRow"/>
                                      </xsl:if>
                                    </td>
                                    <td align="center" width="10">
                                      <xsl:if test="not(@noupdate)">
                                        <img alt="editar" src="{$path_tmpl}/img/icon/edit.png" name="btn_documentos_edit" rel="documentos" class="IGRP_editRow"/>
                                      </xsl:if>
                                    </td>
                                    <input type="hidden" name="p_documentos_id" value="{documentos_id}"/>
                                  </tr>
                                </xsl:for-each>
                              </tbody>
                            </table>
                          </div><!-- END TABELA LIST--></div>
                      </xsl:if>
                    </xsl:if><!-- INICIO SEPARADOR intervenientes--><xsl:if test="rows/content/form/label/intervenientes">
                      <xsl:if test="rows/content/form/label/intervenientes">
                        <div rel="intervenientes" name="sep_intervenientes">
                          <div class="intervenientes">
                            <xsl:call-template name="page-title">
                              <xsl:with-param name="title" select="rows/content/form/label/intervenientes"/>
                              <xsl:with-param name="class" select="'subtitle'"/>
                            </xsl:call-template>
                          </div>
                          <xsl:call-template name="tools-bar-form-group">
                            <xsl:with-param name="rel" select="'intervenientes'"/>
                            <xsl:with-param name="class" select="'IGRP_addRow'"/>
                          </xsl:call-template>
                          <xsl:if test="rows/content/form/label/interneniente">
                            <label>
                              <span>(*)<xsl:value-of select="rows/content/form/label/interneniente"/>
                              </span>
                              <input type="LOOKUP" name="{rows/content/form/value/interneniente/@name}" value="{rows/content/form/value/interneniente}" class="LOOKUP required" maxlength="30" rel="F_intervenientes">
                                <xsl:call-template name="FIELD_validator">
                                  <xsl:with-param name="field" select="rows/content/form/label/interneniente"/>
                                </xsl:call-template>
                              </input>
                              <xsl:call-template name="lookup-tool">
                                <xsl:with-param name="page" select="rows/page"/>
                                <xsl:with-param name="ad_hoc" select="'1'"/>
                                <xsl:with-param name="action" select="'LOOKUP'"/>
                                <xsl:with-param name="name" select="rows/content/form/value/interneniente/@name"/>
                                <xsl:with-param name="js_lookup" select="rows/content/form/lookup/interneniente"/>
                              </xsl:call-template>
                            </label>
                          </xsl:if>
                          <xsl:if test="rows/content/form/label/tipo">
                            <label>
                              <span>(*)<xsl:value-of select="rows/content/form/label/tipo"/>
                              </span>
                              <select name="{rows/content/form/list/tipo/@name}" class="select required" maxlength="30" rel="F_intervenientes">
                                <xsl:call-template name="FIELD_validator">
                                  <xsl:with-param name="field" select="rows/content/form/label/tipo"/>
                                </xsl:call-template>
                                <xsl:for-each select="rows/content/form/list/tipo/option">
                                  <option value="{value}">
                                    <xsl:if test="@selected='true'">
                                      <xsl:attribute name="selected">selected</xsl:attribute>
                                    </xsl:if>
                                    <xsl:value-of select="text"/>
                                  </option>
                                </xsl:for-each>
                              </select>
                            </label>
                          </xsl:if><!-- START TABELA LIST--><div class="simple-table" rel="DIV_intervenientes">
                            <table rel="T_intervenientes">
                              <thead>
                                <tr>
                                  <xsl:if test="rows/content/form/table/intervenientes/label/interneniente">
                                    <xsl:if test="not(rows/content/form/table/intervenientes/label/interneniente/@visible)">
                                      <th>
                                        <xsl:value-of select="rows/content/form/table/intervenientes/label/interneniente"/>
                                      </th>
                                    </xsl:if>
                                  </xsl:if>
                                  <xsl:if test="rows/content/form/table/intervenientes/label/tipo">
                                    <xsl:if test="not(rows/content/form/table/intervenientes/label/tipo/@visible)">
                                      <th>
                                        <xsl:value-of select="rows/content/form/table/intervenientes/label/tipo"/>
                                      </th>
                                    </xsl:if>
                                  </xsl:if>
                                  <th align="center" width="10"/>
                                  <th align="center" width="10"/>
                                </tr>
                              </thead>
                              <tbody>
                                <xsl:for-each select="rows/content/form/table/intervenientes/value/row[not(@total='yes')]">
                                  <xsl:variable name="pos" select="(position()+1) mod 2"/>
                                  <tr>
                                    <xsl:if test="interneniente">
                                      <xsl:if test="not(interneniente/@visible)">
                                        <td class="table_row{$pos}">
                                          <xsl:value-of select="interneniente_desc"/>
                                          <input type="hidden" name="p_interneniente_fk" value="{interneniente}"/>
                                          <input type="hidden" name="p_interneniente_fk_desc" value="{interneniente_desc}"/>
                                        </td>
                                      </xsl:if>
                                      <xsl:if test="interneniente/@visible = 'false'">
                                        <input type="hidden" name="p_interneniente_fk" value="{interneniente}"/>
                                        <input type="hidden" name="p_interneniente_fk_desc" value="{interneniente_desc}"/>
                                      </xsl:if>
                                    </xsl:if>
                                    <xsl:if test="tipo">
                                      <xsl:if test="not(tipo/@visible)">
                                        <td class="table_row{$pos}">
                                          <xsl:value-of select="tipo_desc"/>
                                          <input type="hidden" name="p_tipo_fk" value="{tipo}"/>
                                          <input type="hidden" name="p_tipo_fk_desc" value="{tipo_desc}"/>
                                        </td>
                                      </xsl:if>
                                      <xsl:if test="tipo/@visible = 'false'">
                                        <input type="hidden" name="p_tipo_fk" value="{tipo}"/>
                                        <input type="hidden" name="p_tipo_fk_desc" value="{tipo_desc}"/>
                                      </xsl:if>
                                    </xsl:if>
                                    <td align="center" width="10">
                                      <xsl:if test="not(@nodelete)">
                                        <img alt="apagar" src="{$path_tmpl}/img/icon/ctx-delete.png" name="btn_intervenientes_del" rel="intervenientes" class="IGRP_delRow"/>
                                      </xsl:if>
                                    </td>
                                    <td align="center" width="10">
                                      <xsl:if test="not(@noupdate)">
                                        <img alt="editar" src="{$path_tmpl}/img/icon/edit.png" name="btn_intervenientes_edit" rel="intervenientes" class="IGRP_editRow"/>
                                      </xsl:if>
                                    </td>
                                    <input type="hidden" name="p_intervenientes_id" value="{intervenientes_id}"/>
                                    <input type="hidden" name="p_interveniente_id_fk" value="{interveniente_id}"/>
                                    <input type="hidden" name="p_interveniente_id_fk_desc" value="{interveniente_id_desc}"/>
                                  </tr>
                                </xsl:for-each>
                              </tbody>
                            </table>
                          </div><!-- END TABELA LIST--></div>
                      </xsl:if>
                    </xsl:if><!-- INICIO SEPARADOR produtos--><xsl:if test="rows/content/form/label/produtos">
                      <xsl:if test="rows/content/form/label/produtos">
                        <div rel="produtos" name="sep_produtos">
                          <div class="produtos">
                            <xsl:call-template name="page-title">
                              <xsl:with-param name="title" select="rows/content/form/label/produtos"/>
                              <xsl:with-param name="class" select="'subtitle'"/>
                            </xsl:call-template>
                          </div>
                          <xsl:call-template name="tools-bar-form-group">
                            <xsl:with-param name="rel" select="'produtos'"/>
                            <xsl:with-param name="class" select="'IGRP_addRow'"/>
                          </xsl:call-template>
                          <xsl:if test="rows/content/form/label/produto">
                            <label>
                              <span>(*)<xsl:value-of select="rows/content/form/label/produto"/>
                              </span>
                              <input type="LOOKUP" name="{rows/content/form/value/produto/@name}" value="{rows/content/form/value/produto}" class="LOOKUP required" maxlength="30" rel="F_produtos">
                                <xsl:call-template name="FIELD_validator">
                                  <xsl:with-param name="field" select="rows/content/form/label/produto"/>
                                </xsl:call-template>
                              </input>
                              <xsl:call-template name="lookup-tool">
                                <xsl:with-param name="page" select="rows/page"/>
                                <xsl:with-param name="ad_hoc" select="'1'"/>
                                <xsl:with-param name="action" select="'LOOKUP'"/>
                                <xsl:with-param name="name" select="rows/content/form/value/produto/@name"/>
                                <xsl:with-param name="js_lookup" select="rows/content/form/lookup/produto"/>
                              </xsl:call-template>
                            </label>
                          </xsl:if><!-- START TABELA LIST--><div class="simple-table" rel="DIV_produtos">
                            <table rel="T_produtos">
                              <thead>
                                <tr>
                                  <xsl:if test="rows/content/form/table/produtos/label/produto">
                                    <xsl:if test="not(rows/content/form/table/produtos/label/produto/@visible)">
                                      <th>
                                        <xsl:value-of select="rows/content/form/table/produtos/label/produto"/>
                                      </th>
                                    </xsl:if>
                                  </xsl:if>
                                  <th align="center" width="10"/>
                                  <th align="center" width="10"/>
                                </tr>
                              </thead>
                              <tbody>
                                <xsl:for-each select="rows/content/form/table/produtos/value/row[not(@total='yes')]">
                                  <xsl:variable name="pos" select="(position()+1) mod 2"/>
                                  <tr>
                                    <xsl:if test="produto">
                                      <xsl:if test="not(produto/@visible)">
                                        <td class="table_row{$pos}">
                                          <xsl:value-of select="produto_desc"/>
                                          <input type="hidden" name="p_produto_fk" value="{produto}"/>
                                          <input type="hidden" name="p_produto_fk_desc" value="{produto_desc}"/>
                                        </td>
                                      </xsl:if>
                                      <xsl:if test="produto/@visible = 'false'">
                                        <input type="hidden" name="p_produto_fk" value="{produto}"/>
                                        <input type="hidden" name="p_produto_fk_desc" value="{produto_desc}"/>
                                      </xsl:if>
                                    </xsl:if>
                                    <td align="center" width="10">
                                      <xsl:if test="not(@nodelete)">
                                        <img alt="apagar" src="{$path_tmpl}/img/icon/ctx-delete.png" name="btn_produtos_del" rel="produtos" class="IGRP_delRow"/>
                                      </xsl:if>
                                    </td>
                                    <td align="center" width="10">
                                      <xsl:if test="not(@noupdate)">
                                        <img alt="editar" src="{$path_tmpl}/img/icon/edit.png" name="btn_produtos_edit" rel="produtos" class="IGRP_editRow"/>
                                      </xsl:if>
                                    </td>
                                    <input type="hidden" name="p_produtos_id" value="{produtos_id}"/>
                                    <input type="hidden" name="p_produto_id_fk" value="{produto_id}"/>
                                    <input type="hidden" name="p_produto_id_fk_desc" value="{produto_id_desc}"/>
                                  </tr>
                                </xsl:for-each>
                              </tbody>
                            </table>
                          </div><!-- END TABELA LIST--></div>
                      </xsl:if>
                    </xsl:if>
                  </div><!-- END FORM--></form><!-- END YOUR CODE HERE --></div>
            </div><!-- START HOME BOTTOM --><xsl:call-template name="home-side"/>
          </div>
        </div><!--HOME-FOOTER--></body>
    </html>
  </xsl:template>
  <xsl:include href="../../xsl/tmpl/IGRP-home-include.tmpl.xsl?v=20140619"/>
  <xsl:include href="../../xsl/tmpl/IGRP-JSvalidation.tmpl.xsl?v=20140619"/>
  <xsl:include href="../../xsl/tmpl/IGRP-field-validator.tmpl.xsl?v=20140619"/>
  <xsl:include href="../../xsl/tmpl/IGRP-others.tmpl.xsl?v=20140619"/>
</xsl:stylesheet>
