package com.mapro.mongo.utility;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.mapro.model.MaProCmsMetaData;
import com.mongodb.gridfs.GridFSDBFile;

public class MaProUtility {

	public static List<MaProCmsMetaData> getMaProUserLandingList(List<GridFSDBFile> girdFSDBFileList) {
		List<MaProCmsMetaData> maProCmsMetaDataList = new  ArrayList<MaProCmsMetaData>();
	for(GridFSDBFile gridFSDBFile : girdFSDBFileList){
		MaProCmsMetaData maProCmsMetaData = new MaProCmsMetaData();
		
		maProCmsMetaData.setFileName((String) gridFSDBFile.getMetaData().get("maProFileName"));
		ObjectId objId = (ObjectId) gridFSDBFile.getId();
		maProCmsMetaData.setMaProObjectId(objId.toString());
		
		maProCmsMetaDataList.add(maProCmsMetaData);
	}
		return maProCmsMetaDataList;
	}
}
