package io.luxyva.switches.discuss.domain;

import lombok.Data;
import org.bson.types.ObjectId;

import java.util.Date;

@Data
public class Article {

	private ObjectId id;

	private Long userId;

	private String info;

	private Integer status;

	private String url;

	private String summary;

	private Date createDt;

	private Long modifyUid;

	private Date modifyDt;

}
