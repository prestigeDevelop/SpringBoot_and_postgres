import Vue from "vue";
import Vuex from "vuex";
import UserInfo from "../models/UserInfo";
Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    user: UserInfo.createDefaultUser(),
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
      //const index = this.state.user.twits.findIndex((twoot) => twoot.id === id);
    },
    editTwoot(state, { id, content }) {
      const twoot = state.user.twits.find((twoot) => twoot.id === id);
      if (twoot) {
        twoot.content = content;
      }
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
  },
});
