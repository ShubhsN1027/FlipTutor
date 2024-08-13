package com.flashcard.FlashCard_Backend.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flashcard.FlashCard_Backend.Dto.QuestionDto;
import com.flashcard.FlashCard_Backend.Entity.Questions;
import com.flashcard.FlashCard_Backend.Service.Service;

import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class Controller {
    private Service service;

    @PostMapping("/createQuestion")
    public ResponseEntity<Boolean> postMethodName(@RequestBody QuestionDto questionDto) {
        service.createQuestion(questionDto);
        return ResponseEntity.ok(true);
    }

    @GetMapping("/getQuestions")
    public ResponseEntity<Questions> getQuestion() {
        return ResponseEntity.ok(service.getQuestions());
    }

    @PutMapping("setLikes/{id}")
    public ResponseEntity<Boolean> setLike(@PathVariable Long id) {
        service.setLike(id);
        return ResponseEntity.ok(true);
    }

    @PutMapping("setDislikes/{id}")
    public ResponseEntity<Boolean> setDislike(@PathVariable Long id) {
        service.setDislike(id);
        return ResponseEntity.ok(true);
    }

    @GetMapping("/AllQuestions")
    public ResponseEntity<List<Questions>> getAllQuestions() {
        return ResponseEntity.ok(service.getAllQuestions());
    }

    @PutMapping("/modifyQuestion")
    public ResponseEntity<Boolean> modifyQuestion(@RequestBody Questions questions) {
        service.modifyQuestion(questions);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteQuestion(@PathParam("id") Long id) {
        service.deleteQn(id);
    }
}
