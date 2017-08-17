<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="sec" uri="/WEB-INF/tags/shiro.tld"%>
<%@taglib prefix="pt6" uri="/WEB-INF/tags/platform6.tld"%>
<%@ page import="avicit.platform6.commons.utils.ViewUtil"%>
<%@ page import="avicit.platform6.api.session.SessionHelper"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">
<!-- ControllerPath = "discussion_manage/structuralrelationship/StructuralRelationshipController/StructuralRelationshipInfo" -->
<title>讨论区</title>
<base href="<%=ViewUtil.getRequestPath(request)%>">
<%
	String strid = request.getParameter("id") == null ? "" : request
			.getParameter("id");
	String classCode = request.getParameter("classCode") == null
			? ""
			: request.getParameter("classCode");
	String applyDeptId = SessionHelper.getCurrentDeptId(request);
%>
</head>
<link rel="stylesheet" type="text/css"
	href="avicit/discussion_manage/discussionmanage/js/proposal.css" />
<jsp:include
	page="/avicit/platform6/component/common/EasyUIJsInclude.jsp"></jsp:include>
<jsp:include
	page="/avicit/discussion_manage/structuralrelationship/ztreeLayuiInclude.jsp"></jsp:include>
<jsp:include
	page="/avicit/platform6/modules/system/commonpopup/commonSelectionHead.jsp"></jsp:include>

<script src="static/js/platform/component/sfn/SelfDefiningQuery.js"
	type="text/javascript"></script>
<script src="avicit/discussion_manage/discussionmanage/js/ProposalAuto.js"
	type="text/javascript"></script>
