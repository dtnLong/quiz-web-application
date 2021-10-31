import axios from "axios";
const baseUrl = "http://thelogisticomquizapp.ap-southeast-1.elasticbeanstalk.com";
export default (url = baseUrl)=> {
    return axios.create({
        baseURL: url,
        withCredentials: true
    });
}