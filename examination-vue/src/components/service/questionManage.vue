<template>
    <div>
        <el-card class="box-card" shadow="never">
            <el-button size="mini" @click="add">添加</el-button>
        </el-card>
        <el-table :data="tableData" border style="width: 100%;margin-top: 20px;"
            :header-cell-style="{ background: 'rgb(242, 243, 244)', color: '#515a6e' }">
            <el-table-column fixed prop="id" label="ID" v-if="false">
            </el-table-column>
            <el-table-column prop="content" label="题干">
            </el-table-column>
            <el-table-column prop="type" label="题型">
            </el-table-column>
            <el-table-column prop="subjectName" label="所属科目">
            </el-table-column>
            <el-table-column :formatter="dateFormat" prop="createtime" label="创建时间">
            </el-table-column>
            <el-table-column fixed="right" label="操作">
                <template slot-scope="scope">
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
                <el-form :rules="rules" :model="question" ref="question">
                    <el-form-item style="display:none;" label="id" label-width="100px">
                        <el-input v-model="question.id"></el-input>
                    </el-form-item>
                    <el-form-item label="题型：" label-width="100px" prop="type">
                        <el-select v-model="question.type" placeholder="请选择题目类型" @change="changeType">
                            <el-option v-for="item in quesTypeList" :value="item.code" :label="item.name"
                                :key="item.code">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="所属科目：" label-width="100px" prop="subject">
                        <el-select v-model="question.subject" placeholder="请选择所属科目">
                            <el-option v-for="item in subjectList" :value="item.id" :label="item.name" :key="item.id">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="题干：" label-width="100px" prop="content">
                        <el-input v-model="question.content" type="textarea" :rows="5" placeholder="请输入题干">
                        </el-input>
                    </el-form-item>
                    <el-form-item label="图片：" label-width="100px" prop="imgurl">
                        <el-upload v-model="question.imgurl" class="upload-demo" :action="uploadImageUrl"
                            :file-list="fileList" :headers="getHeader()" :show-file-list="false"
                            :on-success="fileSuccess" :on-remove="fileRemove">
                            <el-button size="small" type="primary">点击上传</el-button>
                        </el-upload>
                        <img v-if="imgViewUrl" :src="imgViewUrl" style="width:100px;height:100px;" />
                    </el-form-item>

                    <el-form-item v-if="xuanzeFlag" label="A选项：" label-width="100px" prop="a">
                        <el-input v-model="question.a" placeholder="请输入A选项">
                        </el-input>
                    </el-form-item>
                    <el-form-item v-if="xuanzeFlag" label="B选项：" label-width="100px" prop="b">
                        <el-input v-model="question.b" placeholder="请输入B选项">
                        </el-input>
                    </el-form-item>
                    <el-form-item v-if="xuanzeFlag" label="C选项：" label-width="100px" prop="c">
                        <el-input v-model="question.c" placeholder="请输入C选项">
                        </el-input>
                    </el-form-item>
                    <el-form-item v-if="xuanzeFlag" label="D选项：" label-width="100px" prop="d">
                        <el-input v-model="question.d" placeholder="请输入D选项">
                        </el-input>
                    </el-form-item>
                    <el-form-item v-if="xuanzeFlag" label="答案：" label-width="100px" prop="answer">
                        <el-radio v-model="question.answer" label="A">A</el-radio>
                        <el-radio v-model="question.answer" label="B">B</el-radio>
                        <el-radio v-model="question.answer" label="C">C</el-radio>
                        <el-radio v-model="question.answer" label="D">D</el-radio>
                    </el-form-item>
                    <el-form-item v-if="panduanFlag" label="答案：" label-width="100px" prop="answer">
                        <el-radio v-model="question.answer" label="对">对</el-radio>
                        <el-radio v-model="question.answer" label="错">错</el-radio>
                    </el-form-item>
                    <el-form-item v-if="jiandaFlag" label="答案：" label-width="100px" prop="answer">
                        <el-input v-model="question.answer" type="textarea" :rows="5" placeholder="请输入答案">
                        </el-input>
                    </el-form-item>
                    <el-form-item label="解析：" label-width="100px" prop="analysis">
                        <el-input v-model="question.analysis" type="textarea" :rows="5" placeholder="请输入解析">
                        </el-input>
                    </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="dialogFormVisible = false">取 消</el-button>
                    <el-button type="primary" @click="submit('question')">确 定
                    </el-button>
                </div>
            </el-dialog>
        </div>
    </div>
</template>
  
