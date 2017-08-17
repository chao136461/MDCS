package avicit.discussion_manage.mdcsproposalpicture.controller;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import avicit.platform6.api.syspermissionresource.permissionanalysis.core.support.LoaderConstant;
import avicit.discussion_manage.mdcsproposalpicture.dto.MdcsProposalPictureDTO;
import avicit.discussion_manage.mdcsproposalpicture.service.MdcsProposalPictureService;

/**
 * @classname: MdcsProposalPictureController
 * @description: 定义  建议发表截图信息表 控制层
 * @author:   AVICIT DEV 
 */
@Controller
@Scope("prototype")
@RequestMapping("discussion_manage/mdcsproposalpicture/MdcsProposalPictureController")
public class MdcsProposalPictureController implements LoaderConstant{
    private static final Logger logger =  LoggerFactory.getLogger(MdcsProposalPictureController.class);
    
    @Autowired
    private  MdcsProposalPictureService service;
    /**
     * 根据建议id查询图片
     * @param req
     * @param response
     */
    @RequestMapping(value = "/operation/picture")
	public void toOpertionPage(HttpServletRequest req,HttpServletResponse response) {
    	//建议id
    	String id = req.getParameter("id");
		ServletOutputStream out = null;
		try {
			MdcsProposalPictureDTO dto = service.queryCappFileWordByPrimaryKey(id);
			if(dto!=null){
				//图片
				byte[] picture = dto.getPictureInfo();
				out = response.getOutputStream();
				out.write(picture);
				out.flush();
				out.close();
			}
		} catch (Exception e) {
			logger.debug(e.getMessage());
			e.printStackTrace();
		}
	}
	}
