package com.spring.quizapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.quizapp.model.Question;

@Repository
//JpaRepository will handle CRUD operations and takes type of table and primary key type
public interface QuestionDao extends JpaRepository<Question, Integer>{

	//Since category is part of table. JPA understand that we are trying to find data based on category
	List<Question> findByCategory(String category);
	

	//in :category ':' is used to define the value from function argument
	@Query(value = "SELECT * FROM question q where q.category=:category ORDER BY RANDOM() LIMIT :numQ", nativeQuery = true)
	List<Question> findRandomQuestionsByCategory(String category, int numQ);
}
