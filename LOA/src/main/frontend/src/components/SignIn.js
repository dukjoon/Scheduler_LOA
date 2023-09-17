import React, { useState } from "react";
import axios from "axios";

const SignIn = () => {
  const [userId, setUserId] = useState("");
  const [password, setPassword] = useState("");

  const handleSignIn = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post("/api/auth/login", {
        userId,
        password,
      });
      alert("로그인 성공");
      const result = response.data.data.split(" ");
      localStorage.setItem("token", result[0]);
      localStorage.setItem("character", result[1]);
      localStorage.setItem("server", result[2]);
    } catch (error) {
      if(error.response.status === 400){
        if(error.response.data.message === "Null Error"){
          alert("데이터 입력 오류");
        }
        else if(error.response.data.message === "Id Error"){
          alert("아이디 오류");
        }
        else if(error.response.data.message === "Password Error"){
          alert("비밀번호 오류");
        }
      }else{
        console.log(error);
      }
    }
  };

  return (
    <div>
      <h2>Sign In</h2>
      <form onSubmit={handleSignIn}>
        <input
          type="text"
          placeholder="User ID"
          value={userId}
          onChange={(e) => setUserId(e.target.value)}
        />
        <input
          type="password"
          placeholder="Password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
        <button type="submit">Sign In</button>
      </form>
    </div>
  );
};

export default SignIn;
