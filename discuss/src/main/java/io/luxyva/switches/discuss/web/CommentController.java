package io.luxyva.switches.discuss.web;

import io.luxyva.switches.discuss.domain.Comment;
import io.luxyva.switches.discuss.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommentController {

	@Autowired
	private CommentService commentService;

	@GetMapping("/comment/insert")
	public void insert() {
		commentService.insert();
	}

	@GetMapping("/comment/find")
	public List<Comment> findAll() {
		return commentService.findAll();
	}
}
