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
                            由于项目咨询的人过多,实在分身乏术，因此决定将提供有偿咨询服务。如果您在安装或者开发学习中遇到困难，可以联系本人提供有偿服务。
                            <div>
                                <el-image style="width:100px;height:100px;" src="../../../static/images/mine.jpg">
                                </el-image>
                            </div>
                            服务类目如下：<br>

                            1.如果项目在运行过程中出现问题无法处理，提供技术服务，帮你查找问题，保证项目运行。<br>

                            2.如果你需要对项目进行一些修改，或者开发一些新功能，提供开发服务。<br>

                            3.如果你需要开源项目的论文，提供论文代写，包查重。<br>

                            <b style="color:blue;">4.也接定制开发和论文哦，价格便宜。</b><br>
                        </el-collapse-item>
                        <el-collapse-item title="个人公众号" name="7">
                            如果本项目不适合你，没关系。关注本人公众号，里面还有更多开源项目等着你。
                            <div>
                                <el-image style="width:100px;height:100px;" src="../../../static/images/gongzhonghao.jpg">
                                </el-image>
                            </div>
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
            person: ['5', '6', '7']
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