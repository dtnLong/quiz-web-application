import API from "./API";

export default {
    auth(data){
        return API().post('/api/login/', data)
    }
}