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
      <div class="twoot-item__user">
        @{{ username }}
        <span class="twoot-item__date">· {{ relativeDate }}</span>
      </div>
      <br />
      <div class="twoot-item__content">{{ twoot.content }}<br /></div>

      <button class="twoot-item__delete" @click.stop="deleteTwoot(twoot.id)">
        Delete
      </button>
      <!-- add edit button -->
      <b-button
        type="submit"
        variant="secondary"
        size="lg"
        pill
        block
        @click.stop="editTwoot(twoot.id)"
        >Edit Twoot</b-button
      >
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
  computed: {
    relativeDate() {
      const diff = Math.round((this.postDate - new Date()) / 1000);
      if (Math.abs(diff) < 60) return this.formattedPostDate();
      if (Math.abs(diff) < 3600) return `${Math.round(diff / 60)}m ago`;
      if (Math.abs(diff) < 86400) return `${Math.round(diff / 3600)}h ago`;
      if (Math.abs(diff) < 2592000) return `${Math.round(diff / 86400)}d ago`;
      if (Math.abs(diff) < 31536000)
        return `${Math.round(diff / 2592000)}mo ago`;
      if (Math.abs(diff) < 315360000)
        return `${Math.round(diff / 31536000)}y ago`;
      return this.postDate.toLocaleDateString("en-US", {
        month: "short",
        day: "numeric",
      });
    },
  },
  methods: {
    favourTwoot(id) {
      this.$emit("favourite", id);
    },
    deleteTwoot(id) {
      this.$emit("delete", id);
    },
    editTwoot(id) {
      this.$emit("editTwoot", id);
    },
    formattedPostDate() {
      const options = { year: "numeric", month: "short", day: "numeric" };
      return new Date(this.postDate).toLocaleDateString(undefined, options);
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
    color: #333;
  }
  .twoot-item__date {
    font-weight: normal;
    color: #999;
    margin-left: 4px;
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
