import { useEffect, useCallback, useState, Component } from "react";
import React from "react";
import DynamicService from "../services/dynamic.service";

import BoxDynComp from "./dynamic/BoxDynComp";
import TextDynComp from "./dynamic/TextDynComp";

const DynamicComponents = {
  box: BoxDynComp,
  text: TextDynComp,
};

const DynamicSide = () => {

    const [content, setContent] = useState([]);

    const update = useCallback(async () => {

      var cont = await DynamicService.getContent();

      setContent(cont);

    }, [content]);

    useEffect(() => {
        update();
    }, []);

    return (
      <div className="col col-4 border">
        {!content ? null : (
          content.map((c) => React.createElement(DynamicComponents[c.type], {data:c}))
        )}
      </div>
    );
};

export default DynamicSide;