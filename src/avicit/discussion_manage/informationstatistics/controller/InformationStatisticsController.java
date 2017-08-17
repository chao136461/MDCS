package avicit.discussion_manage.informationstatistics.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import avicit.discussion_manage.informationstatistics.service.InformationStatisticsService;
import avicit.platform6.api.syspermissionresource.permissionanalysis.core.support.LoaderConstant;
import avicit.platform6.core.properties.PlatformConstant.OpResult;
@Controller
@Scope("prototype")
@RequestMapping("discussion_manage/informationstatistics/InformationStatisticsController")
public class InformationStatisticsController implements LoaderConstant{
	@Autowired
	private InformationStatisticsService service;
	/**
	 * 1.零件   装配件对应讨论区的数量统计
	 * 2.建议关闭状态的统计图形   已关闭  未关闭   争议性关闭
	 * 3.各相关部门的建议统计
	 * @param request
	 * @param reponse
	 * @return
	 */
	@RequestMapping(value = "StructureManageInfo")
	public ModelAndView toStructureManage(HttpServletRequest request, HttpServletResponse reponse) {
		ModelAndView mav = new ModelAndView();
			String one = ServletRequestUtils.getStringParameter(request, "one", "");
			String two = ServletRequestUtils.getStringParameter(request, "two", "");
			String three = ServletRequestUtils.getStringParameter(request, "three", "");
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		try {
		/*	零件   装配件对应讨论区的数量统计*/
			result=service.searchStructureManage();
			StringBuffer htmlStr1=new StringBuffer();
			    htmlStr1.append("<graph showNames='1' decimalPrecision='0' caption='零件   装配件讨论区数量统计图'>");
			    for (int i=0;i<result.size();i++) {
					if(result.get(i).get("TYPE").equals("1")){
					htmlStr1.append("<set name='装配件' color='"+one+"' value='"+result.get(i).get("NUM")+"' isSliced='1' link='n-avicit/discussion_manage/informationstatistics/informationstatistics1.jsp'/>");  
					}else{
					htmlStr1.append("<set name='零件' color='"+two+"' value='"+result.get(i).get("NUM")+"' link='n-avicit/discussion_manage/informationstatistics/informationstatistics2.jsp'/>");
					}
			    } 
			htmlStr1.append("</graph>");
			mav.addObject("htmlStr1", htmlStr1.toString());
		
		/*建议关闭状态的统计图形   已关闭  未关闭   争议性关闭*/
			result=service.searchSuggestionClosed();
			StringBuffer htmlStr2=new StringBuffer();
			    htmlStr2.append("<graph showNames='1' decimalPrecision='0' caption='建议关闭状态的统计图' >");
			    for (int i=0;i<result.size();i++) {
					if(result.get(i).get("STATUS").equals("0")){
					htmlStr2.append("<set name='未关闭' color='"+two+"' value='"+result.get(i).get("NUM")+"' isSliced='1'/>");  
					}else if(result.get(i).get("STATUS").equals("1")){
						htmlStr2.append("<set name='已关闭' color='"+one+"' value='"+result.get(i).get("NUM")+"' />");
					}else{
					htmlStr2.append("<set name='争议性关闭'  color='"+three+"' value='"+result.get(i).get("NUM")+"'/>");
					}
			    } 
			htmlStr2.append("</graph>");
			mav.addObject("htmlStr2", htmlStr2.toString());
			
			/* 各相关部门的建议统计*/
			//result=service.searchSuggestionStatistical();
			StringBuffer htmlStr4=new StringBuffer();
			    htmlStr4.append("<chart palette='2' showBorder='0' borderThickness='0'   caption='相关部门建议统计图' shownames='1' showvalues='0' numberPrefix='' showSum='1' decimals='0' useRoundEdges='1' formatNumberScale='0' baseFontSize='12' >");
			//for (int i=0;i<result.size();i++) {
				htmlStr4.append("<categories><category label='强度' /><category label='质审' /><category label='标审' /><category label='重量' /><category label='强度' /></categories>");
				htmlStr4.append("<dataset seriesName='争议性建议' color='"+one+"' showValues='0'><set value='3456' /><set value='7890' /><set value='2145' /><set value='3452' /><set value='4545' /></dataset>");
				htmlStr4.append("<dataset seriesName='常规性建议' color='"+two+"' showValues='0'><set value='3453' /><set value='3232' /><set value='8665' /><set value='5454' /><set value='1111' /></dataset>");
				//}
			htmlStr4.append("</chart>");
			mav.addObject("htmlStr4", htmlStr4.toString());
			mav.addObject("flag", OpResult.success);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mav.addObject("flag", OpResult.failure);
		}
		mav.setViewName("avicit/discussion_manage/informationstatistics/informationstatistics");
		return mav;
	}
	
	
	/**
	 * 装配件审查讨论   审查确认   审查完成的统计图形
	 * @param request
	 * @param reponse
	 * @return
	 */
	@RequestMapping(value = "AuditOpinionInfo1")
	public ModelAndView toAuditOpinion(HttpServletRequest request, HttpServletResponse reponse) {
		ModelAndView mav = new ModelAndView();
		
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();

		try {
			result=service.searchAuditOpinion();
			StringBuffer htmlStr=new StringBuffer();
			    htmlStr.append("<graph showNames='1' decimalPrecision='0' caption='审查状态统计图'>");
			for (int i=0;i<result.size();i++) {
				htmlStr.append("<set name='审查讨论' value='"+result.get(i).get("M2ANDM3_1")+"' isSliced='1'/><set name='审查确认'value='"+result.get(i).get("M4_1")+"'/><set name='审查完成' value='"+result.get(i).get("M5_1")+"'/>");
			}
			
			htmlStr.append("</graph>");
			mav.addObject("htmlStr", htmlStr.toString());
			mav.addObject("flag", OpResult.success);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mav.addObject("flag", OpResult.failure);
		}
		
		mav.setViewName("avicit/discussion_manage/informationstatistics/informationstatistics1");
		return mav;
	}
	/**
	 *零件审查讨论   审查确认   审查完成的统计图形
	 * @param request
	 * @param reponse
	 * @return
	 */
	@RequestMapping(value = "AuditOpinionInfo2")
	public ModelAndView toAuditOpinion1(HttpServletRequest request, HttpServletResponse reponse) {
		ModelAndView mav = new ModelAndView();
		
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();

		try {
			result=service.searchAuditOpinion();
			StringBuffer htmlStr=new StringBuffer();
			    htmlStr.append("<graph showNames='1' decimalPrecision='0' caption='审查状态统计图'>");
			for (int i=0;i<result.size();i++) {
				htmlStr.append("<set name='审查讨论' value='"+result.get(i).get("M2ANDM3_2")+"' isSliced='1'/><set name='审查确认'value='"+result.get(i).get("M4_2")+"'/><set name='审查完成' value='"+result.get(i).get("M5_2")+"'/>");
			}
			
			htmlStr.append("</graph>");
			mav.addObject("htmlStr", htmlStr.toString());
			mav.addObject("flag", OpResult.success);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mav.addObject("flag", OpResult.failure);
		}
		
		mav.setViewName("avicit/discussion_manage/informationstatistics/informationstatistics2");
		return mav;
	}
	
