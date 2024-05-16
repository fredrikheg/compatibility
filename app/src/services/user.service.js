import api from "./api";
import authHeader from "./auth-header";

const getPublicContent = () => {
    return api.get("index");
};

const getUserBoard = () => {
    return api.get("/test/user");
};

const getModeratorBoard = () => {
    return api.get("/test/mod");
};

const getAdminBoard = () => {
    return api.get("/test/admin");
};

export default {
    getPublicContent,
    getUserBoard,
    getModeratorBoard,
    getAdminBoard,
};