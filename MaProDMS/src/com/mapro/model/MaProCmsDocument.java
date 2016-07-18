package com.mapro.model;

import java.io.InputStream;

public class MaProCmsDocument {
   
	private MaProCmsMetaData maProCmsMetaData;
	private InputStream fileContent;
	
	public MaProCmsMetaData getMaProCmsMetaData() {
		return maProCmsMetaData;
	}
	public void setMaProCmsMetaData(MaProCmsMetaData maProCmsMetaData) {
		this.maProCmsMetaData = maProCmsMetaData;
	}
	public InputStream getFileContent() {
		return fileContent;
	}
	public void setFileContent(InputStream fileContent) {
		this.fileContent = fileContent;
	}

}
