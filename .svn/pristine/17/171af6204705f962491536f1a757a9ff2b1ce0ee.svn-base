<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="avicit.platform6.commons.utils.ViewUtil"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>单位列表</title>
<base href="<%=ViewUtil.getRequestPath(request)%>">
<jsp:include page="/avicit/platform6/component/common/EasyUIJsInclude.jsp"></jsp:include>
<jsp:include page="/avicit/platform6/modules/system/commonpopup/commonSelectionHead.jsp"></jsp:include>
<script src="static/js/platform/component/common/exteasyui.js" type="text/javascript"></script>
<script src="avicit/platform6/eform/formdefine/js/ckeditor/ckeditor.js"></script>
<script src="avicit/platform6/eform/formdefine/js/ckeditor/config.js"></script>
</head>
<style>
</style>
<body class="easyui-layout"  fit="true">
<form id="fm" method="post" novalidate>
<input type="hidden"   id="tableId"   value="${tableId}">

	<div data-options="region:'west',split:true,border:false" style="padding:0px;overflow:hidden;width:300px">
	${allcontent}	
	</div>

	<div data-options="region:'center'" style="background:#f5fafe;height:100%">
		
			<div id="editor" >
				${content}	
			</div>
		
		<div id="toolbar" class="datagrid-toolbar foot-formopera">
			<table class="tableForm" border="0" cellspacing="1" width='100%'>
				<tr>	
					<td width="50%" align="right">
						<a href="javascript:void(0)" class="easyui-linkbutton primary-btn" onclick="saveObj()" >保存</a>
						<a href="javascript:void(0)" class="easyui-linkbutton"  onclick="parent.dlg_close_only('newform')" >返回</a>
					</td>
				</tr>
			</table>
		</div>
		
	</div>


</form>

