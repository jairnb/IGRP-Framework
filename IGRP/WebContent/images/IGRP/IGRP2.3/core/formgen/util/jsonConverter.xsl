<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:ext="http://exslt.org/common" exclude-result-prefixes="ext"
	version="1.0">
	<xsl:output method="text" omit-xml-declaration="yes"
		encoding="UTF-8" doctype-system="about:legacy-compat" />
	<xsl:strip-space elements="*" />
	<xsl:template match="/">
		<xsl:variable name="form" select="rows/content/form" />
		<xsl:variable name="formFields"
			select="$form/label/*[not(@rel)][not(@container='true')][not(@type='list')]" />
		<xsl:variable name="formLength" select="count($formFields)" />
		<xsl:variable name="oContainers" select="$form/label/*[@container='true']" />
		<xsl:variable name="oContainersLength" select="count($oContainers)" />
		<xsl:variable name="fToolsBar" select="$form/tools-bar" />
		<xsl:variable name="filter" select="rows/content/filter" />
		<xsl:variable name="filterFields" select="$filter/label/*" />
		<xsl:variable name="table" select="rows/content/table" />
		<xsl:variable name="tableFields" select="$table/label/*" />
		<xsl:variable name="hasLeft"
			select="rows/content/menu/group/@align = 'left' or rows/content/*[@align='left']" />
		<xsl:variable name="hasRight"
			select="rows/content/menu/group/@align = 'right' or rows/content/*[@align='right']" />
		<xsl:variable name="mainColumnClass">
			<xsl:choose>
				<xsl:when test="$hasLeft and $hasRight">
					col-sm-6
				</xsl:when>
				<xsl:when test="$hasLeft and not($hasRight)">
					col-sm-9
				</xsl:when>
				<xsl:when test="not($hasLeft) and $hasRight">
					col-sm-9
				</xsl:when>
				<xsl:otherwise>
					col-sm-12
				</xsl:otherwise>
			</xsl:choose>
		</xsl:variable>
		{"plsql": {
		<xsl:if test="rows/plsql/with_biztalk !=''">
			"biztalk":
			<xsl:value-of select="rows/plsql/with_biztalk" />
			,
		</xsl:if>
		"gentype": "java","html": "
		<xsl:value-of select="rows/plsql/package_html" />
		","instance": "
		<xsl:value-of select="rows/plsql/package_instance" />
		",
		<xsl:if test="rows/plsql/with_label !=''">
			"label":
			<xsl:value-of select="rows/plsql/with_label" />
			,
		</xsl:if>
		"package": "
		<xsl:value-of select="rows/plsql/package_db" />
		",
		<xsl:if test="rows/plsql/with_label !=''">
			"replace":
			<xsl:value-of select="rows/plsql/with_replace" />
			,
		</xsl:if>
		"subversionpath": "","table": "
		<xsl:value-of select="rows/plsql/table_name" />
		"},"rows":[ { "columns" : [{ "size" : "col-sm-12", "containers":[{
		"proprieties":{"tag":"page_title","type":"sectionheader","text":"
		<xsl:value-of select="//rows/content/title" />
		" }}] }] }, {"columns":[
		<xsl:if test="$hasLeft">
			<xsl:call-template name="leftAndRight">
				<xsl:with-param name="position" select="'left'" />
			</xsl:call-template>
			,
		</xsl:if>
		{"size":"
		<xsl:value-of select="$mainColumnClass" />
		","containers":
		<xsl:text>[</xsl:text>
		<xsl:if test="rows/content/view">
			<xsl:apply-templates select="rows/content/view"
				mode="container">
				<xsl:with-param name="type" select="'view'" />
			</xsl:apply-templates>
			<xsl:if
				test="$formLength &gt; 0 or count($fToolsBar/item) &gt; 0 or $oContainersLength &gt; 0 or $filter or $tableFields">
				,
			</xsl:if>
		</xsl:if>
		<xsl:if test="$fToolsBar/item">
			<xsl:variable name="tb-items">
				<xsl:for-each select="$fToolsBar/item">
					<xsl:variable name="tlb_name">
						<xsl:call-template name="sanitize">
							<xsl:with-param name="tag" select="title" />
						</xsl:call-template>
					</xsl:variable>
					<xsl:element name="{$tlb_name}">
						<xsl:attribute name="target"><xsl:value-of
							select="target" /></xsl:attribute>
						<xsl:attribute name="type">button</xsl:attribute>
						<xsl:attribute name="img"><xsl:value-of
							select="img" /></xsl:attribute>
						<xsl:value-of select="title" />
					</xsl:element>
				</xsl:for-each>
			</xsl:variable>
			<xsl:variable name="tbar-items" select="ext:node-set($tb-items)/*" />
			<xsl:apply-templates select="$fToolsBar" mode="container">
				<xsl:with-param name="type" select="'toolsbar'" />
				<xsl:with-param name="fields" select="$tbar-items" />
				<xsl:with-param name="tag" select="'toolsbar'" />
			</xsl:apply-templates>
			<xsl:if
				test="$formLength &gt; 0 or $oContainersLength &gt; 0 or $filter or $tableFields">
				,
			</xsl:if>
		</xsl:if>
		<xsl:if test="$form">
			<xsl:if test="$formLength &gt; 0 and count($formFields) &gt; 0">
				<xsl:apply-templates select="$form" mode="container">
					<xsl:with-param name="type" select="'form'" />
					<xsl:with-param name="fields" select="$formFields" />
				</xsl:apply-templates>
				<xsl:if test="$oContainersLength &gt; 0 or $filter or $tableFields">
					,
				</xsl:if>
			</xsl:if>
			<xsl:if test="$oContainersLength &gt; 0">
				<xsl:for-each select="$oContainers">
					<xsl:variable name="tag" select="name()" />
					<xsl:variable name="type">
						<xsl:choose>
							<xsl:when test="@type='list'">
								table
							</xsl:when>
							<xsl:otherwise>
								<xsl:value-of select="@type" />
							</xsl:otherwise>
						</xsl:choose>
					</xsl:variable>
					<xsl:variable name="oContainersFields" select="$form/label/*[@rel=$tag]" />
					<xsl:if test="count($oContainersFields) &gt; 0">
						<xsl:apply-templates select="." mode="container">
							<xsl:with-param name="type" select="$type" />
							<xsl:with-param name="fields" select="$oContainersFields" />
						</xsl:apply-templates>
					</xsl:if>
					<xsl:if test="position()!=$oContainersLength">
						,
					</xsl:if>
				</xsl:for-each>
				<xsl:if test="$filter or $tableFields">
					,
				</xsl:if>
			</xsl:if>
		</xsl:if>
		<xsl:if test="$filter">
			<xsl:apply-templates select="." mode="container">
				<xsl:with-param name="type" select="'form'" />
				<xsl:with-param name="fields" select="$filterFields" />
				<xsl:with-param name="toolsbar" select="$filter/tools-bar" />
			</xsl:apply-templates>
			<xsl:if test="$tableFields">
				,
			</xsl:if>
		</xsl:if>
		<xsl:if test="$tableFields">
			<xsl:if test="$table/tools-bar">
				<xsl:variable name="tb-items">
					<xsl:for-each select="$table/tools-bar/item">
						<xsl:variable name="tlb_name">
							<xsl:call-template name="sanitize">
								<xsl:with-param name="tag" select="title" />
							</xsl:call-template>
						</xsl:variable>
						<xsl:element name="{$tlb_name}">
							<xsl:attribute name="target"><xsl:value-of
								select="target" /></xsl:attribute>
							<xsl:attribute name="type">button</xsl:attribute>
							<xsl:attribute name="img"><xsl:value-of
								select="img" /></xsl:attribute>
							<xsl:value-of select="title" />
						</xsl:element>
					</xsl:for-each>
				</xsl:variable>
				<xsl:variable name="tbar-items" select="ext:node-set($tb-items)/*" />
				<xsl:apply-templates select="$table/tools-bar"
					mode="container">
					<xsl:with-param name="type" select="'toolsbar'" />
					<xsl:with-param name="fields" select="$tbar-items" />
					<xsl:with-param name="tag" select="'toolsbar'" />
				</xsl:apply-templates>
				,
			</xsl:if>
			<xsl:apply-templates select="$table" mode="container">
				<xsl:with-param name="type" select="'table'" />
				<xsl:with-param name="fields" select="$tableFields" />
			</xsl:apply-templates>
		</xsl:if>
		<xsl:text>]</xsl:text>
		}
		<xsl:if test="$hasRight">
			,
			<xsl:call-template name="leftAndRight">
				<xsl:with-param name="position" select="'right'" />
			</xsl:call-template>
		</xsl:if>
		]}]}
	</xsl:template>
	<xsl:template name="leftAndRight">
		<xsl:param name="position" />
		{"size":"col-sm-3","containers":[
		<xsl:if test="rows/content/menu/group/@align = $position">
			{"proprieties":{"title":"
			<xsl:value-of select="rows/content/menu/group/@title" />
			","code":"
			<xsl:value-of select="rows/content/menu/group/@code" />
			","type":"tabmenu"},"fields":[
			<xsl:variable name="menuItemsLength" select="count(rows/content/menu/group/item)" />
			<xsl:for-each select="rows/content/menu/group/item">
				{"tag":"
				<xsl:value-of select="concat(name(),position())" />
				","label":"
				<xsl:value-of select="title" />
				","code" :"
				<xsl:value-of select="@code" />
				"}
				<xsl:if test="position() &lt; $menuItemsLength">
					,
				</xsl:if>
			</xsl:for-each>
			]}
			<xsl:if test="rows/content/*/@align=$position">
				,
			</xsl:if>
		</xsl:if>
		<xsl:if test="rows/content/*/@align=$position">
			<xsl:variable name="graphicsLength"
				select="count(rows/content/*[@align=$position])" />
			<xsl:for-each select="rows/content/*[@align=$position]">
				<xsl:if test="@type='chart'">
					{"proprieties":{"title":"
					<xsl:value-of select="caption" />
					","hasTitle":true,"type":"chart","tag":"
					<xsl:value-of select="name()" />
					","height":
					<xsl:value-of select="height" />
					,"chart_type":"
					<xsl:value-of select="chart_type" />
					"},"fields":[]}
					<xsl:if test="position() != last()">
						,
					</xsl:if>
				</xsl:if>
			</xsl:for-each>
		</xsl:if>
		]}
	</xsl:template>
	<xsl:template name="container" mode="container" match="*">
		<xsl:param name="tag" select="name()" />
		<xsl:param name="type" select="@type" />
		<xsl:param name="toolsbar" />
		<xsl:param name="fields" select="label/*" />
		<xsl:variable name="title">
			<xsl:choose>
				<xsl:when test="//rows/content/form/label/*[name() = $tag]">
					<xsl:value-of select="//rows/content/form/label/*[name() = $tag]" />
				</xsl:when>
				<xsl:otherwise>
					<xsl:value-of select="$tag" />
				</xsl:otherwise>
			</xsl:choose>
		</xsl:variable>
		<xsl:variable name="hasTitle">
			<xsl:choose>
				<xsl:when test="//rows/content/form/label/*[name() = $tag]">
					true
				</xsl:when>
				<xsl:otherwise>
					false
				</xsl:otherwise>
			</xsl:choose>
		</xsl:variable>
		<xsl:variable name="count" select="count($fields)" />
		{"proprieties":{"title":"
		<xsl:value-of select="$title" />
		","hasTitle":
		<xsl:value-of select="$hasTitle" />
		,"collapsible":false,"type":"
		<xsl:value-of select="$type" />
		","tag":"
		<xsl:value-of select="$tag" />
		"},"fields":[
		<xsl:for-each select="$fields">
			<xsl:apply-templates select="." mode="fields">
				<xsl:with-param name="parent_type" select="'form'" />
			</xsl:apply-templates>
			<xsl:if test="position()!=$count">
				<xsl:text>,</xsl:text>
			</xsl:if>
		</xsl:for-each>
		<xsl:if test="$toolsbar">
			<xsl:for-each select="$toolsbar/item">
				<xsl:variable name="tlb_name">
					<xsl:call-template name="sanitize">
						<xsl:with-param name="tag" select="title" />
					</xsl:call-template>
				</xsl:variable>
				,{"label":"
				<xsl:value-of select="title" />
				","tag" : "
				<xsl:value-of select="$tlb_name" />
				","target" : "
				<xsl:value-of select="target" />
				","type" : "button","img" : "
				<xsl:value-of select="img" />
				"}
			</xsl:for-each>
		</xsl:if>
		],"place":"c"}
	</xsl:template>
	<xsl:template name="fields" mode="fields" match="*">
		<xsl:param name="parent_type" />
		<xsl:param name="count" />
		<xsl:variable name="attrsLength" select="count(@*)" />
		{"label":"
		<xsl:value-of select="." />
		","tag":"
		<xsl:value-of select="name()" />
		"
		<xsl:if test="$attrsLength &gt; 0">
			,
			<xsl:for-each select="@*[ not( name() = 'rel') and not( name() = 'code' )]">
				<xsl:variable name="attrValue">
					<xsl:choose>
						<xsl:when
							test="local-name() = 'required' or local-name() = 'readonly' or local-name() = 'change' or local-name() = 'disabled' ">
							<xsl:choose>
								<xsl:when test="local-name() = . or . = 'true'">
									true
								</xsl:when>
								<xsl:otherwise>
									false
								</xsl:otherwise>
							</xsl:choose>
						</xsl:when>
						<xsl:otherwise>
							<xsl:variable name="vvalue">
								<xsl:choose>
									<xsl:when test="name() = 'type'">
										<xsl:call-template name="getFieldType">
											<xsl:with-param name="type" select="." />
										</xsl:call-template>
									</xsl:when>
									<xsl:otherwise>
										<xsl:value-of select="." />
									</xsl:otherwise>
								</xsl:choose>
							</xsl:variable>
							<xsl:call-template name="LowerCase">
								<xsl:with-param name="text" select="$vvalue" />
							</xsl:call-template>
						</xsl:otherwise>
					</xsl:choose>
				</xsl:variable>
				<xsl:variable name="aspas">
					<xsl:choose>
						<xsl:when
							test="local-name() = 'required' or local-name() = 'readonly' or local-name() = 'change' or local-name() = 'disabled' or local-name() = 'maxlength'">
							<xsl:text />
						</xsl:when>
						<xsl:otherwise>
							<xsl:text>"</xsl:text>
						</xsl:otherwise>
					</xsl:choose>
				</xsl:variable>
				<xsl:if test="name() = 'type' and . = 'js_passa_valor'">
					"lookup_parser" : true,
				</xsl:if>
				<xsl:if test="name() = 'type' and . = 'total'">
					"total_footer" : true,
				</xsl:if>
				<xsl:if
					test="($parent_type = 'form' and name() = 'type') and (. = 'plaintext' or .= 'separator')">
					"size":"12",
				</xsl:if>
				"
				<xsl:call-template name="LowerCase">
					<xsl:with-param name="text" select="name()" />
				</xsl:call-template>
				":
				<xsl:value-of select="$aspas" />
				<xsl:value-of select="$attrValue" />
				<xsl:value-of select="$aspas" />
				<xsl:if test="position()!=last()">
					,
				</xsl:if>
			</xsl:for-each>
		</xsl:if>
		}
	</xsl:template>
	<xsl:template name="getFieldType">
		<xsl:param name="type" />
		<xsl:choose>
			<xsl:when test="$type = 'hora'">
				time
			</xsl:when>
			<xsl:when test="$type = 'vkb_all'">
				virtualkeyboard
			</xsl:when>
			<xsl:when test="$type = 'image'">
				img
			</xsl:when>
			<xsl:when test="$type = 'color_td'">
				color
			</xsl:when>
			<xsl:when test="$type = 'js_passa_valor'">
				text
			</xsl:when>
			<xsl:when test="$type = 'total'">
				number
			</xsl:when>
			<xsl:otherwise>
				<xsl:value-of select="$type" />
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<xsl:template name="LowerCase">
		<xsl:param name="text" />
		<xsl:value-of
			select="translate($text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz')" />
	</xsl:template>
	<xsl:template name="removeSpecialChars">
		<xsl:param name="text" />
		<xsl:variable name="vAllowedSymbols"
			select="'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz'" />
		<xsl:value-of
			select="translate($text,translate($text, $vAllowedSymbols, ''),'')" />
	</xsl:template>
	<xsl:template name="sanitize">
		<xsl:param name="tag" />
		<xsl:variable name="low">
			<xsl:call-template name="LowerCase">
				<xsl:with-param name="text"
					select="translate(translate($tag,' .-','___'),'-\/','')" />
			</xsl:call-template>
		</xsl:variable>
		<xsl:call-template name="removeSpecialChars">
			<xsl:with-param name="text" select="$low" />
		</xsl:call-template>
	</xsl:template>
</xsl:stylesheet>