import API from "./API";

export default {
    getQuiz(quizCode){
        return API().get(`/api/quiz/${quizCode}`)
    },
    
    submitQuiz(payload){
        return API().post('/api/quiz/answer', payload);
    }
}