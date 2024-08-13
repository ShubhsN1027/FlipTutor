package com.flashcard.FlashCard_Backend.Service;

import java.util.List;
import java.util.Random;

import com.flashcard.FlashCard_Backend.Dto.QuestionDto;
import com.flashcard.FlashCard_Backend.Entity.Questions;
import com.flashcard.FlashCard_Backend.Repository.QuestionsRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@org.springframework.stereotype.Service
public class Service {
    private QuestionsRepository questionsRepository;

    public Questions createQuestion(QuestionDto questionDto) {
        Questions questions = new Questions();
        questions.setAnswer(questionDto.getAnswer());
        questions.setQuestion(questionDto.getQuestion());
        questions.setLikes(0l);
        questions.setDislikes(0l);
        return questionsRepository.save(questions);
    }

    public Questions getQuestions() {
        List<Questions> questionsList = questionsRepository.findAll();
        if (questionsList.size() == 0) {
            Questions questions = new Questions();
            questions.setQuestion("No Questions");
            questions.setAnswer("Create new Question");
            questions.setId(0l);
            return questions;
        }
        Random rand = new Random();
        int randInt = rand.nextInt(questionsList.size());
        System.out.println(randInt);
        Questions questions = questionsList.get(randInt);
        return questions;
    }

    public Questions setLike(Long id) {
        Questions questions = questionsRepository.findById(id).get();
        questions.setLikes(questions.getLikes() + 1);
        questions = questionsRepository.save(questions);
        return questions;
    }

    public Questions setDislike(Long id) {
        Questions questions = questionsRepository.findById(id).get();
        questions.setDislikes(questions.getDislikes() + 1);
        questions = questionsRepository.save(questions);
        return questions;
    }

    public List<Questions> getAllQuestions() {
        return questionsRepository.findAll();
    }

    public Boolean modifyQuestion(Questions questions) {
        questionsRepository.save(questions);
        return true;
    }

    public Boolean deleteQn(Long id) {
        Questions questions = questionsRepository.findById(id).get();
        questionsRepository.delete(questions);
        return true;
    }
}
