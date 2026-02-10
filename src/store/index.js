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