<script>
	var dragFlag = false;
	var upFlag = false;
	var isCrossIFrameDragging = false;
	var tableId = "${tableId}";
	var editor;
	$(function(){ 

		var hg=($('#editor').parent().height());
		editor = CKEDITOR.replace( 'editor',{height:hg-95} );
		CKEDITOR.instances.editor.execCommand('maximize');
		setTimeout(function(){
			init();
			bindEvent();
		},500);

		
		editor.on("instanceReady", function () {  
	        this.document.on("click", clickBlank);  
	    });  
		
		editor.on('blur', function() { 
			clickBlank();
		});
		
		function clickBlank(e) {//取消选中状态
			var table = document.getElementsByTagName('iframe')[0].contentWindow.document.getElementById('edittable');
			$(table).find(".active").removeClass("active");

	    }
		
		function bindEvent(){
			var table = document.getElementsByTagName('iframe')[0].contentWindow.document.getElementById('edittable');
		     
				$(table).find("td").on("dragenter",function(ev){
                if(isCrossIFrameDragging) {
                   // $(ev.target).text("hehe");
                }
            })
            .on('dragleave', function(ev) {
                if(isCrossIFrameDragging) {
                   // $(ev.target).text("jiba");
                }
            })
            .on("dragover",function(ev){
                if(isCrossIFrameDragging) {
                    ev.preventDefault();
                    ev.originalEvent.dataTransfer.dropEffect = 'move';
                }
            })
            .on("drop",function(ev){
                var df = ev.originalEvent.dataTransfer;
                var data = df.getData("html");
                if(isCrossIFrameDragging)  {
                    $(ev.target).html(data);
                    if (CKEDITOR.env.ie){
    					
    				}else{
    					$(ev.target).children().each(function(){
    						$(this).attr("contenteditable",false);
    						$(this).attr("data-cke-editable","1");
    						$(this).click(function(){
    							$(table).find(".active").removeClass("active");
    							$(this).addClass("active");
    							editor.focus();
    							//阻止冒泡
    							return false;
    						})
    					});
    				}
                   // iframeWindow.removeDraggingItem();
                }
        
			});

				if (CKEDITOR.env.ie){
					
				}else{
					
					$(table).find("td").children().each(function(){
						$(this).click(function(){
							$(table).find(".active").removeClass("active");
							$(this).addClass("active");
							editor.focus();
							//阻止冒泡
							return false;
						});
					});
				}
		}
		
		
		function init(){
			
			//文本框
			$("#items").find("input[type='text']").on("dragstart",function(ev){
	            var dt = ev.originalEvent.dataTransfer;
	            var html = "<input name=\""+$(this).attr("name")+"\" type=\""+$(this).attr("type")+"\" value=\""+$(this).val()+"\" width=\"173\" style=\"width:173px;\"/>";
	            dt.setData('html',html);
	            dt.effectAllowed = 'move';
	            isCrossIFrameDragging = true;
	        }).on("dragend",function(ev){
	        	isCrossIFrameDragging = false;
	        });
			
			//checkbox
			$("#items").find("input[type='checkbox']").on("dragstart",function(ev){
	            var dt = ev.originalEvent.dataTransfer;
	            var html = "<input name=\""+$(this).attr("name")+"\" type=\""+$(this).attr("type")+"\" value=\"Y\"/>";
	            dt.setData('html',html);
	            dt.effectAllowed = 'move';
	            isCrossIFrameDragging = true;
	        }).on("dragend",function(ev){
	        	isCrossIFrameDragging = false;
	        });
			
			$("#items").find($("p[type='checkbox']")).on("dragstart",function(ev){
	            var dt = ev.originalEvent.dataTransfer;
	            var html = $(this).html();
	            dt.setData('html',html);
	            dt.effectAllowed = 'move';
	            isCrossIFrameDragging = true;
	        }).on("dragend",function(ev){
	        	isCrossIFrameDragging = false;
	        });
			
			//textarea
			$("#items").find("input[type='textarea']").on("dragstart",function(ev){
	            var dt = ev.originalEvent.dataTransfer;
	            var html = "<textarea name=\""+$(this).attr("name")+"\">"+$(this).val()+"</textarea>";
	            dt.setData('html',html);
	            dt.effectAllowed = 'move';
	            isCrossIFrameDragging = true;
	        }).on("dragend",function(ev){
	        	isCrossIFrameDragging = false;
	        });
			
			//下拉框
			$(".select").on("dragstart",function(ev){
	            var dt = ev.originalEvent.dataTransfer;
	            var html = "<select name=\""+$(this).attr("name")+"\" style='width:173px'></select>";
	            dt.setData('html',html);
	            dt.effectAllowed = 'move';
	            isCrossIFrameDragging = true;
	        }).on("dragend",function(ev){
	        	isCrossIFrameDragging = false;
	        });
			
			//radio
			$("#items").find($("p[type='radio']")).on("dragstart",function(ev){
	            var dt = ev.originalEvent.dataTransfer;
	            var html = $(this).html();
	            dt.setData('html',html);
	            dt.effectAllowed = 'move';
	            isCrossIFrameDragging = true;
	        }).on("dragend",function(ev){
	        	isCrossIFrameDragging = false;
	        });
			
			//label文本
			$("#items").find($("p[type='text']")).on("dragstart",function(ev){
	            var dt = ev.originalEvent.dataTransfer;
	            var html = $(this).html();
	            dt.setData('html',html);
	            dt.effectAllowed = 'move';
	            isCrossIFrameDragging = true;
	        }).on("dragend",function(ev){
	        	isCrossIFrameDragging = false;
	        });
			
			/**
			var table = document.getElementsByTagName('iframe')[0].contentWindow.document.getElementById('edittable');
			var iframe = document.getElementsByTagName('iframe')[0];
			$(iframe).mouseover(function(){
				$(this).focus();
			});
			$(table).find("td").each(function(){
				$(this).mouseover(function(){

					$(this).css("background-color","red");
					if (dragFlag){
						upFlag = true;
					}
				});
				$(this).mouseup(function(){
					if (upFlag){
						$(this).html("哈哈");
					}
				});
			});
			**/
		}
	}); 
	
	
	var baseurl = '<%=request.getContextPath()%>';
	var url;
	
	function saveObj() {
		var tableId=$('#tableId').val();
		url = 'platform/eform/formdefine/add?formversion='+encodeURI('${formversion}');
		var tableWidth = $("#edittable").css("width");
		$.post(url, {
			tableId:tableId,
			editor : CKEDITOR.instances.editor.getData()
		}, function(result) {
			if (result.success) {
				parent.dlg_close('newform');
			} else {
				$.messager.show({
					title : '错误',
					msg : result.msg
				});
			}
		}, 'json');
		
	}
	
</script>

    
    
</body>
</html>