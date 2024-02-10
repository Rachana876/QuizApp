package com.spring.quizapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.spring.quizapp.dao.QuestionDao;
import com.spring.quizapp.model.Question;

@Service
public class QuestionService {
	
	@Autowired
	QuestionDao questiondao;

	public ResponseEntity<List<Question>> getAllQuestions() {
		// TODO Auto-generated method stub
		try {
		    return new ResponseEntity<>(questiondao.findAll(),HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(questiondao.findAll(),HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
		try {
		    return new ResponseEntity<>(questiondao.findByCategory(category),HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(questiondao.findByCategory(category),HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<String> addQuestion(Question question) {
		
		questiondao.save(question);
		try {
		    return new ResponseEntity<>("success",HttpStatus.CREATED);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("success",HttpStatus.BAD_REQUEST);
		
	}

	public ResponseEntity<String> deleteQuestion(Question question) {
		// TODO Auto-generated method stub
		questiondao.delete(question);
		try {
		    return new ResponseEntity<>("row deleted",HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("row deleted",HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<String> UpdateQuestion(Question question) {
		// TODO Auto-generated method stub
		questiondao.save(question);
		try {
		    return new ResponseEntity<>("row updated",HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("row updated",HttpStatus.BAD_REQUEST);
	}

	

}
