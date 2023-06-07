<template>
  <div>
    <el-container :style="defaultHeight">
      <el-aside :width="isCollapse ? '64px' : '200px'">
        <el-menu :collapse="isCollapse" background-color="#304156" text-color="rgb(191, 203, 217)" @open="handleOpen"
          @close="handleClose" :collapse-transition="false" router v-if="menuList" :default-active="defaultSelect">
          <el-menu-item>
            <div class="systemTitle">青云在线考试系统</div>
          </el-menu-item>
          <template v-for="item in menuList">
            <el-submenu v-if="item.children != null && item.children.length > 0" :index="item.route">
              <template slot="title">
                <i :class="item.icon"></i>
                <span slot="title">{{ item.name }}</span>
              </template>
              <template v-for="child in item.children">
                <el-menu-item :index="child.route">
                  <i :class="child.icon"></i>{{ child.name }}
                </el-menu-item>
              </template>
            </el-submenu>
            <el-menu-item v-if="item.children == null || item.children.length == 0" :index="item.route">
              <i :class="item.icon"></i>
              <span slot="title">{{ item.name }}</span>
            </el-menu-item>
          </template>
        </el-menu>
      </el-aside>
      <el-container>
        <el-header>
          <div class="toggle-button" @click="toggleCollapse">
            <i class="el-icon-s-operation" style="color: black!important;"></i>
          </div>
          <div id="mbx">
            <el-breadcrumb separator="/">
              <el-breadcrumb-item v-for="item in breadList" :key="item.path">
                {{ item.meta.title }}
              </el-breadcrumb-item>
            </el-breadcrumb>
          </div>
          <div id="userBaner">
            <i class="el-icon-user-solid" style="color: black!important;"></i>
            <el-dropdown @command="handleCommand">
              <span class="el-dropdown-link" style="cursor:pointer;">
                {{ userBaner }}
                <i class="el-icon-arrow-down el-icon--right"></i>
              </span>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item command="updatePassword">修改密码</el-dropdown-item>
                <el-dropdown-item command="logout">退出</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </div>
        </el-header>
        <el-main>
          <div style="position: relative">
            <router-view></router-view>
          </div>
        </el-main>
      </el-container>
    </el-container>
    <div>
      <el-dialog title="修改密码" :visible.sync="dialogFormVisible">
        <el-form :rules="rules" :model="user" ref="user">
          <el-form-item label="旧密码：" label-width="150px" prop="oldPassword">
            <el-input v-model="user.oldPassword" clearable></el-input>
          </el-form-item>
          <el-form-item label="新密码：" label-width="150px" prop="newPassword">
            <el-input v-model="user.newPassword" clearable></el-input>
          </el-form-item>
          <el-form-item label="重复新密码：" label-width="150px" prop="repeatNewPassword">
            <el-input v-model="user.repeatNewPassword" clearable></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="updatePassword('user')">确 定</el-button>
        </div>
      </el-dialog>
    </div>
  </div>
</template>

<script>
export default {
  name: 'home',
  data() {
    return {
      transitionFlag: "",
      defaultHeight: {
        height: ""
      },
      isCollapse: false,
      userBaner: "",
      breadList: [], // 路由集合
      user: {
        oldPassword: "",
        newPassword: "",
        repeatNewPassword: ""
      },
      dialogFormVisible: false,
      menuList: [],
      defaultSelect: "",
      rules: {
        oldPassword: [
          { required: true, message: '旧密码不能为空', trigger: 'blur' },
          { max: 20, message: "旧密码长度不能超过20个字符", trigger: "blur" }
        ],
        newPassword: [
          { required: true, message: '新密码不能为空', trigger: 'blur' },
          { max: 20, message: "新密码长度不能超过20个字符", trigger: "blur" }
        ],
        repeatNewPassword: [
          { required: true, message: '重复新密码不能为空', trigger: 'blur' },
          { max: 20, message: "重复新密码长度不能超过20个字符", trigger: "blur" }
        ]
      }
    };
  },
  methods: {
    //定义方法，获取高度减去头尾
    getHeight() {
      this.defaultHeight.height = window.innerHeight - 1 + "px";
    },
    toggleCollapse() {
      this.isCollapse = !this.isCollapse
    },
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
    getBreadcrumb() {
      let matched = this.$route.matched.filter(item => item.meta.title);  //获取路由信息，并过滤保留路由名称信息存入数组
      this.breadList = matched;
    },
    setUserBaner() {
      var user = localStorage.getItem("user");
      var userObj = JSON.parse(user);
      this.userBaner = userObj.name;
    },
    handleCommand(command) {
      if (command == "logout") {
        localStorage.setItem("user", "");
        this.$router.replace({
          path: '/'
        });
      } else if (command == "updatePassword") {
        this.user.oldPassword = "";
        this.user.newPassword = "";
        this.user.repeatNewPassword = "";
        this.dialogFormVisible = true;
      }
    },
    updatePassword(formName) {
      this.$refs[formName].validate((valid) => {
        if (!valid) {
          return false;
        } else {
        }
      }
      );
    },
    getMenuList() {
      const that = this;
      that.$http
        .get('/authority/getUserMenuList')
        .then(function (response) {
          if (response.data.code == 200) {
            that.menuList = response.data.data;
            //默认打开第一个菜单
            if (response.data.data.length > 0) {
              var firstMenu = response.data.data[0];
              if (firstMenu.children == null || firstMenu.children.length == 0) {
                that.defaultSelect = firstMenu.route;
              } else {
                that.defaultSelect = firstMenu.children[0].route;
              }
              that.$router.replace({
                path: that.defaultSelect
              });
            }
          }
        })
    }
  },
  created() {
    //页面创建时执行一次getHeight进行赋值，顺道绑定resize事件
    window.addEventListener("resize", this.getHeight);
    this.getHeight();
    this.getMenuList();
    this.getBreadcrumb();
    this.setUserBaner();

  },
  watch: {
    $route() {
      this.getBreadcrumb();  //监听路由变化
    }
  }
}
</script>

<style>
.el-header {
  color: #333;
  text-align: center;
  line-height: 60px;
  border-bottom: 1px solid #e6e6e6;
  padding: 0px;
}

.el-aside {
  background-color: #304156;
  line-height: 200px;
  overflow-x: hidden;
  transition: width .28s;
}

html,
body {
  margin: 0 auto;
  padding: 0;
}

#mbx {
  float: left;
}

#userBaner {
  float: right;
  line-height: 60px;
  margin-right: 30px;
}

i {
  color: rgb(191, 203, 217) !important;
}

.el-breadcrumb {
  line-height: 60px;
  font-weight: 700;
}

.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
}

.el-menu {
  border-right: solid 0px #e6e6e6 !important;
}

.toggle-button {
  font-size: 25px;
  text-align: center;
  cursor: pointer;
  width: 50px;
  float: left;
}

.el-submenu .el-menu-item {
  background-color: #1f2d3d !important;
  text-align: center;
}

.el-submenu .el-menu-item:hover {
  background-color: #001528 !important;
}

.el-aside::-webkit-scrollbar {
  display: none;
}

.systemTitle {
  text-align: center;
  font-size: 16px;
  color: white;
}
</style>