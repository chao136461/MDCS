<%@page import="avicit.platform6.core.rest.client.RestClientConfig"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="avicit.platform6.bpmclient.dto.sys.HistoryTaskVo"%>
<%@page import="avicit.platform6.bpmclient.bpm.service.BpmDisplayService"%>
<%@page import="avicit.platform6.bpm.api.BpmToolService"%>
<%@page import="avicit.platform6.api.session.SessionHelper"%>
<%@page import="avicit.platform6.bpm.api.op.DocRightDefinition"%>
<%@page import="avicit.platform6.bpm.api.op.SubDocRightInterface"%>
<%@page import="avicit.platform6.modules.system.sysfileupload.service.SwfUploadService"%>
<%@page import="avicit.platform6.core.spring.SpringFactory"%>
<%@page import="avicit.platform6.commons.utils.ComUtil"%>
<%@page import="avicit.platform6.commons.utils.ViewUtil"%>
<%@page import="java.util.*"%>
<%@ page import="avicit.platform6.core.properties.PlatformProperties"%>
<%
	//获取系统默认上传参数
	String defaultFileSizeLimit = ComUtil.replaceNull2Space(PlatformProperties.getProperty("platform.default.upload.fileSizeLimit"));
	String defaultfileTypes = ComUtil.replaceNull2Space(PlatformProperties.getProperty("platform.default.upload.fileTypes"));
	String defaultFileNumberLimit = ComUtil.replaceNull2Space(PlatformProperties.getProperty("platform.default.upload.fileNumberLimit"));
	String defaultSaveType = ComUtil.replaceNull2Space(PlatformProperties.getProperty("platform.default.upload.saveType"));
	String defaultFileStyle = ComUtil.replaceNull2Space(PlatformProperties.getProperty("platform.default.upload.fileStyle"));
	
	//获取单独上传参数
	String file_size_limit = ComUtil.replaceNull2Space(request.getParameter("file_size_limit"));
	String file_types = ComUtil.replaceNull2Space(request.getParameter("file_types"));
	String file_upload_limit = ComUtil.replaceNull2Space(request.getParameter("file_upload_limit"));
	String save_type = ComUtil.replaceNull2Space(request.getParameter("save_type"));
	String form_id = ComUtil.replaceNull2Space(request.getParameter("form_id"));
	String form_code = ComUtil.replaceNull2Space(request.getParameter("form_code"));
	String form_field = ComUtil.replaceNull2Space(request.getParameter("form_field"));
	String allowAdd = ComUtil.replaceNull2Space(request.getParameter("allowAdd"));
	String allowDel = ComUtil.replaceNull2Space(request.getParameter("allowDel"));
	String collapsed = ComUtil.replaceNull2Space(request.getParameter("collapsed"));
	String bpm_nodeId = "";
	
	String entryId = ComUtil.replaceNull2Space(request.getParameter("entryId"));
	String executionId = ComUtil.replaceNull2Space(request.getParameter("executionId"));
	String taskId = ComUtil.replaceNull2Space(request.getParameter("taskId"));
	
	String appId = RestClientConfig.systemid;
	String lang = SessionHelper.getCurrentUserLanguageCode(request);
	String file_style = ComUtil.replaceNull2Space(request.getParameter("file_style"));//显示的样式
	String hiddenUploadBtn = ComUtil.replaceNull2Space(request.getParameter("hiddenUploadBtn"));
	
	//设置默认参数
	if("".equals(file_size_limit)){
		file_size_limit = defaultFileSizeLimit;
	}
	if("".equals(file_types)){
		file_types = defaultfileTypes;
	}
	if("".equals(file_upload_limit)){
		file_upload_limit = defaultFileNumberLimit;
	}
	if("".equals(save_type)){
		save_type = defaultSaveType;
	}
	if("".equals(file_style)){
		file_style = defaultFileStyle;
	}
	

	if("".equals(hiddenUploadBtn)){
		hiddenUploadBtn = "false";
    }
	
	if("".equals(file_style) || !"span".equals(file_style.toLowerCase())){
		file_style = "table";
    }
	
	String cleanOnExit = ComUtil.replaceNull2Space(request.getParameter("cleanOnExit"));
	String file_category = ComUtil.replaceNull2Space(request.getParameter("file_category"));
	String secret_level = ComUtil.replaceNull2Space(request.getParameter("secret_level"));
	SwfUploadService swfUploadService = (SwfUploadService) SpringFactory.getBean(SwfUploadService.class);
	if("process".equals(allowAdd)){
		BpmToolService bpmToolService = (BpmToolService) SpringFactory.getBean(BpmToolService.class);
		BpmDisplayService bpmDisplayService = (BpmDisplayService) SpringFactory.getBean(BpmDisplayService.class);
		String states = bpmToolService.getProcessStateByFormId(form_id);
		if(states==null || "".equals(states) || "结束".equals(states)){
			allowDel = "false";
			allowAdd = "false";
		}else{
			String userId = SessionHelper.getLoginSysUserId(request);
			if(entryId==null || "".equals(entryId)){
				List<HistoryTaskVo> taskList = bpmDisplayService.getProcessDetailParameter(null, form_id,userId);
				for(HistoryTaskVo h:taskList){
					entryId = h.getProcessInstance() + "";
					executionId = h.getExecutionId();
					break;
				}
			}
			if(entryId != null && !"".equals(entryId)){
				//if(executionId.indexOf(".to") == -1){
					Map<String,Object> map = bpmToolService.getDocRightDefinitionByExecutionId(entryId,executionId);
					List<DocRightDefinition> list = (List<DocRightDefinition>) map.get("docRight");
					if(list != null){
						for(DocRightDefinition  dr : list){
							String type = dr.getType();
							if("attachCreate".equals(type)){
								allowDel = "true";
								allowAdd = "true";
							}
							if("attachEdit".equals(type)){
								
							}
							if("attachPrint".equals(type)){
								
							}
							if("attachShowByNode".equals(type)){
								bpm_nodeId = (String) map.get("nodeId");
								List<SubDocRightInterface> subDocRights = dr.getSubDocRightList();
								if(subDocRights != null && subDocRights.size() > 0){
									bpm_nodeId = subDocRights.get(0).getName();
								}
							}
						}
					}
				//}
			}
		}
	}
	
	Map<String,String> para = new HashMap<String,String>();
	para.put("formId", form_id);
	para.put("allowDel", allowDel);
	para.put("secretLevel", secret_level);
	para.put("fileCategory", file_category);
	para.put("appId",appId);
	para.put("language",lang);
	para.put("formCode",form_code);
	if(!bpm_nodeId.equals("")){
		para.put("nodeId", bpm_nodeId);
	}
	
	String attachHtml = "";
	String fileCategory = "";
	String fileSecret = "";
	String fileSize = "";
	if("table".equals(file_style)){
		Map<String,String> result = swfUploadService.getAttachHtml(para,SessionHelper.getLoginSysUser(request));
		attachHtml = result.get("html");
		if (form_field != null && !"".equals(form_field)){
			String[] fields = form_field.split(",");
			para.put("formCode","");
			for (String field : fields){
				para.put("formId", field);
				Map<String,String> res = swfUploadService.getAttachHtml(para,SessionHelper.getLoginSysUser(request));
				attachHtml += res.get("html");
			}
		}
		fileCategory = result.get("fileCategory");
		fileSecret = result.get("fileSecret");
		fileSize = result.get("fileSize");
	}else if("span".equals(file_style)){
		Map<String,String> result = swfUploadService.getAttachHtml4Span(para,SessionHelper.getLoginSysUser(request));
		attachHtml = result.get("html");
		if (form_field != null && !"".equals(form_field)){
			String[] fields = form_field.split(",");
			para.put("formCode","");
			for (String field : fields){
				para.put("formId", field);
				Map<String,String> res = swfUploadService.getAttachHtml4Span(para,SessionHelper.getLoginSysUser(request));
				attachHtml += res.get("html");
			}
		}
		fileCategory = result.get("fileCategory");
		fileSecret = result.get("fileSecret");
		fileSize = result.get("fileSize");
	}
