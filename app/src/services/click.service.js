import api from "./api";

const listClicks = async () => {
    var response = 0;
    try {
      response = await api.get("/click/list");

//      console.log(JSON.stringify(response.data.clicks));

      return response.data.clicks;
    } catch(error) {
      console.log(error);
    }
    return -2;
};

const postClick = async (id) => {
    var response = 0;
    try {
      response = await api.post("/click/click",{id});

//      console.log(JSON.stringify(response.data));

      return response.data.clicks;
    } catch (error) {
        console.log(error);
    }
    return -1;
};

const resetClick = async (id) => {
  var response = 0;
  try {
    response = await api.post("/click/reset", {id});

//    console.log(JSON.stringify(response.data));

    return response.data.clicks;
  } catch(error) {
    console.log(error);
  }
}

const createClick = async (id) => {
  var response = 0;
  try {
    response = await api.put("/click/create");

//    console.log(JSON.stringify(response.data));

    return response.data.clicks;
  } catch(error) {
    console.log(error);
  }
}

const ClickService = {
    listClicks,
    postClick,
    resetClick,
    createClick,
};

export default ClickService;