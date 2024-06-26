import { useEffect, useCallback, useState, Component } from "react";
import React from "react";
import QuestionService from "../services/question.service";
import Answer from "./Answer";

const DynamicSide = () => {

    const [content, setContent] = useState([]);

    const update = useCallback(async () => {

      var cont = await QuestionService.getAnsweredQuestions();

      setContent(cont);

    }, [content]);

    useEffect(() => {
        update();
    }, []);

    return (
      <div className="col col-4 ">
        <h5>Answers</h5>
        {!content ? null : (
          content.map((c) => React.createElement(Answer, {data:c,key:c.id}))
        )}
      </div>
    );
};

export default DynamicSide;