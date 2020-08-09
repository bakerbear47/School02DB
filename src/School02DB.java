import java.sql.*;
import java.util.Scanner;

import static java.sql.DriverManager.getConnection;

public class School02DB {
    public static void main(String[] args) {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:C:/Users/baker/Documents/SKOLE.db";
            conn = getConnection(url);

            Statement stmt = conn.createStatement();

            String sql;
            String sqlCourse;
            //sql = "SELECT * FROM SKOLE";

            //ResultSet rs = stmt.executeQuery(sql);
            System.out.println("Which student and course do you wish to find?");
            Scanner scanner = new Scanner(System.in);
            String Student = scanner.nextLine();
            String Course = scanner.nextLine();

            sql = "SELECT STUDENTS.FirstName AS G2, AVG(Grade) AS avg1 FROM teachersAndStudents LEFT JOIN STUDENTS ON teachersAndStudents.StudentID = STUDENTS.StudentID WHERE teachersAndStudents.StudentID LIKE "+Student+"";
                    // "SELECT AVG(Grade) AS avg1 FROM teachersAndStudents WHERE teachersAndStudents.CourseID LIKE "+Student+"";
                    //"SELECT AVG(Grade) AS avg1 FROM teachersAndStudents WHERE teachersAndStudents.StudentID LIKE "+Student+"";
            sqlCourse = "SELECT COURSES.CourseName AS G22, COURSES.CourseYear AS CY, AVG(Grade) AS avg11 FROM teachersAndStudents LEFT JOIN COURSES ON teachersAndStudents.CourseID = COURSES.CourseID WHERE teachersAndStudents.CourseID LIKE "+Course+"";
                    /*"AVG(teachersAndStudents.Grade) AS avg1 " +
                    "FROM STUDENTS INNER JOIN teachersAndStudents ON STUDENTS.StudentID=teachersAndStudents.StudentID INNER JOIN COURSES ON teachersAndStudents.CourseID=COURSES.CourseID " +
                    "WHERE teachersAndStudents.CourseID LIKE "+Student+" GROUP BY CourseID";*/ //, AVG (GradeSD) AS avg1 FROM COURSES, STUDENTS WHERE CourseName LIKE "+Student+"";
            //"SELECT * AVG(STUDENTS.GradeSD) AS avgG1 FROM STUDENTS, COURSES  WHERE STUDENTS.GradeSD=COURSES.CourseName GROUP BY STUDENT.StudentID"
            //INNER JOIN STUDENTS ON STUDENTS.StudentID=COURSES.CourseName
                    //STUDENTS.StudentID, STUDENTS.LastName, STUDENTS.Grade1, STUDENTS.Grade2, COURSES.CourseName
                    //"SELECT StudentID, Course1, Course2, AVG(Grade1) AS G1, AVG(Grade2) AS G2 FROM STUDENTS s WHERE Course1 OR Course2 LIKE '"+Student+"' GROUP BY Course1, Course2";

            ResultSet rs = stmt.executeQuery(sql);

            PresentStudents(rs);

            ResultSet rs2 = stmt.executeQuery(sqlCourse);
            PresentCourse(rs2);




        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    static public void PresentStudents(ResultSet res)
            throws SQLException {
        if (res == null)
            System.out.println("No records for student");
        while (res != null & res.next()) {



            /*String name = res.getString("FirstName");
            int StudentID = res.getInt("StudentID");
            int Grade1 = res.getInt("Grade1");
            int Grade2 = res.getInt("Grade2");*/
            float avgG1 = res.getFloat("avg1");
            String avgG2 = res.getString("G2");
            //float avgG2 = res.getFloat("G2");

            System.out.println(avgG1 +" "+ avgG2);

            /*float avgGrade = ((float)res.getInt("Grade1") + (float)res.getInt("Grade2"))/2;
            int avgGradeif1stnull = res.getInt("Grade2");
            if (Grade1 != 0){
            System.out.println(StudentID + " " + name +" " + Grade1 +" " + Grade2 + " AvgGrade " + avgGrade);}
            else{System.out.println(StudentID + " " + name +" " + Grade1 +" " + Grade2 + " AvgGrade " + avgGradeif1stnull);}*/
        }
    }


    static public void PresentCourse(ResultSet res)
            throws SQLException {
        if (res == null)
            System.out.println("No records for course");
        while (res != null & res.next()) {



            /*String name = res.getString("FirstName");
            int StudentID = res.getInt("StudentID");
            int Grade1 = res.getInt("Grade1");
            int Grade2 = res.getInt("Grade2");*/
            float avgG1 = res.getFloat("avg11");
            String avgG2 = res.getString("G22");
            int CY = res.getInt("CY");
            //float avgG2 = res.getFloat("G2");

            System.out.println(avgG1 +" "+ avgG2 +" "+ CY);

            /*float avgGrade = ((float)res.getInt("Grade1") + (float)res.getInt("Grade2"))/2;
            int avgGradeif1stnull = res.getInt("Grade2");
            if (Grade1 != 0){
            System.out.println(StudentID + " " + name +" " + Grade1 +" " + Grade2 + " AvgGrade " + avgGrade);}
            else{System.out.println(StudentID + " " + name +" " + Grade1 +" " + Grade2 + " AvgGrade " + avgGradeif1stnull);}*/
        }
    }

}