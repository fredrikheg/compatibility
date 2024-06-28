import { useState, useCallback } from "react";
import QuestionService from "../services/question.service";
import { useDispatch } from "react-redux";
import { refreshAnswers } from "../actions/refresh-answers";

const Question = (props) => {

    const [show, setShow] = useState(true);
    var radioSelected = -1;
    var questionID = props.data.id;
    const dispatch = useDispatch();

    const hideElement = useCallback(() => {

      if(radioSelected != -1) {
        setShow(false);
      }
    }, [show]);

    const answerQuestion = async (e) => {

      if(radioSelected == -1 ) return;
      e.preventDefault();

      QuestionService.answerQuestion(questionID, radioSelected);
      hideElement();
      dispatch(refreshAnswers(questionID));
    };

    function selectRadio(r) {
      radioSelected = r;
    };

    const mapStateToProps = ( state ) => ( {
      value: state
    } );

    if(show) {
    return (
      <form className="list-group-item flex-column align-items-start" onSubmit={answerQuestion}>
        <h5 className="mb-1">{props.data.questionText}</h5>
        <p>{props.data.questionBody}</p>
        <div className="mb-3 input-group-text">
          <div className="input-group">
            <div className="form-check form-control-sm">
              <input type="radio" name="options" id="option1" onClick={selectRadio.bind(this,1)}/>
              <label className="mt-0" htmlFor="option1">Never</label>
            </div>
            <div className="form-check form-control-sm">
              <input type="radio" name="options" id="option2" onClick={selectRadio.bind(this,2)}/>
              <label className="mt-0" htmlFor="option2">Nah</label>
            </div>
            <div className="form-check form-control-sm">
              <input type="radio" name="options" id="option3" onClick={selectRadio.bind(this,3)}/>
              <label className="mt-0" htmlFor="option3">Indiff</label>
            </div>
            <div className="form-check form-control-sm">
              <input type="radio" name="options" id="option4" onClick={selectRadio.bind(this,4)}/>
              <label className="mt-0" htmlFor="option4">Yeah</label>
            </div>
            <div className="form-check form-control-sm">
              <input type="radio" name="options" id="option5" onClick={selectRadio.bind(this,5)}/>
              <label className="mt-0" htmlFor="option5">Always</label>
            </div>
          </div>
          <div className="input-group-append">
            <button className="btn py-3 ms-2" id="answer-{props.data.id}" type="submit">Send</button>
          </div>
        </div>
      </form>)
      } else {
        return (<></>);
      }
}

export default Question;