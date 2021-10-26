import axios from "axios";
const baseUrl = "http://localhost:5000";
export default (url = baseUrl)=> {
    return axios.create({
        baseURL: url,
        withCredentials: true
    });
}