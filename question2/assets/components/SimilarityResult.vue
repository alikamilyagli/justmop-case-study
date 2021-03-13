<template>
    <div class="card w-100 mt-2">
        <div class="card-body">
            {{ text }}
        </div>
    </div>
</template>

<script>
    export default {
        name: "SimilarityResult",
        props: {
            text: {
                type: String,
                required: true
            }
        }
    };
</script>