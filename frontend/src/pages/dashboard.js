import { useEffect, useState } from "react";
import API from "../api/api";
import TaskForm from "../components/TaskForm";

function Dashboard(){

const [tasks,setTasks] = useState([]);

const fetchTasks = async()=>{
try{
const res = await API.get("/api/v1/tasks/getAll");
setTasks(res.data);
}catch(error){
console.error("Error fetching tasks", error);
}
}

useEffect(()=>{
fetchTasks();
},[]);

const addTask = async(task)=>{
try{
await API.post("/api/v1/tasks/create", task);
fetchTasks();
}catch(error){
console.error("Error adding task", error);
}
}



return(

<div>

<h2>Task Dashboard</h2>

<TaskForm addTask={addTask}/>


</div>

)

}

export default Dashboard;