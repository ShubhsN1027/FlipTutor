package com.flashcard.FlashCard_Backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flashcard.FlashCard_Backend.Entity.Questions;

@Repository
public interface QuestionsRepository extends JpaRepository<Questions, Long> {

}
