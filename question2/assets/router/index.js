import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../components/Home";
import SimilarityForm from "../components/SimilarityForm";
import Cleaners from "../components/Cleaners";

Vue.use(VueRouter);

export default new VueRouter({
    routes: [
        { path: "/", component: Home },
        { path: "/similarity", component: SimilarityForm },
        { path: "/cleaners", component: Cleaners },
        { path: "*", redirect: "/" }
    ]
});