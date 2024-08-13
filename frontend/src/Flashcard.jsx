import React, { useState } from "react";
import "./Flashcard.css"; // For styling

const Flashcard = ({ flashcard, onFlip, isFlipped }) => {
  return (
    <div className={`flashcard ${isFlipped ? "flipped" : ""}`} onClick={onFlip}>
      <div className="flashcard-front">{flashcard?.question}</div>
      <div className="flashcard-back">{flashcard?.answer}</div>
    </div>
  );
};

export default Flashcard;
