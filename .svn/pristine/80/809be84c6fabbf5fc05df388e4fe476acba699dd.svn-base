var winWidth = 0;
var winHeight = 0;
var bannerHeight = 0; //logo所占高度
var menuHeigth = 0; //菜单栏所占高度
var tabsHeaderHeight = 0; //tabs的选项卡所占高度
var footerHeight = 0;//底部版权栏所占高度
var leftMenuWidth = 0;//左侧菜单宽度
var options = {
	speed: 'fast',   // slow  fast
   //event: 'click',
	effect: 'fade'  // slide  fade  注意最后一行不能有逗号 IE下不解析
};

var headLeftWidth = 0;
var splitWidth = 0;

/**
 * onload之后加载的方法
 */
$(function(){
	headLeftWidth = $('#head-left').outerWidth(true);
	splitWidth = $('#split').outerWidth(true);
	
	initHeight();
	 //调用函数，获取数值
	if(messageCount > 0){
		$(".msgbg").css("display","block");
	}
	initArrow();
	initMenu();
	reSizeWindow();
	initTabs();
	initPortletSetting();
	window.onresize = reSizeWindowAfter;
	//initSearch();
});

/**
 * 加载各个模块的高度
 */
function initHeight(){
	if($('#divBodyWidth').length == 1){
		if($("#divBodyWidth").css("display") == "block"){
			bannerHeight = $('#divBodyWidth').outerHeight(true);
		}else{
			bannerHeight = 0;
		}
	}
	if($('div.index-menu').length == 1){
		menuHeigth = $('div.index-menu').outerHeight(true);
	}else if($('#headtop').length == 1){
		menuHeigth = $('#headtop').outerHeight(true);
	}
	if($('div.tabs-header').length == 1){
		tabsHeaderHeight = $('div.tabs-header').outerHeight(true);
	}
	if($('#copyFooter').length == 1){
		footerHeight = $('#copyFooter').outerHeight(true);
	}
	
	//获取窗口宽度
    if (window.innerWidth){
	    winWidth = window.innerWidth;
    } else {
	    if ((document.body) && (document.body.clientWidth)){
		   winWidth = document.body.clientWidth;
	    }
    }
    //获取窗口高度
    if (window.innerHeight){
	    winHeight = window.innerHeight;
    } else {
	    if ((document.body) && (document.body.clientHeight)){
	    	winHeight = document.body.clientHeight;
	    }
    }
    //通过深入Document内部对body进行检测，获取窗口大小
    if (document.documentElement  && document.documentElement.clientHeight && document.documentElement.clientWidth){
	    winHeight = document.documentElement.clientHeight;
	    winWidth = document.documentElement.clientWidth;
    }
    //计算左侧菜单宽度
    if($('#head-left').length == 1){
		if($("#head-left").css("display") == "block"){
			leftMenuWidth = $('#head-left').outerWidth(true);
		}else{
			leftMenuWidth = 0;
		}
	}
}

/**
 * 增加一个tabs页签
 * @param tabName
 * @param targetUrl
 * @param icon
 * @param menuCode
 * @param iconPosition
 */
