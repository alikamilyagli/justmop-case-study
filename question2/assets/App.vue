<template>
    <div class="container">
        <Navigation></Navigation>
        <router-view />
    </div>
</template>

<script>
    import Navigation from './components/Navigation'
    export default {
        name: "App",
        components: {
            'Navigation': Navigation
        }
    }
</script>