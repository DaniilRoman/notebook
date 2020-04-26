import notebookService from '../../restClient/notebookService';

export default {
    state: {
        // id title text
        notebooks: [],
        currentNotebookId: '',
        alertMsg: ''
    },
    mutations: {
        updateNotebooks(state, notebooks) {
            state.notebooks = notebooks
        },
        updateAlertMsg(state, error) {
            state.alertMsg = error
        },
        addNotebook(state, notebook) {
            state.notebooks.push(notebook)
        },
        updateNotebook(state, updated) {
            state.notebooks = state.notebooks
                .map(notebook => notebook.id === updated.id ? updated : notebook)
        },
        deleteNotebook(state, id) {
            state.notebooks = state.notebooks.filter(notebook => notebook.id !== id)
        }
    },
    getters: {
        getNotebooks(state) {
            return state.notebooks
        }
    },
    actions: {
        fetchNotebooks(state) {
            notebookService.getAll()
                .then(response => {
                    state.commit('updateNotebooks', response.data)
                },
                    error => {
                        state.commit('updateAlertMsg', error)
                    })
        },
        save(state, { title, text }) {
            notebookService.save({title, text})
                .then(response => {
                    state.commit('addNotebook', response.data)
                },
                    error => {
                        state.commit('updateAlertMsg', error)
                    })
        },
        update(state, notebook) {
            notebookService.update(notebook)
                .then(response => {
                    state.commit('updateNotebook', response.data)
                },
                    error => {
                        state.commit('updateAlertMsg', error)
                    })
        },
        deleteNote(state, id) {
            notebookService.delete(id)
                .then(() => {
                    state.commit('deleteNotebook', id)
                },
                    error => {
                        state.commit('updateAlertMsg', error)
                    })
        }
    }
}
