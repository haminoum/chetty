<template>
  <div>
    <h1 class="text-5xl font-bold underline m-2">Chetty!</h1>
    <div class="flex relative justify-center mx-auto items-center">
      <div v-if="connected" class="text-green-600">Connection: ONLINE</div>
      <div v-else class="text-red-500">
        <span class="animate-bounce inline-flex bg-sky-400 opacity-85"
          >Connection: OFFLINE</span
        >
      </div>
      <button v-if="!connected" class="button-success" @click="connect">
        Reconnect
      </button>
    </div>
    <ChatWindow
      :connected="connected"
      :users="users"
      :ws="ws"
      :messages="messages"
    />
  </div>
</template>

<script>
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";
import ChatWindow from "./components/ChatWindow.vue";
import { ref } from "vue";

export default {
  name: "App",
  data() {
    //TODO externalize to config file or env
    const url = "http://127.0.0.1:8081/ws";
    const httpUrl = "http://127.0.0.1:8081";
    const messageTopic = "/topic/messages";

    var connected = ref(false);
    var socket = null;
    var ws = ref(null);
    var messages = [
      "Hello?",
      "Are you available?",
      "I'm i alone in the chat?!",
    ];
    var users = ["HR Manager", "Product Owner", "Tech Lead"];
    return {
      socket,
      users,
      ws,
      messageTopic,
      messages,
      url,
      httpUrl,
      connected,
    };
  },
  methods: {
    joinChat() {
      //TODO set msgType to JOIN and push user to users
    },
    leaveChat() {
      //TODO set msgType to LEAVE and remove user to users
    },
    connect() {
      this.socket = new SockJS(this.url);
      this.ws = Stomp.over(this.socket);
      this.ws.connect(
        {},
        (frame) => {
          this.connected = true;
          console.log("Successfully connected to:", frame);
          this.ws.subscribe(this.messageTopic, (res) => {
            let data = JSON.parse(res.body);
            console.log("Data:", data);
            this.messages.push(data.content);
          });
        },
        (error) => {
          console.log(error);
          this.connected = false;
        }
      );
    },
    disconnect() {
      if (this.ws) {
        this.ws.disconnect();
      }
      this.connected = false;
    },
  },
  created() {
    this.connect();
  },
  components: {
    ChatWindow,
  },
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

.button-primary {
  @apply bg-blue-500 text-white rounded-full p-2 m-2 shadow-md text-lg font-semibold;
}
.button-secondary {
  @apply bg-yellow-400 text-white rounded-full p-2 m-2 shadow-md text-lg font-semibold;
}
.button-success {
  @apply bg-green-400 text-white rounded-full p-2 m-2 shadow-md text-lg font-semibold;
}
.button-danger {
  @apply bg-red-400 text-white rounded-full p-2 m-2 shadow-md text-lg font-semibold;
}
</style>
