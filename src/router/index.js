import Vue from "vue";
import Router from "vue-router";

// page components
import Home from "@/components/HelloWorld.vue";
import Flex from "@/components/flex.vue";
import UserProfile from "@/components/UserProfile.vue";

Vue.use(Router);

export default new Router({
  mode: "history", // cleaner URLs (no #)
  routes: [
    {
      path: "/",
      name: "Home",
      component: Home,
    },
    {
      path: "/flex",
      name: "Flex",
      component: Flex,
    },
    {
      path: "/userProfile",
      name: "UserProfile",
      component: UserProfile,
    },
    {
      path: "/registration",
      name: "Registration",
      component: () => import("@/components/Registration.vue"),
    },
  ],
});
