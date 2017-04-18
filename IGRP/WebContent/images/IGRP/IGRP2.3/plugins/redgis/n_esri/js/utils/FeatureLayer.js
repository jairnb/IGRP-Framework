/*
 COPYRIGHT 2009 ESRI

 TRADE SECRETS: ESRI PROPRIETARY AND CONFIDENTIAL
 Unpublished material - all rights reserved under the
 Copyright Laws of the United States and applicable international
 laws, treaties, and conventions.

 For additional information, contact:
 Environmental Systems Research Institute, Inc.
 Attn: Contracts and Legal Services Department
 380 New York Street
 Redlands, California, 92373
 USA

 email: contracts@esri.com
 */
//>>built
define("esri/layers/FeatureLayer","dojo/_base/declare dojo/_base/connect dojo/_base/lang dojo/_base/array dojo/_base/json dojo/_base/Deferred dojo/date/locale dojo/sniff dojo/io-query dojo/dom-construct dojo/i18n esri/kernel esri/lang esri/request esri/deferredUtils esri/SpatialReference esri/symbols/SimpleMarkerSymbol esri/symbols/SimpleLineSymbol esri/symbols/SimpleFillSymbol esri/symbols/jsonUtils esri/renderers/SimpleRenderer esri/renderers/UniqueValueRenderer esri/renderers/jsonUtils esri/tasks/QueryTask esri/tasks/query esri/tasks/FeatureSet esri/geometry/Extent esri/geometry/jsonUtils esri/geometry/normalizeUtils esri/layers/GraphicsLayer esri/layers/Field esri/layers/TimeInfo esri/layers/FeatureType esri/layers/FeatureTemplate esri/layers/FeatureEditResult esri/layers/LabelClass esri/layers/SnapshotMode esri/layers/OnDemandMode esri/layers/SelectionMode esri/layers/TrackManager dojo/i18n!esri/nls/jsapi dojo/has!extend-esri?esri/layers/agscommon".split(" "),
function(t,A,q,h,E,F,N,G,O,P,Q,B,r,C,u,H,R,S,T,U,J,V,W,X,K,I,L,Y,Z,$,aa,ba,ca,da,D,ea,M,fa,ga,ha){t=t($,{declaredClass:"esri.layers.FeatureLayer",invalidParams:"query contains one or more unsupported parameters",_eventMap:{"add-attachment-complete":["result"],"before-apply-edits":["adds","updates","deletes"],"delete-attachments-complete":["results"],"edits-complete":["adds","updates","deletes"],"query-attachment-infos-complete":["results"],"query-count-complete":["count"],"query-features-complete":["featureSet"],
"query-ids-complete":["objectIds"],"query-related-features-complete":["featureSets"],"selection-complete":["features","method"],"update-end":["error","info"]},constructor:function(a,c){this.i18n=Q.getLocalization("esri","jsapi");c=c||{};this._outFields=c.outFields;this._loadCallback=c.loadCallback;var b=c._usePatch;this._usePatch=null===b||void 0===b?!0:b;this._trackIdField=c.trackIdField;this.objectIdField=c.objectIdField;this._maxOffset=c.maxAllowableOffset;this._optEditable=c.editable;this._optAutoGen=
c.autoGeneralize;this.editSummaryCallback=c.editSummaryCallback;this.userId=c.userId;this.userIsAdmin=c.userIsAdmin;this.useMapTime=c.hasOwnProperty("useMapTime")?!!c.useMapTime:!0;this.source=c.source;this.gdbVersion=c.gdbVersion;this.orderByFields=c.orderByFields;this._selectedFeatures={};this._selectedFeaturesArr=[];this._newFeatures=[];this._deletedFeatures={};this._ulid=this._getUniqueId();b=this.constructor;switch(this.mode=r.isDefined(c.mode)?c.mode:b.MODE_ONDEMAND){case b.MODE_SNAPSHOT:this._mode=
new M(this);this._isSnapshot=!0;break;case b.MODE_ONDEMAND:this._tileWidth=c.tileWidth||512;this._tileHeight=c.tileHeight||512;this._mode=new fa(this);this.latticeTiling=c.latticeTiling;break;case b.MODE_SELECTION:this._mode=new ga(this),this._isSelOnly=!0}this._initLayer=q.hitch(this,this._initLayer);this._selectHandler=q.hitch(this,this._selectHandler);this._editable=!1;if(q.isObject(a)&&a.layerDefinition)return this._collection=!0,this.mode=b.MODE_SNAPSHOT,this._initLayer(a),this;this._task=new X(this.url,
{source:this.source,gdbVersion:this.gdbVersion});b=this._url.path;this._fserver=!1;-1!==b.search(/\/FeatureServer\//i)&&(this._fserver=!0);var d=c.resourceInfo;d?this._initLayer(d):(this.source&&(d={source:this.source.toJson()},this._url.query=q.mixin(this._url.query,{layer:E.toJson(d)})),this.gdbVersion&&(this._url.query=q.mixin(this._url.query,{gdbVersion:this.gdbVersion})),C({url:b,content:q.mixin({f:"json"},this._url.query),callbackParamName:"callback",load:this._initLayer,error:this._errorHandler}));
this.registerConnectEvents()},_initLayer:function(a,c){if(a||c){this._json=a;this._findCredential();if(this.credential&&this.credential.ssl||a&&a._ssl)this._useSSL(),this._task._useSSL();this._collection&&(this._mode=new M(this),this._isSnapshot=!0,this._featureSet=a.featureSet,this._nextId=a.nextObjectId,a=a.layerDefinition);if(a.hasOwnProperty("capabilities")){var b=this.capabilities=a.capabilities;b&&-1!==b.toLowerCase().indexOf("editing")?this._editable=!0:this._editable=!1}else this._collection||
(this._editable=this._fserver);r.isDefined(this._optEditable)&&(this._editable=this._optEditable,delete this._optEditable);this._json=E.toJson(this._json);if(this.isEditable())delete this._maxOffset;else if(this.mode!==this.constructor.MODE_SNAPSHOT&&("esriGeometryPolyline"===a.geometryType||"esriGeometryPolygon"===a.geometryType))this._autoGeneralize=r.isDefined(this._optAutoGen)?this._optAutoGen:this.mode===this.constructor.MODE_ONDEMAND,delete this._optAutoGen;var b=a.effectiveMinScale||a.minScale,
d=a.effectiveMaxScale||a.maxScale;!this._hasMin&&b&&this.setMinScale(b);!this._hasMax&&d&&this.setMaxScale(d);this.layerId=a.id;this.name=a.name;this.description=a.description;this.copyright=a.copyrightText;this.type=a.type;this.geometryType=a.geometryType;this.displayField=a.displayField;this.defaultDefinitionExpression=a.definitionExpression;this.fullExtent=new L(a.extent);this.initialExtent=new L(this.fullExtent.toJson());this.fullExtent.spatialReference&&(this.spatialReference=new H(this.fullExtent.spatialReference.toJson()));
this.defaultVisibility=a.defaultVisibility;if("esriGeometryPoint"===this.geometryType||"esriGeometryMultipoint"===this.geometryType)this.latticeTiling=!1;this.indexedFields=a.indexedFields;this.maxRecordCount=a.maxRecordCount;this.canModifyLayer=a.canModifyLayer;this.supportsStatistics=a.supportsStatistics;this.supportsAdvancedQueries=this._collection?!1:a.supportsAdvancedQueries;this.hasLabels=a.hasLabels;this.canScaleSymbols=a.canScaleSymbols;this.supportsRollbackOnFailure=a.supportsRollbackOnFailure;
this.syncCanReturnChanges=a.syncCanReturnChanges;this.isDataVersioned=a.isDataVersioned;this.editFieldsInfo=a.editFieldsInfo;this.ownershipBasedAccessControlForFeatures=a.ownershipBasedAccessControlForFeatures;this.editFieldsInfo&&this.ownershipBasedAccessControlForFeatures&&(this.creatorField=this.editFieldsInfo.creatorField);this.relationships=a.relationships;this.allowGeometryUpdates=r.isDefined(a.allowGeometryUpdates)?a.allowGeometryUpdates:!0;this._isTable="Table"===this.type;for(var e=this.fields=
[],d=a.fields,b=0;b<d.length;b++)e.push(new aa(d[b]));if(!this.objectIdField){this.objectIdField=a.objectIdField;if(!this.objectIdField){d=a.fields;for(b=0;b<d.length;b++)if(e=d[b],"esriFieldTypeOID"===e.type){this.objectIdField=e.name;break}}this.objectIdField||console.debug("esri.layers.FeatureLayer: "+r.substitute({url:this.url},"objectIdField is not set [url: ${url}]"))}if(!r.isDefined(this._nextId)){d=this.objectIdField;e=-1;if(this._collection&&d)for(var f=(b=this._featureSet)&&b.features,l=
f?f.length:0,g,b=0;b<l;b++)g=(g=f[b].attributes)&&g[d],g>e&&(e=g);this._nextId=e+1}this.globalIdField=a.globalIdField;if(b=this.typeIdField=a.typeIdField)if(b=!this._getField(b)&&this._getField(b,!0))this.typeIdField=b.name;this.visibilityField=a.visibilityField;if(d=a.defaultSymbol)this.defaultSymbol=U.fromJson(d);var m=this.types=[],k=a.types,n,z,e=(b=this.editFieldsInfo)&&b.creatorField,f=b&&b.editorField;g=e||f;l=[];if(k)for(b=0;b<k.length;b++)n=new ca(k[b]),z=n.templates,g&&(z&&z.length)&&(l=
l.concat(z)),m.push(n);k=a.templates;n=this.templates=[];if(k)for(b=0;b<k.length;b++)m=new da(k[b]),g&&l.push(m),n.push(m);for(b=0;b<l.length;b++)if(g=q.getObject("prototype.attributes",!1,l[b]))e&&delete g[e],f&&delete g[f];if(b=a.timeInfo)this.timeInfo=new ba(b),this._startTimeField=b.startTimeField,this._endTimeField=b.endTimeField,this._startTimeField&&this._endTimeField&&(this._twoTimeFields=!0),this._trackIdField?b.trackIdField=this._trackIdField:this._trackIdField=b.trackIdField;this.hasAttachments=
!this._collection&&a.hasAttachments?!0:!1;this.htmlPopupType=a.htmlPopupType;var b=a.drawingInfo,p;if(e=b&&b.labelingInfo)this.labelingInfo=h.map(e,function(a){return new ea(a)});if(!this.renderer)if(b&&b.renderer){if(p=b.renderer,this.setRenderer(W.fromJson(p)),"classBreaks"===p.type&&this.renderer.setMaxInclusive(!0),!this._collection){var s=p.type,d=[];p=this.renderer;switch(s){case "simple":d.push(p.symbol);break;case "uniqueValue":case "classBreaks":d.push(p.defaultSymbol),d=d.concat(h.map(p.infos,
function(a){return a.symbol}))}var d=h.filter(d,r.isDefined),v=this._url.path+"/images/",w=this._getToken();h.forEach(d,function(a){var b=a.url;b&&(-1===b.search(/https?\:/)&&-1===b.indexOf("data:")&&(a.url=v+b),w&&-1!==a.url.search(/https?\:/)&&(a.url+="?token\x3d"+w))})}}else if(d)k=this.types,0<k.length?(p=new V(this.defaultSymbol,this.typeIdField),h.forEach(k,function(a){p.addValue(a.id,a.symbol)})):p=new J(this.defaultSymbol),this.setRenderer(p);else if(!this._isTable){switch(this.geometryType){case "esriGeometryPoint":case "esriGeometryMultipoint":s=
new R;break;case "esriGeometryPolyline":s=new S;break;case "esriGeometryPolygon":s=new T}this.setRenderer(s?new J(s):null)}s=b&&b.transparency||0;!r.isDefined(this.opacity)&&0<s&&(this.opacity=1-s/100);this.version=a.currentVersion;this.version||(this.version="capabilities"in a||"drawingInfo"in a||"hasAttachments"in a||"htmlPopupType"in a||"relationships"in a||"timeInfo"in a||"typeIdField"in a||"types"in a?10:9.3);if((G("ie")||G("safari"))&&this.isEditable()&&10.02>this.version)this._ts=!0;this.loaded=
!0;this._fixRendererFields();this._checkFields();this._updateCaps();s=function(){this.onLoad(this);var a=this._loadCallback;a&&(delete this._loadCallback,a(this))};this._collection?(this._fireUpdateStart(),b=this._featureSet,delete this._featureSet,this._mode._drawFeatures(new I(b)),this._fcAdded=!0,s.call(this)):this._forceIdentity(s)}},onRendererChange:function(){this.inherited(arguments);var a=this._getRenderer();this._ager=!(!a||!a.observationAger||!a.observationRenderer);if(a){var c=[],a=h.filter([a,
a.observationRenderer,a.latestObservationRenderer,a.trackRenderer],r.isDefined);h.forEach(a,function(a){q.isFunction(a.attributeField)||c.push(a.attributeField);c.push(a.attributeField2);c.push(a.attributeField3)},this);this._rendererFields=h.filter(c,r.isDefined)}else this._rendererFields=[];this.loaded&&(this._fixRendererFields(),this._checkFields(this._rendererFields),this._collection&&(this._typesDirty=!0))},redraw:function(){this.inherited(arguments);this._trackManager&&this._trackManager.container&&
this._trackManager.container.redraw()},_evalSDRenderer:function(){this.inherited(arguments);var a=this._getRenderer();this._ager=!(!a||!a.observationAger||!a.observationRenderer);this._trackManager&&this._trackManager.container&&this._trackManager.container.setRenderer(a&&a.trackRenderer)},_setMap:function(a){var c=this.inherited(arguments),b=this._mode,d=this;b&&b.initialize(a);this.geometryType&&this.attr("data-geometry-type",this.geometryType.replace(/esriGeometry/i,"").toLowerCase());this._addHandle=
this.on("graphic-node-add",function(a){a=a.graphic.attributes;(a=d._selectedFeatures[a&&a[d.objectIdField]])&&a.attr("data-selected","")});return c},_unsetMap:function(a){var c=this._mode;c&&c.suspend();this._trackManager&&(this._trackManager.destroy(),this._trackManager=null);A.disconnect(this._zoomConnect);A.disconnect(this._addHandle);this._zoomConnect=this._addHandle=null;this._toggleTime(!1);this.inherited("_unsetMap",arguments)},refresh:function(){var a=this._mode;a&&a.refresh()},getOutFields:function(){return h.filter(this._getOutFields(),
function(a){return"*"===a||!!this._getField(a)},this)},setEditable:function(a){if(!this._collection)return console.log("FeatureLayer:setEditable - this functionality is not yet supported for layer in a feature service"),this;if(!this.loaded)return this._optEditable=a,this;var c=this._editable;this._editable=a;this._updateCaps();if(c!==a)this.onCapabilitiesChange();return this},getEditCapabilities:function(a){var c={canCreate:!1,canUpdate:!1,canDelete:!1};if(!this.loaded||!this.isEditable())return c;
var b=a&&a.feature;a=a&&a.userId;var d=h.map(this.capabilities?this.capabilities.toLowerCase().split(","):[],q.trim),e=-1<h.indexOf(d,"editing"),f=e&&-1<h.indexOf(d,"create"),c=e&&-1<h.indexOf(d,"update"),d=e&&-1<h.indexOf(d,"delete"),l=this.ownershipBasedAccessControlForFeatures,g=this.editFieldsInfo,m=g&&g.creatorField,g=g&&g.realm,b=(b=b&&b.attributes)&&m?b[m]:void 0,k=!!this.userIsAdmin,m=!l||k||!(!l.allowOthersToUpdate&&!l.allowUpdateToOthers),l=!l||k||!(!l.allowOthersToDelete&&!l.allowDeleteToOthers);
if(k||e&&!f&&!c&&!d)f=c=d=!0;e={canCreate:f,canUpdate:c,canDelete:d};null===b?(e.canUpdate=c&&m,e.canDelete=d&&l):""!==b&&b&&((a=a||this.getUserId())&&g&&(a=a+"@"+g),a.toLowerCase()!==b.toLowerCase()&&(e.canUpdate=c&&m,e.canDelete=d&&l));return e},getUserId:function(){var a;this.loaded&&(a=this.credential&&this.credential.userId||this.userId||"");return a},setUserIsAdmin:function(a){this.userIsAdmin=a},setEditSummaryCallback:function(a){this.editSummaryCallback=a},getEditSummary:function(a,c,b){b=
r.isDefined(b)?b:(new Date).getTime();var d="";b=this.getEditInfo(a,c,b);(c=c&&c.callback||this.editSummaryCallback)&&(b=c(a,b)||"");if(q.isString(b))d=b;else{if(b){a=b.action;c=b.userId;var e=b.timeValue,f=0;a&&f++;c&&f++;r.isDefined(e)&&f++;1<f&&(d=("edit"===a?"edit":"create")+(c?"User":"")+(r.isDefined(e)?b.displayPattern:""))}d=d&&r.substitute(b,this.i18n.layers.FeatureLayer[d])}return d},getEditInfo:function(a,c,b){if(this.loaded){b=r.isDefined(b)?b:(new Date).getTime();c=c&&c.action||"last";
var d=this.editFieldsInfo,e=d&&d.creatorField,f=d&&d.creationDateField,l=d&&d.editorField,d=d&&d.editDateField,l=(a=a&&a.attributes)&&l?a[l]:void 0,d=a&&d?a[d]:null,e=this._getEditData(a&&e?a[e]:void 0,a&&f?a[f]:null,b);b=this._getEditData(l,d,b);var g;switch(c){case "creation":g=e;break;case "edit":g=b;break;case "last":g=b||e}g&&(g.action=g===b?"edit":"creation");return g}},_getEditData:function(a,c,b){var d,e,f;r.isDefined(c)&&(e=b-c,f=0>e?"Full":6E4>e?"Seconds":12E4>e?"Minute":36E5>e?"Minutes":
72E5>e?"Hour":864E5>e?"Hours":6048E5>e?"WeekDay":"Full");if(void 0!==a||f)d=d||{},d.userId=a,f&&(a=N.format,b=new Date(c),d.minutes=Math.floor(e/6E4),d.hours=Math.floor(e/36E5),d.weekDay=a(b,{datePattern:"EEEE",selector:"date"}),d.formattedDate=a(b,{selector:"date"}),d.formattedTime=a(b,{selector:"time"}),d.displayPattern=f,d.timeValue=c);return d},isEditable:function(){return!(!this._editable&&!this.userIsAdmin)},setMaxAllowableOffset:function(a){this.isEditable()||(this._maxOffset=a);return this},
getMaxAllowableOffset:function(){return this._maxOffset},setAutoGeneralize:function(a){if(this.loaded){if(!this.isEditable()&&this.mode!==this.constructor.MODE_SNAPSHOT&&("esriGeometryPolyline"===this.geometryType||"esriGeometryPolygon"===this.geometryType))if(this._autoGeneralize=a){if((a=this._map)&&a.loaded)this._maxOffset=Math.floor(a.extent.getWidth()/a.width)}else delete this._maxOffset}else this._optAutoGen=a;return this},setGDBVersion:function(a){if(!this._collection&&a!==this.gdbVersion&&
(a||this.gdbVersion))this.gdbVersion=a,this._task.gdbVersion=a,this._url.query=q.mixin(this._url.query,{gdbVersion:a}),this.loaded&&(this.clearSelection(),this._map&&this.refresh()),this.onGDBVersionChange();return this},setDefinitionExpression:function(a){this._defnExpr=a;(a=this._mode)&&a.propertyChangeHandler(1);return this},getDefinitionExpression:function(){return this._defnExpr},setTimeDefinition:function(a){this._isSnapshot?(this._timeDefn=a,(a=this._mode)&&a.propertyChangeHandler(2)):console.log("FeatureLayer.setTimeDefinition: layer in on-demand or selection mode does not support time definitions. Layer id \x3d "+
this.id+", Layer URL \x3d "+this.url);return this},getTimeDefinition:function(){return this._timeDefn},setTimeOffset:function(a,c){this._timeOffset=a;this._timeOffsetUnits=c;var b=this._mode;b&&b.propertyChangeHandler(0);return this},setUseMapTime:function(a){this.useMapTime=a;this._toggleTime(!this.suspended);(a=this._mode)&&a.propertyChangeHandler(0)},selectFeatures:function(a,c,b,d){c=c||this.constructor.SELECTION_NEW;a=this._getShallowClone(a);var e=this._map,f=u._fixDfd(new F(u._dfdCanceller));
a.outFields=this.getOutFields();a.returnGeometry=!0;e&&(a.outSpatialReference=new H(e.spatialReference.toJson()));if(!this._applyQueryFilters(a,!0))return a={features:[]},this._selectHandler(a,c,b,d,f),f;if(e=this._canDoClientSideQuery(a))a={features:this._doQuery(a,e)},this._selectHandler(a,c,b,d,f);else{if(this._collection)return this._resolve([Error("FeatureLayer::selectFeatures - "+this.invalidParams)],null,d,f,!0),f;var l=this;this._ts&&(a._ts=(new Date).getTime());(f._pendingDfd=this._task.execute(a)).addCallbacks(function(a){l._selectHandler(a,
c,b,d,f)},function(a){l._resolve([a],null,d,f,!0)})}return f},getSelectedFeatures:function(){var a=this._selectedFeatures,c=[],b;for(b in a)a.hasOwnProperty(b)&&c.push(a[b]);return c},clearSelection:function(a){var c=this._selectedFeatures,b=this._mode,d;for(d in c)c.hasOwnProperty(d)&&(this._unSelectFeatureIIf(d,b),b._removeFeatureIIf(d));this._selectedFeatures={};this._isSelOnly&&b._applyTimeFilter(!0);if(!a)this.onSelectionClear();return this},setSelectionSymbol:function(a){if(this._selectionSymbol=
a){var c=this._selectedFeatures,b;for(b in c)c.hasOwnProperty(b)&&c[b].setSymbol(a)}return this},getSelectionSymbol:function(){return this._selectionSymbol},__msigns:[{n:"applyEdits",c:5,a:[{i:0},{i:1}],e:4,f:1}],applyEdits:function(a,c,b,d,e,f){var l=f.assembly,g=f.dfd;this._applyNormalized(a,l&&l[0]);this._applyNormalized(c,l&&l[1]);this.onBeforeApplyEdits(a,c,b);var m={},k=this.objectIdField,l={f:"json"},n=!1;if(this._collection)f={},f.addResults=a?h.map(a,function(){n=!0;return{objectId:this._nextId++,
success:!0}},this):null,f.updateResults=c?h.map(c,function(a){n=!0;var b=a.attributes[k];m[b]=a;return{objectId:b,success:!0}},this):null,f.deleteResults=b?h.map(b,function(a){n=!0;return{objectId:a.attributes[k],success:!0}},this):null,n&&this._editHandler(f,a,m,d,e,g);else{a&&0<a.length&&(l.adds=this._convertFeaturesToJson(a,0,1),n=!0);if(c&&0<c.length){for(f=0;f<c.length;f++){var z=c[f];m[z.attributes[k]]=z}l.updates=this._convertFeaturesToJson(c,0,0,1);n=!0}if(b&&0<b.length){c=[];for(f=0;f<b.length;f++)c.push(b[f].attributes[k]);
l.deletes=c.join(",");n=!0}if(n){var p=this;return C({url:this._url.path+"/applyEdits",content:q.mixin(l,this._url.query),callbackParamName:"callback",load:function(b){p._editHandler(b,a,m,d,e,g)},error:function(a){p._resolve([a],null,e,g,!0)}},{usePost:!0})}}},queryFeatures:function(a,c,b){return this._query("execute","onQueryFeaturesComplete",a,c,b)},queryRelatedFeatures:function(a,c,b){return this._query("executeRelationshipQuery","onQueryRelatedFeaturesComplete",a,c,b)},queryIds:function(a,c,
b){return this._query("executeForIds","onQueryIdsComplete",a,c,b)},queryCount:function(a,c,b){return this._query("executeForCount","onQueryCountComplete",a,c,b)},queryAttachmentInfos:function(a,c,b){var d=this._url.path+"/"+a+"/attachments",e=new F(u._dfdCanceller),f=this;e._pendingDfd=C({url:d,content:q.mixin({f:"json"},this._url.query),callbackParamName:"callback",load:function(b){b=b.attachmentInfos;var g;h.forEach(b,function(b){g=O.objectToQuery({gdbVersion:f._url.query&&f._url.query.gdbVersion,
layer:f._url.query&&f._url.query.layer,token:f._getToken()});b.url=d+"/"+b.id+(g?"?"+g:"");b.objectId=a});f._resolve([b],"onQueryAttachmentInfosComplete",c,e)},error:function(a){f._resolve([a],null,b,e,!0)}});return e},addAttachment:function(a,c,b,d){return this._sendAttachment("add",a,c,b,d)},updateAttachment:function(a,c,b,d,e){b.appendChild(P.create("input",{type:"hidden",name:"attachmentId",value:c}));return this._sendAttachment("update",a,b,d,e)},deleteAttachments:function(a,c,b,d){var e=this._url.path+
"/"+a+"/deleteAttachments",f=new F(u._dfdCanceller),l=this;c={f:"json",attachmentIds:c.join(",")};f._pendingDfd=C({url:e,content:q.mixin(c,this._url.query),callbackParamName:"callback",load:q.hitch(this,function(c){c=c.deleteAttachmentResults;c=h.map(c,function(b){b=new D(b);b.attachmentId=b.objectId;b.objectId=a;return b});l._resolve([c],"onDeleteAttachmentsComplete",b,f)}),error:function(a){l._resolve([a],null,d,f,!0)}},{usePost:!0});return f},addType:function(a){var c=this.types;if(c){if(h.some(c,
function(b){return b.id==a.id?!0:!1}))return!1;c.push(a)}else this.types=[a];return this._typesDirty=!0},deleteType:function(a){if(this._collection){var c=this.types;if(c){var b=-1;h.some(c,function(c,e){return c.id==a?(b=e,!0):!1});if(-1<b)return this._typesDirty=!0,c.splice(b,1)[0]}}},toJson:function(){var a=this._json;if(a=q.isString(a)?E.fromJson(a):q.clone(a)){var a=a.layerDefinition?a:{layerDefinition:a},c=a.layerDefinition,b=this._collection;if(b&&this._typesDirty){c.types=h.map(this.types||
[],function(a){return a.toJson()});var d=this.renderer,e=c.drawingInfo;d&&!e&&(e=c.drawingInfo={});e&&(d&&-1===d.declaredClass.indexOf("TemporalRenderer"))&&(e.renderer=d.toJson())}d=null;if(!b||this._fcAdded)d={geometryType:c.geometryType,features:this._convertFeaturesToJson(this.graphics,!0)};a.featureSet=q.mixin({},a.featureSet||{},d);b&&(a.nextObjectId=this._nextId,c.capabilities=this.capabilities);return a}},onSelectionComplete:function(){},onSelectionClear:function(){},onBeforeApplyEdits:function(){},
onEditsComplete:function(){},onQueryFeaturesComplete:function(){},onQueryRelatedFeaturesComplete:function(){},onQueryIdsComplete:function(){},onQueryCountComplete:function(){},onQueryAttachmentInfosComplete:function(){},onAddAttachmentComplete:function(){},onUpdateAttachmentComplete:function(){},onDeleteAttachmentsComplete:function(){},onCapabilitiesChange:function(){},onGDBVersionChange:function(){},onQueryLimitExceeded:function(){},_forceIdentity:function(a){var c=this,b=this._url&&this._url.path;
(this.ownershipBasedAccessControlForFeatures||this.userIsAdmin)&&!this._getToken()&&b&&B.id&&B.id._hasPortalSession()&&B.id._doPortalSignIn(b)?B.id.getCredential(b).then(function(){c._findCredential();a.call(c)},function(){a.call(c)}):a.call(this)},_updateCaps:function(){var a=this._editable,c=q.trim(this.capabilities||""),b=h.map(c?c.split(","):[],q.trim),d=h.map(c?c.toLowerCase().split(","):[],q.trim),c=h.indexOf(d,"editing"),e,d={Create:h.indexOf(d,"create"),Update:h.indexOf(d,"update"),Delete:h.indexOf(d,
"delete")};if(a&&-1===c)b.push("Editing");else if(!a&&-1<c){a=[c];for(e in d)-1<d[e]&&a.push(d[e]);a.sort();for(e=a.length-1;0<=e;e--)b.splice(a[e],1)}this.capabilities=b.join(",")},_counter:{value:0},_getUniqueId:function(){return this._counter.value++},onSuspend:function(){this.inherited(arguments);this._toggleTime(!1);var a=this._mode;a&&a.suspend()},onResume:function(a){this.inherited(arguments);this._toggleTime(!0);this._updateMaxOffset();var c=this._mode,b=this._map,d=this._getRenderer();if(a.firstOccurrence){this._fixRendererFields();
this._checkFields();this.clearSelection();if(this.timeInfo&&(this._trackIdField||d&&(d.latestObservationRenderer||d.trackRenderer)))this._trackManager=new ha(this),this._trackManager.initialize(b);this._zoomConnect=A.connect(b,"onZoomEnd",this,this._updateMaxOffset)}c&&(a.firstOccurrence?c.startup():c.resume())},_updateMaxOffset:function(){var a=this._map;a&&a.loaded&&this._autoGeneralize&&(this._maxOffset=Math.floor(a.extent.getWidth()/a.width))},_toggleTime:function(a){var c=this._map;a&&this.timeInfo&&
this.useMapTime&&c?(this._mapTimeExtent=c.timeExtent,this._timeConnect||(this._timeConnect=A.connect(c,"onTimeExtentChange",this,this._timeChangeHandler))):(this._mapTimeExtent=null,A.disconnect(this._timeConnect),this._timeConnect=null)},_timeChangeHandler:function(a){this._mapTimeExtent=a;(a=this._mode)&&a.propertyChangeHandler(0)},_getOffsettedTE:function(a){var c=this._timeOffset,b=this._timeOffsetUnits;return a&&c&&b?a.offset(-1*c,b):a},_getTimeOverlap:function(a,c){return a&&c?a.intersection(c):
a||c},_getTimeFilter:function(a){var c=this.getTimeDefinition(),b;if(c&&(b=this._getTimeOverlap(c,null),!b))return[!1];if(a){if(a=b?this._getTimeOverlap(a,b):a,!a)return[!1]}else a=b;return[!0,a]},_getAttributeFilter:function(a){var c=this.getDefinitionExpression();return a?c?"("+c+") AND ("+a+")":a:c},_applyQueryFilters:function(a,c){a.where=this._getAttributeFilter(a.where);a.maxAllowableOffset=this._maxOffset;c&&this.supportsAdvancedQueries&&(a.orderByFields=a.orderByFields||this.getOrderByFields());
if(this.timeInfo){var b=this._getTimeFilter(a.timeExtent);if(b[0])a.timeExtent=b[1];else return!1}return!0},_add:function(a){var c=this._selectionSymbol,b=a.attributes,d=this.visibilityField;c&&this._isSelOnly&&a.setSymbol(c);if(d&&b&&b.hasOwnProperty(d))a[b[d]?"show":"hide"]();return this.add.apply(this,arguments)},_remove:function(){return this.remove.apply(this,arguments)},_canDoClientSideQuery:function(a){var c=[],b=this._map;if(!this._isTable&&b&&!a.text&&!(a.where&&a.where!==this.getDefinitionExpression()||
a.orderByFields&&a.orderByFields.length)){var d=this._isSnapshot,e=this._isSelOnly,f=a.geometry;if(f)if(!e&&a.spatialRelationship===K.SPATIAL_REL_INTERSECTS&&"extent"===f.type&&(d||b.extent.contains(f)))c.push(1);else return;if(b=a.objectIds)if(d)c.push(2);else{var f=b.length,l=this._mode,g=0,m;for(m=0;m<f;m++)l._getFeature(b[m])&&g++;if(g===f)c.push(2);else return}if(this.timeInfo)if(a=a.timeExtent,b=this._mapTimeExtent,d)a&&c.push(3);else if(e){if(a)return}else if(b)if(-1!==h.indexOf(c,2))a&&c.push(3);
else return;else if(0<c.length)a&&c.push(3);else if(a)return;return 0<c.length?c:null}},_doQuery:function(a,c,b){var d=[],e=this._mode,f=this.objectIdField,l,g;if(-1!==h.indexOf(c,2)){var d=[],m=a.objectIds;g=m.length;for(l=0;l<g;l++){var k=e._getFeature(m[l]);k&&d.push(k)}if(0===d.length)return[]}if(-1!==h.indexOf(c,1)){e=0<d.length?d:this.graphics;g=e.length;m=a.geometry._normalize(null,!0);d=[];for(l=0;l<g;l++){var k=e[l],n=k.geometry;n&&(this.normalization&&m.length?(m[0].intersects(n)||m[1].intersects(n))&&
d.push(k):m.intersects(n)&&d.push(k))}if(0===d.length)return[]}-1!==h.indexOf(c,3)&&this.timeInfo&&(e=0<d.length?d:this.graphics,a=a.timeExtent,d=this._filterByTime(e,a.startTime,a.endTime).match);return b?h.map(d,function(a){return a.attributes[f]},this):d},_filterByTime:function(a,c,b){var d=this._startTimeField,e=this._endTimeField,f;this._twoTimeFields||(f=d||e);var l=r.isDefined,g=[],m=[],k,n=a.length,h,p;c=c?c.getTime():-Infinity;b=b?b.getTime():Infinity;if(f)for(k=0;k<n;k++)h=a[k],p=h.attributes,
d=p[f],d>=c&&d<=b?g.push(h):m.push(h);else for(k=0;k<n;k++)h=a[k],p=h.attributes,f=p[d],p=p[e],f=l(f)?f:-Infinity,p=l(p)?p:Infinity,f>=c&&f<=b||p>=c&&p<=b||c>=f&&b<=p?g.push(h):m.push(h);return{match:g,noMatch:m}},_resolve:function(a,c,b,d,e){c&&this[c].apply(this,a);b&&b.apply(null,a);d&&u._resDfd(d,a,e)},_getShallowClone:function(a){var c=new K,b;for(b in a)a.hasOwnProperty(b)&&(c[b]=a[b]);return c},_query:function(a,c,b,d,e){var f=this,l=this._map,g=new F(u._dfdCanceller),m=b,k=function(b,e){if(!e&&
"execute"===a&&!f._isTable){var l=b.features,h=f._mode,m=f.objectIdField,k;for(k=l.length-1;0<=k;k--){var n=h._getFeature(l[k].attributes[m]);n&&l.splice(k,1,n)}}f._resolve([b],c,d,g)};if("executeRelationshipQuery"!==a){m=this._getShallowClone(b);m.outFields=this.getOutFields();m.returnGeometry=b.hasOwnProperty("returnGeometry")?b.returnGeometry:!0;var h;l&&(m.outSpatialReference=new H(l.spatialReference.toJson()));if(!this._applyQueryFilters(m,"execute"===a)){switch(a){case "execute":h=new I({features:[]});
break;case "executeForIds":h=[];break;case "executeForCount":h=0}k(h,!0);return g}if(b=this._canDoClientSideQuery(m)){m=this._doQuery(m,b,"executeForIds"===a||"executeForCount"===a);switch(a){case "execute":h=new I;h.features=m;break;case "executeForIds":h=m;break;case "executeForCount":h=m.length}k(h,!0);return g}}if(this._collection)return this._resolve([Error("FeatureLayer::_query - "+this.invalidParams)],null,e,g,!0),g;this._ts&&(m._ts=(new Date).getTime());(g._pendingDfd=this._task[a](m)).addCallbacks(k,
function(a){f._resolve([a],null,e,g,!0)});return g},_convertFeaturesToJson:function(a,c,b,d){var e=[],f=this._selectionSymbol,l=this.visibilityField,g,m=this.objectIdField;if(this.loaded&&(b||d))g=h.filter(this.fields,function(a){return!1===a.editable&&(!d||a.name!==m)});for(b=0;b<a.length;b++){var k=a[b],n={},r=k.geometry,p=k.attributes,s=k.symbol;if(r&&(!d||!this.loaded||this.allowGeometryUpdates))n.geometry=r.toJson();l?(n.attributes=p=q.mixin({},p),p[l]=k.visible?1:0):p&&(n.attributes=q.mixin({},
p));n.attributes&&(g&&g.length)&&h.forEach(g,function(a){delete n.attributes[a.name]});s&&s!==f&&(n.symbol=s.toJson());e.push(n)}return c?e:E.toJson(e)},_selectHandler:function(a,c,b,d,e){var f;d=this.constructor;switch(c){case d.SELECTION_NEW:this.clearSelection(!0);f=!0;break;case d.SELECTION_ADD:f=!0;break;case d.SELECTION_SUBTRACT:f=!1}d=a.features;var l=this._mode,g=[],h=this.objectIdField,k,n;if(f)for(f=0;f<d.length;f++)k=d[f],n=k.attributes[h],k=l._addFeatureIIf(n,k),g.push(k),this._selectFeatureIIf(n,
k,l);else for(f=0;f<d.length;f++)k=d[f],n=k.attributes[h],this._unSelectFeatureIIf(n,l),n=l._removeFeatureIIf(n),g.push(n||k);this._isSelOnly&&l._applyTimeFilter(!0);this._resolve([g,c,a.exceededTransferLimit?{queryLimitExceeded:!0}:null],"onSelectionComplete",b,e);if(a.exceededTransferLimit)this.onQueryLimitExceeded()},_selectFeatureIIf:function(a,c,b){var d=this._selectedFeatures,e=d[a];e||(b._incRefCount(a),d[a]=c,this._isTable||(this._setSelectSymbol(c),c.attr("data-selected","")));return e||
c},_unSelectFeatureIIf:function(a,c){var b=this._selectedFeatures[a];b&&(c._decRefCount(a),delete this._selectedFeatures[a],this._isTable||(this._setUnSelectSymbol(b),b.attr("data-selected")));return b},_isSelected:function(a){},_setSelectSymbol:function(a){var c=this._selectionSymbol;c&&!this._isSelOnly&&a.setSymbol(c)},_setUnSelectSymbol:function(a){var c=this._selectionSymbol;c&&!this._isSelOnly&&c===a.symbol&&a.setSymbol(null,!0)},_getOutFields:function(){var a=[this.objectIdField,this.typeIdField,
this.creatorField,this._startTimeField,this._endTimeField,this._trackIdField].concat(this._rendererFields).concat(this.dataAttributes),a=h.filter(a,function(a,c,e){return!!a&&h.indexOf(e,a)===c}),c=q.clone(this._outFields);if(c){if(-1!==h.indexOf(c,"*"))return c;h.forEach(a,function(a){-1===h.indexOf(c,a)&&c.push(a)});return c}return a},_checkFields:function(a){var c=a||this._getOutFields();h.forEach(c,function(a){"*"!==a&&(this._getField(a)||console.debug("esri.layers.FeatureLayer: "+r.substitute({url:this.url,
field:a},"unable to find '${field}' field in the layer 'fields' information [url: ${url}]")))},this);!a&&(!this._isTable&&!this._fserver&&!this._collection)&&(h.some(this.fields,function(a){return a&&"esriFieldTypeGeometry"===a.type?!0:!1})||console.debug("esri.layers.FeatureLayer: "+r.substitute({url:this.url},"unable to find a field of type 'esriFieldTypeGeometry' in the layer 'fields' information. If you are using a map service layer, features will not have geometry [url: ${url}]")))},_fixRendererFields:function(){var a=
this.renderer;this._orderBy=null;if(a&&0<this.fields.length){var c=[],b,d,a=h.filter([a,a.observationRenderer,a.latestObservationRenderer,a.trackRenderer],r.isDefined),e=[].concat(a);h.forEach(a,function(a){h.forEach(a.rendererInfos,function(a){a.renderer&&e.push(a.renderer)})});h.forEach(e,function(a){if((d=a.attributeField)&&!q.isFunction(d))if(b=!this._getField(d)&&this._getField(d,!0))a.attributeField=b.name;if(d=a.attributeField2)if(b=!this._getField(d)&&this._getField(d,!0))a.attributeField2=
b.name;if(d=a.attributeField3)if(b=!this._getField(d)&&this._getField(d,!0))a.attributeField3=b.name;q.isFunction(a.attributeField)||c.push(a.attributeField);c.push(a.attributeField2);c.push(a.attributeField3);if((d=a.rotationInfo&&a.rotationInfo.field)&&!q.isFunction(d)){if(b=!this._getField(d)&&this._getField(d,!0))d=a.rotationInfo.field=b.name;c.push(d)}if(a.proportionalSymbolInfo){if((d=a.proportionalSymbolInfo.field)&&!q.isFunction(d)){if(b=!this._getField(d)&&this._getField(d,!0))d=a.proportionalSymbolInfo.field=
b.name;c.push(d);this._orderBy||(this._orderBy=[d+" DESC"])}if((d=a.proportionalSymbolInfo.normalizationField)&&!q.isFunction(d)){if(b=!this._getField(d)&&this._getField(d,!0))d=a.proportionalSymbolInfo.normalizationField=b.name;c.push(d)}}if(a.colorInfo){if((d=a.colorInfo.field)&&!q.isFunction(d)){if(b=!this._getField(d)&&this._getField(d,!0))d=a.colorInfo.field=b.name;c.push(d)}if((d=a.colorInfo.normalizationField)&&!q.isFunction(d)){if(b=!this._getField(d)&&this._getField(d,!0))d=a.colorInfo.normalizationField=
b.name;c.push(d)}}if(!this._orderBy&&a.addBreak&&!q.isFunction(a.attributeField)&&(a.backgroundFillSymbol||this._hasSizeDiff(a)))this._orderBy=[a.attributeField+" DESC"]},this);this._rendererFields=h.filter(c,r.isDefined)}},_hasSizeDiff:function(a){var c=Number.MAX_VALUE,b=-Number.MAX_VALUE,d,e;h.forEach(a.infos,function(a){if(e=a.symbol){d=0;switch(e.type){case "simplemarkersymbol":d=e.size;break;case "picturemarkersymbol":d=(e.width+e.height)/2;break;case "simplelinesymbol":case "cartographiclinesymbol":d=
e.width;break;case "simplefillsymbol":case "picturefillsymbol":d=e.outline&&e.outline.width}d&&(c=Math.min(c,d),b=Math.max(b,d))}});return c!==Number.MAX_VALUE&&b!==-Number.MAX_VALUE&&1<Math.abs(b-c)},getOrderByFields:function(){var a=this.orderByFields||this._orderBy;return a&&a.length?a:null},_getField:function(a,c){var b=this.fields;if(!b||0===b.length)return null;var d;c&&(a=a.toLowerCase());h.some(b,function(b){var f=!1;(f=c?b&&b.name.toLowerCase()===a?!0:!1:b&&b.name===a?!0:!1)&&(d=b);return f});
return d},_getDateOpts:function(){this._dtOpts||(this._dtOpts={properties:h.map(h.filter(this.fields,function(a){return!!(a&&"esriFieldTypeDate"===a.type)}),function(a){return a.name})});return this._dtOpts},_applyNormalized:function(a,c){a&&c&&h.forEach(a,function(a,d){a&&c[d]&&a.setGeometry(c[d])})},_editHandler:function(a,c,b,d,e,f){e=a.addResults;var l=a.updateResults;a=a.deleteResults;var g,m,k,n,q=this.objectIdField,p=this._mode,r=this._isTable;g=this.editFieldsInfo;var v=this.getOutFields()||
[],w=g&&g.creatorField,t=g&&g.creationDateField,x=g&&g.editorField,y=g&&g.editDateField;g=g&&g.realm;-1===h.indexOf(v,"*")&&(w&&-1===h.indexOf(v,w)&&(w=null),t&&-1===h.indexOf(v,t)&&(t=null),x&&-1===h.indexOf(v,x)&&(x=null),y&&-1===h.indexOf(v,y)&&(y=null));var v=t||y?(new Date).getTime():null,u=w||x?this.getUserId():void 0;u&&g&&(u=u+"@"+g);if(e)for(g=0;g<e.length;g++)e[g]=new D(e[g]),r||(m=e[g],m.success&&(m=m.objectId,k=c[g],(n=k._graphicsLayer)&&n!==this&&n.remove(k),n=k.attributes||{},n[q]=m,
w&&(n[w]=u),x&&(n[x]=u),t&&(n[t]=v),y&&(n[y]=v),k.setAttributes(n),p._init&&p.drawFeature(k)));if(l)for(g=0;g<l.length;g++)if(l[g]=new D(l[g]),!r&&(m=l[g],m.success)){m=m.objectId;k=b[m];if(c=p._getFeature(m))c.geometry!==k.geometry&&c.setGeometry(Y.fromJson(k.geometry.toJson())),this._repaint(c,m);k=c||k;n=k.attributes||{};x&&(n[x]=u);y&&(n[y]=v);k.setAttributes(n)}if(a){b=[];for(g=0;g<a.length;g++)if(a[g]=new D(a[g]),!r&&(m=a[g],m.success&&(m=m.objectId,k=p._getFeature(m))))this._unSelectFeatureIIf(m,
p)&&b.push(k),k._count=0,p._removeFeatureIIf(m);if(0<b.length)this.onSelectionComplete(b,this.constructor.SELECTION_SUBTRACT)}this._resolve([e,l,a],"onEditsComplete",d,f)},_sendAttachment:function(a,c,b,d,e){var f=this;return C({url:this._url.path+"/"+c+"/"+("add"===a?"addAttachment":"updateAttachment"),form:b,content:q.mixin(this._url.query,{f:"json",token:this._getToken()||void 0}),callbackParamName:"callback.html",handleAs:"json"}).addCallback(function(b){var e="add"===a?"onAddAttachmentComplete":
"onUpdateAttachmentComplete";b=new D(b["add"===a?"addAttachmentResult":"updateAttachmentResult"]);b.attachmentId=b.objectId;b.objectId=c;f._resolve([b],e,d);return b}).addErrback(function(a){f._resolve([a],null,e,null,!0)})},_repaint:function(a,c,b){c=r.isDefined(c)?c:a.attributes[this.objectIdField];(!(c in this._selectedFeatures)||!this._selectionSymbol)&&a.setSymbol(a.symbol,b)},_getKind:function(a){var c=this._trackManager;return c?c.isLatestObservation(a)?1:0:0}});q.mixin(t,{MODE_SNAPSHOT:0,
MODE_ONDEMAND:1,MODE_SELECTION:2,SELECTION_NEW:3,SELECTION_ADD:4,SELECTION_SUBTRACT:5,POPUP_NONE:"esriServerHTMLPopupTypeNone",POPUP_HTML_TEXT:"esriServerHTMLPopupTypeAsHTMLText",POPUP_URL:"esriServerHTMLPopupTypeAsURL"});Z._createWrappers(t);G("extend-esri")&&q.setObject("layers.FeatureLayer",t,B);return t});