import api from "./api";

const getCount = () => {
  api.get("/click/1")
    .then(response => {
        console.log("click response: " + JSON.stringify(response.data));
      return response.data.clicks;
    })
    .catch(error =>  {
      console.log(error);
      return -1;
    });
};

const getCountAss = async () => {
    var response = 0;
    try {
      response = await api.get("/click/1");
    } catch(error) {
      console.log(error);
    }
    return response.data.clicks;
};

const ClickService = {
    getCount,
    getCountAss,
};

export default ClickService;