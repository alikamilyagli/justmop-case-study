<template>
    <div>
        <div class="row col">
            <h1>Calculate Similarity</h1>
        </div>
        <div class="row col">
            <div class="col-12">
            <form>
                <div class="form-row">
                    <div class="col-8">
                        <input v-model="text"
                               type="text"
                               class="form-control">
                    </div>
                    <div class="col-4">
                        <button :disabled="text.length === 0 || isLoading"
                                type="button"
                                class="btn btn-primary"
                                @click="calculateSimilarity()">
                            Calculate
                        </button>
                    </div>
                </div>
            </form>
            </div>
        </div>
        <div v-if="isLoading"
             class="row col">
            <p>Loading...</p>
        </div>
        <div v-else-if="hasError"
             class="row col">
            <div class="alert alert-danger"
                 role="alert">
                {{ error }}
            </div>
        </div>
        <div v-if="!isLoading"
             class="row col">
            Similarity Score: {{ score }}
        </div>
    </div>
</template>

<script>
    import SimilarityResult from "./SimilarityResult";

    export default {
        name: "SimilarityForm",
        components: {
            SimilarityResult
        },
        data() {
            return {
                text: ""
            };
        },
        computed: {
            isLoading() {
                return this.$store.getters["similarity/isLoading"];
            },
            hasError() {
                return this.$store.getters["similarity/hasError"];
            },
            error() {
                return this.$store.getters["similarity/error"];
            },
            score() {
                return this.$store.getters["similarity/score"];
            }
        },
        methods: {
            async calculateSimilarity() {
                const result = await this.$store.dispatch("similarity/calculateSimilarity", this.$data.text);
                if (result !== null) {
                    this.$data.text = "";
                }
            }
        }
    };
</script>