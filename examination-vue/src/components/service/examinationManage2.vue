<template>
    <div>
        <el-card class="box-card" shadow="never">
            <el-button size="mini" @click="add">添加</el-button>
        </el-card>
        <el-table :data="tableData" border style="width: 100%;margin-top: 20px;"
            :header-cell-style="{background: 'rgb(242, 243, 244)',color:'#515a6e'}">
            <el-table-column fixed prop="id" label="ID" v-if="false">
            </el-table-column>
            <el-table-column prop="name" label="考试名称">
            </el-table-column>
            <el-table-column prop="testPapername" label="考试试卷">
            </el-table-column>
            <el-table-column :formatter="typeFormat" prop="type" label="考试权限">
            </el-table-column>
            <el-table-column :formatter="dateFormat" prop="createtime" label="创建时间">
            </el-table-column>
            <el-table-column fixed="right" label="操作">
                <template slot-scope="scope">
                    <el-button size="mini" @click="report(scope.row)">成绩单</el-button>
                    <el-button size="mini" @click="update(scope.row)">编辑</el-button>
                    <el-button type="danger" size="mini" @click="del(scope.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <div style="margin-top:20px;float:right;">
            <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
                :page-sizes="[5, 10, 15, 20]" :page-size="pageSize" :current-page="currentPage" background
                layout="total, sizes, prev, pager, next, jumper" :total="total">
            </el-pagination>
        </div>
        <div class="dialogjz">
            <el-dialog :title="title" :visible.sync="dialogFormVisible">
                <el-form :rules="rules" :model="examination" ref="examination">
                    <el-form-item style="display:none;" label="id" label-width="100px" prop="id">
                        <el-input v-model="examination.id"></el-input>
                    </el-form-item>
                    <el-form-item label="考试名称：" label-width="100px" prop="name">
                        <el-input v-model="examination.name" clearable></el-input>
                    </el-form-item>
                    <el-form-item label="考试试卷：" label-width="100px" prop="testpaper">
                        <el-select v-model="examination.testpaper" placeholder="请选择试卷" @change="changeTestPaperData">
                            <el-option v-for="item in testPapaerList" :value="item.id" :label="item.name"
                                :key="item.id">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="科目：" label-width="100px" prop="subjectName">
                        <el-input :disabled="true" v-model="examination.testPaperData.subjectName"></el-input>
                    </el-form-item>
                    <el-form-item label="考试时间：" label-width="100px" prop="time">
                        <el-input :disabled="true" v-model="examination.testPaperData.time"></el-input>
                    </el-form-item>
                    <el-form-item label="总分：" label-width="100px" prop="score">
                        <el-input :disabled="true" v-model="examination.testPaperData.score"></el-input>
                    </el-form-item>
                    <el-form-item label="合格分数：" label-width="100px" prop="qualifiedscore">
                        <el-input-number v-model="examination.qualifiedscore"></el-input-number>
                    </el-form-item>
                    <el-form-item label="考试权限：" label-width="100px" prop="type">
                        <el-radio v-model="examination.type" label="0">完全开放</el-radio>
                        <el-radio v-model="examination.type" label="1">口令考试</el-radio>
                    </el-form-item>
                    <el-form-item v-if="examination.type=='1'" label="口令：" label-width="100px" prop="password">
                        <el-input v-model="examination.password"></el-input>
                    </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="dialogFormVisible = false">取 消</el-button>
                    <el-button type="primary" @click="submit('examination')">确 定
                    </el-button>
                </div>
            </el-dialog>
        </div>
        <div class="dialogjz">
            <el-dialog :visible.sync="dialogFormVisibleReport">
                <el-table :data="tableDataReport" border style="width: 100%;margin-top: 20px;" :cell-style="cellStyle"
                    :header-cell-style="{background: 'rgb(242, 243, 244)',color:'#515a6e'}">
                    <el-table-column fixed prop="id" label="ID" v-if="false">
                    </el-table-column>
                    <el-table-column prop="name" label="考试名称">
                    </el-table-column>
                    <el-table-column prop="subjectName" label="考试科目">
                    </el-table-column>
                    <el-table-column prop="score" label="总分">
                    </el-table-column>
                    <el-table-column prop="qualifiedscore" label="合格分数">
                    </el-table-column>
                    <el-table-column prop="username" label="考试人">
                    </el-table-column>
                    <el-table-column prop="myScore" label="考试分数">
                    </el-table-column>
                    <el-table-column prop="pass" label="是否合格">
                    </el-table-column>
                    <el-table-column :formatter="dateFormat" prop="time" label="考试时间">
                    </el-table-column>
                </el-table>
                <div style="margin-top:20px;float:right;">
                    <el-pagination @size-change="handleSizeChangeReport" @current-change="handleCurrentChangeReport"
                        :page-sizes="[5, 10, 15, 20]" :page-size="pageSizeReport" :current-page="currentPageReport"
                        background layout="total, sizes, prev, pager, next, jumper" :total="totalReport">
                    </el-pagination>
                </div>
            </el-dialog>
        </div>
    </div>
