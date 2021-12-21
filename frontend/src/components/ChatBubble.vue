<template>
  <div class="rounded bg-gray-100 text-xl m-2 w-44">
    <h1>{{ message }}</h1>
  </div>
</template>

<script>
export default {
  props: {
    message: String,
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
