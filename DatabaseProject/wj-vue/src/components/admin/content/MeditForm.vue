<template>
  <div style="text-align: left">
    <el-button class="add-button" type="success" @click="dialogFormVisible = true">添加电影</el-button>
    <el-dialog
      title="添加/修改电影"
      :visible.sync="dialogFormVisible"
      @close="clear">
      <el-form v-model="form" style="text-align: left" ref="dataForm">
        <el-form-item label="电影名" :label-width="formLabelWidth" prop="title">
          <el-input v-model="form.title" autocomplete="off" placeholder="不加《》"></el-input>
        </el-form-item>
        <el-form-item label="导演" :label-width="formLabelWidth" prop="director">
          <el-input v-model="form.director" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="上映日期" :label-width="formLabelWidth" prop="date">
          <el-input v-model="form.date" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="评分" :label-width="formLabelWidth" prop="grade">
          <el-input v-model="form.grade" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="封面" :label-width="formLabelWidth" prop="cover">
          <el-input v-model="form.cover" autocomplete="off" placeholder="图片 URL"></el-input>
          <img-upload @onUpload="uploadImg" ref="imgUpload"></img-upload>
        </el-form-item>
        <el-form-item label="简介" :label-width="formLabelWidth" prop="abs">
          <el-input type="textarea" v-model="form.abs" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="分类" :label-width="formLabelWidth" prop="cid">
        <el-select v-model="form.mcategory.id" placeholder="请选择分类">
          <el-option label="科幻" value="1"></el-option>
          <el-option label="剧情" value="2"></el-option>
          <el-option label="犯罪" value="3"></el-option>
          <el-option label="喜剧" value="4"></el-option>
          <el-option label="冒险" value="5"></el-option>
          <el-option label="惊悚" value="6"></el-option>
          <el-option label="战争" value="7"></el-option>
          <el-option label="爱情" value="8"></el-option>
        </el-select>
        </el-form-item>
        <el-form-item prop="id" style="height: 0">
          <el-input type="hidden" v-model="form.id" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="onSubmit">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import ImgUpload from './ImgUpload'
  export default {
    name: 'MeditForm',
    components: {ImgUpload},
    data () {
      return {
        dialogFormVisible: false,
        form: {
          id: '',
          title: '',
          director: '',
          date: '',
          grade: '',
          cover: '',
          abs: '',
          cid: '',
          mcategory: {
            id: '',
            name: ''
          }
        },
        formLabelWidth: '120px'
      }
    },
    methods: {
      clear () {
        this.$refs.imgUpload.clear()
        this.form = {
          id: '',
          title: '',
          director: '',
          date: '',
          grade: '',
          cover: '',
          abs: '',
          mcategory: {
            id: '',
            name: ''
          }
        }
      },
      onSubmit () {
        this.$axios
          .post('/admin/content/movies', {
            id: this.form.id,
            cover: this.form.cover,
            title: this.form.title,
            director: this.form.director,
            date: this.form.date,
            grade: this.form.grade,
            abs: this.form.abs,
            mcategory: this.form.mcategory
          }).then(resp => {
            if (resp && resp.data.code === 200) {
              this.dialogFormVisible = false
              this.$emit('onSubmit')
            }
        })
      },
      uploadImg () {
        this.form.cover = this.$refs.imgUpload.url
      }
    }
  }
</script>

<style scoped>
  .add-button {
    margin: 18px 0 0 10px;
  }
</style>
