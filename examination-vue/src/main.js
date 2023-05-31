// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
// 引入element-ui当中的所有组件
import ElementUI from 'element-ui';
//引入element-ui的所有样式
import 'element-ui/lib/theme-chalk/index.css';
import axios from './utils/axiosInterceptors.js';

Vue.config.productionTip = false
// 配置 Vue 插件
Vue.use(ElementUI);

axios.defaults.baseURL = "http://localhost:10000";
Vue.prototype.$http = axios;
//关闭弹框点击遮罩之外的地方关闭弹框
ElementUI.Dialog.props.closeOnClickModal.default = false;

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})