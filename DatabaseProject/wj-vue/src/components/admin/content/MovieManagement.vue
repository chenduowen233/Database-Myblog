<template>
  <div>
    <el-row style="margin: 18px 0px 0px 18px ">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/admin/dashboard' }">管理中心</el-breadcrumb-item>
        <el-breadcrumb-item>内容管理</el-breadcrumb-item>
        <el-breadcrumb-item>电影管理</el-breadcrumb-item>
      </el-breadcrumb>
    </el-row>
    <medit-form @onSubmit="loadMovies()" ref="edit"></medit-form>
    <el-card style="margin: 18px 2%;width: 95%">
      <el-table
        :data="movies"
        stripe
        style="width: 100%"
        :max-height="tableHeight">
        <el-table-column
          type="selection"
          width="55">
        </el-table-column>
        <el-table-column type="expand">
          <template slot-scope="props">
            <el-form label-position="left" inline>
              <el-form-item>
                <span>{{ props.row.abs }}</span>
              </el-form-item>
            </el-form>
          </template>
        </el-table-column>
        <el-table-column
          prop="title"
          label="电影名（展开查看摘要）"
          fit>
        </el-table-column>
        <el-table-column
          prop="mcategory.name"
          label="分类"
          width="100">
        </el-table-column>
        <el-table-column
          prop="director"
          label="导演"
          fit>
        </el-table-column>
        <el-table-column
          prop="date"
          label="上映日期"
          width="120">
        </el-table-column>
        <el-table-column
          prop="grade"
          label="评分"
          fit>
        </el-table-column>
        <!--<el-table-column-->
        <!--prop="abs"-->
        <!--label="摘要"-->
        <!--show-overflow-tooltip-->
        <!--fit>-->
        <!--</el-table-column>-->
        <el-table-column
          fixed="right"
          label="操作"
          width="120">
          <template slot-scope="scope">
            <el-button
              @click.native.prevent="editMovie(scope.row)"
              type="text"
              size="small">
              编辑
            </el-button>
            <el-button
              @click.native.prevent="deleteMovie(scope.row.id)"
              type="text"
              size="small">
              移除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <div style="margin: 20px 0 20px 0;float: left">
        <el-button>取消选择</el-button>
        <el-button>批量删除</el-button>
      </div>
    </el-card>
  </div>
</template>

<script>
import MeditForm from './MeditForm'
export default {
  name: 'MovieManagement',
  components: {MeditForm},
  data () {
    return {
      movies: []
    }
  },
  mounted () {
    this.loadMovies()
  },
  computed: {
    tableHeight () {
      return window.innerHeight - 320
    }
  },
  methods: {
    deleteMovie (id) {
      this.$confirm('此操作将永久删除该电影, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
          this.$axios
            .post('/admin/content/movies/delete', {id: id}).then(resp => {
            if (resp && resp.data.code === 200) {
              this.loadMovies()
            }
          })
        }
      ).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    },
    editMovie (item) {
      this.$refs.edit.dialogFormVisible = true
      this.$refs.edit.form = {
        id: item.id,
        cover: item.cover,
        title: item.title,
        director: item.director,
        date: item.date,
        grade: item.grade,
        abs: item.abs,
        mcategory: {
          id: item.mcategory.id.toString(),
          name: item.mcategory.name
        }
      }
      // this.$refs.edit.category = {
      //   id: item.category.id.toString()
      // }
    },
    loadMovies () {
      var _this = this
      this.$axios.get('/movies').then(resp => {
        if (resp && resp.data.code === 200) {
          _this.movies = resp.data.result
        }
      })
    }
  }
}
</script>

<style scoped>
</style>
