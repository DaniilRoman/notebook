import { AXIOS } from "../axios";

const NOTEBOOK_URL = '/notebook'

const authHeader = () => {
    return {
        headers: { Authorization: "Bearer " + localStorage.getItem('userToken') }
    }
}

// id title text
export default {
    getAll() {
        return AXIOS.get(`${NOTEBOOK_URL}/`, authHeader())
    },

    getById(id) {
        return AXIOS.get(`${NOTEBOOK_URL}/${id}`, authHeader())
    },

    save(notebook) {
        return AXIOS.post(`${NOTEBOOK_URL}/`, notebook, authHeader())
    },

    update(notebook) {
        return AXIOS.put(`${NOTEBOOK_URL}/`, notebook, authHeader())
    },

    delete(id) {
        return AXIOS.delete(`${NOTEBOOK_URL}/${id}`, authHeader())
    }
}