%>
<script type="text/javascript">
var baseurl = "<%=ViewUtil.getRequestPath(request)%>";
var file_size_limit = "<%=file_size_limit%>";
var file_types = "<%=file_types%>";
var file_upload_limit = "<%=file_upload_limit%>";
var save_type = "<%=save_type%>";
var form_id = "<%=form_id%>";
var form_code = "<%=form_code%>";
var form_field = "<%=form_field%>";
var allowAdd = "<%=allowAdd%>";
var allowDel = "<%=allowDel%>";
var cleanOnExit = "<%=cleanOnExit%>";
var file_category = "<%=file_category%>";
var hiddenUploadBtn = "<%=hiddenUploadBtn%>";
var secret_level = "<%=secret_level%>";
var collapsed = "<%=collapsed%>";
fileCategory = eval('('+'<%=fileCategory%>'+')');
fileSecret = eval('('+'<%=fileSecret%>'+')');
var bpm_nodeId = "<%=bpm_nodeId%>";
var markProcess = "1";
var collapsed = "<%=collapsed%>";
var file_style = "<%=file_style%>";
</script>
<link href="avicit/platform6/modules/system/swfupload/swf/css/default.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="avicit/platform6/modules/system/swfupload/swf/js/swfupload.js"></script>
<script type="text/javascript"
	src="avicit/platform6/modules/system/swfupload/swf/js/swfupload.swfobject.js"></script>
