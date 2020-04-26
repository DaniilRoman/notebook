<template>
  <div class="login-form">
    <b-card title="Login" style="max-width: 20rem;" class="mb-2">
      <div>
        <b-form-input type="text" placeholder="Username" v-model="username" />
        <div class="mt-2"></div>

        <b-form-input type="password" placeholder="Password" v-model="password" />
        <div class="mt-2"></div>
      </div>

      <b-button class="login-btn" v-on:click="_login" variant="primary">Login</b-button>
    </b-card>
  </div>
</template>

<script>
import { mapActions } from "vuex";

export default {
  name: "Login",
  data() {
    return {
      username: "",
      password: ""
    };
  },
  methods: {
    ...mapActions(["login"]),
    _login() {
      this.login({ username: this.username, password: this.password });
    }
  }
};
</script>

<style>
.login-form {
  margin-left: 38%;
  margin-top: 50px;
}
.login-btn {
  margin-top: 10px;
}
</style>
