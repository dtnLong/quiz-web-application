import axios from "axios";
const baseUrl = "http://quiz.thelogisticom.org/";
export default (url = baseUrl)=> {
    return axios.create({
        baseURL: url,
        withCredentials: true
    });
}