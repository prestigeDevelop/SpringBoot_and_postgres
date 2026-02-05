<template>
  <transition name="fade">
    <div v-if="isVisible" class="notification" :class="currentType">
      <div class="notification__content">
        <p class="notification__message">{{ currentMessage }}</p>
        <button class="notification__close" @click="close">×</button>
      </div>
    </div>
  </transition>
</template>

<script>
export default {
  name: "Notification",
  props: {
    message: {
      type: String,
      default: "",
    },
    type: {
      type: String,
      default: "success",
      validator: (value) =>
        ["success", "error", "warning", "info"].includes(value),
    },
    duration: {
      type: Number,
      default: 3000,
    },
  },
  data() {
    return {
      isVisible: false,
      currentMessage: this.message || "",
      currentType: this.type || "success",
      currentDuration: this.duration || 3000,
      timeoutId: null,
    };
  },
  methods: {
    show() {
      this.isVisible = true;
      if (this.timeoutId) {
        clearTimeout(this.timeoutId);
        this.timeoutId = null;
      }
      if (this.currentDuration > 0) {
        this.timeoutId = setTimeout(() => this.close(), this.currentDuration);
      }
    },
    close() {
      this.isVisible = false;
      if (this.timeoutId) {
        clearTimeout(this.timeoutId);
        this.timeoutId = null;
      }
    },
    notify(message, type = this.currentType, duration = this.currentDuration) {
      this.currentMessage = message;
      this.currentType = type;
      this.currentDuration = duration;
      this.show();
    },
  },
  beforeUnmount() {
    if (this.timeoutId) {
      clearTimeout(this.timeoutId);
    }
  },
};
</script>

<style lang="scss" scoped>
.notification {
  position: fixed;
  top: 20px;
  right: 20px;
  border-radius: 5px;
  padding: 15px 20px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
  z-index: 1000;
  min-width: 300px;

  &.success {
    background-color: #4caf50;
    color: white;
  }

  &.error {
    background-color: #f44336;
    color: white;
  }

  &.warning {
    background-color: #ff9800;
    color: white;
  }

  &.info {
    background-color: #2196f3;
    color: white;
  }
}

.notification__content {
  display: flex;
  justify-content: space-between;
  align-items: center;
  gap: 10px;
}

.notification__message {
  margin: 0;
  font-size: 14px;
}

.notification__close {
  background: none;
  border: none;
  color: inherit;
  font-size: 24px;
  cursor: pointer;
  padding: 0;
  min-width: auto;
  width: 24px;
  height: 24px;
  display: flex;
  align-items: center;
  justify-content: center;

  &:hover {
    opacity: 0.8;
  }
}

.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.3s ease;
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}
</style>