function addTab(tabName,targetUrl,icon,menuCode,iconPosition){
	if(tabName.length > 20){
		tabName = tabName.substring(0, 20);
	}
	var initIframeHeight = winHeight - bannerHeight - menuHeigth - tabsHeaderHeight - footerHeight;
	var initIframeWidth = winWidth - leftMenuWidth;
	var closeFlag = true;
	if(menuCode == "homePage"||menuCode == "portal"){
		closeFlag = false;
	}
	var randomIcon = parseInt(Math.random() * 100000);
	if ($('#tabs').tabs('exists',tabName)){
		$('#tabs').tabs('select', tabName);
		$("." + tabName).attr('src',targetUrl);
		closeMegaDrop();
	} else {
		if(targetUrl.indexOf('javascript') == -1 && targetUrl != ''){//判断当前的targetUrl是url时添加新的tab
			if(icon == '' || icon == null){
				currentTab = $('#tabs').tabs('add',{
					title:tabName,
					content:"<iframe id=iframeBody class='" + tabName  + "' title='" + menuCode +"' style='overflow-y:auto;overflow-x:hidden;height:"+initIframeHeight+"px;width:"+ initIframeWidth +"px;' src="+targetUrl+"  frameborder=0 ></iframe>",
					closable:closeFlag,
					onclick:closeMegaDrop()
				});
			}else{
				currentTab = $('#tabs').tabs('add',{
					title:tabName,
					iconCls : "_" + randomIcon+'tabIcon',
					content:"<iframe id=iframeBody class='" + tabName  + "' title='" + menuCode +"' style='overflow-y:auto;overflow-x:hidden;height:"+initIframeHeight+"px;width:"+ initIframeWidth +"px;' src="+targetUrl+"  frameborder=0 ></iframe>",
					closable:closeFlag,
					onclick:closeMegaDrop()
				});
				$("._" + randomIcon + "tabIcon").css("background","url('" + icon + "') no-repeat").css("background-position",iconPosition);
			}
		}
	}
}

// 关闭当前megaDrop div的。
function closeMegaDrop(){
	$(".sub").hide(); 
	$(".sub-container").hide(); 
	var $parentLi = $(".sub").parent().parent();
	if($parentLi.hasClass('mega-hover')){
		$parentLi.removeClass('mega-hover');
	}
}

/**
 * 初始化tabs
 */
function initTabs(){
	// tabs 标签页切换,隐藏/显fi
    $('#tabs').tabs({
		onSelect : function(title){
			if(title == homePage){
				$(".portlet").css("display","");
			} else {
				$(".portlet").css("display","none");
			}
		}
    });
    
    //为选项卡绑定右键 
	$(".tabs li").live('contextmenu',function(e){  
		/* 选中当前触发事件的选项卡 */  
		var subtitle =$(this).text(); 
		var tabcount = $('#tabs').tabs('tabs').length;
		if(tabcount == 1){
			$("#m-closeother").attr({"disabled":"disabled"}).css({ "cursor": "default", "opacity": "0.4" }); 
			$("#m-closeall").attr({"disabled":"disabled"}).css({ "cursor": "default", "opacity": "0.4" }); 
			$("#m-close").attr({"disabled":"disabled"}).css({ "cursor": "default", "opacity": "0.4" }); 
		}else{
			if(tabcount == 2){
				$("#m-closeall").remove("disabled");
				$("#m-closeall").removeAttr("style");
				if(subtitle == homePage){
					if(typeof($("#m-close").attr("disabled"))== "undefined"){
						$("#m-close").attr({"disabled":"disabled"}).css({ "cursor": "default", "opacity": "0.4" });
					}
					$("#m-closeother").remove("disabled");
					$("#m-closeother").removeAttr("style");
				}else{
					$("#m-close").remove("disabled");
					$("#m-close").removeAttr("style");
					if(typeof($("#m-closeother").attr("disabled")) == 'undefined'){
						$("#m-closeother").attr({"disabled":"disabled"}).css({ "cursor": "default", "opacity": "0.4" });
					}
				}
			}else{
				$("#m-closeother").remove("disabled");
				$("#m-closeall").remove("disabled");
				$("#m-close").remove("disabled");
				$("#m-closeall").removeAttr("style");
				$("#m-close").removeAttr("style");
				$("#m-closeother").removeAttr("style");
				if(subtitle == homePage){
					if(typeof($("#m-close").attr("disabled"))== "undefined"){
						$("#m-close").attr({"disabled":"disabled"}).css({ "cursor": "default", "opacity": "0.4" });
					}
				}
			}
		}
		$('#tabs').tabs('select',subtitle);   
		//显示快捷菜单      
		$('#context_menu').menu('show', {    
	       left: e.pageX,          
	       top: e.pageY        
       });               
		return false;  
	});
	
	//刷新    
	$("#m-refresh").click(function(){
		var currTab = $('#tabs').tabs('getSelected'); 
		//获取选中的标签项      
		var url = $(currTab.panel('options').content).attr('src');  
		var tabName = $(currTab.panel('options').content)[0].className; 
		$("." + tabName).attr('src',url);
    });
	//关闭所有
	 $("#m-closeall").click(function(){
		 $(".tabs li").each(function(i, n){ 
			 var title = $(n).text(); 
			 if(title != homePage){
				 $('#tabs').tabs('close',title);
			 }
			 });  
		 });
	//除当前之外关闭所有
	 $("#m-closeother").click(function(){      
		 var currTab = $('#tabs').tabs('getSelected');     
		 currTitle = currTab.panel('options').title;   
		 $(".tabs li").each(function(i, n){    
			 var title = $(n).text(); 
			 if(currTitle != title && title != homePage){ 
				 $('#tabs').tabs('close',title);   
			 }    
		});   
	});
	 //关闭当前
    $("#m-close").click(function(){     
		var currTab = $('#tabs').tabs('getSelected');   
		currTitle = currTab.panel('options').title; 
		if(currTitle != homePage){
			$('#tabs').tabs('close', currTitle);
		}
	});
}

