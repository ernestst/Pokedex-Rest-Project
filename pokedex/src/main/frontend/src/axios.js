import axios from 'axios'

axios.defaults.baseURL = `http://localhost:8080/`
axios.defaults.headers = {'Content-Type': 'application/json'}

axios.interceptors.request.use(config => {
    return config;
})

export default axios;
