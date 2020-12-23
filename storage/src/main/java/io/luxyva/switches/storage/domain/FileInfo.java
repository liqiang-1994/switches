package io.luxyva.switches.storage.domain;

import lombok.Data;

import java.util.Date;

@Data
public class FileInfo {

	private Long id;

	private String name;

	private Long userId;

	private String sourceName;

	private Integer fileType;

	private Integer bizType;

	private Integer checkStatus;

	private String location;

	private String fileSuffix;

	private Date createDt;

	private Long modifyUid;

	private Date modifyDt;
}
