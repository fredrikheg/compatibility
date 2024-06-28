import { combineReducers } from "redux";
import auth from "./auth";
import message from "./message";
import refreshAnswers from "./refresh-answers";

export default combineReducers({
  auth,
  message,
  refreshAnswers,
});