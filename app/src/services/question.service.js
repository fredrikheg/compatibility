import api from "./api";

const getCurrentQuestions = async () => {

    var response = [];
    try {
      response = await api.get("/question/current");

//      console.log(response.data.questions);

      return response.data.questions;

    } catch(error) {
      console.log(error);
    }
    return response;
};

const answerQuestion = async (questionId, answer) => {

    var response;

    try {
      response = await api.post("/question/answer",{questionId, answer});
    } catch (error) {
      console.log(error);
      response = "error";
    }
    return response;
}

const QuestionService = {
    getCurrentQuestions,
    answerQuestion
};

export default QuestionService;