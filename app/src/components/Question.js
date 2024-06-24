import {useState} from "react";
import QuestionService from "../services/question.service";

const Question = (props) => {

    const [radioSelected, setRadioSelected] = useState(0);

    var questionID = props.data.id;

    const answerQuestion = async (e) => {
      e.preventDefault();
      QuestionService.answerQuestion(questionID, radioSelected);
      //TODO: Inactivate button and change text
    };

    const selectRadio = (r) => {
      setRadioSelected(r);
    }

    return (
      <form className="list-group-item flex-column align-items-start" key="{questionID}" onSubmit={answerQuestion}>
        <h5 className="mb-1">{props.data.questionText}</h5>
        <p>{props.data.questionBody}</p>
        <div className="input-group mb-3 input-group-text">
          <div className="form-check form-control-sm">
            <input type="radio" name="options" id="option1" onChange={selectRadio.bind(this,1)}/>
            <label className="mt-0" htmlFor="option1">Never</label>
          </div>
          <div className="form-check form-control-sm">
            <input type="radio" name="options" id="option2" onChange={selectRadio.bind(this,2)}/>
            <label className="mt-0" htmlFor="option2">Nah</label>
          </div>
          <div className="form-check form-control-sm">
            <input type="radio" name="options" id="option3" onChange={selectRadio.bind(this,3)}/>
            <label className="mt-0" htmlFor="option3">Indiff</label>
          </div>
          <div className="form-check form-control-sm">
            <input type="radio" name="options" id="option4" onChange={selectRadio.bind(this,4)}/>
            <label className="mt-0" htmlFor="option4">Yeah</label>
          </div>
          <div className="form-check form-control-sm">
            <input type="radio" name="options" id="option5" onChange={selectRadio.bind(this,5)}/>
            <label className="mt-0" htmlFor="option5">Always</label>
          </div>
          <div className="input-group-append">
            <button className="btn py-3 ms-2" id="answer-{props.data.id}" type="submit">Send</button>
          </div>
        </div>
      </form>
    );

}

export default Question;