/**
 * 初始化显示与隐藏banner的按钮
 */
function initArrow(){    
	$('#arrowUpAndDown').click(function(){
		var isShow = $('#arrowUpAndDown').hasClass('arrowUp');
	    if(isShow == false){
		   $("#arrowUpAndDown").removeClass('arrowDown');
		   $("#arrowUpAndDown").addClass('arrowUp');
		   $("#divBodyWidth").show();
		   reSizeTabsHeight();
	    }else{
		   $("#arrowUpAndDown").removeClass('arrowUp');
		   $("#arrowUpAndDown").addClass('arrowDown');
		   $("#divBodyWidth").hide();
		   reSizeTabsHeight();
	    }
	});
};

//切换语言
var changeLgg=function(title){
	var l=$('#language'),d;
	if(l.length ===0){
		d =$('<div id="language"></div>').dialog({    
		    title: title,    
		    width: 300,    
		    height: 150,    
		    closed: false,    
		    cache: true,
		    zIndex:1,
		    modal: true   
		}).html($("<iframe name='languageFrame' id='languageFrame' scrolling='yes' frameborder='0' src='"+baseUrl+"platform/syslanguage/change' style='width:100%;height:91%;'></iframe>"));
		return;
	}
	l.dialog('open');
	
};
// 注销
function logout() {
	if(confirm(logoutTip)) {
		window.location = baseUrl+'platform/user/logout';
	} 
};

function openSubWindow_pwd(){
	$('#modify_dialog').dialog().html($("<iframe name='applyPasFrame' id='applyPasFrame' scrolling='yes' frameborder='0' src='"+baseUrl+"platform/syspassword/sysPasswordController/toPassword' style='width:100%;height:91%;'></iframe>"));
}

function showVersion(){	
	$('#versionDialog').dialog().html($("<iframe name='applyVerFrame' id='applyVerFrame' scrolling='yes' frameborder='0'  src='"+baseUrl+"platform/systemversion/toVesionAlert' style='width:100%;height:100%;'></iframe>"));
};

function reSizeWindow(){
   initHeight();
   $("#tabs").height(winHeight - bannerHeight- menuHeigth - footerHeight);
   $("#head-left").height(winHeight - bannerHeight- menuHeigth - footerHeight);
   //ie6下需要将下面两个的高度一起设置
   $("#left-menu-header").height(winHeight - bannerHeight- menuHeigth - footerHeight);
   $("#split").height(winHeight - bannerHeight- menuHeigth - footerHeight);
   $(".tabs li").each(function(i, n){ 
		 var title = $(n).text(); 
		 var aTab = $('#tabs').tabs('getTab', title);
		 if(aTab){
			 var iframeBody = aTab.find('iframe[id="iframeBody"]');
			 iframeBody.height(winHeight - bannerHeight - tabsHeaderHeight - menuHeigth - footerHeight);  
			 iframeBody.width(winWidth - leftMenuWidth); 
		 }
		 // 结合easyui中的样式，需要把panel-body panel-body-noheader中的宽度也要实现自适应的,当小窗口打开时，再最大化，可以自适应。
		 $('.panel-body-noborder').width(winWidth- leftMenuWidth);
	 });
   $("#divBodyWidth").width(winWidth);
   $("#tabs").width(winWidth - leftMenuWidth);
   $(".tabs-wrap").width(winWidth - leftMenuWidth);
};

