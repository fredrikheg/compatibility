import { useEffect, useCallback, useState, Component } from "react";
import React from "react";
import QuestionService from "../services/question.service";
import Answer from "./Answer";
import { useSelector } from 'react-redux';

const DynamicSide = () => {

    const refresh = useSelector((state) => state.payload);
    const [content, setContent] = useState([]);

    const update = useCallback(async () => {

      var cont = await QuestionService.getAnsweredQuestions();

      setContent(cont);

    }, [content]);

    useEffect(() => {
        update();
    }, [refresh]);

    return (
      <div className="col col-4 ">
        <h5>Answers</h5>
        {!content ? null : (
          content.map((c) => React.createElement(Answer, {data:c,key:c.questionId}))
        )}
        <span>{refresh}</span>
      </div>
    );
};

export default DynamicSide;