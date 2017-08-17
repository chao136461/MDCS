package avicit.discussion_manage.mdcsproposalpicture.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;

import avicit.platform6.core.rest.resteasy.RestEasyController;
import avicit.discussion_manage.mdcsproposalpicture.service.MdcsProposalPictureService;

@RestEasyController
@Path("/api/discussion_manage/mdcsproposalpicture/MdcsProposalPictureRest")
public class MdcsProposalPictureRest{
//    private static final Logger logger =  LoggerFactory.getLogger(MdcsProposalPictureRest.class);
    
	@Autowired
	private MdcsProposalPictureService mdcsProposalPictureService;
	
	@GET
	@Path("/get/v1/{id}")
	@Produces("application/json;charset=UTF-8")
	public String saveProposalPictureData(@PathParam("proposalId") String proposalId,
			@PathParam("saveAddress") String saveAddress,@PathParam("pictureOption") String pictureOption,@PathParam("pictureInfo") byte[] pictureInfo) throws Exception {
		String  rerult = mdcsProposalPictureService.saveProposalPictureData(proposalId,saveAddress,pictureOption,pictureInfo);
		return rerult;
	}
}