function reSizeWindowAfter(){
	try{
		$.fn.dcMegaMenu(options,'1'); //'1',resize窗口的标识为真
	}catch(e){
		//alert(e);
	}
	reSizeWindow();
	//重新计算是否菜单滚动和滚动长度
	initMenu();
};

/**
 * 调整tabs的高度
 */
function reSizeTabsHeight(){
	initHeight();
	$("#tabs").height(winHeight - bannerHeight - menuHeigth - footerHeight);
	$("#head-left").height(winHeight - bannerHeight- menuHeigth - footerHeight);
	//ie6下需要将下面两个的高度一起设置
	$("#left-menu-header").height(winHeight - bannerHeight- menuHeigth - footerHeight);
	$("#split").height(winHeight - bannerHeight- menuHeigth - footerHeight);
	$(".tabs li").each(function(i, n){ 
		 var title = $(n).text(); 
		 var aTab = $('#tabs').tabs('getTab', title);
		 if(aTab){
			 var iframeBody = aTab.find('iframe[id="iframeBody"]');
			 iframeBody.height(winHeight - bannerHeight - tabsHeaderHeight - menuHeigth - footerHeight);  
		 }	 
	});
};

/**
 * 调整tabs的高度
 */
function reSizeTabsWidth(){
	initHeight();
	$("#tabs").width(winWidth - leftMenuWidth);
	$(".tabs-wrap").width(winWidth - leftMenuWidth);
    $(".tabs li").each(function(i, n){ 
		 var title = $(n).text(); 
		 var aTab = $('#tabs').tabs('getTab', title);
		 if(aTab){
			 var iframeBody = aTab.find('iframe[id="iframeBody"]');
			 iframeBody.width(winWidth - leftMenuWidth); 
		 }
		 // 结合easyui中的样式，需要把panel-body panel-body-noheader中的宽度也要实现自适应的,当小窗口打开时，再最大化，可以自适应。
		 $('.panel-body-noborder').width(winWidth- leftMenuWidth);
	 });
	
};

//全文搜索的focus与blur事件
function onFocusEvent(obj){
	if(obj.value==onFocusTip){
		obj.value = "";
	}
};

function onBlurEvent(obj){
	if(obj.value == ""){
		obj.value = onFocusTip;
	}
};


/**
 * 保存选择的portlet
 */
function savePortlet(){
	$('#portletConfigIframe')[0].contentWindow.$saveConfigPersonPortlet();
};

/**
 * 恢复出厂设置
 */
function restDefault(){
	if(confirm(resetPorletTip)) {
		$.ajax({
		    url : baseUrl + "platform/portlet/restDefault",
		    method : "GET",
		    async: false,
		    success: function(msg){
		    	
		   }
		});
		refreshPortlet($("#iframeBody")[0].contentWindow.flag);
    }
	$("#menu").hide();
};

/**
 * 设置全局portlet
 */
function setGlobalPortlet(){
	$("#iframeBody").attr('src',baseUrl + 'platform/portlet/getContent?flag=global');
	$("#menu").hide();
	return false;
};

/**
 * 保存选择的布局
 */
function saveLayout(){
	var checkedValue = '';
	var radios = $('#portletConfigIframe')[0].contentWindow.document.getElementsByName('layout');
	for(i=0;i<radios.length;i++){
		if(radios[i].checked){
			checkedValue = radios[i].value;
		}
	}
	$.ajax({
	    url : baseUrl + "platform/portlet/saveSelectLayout?flag=" + $('#portletConfigIframe')[0].contentWindow.flag,
	    method : "GET",
	    dataType : "text",
	    async: false,
		data : "layoutValue=" + checkedValue,
	    success: function(msg){
	    	
	   }
	});
	hideDialog();
	refreshPortlet();
}

