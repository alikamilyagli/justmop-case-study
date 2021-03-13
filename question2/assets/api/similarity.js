import axios from "axios";

export default {
    calculateSimilarity(text) {
        return axios.post("http://localhost:8082/api/similarity", {
            text: text
        });
    }
};