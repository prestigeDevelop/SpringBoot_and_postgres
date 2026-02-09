# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Commands

- **Dev server:** `npm run serve` (runs vue-cli-service serve with hot reload)
- **Build:** `npm run build`
- **Lint:** `npm run lint`

No test framework is configured.

**Node.js compatibility:** Requires `NODE_OPTIONS=--openssl-legacy-provider` for Node.js 17+ (already set in the npm scripts). This is needed because Webpack 4 uses the MD4 hash which OpenSSL 3 removed.

## Architecture

This is a Vue 2 single-page application scaffolded with Vue CLI 4, styled with BootstrapVue and SCSS.

**Key framework choices:**
- Vue 2 (not Vue 3) — uses Options API throughout
- Vue Router 3 in history mode (`src/router/index.js`)
- BootstrapVue for UI components (registered globally in `main.js`, also imported per-component in some files)
- SCSS via `lang="scss"` in `<style scoped>` blocks
- ESLint with `plugin:vue/essential` + `eslint:recommended` (config in `package.json`)

**App structure:**
- `App.vue` renders a persistent `Header` nav and a `<router-view>` for page content
- Routes: `/` (HelloWorld), `/flex` (CSS grid demo), `/userProfile` (main feature page)
- `UserProfile` is the core component — contains local state for a user object with "twoots" (tweets), supports creating, editing, deleting, and favouriting twoots with a 50-character limit
- `TwootItem` is a child of `UserProfile`, communicates upward via `$emit` events (`favourite`, `delete`, `editTwoot`)
- `Notification` is a ref-driven toast component called imperatively via `this.$refs.notification.notify()`

**Data management:** All data is local component state (no Vuex). The user object and twoots array live in `UserProfile`'s `data()`.
