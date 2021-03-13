import CaseStudy from "../api/caseStudy";

const FETCHING_CLEANERS = "FETCHING_CLEANERS",
    FETCHING_CLEANERS_SUCCESS = "FETCHING_CLEANERS_SUCCESS",
    FETCHING_CLEANERS_ERROR = "FETCHING_CLEANERS_ERROR";

export default {
    namespaced: true,
    state: {
        isLoading: false,
        error: null,
        cleaners: []
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
        hasCleaners(state) {
            return state.cleaners.length > 0;
        },
        cleaners(state) {
            return state.cleaners;
        }
    },
    mutations: {
        [FETCHING_CLEANERS](state) {
            state.isLoading = true;
            state.error = null;
            state.cleaners = [];
        },
        [FETCHING_CLEANERS_SUCCESS](state, response) {
            state.isLoading = false;
            state.error = null;
            state.cleaners = response['content'];
        },
        [FETCHING_CLEANERS_ERROR](state, error) {
            state.isLoading = false;
            state.error = error;
            state.cleaners = [];
        }
    },
    actions: {
        async findCleaners({ commit }) {
            commit(FETCHING_CLEANERS);
            try {
                let response = await CaseStudy.findCleaners();
                commit(FETCHING_CLEANERS_SUCCESS, response.data);
                return response.data;
            } catch (error) {
                commit(FETCHING_CLEANERS_ERROR, error);
                return null;
            }
        }
    }
};