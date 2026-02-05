<template>
  <nav class="main-nav">
    <div class="nav-container">
      <router-link to="/" class="brand">Home</router-link>

      <div class="nav-links brand">
        <router-link to="/flex">flex</router-link>
        <router-link to="/userProfile">User Profile</router-link>
      </div>
    </div>
  </nav>
</template>

<script>
export default {
  name: "MainMenu",
};
</script>

<style lang="scss" scoped>
.main-nav {
  background: #222;
  padding: 12px 0;
}

.nav-container {
  max-width: 1170px;
  margin: 0 auto;
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.brand {
  color: #fff;
  font-weight: bold;
  text-decoration: none;
  font-size: 18px;
  gap: 20px;
}

.nav-links {
  display: flex;
  gap: 20px;
}

.nav-links a {
  color: #ddd;
  text-decoration: none;
}

.nav-links a.router-link-active {
  color: #fff;
  border-bottom: 2px solid #fff;
}
</style>
