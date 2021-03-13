import axios from "axios";

export default {
    findCleaners() {
        return axios.get("http://localhost:8081/api/cleaner?page=0&size=30&sortBy=id&direction=ASC");
    }
};