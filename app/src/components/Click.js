import React, { useState, useCallback } from "react";
import ClickService from "../services/click.service";

const Click = () => {
    const [isSending, setIsSending] = useState(false);
    const [click, setClick] = useState(0);

    const clicker = useCallback(async () => {
      if(isSending) return;
      setIsSending(true);

      var counter = await ClickService.getCountAss();
      console.log("counter: " + counter);
      await setClick(counter);

      setIsSending(false);
    },[click]);

    return (
      <div>
        <button disabled={isSending} id="clickbutton" onClick={clicker}>
          Click
        </button>
        <p>
          Count: {click}
        </p>
      </div>
    );
}

export default Click;