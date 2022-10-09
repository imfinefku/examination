<template>
    <div style="float:left;">
        <div style="float:left;width:700px;">
            <el-card class="box-card">
                <div class="console">
                    <el-collapse v-model="activeNames">
                        <el-collapse-item title="项目介绍" name="1">
                            青云是一套麻雀虽小但五脏俱全的在线考试系统。采用了目前主流的技术栈并进行了前后端分离，对于事务和锁都有应用，非常适合学习练手。
                        </el-collapse-item>
                        <el-collapse-item title="使用技术" name="2">
                            <div>
                                <el-tag>SpringBoot</el-tag> 
                                <el-tag>Vue2.0</el-tag> 
                                <el-tag>Elementui</el-tag> 
                                <el-tag>jwt</el-tag> 
                                <el-tag>MyBatis</el-tag> 
                                <el-tag>Mysql</el-tag> 
                            </div>
                        </el-collapse-item>
                        <el-collapse-item title="功能介绍" name="3">
                            <div>权限管理：包含菜单管理、用户管理、角色管理、部门管理、用户权限分配。</div>
                            <div>考试管理：包含科目管理、试题管理、试卷管理、考试管理、试卷批改。</div>
                            <div>我的考试：包含在线考试、我的成绩。</div>
                            <div>题型支持：目前暂时支持单选题、判断题、简答题。</div>
                            <div>自动批改：对于单选题和判断题，系统会自动进行批改，简答题需要人工批改。</div>
                        </el-collapse-item>
                    </el-collapse>
                </div>
            </el-card>
        </div>
        <div style="float:left;width:550px;margin-left: 20px;">
            <el-card class="box-card">
                <div class="console">
                    <el-collapse v-model="person">
                        <el-collapse-item title="开发者信息" name="5">
                            一名在三线城市混迹的程序员。如果有网站或应用系统开发需求或者毕业设计需求，可以加我微信。
                        <div>
                            <el-image 
                            style="width:100px;height:100px;" 
                            src="../../../static/images/mine.jpg">
                            </el-image>
                        </div>
                        </el-collapse-item>
                        <el-collapse-item title="个人博客" name="6">
                            <a href="https://xuezhabiji.com" target="_blank">
                                https://xuezhabiji.com
                            </a>
                        </el-collapse-item>
                        <el-collapse-item title="个人公众号及面试题小程序" name="7">
                            <el-image 
                            style="width:100px;height:100px;" 
                            src="../../../static/images/gongzhonghao.jpg">
                            </el-image>
                            <el-image 
                            style="width:100px;height:100px;" 
                            src="../../../static/images/xiaochengxu.jpg">
                            </el-image>
                        </el-collapse-item>
                    </el-collapse>
                </div>
            </el-card>
        </div>
    </div>
</template>
<script>
export default {
    data() {
        return {
            activeNames: ['1', '2', '3'],
            person: ['5','6','7']
        };
    },
    methods: {
    }
}
</script>
<style>
.console .el-collapse-item__header {
    font-size: 16px;
    font-weight: bold;
}

.console .el-collapse-item__content {
    font-size: 15px;
    line-height: 35px;
}

</style>