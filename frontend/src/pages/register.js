import { useState } from "react";
import API from "../api/api";

function Register(){

const [username,setUsername] = useState("");
const [email,setEmail] = useState("");
const [password,setPassword] = useState("");

const handleRegister = async(e)=>{
e.preventDefault();

await API.post("/api/auth/register",{
username,
email,
password
});

alert("User Registered");

}

return(

<div>

<h2>Register</h2>

<form onSubmit={handleRegister}>

<input
placeholder="username"
value={username}
onChange={(e)=>setUsername(e.target.value)}
/>

<br></br>

<input
type="email"
placeholder="email"
value={email}
onChange={(e)=>setEmail(e.target.value)}
/>

<br></br>

<input
type="password"
placeholder="password"
value={password}
onChange={(e)=>setPassword(e.target.value)}
/>

<button type="submit">Register</button>

</form>

</div>

)

}

export default Register;