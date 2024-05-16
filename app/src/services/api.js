import axios from "axios";

const instance = axios.create({
  baseURL: "http://localhost:8081/api",
  headers: {
    "Content_Type": "application/json",
  },
});

export default instance;