<style>
</style>
<script type="text/javascript">
var proposalManage;
	var oldStrId='<%=strid%>';
	var classCode='<%=classCode%>';
	var strid ='<%=strid%>';
	var instancenumber;
	var nodeId='';
	var types;
	var complex;
	var setting = {
		async : {
			autoParam : [ "id" ],
			dataType : "json",
			enable : true,
			type : "get",
			url : "platform/discussion_manage/structuralrelationship/StructuralRelationshipController/searchVciZtreeByStrId/"+strid
		},
		data : {
			simpleData : {
				enable : true
			}
		},
		callback : {
			onClick : zTreeOnClick
		}
	};
	function getFont(treeId, node) {
		return node.font ? node.font : {};
	}
	
	//初始化树
	$(function() {
		document.body.style.visibility = 'visible';
		var arr=[]; 
		var complex;
		var applyDeptId = '<%=applyDeptId%>';
		var purl = "platform/discussion_manage/proposalmanage/ProposalManageController/operation/";
		$.fn.zTree.init($("#tree"), setting);
		setTimeout(selectNodeById, 300);
		
		findDiscussionDate();
		proposalManage = new ProposalManage('dgProposalManage', purl,
				'searchDialog', 'proposalManage','<%=strid%>','<%=applyDeptId%>');
		$(document).on(
				'click',
				'.addLabelList li',
				function(e) {//点击专业筛选，根据span数量。限制最多选择条件数量  add by wuc
					if ($(".mainSection .screening span").length > 2) {
						$.messager.alert('警告', '最多可添加3类状态');
						return;
					}
					var thisLi = $(this);
					var bol = true;
					$(".mainSection .screening span").each(
							function() {
								var tem = $(this).html().substring(0,
										$(this).html().indexOf("<"));
								if (tem == thisLi.html()) {
									bol = false;
									return;
								}
							});
					//根据条件点击专业，在右侧生成专业标签。add by wuc
					if (bol) {
						$(".addLabel").after(
								"<span val=\"" + $(this).attr("val") + "\">"
										+ $(this).html()
										+ "<span class=\"sx_\">X</span>"
										+ "</span> ");
						arr.push($(this).attr("val"));
						$("input[name='strId']").val(strid);
						$("input[name='serdeptNames']").val(arr);
						proposalManage.reLoad();
					}
					$(".addLabel").css("display", "none");
				});

		$(document).on('click', '.mainSection .screening span', function(e) {
			var str = $(this).attr("val");
			$(this).remove();
			for ( var i in arr) {
				if (arr[i] == str) {
					arr.remove(i);
				}
			}
			$("input[name='serdeptNames']").val(arr);
			$("input[name='complex']").val(complex == '0' ? "" : complex);
			proposalManage.reLoad();
		});
		//通用代码
		var proposalUserIdUserCommonSelector = new CommonSelector("user",
				"userSelectCommonDialog", "proposalUserId",
				"proposalUserIdAlias");
		proposalUserIdUserCommonSelector.init();
		var proposalUserDeptDeptCommonSelector = new CommonSelector("dept",
				"deptSelectCommonDialog", "proposalUserDept",
				"proposalUserDeptAlias");
		proposalUserDeptDeptCommonSelector.init();
		var replierUserIdUserCommonSelector = new CommonSelector("user",
				"userSelectCommonDialog", "replierUserId", "replierUserIdAlias");
		replierUserIdUserCommonSelector.init();
		//部门选择下拉框
		$('#factoryinfo').combobox({
			width : '90',
			panelHeight : '75',
			valueField : 'id',
			textField : 'value',
			data : [ {
				id : '1',
				value : '601研究所'
			}, {
				id : '2',
				value : '320制造厂'
			}, {
				id : '0',
				value : '全部'
			} ],
			onSelect : function(record) {
				var id = record.id;
				$("input[name='strId']").val(oldStrId);
				$("input[name='complex']").val(id == '0' ? '' : id);
				complex = id;
				proposalManage.reLoad();
				getdeptLi(id == '0' ? '' : id, strid);
			}
		});
		//部门选择下拉框
		$.ajax({
					url : 'platform/discussion_manage/proposalmanage/ProposalManageController/operation/topDeptNumber',
					data : {
						id : applyDeptId
					},
					type : 'post',
					dataType : 'json',
					success : function(r) {
						if (r.flag == "success") {
							$('#factoryinfo').combobox('setValue', r.complex);
							complex = r.complex;
							getdeptLi(r.complex, strid);
						}
					}
				});
		//合并行第一列颜色设置
		$("#dgProposalManage").datagrid(
				{
					onLoadSuccess : function() {
						//合并第一列值相同的行
						$("#dgProposalManage").datagrid('autoMergeCells',
								[ "deptName" ]);
						//设置第一列的背景色
						$('.datagrid-row td[field=deptName]').css(
								'background-color', '#ffffff');
					}
				});
		
	});

	function getdeptLi(complex, strid) {
		$.ajax({
					url : 'platform/discussion_manage/proposalmanage/ProposalManageController/operation/getdeptLi',
					data : {
						complex : complex,
						strid : strid
					},
					type : 'post',
					dataType : 'json',
					success : function(r) {
						if (r.flag == "success") {
							if ((r.rows).length > 0) {
								$("#ullist li").remove();
								for (i = 0; i < (r.rows).length; i++) {
									var arr = r.rows[i];
									$.each(arr, function(key, value) {
										$("#ullist").append(
												"<li val=\"" + value + "\">"
														+ key + "</li>");
									});
								}
							}

						}
					}
				});
	}
	//选中节点
	function selectNodeById() {
		var zTree = $.fn.zTree.getZTreeObj("tree");
		var selecrNode = zTree.getNodeByParam("id", strid);
		instancenumber=selecrNode.instancenumber;
		zTree.selectNode(selecrNode);
	};
	//初始化datagrid
	function findDiscussionDate() {
		$("#dgDiscussion")
				.datagrid(
						{
							url : 'platform/discussion_manage/discussionmanage/DiscussionManageController/selectDiscussionData/'
									+ strid

						});
	};
	//根据条件设置表格行背景颜色
	function setRowBgColor(index, row) {
		var color = row.proposalColor;
		var state = row.status;
		if (color != null && color != ""&&state!="1") {
			return 'background-color:' + color + ';';
		}
	}

	//获取选择节点数据
	function zTreeOnClick(event, treeId, treeNode) {
		oldStrId = treeNode.id;
		instancenumber = treeNode.instancenumber;
		classCode = treeNode.name;
		$("input[name='strId']").val(oldStrId);
		proposalManage.reLoad();
	};
	//时间格式化
	function formateDate(value, row, index) {
		return formate(value);
	}
	//时间格式化
	function formate(value) {
		if (value) {
			return new Date(value).format("yyyy-MM-dd hh:mm:ss");
		}
		return '';
	};
	
	//建议回复
	function forum(id) {

		var url = 'avicit/discussion_manage/discussionmanage/ForumManage.jsp?id='
				+ id+'&instancenumber='+instancenumber;
		if (typeof (top.addTab) != 'undefined') {
			top.addTab("建议详情", url);
		} else {
			window.open(url);
		}
	};
	//设置争议显示内容
	function forummanage(value, row, inde) {
		if (row.isDispute == "1") {
			return '<a href="javascript:void(0);" onclick="forum(\''
					+ row.id
					+ '\');">'
					+ value
					+ '</a>'
					+ '<img src="avicit/discussion_manage/discussionmanage/js/images/zhengy.gif" onclick="replydetil(\''
					+ row.id + '\');"/>';
		} else {
			return '<a href="javascript:void(0);" onclick="forum(\'' + row.id
					+ '\');">' + value + '</a>';
		}
	};
	//专业内容字体大小显示
	function fontSize(value, row, inde) {
		return '<font size="4px">' + value + '</font>';
	};
	//争议内容显示
	function replydetil(id) {
		var url = 'platform/discussion_manage/disputememo/DisputeMemoController/operation/disputed?id='
				+ id;
		this.nData = new CommonDialog("disputed", "410", "300", url, "争议备忘",
				false, true, false);
		this.nData.show();
	}

	/*点击按钮控制下拉框显示  */
	function choose() {
		var dis = $(".addLabel").css("display");
		if (dis == "none") {
			$(".addLabel").css("display", "block");
			window.setTimeout(function() {
				$("body").click(function() {
					$(".addLabel").css("display", "none");
					$("body").unbind("click");
				});
			}, 10);
		} else {
			$(".addLabel").css("display", "none");
		}
	};
	/*点击按钮控制下拉框显示  */
	function chooseAd() {
		var dis = $(".adLabel").css("display");
		if (dis == "none") {
			$(".adLabel").css("display", "block");
			window.setTimeout(function() {
				$("body").click(function() {
					$(".adLabel").css("display", "none");
					$("body").unbind("click");
				});
			}, 10);
		} else {
			$(".adLabel").css("display", "none");
		}
	};
	/*点击按钮控制下拉框显示  */
	function chooseShc() {
		var dis = $(".scLabel").css("display");
		if (dis == "none") {
			$(".scLabel").css("display", "block");
			window.setTimeout(function() {
				$("body").click(function() {
					$(".scLabel").css("display", "none");
					$("body").unbind("click");
				});
			}, 10);
		} else {
			$(".scLabel").css("display", "none");
		}
	};

	function chooseCloss() {
		alert("关闭建议事件！");
	}
	function reloads(){
		
	}
	//通用代码
	function formatestatus(value) {
		return Platform6.fn.lookup.formatLookupType(value,
				proposalManage.status);
	}
	function formateisDispute(value) {
		return Platform6.fn.lookup.formatLookupType(value,
				proposalManage.isDispute);
	}
	//加载模型干涉表格数据通过当前选择节点
	function getInterveneDataByStrId(strId) {
		$("#dgInterveneManage").datagrid({
			url : "platform/discussion_manage/discussionmanage/DiscussionManageController/getInterveneDataByStrId/"+ strId+"?instancenumber="+instancenumber,
			//在双击一个单元格的时弹出干涉内容详细内容	
			onClickCell: function(index,field,value){
				if("description" == field){
					$.messager.alert("干涉内容详细",value);
				}
			}
		});
	}
	$(function(){
		//点击tab标签初始化tab页面
		$("#tt").tabs({    
		    onSelect:function(title,index){    
		    	//加载模型干涉表格数据通过当前选择节点
				getInterveneDataByStrId(oldStrId);    
		    }    
		}); 
	});
	
	//强制通过选中干涉检查结果
	function passIntervene(ides){
		$.messager.confirm("确认对话框","您确认要强制通过吗？",function(r){
			if(r){
				var ids= [];
				ids.push(ides);
				$.ajax({
					url : 'platform/discussion_manage/discussionmanage/DiscussionManageController/updateInterveneResult',
					data : JSON.stringify(ids),
					contentType : 'application/json',
					type : 'post',
					dataType : 'json',
					success : function(r){
						if(r.flag=="success"){
							$.messager.show({
								 title : '提示',
								 msg : '保存成功！'
							 });
						 }else{
							 $.messager.show({
								 title : '提示',
								 msg : "操作失败！"
							});
						 } 
					}
			    });
			}else{
				var selectCheckBoxId = "#"+ides;
				$(selectCheckBoxId).attr("checked",false);
			}
		});
	};
	
	//给干涉结果是否通过加复选框
	function ischeckbox(value, row, index){
		if('positive' == value){
			return '<input class="pass" type="checkbox" checked="checked"/>通过';
		}else{
			return '<input id="'+row.id+'" type="checkbox" onclick="passIntervene(\''+row.id+'\')"/>通过';
		}
	};
	
	//强制通过全部选中干涉检查结果
	function selectAllRow(){
		var flag = $("#selectAll").is(':checked');
		if(flag == true){
			$.messager.confirm("确认对话框","您确认要强制全部通过吗？",function(r){
				if(r){
					$(".pass").attr("checked","checked");
					var ides = [];
				    var array = $("#dgInterveneManage").datagrid('getRows');
					for(var i=0; i < array.length;i++){
						ides.push(array[i].id);
					}
					$.ajax({
						url : 'platform/discussion_manage/discussionmanage/DiscussionManageController/updateInterveneResult',
						data : JSON.stringify(ides),
						contentType : 'application/json',
						type : 'post',
						dataType : 'json',
						success : function(r){
							if(r.flag=="success"){
								$.messager.show({
									 title : '提示',
									 msg : '保存成功！'
								 });
							 }else{
								 $.messager.show({
									 title : '提示',
									 msg : "操作失败！"
								});
							 } 
						}
				    });
				}else{
					$("#selectAll").attr("checked",false);
				}
			});
		}
	}
