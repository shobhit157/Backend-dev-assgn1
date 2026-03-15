import { useState } from "react";

function TaskForm({ addTask }) {

const [title,setTitle] = useState("");
const [description,setDescription] = useState("");
const [status,setStatus] = useState("");

const handleSubmit = (e)=>{
e.preventDefault();

addTask({
        title,
        description,
        status
    });


setTitle("");
setDescription("");
setStatus("");
}

return(

<form onSubmit={handleSubmit}>

<input
placeholder="Enter task"
value={title}
onChange={(e)=>setTitle(e.target.value)}
/>

<input
placeholder="Enter description"
value={description}
onChange={(e)=>setDescription(e.target.value)}
/>

<input
placeholder="Enter status"
value={status}
onChange={(e)=>setStatus(e.target.value)}
/>

<button type="submit">Add Task</button>

</form>

)

}

export default TaskForm;