import React, { useEffect, useRef }from "react";
import { useCounter } from "../actions/click";

const Click = () => {
    const { count, handle } = useCounter(0);
    return (
      <div>
        <button id="clickbutton" onClick={handle}>
          Click
        </button>
        <p>
          Count: {count}
        </p>
      </div>
    );
}

export default Click;