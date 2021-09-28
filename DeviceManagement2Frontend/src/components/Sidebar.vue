<template>
  <nav class="sidebar">
    <div class="position-sticky pt-3">
      <ul class="nav flex-column">
        <slot></slot>
      </ul>
    </div>
  </nav>
</template>

<script lang="ts">
import { Vue } from "vue-class-component";
export default class Sidebar extends Vue {}
</script>

<style scoped>
.sidebar {
  position: fixed;
  top: 0;
  bottom: 0;
  left: 0;
  z-index: 100;
  padding: 48px 0 0;
  box-shadow: inset -1px 0 0 rgba(0, 0, 0, 0.1);
}
</style>