function saveLayout1(){
	var frm = $('#portletConfigIframe')[0].contentWindow;
	var configResultXml = frm.saveConfigResultXml();
	//获取当前操作的数据行记录
	alert('布局设置成功!')
	hideDialog();
	refreshPortlet();
};

function hideDialog(){
	$.mask("close");
    $("#overlay").hide();
    $("#save").unbind("click");
    $("#dialog").fadeOut(100);
};

function refreshPortlet(flag){
	if(typeof(flag) == 'undefined'){
		flag = '';
	}
	$("#iframeBody").attr('src',baseUrl + 'platform/portlet/getContent?flag=' + flag);
	return false;
};

//设置portlet
function setPortlet(){
	$('#setPortlet_dialog').dialog('open');
	return false;
};

function showDialog(modal,title,w,h,iframeSrc,saveClickEvent){
	$.mask();
    $("#overlay").show();
    //指定title
    if(typeof(title) != 'undefined'){
    	$(".web_dialog_title span").text(title);
    }
    if(typeof(title) != 'saveClickEvent'){
    	$("#save").bind("click",function(){
    		eval(saveClickEvent);
    	});
    }
    var left = (screen.availWidth - w -30) / 2;
	var top = (screen.availHeight - h - 30) / 2;
	$("#dialog").css({"width": w,"height": h,top: top,left: left});
	$("#dialog #context").css({"height": h - 75 });
	//设置显示内容
	if(typeof(iframeSrc) != 'undefined'){
		var flag = $("#iframeBody")[0].contentWindow.flag;
		$("#dialog #context").html("<iframe id='portletConfigIframe' name='portletConfigIframe' src='" + iframeSrc + "' width='100%' height='" + (h - 75) + "px' frameborder='0' marginheight='0' marginwidth='0' scrolling='auto'></iframe>")
	} else {
		$("#dialog #context").html('<span></span>');
	}
    $("#dialog").fadeIn(300);
    if(modal){
       $("#overlay").unbind("click");
    }else{
       $("#overlay").click(function (e){
          hideDialog();
       });
    }
    var obj = document.getElementById('dialog');
    rDrag.init(obj);
    $("#menu").hide();
 };

/**
 * dialog 拖动
 */
var rDrag = {
	o:null,
	init:function(o){
		o.onmousedown = this.start;
	},
	start:function(e){
		var o;
		e = rDrag.fixEvent(e);
		e.preventDefault && e.preventDefault();
		rDrag.o = o = this;
		o.x = e.clientX - rDrag.o.offsetLeft;
		o.y = e.clientY - rDrag.o.offsetTop;
		document.onmousemove = rDrag.move;
		document.onmouseup = rDrag.end;
	},
	move:function(e){
		e = rDrag.fixEvent(e);
		var oLeft,oTop;
		oLeft = e.clientX - rDrag.o.x;
		oTop = e.clientY - rDrag.o.y;
		rDrag.o.style.left = oLeft + 'px';
		rDrag.o.style.top = oTop + 'px';
	},
	end:function(e){
		e = rDrag.fixEvent(e);
		rDrag.o = document.onmousemove = document.onmouseup = null;
	},
	fixEvent: function(e){
		if (!e) {
            e = window.event;
            e.target = e.srcElement;
            e.layerX = e.offsetX;
            e.layerY = e.offsetY;
        }
        return e;
    }
};

/**
 * 初始化portlet icon
 * liub
 */
