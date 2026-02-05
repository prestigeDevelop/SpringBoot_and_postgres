<template>
  <!-- <div @click="favourTwoot(twoot.id)">
    <div class="twoot-item">
      <div>@{{ username }}</div>
      <div>{{ followers }}</div>
      <div>{{ twoot.content }}</div>
    </div>
  </div> -->

  <div class="twoot-item" @click="favourTwoot(twoot.id)">
    <div class="user-profile__twoot">
      <div class="twoot-item__user">@{{ username }}</div>
      <div class="twoot-item__content">
        {{ twoot.content }}<br />
        {{ twoot.id }}
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "TwootItem",
  props: {
    username: {
      type: String,
      required: true,
    },
    followers: {
      type: Number,
      required: false,
    },
    twoot: {
      type: Object,
      required: true,
    },
  },
  methods: {
    favourTwoot(id) {
      this.$emit("favourite", id);
    },
  },
};
</script>

<style lang="scss" scoped>
.twoot-item {
  padding: 20px;
  background-color: white;
  border: 1px solid #dfe3e8;
  //box-sizing: border-box;
  cursor: pointer;
  transition: all 0.25s ease;
  border-radius: 25px;
  &:hover {
    transform: scale(1.2, 1.1);
  }
  .twoot-item__user {
    font-weight: bold;
  }
}
</style>
