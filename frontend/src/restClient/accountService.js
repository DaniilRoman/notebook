import { AXIOS } from "../axios";

const ACCOUNT_URL = '/account' 

export default {
    login(user) {
        return AXIOS.post(`${ACCOUNT_URL}/login`, user)
    },

    signUp(user) {
        return AXIOS.post(`${ACCOUNT_URL}/sign_up`, user)
    }
}