<template>
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

    <div v-else-if="!hasCleaners"
         class="row col">
        No cleaners!
    </div>

    <div v-else class="row col">
        <table class="table table-striped table-bordered">
            <thead>
            <tr>
                <th>id</th>
                <th>name</th>
                <th>company</th>
                <th>holiday</th>
                <th>workingHourStart</th>
                <th>workingHourEnd</th>
                <th>occupiedTimes</th>
            </tr>
            </thead>
            <tbody>
                <tr v-for="cleaner in cleaners" :key="cleaner.id">
                    <td>{{cleaner.id}}</td>
                    <td>{{cleaner.firstName}} {{cleaner.lastName}}</td>
                    <td>{{cleaner.company.title}}</td>
                    <td>{{cleaner.holiday}}</td>
                    <td>{{cleaner.workingHourStart}}</td>
                    <td>{{cleaner.workingHourEnd}}</td>
                    <td>{{cleaner.occupiedTimes}}</td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script>

    export default {
        name: "Cleaners",
        data() {
            return {
                text: ""
            };
        },
        computed: {
            isLoading() {
                return this.$store.getters["caseStudy/isLoading"];
            },
            hasError() {
                return this.$store.getters["caseStudy/hasError"];
            },
            error() {
                return this.$store.getters["caseStudy/error"];
            },
            hasCleaners() {
                return this.$store.getters["caseStudy/hasCleaners"];
            },
            cleaners() {
                return this.$store.getters["caseStudy/cleaners"];
            }
        },
        created() {
            this.$store.dispatch("caseStudy/findCleaners");
        }
    };
</script>