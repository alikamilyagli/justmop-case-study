import Similarity from "../api/similarity";

const CALCULATING_SIMILARITY = "CALCULATING_SIMILARITY",
    CALCULATING_SIMILARITY_SUCCESS = "CALCULATING_SIMILARITY_SUCCESS",
    CALCULATING_SIMILARITY_ERROR = "CALCULATING_SIMILARITY_ERROR";

export default {
    namespaced: true,
    state: {
        isLoading: false,
        error: null,
        score: null
    },
    getters: {
        isLoading(state) {
            return state.isLoading;
        },
        hasError(state) {
            return state.error !== null;
        },
        error(state) {
            return state.error;
        },
        score(state) {
            return state.score;
        }
    },
    mutations: {
        [CALCULATING_SIMILARITY](state) {
            state.isLoading = true;
            state.error = null;
        },
        [CALCULATING_SIMILARITY_SUCCESS](state, score) {
            state.isLoading = false;
            state.error = null;
            state.score = score['score'];
        },
        [CALCULATING_SIMILARITY_ERROR](state, error) {
            state.isLoading = false;
            state.error = error;
            state.score = null;
        }
    },
    actions: {
        async calculateSimilarity({ commit }, text) {
            commit(CALCULATING_SIMILARITY);
            try {
                let response = await Similarity.calculateSimilarity(text);
                commit(CALCULATING_SIMILARITY_SUCCESS, response.data);
                return response.data;
            } catch (error) {
                commit(CALCULATING_SIMILARITY_ERROR, error);
                return null;
            }
        }
    }
};