import React, { useState, useEffect } from "react";

import UserService from "../services/user.service";
import AuthService from "../services/auth.service";
import QuestionList from "./QuestionList";
import DynamicSide from "./DynamicSide";

const BoardUser = () => {
  const [content, setContent] = useState("");
  const [questionAnswered, setQuestionAnswered] = useState("");

  useEffect(() => {
    UserService.getUserBoard().then(
      (response) => {
        setContent(response.data);
      },
      (error) => {
        const _content =
          (error.response &&
            error.response.data &&
            error.response.data.message) ||
          error.message ||
          error.toString();

        setContent(_content);
      }
    );
  }, []);


  const handleAnsweredQuestion = () => {
    var qNum = questionAnswered+1;
    console.log("Question answered " + questionAnswered);
  };

  return (
    <div className="container">
      <header className="jumbotron">
        <h3>{content}</h3>
      </header>
      <div className="row">
        <QuestionList handleAnsweredQuestion={handleAnsweredQuestion}/>
        <DynamicSide questionAnswered={questionAnswered}/>
      </div>
    </div>
  );
};

export default BoardUser;