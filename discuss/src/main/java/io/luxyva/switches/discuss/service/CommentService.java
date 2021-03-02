package io.luxyva.switches.discuss.service;

import io.luxyva.switches.discuss.domain.Comment;
import io.luxyva.switches.discuss.repository.CommentRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class CommentService {

	@Resource
	private CommentRepository commentRepository;

	public void insert() {
		Comment comment = new Comment();
		comment.setMessage("12345");
		//comment.setTopicId(new ObjectId());
		comment.setTopicType(34);
		comment.setCreateDt(new Date());
		commentRepository.insertOne(comment);
	}

	public List<Comment> findAll() {
		return commentRepository.findAll();
	}

}
