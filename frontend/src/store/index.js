import Vue from 'vue';
import Vuex from 'vuex';
import accountStore from './modules/accountStore';
import notebookStore from './modules/notebookStore';

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    accountStore,
    notebookStore
  }
})