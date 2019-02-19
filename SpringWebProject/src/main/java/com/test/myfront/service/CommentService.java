package com.test.myfront.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.myfront.Dao.CommentDao;
import com.test.myfront.comment.Comment;

@Service
public class CommentService {
	
	@Autowired
	CommentDao dao;
	
	public void CommentInsert(Comment comment) {
		dao.CommentInsert(comment);
	}
	
	public List<Comment> getList(Comment comment, int cnt){
		return dao.getList(comment, cnt);
	}
	
	public void CommentModify(Comment comment) {
		
		dao.CommentModify(comment);
	}
	
	public void CommentDelete(Comment comment) {
		dao.CommentDelete(comment);
	}
}
