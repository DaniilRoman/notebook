import axios from 'axios'

export const AXIOS = axios.create({
  baseURL: `http://localhost:9090/api/v1`,
  })