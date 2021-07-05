import API from "./API";

export default {
    getQuiz(quizCode){
        return API().get(`/api/quiz/${quizCode}`)
    }
}