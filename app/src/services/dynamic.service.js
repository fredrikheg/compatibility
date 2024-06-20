import api from "./api";

const getContent = async () => {
    var response = {};
    try {

      response = await api.get("/test/dynamic");
      return response.data.content;

    } catch(error) {
      console.log(error);
    }
    return response;
};

const DynamicService = {
    getContent,
};

export default DynamicService;