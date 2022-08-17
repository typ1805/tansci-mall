import { defineStore } from 'pinia'

// 存储用户信息
export const useUserStore = defineStore('user', {
    state: () => ({
        username: '',
        nickname: '',
        type: '',
        shop: '',
        loginTime: '',
    }),
    getters: {
        getUser(){
            if(!this.username){
                let user = JSON.parse(sessionStorage.getItem('user'))
                this.username = user.username
                this.nickname = user.nickname
                this.type = user.type
                this.shop = user.shop
                this.loginTime = user.loginTime
            }
            return {
                username: this.username,
                nickname: this.nickname,
                type: this.type,
                shop: this.shop,
                loginTime: this.loginTime
            }
        }
    },
    actions: {
        setUser (data) {
            this.username = data.username
            this.nickname = data.nickname
            this.type = data.type
            this.shop = data.shop
            this.loginTime = data.loginTime
            sessionStorage.setItem('user', JSON.stringify({
                username: data.username,
                nickname: data.nickname,
                type: data.type,
                shop: data.shop,
                loginTime: data.loginTime
            }))
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