function initPortletSetting(){
	setTimeout(function(){
//		$('#tabs .tabs-header .tabs-wrap').css("width","100%");
		$('#tabs .tabs-header .tabs-wrap .tabs').css("float","left");
		$('#tabs .tabs-header .tabs-wrap').append("<div class='portlet'></div>");
		$(".portlet").click(function(){
             $("#menu").show();
        });
		$("#portlet").bind('mouseout',function(){
			setTimeout(function(){$("#menu").hide();},800);
		});
		$("#tooltip_menu").bind('mouseenter',function(){
			$("#portlet").unbind('mouseout');
		}).bind('mouseleave',function(){
			$("#menu").hide();
		});
	},500);
	// 设置鼠标经过样式
	$("#menu #tooltip_menu a").mouseover(function(){
		$(this).addClass('mouseover');
	}).mouseout(function(){
		$(this).removeClass('mouseover');
	});
	var maskStackCount = 0;//遮罩
	$.mask = function(method){
		if(typeof method == "undefined"){
			method="open";
		}
		if (method == "open") {
			if (maskStackCount == 0) {
				var mask = $("<div id='window-mask' class='window-mask' style='display:none'></div>").appendTo("body");
				mask.css({
					width: $(window).width() + "px",
					height: $(window).height() + "px"
				}).fadeIn(function(){
					$(this).css("filter","alpha(opacity=60)");
				});
				$(window).bind("resize.mask", function(){
					mask.css({
						width: $(window).width() + "px",
						height: $(window).height() + "px"
					});
				});
			}
			maskStackCount++;
		}else if(method == "close"){
			maskStackCount--;
			if(maskStackCount == 0){
				$("#window-mask").fadeOut(function(){
					$("#window-mask").remove();
				});
				$(window).unbind("resize.mask");
			}
		}
		
	 };
     $("#btnClose").click(function (e){
		 $.mask("close");
         hideDialog();
         e.preventDefault();
     });
};

/**
 * 菜单栏滚动实现
 */
function initMenu(){
	var sLeft = $('div.menu-scroller-left');
	var sRight = $('div.menu-scroller-right');
	var tabsWidth = 0;
	var uiObj;
	
	if($('div.index-menu').length > 0){
		$('ul.mega-menu > li').each(function(){
			tabsWidth += $(this).outerWidth(true);
		});
		uiObj = $('ul.mega-menu');
	}else if($('#headtop').length > 0){
		$('#navigation > li').each(function(){
			tabsWidth += $(this).outerWidth(true);
		});
		uiObj = $('#navigation');
	}else{
		return;
	}
	
	if (tabsWidth > winWidth) {
		sLeft.add(sRight).show();
		
		if(false){
			// 循环滚动
			var defaultLeft = sLeft.outerWidth(true) - tabsWidth;
			var beginLeft = sLeft.outerWidth(true);
			var endLeft = sLeft.outerWidth(true) - tabsWidth * 2;
			var liClone = $('ul.mega-menu > li').clone(true);
			$("ul.mega-menu").append(liClone);
			$("ul.mega-menu").append(liClone.clone(true));
			$('ul.mega-menu').css('width', (tabsWidth * 3 + 50) + 'px');
			$('div.menu-header').css('margin-left', defaultLeft + 'px');
			
			//ie6做特殊处理
			if($.browser.msie && $.browser.version == "6.0"){
				sRight.css('left', (winWidth - sRight.outerWidth(true)) + 'px');
				$('div.menu-arrow').css('left', (winWidth - sRight.outerWidth(true) - $('div.menu-arrow').outerWidth()) + 'px');
			}else{
				sRight.css('left', '');
				$('div.menu-arrow').css('left', '');
				$('div.menu-arrow').css('right', sRight.outerWidth(true) + 'px');
			}
			
			//绑定事件
			var scrollInterval;
			$('div.menu-scroller-left').unbind().bind('mouseover', function(e){
				scrollInterval = setInterval(function(){
					var num = parseInt($('div.menu-header').css('marginLeft'));
					num++;
					if(num >= beginLeft){
						num = defaultLeft;
					}
					$("div.menu-header").css({marginLeft: num});
				}, 5);
			}).bind('mouseout', function(e){
				 clearInterval(scrollInterval); 
			})
			
			$('div.menu-scroller-right').unbind().bind('mouseover', function(e){
				scrollInterval = setInterval(function(){
					var num = parseInt($('div.menu-header').css('marginLeft'));
					num--;
					if(num <= endLeft){
						num = defaultLeft;
					}
					$("div.menu-header").css({marginLeft: num});
				}, 5);
			}).bind('mouseout', function(e){
				 clearInterval(scrollInterval); 
			})
		} else {
			// 一般滚动
			uiObj.css('width', (tabsWidth + 50) + 'px');
			$('div.menu-header').css({
				marginLeft: sLeft.outerWidth(true)
			});
			//ie6做特殊处理
			if($.browser.msie && $.browser.version == "6.0"){
				sRight.css('left', (winWidth - sRight.outerWidth(true)) + 'px');
				$('div.menu-arrow').css('left', (winWidth - sRight.outerWidth(true) - $('div.menu-arrow').outerWidth()) + 'px');
			}else{
				sRight.css('left', '');
				$('div.menu-arrow').css('left', '');
				$('div.menu-arrow').css('right', sRight.outerWidth(true) + 'px');
			}
			
			//绑定事件
			var maxScrollWidth = tabsWidth - 1 - winWidth + sRight.outerWidth(true) + $('div.menu-arrow').outerWidth(true);
			$('div.menu-scroller-left').unbind().bind('click', function(e){
				var num = parseInt( $('div.menu-header').css('marginLeft'));
				var pos = Math.min(num + 200, sLeft.outerWidth(true));
				$("div.menu-header").animate({marginLeft: pos}, 50);
			})
			$('div.menu-scroller-right').unbind().bind('click', function(e){
				var num = parseInt( $('div.menu-header').css('marginLeft'));
				var pos = Math.max(num - 200, -maxScrollWidth);
				$("div.menu-header").animate({marginLeft: pos}, 50);
			})
		}

		$('div.menu-scroller-left').hover(
			function(){$(this).addClass('menu-scroller-left-hover');},
			function(){$(this).removeClass('menu-scroller-left-hover');}
		);
		$('div.menu-scroller-right').hover(
			function(){$(this).addClass('menu-scroller-right-hover');},
			function(){$(this).removeClass('menu-scroller-right-hover');}
		);
		
		//将原来菜单创建放倒这里
		if($('#mega-menu').length > 0){
			$('#mega-menu').dcMegaMenu(options,'1');
		}
	} else {
		sLeft.add(sRight).hide();
		uiObj.css('width','100%');
		$('div.menu-header').css('margin-left', '0px');
		sRight.css('left', '');
		$('div.menu-arrow').css('left', '');
		$('div.menu-arrow').css('right', '0px');
		//将原来菜单创建放倒这里
		if($('#mega-menu').length > 0){
			$('#mega-menu').dcMegaMenu(options,'0');
		}
	}
};


