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
<title>建议回复区</title>
<base href="<%=ViewUtil.getRequestPath(request)%>">
</head>
<jsp:include
	page="/avicit/platform6/component/common/EasyUIJsInclude.jsp"></jsp:include>
<jsp:include
	page="/avicit/discussion_manage/structuralrelationship/ztreeLayuiInclude.jsp"></jsp:include>
<script type="text/javascript" src="avicit/discussion_manage/discussionmanage/js/jquery.mousewheel.js"></script>
<link rel="stylesheet" type="text/css" href="avicit/discussion_manage/discussionmanage/js/Forum.css"/>
<%
	String id = request.getParameter("id");
	String instancenumber = request.getParameter("instancenumber");
	String applyUserId = SessionHelper.getLoginSysUser(request).getId();
	String applyUserName = SessionHelper.getLoginSysUser(request).getName();
	String applyDeptId = SessionHelper.getCurrentDeptId(request);
	//String applyDeptName = SessionHelper.getCurrentDeptTl(request).getDeptName();
%>


<script type="text/javascript">
	//当前评论主键Id
	var id = '<%=id%>'; 
	//实例号
	var instancenumber = '<%=instancenumber%>'; 
	//当前登录人名称
	var applyUserName='<%=applyUserName%>';
	//当前登录人ID
	var applyUserId='<%=applyUserId%>';
	//当前登录人部门
	var applyDeptId='<%=applyDeptId%>';
	//回复数据
	var datas;
	//评论数据
	var proposal;
	//被回复人
	var hideId;
    $(function(){
    	//解决样式加载问题
    	document.body.style.visibility = 'visible';
    	//获取list容器
    	var list = $('#list');
    	//获取list的所有子元素
    	var lis = list.children();
    	//时间
    	var timer;
    	 $.ajax({
    		url:'platform/discussion_manage/proposalmanage/ProposalManageController/operation/forum',
    		data:{id:id},
    		type:'post',
    		dataType : 'json',
    		success:function(r){ 
    			 if(r.flag=="success"){ 
    				datas=r.datas;
    				proposal=r.proposal;
    				//建议内容
    				var pstr = proposal.proposalMain;
    				$('.txt').html(pstr);
    				//建议提出人
    				$('#rperl').html(proposal.proposalUserIdAlias);
    				//建议提出人部门
    				$('#depts').html(proposal.proposalUserDeptAlias);
    				//建议提出人的联系方式
    				//$('.tel').html('手机:'+proposal.mobile+',座机:'+proposal.officeTel);
    				//$('#headse').attr('title','手机:'+proposal.mobile+',座机:'+proposal.officeTel);
    				$('#headse').tooltip({
    					position: 'right',
    					content: '<table style="color:#fff"><tr height="20px;"><td width="12%">座机:</td><td width="88%" algin="center">'+proposal.officeTel+'</td></tr><tr height="20px;"><td>手机:</td><td>'+proposal.mobile+'</td></tr><tr height="20px;"><td>单位:</td><td>'+proposal.deptNameIds+'</td></tr></table>',
    					onShow: function(){ 
    						$(this).tooltip('tip').css({ 
    							backgroundColor: '#666',  
    							borderColor: '#666'
    						}); 
    					}});
    				//建议图片
    				$('.pic').attr('src','platform/discussion_manage/mdcsproposalpicture/MdcsProposalPictureController/operation/picture.json?id='+proposal.id);
    				//建议提出时间
    				$('.time').html("发表于:&nbsp;"+formate(proposal.publicationTime));
    				//建议标题
    				$('h1').html(proposal.proposalName);
    				//零件名称
    				$('font').html('★'+proposal.strCode+'★'+'机身大部段');
    				//赞数据
    				$('.praises-total').attr('total',proposal.praise==null?'0':proposal.praise);
    				//赞初始化
    				if(proposal.praise!=null&&proposal.praise!='0'){
    					$('.praises-total').html(proposal.praise+'个人觉得很赞');
    				}else{
    					$('.praises-total').hide();
    				}
    				//建议提出人图像
    				if(proposal.proposalUserId!=null){//有则查找
      		            $('#headse').attr('src','platform/sysuser/photo/upload/headerphoto?sysUserId='+proposal.proposalUserId);
       				}else{//没有则默认
       					$('#headse').attr('src','static/images/platform/sysuser/userPhoto.gif');
       				}
    			for(var i = 0,len =datas.length;i<len;i++){
    				//楼层集合
  				 	var commentList = $('.comment-list')[0];
    				//回复框
  		            var textarea = $('.comment')[0];
    				//被回复人
  		            var duser = datas[i].proposalUserIdAlias?'<font style="font-size:14px;">回复</font><span class="user">'+datas[i].proposalUserIdAlias+'</span>:':':';
	  		        //回复人图像
  		            var imgurl;
	  		          if(datas[i].replierUserId!=null){//已有图像
	  			            imgurl ='platform/sysuser/photo/upload/headerphoto?sysUserId='+datas[i].replierUserId;
	  					}else{//默认图像
	  						imgurl ='static/images/platform/sysuser/userPhoto.gif';
	  					}
	  		        var repliPraise = datas[i].praise==null?'':datas[i].praise;  
	  		        var total = datas[i].praise==null?'0':datas[i].praise; 
	  		        //创建容器
  		            var commentBox = document.createElement('div');
  		            commentBox.className = 'comment-box clearfix';
  		            commentBox.setAttribute('user', 'self');
  		            commentBox.innerHTML = 
  		                '<img  class="myhead" src="'+imgurl+'" alt=""/>' +
  		                    '<div class="comment-content">' +
  		                    '<p class="comment-text"><span class="user">'+datas[i].replierUserIdAlias+'</span>'+'<span>'+duser+'</span>'+ datas[i].proposalMain + '</p>' +
  		                    '<p class="comment-time">' +
  		                  formate(datas[i].publicationTime) +
  		                     '<a href="javascript:;" class="comment-praise" total="'+total+'" my="0" you="'+datas[i].id+'" style="">'+(repliPraise=='0'?'':repliPraise)+'赞</a>' + 
  		                    '<a href="javascript:;" class="comment-operate" de="'+datas[i].id+'" re="'+datas[i].replierUserId+'">删除</a>' +
  		                  '<a href="javascript:;" class="comment-reply" re="'+datas[i].replierUserId+'">回复</a>'
  		                    +'</p>' +
  		                    '</div>';
  		            commentList.appendChild(commentBox);
  		            //提示框
  		        	$($('.myhead')[i]).tooltip({
    					position: 'right',
    					content: '<table style="color:#fff"><tr height="20px;"><td width="12%">座机:</td><td width="88%" algin="center">'+datas[i].officeTel+'</td></tr><tr height="20px;"><td>手机:</td><td>'+datas[i].mobile+'</td></tr><tr height="20px;"><td>单位:</td><td>'+datas[i].deptNameIds+'</td></tr></table>',
    					onShow: function(){ 
    						$(this).tooltip('tip').css({ 
    							backgroundColor: '#666',  
    							borderColor: '#666'
    						}); 
    				}});
  		            //清空回复框
  		            textarea.value = '';
  		            //光标事件
  		            textarea.onblur();
  		           }
    			
    			
    			}
    		}
    	});
        //把事件代理到每条分享div容器
    	for(var i=0;i<lis.length;i++){
    		//给每一个点击的按钮加上点击事件
    		lis[i].onclick = function(e){
    			e = e || window.event;//兼容ie8问题
    			var el = e.srcElement;
    			
    			switch (el.className) {
                //刷新分享
                case 'flush':
                    flush();
                    break;

                 //赞分享
                case 'praise':
                    praiseBox(el.parentNode.parentNode.parentNode, el);
                    break;

                //回复按钮栏
                case 'btn':
                    reply(el.parentNode.parentNode.parentNode, el);
                    break;

                //回复按钮灰
                case 'btn btn-off':
                    clearTimeout(timer);
                    break;

                //赞留言
                case 'comment-praise':
                    praiseReply(el);
                    break;
                    
                //回复回复人
                case 'comment-reply':
                	operate(el);
                    break;
                    
                //操作留言
                case 'comment-operate':
                    operate(el);
                    break; 
            }
    		
    		};
    	};
    	//获取工具按钮btnlist容器
    	var btnlist = $('#ToolBars');
    	//获取btnlist的所有子元素
    	var btnlis = btnlist.children();
    	//把事件代理到每个div容器
    	for(var i=0;i<btnlis.length;i++){
    		//给每一个点击的按钮加上点击事件
    		btnlis[i].onclick = function(e){
    			e = e || window.event;//兼容ie8问题
    			var el = e.srcElement;
    			switch (el.className) {
                //刷新分享
                case 'shuaxin':
                    flush();
                    break;
                //关闭页签
                case 'guanbi':
                	closeForm();
                    break;
                //建议点赞
                case 'dianzan':
                 	praiseBoxs(el.parentNode.parentNode.parentNode, el);
                    break;
                //取消点赞
                case 'qxzan':
                 	praiseBoxs(el.parentNode.parentNode.parentNode, el);
                    break;
                //建议回复
                case 'huifu':
                	operates(el);
                	break;
            }
    		};
    	};
    	//评论
        var textArea = $('.comment')[0];
        //评论获取焦点
        textArea.onfocus = function () {
            this.parentNode.className = 'text-box text-box-on';
            this.value = this.value == '评论…' ? '' : this.value;
            this.onkeyup();
        };
        //评论失去焦点
        textArea.onblur = function () {
            var me = this;
            var val = me.value;
            if (val == '') {
                timer = setTimeout(function () {
                    me.value = '评论…';
                    me.parentNode.className = 'text-box';
                }, 200);
            }
        };

        //评论按键事件
        textArea.onkeyup = function () {
            var val = this.value;
            var len = val.length;
            var els = this.parentNode.children;
            var btn = els[1];
            var word = els[2];
            if (len <=0 || len > 500) {
                btn.className = 'btn btn-off';
            }else {
                btn.className = 'btn';
            }
            word.innerHTML = len + '/500';
        }
	  	//body绑定滚轮事件
		$('#bodys').bind('mousewheel', function(event, delta) {
		  	var dir = delta > 0 ? 'Up' : 'Down';
		  	if (dir == 'Up') {//上
		  		$('#nouses').hide();
		  	} else {//下
		  		$('#nouses').show();
		  	}
		  	});
	  	
		//小飞机绑定点击事件
	    $('.goTop').click(function(){
	        var timer = setInterval(function () {
	          var toTop = document.documentElement.scrollTop || document.body.scrollTop;
	          // 判断是否到达顶部，到达顶部停止滚动，没到达顶部继续滚动
	          if(toTop == 0){
	            clearInterval(timer);
	          }else {
	            // 设置滚动速度
	            var speed = Math.ceil(toTop/5);
	            // 页面向上滚动
	            document.documentElement.scrollTop=document.body.scrollTop=toTop-speed;
	          };
	        },5);
	      });
    });
	
	
    /**
     * 建议赞分享
     * @param box 每个分享的div容器
     * @param el 点击的元素
     */
    function praiseBox(box, el) {
        var txt = el.innerHTML;
        var praisesTotal = $('.praises-total')[0];
        var oldTotal = parseInt(praisesTotal.getAttribute('total'));
        //赞数据结果
        var newTotal;
        if (txt == '赞') {
        	//点赞，赞数量加1
            newTotal = oldTotal + 1;
            praisesTotal.setAttribute('total', newTotal);
            praisesTotal.innerHTML = (newTotal == 1) ? '我觉得很赞' : '我和' + oldTotal + '个人觉得很赞';
            el.innerHTML = '取消赞';
            savePraise(id,newTotal);
        }
        else {//取消，赞数量减1
            newTotal = oldTotal - 1;
            praisesTotal.setAttribute('total', newTotal);
            praisesTotal.innerHTML = (newTotal == 0) ? '' : newTotal + '个人觉得很赞';
            el.innerHTML = '赞';
            savePraise(id,newTotal);
        }
        praisesTotal.style.display = (newTotal == 0) ? 'none' : 'block';
    } 
    /**
     * 建议点赞
     * @param box 每个分享的div容器
     * @param el 点击的元素
     */
    function praiseBoxs(box, el) {
        var praisesTotal = $('.praises-total')[0];
        var oldTotal = parseInt(praisesTotal.getAttribute('total'));
        //赞数据结果
        var newTotal;
        //当前按钮的class名
       	var name = el.className;
         if(name == 'dianzan'){//点赞
        	$('.dianzan').attr('class','qxzan');
        	//点赞，赞数量加1
            newTotal = oldTotal + 1;
            praisesTotal.setAttribute('total', newTotal);
            praisesTotal.innerHTML = (newTotal == 1) ? '我觉得很赞' : '我和' + oldTotal + '个人觉得很赞';
            savePraise(id,newTotal);
        }else{//取消赞
        	$('.qxzan').attr('class','dianzan');
        	newTotal = oldTotal - 1;
            praisesTotal.setAttribute('total', newTotal);
            praisesTotal.innerHTML = (newTotal == 0) ? '' : newTotal + '个人觉得很赞';
            savePraise(id,newTotal);
        }
        praisesTotal.style.display = (newTotal == 0) ? 'none' : 'block';
    } 
	
    /**
     * 发评论
     * @param box 每个分享的div容器
     * @param el 点击的元素
     */
    function reply(box, el) {
    	//获取被回复人ID
        var proposalUserId = hideId;
    	//当前选中楼层
        var commentList = $('.comment-list')[0];
        //回复框
        var textarea = $('.comment')[0];
        var commentBox = document.createElement('div');
        //回复的内容
        var text = textarea.value;
        //被回复人名称
        var proposalUser = text.substring(3,text.indexOf(':'));
       	//回复内容
        var proposalMain = text.substring(text.indexOf(':')+1);
       	//被回复人
       	var proposaName = proposalUserId?'<font style="font-size:14px;">回复</font><span class="user">'+proposalUser+'</span>:':':';
        commentBox.className = 'comment-box clearfix';
        commentBox.setAttribute('user', 'self');
       
      //保存回复内容
        $.ajax({
        	url:'platform/discussion_manage/proposalmanage/ProposalManageController/operation/replyAdd',
        	data : {data :JSON.stringify({replierUserId:applyUserId,proposalMain:proposalMain,proposalUserDept:applyDeptId,publicationTime:formate(new Date()),parentId:id,proposalUserId:proposalUserId,strId:proposal.strId})},
   		 	type : 'post',
   			dataType : 'json',
        	success:function(r){
        		if(r.flag=='success'){//成功清空回复框中内容
       			 commentBox.innerHTML =
       	                '<img class="myhead" src="platform/sysuser/photo/upload/headerphoto?sysUserId='+applyUserId+'" alt=""/>' +
       	                    '<div class="comment-content">' +
       	                    '<p class="comment-text"><span class="user">'+applyUserName+'</span>'+'<span>'+proposaName+'</span>'+ proposalMain + '</p>' +
       	                    '<p class="comment-time">' +
       	                    formate(new Date()) +
       	                     '<a href="javascript:;" class="comment-praise" total="0" my="0" you="'+r.id+'" style="">赞</a>' + 
       	                    '<a href="javascript:;" class="comment-operate" de="'+r.id+'" re="'+applyUserId+'">删除</a>' +
       	                    '<a href="javascript:;" class="comment-reply" re="'+applyUserId+'">回复</a>'+
       	                    '</p>' +
       	                    '</div>';
       	            //当前集合添加子元素
       	            commentList.appendChild(commentBox);
        			textarea.value = '';
                    textarea.onblur();
        		}else{//失败给出提示
        			$.messager.alert(
      					  '温馨提示',
      					 '操作失败！',
      					 'warning'
      				 );
        		}
        	}
        });
        
    }

    /**
     * 操作留言
     * @param el 点击的元素
     */
    function operate(el) {
    	//当前选中节点
        var commentBox = el.parentNode.parentNode.parentNode;
        //hideId为被回复人ID
        hideId = el.getAttribute('re');
        //var box = commentBox.parentNode.parentNode.parentNode;
        var txt = el.innerHTML;
        //user为被回复人
        var user = el.parentNode.parentNode.firstChild.firstChild.innerHTML;
        //var user = $('.user')[0].innerHTML;
        //回复框
        var textarea = $('.comment')[0];
        //回复操作
        if (txt == '回复') {
        	//若被回复人为当前登录人自己，则返回
        	if(hideId==applyUserId){
            	$.messager.alert(
					  '温馨提示',
					 '不能自己回复自己！',
					 'warning'
				 );
            	return;
            }
        	//获取回复框焦点
            textarea.focus();
        	//回复框内显示内容
            textarea.value ='回复 '+user+':';
        	//键盘事件
            textarea.onkeyup();
        }else {//回复内容删除操作
        	//如果将要删除的回复不是自己(建议发表人除外)，则无法删除
        	if(applyUserId!=hideId&&applyUserId!=proposal.proposalUserId){
        		$.messager.alert(
  					  '温馨提示',
  					 '不能删除别人的回复！',
  					 'warning'
  				 );
              	return;
        	}else{//不是自己可以删除(建议发表人除外)
        		var deleid = el.getAttribute('de');
            	removeNode(commentBox,deleid);
        	}
        }
    }
    /**
     * 操作留言
     * @param el 点击的元素
     */
    function operates(el) {
    	debugger;
    	//当前选中节点
        var commentBox = el.parentNode.parentNode.parentNode;
        var txt = el.innerHTML;
        //回复框
        var textarea = $('.comment')[0];
       	//获取回复框焦点
        textarea.focus();
       	//回复框内显示内容
        textarea.value ='回复 '+user+':';
       	//键盘事件
        textarea.onkeyup();
        
    }
    //日期格式化
    function formate(value) {
		if (value) {
			return new Date(value).format("yyyy-MM-dd hh:mm:ss");
		}
		return '';
	};
	
    //删除节点
    function removeNode(node,deleid) {
    
    $.messager.confirm('请确认','您确定要删除当前所选的数据？',function(b){
   		if(b){
        node.parentNode.removeChild(node);
        var ids = [];
        ids.push(deleid);
        $.ajax({
  			url:'platform/discussion_manage/proposalmanage/ProposalManageController/operation/delete',
    		data:JSON.stringify(ids),
    		contentType : 'application/json',
    		type:'post',
    		dataType : 'json',
    		success:function(r){
    			//预留
    		}
  		});
   		}
   	 });
    }
    /**
     * 回复点赞
     * @param el 点击的元素
     */
    function praiseReply(el) {
        var myPraise = parseInt(el.getAttribute('my'));
        var oldTotal = parseInt(el.getAttribute('total'));
        var newTotal;
        var id = el.getAttribute('you');
        if (myPraise == 0) {//点赞操作，数量加1
            newTotal = oldTotal + 1;
            el.setAttribute('total', newTotal);
            el.setAttribute('my', 1);
            el.innerHTML = newTotal + ' 取消赞';
            savePraise(id,newTotal);
        }
        else {//取消赞操作，数量减1
            newTotal = oldTotal - 1;
            el.setAttribute('total', newTotal);
            el.setAttribute('my', 0);
            el.innerHTML = (newTotal == 0) ? '赞' : newTotal + ' 赞';
            savePraise(id,newTotal);
        }
        el.style.display = (newTotal == 0) ? '' : 'inline-block'
    }
    //保存赞的数据
    function savePraise(repliId,praise){
    	$.ajax({
  			url:'platform/discussion_manage/proposalmanage/ProposalManageController/operation/commonality',
    		data:{data :JSON.stringify({id:repliId,praise:praise})},
    		type:'post',
    		dataType : 'json',
    		success:function(r){
    			//预留
    		}
  		});
    }
    	
  	//刷新页面
    function flush() {
    	window.location.reload();
    }
	  // 获取视窗高度
	  var winHeight = document.documentElement.clientHeight;
	  window.onscroll = function () {
	    // 获取页面向上滚动距离，chrome浏览器识别document.body.scrollTop，而火狐识别document.documentElement.scrollTop，这里做了兼容处理
	    var toTop = document.documentElement.scrollTop || document.body.scrollTop;
	    // 如果滚动超过一屏，返回顶部按钮出现，反之隐藏
	    if(toTop>=50){//大于50显示
	      $('.goTop').css('display','block');
	    }else {//隐藏
	    	$('.goTop').css('display','none');
	    };
	  }
	  //关闭标签页
	  function closeForm() {
			if (parent != null && parent.$('#tabs') != null) {
				var currTab = parent.$('#tabs').tabs('getSelected');
				var currTitle = currTab.panel('options').title;
				parent.$('#tabs').tabs('close', currTitle);
			}
		} 
	  
	  
	//设置争议，关闭，视角转换按钮事件
  	function conversion(state){
  		switch (state) {
  		//视角转换
		case 'conversion':
			  var objShell = new ActiveXObject("WScript.Shell"); 
			  objShell.Run("D:\\1.bat "+proposal.id+" "+proposal.strCode+" 1 "+instancenumber+""); 
			  objShell = null; 
			break;
		//关闭按钮	
		case 'padlock':
			if(applyUserId==proposal.proposalUserId){
				$.messager.confirm('提示','您确定要关闭当前建议吗？',function(b){	
					if(b){
						$.ajax({
				  			url:'platform/discussion_manage/proposalmanage/ProposalManageController/operation/commonality',
				    		data:{data :JSON.stringify({id:id,status:'1'})},
				    		type:'post',
				    		dataType : 'json',
				    		success:function(r){
				    			if(r.flag=="success"){
				    				$.messager.alert(
				     					 '温馨提示',
				     					 '建议关闭成功！',
				     					 'warning'
				     				 );
				    			}
				    		}
				  		});
					}
				});
			}else{
				$.messager.alert(
   					 '温馨提示',
   					 '只有建议发表人才可以关闭建议！',
   					 'warning'
   				 );
				/* $.messager.show({
				 title : '提示',
				 msg : '只有建议发表人才可以关闭建议！'
			}); */
			}
			break;
			
		//设置争议	
		case 'disputed':
			if(applyUserId==proposal.proposalUserId){
			 $.ajax({
	  			url:'platform/discussion_manage/proposalmanage/ProposalManageController/operation/commonality',
	    		data:{data :JSON.stringify({id:id,isDispute:'1'})},
	    		type:'post',
	    		dataType : 'json',
	    		success:function(r){
	    			if(r.flag=="success"){
	    				var url = 'platform/discussion_manage/disputememo/DisputeMemoController/operation/disputed?id='+id;
	    				this.nData = new CommonDialog("disputed","410","300",url,"争议备忘",false,true,false);
	    				this.nData.show();
	    			}
	    		}
	  		});
			}else{
				$.messager.alert(
    					 '温馨提示',
    					 '只有建议发表人才可以设置争议！',
    					 'warning'
    				 );
				 /* $.messager.show({
					 title : '提示',
					 msg : '只有建议发表人才可以设置争议！'
				}); */
			}
			break;
		};
  		
  	};
  //图片放大
	function magnify(){
		var wid = $(".pic").attr("width");
		if(wid=="50%"){
			$(".pic").attr("width","70%").attr("height","70%");
		}else{
			$(".pic").attr("width","50%").attr("height","50%");	
		}
	}
  
  
