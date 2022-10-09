import axios from 'axios'
import router from '../router'

import {Message} from "element-ui";
import { Loading } from "element-ui";

let loading = null;

//请求拦截器
axios.interceptors.request.use(
  function (config) {
    var user = localStorage.getItem("user");
    if (user != null && user != ""){
      var userObj = JSON.parse(user);
      var token = userObj.token;
      config.headers['Authorization'] = token;
    }
    //请求之前
    loading = Loading.service({
      lock: true,
      spinner: 'el-icon-loading',
      text:"拼命加载中...",//显示在加载图标下方的加载文案
      background:"rgba(0, 0, 0, 0.7)",//遮罩背景色
    });
    return config;
  },
  function (error) {
    //请求错误
    loading.close();
    return Promise.reject(error);
  }
)

//响应拦截器
axios.interceptors.response.use(
  function (response) {
    loading.close();
    if (response.data.code == "401") {
      Message.error({message:'暂未登录或token已经过期'});
      router.replace({
        path: '/'
      });
    }else if (response.data.code != "200"){
      Message.error({message:response.data.message});
    }
    //响应数据
    return response;
  },
  function (error) {
    //响应错误
    loading.close();
    Message.error({message:"响应错误"});
    return Promise.reject(error);
  }
)

export default axios;