import { useQuery } from "@tanstack/react-query";
import "./Edit.css";
import axios from "axios";
import { useEffect, useState } from "react";

const Edit = () => {
  const [data, setData] = useState({ id: 0, question: "", answer: "" });
  const fetchData = async () => {
    const response = await axios.get("http://localhost:8080/api/AllQuestions");
    return response.data;
  };

  const query = useQuery({ queryKey: ["All-Questions"], queryFn: fetchData });

  const edit = async () => {
    if (data.id !== 0)
      await axios.put("http://localhost:8080/api/modifyQuestion", data);
    query.refetch();
  };

  const deleteQn = async () => {
    console.log(data);
    if (data.id !== 0)
      await axios.delete(`http://localhost:8080/api/delete/${data.id}`);
    query.refetch();
    window.location.reload();
  };

  const handleSelectChange = (e) => {
    setData(query.data[e.target.value]);
  };

  const handleChange = (e) => {
    setData((prevState) => ({ ...prevState, [e.target.name]: e.target.value }));
  };

  return (
    <div className="edit">
      <select
        name="selectedQuestion"
        defaultValue="SelectQuestion"
        onChange={handleSelectChange}
      >
        <option value="SelectQuestion" disabled>
          Select a Question
        </option>
        {query.isFetched &&
          query.data.map((item, index) => (
            <option key={index} value={index}>
              {item.question}
            </option>
          ))}
      </select>
      <input
        type="text"
        name="question"
        onChange={handleChange}
        value={data.question}
        placeholder="Question"
      />
      <input
        type="text"
        name="answer"
        onChange={handleChange}
        value={data.answer}
        placeholder="Answer"
      />
      <div className="controls">
        <button onClick={deleteQn}>Delete</button>
        <button onClick={edit}>Edit</button>
      </div>
    </div>
  );
};

export default Edit;
