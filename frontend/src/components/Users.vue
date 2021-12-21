<template>
  <div class="col-span-1">
    <h2 class="font-bold text-white text-2xl p-2">Users</h2>
    <div v-for="user in users" :key="user">
      <div
        class="
          flex
          justify-evenly
          bg-gray-50
          rounded-full
          cursor-pointer
          m-2
          p-2
          hover:bg-red-400 hover:text-white
        "
      >
        <div class="font-semibold">
          {{ user }}
        </div>
        <span class="font-bold invisible hover:text-red-500">X</span>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    users: { type: Object, required: true },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
