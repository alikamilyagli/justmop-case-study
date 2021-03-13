<template>

    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div id="navbarNav">
            <ul class="navbar-nav">
                <router-link class="nav-item"
                             v-for="routes in links"
                             v-bind:key="routes.id"
                             :to="`${routes.page}`">
                    <a class="nav-link">{{routes.text}}</a>
                </router-link>
            </ul>
        </div>
    </nav>

</template>

<script>
    export default {
        name: 'Navigation',
        data() {
            return {
                links: [
                    {
                        id: 0,
                        text: 'Home',
                        page:'/'
                    },
                    {
                        id: 1,
                        text: 'Similarity',
                        page:'/similarity'
                    },
                    {
                        id: 2,
                        text: 'Cleaners',
                        page:'/cleaners'
                    }
                ]
            }
        }
    }
</script>
<style>
    .spacing {
        margin-right: 10px;
    }
</style>