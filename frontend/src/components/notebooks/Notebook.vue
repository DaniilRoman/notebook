<template>
  <div class="card shadow">
    <div>{{ notebook.title }}</div>
    <div>{{ notebook.text }}</div>
    <div class="row">
      <div class="row-item" v-on:click="deleteNote(notebook.id)">
        <b-icon class="delete-icon" icon="trash-fill"></b-icon>
      </div>
      <div class="row-item" v-on:click="editNote">
        <b-icon class="edit-icon" icon="pencil-square"></b-icon>
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions } from 'vuex';
export default {
  name: "Notebook",
  data() {
    return {};
  },
  props: ["notebook"],
  computed: {},
  methods: {
      ...mapActions(["deleteNote"]),
      editNote() {
          // TODO
      }
  }
};
</script>

<style scoped>
.card {
  width: 50%;
  margin: 0 auto;
  height: 10%;
  text-align: center;

  border: 1px solid #c5c7c9;
  margin-bottom: 1%;
  padding: 10px;
}
.card:hover {
  border-color: #7f8181;
  box-shadow: 0 0 5px 0.2px rgba(0, 0, 0, 0.3);
}
.shadow {
  box-shadow: 0 0 3px 0.2px rgba(46, 46, 46, 0.3);
}
.row {
  display: flex;
  flex-direction: row-reverse;
  padding-right: 5%;
}
.row-item {
    margin-left: 2%;
}
</style>