<script type="text/javascript"
	src="avicit/platform6/modules/system/swfupload/swf/js/swfupload.queue.js"></script>
<script type="text/javascript"
	src="avicit/platform6/modules/system/swfupload/swf/js/fileprogress.js"></script>
<script type="text/javascript"
	src="avicit/platform6/modules/system/swfupload/swf/js/handlers.js"></script>
<script type="text/javascript"
	src="avicit/platform6/modules/system/swfupload/swf/js/myswfupload.js"></script>
<script type="text/javascript"
	src="avicit/platform6/modules/system/attachment/jquerymutilupload/jquery.MultiFile.Edit.js"></script>
	
<script src="static/js/platform/component/jQuery/jquery-easyui-1.3.5/extend/easyui.fieldset.extentd.js" type="text/javascript"></script>
<script type="text/javascript">
	function swfUploadLoaded() {
		clearTimeout(this.customSettings.loadingTimeout);
		document.getElementById("divLoadingContent").style.display = "none";
		document.getElementById("divLongLoading").style.display = "none";
		document.getElementById("divAlternateContent").style.display = "none";
		var stats = swfu.getStats();
		stats.successful_uploads = <%=fileSize %>;
		swfu.setStats(stats);
	}

	function upload(id){
		if (swfu.getStats().files_queued > 0){
			if(typeof(id) != "undefined"){
				swfu.addPostParam("form_id",id);
			}
			swfu.startUpload();
			return 1;
		}else{
			return 0;
		}
	}

        
        function formUpload(secritValue){
            easyuiMask();//遮盖
            if (swfu.getStats().files_queued > 0) {
                 if(secritValue && !validateSecritLevel(secritValue)){
                    easyuiUnMask();//取消遮盖
                    return;
                   }
                  swfu.startUpload();
		}
                easyuiUnMask();//取消遮盖
            }

            /**
           *  验证附件中的密级不能高于表单的密级
           * @param {type} secritValue 表单密级值
           * @returns false:验证失败；true：验证通过
           */  
         function validateSecritLevel(secritValue){
             var selLevels = $("select[id^='secret_']");
		var msg = "";
		for(var i = 0; i < selLevels.length; i++){
			if(selLevels[i].value > secritValue){
				msg = "待上传的第" + (i+1) + "个附件密级高于文档密级！";
				break;
			}
		}
		if("" != msg){
			$.messager.alert('提示',msg,'warning');
			return false;
		}else{
                    return true;
                }
         }
	function deleteFile(fileId, fileName, obj) {
		//进行删除确认
		if (confirm("您要删除选中的附件吗?")) {
			obj.innerText = "删除中...";
			//修改删除链接
			$.ajax({
						type : 'GET',
						url : "platform/swfUploadController/doDelete",
						data : '&pkId=' + fileId + "&fileuploadBusinessId="
								+ form_id + "&fileuploadBusinessTableName="
								+ form_code + "&fileuploadIsSaveToDatabase="
								+ save_type + "&fileName=",
						success : function() {
							var stats = swfu.getStats();
							stats.successful_uploads--;
							swfu.setStats(stats);
							$('#'+fileId).remove();
							document.getElementById("shengyu").innerHTML = (file_upload_limit - stats.successful_uploads);
						}
					});
		}
	}

	//小心使用，也可不用此方法，而是去手工删除附件
	//保存后务必将此修改为true，否则退出页面时附件会被删除，默认为false
	var saveSuccess = true;
	window.onbeforeunload = function() {
		//如果是新增页面，且未保存数据，那么关闭页面时调用清除附件的方法
		if (cleanOnExit == "true" && !saveSuccess) {
			clearFiles();
		}
	};

	/**
	 *清除此formID下所有的附件、
	 */
	function clearFiles() {
		$.ajax({
			type : 'GET',
			url : "platform/swfUploadController/doDeleteByFormId",
			data : "&fileuploadBusinessId=" + form_id,
			success : function() {
			}
		});
	}
	
	function downloadFile(fileId,formId,formTable,saveType){
		window.open("<%=request.getContextPath()%>/platform/swfUploadController/doDownload?fileuploadBusinessId="+formId+"&fileuploadBusinessTableName="+formTable+"&fileuploadIsSaveToDatabase="+saveType+"&fileId="+fileId,"_blank");
	}
