import React, { useEffect, useState } from "react";
import Flashcard from "./Flashcard";
import "./App.css";
import { useQuery } from "@tanstack/react-query";
import axios from "axios";
import { Link } from "react-router-dom";

const App = () => {
  const [isFlipped, setIsFlipped] = useState(false);
  const [question, setQuestion] = useState();

  const fetchData = async () => {
    const response = await axios.get("http://localhost:8080/api/getQuestions");
    return response.data;
  };

  const query = useQuery({
    queryKey: ["fetch-qn"],
    queryFn: fetchData,
  });

  useEffect(() => {
    console.log(query.data);
  }, [query]);

  const handleLike = async (id) => {
    if (id !== 0) {
      await axios.put(`http://localhost:8080/api/setLikes/${id}`);
      query.refetch();
    }
  };

  const handleDislike = async (id) => {
    if (id !== 0) {
      await axios.put(`http://localhost:8080/api/setDislikes/${id}`);
      query.refetch();
    }
  };

  const handleFlip = () => {
    setIsFlipped(!isFlipped);
  };

  return (
    <div className="app">
      <Flashcard
        flashcard={query.data}
        onFlip={handleFlip}
        isFlipped={isFlipped}
      />
      <div className="controls">
        <button
          onClick={() => {
            handleDislike(query.data?.id);
          }}
        >{`Dislike ${query.data?.dislikes || ""}`}</button>
        <button
          onClick={() => {
            handleLike(query.data?.id);
          }}
        >{`Like ${query.data?.likes || ""}`}</button>
      </div>
      <Link to="/create">Create questions</Link>
    </div>
  );
};

export default App;
