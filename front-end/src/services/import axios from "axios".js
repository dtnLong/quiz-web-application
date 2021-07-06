import axios from "axios"
axios.get('http://localhost:8080/api/quiz/quiz01')
    .then(result => console.log(result));