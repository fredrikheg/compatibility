import api from "./api";

const click = () => {
    localStorage.setItem("click", localStorage.getItem("click")+" 1");
};

const getCount = () => {
  api.get("/click/1").then((response) => {

    if( response.data ) {
        console.log(response.data.clicks);
        return response.data.clicks;
    } else {
        console.log("else");
        return 0;
    }
  });
}

const ClickService = {
    click,
    getCount,
};

export default ClickService;