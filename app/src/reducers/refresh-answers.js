import { REFRESH_ANSWERS } from "../actions/types";
import { refreshAnswers } from '../actions/refresh-answers';

const initialState = {};

export default function (state = initialState, action) {

  const { type, payload } = action;

  switch (action.type) {
    case REFRESH_ANSWERS : return {...state, payload};
    default : return state;
  }
};