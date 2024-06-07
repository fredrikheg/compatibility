import React, { useState, useCallback, useEffect } from "react";
import ClickService from "../services/click.service";

const Click = (props) => {

    const [isUpdating, setIsUpdating] = useState(false);
    const [isClicking, setIsClicking] = useState(false);
    const [isResetting, setIsResetting] = useState(false);
    const [click, setClick] = useState(0);

    const update = useCallback(async () => {
      if(isUpdating) return;
      setIsUpdating(true);

      var counter = await ClickService.getCountAss(props.data);
      setClick(counter);

      setIsUpdating(false);
    }, [click]);

    const clicker = useCallback(async () => {
      if(isClicking) return;
      setIsClicking(true);

      var counter = await ClickService.postClick(props.data);
      setClick(counter);

      setIsClicking(false);
    }, [click]);

    const reset = useCallback(async () => {
      if(isResetting) return;
      setIsResetting(true);

      var counter = await ClickService.resetClick(props.data);
      setClick(counter);

      setIsResetting(false);
    }, [click]);

    useEffect(() => {
      update();
    }, []);

    return (
      <div>
        <div className="pb-2 ps-2 border">
            <button className="mt-2" disabled={isClicking} id="clickbutton" onClick={clicker}>
              Click
            </button>
            <br/>
            <button className="mt-2" disabled={isResetting} id="resetbutton" onClick={reset}>
              Reset
            </button>
      </div>
        <div className="pb-2 ps-2 border">
          Count: {click}
        </div>
      </div>
    );
}

export default Click;