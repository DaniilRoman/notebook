import accountService from '../../restClient/accountService';
import router from '../../router';

export default {
    state: {
        token: localStorage.getItem('userToken') || '',
        role: localStorage.getItem('userRole') || '',
        username: localStorage.getItem('userName') || '',
        password: '',
        alertMessage: 'Request error',
        authorities: localStorage.getItem('authorities') || '',
    },
    mutations: {
        authLogin(state, user) {
            localStorage.setItem('userToken', user.token);
            localStorage.setItem('userName', user.username);
            localStorage.setItem('userAuthorities', user.roles);

            state.token = user.token;
            state.username = user.username;
            state.authorities = user.roles;

            var isUser = false;
            var isAdmin = false;
            for (var i = 0; i < user.roles.length; i++) {
                if (user.roles[i].authority === 'ROLE_USER') {
                    isUser = true;
                } else if (user.roles[i].authority === 'ROLE_ADMIN') {
                    isAdmin = true;
                }
            }
            if (isUser) {
                localStorage.setItem('userRole', 'user');
                state.role = 'user';
            }
            if (isAdmin) {
                localStorage.setItem('userRole', 'admin');
                state.role = 'admin';
            }
        },
        authLogout(state) {
            state.token = '';
            state.role = '';
            state.username = '';
            state.authorities = [];

            localStorage.removeItem('userToken');
            localStorage.removeItem('userRole');
            localStorage.removeItem('userName');
            localStorage.removeItem('userAuthorities');
        },
        updateAlertMsg(state, newAlertMsg) {
            state.alertMessage = newAlertMsg;
        }
    },
    getters: {
        isAuthenticated(state) {
            if (state.token != null && state.token != '') {
                return true;
            } else {
                return false;
            }
        },
        isAdmin(state) {
            if (state.role === 'admin') {
                return true;
            } else {
                return false;
            }
        },
        getUsername(state) {
            return state.username;
        },
        getAuthorities(state) {
            return state.authorities;
        },
        getToken(state) {
            return state.token;
        }
    },
    actions: {
        login: (state, user) => {
            accountService.login(user)
                .then(
                    response => {
                        state.commit('authLogin', {
                            token: response.data.token,
                            roles: response.data.authorities,
                            username: response.data.username
                        });
                        router.push("/notebooks");
                    },
                    error => {
                        state.commit('updateAlertMsg',
                            error.response.data.message.length < 150
                                ? error.response.data.message
                                : "Request error. Please, report this error website owners");
                    }
                )
                .catch(e => {
                    console.log(e);
                    state.commit('authLogout',
                        "Server error. Please, report this error website owners"
                    );
                })
        },

        signUp(state, user) {
            if (user.username === '' || user.username == null) {
                state.commit("updateAlertMsg", 'Please, fill "Username" field')
            } else if (user.password === '' || user.password == null) {
                state.commit("updateAlertMsg", 'Please, fill "Password" field')
            } else if (user.confirmpassword === '' || user.confirmpassword == null) {
                state.commit("updateAlertMsg", 'Please, confirm password')
            } else if (user.confirmpassword !== user.password) {
                state.commit("updateAlertMsg", 'Passwords are not match')
            } else {
                accountService.signUp(user)
                    .then(() => {
                        state.dispatch('login', user)
                    }, error => {
                        state.commit("updateAlertMsg", (error.response.data.message.length < 150)
                            ? error.response.data.message
                            : 'Request error. Please, report this error website owners')
                    })
                    .catch(error => {
                        console.log(error);
                        state.commit("updateAlertMsg", 'Request error. Please, report this error website owners')
                    });
            }
        },

        logout(state) {
            state.commit('authLogout');
        }
    }

}
