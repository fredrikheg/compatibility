
const getLocalRefreshToken = () => {
  const user = JSON.parse(localStorage.getItem("user"));
  return user?.refreshToken;
};

const getLocalAccessToken = () => {
  const user = JSON.parse(localStorage.getItem("user"));
  return user?.token;
};

const updateLocalAccessToken = (newToken) => {
  let user = JSON.parse(localStorage.getItem("user"));
  user.token = newToken;
  localStorage.setItem("user", JSON.stringify(user));
};

const getUser = () => {
  return JSON.pars(localStorage.getItem("user"));
}

const setUser = (user) => {
  console.log(JSON.stringify(user));
  localStorage.setItem("user", JSON.stringify(user));
}

const removeUser = () => {
  localStorage.removeItem("user");
}

const TokenService = {
  getLocalRefreshToken,
  getLocalAccessToken,
  updateLocalAccessToken,
  getUser,
  setUser,
  removeUser,
};

export default TokenService;