</template>
  
<script>
import formatDate from '../../utils/dateUtil.js';
export default {
    methods: {
        handleSizeChange(val) {
            this.pageSize = val;
            this.getDataPage(this.currentPage, this.pageSize);
        },
        handleCurrentChange(val) {
            this.currentPage = val;
            this.getDataPage(this.currentPage, this.pageSize);
        },
        handleSizeChangeReport(val) {
            this.pageSizeReport = val;
            this.getDataPage(this.currentPageReport, this.pageSizeReport);
        },
        handleCurrentChangeReport(val) {
            this.currentPageReport = val;
            this.getDataPage(this.currentPageReport, this.pageSizeReport);
        },
        add() {
            this.title = "添加考试";
            this.examination.id = "";
            this.examination.name = "";
            this.examination.type = "0";
            this.examination.testpaper = "";
            this.examination.password = "";
            this.examination.qualifiedscore = "0";
            this.examination.testPaperData = {};
            this.dialogFormVisible = true;
        },
        report(row) {
            this.getDataPageReport(1, this.pageSizeReport, row.id);
            this.dialogFormVisibleReport = true;
        },
        cellStyle(row, column, rowIndex, columnIndex) {
            if (row.column.label === "是否合格") {
                return 'color:' + row.row.color;
            }
        },
        getDataPageReport(currentPage, pageSize, id) {
            const that = this;
            var param = new URLSearchParams();
            param.append("page", currentPage);
            param.append("limit", pageSize);
            param.append("id", id);
            that.$http
                .get('/examinationManage/getReportByExaminationIdPage', {
                    params: param
                })
                .then(function (response) {
                    if (response.data.code == 200) {
                        that.tableDataReport = response.data.data;
                        that.totalReport = response.data.count;
                    }
                })
        },
        update(row) {
            this.examination.id = row.id;
            this.examination.name = row.name;
            this.examination.type = row.type + "";
            this.examination.testpaper = row.testpaper;
            this.examination.password = row.password;
            this.examination.qualifiedscore = row.qualifiedscore;
            this.examination.testPaperData = row.testPaperData;
            this.changeTestPaperData(this.examination.testpaper);
            this.title = "修改考试";
            this.dialogFormVisible = true;
        },
        del(row) {
            const that = this;
            that.$confirm('是否删除该考试?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                var curId = row.id;
                that.$http
                    .post('/examinationManage/deleteExamination', {
                        "id": curId
                    })
                    .then(function (response) {
                        if (response.data.code == 200) {
                            that.$message({
                                showClose: true,
                                type: 'success',
                                message: '操作成功'
                            });
                            that.currentPage = 1;
                            that.getDataPage(1, that.pageSize);
                        }
                    })
            });
        },
        submit(formName) {
            this.$refs[formName].validate((valid) => {
                if (!valid) {
                    return false;
                } else {
                    const that = this;
                    if (that.title == "添加考试") {
                        that.$http
                            .post('/examinationManage/addExamination', that.examination)
                            .then(function (response) {
                                if (response.data.code == 200) {
                                    that.$message({
                                        showClose: true,
                                        type: 'success',
                                        message: '操作成功'
                                    });
                                    that.getDataPage(that.currentPage, that.pageSize);
                                    that.dialogFormVisible = false;
                                }
                            })
                    } else if (this.title == "修改考试") {
                        this.$http
                            .post('/examinationManage/updateExamination', that.examination)
                            .then(function (response) {
                                if (response.data.code == 200) {
                                    that.$message({
                                        showClose: true,
                                        type: 'success',
                                        message: '操作成功'
                                    });
                                    that.getDataPage(that.currentPage, that.pageSize);
                                    that.dialogFormVisible = false;
                                }
                            })
                    }
                }
            });
        },
        getDataPage(currentPage, pageSize) {
            const that = this;
            var param = new URLSearchParams();
            param.append("page", currentPage);
            param.append("limit", pageSize);
            this.$http
                .get('/examinationManage/getExaminationPage', {
                    params: param
                })
                .then(function (response) {
                    if (response.data.code == 200) {
                        that.tableData = response.data.data;
                        that.total = response.data.count;
                    }
                })
        },
        //时间格式化 
        dateFormat(row, column) {
            let date = new Date(row[column.property]);
            return formatDate(date, 'yyyy-MM-dd hh:mm:ss');
        },
        typeFormat(row, column) {
            let tp = row[column.property];
            if (tp == "1") {
                tp = "口令考试";
            } else if (tp == "0") {
                tp = "完全开放";
            }
            return tp;
        },
        getTestPaperList() {
            const that = this;
            that.$http
                .get('/examinationManage/getTestPagerAll')
                .then(function (response) {
                    if (response.data.code == 200) {
                        that.testPapaerList = response.data.data;
                    }
                })
        },
        changeTestPaperData(val) {
            const that = this;
            var param = new URLSearchParams();
            param.append("id", val);
            that.$http
                .get('/examinationManage/getTestPagerById', {
                    params: param
                })
                .then(function (response) {
                    if (response.data.code == 200) {
                        that.examination.testPaperData = response.data.data;
                    }
                })
        }
    },
    mounted() {
        this.getDataPage(this.currentPage, this.pageSize);
        this.getTestPaperList();
    },
    data() {
        return {
            examination: {
                id: "",
                name: "",
                type: "",
                testpaper: "",
                testPapername: "",
                qualifiedscore: "",
                password: "",
                testPaperData: {}
            },
            title: "",
            dialogFormVisible: false,
            total: 0,
            pageSize: 5,
            currentPage: 1,
            tableData: [],
            testPapaerList: [],
            rules: {
                name: [
                    { required: true, message: '考试名称不能为空', trigger: 'blur' },
                    { max: 20, message: "考试名称长度不能超过20个字符", trigger: "blur" }
                ],
                testpaper: [
                    { required: true, message: '考试试卷不能为空', trigger: 'blur' }
                ]
            },
            tableDataReport: [],
            dialogFormVisibleReport: false,
            totalReport: 0,
            pageSizeReport: 5,
            currentPageReport: 1
        }
    }
}
</script>
<style >
.dialogjz .el-dialog {
    display: flex;
    display: -ms-flex;
    /* 兼容IE */
    flex-direction: column;
    -ms-flex-direction: column;
    /* 兼容IE */
    margin: 0 !important;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    max-height: calc(100% - 30px);
    max-width: calc(100% - 30px);
}

.dialogjz .el-dialog .el-dialog__body {
    max-height: 100%;
    flex: 1;
    -ms-flex: 1 1 auto;
    /* 兼容IE */
    overflow-y: auto;
    overflow-x: hidden;
}

.dialogjz .el-dialog__wrapper {
    /*隐藏ie和edge中遮罩的滚动条*/
    overflow: hidden;
}
</style>