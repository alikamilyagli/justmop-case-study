import Vue from "vue";
import Vuex from "vuex";
import SimilarityModule from "./similarity";
import CaseStudyModule from "./caseStudy";

Vue.use(Vuex);

export default new Vuex.Store({
    modules: {
        similarity: SimilarityModule,
        caseStudy: CaseStudyModule,
    }
});