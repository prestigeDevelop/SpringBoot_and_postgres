import Vue from "vue";
import Vuex from "vuex";
import UserInfo from "../models/UserInfo";
Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    user: UserInfo.createDefaultUser(),
    users: [
      {
        id: 1,
        username: "golda6",
        firstname: "avi",
        lastname: "gold",
        email: "avishaygold@gmail.com",
        isAdmin: true,
        twits: [
          {
            id: 1,
            content: "Twitter is amazing",
            postDate: new Date("2024-06-01T12:00:00"),
          },
          { id: 2, content: "Facebook is amazing" },
        ],
        joinDate: new Date("2024-06-01T12:00:00"),
      },
    ],
    selectedRegistrationType: "new",
  },
  mutations: {
    setUser(state, user) {
      state.user = user;
    },
    addTwoot(state, twoot) {
      state.user.twits.push(twoot);
    },
    deleteTwoot(state, id) {
      console.log("Deleting twoot with id:", id);
      state.user.twits = state.user.twits.filter((twoot) => twoot.id !== id);
    },
    editTwoot(state, { id, content }) {
      const twoot = state.user.twits.find((twoot) => twoot.id === id);
      if (twoot) {
        twoot.content = content;
      }
    },
    setSelectedRegistrationType(state, registrationType) {
      state.selectedRegistrationType = registrationType;
    },
    addUserToUsers(state, user) {
      state.users.push({ ...user, id: Date.now() });
    },
  },
  actions: {
    updateUser({ commit }, user) {
      commit("setUser", user);
    },
  },
  getters: {
    getUser(state) {
      return state.user;
    },
    existingUsers(state) {
      return state.users;
    },
  },
});
