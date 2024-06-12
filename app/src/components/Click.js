import React, { useState, useCallback, useEffect } from "react";
import ClickService from "../services/click.service";

const Click = (props) => {

    const [isUpdating, setIsUpdating] = useState(false);
    const [isClicking, setIsClicking] = useState(false);
    const [isResetting, setIsResetting] = useState(false);
    const [clicks, setClicks] = useState(0);

    const update = useCallback(async () => {
      if(isUpdating) return;
      setIsUpdating(true);

      var counter = await ClickService.getCountAss();
      setClicks(counter);

      setIsUpdating(false);
    }, [clicks]);

    const clicker = useCallback(async () => {
      if(isClicking) return;
      setIsClicking(true);

      var counter = await ClickService.postClick(props.data);
      setClicks(counter);

      setIsClicking(false);
    }, [clicks]);

    const reset = useCallback(async () => {
      if(isResetting) return;
      setIsResetting(true);

      var counter = await ClickService.resetClick(props.data);
      setClicks(counter);

      setIsResetting(false);
    }, [clicks]);

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
          My clicker count: {clicks}
        </div>
      </div>
    );
}

export default Click;