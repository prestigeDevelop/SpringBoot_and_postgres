<template>
  <div class="user-profile">
    <notification ref="notification" message="" type="success" />
    <div class="user-profile__user-panel">
      <h1 class="user-profile__username">@{{ user.username }}</h1>
      <div class="user-profile__admin-badge" v-if="this.user.isAdmin">
        Admin
      </div>
      <div class="user-profile__follower-count">
        <strong>Followers: </strong> {{ followers }}
      </div>
      <form @submit.prevent="createNewTwoot" class="user-profile__create-twoot">
        <label for="newToot">New Twoot ({{ newTwootCharacterCount }}/50)</label>
        <textarea
          id="newTwoot"
          rows="4"
          v-model="newTwootContent"
          :class="{ exceeded: newTwootCharacterCount > 50 }"
        ></textarea>
        <div class="user-profile__create-twoot-type">
          <label for="newTwootType">Type</label>
          <select id="newTwootType" v-model="contentType">
            <option
              :value="option.value"
              v-for="(option, index) in newTwootTypes"
              :key="index"
            >
              {{ option.name }}
            </option>
          </select>
        </div>
        <button :disabled="isNewTwootDisabled">Twoot!</button>
      </form>
    </div>
    <div class="user-profile__twoots-wrapper">
      <twootItem
        v-for="twit in user.twits"
        :key="twit.id"
        :username="user.username"
        :twoot="twit"
        :followers="followers"
        @favourite="toggleMakeFavourite"
        @delete="deleteTwoot"
      />
    </div>

    <div class="user-profile__buttons">
      <button @click="followUser">push</button>
      <button @click="resetFollowers">reset</button>
    </div>
  </div>
</template>

<script>
import twootItem from "./TwootItem";
import Notification from "./Notification";
export default {
  name: "UserProfile",
  components: { twootItem, Notification },
  data() {
    return {
      followers: 0,
      newTwootContent: "",
      contentType: "instant",
      newTwootTypes: [
        { value: "draft", name: "Draft" },
        { value: "instant", name: "Instant Twoot" },
      ],
      user: {
        id: 1,
        username: "golda6",
        firstname: "avi",
        lastname: "gold",
        email: "avishaygold@gmail.com",
        isAdmin: true,
        twits: [
          { id: 1, content: "Twitter is amazing" },
          { id: 2, content: "Facebook is amazing" },
        ],
      },
    };
  },
  watch: {
    followers(newnumber, old) {
      if (newnumber > old) {
        console.log(
          `${this.user.firstname} ${this.user.lastname} has follower joined`,
        );
      }
    },
  },
  computed: {
    newTwootCharacterCount() {
      return this.newTwootContent.length;
    },
    isNewTwootDisabled() {
      return !this.newTwootContent.length || this.newTwootContent.length > 50;
    },
  },
  methods: {
    followUser() {
      this.followers++;
      if (this.followers > 0 && this.followers % 10 === 0) {
        if (
          this.$refs.notification &&
          typeof this.$refs.notification.notify === "function"
        ) {
          this.$refs.notification.notify(
            `You have reached ${this.followers} followers!`,
            "success",
            3000,
          );
        }
      }
    },
    resetFollowers() {
      this.followers = 0;
    },
    toggleMakeFavourite(id) {
      console.log(`Favourite Id #${id}`);
    },
    deleteTwoot(id) {
      this.user.twits = this.user.twits.filter((twit) => twit.id !== id);
    },
    createNewTwoot() {
      if (this.newTwootContent && this.contentType !== "draft") {
        this.user.twits.unshift({
          id: Date.now(),
          content: this.newTwootContent,
        });
        this.newTwootContent = "";
      }
    },
  },
  mounted() {
    this.followUser();
  },
};
</script>

<style lang="scss" scoped>
.user-profile {
  display: grid;
  // flex-wrap: wrap;
  grid-template-columns: 1fr 3fr;
  grid-gap: 10px;
  padding: 50px 5%;

  .user-profile__user-panel {
    display: flex;
    flex-direction: column;
    padding: 20px;
    background-color: white;
    border-radius: 5px;
    border: 1px solid #dfe3e8;
    margin-bottom: auto;
    .user-profile__admin-badge {
      background: rebeccapurple;
      color: white;
      border-radius: 5px;
      margin-right: auto;
      padding: 0 10px;
      font-weight: bold;
    }
  }
}
h1 {
  margin: 0;
}

.user-profile__twoots-wrapper {
  display: grid;
  grid-gap: 10px;
  margin-bottom: auto;
}

.user-profile__create-twoot {
  padding-top: 20px;
  display: flex;
  flex-direction: column;
}
.user-profile__buttons {
  display: flex;
  flex-direction: column;
  padding: 20px;
  background-color: rgb(187, 181, 181);
  border-radius: 5px;
  border: 1px solid #dfe3e8;
  margin-bottom: auto;
  // float: left;
  // display: block;
}

.exceeded {
  color: red;
}
</style>
