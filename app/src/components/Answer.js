import { Component } from "react";

export default function Answer (props) {

    const answerStyle =  {
      background : 'var(--bs-tertiary-bg)',
    };

    return (
        <div className="mt-2 p-2 border" style={answerStyle}>
            <h6>{props.data.text}</h6>
            <span>{props.data.answerText}</span>
        </div>
    );
};