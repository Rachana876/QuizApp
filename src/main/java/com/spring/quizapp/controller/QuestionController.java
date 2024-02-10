package com.spring.quizapp.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.quizapp.model.Question;
import com.spring.quizapp.service.QuestionService;

@RestController
@RequestMapping("question")
public class QuestionController {
	
	@Autowired
	QuestionService questionservice;

	//GetMapping -> getting data
	//PostMapping -> Add data 
	//DeleteMapping -> Deleting data
	//PutMapping -> Update data
	@GetMapping("allQuestions")
	public ResponseEntity<List<Question>> getAllQuestions() {
		return questionservice.getAllQuestions();
	}
	
	//PathVariable is used to take the path from address bar and assign to category . 
	//If the {category} would be {cat} , then we need to specify which variable we are setting 
	//eg. @PathVariable("cat") String category
	@GetMapping("category/{category}")
	public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category){
		return questionservice.getQuestionsByCategory(category);
		
	}
	
	//@RequestBody because the data is sent in the body part of request
	@PostMapping("add")
	public ResponseEntity<String> addQuestion(@RequestBody Question question) {
		return questionservice.addQuestion(question);
	}
	
	@DeleteMapping("delete")
	public ResponseEntity<String> deleteQuestion(@RequestBody Question question) {
		return questionservice.deleteQuestion(question);
	}
	
	@PutMapping("update")
	public ResponseEntity<String> updateQuestion(@RequestBody Question question) {
		return questionservice.UpdateQuestion(question);
	}
	
}