<script>
import formatDate from '../../utils/dateUtil.js';
export default {
    methods: {
        getSubject() {
            const that = this;
            this.$http
                .get('/examinationManage/getSubjectAll')
                .then(function (response) {
                    if (response.data.code == 200) {
                        that.subjectList = response.data.data;
                    }
                })
        },
        changeType(val) {
            if (val == "单选题") {
                this.xuanzeFlag = true;
                this.panduanFlag = false;
                this.jiandaFlag = false;
            } else if (val == "判断题") {
                this.xuanzeFlag = false;
                this.panduanFlag = true;
                this.jiandaFlag = false;
            } else if (val == "简答题") {
                this.xuanzeFlag = false;
                this.panduanFlag = false;
                this.jiandaFlag = true;
            }
        },
        handleSizeChange(val) {
            this.pageSize = val;
            this.getDataPage(this.currentPage, this.pageSize);
        },
        handleCurrentChange(val) {
            this.currentPage = val;
            this.getDataPage(this.currentPage, this.pageSize);
        },
        add() {
            this.title = "添加试题";
            this.xuanzeFlag = false;
            this.panduanFlag = false;
            this.jiandaFlag = false;
            this.question.id = "";
            this.question.type = "";
            this.question.subject = "";
            this.question.content = "";
            this.question.a = "";
            this.question.b = "";
            this.question.c = "";
            this.question.d = "";
            this.question.imgurl = "";
            this.question.analysis = "";
            this.question.answer = "";
            this.imgViewUrl = "";
            this.dialogFormVisible = true;
        },
        update(row) {
            this.question.id = row.id;
            this.question.type = row.type;
            this.question.subject = row.subject;
            this.question.content = row.content;
            this.question.a = row.a;
            this.question.b = row.b;
            this.question.c = row.c;
            this.question.d = row.d;
            this.question.imgurl = row.imgurl;
            this.question.analysis = row.analysis;
            this.question.answer = row.answer;
            if (this.question.imgurl != null && this.question.imgurl != "") {
                this.imgViewUrl = this.$http.defaults.baseURL + "/file/" + this.question.imgurl;
            } else {
                this.imgViewUrl = "";
            }
            this.title = "修改试题";
            this.dialogFormVisible = true;
            this.changeType(this.question.type);
        },
        del(row) {
            const that = this;
            that.$confirm('是否删除该试题?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                var curId = row.id;
                that.$http
                    .post('/examinationManage/deleteQuestion', {
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
                    if (that.title == "添加试题") {
                        that.$http
                            .post('/examinationManage/addQuestion', that.question)
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
                    } else if (this.title == "修改试题") {
                        this.$http
                            .post('/examinationManage/updateQuestion', that.question)
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
                .get('/examinationManage/getQuestionPage', {
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
        getHeader() {
            var authorization = "";
            var user = localStorage.getItem("user");
            if (user != null && user != "") {
                var userObj = JSON.parse(user);
                var token = userObj.token;
                authorization = token;
            }
            return { Authorization: authorization };;
        },
        fileSuccess(response, file, fileList) {
            this.question.imgurl = response.data;
            this.imgViewUrl = this.$http.defaults.baseURL + "/file/" + this.question.imgurl;
        },
        fileRemove(file, fileList) {
            this.question.imgurl = "";
        }
    },
    mounted() {
        this.getSubject();
        this.getDataPage(this.currentPage, this.pageSize);
    },
    data() {
        return {
            question: {
                id: "",
                type: "",
                subject: "",
                content: "",
                a: "",
                b: "",
                c: "",
                d: "",
                imgurl: "",
                analysis: "",
                answer: ""
            },
            quesTypeList: [
                {
                    "name": "单选题",
                    "code": "单选题"
                }, {
                    "name": "判断题",
                    "code": "判断题"
                }, {
                    "name": "简答题",
                    "code": "简答题"
                }
            ],
            subjectList: [],
            xuanzeFlag: false,
            panduanFlag: false,
            jiandaFlag: false,
            title: "",
            dialogFormVisible: false,
            total: 0,
            pageSize: 5,
            currentPage: 1,
            tableData: [],
            fileList: [],
            uploadImageUrl: this.$http.defaults.baseURL + "/image/upload",
            imgViewUrl: "",
            rules: {
                content: [
                    { required: true, message: '题干不能为空', trigger: 'blur' },
                    { max: 1000, message: "题干长度不能超过1000个字符", trigger: "blur" }
                ],
                type: [
                    { required: true, message: '题型不能为空', trigger: 'blur' },
                    { max: 20, message: "题型长度不能超过20个字符", trigger: "blur" }
                ],
                answer: [
                    { required: true, message: '答案不能为空', trigger: 'blur' },
                    { max: 1000, message: "答案长度不能超过1000个字符", trigger: "blur" }
                ]
            }
        }
    }
}
</script>
<style>
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