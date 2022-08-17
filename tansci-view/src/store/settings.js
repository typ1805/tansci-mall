import { defineStore } from 'pinia'

// 存储用户信息
export const useUserStore = defineStore('user', {
    state: () => ({
        user: ''
    }),
    getters: {
        getUser(){
            if(!this.user){
                this.user = JSON.parse(sessionStorage.getItem('user'))
            }
            return {
                user: this.user
            }
        }
    },
    actions: {
        setUser (data) {
            this.user = data
            sessionStorage.setItem('user', JSON.stringify(data))
        },
        delUser () {
            sessionStorage.clear()
        }
    }
})

// 存储 token
export const useTokenStore = defineStore('token', {
    state: () => ({
        token: '',
    }),
    getters: {
        getToken(){
            if(!this.token){
                this.token = sessionStorage.getItem('token')
            }
            return this.token;
        }
    },
    actions: {
        setToken (data) {
            this.token = data
            sessionStorage.setItem('token', data)
        },
        delToken () {
            sessionStorage.clear()
        }
    }
})

// 存储菜单信息
export const useMenuStore = defineStore('menu', {
    state: () => ({
        menu: '',
    }),
    getters: {
        getMenu(){
            if(!this.menu){
                this.menu = JSON.parse(sessionStorage.getItem('menu'))
            }
            return this.menu;
        }
    },
    actions: {
        setMenu (data) {
            this.menu = data
            sessionStorage.setItem('menu', JSON.stringify(data))
        },
        delMenu () {
            sessionStorage.clear()
        }
    }
})
