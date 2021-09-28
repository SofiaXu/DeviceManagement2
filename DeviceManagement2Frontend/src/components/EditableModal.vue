<template>
  <div
    class="modal fade"
    :class="{
      show: internalIsShow,
      'd-block': internalIsShow,
      'd-none': !internalIsShow,
    }"
  >
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <slot class="modal-title" name="title">编辑</slot>
          <button
            type="button"
            class="btn-close"
            aria-label="Close"
            @click="close()"
          ></button>
        </div>
        <div class="modal-body">
          <slot></slot>
        </div>
        <div class="modal-footer">
          <button
            type="button"
            class="btn btn-secondary"
            @click="close()"
          >
            ❌取消
          </button>
          <button type="button" class="btn btn-primary" @click="save()">
            💾保存
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Vue } from "vue-class-component";

export default class EditableModal extends Vue {
  private internalIsShow = false;

  public show() {
    this.internalIsShow = true;
  }

  public close() {
    this.internalIsShow = false;
    this.$emit("close", null);
  }

  private save() {
    this.close();
    this.$emit("save", null);
  }
}
</script>
