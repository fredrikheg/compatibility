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

      var userClicks = await ClickService.listClicks();

      console.log(JSON.stringify(userClicks));

      setClicks(userClicks);

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
        {
          !clicks ? null : (
            clicks.map((click) =>
              <>
              <div className="pb-2 ps-2 border" id={click.clickId}>
                <button className="mt-2" disabled={isClicking} id="click-{click.clickId}" onClick={clicker}>
                  Click
                </button>
                <br/>
                <button className="mt-2" disabled={isResetting} id="resetbutton" onClick={reset}>
                  Reset
                </button>
                <div className="pb-2 ps-2 border">
                  My clicker count: {click.numClicks}
                </div>
              </div>
              </>
            )
          )
        }
      </div>
    );
}

export default Click;