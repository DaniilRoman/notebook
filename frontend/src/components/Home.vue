<template>
    <div div="home">
        <b-jumbotron>
        <template slot="header">Notebooks</template>

        <template slot="lead">
          You are welcome! <span v-if="!isAuthenticated">Login for start.</span>
        </template>

        <hr class="my-4" />

        <router-link to="/login" v-if="!isAuthenticated">
            <b-button variant="primary">Login</b-button>
        </router-link>

      </b-jumbotron>
    </div>
</template>

<script>
import { mapGetters } from "vuex";
export default {
  name: 'Home',
  computed: mapGetters(["isAuthenticated"]),
  props: {
    msg: String
  },
  components: {
  }
}
</script>

<style>
</style>
