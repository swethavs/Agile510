package com.mapro.model;

public class MaProCmsMetaData {

	private String maProObjectId;
	private String maProUserName;
	private String maProAppRole;
	private String fileName;
	private String maProAppName;
	private String fileType;
	private byte[] fileByteArray;
	
	public byte[] getFileByteArray() {
		return fileByteArray;
	}
	public void setFileByteArray(byte[] fileByteArray) {
		this.fileByteArray = fileByteArray;
	}
	public String getMaProUserName() {
		return maProUserName;
	}
	public void setMaProUserName(String maProUserName) {
		this.maProUserName = maProUserName;
	}
	public String getMaProAppRole() {
		return maProAppRole;
	}
	public void setMaProAppRole(String maProAppRole) {
		this.maProAppRole = maProAppRole;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getMaProAppName() {
		return maProAppName;
	}
	public void setMaProAppName(String maProAppName) {
		this.maProAppName = maProAppName;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getMaProObjectId() {
		return maProObjectId;
	}
	public void setMaProObjectId(String maProObjectId) {
		this.maProObjectId = maProObjectId;
	}
}
