import React, { useState, useCallback, useEffect } from "react";
import QuestionService from "../services/question.service";
import Question from "./Question";

const QuestionList = (props) => {

  const [questions, setQuestions] = useState([]);

  const update = useCallback(async () => {
    var curQuests = await QuestionService.getCurrentQuestions();

    setQuestions(curQuests);
  }, [questions]);

  useEffect(() => {
    update();
  }, []);

  return (
    <div className="col col-8">
      <div className="list-group list-group-flush">
        {!questions ? null : (
          questions.map(question => React.createElement(Question, {data:question,key:question.id,handleAnswer:props.handleAnsweredQuestion}))
        )}
      </div>
    </div>
  );
}

export default QuestionList;