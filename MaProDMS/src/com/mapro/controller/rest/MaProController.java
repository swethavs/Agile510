package com.mapro.controller.rest;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;

import com.mapro.model.CmsLoginObject;
import com.mapro.model.MaProCmsMetaData;
import com.mapro.mongo.utility.MaProUtility;
import com.mapro.service.delegate.MaProServiceDelegate;
import com.mongodb.gridfs.GridFSDBFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class MaProController {

	private static String username;
	@Autowired
	public MaProServiceDelegate maProServiceDelegate;
	
	@RequestMapping(value="/cmslogin",method=RequestMethod.GET)
	public ModelAndView cmsLoginView(HttpServletRequest request, HttpServletResponse response) {
		 ModelAndView model = new ModelAndView("cmsLoginView");
		 CmsLoginObject cmsLoginObject = new CmsLoginObject();
		 model.addObject("cmsLoginObject", cmsLoginObject);
		 return model;

	}
	
	@RequestMapping(value="/cmslogin",method=RequestMethod.POST)
	 public ModelAndView executeLogin(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("cmsLoginObject")CmsLoginObject cmsLoginObject)
	 {
		boolean isValidMaProUser = maProServiceDelegate.validateMaProUser(cmsLoginObject.getMaProUserId(),cmsLoginObject.getMaProPassword());
		System.out.println("CMS USER ID:::::"+cmsLoginObject.getMaProUserId());
		System.out.println("CMS PASSWORD ID:::::"+cmsLoginObject.getMaProPassword());
		username=cmsLoginObject.getMaProUserId();
		ModelAndView model = null;
		isValidMaProUser = true;
		if(isValidMaProUser){
			List<GridFSDBFile> girdFSDBFileList = maProServiceDelegate.listUserDocumentsInfo(cmsLoginObject.getMaProUserId());
			Map<String,Object> modelMapLanding = new HashMap<String,Object>();
			List<MaProCmsMetaData> cmsDataLandingList = MaProUtility.getMaProUserLandingList(girdFSDBFileList);
			modelMapLanding.put("maProDocumentList", cmsDataLandingList);
			model = new ModelAndView("maProLanding","modelMapLanding",modelMapLanding);
		}else{
			model = new ModelAndView("cmsLoginView");
		}
		 return model;
	 }
	
	@RequestMapping(value="/uploadMaProDocument", method=RequestMethod.POST)
	public String uploadDocument(HttpServletRequest request, HttpServletResponse response){
		String responseResult = "";
		System.out.println("logged in user:::::"+request.getRemotePort());
		try {
			 responseResult = maProServiceDelegate.storeMaProDocument(request,response, username);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "maProLanding";
	}
	
	@RequestMapping(value="/listdocuments",method=RequestMethod.GET)
	public ModelAndView listAllDocuments(HttpServletRequest request, HttpServletResponse response) {
		 ModelAndView model = new ModelAndView("listDocument");
		 CmsLoginObject cmsLoginObject = new CmsLoginObject();
		 model.addObject("cmsLoginObject", cmsLoginObject);
		 return model;

	}
	
	@RequestMapping(value="/listdocument/{documentName}/{documentId}/{userId}",method=RequestMethod.GET)
	public ModelAndView listOneDocument(HttpServletRequest request, HttpServletResponse response) {
		 ModelAndView model = new ModelAndView("listDocument");
		 CmsLoginObject cmsLoginObject = new CmsLoginObject();
		 model.addObject("cmsLoginObject", cmsLoginObject);
		 return model;

	}
	
	@RequestMapping(value="/getDocument/{objectId}",method=RequestMethod.GET)
	public void getOneDocumentContent(HttpServletRequest request, HttpServletResponse response, @PathVariable(value="objectId") String objectId) throws IOException {
		System.out.println(objectId + "xas");
		 GridFSDBFile girdFSDBFileList = maProServiceDelegate.getUserDocumentsInfo(objectId);
//		 GridFSDBFile fileObj= girdFSDBFileList.get(0);
		 String fileNameFromMetaData = (String) girdFSDBFileList.getMetaData().get("maProFileName");
		 String fileContentType = (String) girdFSDBFileList.getMetaData().get("maProFileType");
		 response.setContentType(fileContentType);
		 response.setHeader("Content-Disposition", "attachment; filename="+fileNameFromMetaData);
		 
		 
		  
		 ByteArrayOutputStream baos = new ByteArrayOutputStream();
		 girdFSDBFileList.writeTo(baos);
		
		 OutputStream out= null;
		 try {
			out=response.getOutputStream();
			System.out.println("CODE CHANGED>>>>");
			//girdFSDBFileList.writeTo("D:\\textMaProLatest345.pdf");
			baos.writeTo(out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//out.close();
		}
	}


}
