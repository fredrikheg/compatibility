import api from "./api";

const getCount = (id) => {
  api.get("/click/"+id)
    .then(response => {
        console.log("click response: " + JSON.stringify(response.data));
      return response.data.clicks;
    })
    .catch(error =>  {
      console.log(error);
      return -1;
    });
};

const getCountAss = async (id) => {
    var response = 0;
    try {
      response = await api.get("/click/"+id);
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
    return response.data.clicks;
  } catch(error) {
    console.log(error);
  }
}

const ClickService = {
    getCount,
    getCountAss,
    postClick,
    resetClick,
};

export default ClickService;