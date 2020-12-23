package io.luxyva.switches.storage.service;

import io.luxyva.switches.storage.domain.FileInfo;
import io.luxyva.switches.storage.domain.FileType;
import io.luxyva.switches.storage.mapper.FileInfoMapper;
import io.luxyva.switches.storage.mapper.FileTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileService {

	@Autowired
	private FileInfoMapper fileInfoMapper;

	public void saveFile() {
		FileInfo fileInfo = new FileInfo();
		fileInfo.setId(25L);
		fileInfo.setUserId(12L);
		fileInfo.setFileType(3);
		fileInfo.setName("25");
		fileInfoMapper.insert(fileInfo);

		FileInfo fileInfo1 = new FileInfo();
		fileInfo1.setId(26L);
		fileInfo1.setUserId(11L);
		fileInfo1.setFileType(2);
		fileInfo1.setName("26");
		fileInfoMapper.insert(fileInfo1);

		FileInfo fileInfo2 = new FileInfo();
		fileInfo2.setId(27L);
		fileInfo2.setUserId(13L);
		fileInfo2.setFileType(3);
		fileInfo2.setName("27");
		fileInfoMapper.insert(fileInfo2);

		FileInfo fileInfo4 = new FileInfo();
		fileInfo4.setId(28L);
		fileInfo4.setUserId(14L);
		fileInfo4.setFileType(2);
		fileInfo4.setName("28");
		fileInfoMapper.insert(fileInfo4);
	}

	@Autowired
	private FileTypeMapper fileTypeMapper;
	public void x() {
		FileType t1 = new FileType();
		t1.setId(1L);
		t1.setUserId(1L);
		t1.setDescription("1");

		FileType t2 = new FileType();
		t2.setId(2L);
		t2.setUserId(2L);
		t2.setDescription("2");

		FileType t3 = new FileType();
		t3.setId(3L);
		t3.setUserId(3L);
		t3.setDescription("3");

		FileType t4 = new FileType();
		t4.setId(4L);
		t4.setUserId(4L);
		t4.setDescription("4");
		fileTypeMapper.insert(t1);
		fileTypeMapper.insert(t2);
		fileTypeMapper.insert(t3);
		fileTypeMapper.insert(t4);
	}

}
