import { REFRESH_ANSWERS } from "./types";

export const refreshAnswers = (answerId) => {
  return { type: REFRESH_ANSWERS, payload: {answerId}};
};