</script>
    
</head>
<body id="bodys" style="background-image: url('avicit/discussion_manage/discussionmanage/js/images/666.jpg');;visibility:hidden;">
<div id="nouses" ><h1 style="font-size: 20px;line-height: 30px;padding-left: 80px;display: inline;font-family: SimHei;"></h1><font style="float: right;font-size: 15px;line-height: 40px;display: inline;font-family: SimHei;"></font></div>
<div id="list">
        <div style="background-color: #fff;height: 50px;width:100.3%;text-align: left;display: inline-block;"><h1 style="font-size: 20px;line-height: 50px;padding-left: 80px;display: inline;font-family: SimHei;"></h1><font style="float: right;font-size: 15px;line-height: 50px;display: inline;padding-right: 5px;"></font></div>
        <div class="box clearfix">
		<!-- <img title="刷新" class="flush" src="avicit/discussion_manage/discussionmanage/js/images/flush.jpg"> -->
            <div class="head">
            <table>
            <tr>
            <td align="center"><img id="headse" class="heads"  alt=""/></td>
            </tr>
            <tr>
            	<td align="center"><p id="rperl"></p></td>
            </tr>
            <tr>
            	<td align="center"><p id="depts"></p></td>
            </tr>
            </table>
            </div>
            <div class="content">
                <div class="main">
                	<!-- <p class="tel"></p> -->
                    <p class="txt"></p>
                    <img class="pic" width="50%" height="50%" src="avicit/discussion_manage/discussionmanage/js/images/botton.png" alt="" onclick="magnify();"/>
                </div>
                <div class="info clearfix">
                   <span class="time"></span>
                   <a class="praise" href="javascript:;">赞</a> 
                   <a title="设置争议" id="praise" 
						class="easyui-linkbutton"  data-options="iconCls:'icon-flagred'" onclick="conversion('disputed');" plain="true" 
						href="javascript:void(0);">设置争议</a>
                   <a id="praise" title="关闭" 
						class="easyui-linkbutton"  data-options="iconCls:'icon-lockzy'" onclick="conversion('padlock');" plain="true" 
						href="javascript:void(0);">关闭</a>
                   <a id="praise" title="视角转换" 
						class="easyui-linkbutton"  data-options="iconCls:'icon-world_link'" onclick="conversion('conversion');" plain="true" 
						href="javascript:void(0);">视角转换</a>
                </div>
                 <div class="praises-total" style="display: block;"></div> 
                 <!-- <a href="javascript:;" class="comment-praises" total="0" my="0" style="">赞</a> -->
                <div class="comment-list">
                    <div class="comment-box clearfix" user="self">
                    </div>
                </div>
                <div class="text-box">
                    <textarea class="comment" autocomplete="off">评论…</textarea>
                    <button class="btn ">回 复</button>
                    <span class="word"><span class="length">0</span>/500</span>
                </div>
            </div>
        </div>
    </div>
<div class="goTop" style="display: none;"></div>
	<ul id="ToolBars" class="ToolBars">
		<li><div class="guanbi"></li>
		<li><div class="shuaxin"></li>
		<li><div class="dianzan"></li>
		<li><div class="huifu"></li>
	</ul>



</body>
</html>