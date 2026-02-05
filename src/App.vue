<template>
  <div id="app">
    <Header />
    <router-view />
    <!-- <TwootItem />
    <UserProfile /> -->
  </div>
</template>

<script>
import Header from "./components/header.vue";
// import TwootItem from "./components/TwootItem.vue";
// import UserProfile from "./components/UserProfile.vue";

export default {
  name: "App",
  components: { Header },
};
</script>

<style lang="scss">
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #4f502c;
  min-height: 100vh;
  background-color: #f3f5fa;
  nav {
    display: flex;
    align-items: center;
    justify-content: space-between;
    padding: 10px 5%;
    background-color: deeppink;
    color: white;
    .navigation__logo {
      font-weight: bold;
      font-size: 24px;
    }
    .navigation__user {
      font-weight: bold;
    }
  }
}
</style>
