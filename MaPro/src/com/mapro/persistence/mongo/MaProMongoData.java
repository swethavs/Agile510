package com.mapro.persistence.mongo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import com.mapro.model.MaProCmsDocument;
import com.mapro.model.MaProCmsMetaData;
import com.mapro.mongo.utility.MongDBConnectionInstance;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.MongoException;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

@Component
public class MaProMongoData {
	
	public String storeMaProDocument(MaProCmsDocument maProCmsDocument)
	{
	   try {
			DB db=MongDBConnectionInstance.getMongoConnectionInstance();
			GridFS gfsFile=new GridFS(db,"maProFiles");
			GridFSInputFile firstInputFile=gfsFile.createFile(maProCmsDocument.getFileContent());
			firstInputFile.setFilename(maProCmsDocument.getMaProCmsMetaData().getFileName());
			DBObject maProFileMetaData = new BasicDBObject() ;
			
			maProFileMetaData.put("maProFileName", maProCmsDocument.getMaProCmsMetaData().getFileName());
			maProFileMetaData.put("maProFileType", maProCmsDocument.getMaProCmsMetaData().getFileType());
			maProFileMetaData.put("maProAppName", maProCmsDocument.getMaProCmsMetaData().getMaProAppName());
			maProFileMetaData.put("maProAppRole", maProCmsDocument.getMaProCmsMetaData().getMaProAppRole());
			maProFileMetaData.put("maProUserName", maProCmsDocument.getMaProCmsMetaData().getMaProUserName());
			
			firstInputFile.setMetaData(maProFileMetaData);
			
			firstInputFile.save();
			}
	   	catch (MongoException e) {
		 	e.printStackTrace();
		}
	return "success";
	}
	
	
	public List<GridFSDBFile> listUserDocumentsInfo(String userId){
		List<GridFSDBFile> gridFSDBFileList = new ArrayList<GridFSDBFile>();
		DB db=MongDBConnectionInstance.getMongoConnectionInstance();
		GridFS gfsFile=new GridFS(db,"maProFiles");
		BasicDBObject query = new BasicDBObject("metadata.maProUserName", userId);
		gridFSDBFileList= gfsFile.find(query);
		return gridFSDBFileList;
	}


}
