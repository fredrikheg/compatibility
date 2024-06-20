import { Component } from "react";

export default function TextDynComp (props) {

  return (
    <div key="dyn-text-{props.data.id}" className="mb-2">
      <strong>{props.data.meta}</strong>
      <p>
      {props.data.content}
      </p>
    </div>
  );
};