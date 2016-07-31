package com.mapro.service.delegate;

import com.mapro.model.MaProCmsDocument;
import com.mapro.model.MaProCmsMetaData;
import com.mapro.persistence.mongo.MaProMongoData;
import com.mongodb.gridfs.GridFSDBFile;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

@Service
public class MaProServiceDelegate {
	
	@Autowired
	private MaProMongoData maProMongoData;

	public boolean validateMaProUser(String maProUserId, String maProPassword){
		/*To DO Validate User Id/Pwd with mongoDB User collections*/
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public String storeMaProDocument(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String responseResult = "";
		try {
			List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
			
			for (FileItem item : items) {
				
				if (item.isFormField()) {
					} else {
					try{
						
				String fullPath = item.getName();
					int index = fullPath.lastIndexOf("\\");
					String fileName = fullPath.substring(index + 1);
					Date date = new Date();
					long timePrefix = date.getTime(); 
					
					String physicalPathName = timePrefix+"_"+fileName.trim();
					
					InputStream content = item.getInputStream();

					byte[] bytesArrayWAR275 = IOUtils.toByteArray(content);
				//	bytesArrayWAR275 =   org.apache.commons.codec.binary.Base64.encodeBase64(bytesArrayWAR275);
				
					MaProCmsMetaData maProCmsMetaData = new MaProCmsMetaData();
					MaProCmsDocument maProCmsDocument = new MaProCmsDocument();
					
					
					maProCmsMetaData.setFileName(fileName);
					maProCmsMetaData.setFileType("test");
					maProCmsMetaData.setMaProAppName("MaPro");
					maProCmsMetaData.setMaProAppRole("maProAdmin");
					maProCmsMetaData.setMaProUserName("joshivi");
					maProCmsMetaData.setFileByteArray(bytesArrayWAR275);
					
					maProCmsDocument.setFileContent(content);
					maProCmsDocument.setMaProCmsMetaData(maProCmsMetaData);
					
					responseResult = maProMongoData.storeMaProDocument(maProCmsDocument);
					
					
					}catch(Exception ex){
						ex.printStackTrace();
						}
				}
			}
		} catch (FileUploadException e) {
			throw new Exception("Parsing file upload failed.", e);
		}
		return responseResult;
	}
	
	public List<GridFSDBFile> listUserDocumentsInfo(String userId){
		List<GridFSDBFile> gridFSDBFileList = maProMongoData.listUserDocumentsInfo(userId);
		return gridFSDBFileList;
	}
	

	public GridFSDBFile getUserDocumentsInfo(String objectId){
		GridFSDBFile gridFSDBFileList = maProMongoData.getUserDocumentsInfo(objectId);
		return gridFSDBFileList;
	}
	public String retrieveMaProDocument(){
		
		return "success";
	}
}
