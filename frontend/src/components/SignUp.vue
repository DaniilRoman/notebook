<template>
    <div div="signup">
        <div class="login-form">
        <b-card
              title="Register"
              style="max-width: 20rem;"
              class="mb-2"
            >
              <div>
                 <b-form-input type="text" placeholder="Username" v-model="username" />
                 <div class="mt-2"></div>

                 <b-form-input type="password" placeholder="Password" v-model="password" />
                 <div class="mt-2"></div>

                <b-form-input type="password" placeholder="Confirm Password" v-model="confirmpassword" />

                 <div class="mt-3"></div>
              </div>

              <b-button v-on:click="_signUp" variant="primary">Register</b-button>

            </b-card>
        </div>
    </div>
</template>

<script>
import { mapActions } from 'vuex'

export default {
  name: 'SignUp',
  data () {
    return {
        username: '',
        password: '',
        confirmpassword: ''
    }
  },
  methods: {
    ...mapActions(["signUp"]),
    _signUp() {
      this.signUp({
        username: this.username,
        password: this.password,
        confirmpassword: this.confirmpassword 
      })
      this.$router.push("/notebooks")
    }
  }
}
</script>

<style scoped>
</style>
