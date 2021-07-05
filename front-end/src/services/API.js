import axios from "axios";
const baseUrl = "http://localhost:8080";
export default (url = baseUrl)=> {
    return axios.create({
        baseURL: url,
        withCredentials: true
    });
}