/**
 * 切换皮肤
 * @param skin
 */
function switchSkin(skin){
	//不能切换直接返回
	if(skinSwitch != null && skinSwitch == 'false'){
		return;
	}
    //动态切换皮肤
    doChangeSkin(skin, window);
    //保存用户新皮肤
    saveUserSkin(skin);
    //切换皮肤后重新计算是否菜单滚动和滚动长度
    if(typeof(initMenu) != "undefined"){
    	setTimeout(function(){initMenu();},1000);
    }
};

/**
 * 动态切换皮肤
 * @param skin
 * @param win
 */
function doChangeSkin(skin, win){
	//首页平台皮肤切换,todo和portlet样式切换
	var beginHref = 'static/css/platform/themes/';
	switchHref(skin, beginHref, win);
	
	//首页客户自定义皮肤切换,todo和portlet样式切换
	beginHref = 'static/css/custom/themes/';
	switchHref(skin, beginHref, win);
	//easyui换肤
	var beginHref = 'static/js/platform/component/jQuery/jquery-easyui-1.3.5/themes/';
	switchHref(skin, beginHref, win);
	//子页面换肤
	var frames = win.document.getElementsByTagName("iframe");
	for(var i = 0; i < frames.length; i++){
		var childwin = frames[i].contentWindow;
	    if(childwin){
			try {
		      doChangeSkin(skin, childwin);
			}catch(e) {
			  // 在跨域情况下换肤会报错，导致整个换肤失败，所以要吃掉这个异常	
			}
	    }
	}
};

/**
 * 修改link的href
 * @param skin
 * @param beginHref
 * @param win
 */