</script>
<div id="<%=form_code%>fs" style="overflow:hidden;position: relative;">
<form id="swfform" name="swfform" method="post" enctype="multipart/form-data">
	<table width="95%" cellspacing="1" cellpadding="1" border="0" align="center">
		<tr>
			<td>
				<div id="uploadDiv">
					<table cellpadding="1" cellspacing="1">
						<tr style="font-size: 12px; font-weight: normal; color: aaaaaa;">
							<td rowspan="2"><span id="spanButtonPlaceholder"></span></td>
							<td rowspan="2">
								<input id="btnUpload" type="button" onclick="upload();" value="上传" class="btn-upload"></td>
							<td>
								<%
									if(file_upload_limit!=null && Integer.parseInt(file_upload_limit)>0){
								%> (最多<%=file_upload_limit%>个附件,您还可以上传<span id="shengyu"><%=(Integer.parseInt(file_upload_limit)-Integer.parseInt(fileSize))%></span>个附件)
								<%
									}
								%>
							</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
		<tr>
			<td><%if("table".equals(file_style)){ %>
				<TABLE id="<%=form_code%>" width="100%" border="0" cellpadding="1" cellspacing="1">
					<tr style="background:url('static/js/platform/component/jQuery/jquery-easyui-1.3.5/themes/gray/images/title.jpg')" height="25px;">
							<td align="center">
								<font size="-1">附件名称</font>
							</td>
							<% if(file_category!=null && !file_category.equals("")) {%>
							<td align="center">
								<font size="-1">附件类型</font>
							</td>
							<%} %>
							<% if(secret_level!=null && !secret_level.equals("")) {%>
							<td align="center">
								<font size="-1">附件密级</font>
							</td>
							<%} %>
							<td align="center">
								<font size="-1">附件大小</font>
							</td>
							<td align="center">
								<font size="-1">状态</font>
							</td>
							<td  align="center">
								<font size="-1">操作</font>
							</td>
						</tr>
					<%=attachHtml%>
				</table> <%}else if("span".equals(file_style)){ %>
				<div id="<%=form_code%>" class="attch_display_div"><%=attachHtml %></div>
			<%}%></td>
		</tr>
		<tr>
			
		</tr>
	</table>
</form>

<div id="uploadCountDiv" style="padding-top: 5px; display: none;">
	<div style="display: none">
		</span>等待上传 <span id="<%=form_code%>Count">0</span> 个 ，已上传 <span
			id="<%=form_code%>SuccessUploadCount">0</span> 个。&nbsp;&nbsp;
	</div>
	<div id="divSWFUploadUI" style="visibility: hidden;"></div>
	<noscript
		style="display: block; margin: 10px 25px; padding: 10px 15px;">
		很抱歉，附件上传界面无法载入，请将浏览器设置成支持JavaScript。</noscript>
	<div id="divLoadingContent" class="content"
		style="background-color: #FFFF66; border-top: solid 4px #FF9966; border-bottom: solid 4px #FF9966; margin: 10px 25px; padding: 10px 15px; display: none;">
		附件上传界面正在载入，请稍后...</div>
	<div id="divLongLoading" class="content"
		style="background-color: #FFFF66; border-top: solid 4px #FF9966; border-bottom: solid 4px #FF9966; margin: 10px 25px; padding: 10px 15px; display: none;">
		附件上传界面载入失败，请确保浏览器已经开启对JavaScript的支持，并且已经安装可以工作的Flash插件版本。</div>
	<div id="divAlternateContent" class="content"
		style="background-color: #FFFF66; border-top: solid 4px #FF9966; border-bottom: solid 4px #FF9966; margin: 10px 25px; padding: 10px 15px; display: none;">
		很抱歉，附件上传界面无法载入，请安装或者升级您的Flash插件</div>
</div>
</div>
<script>
	if (allowAdd == "true") {
		document.getElementById("uploadDiv").style.display = "block";
		document.getElementById("uploadCountDiv").style.display = "block";
		document.getElementById("btnUpload").style.display = "block";
	} else {
		document.getElementById("uploadDiv").style.display = "none";
		document.getElementById("uploadCountDiv").style.display = "none";
		document.getElementById("btnUpload").style.display = "none";
	}
	if(hiddenUploadBtn === "true"){
        document.getElementById("btnUpload").style.display = "none";
    }
	var collapsedFlag = false;
    if (collapsed =="true"){
    	collapsedFlag = true;
    }else{
    	collapsedFlag = false;
    }
    <%if("table".equals(file_style)){ %>
    var filescount = $("#"+"<%=form_code%>").find('tr').length-1;
    <%}else if("span".equals(file_style)){ %>
    var filescount = $("#"+"<%=form_code%>").find('.attch_unit').length;
    <%}%>
    $("#"+"<%=form_code%>fs").lqfieldset({  
        title:'附件(已添加'+filescount+'个附件)',  
        collapsible:true,  
        collapsed:collapsedFlag,  
        checkboxToggle:false  
    }); 
</script>
