package com.qdu.dao;

import java.util.List;

import com.qdu.pojo.FilePackage;

public interface FilePackageDao {
	
	public int insertFile(FilePackage filePackage);
	
	public List<FilePackage> selectFileByCourseId(int courseId);
	
	public List<FilePackage> selectFile(String fileName);

}
