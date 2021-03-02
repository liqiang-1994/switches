package io.luxyva.switches.discuss.domain;

import lombok.Data;
import org.bson.types.ObjectId;

import java.util.Date;

@Data
public class Comment {

	private ObjectId id;

	private Long userId;

	/**
	 * 评论主体对象编号
	 */
	private Long topicId;

	/**
	 * 评论主体对象类型，如文章，商品
	 */
	private Integer topicType;

	/**
	 * 上级评论对象编号，顶级评论此项为NULL
	 */
	private ObjectId parentId;

	/**
	 * 状态，0：未审核，1：正常
	 */
	private Integer status;

	/**
	 * 评论内容
	 */
	private String message;

	/**
	 * 赞
	 */
	private Long up;

	/**
	 * 踩
	 */
	private Long down;

	private Date createDt;

	private Long modifyUid;

	private Date modifyDt;
}
