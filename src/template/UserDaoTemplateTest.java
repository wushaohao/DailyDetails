package template;

import This.Student;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDaoTemplateTest extends jdbcTemplate{

    public int addUser(Student student){
        String addSql="INSERT INTO USER VALUES (NAME,AGE,SEX)";

        Object[] params={student.getName(),25,1};

        return this.updateTemplate(addSql,params);
    }



     public List<Student> getStudentList(){
        List<Student> stuList=new ArrayList<>();
        final String sql="SELECT ID,NAME,AGE,SEX FROM USER";
         Object[] params=new Object[]{};

         stuList=this.queryForList(new RowMapper<Student>() {
             @Override
             public Student mappingRows(ResultSet rs, int rowNum) throws Exception {
                 Student student=new Student();
                 student.setName(rs.getString("userName"));
                 return student;
             }
         },sql,params);

         return stuList;
     }
}
