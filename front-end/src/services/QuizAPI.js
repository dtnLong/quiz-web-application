import API from "./API";
import {data} from "./Add.js";

export default {
    getQuiz(quizCode){
        return API().get(`/api/quiz/${quizCode}`)
    },
    getLocalQuiz(){
        return data;
    }
    ,
    submitQuiz(payload){
        return API().post('/api/quiz/answer', payload);
    }
}