	/**
	 * 建议关闭状态的统计图形   已关闭  未关闭   争议性关闭
	 * @param request
	 * @param reponse
	 * @return
	 */
	/*@RequestMapping(value = "SuggestionClosedInfo")
	public ModelAndView toSuggestionClosed(HttpServletRequest request, HttpServletResponse reponse) {
		ModelAndView mav = new ModelAndView();
		
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();

		try {
			result=service.searchSuggestionClosed();
			StringBuffer htmlStr2=new StringBuffer();
			    htmlStr2.append("<graph showNames='1' decimalPrecision='0' caption='建议关闭状态的统计图' >");
			    for (int i=0;i<result.size();i++) {
					if(result.get(i).get("STATUS").equals("0")){
					htmlStr2.append("<set name='未关闭' value='"+result.get(i).get("NUM")+"' isSliced='1'/>");  
					}else if(result.get(i).get("STATUS").equals("1")){
						htmlStr2.append("<set name='已关闭' value='"+result.get(i).get("NUM")+"' />");
					}else{
					htmlStr2.append("<set name='争议性关闭' value='"+result.get(i).get("NUM")+"'/>");
					}
			    } 
			htmlStr2.append("</graph>");
			mav.addObject("htmlStr2", htmlStr2.toString());
			mav.addObject("flag", OpResult.success);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mav.addObject("flag", OpResult.failure);
		}
		mav.setViewName("avicit/discussion_manage/informationstatistics/informationstatistics");
		return mav;
	}*/
	/**
	 * 各相关部门的建议统计
	 * @param request
	 * @param reponse
	 * @return
	 */
	/*@RequestMapping(value = "SuggestionStatisticalInfo")
	public ModelAndView toSuggestionStatistical(HttpServletRequest request, HttpServletResponse reponse) {
		ModelAndView mav = new ModelAndView();
		
		//List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();

		try {
			//result=service.searchSuggestionStatistical();
			StringBuffer htmlStr=new StringBuffer();
			    htmlStr.append("<graph xAxisName='部门' yAxisName='建议数' caption='相关部门的建议统计表'  decimalPrecision='0' rotateNames='1' numDivLines='3' showValues='0' formatNumberScale='0'>");
			//for (int i=0;i<result.size();i++) {
				htmlStr.append("<categories><category name='Product A'/><category name='Product B'/><category name='Product C'/></categories><dataset seriesName='常规性建议' color='AFD8F8' showValues='0'><set value='25601.34'/><set value='20148.82'/><set value='17372.76'/></dataset><dataset seriesName='争议性建议' color='F6BD0F' showValues='0'><set value='57401.85'/><set value='41941.19'/><set value='45263.37'/></dataset>");
			//}
			htmlStr.append("</graph>");
			mav.addObject("htmlStr", htmlStr.toString());
			mav.addObject("flag", OpResult.success);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mav.addObject("flag", OpResult.failure);
		}
		mav.setViewName("avicit/discussion_manage/informationstatistics/informationstatistics");
		return mav;
	}*/
}
