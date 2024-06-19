import { Component } from "react";

export default function BoxDynComp (props) {

  return (
    <div key="dyn-box-{props.data.id}" className="mb-2 p-1 border">
      <strong>{props.data.meta}</strong>
      <p>
        {props.data.content}
      </p>
    </div>
  );
};