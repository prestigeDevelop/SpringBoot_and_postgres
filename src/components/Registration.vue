<template>
  <div class="registration">
    <BRow>
      <BCol cols="12" md="6">
        <div class="user-profile__header">
          <h2>Registration Profile</h2>
        </div>
        <BButtonClose @click="$emit('closeRegistration')" />
        <div class="user-profile__info">
          <BInputGroup name="username" class="mb-3">
            <BFormInput v-model="user.username" placeholder="Username" />
          </BInputGroup>

          <BInputGroup name="Email" class="mb-3">
            <BFormInput v-model="user.email" placeholder="Email" />
          </BInputGroup>
          <BInputGroup name="firstname" class="mb-3">
            <BFormInput v-model="user.firstname" placeholder="First Name" />
          </BInputGroup>
          <BInputGroup name="lastname" class="mb-3">
            <BFormInput v-model="user.lastname" placeholder="Last Name" />
          </BInputGroup>

          <p><strong>Username:</strong> {{ user.username }}</p>
          <p><strong>Email:</strong> {{ user.email }}</p>
          <p><strong>First Name:</strong> {{ user.firstname }}</p>
          <p><strong>Last Name:</strong> {{ user.lastname }}</p>
          <p><strong>Join Date:</strong> {{ formattedJoinDate }}</p>
        </div>
        <div class="user-profile__twoot-form">
          <form @submit.prevent="registerUser">
            <div class="form-group">
              <label for="registrationType">Registration Type</label>
              <select
                id="registrationType"
                v-model="selectedRegistrationType"
                class="form-control"
              >
                <option
                  v-for="option in registrationOptions"
                  :key="option.id"
                  :value="option.value"
                >
                  {{ option.name }}
                </option>
              </select>
            </div>
            <BButton
              type="submit"
              :disabled="isRegisterDisabled"
              variant="primary"
              size="lg"
              pill
              block
              >Register!</BButton
            >
          </form>
        </div>
      </BCol>
      <BCol>
        <div class="form-group">
          <label for="existingUsers">Users:</label>
          <select
            id="existingUsers"
            v-model="selectedExistingUsers"
            class="form-control"
          >
            <option
              v-for="option in existingUsersOptions"
              :key="option.id"
              :value="option.id"
            >
              {{ option.username }}
            </option>
          </select>
        </div>
      </BCol>
    </BRow>
  </div>
</template>

<script>
import {
  BRow,
  BCol,
  BButton,
  BButtonClose,
  BFormInput,
  BInputGroup,
} from "bootstrap-vue";
export default {
  name: "Registration",
  components: {
    BRow,
    BCol,
    BButton,
    BButtonClose,
    BFormInput,
    BInputGroup,
  },
  data() {
    return {
      selectedExistingUsers: null,
      registrationOptions: [
        { id: 1, name: "Standard", value: "standard" },
        { id: 2, name: "Premium", value: "premium" },
        { id: 3, name: "VIP", value: "vip" },
      ],
      selectedRegistrationType: "Standard",
    };
  },
  computed: {
    formattedJoinDate() {
      const options = { year: "numeric", month: "long", day: "numeric" };
      if (!this.user.joinDate) return "";
      return new Date(this.user.joinDate).toLocaleDateString(undefined, options);
    },
    isRegisterDisabled() {
      return this.selectedRegistrationType === "";
    },
    user() {
      return this.$store.state.user;
    },

    existingUsersOptions() {
      return this.$store.state.users;
    },
  },
  watch: {
    selectedExistingUsers(selectedId) {
      const selectedUser = this.$store.state.users.find(
        (u) => u.id === selectedId,
      );
      if (selectedUser) {
        this.$store.commit("setUser", { ...selectedUser });
      }
    },
  },
  methods: {
    registerUser() {
      console.log("User registered with type:", this.selectedRegistrationType);
      this.$store.commit("addUserToUsers", {
        id: new Date().getTime(),
        username: this.user.username,
        firstname: this.user.firstname,
        lastname: this.user.lastname,
        email: this.user.email,
        isAdmin: true,
        twits: [],
        joinDate: new Date(),
      });
      //this.$emit("closeRegistration");
    },
    closeRegistration() {
      this.$emit("setUser", {
        id: 1,
        username: "golda7",
        firstname: "John",
        lastname: "Dohe",
        email: "John@Dohe@gmail.com",
        isAdmin: true,
        twits: [],
        joinDate: new Date(),
      });
    },
  },
};
</script>

<style></style>