function switchHref(skin, beginHref, win){
	var links = win.$('link[rel="stylesheet"]');
	for (var i = 0; i < links.length; i++) {
		var href = $(links[i]).attr('href');
		if (href && href.indexOf(beginHref) != -1) {
			var beginIndex = href.indexOf(beginHref) + beginHref.length;
			var endIndex = href.indexOf("/", beginIndex);
			if(endIndex != -1){
				var newHref = href.substring(0, beginIndex) + skin + href.substring(endIndex);
				$(links[i]).attr('href', newHref);
			}
		}
	}
};

/**
 * 保存皮肤设置
 * @param skin
 */
function saveUserSkin(skin){
	$.ajax({
		url : 'platform/syscustomed/sysCustomedController/saveCustomesSkin.json',
		data : {skin : skin},
		type : 'post',
		dataType : 'json',
		success : function(result) {}
	});
};

/******************  左侧菜单方法  ****************/
/**
 * 展开显示菜单
 * @param obj
 */
function showSubMenu(obj) {          
	var others = $(obj).parent().parent().find('> li');
	others.each(function(){
		if(this != $(obj).parent()[0]){
			var othersubMenu =  $(this).find('> ul');
			if(othersubMenu.css('display') != "none"){
				othersubMenu.hide();
				$(this).find('a:first').removeClass("expand");
			}
		}
		
	});
	
	var subMenu =  $(obj).parent().find('> ul');
	if(subMenu.css('display') == "none"){
		subMenu.show();
		$(obj).find('> a').addClass("expand");
	}else{
		subMenu.hide();
		$(obj).find('> a').removeClass("expand");
	}
}

/**
 * 
 * @param span
 */
function selectMenu(span){
	$(span).addClass("select");
}

/**
 * 
 */
function unSelectMenu(span){
	$(span).removeClass("select");
}

/**
 * 伸缩左侧菜单
 * @param obj
 */
function doSplit(obj){
	if($(obj).hasClass('open')){
		$('#head-left').animate({width: headLeftWidth}, {
			speed: 500,
			complete: function(){
				$('#left-menu-header').show();
				$(obj).removeClass('open');
				reSizeTabsWidth();
				$('#divBody').css('left', (headLeftWidth + 1) + 'px')
			}
		});
	}else{
		$('#head-left').animate({width: splitWidth}, {
			speed: 500,
			complete: function(){
				$('#left-menu-header').hide();
				$(obj).addClass('open');
				reSizeTabsWidth();
				$('#divBody').css('left', (splitWidth + 1) + 'px')
			},
			step: function(){
				
			}
		});
	}
}

/******************  全文检索方法  ****************/

function initSearch(){
	var fulltextSearchPath = "platform/search/search.html";
	$('#subjectSearch').bind('keyup', function(event) {
		if (event.keyCode == "13") {
			doSearchAddTab();
		}
	});
	$('#searchIcon').bind('click', function(event) {
		doSearchAddTab();
	});
}

function doSearchAddTab(){
	var keywords = $.trim($('#subjectSearch').val());
	if (keywords != "") {
		keywords = encodeURIComponent(keywords);
		keywords = encodeURIComponent(keywords);
		addTab('搜索首页', 'platform/search/search.html?keywords=' + keywords
				+ '&isFromAgile=1', '', 'sysSearchResult', '');
	}
}

function openDeptWin(){
	//$('#change_dept_dialog').html($("<iframe name='deptFrame' id='deptFrame' scrolling='yes' frameborder='0' src='"+baseUrl+"platform/sysuser/toChangeDept' style='width:100%;height:91%;'></iframe>"));
	//l.dialog('open');
	$('#change_dept_dialog').dialog().html($("<iframe name='deptFrame' id='deptFrame' scrolling='yes' frameborder='0' src='"+baseUrl+"platform/sysuser/toChangeDept' style='width:100%;height:91%;'></iframe>"));
};

//在新页面打开工作台
function openMyWorkbench(){
	//不加avicit-platform6-main在ie打开出错
	//window.open("platform/myWorkbenchController/getIndex");
	window.open("/avicit-platform6-main/platform/myWorkbenchController/getIndex");
}