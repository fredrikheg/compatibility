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

      setClicks(userClicks);

      setIsUpdating(false);
    }, [clicks]);

    const clicker = useCallback(async (clickId) => {
      if(isClicking) return;
      setIsClicking(true);

      var counter = await ClickService.postClick(clickId);
      setClicks(counter);

      setIsClicking(false);
    }, [clicks]);

    const reset = useCallback(async (clickId) => {
      if(isResetting) return;
      setIsResetting(true);

      var counter = await ClickService.resetClick(clickId);
      setClicks(counter);

      setIsResetting(false);
    }, [clicks]);

    useEffect(() => {
      update();
    }, []);

    return (
      <div className="col col-8">
        {!clicks ? null : (
            clicks.map((click) =>
              <div className="mb-2 pb-1 ps-2 border" key={click.clickId}>
                <button className="mt-2" disabled={isClicking} key="click-{click.clickId}" onClick={clicker.bind(this,click.clickId)}>
                  Click
                </button>
                <br/>
                <button className="mt-2" disabled={isResetting} key="resetbutton-{click.clickId}" onClick={reset.bind(this,click.clickId)}>
                  Reset
                </button>
                <div className="mt-1">
                  This clicker count: {click.numClicks}
                </div>
              </div>
            )
        )}
      </div>
    );
}

export default Click;