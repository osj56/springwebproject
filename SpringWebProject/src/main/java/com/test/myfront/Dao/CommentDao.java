package com.test.myfront.Dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.myfront.comment.Comment;

@Repository
public class CommentDao {
	@Autowired
	private SqlSession sqlsession;
	
	public void CommentInsert(final Comment comment) {
		sqlsession.insert("comment.CommentWrite", comment);
	}
	
	public List<Comment> getList(final Comment comment, int cnt){
		return sqlsession.selectList("comment.getList", cnt);
	}
	
	public void CommentModify(final Comment comment) {
		
		//HttpSession session = request.getSession();
		//String name =(String)session.getAttribute("login");
		Map<Integer, String> parameters = new HashMap<Integer, String>();
	///	parameters.put(cnt,cnt);
	//	parameters.put("name", name);
		sqlsession.update("comment.Commentmodify",comment);
	}
	
	public void CommentDelete(final Comment comment) {
		sqlsession.delete("comment.CommentDelete", comment);
	}
}
