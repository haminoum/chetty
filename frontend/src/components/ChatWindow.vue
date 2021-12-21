<template>
  <div class="shadow-lg m-5 w-2/4 mx-auto justify-center">
    <div class="m-2 grid grid-cols-3">
      <Users :users="users" />
      <div class="col-span-2 bg-red-300 p-4">
        <div v-for="msg in messages" :key="msg">
          <ChatBubble :message="msg" />
        </div>
        <div class="flex">
          <input
            class="p-2 w-full rounded-lg"
            type="text"
            name="message"
            id="message"
            placeholder="message"
            v-model="form.message"
          />

          <button
            class="
              button-primary
              hover:bg-green-600
              shadow-md
              text-lg
              font-semibold
            "
            @click="sendMessage()"
          >
            Send
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ChatBubble from "./ChatBubble.vue";
import Users from "./Users.vue";
import { reactive } from "vue";

export default {
  props: {
    messages: { required: true, type: Object },
    users: { required: true, type: Object },
    connected: { required: true, type: Boolean },
    ws: { required: true, type: Object },
  },
  components: {
    ChatBubble,
    Users,
  },
  mounted() {},
  data() {
    var form = reactive({
      message: null,
    });
    const messageDestination = "/app/chat/";
    return {
      form,
      messageDestination,
    };
  },
  methods: {
    sendMessage() {
      let user = "hero";
      let header = {};

      let message = {
        type: "CHAT",
        content: this.form.message ? this.form.message : "Sample Msg",
        sender: user,
      };

      if (this.connected) {
        this.ws.send(
          this.messageDestination + user,
          JSON.stringify(message),
          header
        );

        console.log("MESSAGES", this.messages);
      } else {
        alert("Connection lost...");
      }
    },
  },
};
</script>

<style>
</style>