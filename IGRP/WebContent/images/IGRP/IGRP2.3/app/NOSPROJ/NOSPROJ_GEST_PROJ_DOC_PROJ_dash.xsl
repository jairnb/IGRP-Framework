<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
  <xsl:output method="html" omit-xml-declaration="yes" encoding="ISO-8859-1" doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN" doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"/>
  <xsl:template match="/">
    <html><!--XSL Generator (RED/DIM 2015)--><head><!--HOME-HEADER--><xsl:call-template name="home-header"/>
      </head>
      <body class="bodyColor">
        <div class="IGRP_overlay"/><!--HOME TOP--><xsl:call-template name="home-top-main"/><!--HOME--><div id="igrp-bodyPage"><!--HOME LEFT--><xsl:call-template name="home-slide-menu"/><!--HOME RIGTH--><div class="bodyPageRigth"><!-- START YOUR CODE HERE --><!-- SHOW TABLET AND MOBILE TITLE --><div class="box-head showTabMobile">
              <xsl:value-of select="rows/content/title"/>
            </div><!-- SHOW NOTIFICATION MESSAGES--><xsl:apply-templates mode="notif_messages" select="rows/content/messages"/><!-- START SIMPLE-MENU --><xsl:apply-templates mode="simple-menu" select="rows/content/menu"/><!-- END SIMPLE-MENU --><form action="#" method="post" id="formular_default" name="formular_default" class="default_filter"><!-- SHOW HIDDEN--><xsl:apply-templates mode="form-hidden" select="rows/content/filter/value"/>
              <xsl:apply-templates mode="form-hidden" select="rows/content/form/value"/><!-- START FILTER--><div class="box-content resetPadding">
                <div class="col">
                  <xsl:if test="rows/content/filter/label/id_tp_doc">
                    <div class="col-1-4 item" item-name="id_tp_doc">
                      <div class="igrp_item select">
                        <label>
                          <xsl:if test="string-length(rows/content/filter/label/id_tp_doc) > 45">
                            <xsl:attribute name="title">
                              <xsl:value-of select="rows/content/filter/label/id_tp_doc" disable-output-escaping="yes"/>
                            </xsl:attribute>
                          </xsl:if>
                          <xsl:value-of select="rows/content/filter/label/id_tp_doc" disable-output-escaping="yes"/>
                        </label>
                        <select name="{rows/content/filter/list/id_tp_doc/@name}" chosen="select" id="{rows/content/filter/list/id_tp_doc/@name}" data-placeholder="{rows/content/filter/list/id_tp_doc/option[position() = 1]}" class="select">
                          <xsl:call-template name="FIELD_validator">
                            <xsl:with-param name="field" select="rows/content/filter/label/id_tp_doc"/>
                          </xsl:call-template>
                          <option value=""/>
                          <xsl:for-each select="rows/content/filter/list/id_tp_doc/option[position() != 1]">
                            <option value="{value}">
                              <xsl:if test="@selected='true'">
                                <xsl:attribute name="selected">selected</xsl:attribute>
                              </xsl:if>
                              <xsl:value-of select="text"/>
                            </option>
                          </xsl:for-each>
                        </select>
                        <div class="_clear"/>
                      </div>
                      <div class="_clear"/>
                    </div>
                  </xsl:if>
                  <div class="_clear"/>
                </div><!--START TOOL BAR FILTER--><xsl:apply-templates mode="button-bar" select="rows/content/filter/tools-bar"/><!--END TOOL BAR FILTER--></div><!-- END FILTER--><!-- START TABELA--><div class="col addBorderLeftRight borderColor"><!--START TOOL BAR TABLE--><xsl:apply-templates mode="tools-bar" select="rows/content/table/tools-bar">
                  <xsl:with-param name="isTable" select="'true'"/>
                  <xsl:with-param name="rel" select="'table'"/>
                </xsl:apply-templates><!--END TOOL BAR TABLE--><div class="box-table table-responsive">
                  <div class="table-container"><!--  CONTEXT-MENU  --><xsl:apply-templates mode="context-menu" select="rows/content/table/context-menu">
                      <xsl:with-param name="contextId" select="'table'"/>
                    </xsl:apply-templates>
                    <table class="IGRP_table default-table" data-control="table">
                      <thead>
                        <tr>
                          <xsl:if test="rows/content/table/label/id_tipo_doc">
                            <th align="">
                              <xsl:value-of select="rows/content/table/label/id_tipo_doc"/>
                            </th>
                          </xsl:if>
                          <xsl:if test="rows/content/table/label/nome">
                            <th align="">
                              <xsl:value-of select="rows/content/table/label/nome"/>
                            </th>
                          </xsl:if>
                          <xsl:if test="rows/content/table/label/documento">
                            <th align="">
                              <xsl:value-of select="rows/content/table/label/documento"/>
                            </th>
                          </xsl:if>
                          <xsl:if test="rows/content/table/label/dt_alteracao">
                            <th align="">
                              <xsl:value-of select="rows/content/table/label/dt_alteracao"/>
                            </th>
                          </xsl:if>
                          <xsl:if test="rows/content/table/label/uti_alteracao">
                            <th align="">
                              <xsl:value-of select="rows/content/table/label/uti_alteracao"/>
                            </th>
                          </xsl:if>
                        </tr>
                      </thead>
                      <tbody>
                        <xsl:for-each select="rows/content/table/value/row[not(@total='yes')]">
                          <tr row="{position()}" class="IGRP_contextMenu">
                            <xsl:apply-templates mode="context-param" select="context-menu"/>
                            <xsl:if test="id_tipo_doc">
                              <td align="" data-row="{position()}" data-title="{../../label/id_tipo_doc}" class="text">
                                <xsl:value-of select="id_tipo_doc"/>
                                <xsl:apply-templates mode="cxtmenu-resp" select="//rows/content/table/context-menu">
                                  <xsl:with-param name="ctx" select="context-menu"/>
                                </xsl:apply-templates>
                              </td>
                            </xsl:if>
                            <xsl:if test="nome">
                              <td align="" data-row="{position()}" data-title="{../../label/nome}" class="text">
                                <xsl:value-of select="nome"/>
                              </td>
                            </xsl:if>
                            <xsl:if test="documento">
                              <td align="" data-row="{position()}" data-title="{../../label/documento}" class="link">
                                <xsl:choose>
                                  <xsl:when test="documento != ''">
                                    <a href="{documento}" class="link bClick" target="_blank" name="documento">
                                      <xsl:value-of select="documento_desc" disable-output-escaping="yes"/>
                                    </a>
                                  </xsl:when>
                                  <xsl:otherwise>
                                    <xsl:value-of select="documento_desc" disable-output-escaping="yes"/>
                                  </xsl:otherwise>
                                </xsl:choose>
                              </td>
                            </xsl:if>
                            <xsl:if test="dt_alteracao">
                              <td align="" data-row="{position()}" data-title="{../../label/dt_alteracao}" class="date">
                                <xsl:value-of select="dt_alteracao"/>
                              </td>
                            </xsl:if>
                            <xsl:if test="uti_alteracao">
                              <td align="" data-row="{position()}" data-title="{../../label/uti_alteracao}" class="text">
                                <xsl:value-of select="uti_alteracao"/>
                              </td>
                            </xsl:if>
                          </tr>
                        </xsl:for-each>
                      </tbody>
                    </table>
                  </div>
                </div>
              </div><!-- END TABELA--></form><!-- END YOUR CODE HERE --><div class="_clear"/>
          </div>
          <div class="_clear"/>
        </div><!--FOOTER PAGE--><div id="igrp-footerPage">
          <xsl:call-template name="footer"/>
        </div>
      </body>
    </html>
  </xsl:template>
  <xsl:include href="../../xsl/tmpl/IGRP-home-include.tmpl.xsl?v=20150825"/>
  <xsl:include href="../../xsl/tmpl/IGRP-setAttribute.tmpl.xsl?v=20150825"/>
  <xsl:include href="../../xsl/tmpl/IGRP-slide-menu.tmpl.xsl?v=20150825"/>
  <xsl:include href="../../xsl/tmpl/IGRP-menu.tmpl.xsl?v=20150825"/>
  <xsl:include href="../../xsl/tmpl/IGRP-context.tmpl.xsl?v=20150825"/>
</xsl:stylesheet>
