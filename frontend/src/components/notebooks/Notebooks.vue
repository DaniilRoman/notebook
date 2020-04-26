<template>
  <div class="notebooks">
    <div class="notebooks" v-for="notebook in getNotebooks" :key="notebook.id">
      <Notebook :notebook="notebook" />
    </div>

    <div>
      <b-icon v-b-modal.create-note-modal class="add-icon" icon="plus-square">
      </b-icon>
      <CreateNoteModal />
    </div>
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
import Notebook from "./Notebook";
import CreateNoteModal from "./CreateNoteModal";

export default {
  name: "Notebooks",
  components: {
    Notebook,
    CreateNoteModal
  },
  data() {
    return {
      title: "",
      text: ""
    };
  },
  computed: {
    ...mapGetters(["getNotebooks"])
  },
  methods: {
    ...mapActions(["fetchNotebooks"])
  },
  mounted() {
    this.fetchNotebooks();
  }
};
</script>

<style scoped>
.notebooks {
  margin-top: 2%;
} 
.add-icon {
  width: 30px;
  height: 30px;
  color: #c5c7c9;
  box-shadow: 0 0 5px -1px rgba(0, 0, 0, 0.2);
}
.add-icon:active {
  color: #a5a7a8;
  box-shadow: 0 0 5px -1px rgba(0, 0, 0, 0.6);
}
.add-icon:hover {
  color: #a5a7a8;
}
</style>