</script>
</head>
<body class="easyui-layout" fit="true" style="visibility: hidden;">
	<div data-options="region:'west',split:true,title:'结构'"
		style="width: 250px">
		<ul id="tree" class="ztree"></ul>
	</div>
	<div data-options="region:'center',split:true"
		style="padding: 0px; overflow: auto;">
		<div id="tt" class="easyui-tabs" style="padding: 0px;" 
			data-options="fit:true">
			<div title="讨论区"
				data-options="iconCls:'icon-filter',closable:true,closable:false,fit:true"
				style="padding: 0px; ">
				<div id="toolbarStructureManage" class="datagrid-toolbar">
					<div class="mainSection">
						<label style="float: left;"><input id="factoryinfo"
							name="dept" /></label>
						<div class="screening">
							<label class="label" for="mainBtn">专业筛选:</label>
							<button class="mainSectionBtn" id="mainBtn" onclick="choose()">
								<img ismap
									src="static/images/platform/common/icons/text_list_bullets.png">
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<div class="addLabel">
								<div class="addLabelList" style="width: 150px;">
									<ul id="ullist">
									</ul>
								</div>
							</div>
						</div>
						<!-- <div class="jygbBtn">
							 <label class="shclabel" for="gb">关闭建议:</label>
							<button class="mainSectionBtn" id="gb" onclick="chooseCloss()">
								<img class="ad" ismap
									src="avicit/discussion_manage/discussionmanage/js/images/cross.png">
							</button> 
						</div> -->
						<div class="shcbtn">
							<label class="shclabel" for="shc">审查:</label>
							<button class="mainSectionBtn" id="shc" onclick="chooseShc()">
								<img class="ad" ismap
									src="avicit/discussion_manage/discussionmanage/js/images/scqr.png">
							</button>
							<div class="scLabel">
								<div class="shcLabelList">
									<ul>
										<li onclick="alert('审查确认！')">审查确认</li>
										<li onclick="alert('重新审查！')">重新审查</li>
									</ul>
								</div>
							</div>
						</div>
						<div class="addbtn">
							<label class="fblabel" for="fb">发表建议:</label>
							<button class="mainSectionBtn" id="fb" onclick="chooseAd()">
								<img class="ad" ismap
									src="avicit/discussion_manage/discussionmanage/js/images/add-b.png">
							</button>
							<div class="adLabel">
								<div class="adLabelList">
									<ul>
										<li onclick="proposalManage.insert(oldStrId,classCode,instancenumber);">发表建议</li>
										<li onclick="proposalManage.ksinsert(oldStrId);">快速发表</li>
									</ul>
								</div>
							</div>
						</div>


					</div>
				</div>
				<table id="dgProposalManage"
					data-options="
				fit: true,
				border: false,
				rownumbers: true,
				animate: true,
				collapsible: false,
				fitColumns: true,
				autoRowHeight: false,
				toolbar:'#toolbarProposalManage',
				idField :'id',
				singleSelect: true,
				rowStyler:setRowBgColor,
				checkOnSelect: true,
				selectOnCheck: false,
				pagination:false,
				pageSize:dataOptions.pageSize,
				pageList:dataOptions.pageList,
				striped:true">
					<thead>
						<tr>
							<th
								data-options="field:'id', halign:'center',checkbox:true,hidden:true"
								width="220">ID</th>
							<th data-options="field:'deptName',align:'center',formatter:fontSize" width="220">建议提出人部门（专业）</th>
							<!-- <th data-options="field:'proposalUserDeptAlias',align:'center'"
						width="220">建议提出人部门（专业）</th>	 -->

							<th data-options="field:'strId', halign:'center',hidden:true"
								width="220">装配结构ID</th>

							<th data-options="field:'parentId', halign:'center',hidden:true"
								width="220">父类建议ID</th>

							<th
								data-options="field:'proposalCode', halign:'center',hidden:true"
								width="220">建议编号</th>

							<th
								data-options="field:'proposalName', halign:'center',formatter:forummanage"
								width="220">建议标题</th>
							<th data-options="field:'proposalUserIdAlias',align:'center'"
								width="220">建议提出人</th>

							<th
								data-options="field:'proposalMain', halign:'center',hidden:true"
								width="220">建议正文</th>

							<th
								data-options="field:'publicationTime', align:'center',formatter:formateDate"
								width="220">建议发表时间</th>
							<th
								data-options="field:'status', align:'center',formatter:formatestatus"
								width="220">状态</th>


							<th
								data-options="field:'isDispute', align:'center',hidden:true,formatter:formateisDispute"
								width="220">是否有争议</th>

							<th
								data-options="field:'isApproval', halign:'center',hidden:true"
								width="220">320厂是否批准建议（320厂默认否）</th>

							<th
								data-options="field:'replierUserIdAlias',align:'center',hidden:true"
								width="220">回复人ID</th>
						</tr>
					</thead>
				</table>
			</div>
			<div title="模型质量检查" 
				data-options="iconCls:'icon-search',closable:true,closable:false"
				style="overflow: auto; padding: 20px;">
				<span class="badge badge-danger">争议</span>
			</div>
			<div title="模型干涉检查" id="Intervene"
				data-options="iconCls:'icon-reload',closable:true,closable:false"
				style="overflow: auto; padding: 0px;">
				<table id="dgInterveneManage"
					data-options=  "fit: true,
									border: false,
									rownumbers: true,
									animate: true,
									collapsible: false,
									fitColumns: true,
									autoRowHeight: false,
									idField :'id',
									singleSelect: true,
									rowStyler:setRowBgColor,
									checkOnSelect: true,
									selectOnCheck: false,
									pagination:true,
									pageSize:dataOptions.pageSize,
									pageList:dataOptions.pageList,
									striped:true">
					<thead>
						<tr>
							<th data-options="field:'classCode',align:'center'" width="220">关联零部件</th>
							
							<th data-options="field:'description',align:'center'" width="220">干涉内容</th>

							<th data-options="field:'checkTime', align:'center'" width="220">检查时间</th>

							<th data-options="field:'result', align:'center',formatter: ischeckbox" width="220"><input type="checkbox" id ="selectAll" onclick="selectAllRow()"/>全选</th>

						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>

	<!--*****************************搜索*********************************  -->
	<div id="searchDialog"
		data-options="iconCls:'icon-search',resizable:true,modal:false,buttons:'#searchBtns'"
		style="width: 904px; height: 340px; display: none;">
		<form id="proposalManage">
			<table class="form_commonTable">
				<tr>
					<th width="10%">装配结构ID:</th>
					<td width="40%"><input class="easyui-validatebox"
						style="width: 151px;" type="text" name="strId" /></td>
					<th width="10%">父类建议ID:</th>
					<td width="40%"><input class="easyui-validatebox"
						style="width: 151px;" type="text" name="parentId" /></td>
				</tr>
				<tr>
					<th width="10%">建议编号:</th>
					<td width="40%"><input class="easyui-validatebox"
						style="width: 151px;" type="text" name="proposalCode" /></td>
					<th width="10%">建议标题:</th>
					<td width="40%"><input class="easyui-validatebox"
						style="width: 151px;" type="text" name="proposalName" /></td>
				</tr>
				<tr>
					<th width="10%">建议正文:</th>
					<td width="40%"><input class="easyui-validatebox"
						style="width: 151px;" type="text" name="proposalMain" /></td>
					<th width="10%">状态:</th>
					<td width="40%"><input class="easyui-validatebox"
						style="width: 151px;" type="text" name="status" /></td>
				</tr>
				<tr>
				<tr>
					<th width="10%">建议发表时间 从:</th>
					<td width="40%"><input name="publicationTimeBegin"
						id="publicationTimeBegin" class="easyui-datebox" editable="false" />
					<th width="10%">建议发表时间 (至):</th>
					<td width="40%"><input name="publicationTimeEnd"
						id="publicationTimeEnd" class="easyui-datebox" editable="false" />
					</td>
				</tr>
				<th width="10%">建议提出人:</th>
				<td width="40%"><input title="建议提出人" class="inputbox"
					type="hidden" name="proposalUserId" id="proposalUserId" />
					<div class="">
						<input class="easyui-validatebox" name="proposalUserIdAlias"
							id="proposalUserIdAlias" readOnly="readOnly"></input>
					</div></td>
				<th width="10%">建议提出人部门（专业）:</th>
				<td width="40%"><input title="建议提出人部门（专业）" class="inputbox"
					type="hidden" name="proposalUserDept" id="proposalUserDept" />
					<div class="">
						<input class="easyui-validatebox" name="proposalUserDeptAlias"
							id="proposalUserDeptAlias" readOnly="readOnly"></input>
					</div></td>
				</tr>
				<tr>
					<th width="10%">是否有争议:</th>
					<td width="40%"><input class="easyui-validatebox"
						style="width: 151px;" type="text" name="isDispute" /></td>
					<th width="10%">320厂是否批准建议（320厂默认否）:</th>
					<td width="40%"><input class="easyui-validatebox"
						style="width: 151px;" type="text" name="isApproval" /></td>
				</tr>
				<tr>
					<th width="10%">回复人ID:</th>
					<td width="40%"><input title="回复人ID" class="inputbox"
						type="hidden" name="replierUserId" id="replierUserId" />
						<div class="">
							<input class="easyui-validatebox" name="replierUserIdAlias"
								id="replierUserIdAlias" readOnly="readOnly"></input>
						</div></td>
					<th width="10%">320厂是否批准建议（320厂默认否）:</th>
					<td width="40%"><input class="easyui-validatebox"
						style="width: 151px;" type="text" name="complex" /></td>
					<th width="10%">专业筛选:</th>
					<td width="40%"><input class="easyui-validatebox"
						style="width: 151px;" type="text" name="serdeptNames" /></td>
				</tr>
			</table>
		</form>
		<div id="searchBtns" class="datagrid-toolbar foot-formopera">
			<table class="tableForm" border="0" cellspacing="1" width='100%'>
				<tr>
					<td align="right"><a class="easyui-linkbutton primary-btn"
						iconCls="" plain="false" onclick="proposalManage.searchData();"
						href="javascript:void(0);">查询</a> <a class="easyui-linkbutton"
						iconCls="" plain="false" onclick="proposalManage.clearData();"
						href="javascript:void(0);">清空</a> <a class="easyui-linkbutton"
						iconCls="" plain="false"
						onclick="proposalManage.hideSearchForm();"
						href="javascript:void(0);">返回</a></td>
				</tr>
			</table>
		</div>
	</div>
	<script
		src=" avicit/discussion_manage/proposalmanage/js/ProposalManage.js"
		type="text/javascript"></script>
</body>
</html>