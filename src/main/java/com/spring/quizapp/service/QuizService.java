package com.spring.quizapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.spring.quizapp.dao.QuestionDao;
import com.spring.quizapp.dao.QuizDao;
import com.spring.quizapp.model.Question;
import com.spring.quizapp.model.QuestionWrapper;
import com.spring.quizapp.model.Quiz;
import com.spring.quizapp.model.Response;

@Service
public class QuizService {
	//Quiz and Question have many to many mapping
	@Autowired
	QuizDao quizDao;
	
	@Autowired
	QuestionDao questionDao;
	
	

	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
		// TODO Auto-generated method stub
		List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numQ);
		Quiz quiz = new Quiz() ;
		quiz.setTitle(title);
		quiz.setQuestions(questions);
		quizDao.save(quiz);
		return new ResponseEntity<>("Success",HttpStatus.OK);
	}



	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
		// Optional is used as we are not sure data might come or not as there is a possibility that id is not present
		Optional<Quiz> quiz = quizDao.findById(id);
		//when we use Optional we have to first get() the object
		List<Question> questionsFromDB = quiz.get().getQuestions();
		List<QuestionWrapper> questionForUser = new ArrayList<>();
		for (Question q : questionsFromDB) {
			QuestionWrapper qa = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
			questionForUser.add(qa);
		}
		return new ResponseEntity<>(questionForUser,HttpStatus.OK);
	}



	public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
		//findById returns Optional so using .get() we get the data
		Quiz quiz = quizDao.findById(id).get();
		List<Question> questions = quiz.getQuestions();
		int right =0;
		int i=0;
		for(Response response : responses) {
			if(response.getResponse().equals(questions.get(i).getRightanswer())){
				right++;
			}
		i++;
		}
		return new ResponseEntity<>(right,HttpStatus.OK);
	}
	
	

}
