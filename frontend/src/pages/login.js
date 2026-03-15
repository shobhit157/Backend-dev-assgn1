import { useState } from "react";
import API from "../api/api";
import { useNavigate } from "react-router-dom";

function Login(){

const [username,setUsername] = useState("");
const [password,setPassword] = useState("");
const navigate = useNavigate();

const handleLogin = async(e)=>{
e.preventDefault();

const res = await API.post("/api/auth/login",{
username,
password
});

localStorage.setItem("token",res.data.token);

alert("Login successful");

navigate("/dashboard");



}

return(

<div>

<h2>Login</h2>

<form onSubmit={handleLogin}>

<input
placeholder="username"
value={username}
onChange={(e)=>setUsername(e.target.value)}
/>

<input
type="password"
placeholder="password"
value={password}
onChange={(e)=>setPassword(e.target.value)}
/>

<button type="submit">Login</button>

</form>

</div>

)

}

export default Login;