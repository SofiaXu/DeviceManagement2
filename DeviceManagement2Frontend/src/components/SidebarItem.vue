<template>
  <li class="nav-item">
    <router-link class="nav-link" :to="to">
      <slot></slot>
    </router-link>
  </li>
</template>

<script lang="ts">
import { Options, Vue } from "vue-class-component";
@Options({
  props: {
    to: String
  },
})
export default class SidebarItem extends Vue {
}
</script>