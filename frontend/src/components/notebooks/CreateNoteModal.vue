<template>
  <b-modal ref="modal" hide-footer hide-header id="create-note-modal">
    <div class="content">
      <div class="field">
        <label>Title</label>
        <b-form-input v-model="title" placeholder="Title"></b-form-input>
      </div>

      <div class="field">
        <label>Description</label>
        <b-form-textarea v-model="text" id="textarea-small" size="sm" placeholder="Text"></b-form-textarea>
      </div>

      <div class="buttons">
        <button class="btn create" v-on:click="create">Create</button>
        <button class="btn cancel" v-on:click="hideModal">Cancel</button>
      </div>
    </div>
  </b-modal>
</template>

<script>
import { mapActions } from "vuex";
export default {
  name: "CreateNoteModal",
  data() {
    return {
      title: "",
      text: ""
    };
  },
  computed: {},
  mounted() {
    this.$root.$on("bv::modal::hide", () => {
      this.title = "";
      this.text = "";
    });
  },
  methods: {
    ...mapActions(["save"]),
    create() {
      this.save({ title: this.title, text: this.text });
      this.hideModal();
    },
    hideModal() {
      this.$refs["modal"].hide();
    }
  }
};
</script>

<style scoped>
.content {
  font-family: "Century Gothic";
  display: flex;
  flex-direction: column;
  align-items: center;
}
.field {
  margin-top: 2%;
  display: flex;
  flex-direction: column;
  align-items: center;
}
.buttons {
  margin-top: 4%;
}
.btn {
  background-color: white;
  border: 1px solid transparent;
  border-radius: 0.25rem;
}
.create {
  border-top-right-radius: 0;
  border-bottom-right-radius: 0;
  color: #8bf07c;
  border-color: #8bf07c;
}
.create:hover {
  color: #87cd63;
}
.cancel {
  border-top-left-radius: 0;
  border-bottom-left-radius: 0;
  color: #c5c7c9;
  border-color: #c5c7c9;
}
.cancel:hover {
  color: #a5a7a8;
}
</style>
