import api from "./api";
import authHeader from "./auth-header";

const getPublicContent = () => {
    return api.get("index");
};

const getUserBoard = () => {
    return api.get("/user/user");
};

const getModeratorBoard = () => {
    return api.get("/user/mod");
};

const getAdminBoard = () => {
    return api.get("/user/admin");
};

export default {
    getPublicContent,
    getUserBoard,
    getModeratorBoard,
    getAdminBoard,
};