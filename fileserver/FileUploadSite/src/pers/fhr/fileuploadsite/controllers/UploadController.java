package pers.fhr.fileuploadsite.controllers;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {
	private static final Logger logger=Logger.getLogger(UploadController.class);
	
	@RequestMapping(value="/upload",method=RequestMethod.POST)
	@ResponseBody
	public String upload(
			@RequestParam(value="param1",required=true) int param1,
			@RequestParam(value="param2",required=true) String param2,
			MultipartFile file,HttpServletRequest request){
		logger.info(String.format("收到文件:%s 大小:%d", file.getOriginalFilename(),file.getSize()));
		return "";
	}
}
