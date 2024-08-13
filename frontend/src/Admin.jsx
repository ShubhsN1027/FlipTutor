import { useState } from "react"
import "./Admin.css"
import axios from "axios";
import { Link } from "react-router-dom";

const Admin = () => {

    const [data,setData] = useState({question:'',answer:''});

    const handleSubmit = async () => {
       await axios.post('http://localhost:8080/api/createQuestion',data)
       setData({question:'',answer:''});
    }

    const handleChange = (e) =>{
        setData(prevState => ({...prevState, [e.target.name]: e.target.value}))
    }

  return (
    <div className="admin">
        <p>Create Question</p>
        <input type="text" name="question" placeholder="Question" value={data.question} onChange={handleChange}/>
        <input type="text" name="answer" placeholder="Answer" value={data.answer }onChange={handleChange}/>
        <button onClick={handleSubmit}>Submit</button>
        <Link to="/">Back to questions</Link>
    </div>
  )
}
export default Admin