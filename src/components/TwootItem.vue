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
      <div class="twoot-item__user">@{{ username }}{{ postDate }}</div>
      <br />
      <div class="twoot-item__content">{{ twoot.content }}<br /></div>

      <button class="twoot-item__delete" @click.stop="deleteTwoot(twoot.id)">
        Delete
      </button>
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
    postDate: {
      type: Date,
      required: false,
      default: () => new Date(),
    },
  },
  methods: {
    favourTwoot(id) {
      this.$emit("favourite", id);
    },
    deleteTwoot(id) {
      this.$emit("delete", id);
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
    transform: scale(1, 1.05);
    background-color: #cfd0d3;
  }
  .twoot-item__user {
    font-weight: bold;
  }
  .twoot-item__delete {
    background-color: red;
    color: white;
    border: none;
    padding: 5px 10px;
    border-radius: 5px;
    cursor: pointer;
    margin-top: 10px;
    float: right;
  }
}
</style>
