import java.sql.*;
import static org.junit.Assert.*;
import org.junit.Test;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

class check {
    Connection c;
    Statement stmt;
    private void executeUpdate(String sql) throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+test.entry_no, "username", "password");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        stmt = c.createStatement();
        stmt.executeUpdate(sql);
        stmt.close();
        c.close();
    }

    private ResultSet executeQuery(String sql)  throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+test.entry_no, "username", "password");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery(sql);;
        return rs;
    }

    public void initializeDB() throws SQLException {
        executeUpdate("""
        INSERT INTO department (dept_id, dept_name) VALUES ('CSE', 'Computer Science and Engineering');
        INSERT INTO department (dept_id, dept_name) VALUES ('MTL', 'Mathematics and Computing');
        INSERT INTO department (dept_id, dept_name) VALUES ('ELL', 'Electrical Engineering');
        INSERT INTO department (dept_id, dept_name) VALUES ('CVL', 'Civil Engineering');
        INSERT INTO department (dept_id, dept_name) VALUES ('PHY', 'Physics');

        INSERT INTO valid_entry (dept_id, entry_year, seq_number) VALUES ('CSE', 2019, 1);
        INSERT INTO valid_entry (dept_id, entry_year, seq_number) VALUES ('MTL', 2020, 2);
        INSERT INTO valid_entry (dept_id, entry_year, seq_number) VALUES ('ELL', 2024, 3);

        INSERT INTO professor (professor_id, professor_first_name, professor_last_name, office_number, contact_number, start_year, resign_year, dept_id) VALUES ('PROF000001', 'PROF001', 'PROF001', 'SIT001', '0000000001', 2015, 2025, 'CSE');
        INSERT INTO professor (professor_id, professor_first_name, professor_last_name, office_number, contact_number, start_year, resign_year, dept_id) VALUES ('PROF000002', 'PROF002', 'PROF002', 'SIT002', '0000000002', 2015, NULL, 'CSE');
        INSERT INTO professor (professor_id, professor_first_name, professor_last_name, office_number, contact_number, start_year, resign_year, dept_id) VALUES ('PROF000003', 'PROF003', 'PROF003', 'SIT003', '0000000003', 2015, NULL, 'CSE');
        INSERT INTO professor (professor_id, professor_first_name, professor_last_name, office_number, contact_number, start_year, resign_year, dept_id) VALUES ('PROF000004', 'PROF004', 'PROF004', 'SIT004', '0000000004', 2015, NULL, 'CSE');
        INSERT INTO professor (professor_id, professor_first_name, professor_last_name, office_number, contact_number, start_year, resign_year, dept_id) VALUES ('PROF000005', 'PROF005', 'PROF005', 'SIT005', '0000000005', 2015, NULL, 'CSE');
        INSERT INTO professor (professor_id, professor_first_name, professor_last_name, office_number, contact_number, start_year, resign_year, dept_id) VALUES ('PROF000006', 'PROF006', 'PROF006', 'SIT006', '0000000006', 2015, NULL, 'MTL');
        INSERT INTO professor (professor_id, professor_first_name, professor_last_name, office_number, contact_number, start_year, resign_year, dept_id) VALUES ('PROF000007', 'PROF007', 'PROF007', 'SIT007', '0000000007', 2015, NULL, 'MTL');
        INSERT INTO professor (professor_id, professor_first_name, professor_last_name, office_number, contact_number, start_year, resign_year, dept_id) VALUES ('PROF000008', 'PROF008', 'PROF008', 'SIT008', '0000000008', 2015, NULL, 'ELL');
        INSERT INTO professor (professor_id, professor_first_name, professor_last_name, office_number, contact_number, start_year, resign_year, dept_id) VALUES ('PROF000009', 'PROF009', 'PROF009', 'SIT009', '0000000009', 2015, NULL, 'ELL');
        INSERT INTO professor (professor_id, professor_first_name, professor_last_name, office_number, contact_number, start_year, resign_year, dept_id) VALUES ('PROF000010', 'PROF010', 'PROF010', 'SIT010', '0000000010', 2016, NULL, 'CVL');
        INSERT INTO professor (professor_id, professor_first_name, professor_last_name, office_number, contact_number, start_year, resign_year, dept_id) VALUES ('PROF000011', 'PROF011', 'PROF011', 'SIT011', '0000000011', 2016, NULL, 'CVL');

        INSERT INTO courses (course_id, course_name, course_desc, credits, dept_id) VALUES ('CSE101', 'CSE101', NULL, 4, 'CSE');
        INSERT INTO courses (course_id, course_name, course_desc, credits, dept_id) VALUES ('CSE102', 'CSE102', NULL, 3, 'CSE');
        INSERT INTO courses (course_id, course_name, course_desc, credits, dept_id) VALUES ('CSE103', 'CSE103', NULL, 3, 'CSE');
        INSERT INTO courses (course_id, course_name, course_desc, credits, dept_id) VALUES ('CSE104', 'CSE104', NULL, 4, 'CSE');
        INSERT INTO courses (course_id, course_name, course_desc, credits, dept_id) VALUES ('CSE105', 'CSE105', NULL, 4, 'CSE');
        INSERT INTO courses (course_id, course_name, course_desc, credits, dept_id) VALUES ('CSE106', 'CSE106', NULL, 4, 'CSE');
        INSERT INTO courses (course_id, course_name, course_desc, credits, dept_id) VALUES ('CSE111', 'CSE111', NULL, 4, 'CSE');
        INSERT INTO courses (course_id, course_name, course_desc, credits, dept_id) VALUES ('CSE201', 'CSE201', NULL, 3, 'CSE');
        INSERT INTO courses (course_id, course_name, course_desc, credits, dept_id) VALUES ('CSE202', 'CSE202', NULL, 2, 'CSE');
        INSERT INTO courses (course_id, course_name, course_desc, credits, dept_id) VALUES ('MTL101', 'MTL101', NULL, 4, 'MTL');
        INSERT INTO courses (course_id, course_name, course_desc, credits, dept_id) VALUES ('ELL101', 'ELL101', NULL, 4, 'ELL');
        INSERT INTO courses (course_id, course_name, course_desc, credits, dept_id) VALUES ('CSE700', 'CSE700', NULL, 4, 'CSE');
        INSERT INTO courses (course_id, course_name, course_desc, credits, dept_id) VALUES ('CSE701', 'CSE701', NULL, 5, 'CSE');
        INSERT INTO courses (course_id, course_name, course_desc, credits, dept_id) VALUES ('CSE702', 'CSE702', NULL, 5, 'CSE');
        INSERT INTO courses (course_id, course_name, course_desc, credits, dept_id) VALUES ('CSE703', 'CSE703', NULL, 5, 'CSE');
        INSERT INTO courses (course_id, course_name, course_desc, credits, dept_id) VALUES ('CSE704', 'CSE704', NULL, 5, 'CSE');
        INSERT INTO courses (course_id, course_name, course_desc, credits, dept_id) VALUES ('CSE705', 'CSE705', NULL, 5, 'CSE');
        INSERT INTO courses (course_id, course_name, course_desc, credits, dept_id) VALUES ('CSE706', 'CSE706', NULL, 5, 'CSE');
        INSERT INTO courses (course_id, course_name, course_desc, credits, dept_id) VALUES ('CSE707', 'CSE707', NULL, 5, 'CSE');
        INSERT INTO courses (course_id, course_name, course_desc, credits, dept_id) VALUES ('CSE708', 'CSE708', NULL, 5, 'CSE');
        INSERT INTO courses (course_id, course_name, course_desc, credits, dept_id) VALUES ('CSE709', 'CSE709', NULL, 5, 'CSE');
        INSERT INTO courses (course_id, course_name, course_desc, credits, dept_id) VALUES ('CSE362', 'CSE362', NULL, 4, 'CSE');
        INSERT INTO courses (course_id, course_name, course_desc, credits, dept_id) VALUES ('CVL708', 'CVL708', NULL, 5, 'CVL');
        INSERT INTO courses (course_id, course_name, course_desc, credits, dept_id) VALUES ('CVL709', 'CVL709', NULL, 5, 'CVL');
        INSERT INTO courses (course_id, course_name, course_desc, credits, dept_id) VALUES ('CVL362', 'CVL362', NULL, 4, 'CVL');

        INSERT INTO student (first_name, last_name, student_id, address, contact_number, email_id, tot_credits, dept_id) VALUES ('STUDENT001', NULL, '2019CSE001', NULL, '0000000001', '2019CSE001@CSE.iitd.ac.in', 0, 'CSE');
        INSERT INTO student (first_name, last_name, student_id, address, contact_number, email_id, tot_credits, dept_id) VALUES ('STUDENT002', NULL, '2019CSE002', NULL, '0000000002', '2019CSE002@CSE.iitd.ac.in', 0, 'CSE');
        INSERT INTO student (first_name, last_name, student_id, address, contact_number, email_id, tot_credits, dept_id) VALUES ('STUDENT003', NULL, '2019CSE003', NULL, '0000000003', '2019CSE003@CSE.iitd.ac.in', 0, 'CSE');
        INSERT INTO student (first_name, last_name, student_id, address, contact_number, email_id, tot_credits, dept_id) VALUES ('STUDENT004', NULL, '2019CSE004', NULL, '0000000004', '2019CSE004@CSE.iitd.ac.in', 0, 'CSE');
        INSERT INTO student (first_name, last_name, student_id, address, contact_number, email_id, tot_credits, dept_id) VALUES ('STUDENT005', NULL, '2019CSE005', NULL, '0000000005', '2019CSE005@CSE.iitd.ac.in', 0, 'CSE');
        INSERT INTO student (first_name, last_name, student_id,address,contact_number, email_id, tot_credits, dept_id) VALUES('Raj','Kumari', '2024ELL003','Hauz Khas', '9080005679', '2024ELL003@ELL.iitd.ac.in',0,'ELL');
        INSERT INTO student (first_name, last_name, student_id,address,contact_number, email_id, tot_credits, dept_id) VALUES('Rani','B', '2024ELL004','Hauz Khas', '9084045679', '2024ELL004@ELL.iitd.ac.in',0,'ELL');

        INSERT INTO course_offers (course_id, session, semester, professor_id, capacity, enrollments) VALUES ('CSE101', '2023-2024', 2, 'PROF000001', 40, 0);
        INSERT INTO course_offers (course_id, session, semester, professor_id, capacity, enrollments) VALUES ('CSE101', '2023-2024', 1, 'PROF000001', 40, 0);
        INSERT INTO course_offers (course_id, session, semester, professor_id, capacity, enrollments) VALUES ('CSE101', '2020-2021', 1, 'PROF000001', 40, 0);
        INSERT INTO course_offers (course_id, session, semester, professor_id, capacity, enrollments) VALUES ('CSE101', '2022-2023', 2, 'PROF000005', 40, 0);
        INSERT INTO course_offers (course_id, session, semester, professor_id, capacity, enrollments) VALUES ('CSE102', '2023-2024', 2, 'PROF000001', 40, 0);
        INSERT INTO course_offers (course_id, session, semester, professor_id, capacity, enrollments) VALUES ('CSE103', '2023-2024', 2, 'PROF000003', 40, 0);
        INSERT INTO course_offers (course_id, session, semester, professor_id, capacity, enrollments) VALUES ('CSE104', '2023-2024', 2, 'PROF000003', 40, 0);
        INSERT INTO course_offers (course_id, session, semester, professor_id, capacity, enrollments) VALUES ('CSE105', '2023-2024', 2, 'PROF000004', 40, 0);
        INSERT INTO course_offers (course_id, session, semester, professor_id, capacity, enrollments) VALUES ('CSE105', '2024-2025', 1, 'PROF000003', 40, 0);
        INSERT INTO course_offers (course_id, session, semester, professor_id, capacity, enrollments) VALUES ('CSE106', '2023-2024', 2, 'PROF000004', 40, 0);
        INSERT INTO course_offers (course_id, session, semester, professor_id, capacity, enrollments) VALUES ('CSE106', '2024-2025', 1, 'PROF000003', 40, 0);
        INSERT INTO course_offers (course_id, session, semester, professor_id, capacity, enrollments) VALUES ('CSE111', '2023-2024', 1, 'PROF000004', 1, 0);
        INSERT INTO course_offers (course_id, session, semester, professor_id, capacity, enrollments) VALUES ('CSE111', '2023-2024', 2, 'PROF000004', 1, 0);
        INSERT INTO course_offers (course_id, session, semester, professor_id, capacity, enrollments) VALUES ('CSE201', '2023-2024', 2, 'PROF000001', 40, 0);
        INSERT INTO course_offers (course_id, session, semester, professor_id, capacity, enrollments) VALUES ('CSE202', '2023-2024', 2, 'PROF000005', 40, 0);
        INSERT INTO course_offers (course_id, session, semester, professor_id, capacity, enrollments) VALUES ('MTL101', '2023-2024', 2, 'PROF000002', 40, 0);
        INSERT INTO course_offers (course_id, session, semester, professor_id, capacity, enrollments) VALUES ('ELL101', '2023-2024', 2, 'PROF000002', 40, 0);
        INSERT INTO course_offers (course_id, session, semester, professor_id, capacity, enrollments) VALUES ('CSE700', '2024-2025', 1, 'PROF000001', 40, 0);
        INSERT INTO course_offers (course_id, session, semester, professor_id, capacity, enrollments) VALUES ('CSE701', '2019-2020', 1, 'PROF000001', 40, 0);
        INSERT INTO course_offers (course_id, session, semester, professor_id, capacity, enrollments) VALUES ('CSE701', '2018-2019', 2, 'PROF000001', 40, 0);
        INSERT INTO course_offers (course_id, session, semester, professor_id, capacity, enrollments) VALUES ('CSE701', '2020-2021', 1, 'PROF000001', 40, 0);
        INSERT INTO course_offers (course_id, session, semester, professor_id, capacity, enrollments) VALUES ('CSE702', '2019-2020', 1, 'PROF000001', 40, 0);
        INSERT INTO course_offers (course_id, session, semester, professor_id, capacity, enrollments) VALUES ('CSE703', '2019-2020', 1, 'PROF000001', 40, 0);
        INSERT INTO course_offers (course_id, session, semester, professor_id, capacity, enrollments) VALUES ('CSE704', '2019-2020', 1, 'PROF000001', 40, 0);
        INSERT INTO course_offers (course_id, session, semester, professor_id, capacity, enrollments) VALUES ('CSE705', '2019-2020', 1, 'PROF000002', 40, 0);
        INSERT INTO course_offers (course_id, session, semester, professor_id, capacity, enrollments) VALUES ('CSE706', '2019-2020', 2, 'PROF000002', 40, 0);
        INSERT INTO course_offers (course_id, session, semester, professor_id, capacity, enrollments) VALUES ('CSE707', '2019-2020', 2, 'PROF000002', 40, 0);
        INSERT INTO course_offers (course_id, session, semester, professor_id, capacity, enrollments) VALUES ('CSE708', '2019-2020', 2, 'PROF000002', 40, 0);
        INSERT INTO course_offers (course_id, session, semester, professor_id, capacity, enrollments) VALUES ('CSE709', '2019-2020', 2, 'PROF000005', 40, 0);
        INSERT INTO course_offers (course_id, session, semester, professor_id, capacity, enrollments) VALUES ('CVL708', '2019-2020', 2, 'PROF000010', 40, 0);
        INSERT INTO course_offers (course_id, session, semester, professor_id, capacity, enrollments) VALUES ('CVL709', '2019-2020', 2, 'PROF000010', 40, 0);
        INSERT INTO course_offers (course_id, session, semester, professor_id, capacity, enrollments) VALUES ('CVL362', '2019-2020', 2, 'PROF000011', 40, 0);
        
        INSERT INTO student_courses (student_id, course_id, session, semester, grade) VALUES ('2024ELL003', 'ELL101', '2023-2024', 2, 10);
        INSERT INTO student_courses (student_id, course_id, session, semester, grade) VALUES ('2024ELL004', 'ELL101', '2023-2024', 2, 8);
        

        """);

        
    }

    public void clearDB() throws SQLException {
        executeUpdate("""
        DO
        $do$
        BEGIN
        EXECUTE
        (SELECT 'TRUNCATE TABLE ' || string_agg(oid::regclass::text, ', ') || ' CASCADE'
            FROM   pg_class
            WHERE  relkind = 'r'  -- only tables
            AND    relnamespace = 'public'::regnamespace
        );
        END
        $do$;
        """);        
    }

    // department table
    @Test
    public void test_schema() throws SQLException{
        ResultSet rs = executeQuery("SELECT COUNT(*) FROM student;");
        assertTrue(rs.next());
        assertEquals(0, rs.getInt(1));
        rs = executeQuery("SELECT COUNT(*) FROM courses;");
        assertTrue(rs.next());
        assertEquals(0, rs.getInt(1));
        rs = executeQuery("SELECT COUNT(*) FROM student_courses;");
        assertTrue(rs.next());
        assertEquals(0, rs.getInt(1));
        rs = executeQuery("SELECT COUNT(*) FROM course_offers;");
        assertTrue(rs.next());
        assertEquals(0, rs.getInt(1));
        rs = executeQuery("SELECT COUNT(*) FROM professor;");
        assertTrue(rs.next());
        assertEquals(0, rs.getInt(1));
        rs = executeQuery("SELECT COUNT(*) FROM valid_entry;");
        assertTrue(rs.next());
        assertEquals(0, rs.getInt(1));
        rs = executeQuery("SELECT COUNT(*) FROM department;");
        assertTrue(rs.next());
        assertEquals(0, rs.getInt(1));
    }

    // department table
    @Test
    public void test_2_0_1() throws SQLException{
        try{
            executeUpdate("INSERT INTO department (dept_id, dept_name) VALUES ('CSY', 'Computer Science and Engineering');");
            fail("Exception not raised!");
        } catch (SQLException e) {
            // Do nothing
        }
    }

    @Test
    public void test_2_0_2() throws SQLException{
        try{
            executeUpdate("INSERT INTO department (dept_id, dept_name) VALUES ('MCL', NULL);");
            fail("Exception not raised!");
        } catch (SQLException e) {
            // Do nothing
        }
    }

    @Test
    public void test_2_0_3() throws SQLException{
        try{
            executeUpdate("INSERT INTO department (dept_id, dept_name) VALUES ('CSED', 'Computer Science');");
            fail("Exception not raised!");
        } catch (SQLException e) {
            // Do nothing
        }
    }

    // @Test
    // public void test_2_0_4() throws SQLException{
        
    //     executeUpdate("INSERT INTO department (dept_id, dept_name) VALUES ('PHY', 'Physics');");
    //     ResultSet rs = executeQuery("SELECT COUNT(*) FROM department WHERE dept_id='PHY' and dept_name='Physics';");
    //     assertTrue(rs.next());
    //     assertEquals(1, rs.getInt(1));
            
        
    // }


    //valid_entry table
    @Test
    public void test_2_0_4() throws SQLException{
        try{
            executeUpdate("INSERT INTO valid_entry VALUES('PHY',NULL, NULL);");
            fail("Exception not raised!");
        } catch (SQLException e) {
            // Do nothing
        }
    }

    @Test
    public void test_2_0_5() throws SQLException{
        try{
            executeUpdate("INSERT INTO valid_entry VALUES('PHY',NULL, 002);");
            fail("Exception not raised!");
        } catch (SQLException e) {
            // Do nothing
        }
    }

    @Test
    public void test_2_0_6() throws SQLException{
        try{
            executeUpdate("INSERT INTO valid_entry VALUES('CSY',2024, 002);");
            fail("Exception not raised!");
        } catch (SQLException e) {
            // Do nothing
        }
    }

    @Test
    public void test_2_0_7() throws SQLException{
        
        executeUpdate("INSERT INTO valid_entry VALUES('PHY',2024, 005);");
        ResultSet rs = executeQuery("SELECT COUNT(*) FROM valid_entry WHERE dept_id='PHY' and entry_year=2024 and seq_number=5;");
        assertTrue(rs.next());
        assertEquals(1, rs.getInt(1));
        
    }

    //courses table
    @Test
    public void test_2_0_8() throws SQLException{
        try{
            executeUpdate("INSERT INTO courses (course_id, course_name, course_desc, credits, dept_id) VALUES ('PHY101', 'CSE101', NULL, 4, 'PHY');");
            fail("Exception not raised!");
        } catch (SQLException e) {
            // Do nothing
        }
    }

    @Test
    public void test_2_0_9() throws SQLException{
        try{
            executeUpdate("INSERT INTO courses (course_id, course_name, course_desc, credits, dept_id) VALUES ('PHY101', 'PHY101', NULL, 0, 'PHY');");
            fail("Exception not raised!");
        } catch (SQLException e) {
            // Do nothing
        }
    }

    @Test
    public void test_2_0_10() throws SQLException{
        try{
            executeUpdate("INSERT INTO courses (course_id, course_name, course_desc, credits, dept_id) VALUES ('PHY101', 'PHY101', NULL, NULL, 'PHY');");
            fail("Exception not raised!");
        } catch (SQLException e) {
            // Do nothing
        }
    }

    @Test
    public void test_2_0_11() throws SQLException{
        try{
            executeUpdate("INSERT INTO courses (course_id, course_name, course_desc, credits, dept_id) VALUES ('PHY101', 'PHY101', NULL, NULL, 'PHS');");
            fail("Exception not raised!");
        } catch (SQLException e) {
            // Do nothing
        }
    }

    @Test
    public void test_2_0_12() throws SQLException{
        try{
            executeUpdate("INSERT INTO courses (course_id, course_name, course_desc, credits, dept_id) VALUES ('PHS101', 'PHY101', NULL, NULL, 'PHY');");
            fail("Exception not raised!");
        } catch (SQLException e) {
            // Do nothing
        }
    }

    @Test
    public void test_2_0_13() throws SQLException{
        
        executeUpdate("INSERT INTO courses (course_id, course_name, course_desc, credits, dept_id) VALUES ('PHY101', 'PHY101', NULL, 4, 'PHY');");
        ResultSet rs = executeQuery("SELECT COUNT(*) FROM courses WHERE dept_id='PHY' and course_id='PHY101' and credits=4;");
        assertTrue(rs.next());
        assertEquals(1, rs.getInt(1));
        
    }

    //professor table
    @Test
    public void test_2_0_14() throws SQLException{
        try{
            executeUpdate("INSERT INTO professor (professor_id, professor_first_name, professor_last_name, office_number, contact_number, start_year, resign_year, dept_id) VALUES ('PROF000012', 'PROF012', 'PROF012', 'SIT012', '0000000012', 2025, 2015, 'CSE');");
            fail("Exception not raised!");
        } catch (SQLException e) {
            // Do nothing
        }
    }

    @Test
    public void test_2_0_15() throws SQLException{
        try{
            executeUpdate("INSERT INTO professor (professor_id, professor_first_name, professor_last_name, office_number, contact_number, start_year, resign_year, dept_id) VALUES ('PROF000012', 'PROF012', NULL, 'SIT012', '0000000012', 2015, 2025, 'CSE');");
            fail("Exception not raised!");
        } catch (SQLException e) {
            // Do nothing
        }
    }

    @Test
    public void test_2_0_16() throws SQLException{
        try{
            executeUpdate("INSERT INTO professor (professor_id, professor_first_name, professor_last_name, office_number, contact_number, start_year, resign_year, dept_id) VALUES ('PROF000012', 'PROF012', 'PROF012', 'SIT012', NULL, 2015, 2025, 'CSE');");
            fail("Exception not raised!");
        } catch (SQLException e) {
            // Do nothing
        }
    }

    @Test
    public void test_2_0_17() throws SQLException{
        try{
            executeUpdate("INSERT INTO professor (professor_id, professor_first_name, professor_last_name, office_number, contact_number, start_year, resign_year, dept_id) VALUES ('PROF000012', 'PROF012', 'PROF012', 'SIT012', NULL, 2015, 2025, 'CSY');");
            fail("Exception not raised!");
        } catch (SQLException e) {
            // Do nothing
        }
    }

    //student_courses table
    @Test
    public void test_2_0_18() throws SQLException{
        
    try{
        executeUpdate("INSERT INTO student_courses (student_id, course_id, session, semester, grade) VALUES ('2024PHY005', 'ELL101', '2023-2024', 2, 10);");
        fail("Exception not raised!");
    } catch (SQLException e) {
        // Do nothing
    }
        
    }

    @Test
    public void test_2_0_19() throws SQLException{

        try{
            executeUpdate("INSERT INTO student_courses (student_id, course_id, session, semester, grade) VALUES ('2019CSE001', 'CSE101', '2023-2024', 3, 10);");
            fail("Exception not raised!");
        } catch (SQLException e) {
            // Do nothing
        }
        
    }

    @Test
    public void test_2_0_20() throws SQLException{
        

        try{
            executeUpdate("INSERT INTO student_courses (student_id, course_id, session, semester, grade) VALUES ('2019CSE001', 'CSE101', '2023-2024', 2, 11);");
            fail("Exception not raised!");
        } catch (SQLException e) {
            // Do nothing
        }
        
    }

    @Test
    public void test_2_0_21() throws SQLException{

        try{
            executeUpdate("INSERT INTO student_courses (student_id, course_id, session, semester, grade) VALUES ('2019CSE001', 'CSE101', '2023-2024', 2, -1);");
            fail("Exception not raised!");
        } catch (SQLException e) {
            // Do nothing
        }
        
    }

    @Test
    public void test_2_0_22() throws SQLException{

        try{
            executeUpdate("INSERT INTO student_courses (student_id, course_id, session, semester, grade) VALUES ('2019CSE001', 'COL101', '2023-2024', 2, 8);");
            fail("Exception not raised!");
        } catch (SQLException e) {
            // Do nothing
        }
        
    }

    @Test
    public void test_2_0_23() throws SQLException{

        try{
            executeUpdate("INSERT INTO student_courses (student_id, course_id, session, semester, grade) VALUES ('2019CSE001', 'COL101', '2023-2024', 2, NULL);");
            fail("Exception not raised!");
        } catch (SQLException e) {
            // Do nothing
        }
        
    }

    //course_offers table
    @Test
    public void test_2_0_24() throws SQLException{

        try{
            executeUpdate("INSERT INTO course_offers (course_id, session, semester, professor_id, capacity, enrollments) VALUES ('CSE101', '2019-2020', 3, 'PROF000011', 40, 0);");
            fail("Exception not raised!");
        } catch (SQLException e) {
            // Do nothing
        }
        
    }

    @Test
    public void test_2_0_25() throws SQLException{

        try{
            executeUpdate("INSERT INTO course_offers (course_id, session, semester, professor_id, capacity, enrollments) VALUES ('PHY362', '2019-2020', 2, 'PROF000011', 40, 0);");
            fail("Exception not raised!");
        } catch (SQLException e) {
            // Do nothing
        }
        
    }

    @Test
    public void test_2_0_26() throws SQLException{

        try{
            executeUpdate("INSERT INTO course_offers (course_id, session, semester, professor_id, capacity, enrollments) VALUES ('CSE101', '2019-2020', 2, 'PROF000001', 40, 0);");
            fail("Exception not raised!");
        } catch (SQLException e) {
            // Do nothing
        }
        
    }

    @Test
    public void test_2_0_27() throws SQLException{

        try{
            executeUpdate("INSERT INTO course_offers (course_id, session, semester, professor_id, capacity, enrollments) VALUES ('CSE101', '2019-2020', NULL, 'PROF000001', 40, 0);");
            fail("Exception not raised!");
        } catch (SQLException e) {
            // Do nothing
        }
        
    }

    
    

    //2.1 Q1
    @Test
    public void test_2_1_1_1() throws SQLException {
        executeUpdate("""
        INSERT INTO student (first_name, last_name, student_id,address,contact_number, email_id, tot_credits, dept_id) VALUES('Raj','Kumari', '2024ELL005','Hauz Khas', '9088005679', '2024ELL005@ELL.iitd.ac.in',0,'ELL');
        """);
        ResultSet rs = executeQuery("SELECT COUNT(*) FROM valid_entry WHERE dept_id='ELL' and entry_year=2024 and seq_number=6");
        assertTrue(rs.next());
        assertEquals(1, rs.getInt(1));
    }

    @Test
    public void test_2_1_1_2() throws SQLException{
        try{
            executeUpdate("INSERT INTO student (first_name, last_name, student_id,address,contact_number, email_id, tot_credits, dept_id) VALUES('Rani','Kulkarni', '2022CSE006','Hauz Khas', '9080005670', '2019CSE006@CSE.iitd.ac.in',0,'CSE');");
            fail("Exception not raised!");
        } catch (SQLException e) {
            // Do nothing
        }
    }

    @Test
    public void test_2_1_1_3() throws SQLException{
        try{
            executeUpdate("INSERT INTO student (first_name, last_name, student_id,address,contact_number, email_id, tot_credits, dept_id) VALUES('Rani','Kulkarni', '2019CSE005','Hauz Khas', '9080005670', '2019CSE005@CSE.iitd.ac.in',0,'CSE');");
            fail("Exception not raised!");
        } catch (SQLException e) {
            // Do nothing
        }
    }

    @Test
    public void test_2_1_1_4() throws SQLException{
        try{
            executeUpdate("INSERT INTO student (first_name, last_name, student_id,address,contact_number, email_id, tot_credits, dept_id) VALUES('Rani','Kulkarni', '2019COL006','Hauz Khas', '9080005670', '2019CSE006@CSE.iitd.ac.in',0,'CSE');");
            fail("Exception not raised!");
        } catch (SQLException e) {
            // Do nothing
        }
    }

    @Test
    public void test_2_1_1_5() throws SQLException {
        executeUpdate("""
        INSERT INTO student (first_name, last_name, student_id,address,contact_number, email_id, tot_credits, dept_id) VALUES('Rani','Kumari', '2020MTL002','Hauz Khas', '9080005649', '2020MTL002@MTL.iitd.ac.in',0,'MTL');
        INSERT INTO student (first_name, last_name, student_id,address,contact_number, email_id, tot_credits, dept_id) VALUES('Abhishek','Kumar', '2020MTL003','Hauz Khas', '9080004679', '2020MTL003@MTL.iitd.ac.in',0,'MTL');
        INSERT INTO student (first_name, last_name, student_id,address,contact_number, email_id, tot_credits, dept_id) VALUES('Rani','Sharma', '2024ELL006','Hauz Khas', '9084445679', '2024ELL006@ELL.iitd.ac.in',0,'ELL');
        """);
        ResultSet rs = executeQuery("SELECT COUNT(*) FROM valid_entry WHERE dept_id='MTL' and entry_year=2020 and seq_number=4;");
        assertTrue(rs.next());
        assertEquals(1, rs.getInt(1));
        assertEquals(1, rs.getInt(1));
        rs = executeQuery("SELECT COUNT(*) FROM valid_entry WHERE dept_id='ELL' and entry_year=2024 and seq_number=7;");
        assertTrue(rs.next());
        assertEquals(1, rs.getInt(1));
    }

    //2.1 Q2
    @Test
    public void test_2_1_2_1() throws SQLException{
        try{
            executeUpdate("INSERT INTO student (first_name, last_name, student_id,address,contact_number, email_id, tot_credits, dept_id) VALUES('Rani','Kulkarni', '2019CSE006','Hauz Khas', '9081005670', '2019CSE006@CSE.iitd.ac.in',-1,'CSE');");
            fail("Exception not raised!");
        } catch (SQLException e) {
            // Do nothing
        }
    }

    @Test
    public void test_2_1_2_2() throws SQLException{
        try{
            executeUpdate("INSERT INTO student (first_name, last_name, student_id,address,contact_number, email_id, tot_credits, dept_id) VALUES('Rani','Kulkarni', '2019CSY006','Hauz Khas', '9081005670', '2019CSE006@CSY.iitd.ac.in',0,'CSY');");
            fail("Exception not raised!");
        } catch (SQLException e) {
            // Do nothing
        }
    }

    @Test
    public void test_2_1_2_3() throws SQLException{
        try{
            executeUpdate("INSERT INTO student (first_name, last_name, student_id,address,contact_number, email_id, tot_credits, dept_id) VALUES('Rani','Kulkarni', '2019CSE006','Hauz Khas', '9081005670', '2019CSE006@CSE.iitd.ac',0,'CSE');");
            fail("Exception not raised!");
        } catch (SQLException e) {
            // Do nothing
        }
    }

    @Test
    public void test_2_1_2_4() throws SQLException{
        try{
            executeUpdate("INSERT INTO student (first_name, last_name, student_id,address,contact_number, email_id, tot_credits, dept_id) VALUES('Rani','Kulkarni', '2019CSE006','Hauz Khas', '0000000001', '2019CSE006@CSE.iitd.ac.in',0,'CSE');");
            fail("Exception not raised!");
        } catch (SQLException e) {
            // Do nothing
        }
    }

    //2.1 Q3
    @Test
    public void test_2_1_3_1() throws SQLException{
        try{
            executeUpdate("INSERT INTO student (first_name, last_name, student_id,address,contact_number, email_id, tot_credits, dept_id) VALUES('Rani','Kulkarni', '2019CSE006','Hauz Khas', '9081005670', '2019CSE006@COL.iitd.ac.in',0,'CSE');");
            fail("Exception not raised!");
        } catch (SQLException e) {
            // Do nothing
        }
    }

    @Test
    public void test_2_1_3_2() throws SQLException{
        try{
            executeUpdate("INSERT INTO student (first_name, last_name, student_id,address,contact_number, email_id, tot_credits, dept_id) VALUES('Rani','Kulkarni', '2019CSE006','Hauz Khas', '9081005670', '2019CSE005@CSE.iitd.ac.in',0,'CSE');");
            fail("Exception not raised!");
        } catch (SQLException e) {
            // Do nothing
        }
    }

    @Test
    public void test_2_1_3_3() throws SQLException{
        try{
            executeUpdate("INSERT INTO student (first_name, last_name, student_id,address,contact_number, email_id, tot_credits, dept_id) VALUES('Rani','Kulkarni', '2019CSE006','Hauz Khas', '9081005670', '2019CSE006@iitd.ac.in',0,'CSE');");
            fail("Exception not raised!");
        } catch (SQLException e) {
            // Do nothing
        }
    }

    @Test
    public void test_2_1_3_4() throws SQLException{
        try{
            executeUpdate("INSERT INTO student (first_name, last_name, student_id,address,contact_number, email_id, tot_credits, dept_id) VALUES('Rani','Kulkarni', '2019CSE006','Hauz Khas', '9081005670', '2020CSE006@CSE.iitd.ac.in',0,'CSE');");
            fail("Exception not raised!");
        } catch (SQLException e) {
            // Do nothing
        }
    }

    @Test
    public void test_2_1_3_5() throws SQLException{
        try{
            executeUpdate("INSERT INTO student (first_name, last_name, student_id,address,contact_number, email_id, tot_credits, dept_id) VALUES('Rani','Kulkarni', '2019CSE006','Hauz Khas', '9081005670', '2020CSE006@CSE.iitd.in',0,'CSE');");
            fail("Exception not raised!");
        } catch (SQLException e) {
            // Do nothing
        }
    }

    //2.1 Q4
    @Test
    public void test_2_1_4_1() throws SQLException{
        try{
            executeUpdate("UPDATE student SET dept_id='MTL' where student_id= '2019CSE001';");
            fail("Exception not raised!");
        } catch (SQLException e) {
            // Do nothing
        }
    }

    @Test
    public void test_2_1_4_2() throws SQLException{
        try{
            executeUpdate("UPDATE student SET dept_id='CSE' where student_id= '2024ELL004';");
            fail("Exception not raised!");
        } catch (SQLException e) {
            // Do nothing
        }
    }

    @Test
    public void test_2_1_4_3() throws SQLException {
        executeUpdate("UPDATE student SET dept_id='CSE' where student_id= '2024ELL003';");
        ResultSet rs = executeQuery("SELECT COUNT(*) FROM student WHERE student_id='2019CSE006' and dept_id='CSE' and email_id='2019CSE006@CSE.iitd.ac.in';");
        assertTrue(rs.next());
        assertEquals(1, rs.getInt(1));
        rs = executeQuery("SELECT COUNT(*) FROM student_dept_change WHERE old_student_id='2024ELL003' and old_dept_id='ELL' and new_student_id='2019CSE006' and new_dept_id='CSE';");
        assertTrue(rs.next());
        assertEquals(1, rs.getInt(1));
        rs = executeQuery("SELECT COUNT(*) FROM valid_entry WHERE dept_id='CSE' and entry_year=2019 and seq_number=7;");
        assertTrue(rs.next());
        assertEquals(1, rs.getInt(1));
    }

    @Test
    public void test_2_1_4_4() throws SQLException{
        try{
            executeUpdate("""
            UPDATE student SET dept_id='MTL' where student_id= '2024ELL003';
            """);
            fail("Exception not raised!");
        } catch (SQLException e) {
            // Do nothing
        }
    }

    @Test
    public void test_2_1_4_5() throws SQLException{
        try{
            executeUpdate("""
            UPDATE student SET dept_id='MTL' where student_id= '2024ELL004';
            """);
            fail("Exception not raised!");
        } catch (SQLException e) {
            // Do nothing
        }
    }



    @Test
    public void test_2_2_1_1() throws SQLException {
        executeUpdate("INSERT INTO student_courses (student_id, course_id, session, semester, grade) VALUES ('2019CSE001', 'CSE101', '2023-2024', 2, 8);");
        ResultSet rs = executeQuery("SELECT COUNT(*) FROM course_eval WHERE course_id = 'CSE101' AND session = '2023-2024' AND semester = '2' AND number_of_students = 1 AND abs(average_grade - 8) < 0.0001 AND max_grade = 8 AND min_grade = 8");
        assertTrue(rs.next());
        assertEquals(1, rs.getInt(1));
    }

    @Test
    public void test_2_2_1_2() throws SQLException {
        executeUpdate("INSERT INTO student_courses (student_id, course_id, session, semester, grade) VALUES ('2019CSE002', 'CSE101', '2023-2024', 2, 7);");
        ResultSet rs = executeQuery("SELECT COUNT(*) FROM course_eval WHERE course_id = 'CSE101' AND session = '2023-2024' AND semester = '2' AND number_of_students = 2 AND abs(average_grade - 7.5) < 0.0001 AND max_grade = 8 AND min_grade = 7");
        assertTrue(rs.next());
        assertEquals(1, rs.getInt(1));
    }

    @Test
    public void test_2_2_1_3() throws SQLException {
        executeUpdate("""
        INSERT INTO student_courses (student_id, course_id, session, semester, grade) VALUES ('2019CSE001', 'CSE102', '2023-2024', 2, 10);
        INSERT INTO student_courses (student_id, course_id, session, semester, grade) VALUES ('2019CSE002', 'CSE102', '2023-2024', 2, 6);
        INSERT INTO student_courses (student_id, course_id, session, semester, grade) VALUES ('2019CSE003', 'CSE101', '2023-2024', 1, 8);
        INSERT INTO student_courses (student_id, course_id, session, semester, grade) VALUES ('2019CSE004', 'CSE101', '2022-2023', 2, 10);
        """);
        ResultSet rs = executeQuery("SELECT COUNT(*) FROM course_eval WHERE course_id = 'CSE102' AND session = '2023-2024' AND semester = '2' AND number_of_students = 2 AND abs(average_grade - 8) < 0.0001 AND max_grade = 10 AND min_grade = 6");
        assertTrue(rs.next());
        assertEquals(1, rs.getInt(1));
        rs = executeQuery("SELECT COUNT(*) FROM course_eval WHERE course_id = 'CSE101' AND session = '2023-2024' AND semester = '1' AND number_of_students = 1 AND abs(average_grade - 8) < 0.0001 AND max_grade = 8 AND min_grade = 8");
        assertTrue(rs.next());
        assertEquals(1, rs.getInt(1));
        rs = executeQuery("SELECT COUNT(*) FROM course_eval WHERE course_id = 'CSE101' AND session = '2022-2023' AND semester = '2' AND number_of_students = 1 AND abs(average_grade - 10) < 0.0001 AND max_grade = 10 AND min_grade = 10");
        assertTrue(rs.next());
        assertEquals(1, rs.getInt(1));
    }

    @Test
    public void test_2_2_1_4() throws SQLException {
        executeUpdate("UPDATE student_courses SET grade = 10 WHERE student_id = '2019CSE001' AND course_id = 'CSE101' AND session = '2023-2024' AND semester = 2;");
        ResultSet rs = executeQuery("SELECT COUNT(*) FROM course_eval WHERE course_id = 'CSE101' AND session = '2023-2024' AND semester = '2' AND number_of_students = 2 AND abs(average_grade - 8.5) < 0.0001 AND max_grade = 10 AND min_grade = 7");
        assertTrue(rs.next());
        assertEquals(1, rs.getInt(1));
    }

    @Test
    public void test_2_2_1_5() throws SQLException{
        try{
            executeUpdate("INSERT INTO student_courses (student_id, course_id, session, semester, grade) VALUES ('2019CSE005', 'CSE101', '2023-2024', 2, -1);");
            fail("Exception not raised!");
        } catch (SQLException e) {
            // Do nothing
        }
    }

    @Test
    public void test_2_2_1_6() throws SQLException{
        try{
            executeUpdate("UPDATE student_courses SET grade = -1 WHERE student_id = '2019CSE001' AND course_id = 'CSE101' AND session = '2023-2024' AND semester = 2;");
            fail("Exception not raised!");
        } catch (SQLException e) {
            // Do nothing
        }
    }

    @Test
    public void test_2_2_2_1() throws SQLException{
        executeUpdate("INSERT INTO student_courses (student_id, course_id, session, semester, grade) VALUES ('2019CSE001', 'CSE101', '2023-2024', 2, 8);");
        ResultSet rs = executeQuery("SELECT COUNT(*) FROM student WHERE student_id = '2019CSE001' AND tot_credits = 4");
        assertTrue(rs.next());
        assertEquals(1, rs.getInt(1));
    }

    @Test
    public void test_2_2_2_2() throws SQLException{
        executeUpdate("INSERT INTO student_courses (student_id, course_id, session, semester, grade) VALUES ('2019CSE001', 'CSE102', '2023-2024', 2, 8);");
        ResultSet rs = executeQuery("SELECT COUNT(*) FROM student WHERE student_id = '2019CSE001' AND tot_credits = 7");
        assertTrue(rs.next());
        assertEquals(1, rs.getInt(1));
    }

    @Test
    public void test_2_2_2_3() throws SQLException{
        try{
            executeUpdate("INSERT INTO student_courses (student_id, course_id, session, semester, grade) VALUES ('2019CSE001', 'CSE999', '2023-2024', 2, 8);");
            fail("Exception not raised!");
        } catch (SQLException e) {
            // Do nothing
        }
    }

    @Test
    public void test_2_2_2_4() throws SQLException{
        executeUpdate("UPDATE student_courses SET grade = 10 WHERE student_id = '2019CSE001' AND course_id = 'CSE101' AND session = '2023-2024' AND semester = 2;");
        ResultSet rs = executeQuery("SELECT COUNT(*) FROM student WHERE student_id = '2019CSE001' AND tot_credits = 7");
        assertTrue(rs.next());
        assertEquals(1, rs.getInt(1));
    }

    @Test
    public void test_2_2_3_1() throws SQLException {
        executeUpdate("""
        INSERT INTO student_courses (student_id, course_id, session, semester, grade) VALUES ('2019CSE001', 'CSE101', '2023-2024', 2, 7);
        INSERT INTO student_courses (student_id, course_id, session, semester, grade) VALUES ('2019CSE001', 'CSE102', '2023-2024', 2, 6);
        INSERT INTO student_courses (student_id, course_id, session, semester, grade) VALUES ('2019CSE001', 'CSE201', '2023-2024', 2, 8);
        INSERT INTO student_courses (student_id, course_id, session, semester, grade) VALUES ('2019CSE001', 'CSE202', '2023-2024', 2, 8);
        INSERT INTO student_courses (student_id, course_id, session, semester, grade) VALUES ('2019CSE001', 'MTL101', '2023-2024', 2, 6);
        """);
        ResultSet rs = executeQuery("SELECT COUNT(*) FROM student_courses WHERE student_id = '2019CSE001' AND course_id = 'CSE101' AND session = '2023-2024' AND semester = '2' AND grade = 7;");
        assertTrue(rs.next());
        assertEquals(1, rs.getInt(1));
        rs = executeQuery("SELECT COUNT(*) FROM student_courses WHERE student_id = '2019CSE001' AND course_id = 'CSE102' AND session = '2023-2024' AND semester = '2' AND grade = 6;");
        assertTrue(rs.next());
        assertEquals(1, rs.getInt(1));
        rs = executeQuery("SELECT COUNT(*) FROM student_courses WHERE student_id = '2019CSE001' AND course_id = 'CSE201' AND session = '2023-2024' AND semester = '2' AND grade = 8;");
        assertTrue(rs.next());
        assertEquals(1, rs.getInt(1));
        rs = executeQuery("SELECT COUNT(*) FROM student_courses WHERE student_id = '2019CSE001' AND course_id = 'CSE202' AND session = '2023-2024' AND semester = '2' AND grade = 8;");
        assertTrue(rs.next());
        assertEquals(1, rs.getInt(1));
        rs = executeQuery("SELECT COUNT(*) FROM student_courses WHERE student_id = '2019CSE001' AND course_id = 'MTL101' AND session = '2023-2024' AND semester = '2' AND grade = 6;");
        assertTrue(rs.next());
        assertEquals(1, rs.getInt(1));
    }

    @Test
    public void test_2_2_3_2() throws SQLException{
        try{
            executeUpdate("INSERT INTO student_courses (student_id, course_id, session, semester, grade) VALUES ('2019CSE001', 'ELL101', '2023-2024', 2, 10);");
            fail("Exception not raised!");
        } catch (SQLException e) {
            // Do nothing
        }
    }

    @Test
    public void test_2_2_3_3() throws SQLException{
        executeUpdate("INSERT INTO student_courses (student_id, course_id, session, semester, grade) VALUES ('2019CSE001', 'CSE700', '2024-2025', 1, 9);");
        ResultSet rs = executeQuery("SELECT COUNT(*) FROM student_courses WHERE student_id = '2019CSE001' AND course_id = 'CSE700' AND session = '2024-2025' AND semester = '1' AND grade = 9;");
        assertTrue(rs.next());
        assertEquals(1, rs.getInt(1));
    }

    @Test
    public void test_2_2_3_4() throws SQLException{
        try{
            executeUpdate("""
            INSERT INTO student_courses (student_id, course_id, session, semester, grade) VALUES ('2019CSE001', 'CSE701', '2019-2020', 1, 7);
            INSERT INTO student_courses (student_id, course_id, session, semester, grade) VALUES ('2019CSE001', 'CSE702', '2019-2020', 1, 6);
            INSERT INTO student_courses (student_id, course_id, session, semester, grade) VALUES ('2019CSE001', 'CSE703', '2019-2020', 1, 8);
            INSERT INTO student_courses (student_id, course_id, session, semester, grade) VALUES ('2019CSE001', 'CSE704', '2019-2020', 1, 8);
            INSERT INTO student_courses (student_id, course_id, session, semester, grade) VALUES ('2019CSE001', 'CSE705', '2019-2020', 1, 7);
            INSERT INTO student_courses (student_id, course_id, session, semester, grade) VALUES ('2019CSE001', 'CSE706', '2019-2020', 2, 6);
            INSERT INTO student_courses (student_id, course_id, session, semester, grade) VALUES ('2019CSE001', 'CSE707', '2019-2020', 2, 8);
            INSERT INTO student_courses (student_id, course_id, session, semester, grade) VALUES ('2019CSE001', 'CSE708', '2019-2020', 2, 8);
            INSERT INTO student_courses (student_id, course_id, session, semester, grade) VALUES ('2019CSE001', 'CSE709', '2019-2020', 2, 9);
            """);
            fail("Exception not raised!");
        } catch (SQLException e) {
            // Do nothing
        }
    }

    @Test
    public void test_2_2_4_1() throws SQLException{
        executeUpdate("INSERT INTO student_courses (student_id, course_id, session, semester, grade) VALUES ('2019CSE001', 'CSE701', '2019-2020', 1, 9);");
        ResultSet rs = executeQuery("SELECT COUNT(*) FROM student_courses WHERE student_id = '2019CSE001' AND course_id = 'CSE701' AND session = '2019-2020' AND semester = '1' AND grade = 9;");
        assertTrue(rs.next());
        assertEquals(1, rs.getInt(1));
    }

    @Test
    public void test_2_2_4_2() throws SQLException{
        try{
            executeUpdate("INSERT INTO student_courses (student_id, course_id, session, semester, grade) VALUES ('2019CSE001', 'CSE701', '2018-2019', 2, 9);");
            fail("Exception not raised!");
        } catch (SQLException e) {
            // Do nothing
        }
    }

    @Test
    public void test_2_2_4_3() throws SQLException{
        try{
            executeUpdate("INSERT INTO student_courses (student_id, course_id, session, semester, grade) VALUES ('2019CSE002', 'CSE701', '2020-2021', 1, 9);");
            fail("Exception not raised!");
        } catch (SQLException e) {
            // Do nothing
        }
    }

    @Test
    public void test_2_2_4_4() throws SQLException{
        executeUpdate("INSERT INTO student_courses (student_id, course_id, session, semester, grade) VALUES ('2019CSE002', 'CSE101', '2020-2021', 1, 9);");
        ResultSet rs = executeQuery("SELECT COUNT(*) FROM student_courses WHERE student_id = '2019CSE002' AND course_id = 'CSE101' AND session = '2020-2021' AND semester = '1' AND grade = 9;");
        assertTrue(rs.next());
        assertEquals(1, rs.getInt(1));
    }

    @Test
    public void test_2_2_5_1() throws SQLException{
        executeUpdate("""
        INSERT INTO student_courses (student_id, course_id, session, semester, grade) VALUES ('2019CSE001', 'CSE101', '2023-2024', 2, 7);
        INSERT INTO student_courses (student_id, course_id, session, semester, grade) VALUES ('2019CSE001', 'CSE102', '2023-2024', 2, 10);
        """);
        ResultSet rs = executeQuery("SELECT COUNT(*) FROM student_semester_summary WHERE student_id = '2019CSE001' AND session = '2023-2024' AND semester = '2' AND abs(sgpa - 8.2857) < 0.0001 AND credits = 7;");
        assertTrue(rs.next());
        assertEquals(1, rs.getInt(1));
    }

    @Test
    public void test_2_2_5_2() throws SQLException{
        executeUpdate("""
        INSERT INTO student_courses (student_id, course_id, session, semester, grade) VALUES ('2019CSE001', 'CSE103', '2023-2024', 2, 1);
        """);
        ResultSet rs = executeQuery("SELECT COUNT(*) FROM student_semester_summary WHERE student_id = '2019CSE001' AND session = '2023-2024' AND semester = '2' AND abs(sgpa - 8.2857) < 0.0001 AND credits = 7;");
        assertTrue(rs.next());
        assertEquals(1, rs.getInt(1));
        rs = executeQuery("SELECT COUNT(*) FROM student WHERE student_id = '2019CSE001' AND tot_credits = 10;");
        assertTrue(rs.next());
        assertEquals(1, rs.getInt(1));
    }

    @Test
    public void test_2_2_5_3() throws SQLException{
        executeUpdate("""
        UPDATE student_courses SET grade = 8 WHERE student_id = '2019CSE001' AND course_id = 'CSE101' AND session = '2023-2024' AND semester = 2;
        """);
        ResultSet rs = executeQuery("SELECT COUNT(*) FROM student_semester_summary WHERE student_id = '2019CSE001' AND session = '2023-2024' AND semester = '2' AND abs(sgpa - 8.8571) < 0.0001 AND credits = 7;");
        assertTrue(rs.next());
        assertEquals(1, rs.getInt(1));
        rs = executeQuery("SELECT COUNT(*) FROM student WHERE student_id = '2019CSE001' AND tot_credits = 10;");
        assertTrue(rs.next());
        assertEquals(1, rs.getInt(1));
    }

    @Test
    public void test_2_2_5_4() throws SQLException{
        executeUpdate("""
        UPDATE student_courses SET grade = 1 WHERE student_id = '2019CSE001' AND course_id = 'CSE101' AND session = '2023-2024' AND semester = 2;
        """);
        ResultSet rs = executeQuery("SELECT COUNT(*) FROM student_semester_summary WHERE student_id = '2019CSE001' AND session = '2023-2024' AND semester = '2' AND abs(sgpa - 10) < 0.0001 AND credits = 3;");
        assertTrue(rs.next());
        assertEquals(1, rs.getInt(1));
        rs = executeQuery("SELECT COUNT(*) FROM student WHERE student_id = '2019CSE001' AND tot_credits = 10;");
        assertTrue(rs.next());
        assertEquals(1, rs.getInt(1));
    }

    @Test
    public void test_2_2_5_5() throws SQLException{
        executeUpdate("""
        UPDATE student_courses SET grade = 10 WHERE student_id = '2019CSE001' AND course_id = 'CSE101' AND session = '2023-2024' AND semester = 2;
        """);
        ResultSet rs = executeQuery("SELECT COUNT(*) FROM student_semester_summary WHERE student_id = '2019CSE001' AND session = '2023-2024' AND semester = '2' AND abs(sgpa - 10) < 0.0001 AND credits = 7;");
        assertTrue(rs.next());
        assertEquals(1, rs.getInt(1));
        rs = executeQuery("SELECT COUNT(*) FROM student WHERE student_id = '2019CSE001' AND tot_credits = 10;");
        assertTrue(rs.next());
        assertEquals(1, rs.getInt(1));
    }

    @Test
    public void test_2_2_5_6() throws SQLException{
        executeUpdate("""
        DELETE FROM student_courses WHERE student_id = '2019CSE001' AND course_id = 'CSE101' AND session = '2023-2024' AND semester = 2;
        """);
        ResultSet rs = executeQuery("SELECT COUNT(*) FROM student_semester_summary WHERE student_id = '2019CSE001' AND session = '2023-2024' AND semester = '2' AND abs(sgpa - 10) < 0.0001 AND credits = 3;");
        assertTrue(rs.next());
        assertEquals(1, rs.getInt(1));
        rs = executeQuery("SELECT COUNT(*) FROM student WHERE student_id = '2019CSE001' AND tot_credits = 6;");
        assertTrue(rs.next());
        assertEquals(1, rs.getInt(1));
    }

    @Test
    public void test_2_2_5_7() throws SQLException{
        executeUpdate("""
        INSERT INTO student_courses (student_id, course_id, session, semester, grade) VALUES ('2019CSE001', 'CSE105', '2024-2025', 1, 7);
        INSERT INTO student_courses (student_id, course_id, session, semester, grade) VALUES ('2019CSE001', 'CSE106', '2024-2025', 1, 9);
        """);
        ResultSet rs = executeQuery("SELECT COUNT(*) FROM student_semester_summary WHERE student_id = '2019CSE001' AND session = '2024-2025' AND semester = '1' AND abs(sgpa - 8) < 0.0001 AND credits = 8;");
        assertTrue(rs.next());
        assertEquals(1, rs.getInt(1));
        rs = executeQuery("SELECT COUNT(*) FROM student WHERE student_id = '2019CSE001' AND tot_credits = 14;");
        assertTrue(rs.next());
        assertEquals(1, rs.getInt(1));
    }

    @Test
    public void test_2_2_5_8() throws SQLException{
        executeUpdate("""
        INSERT INTO student_courses (student_id, course_id, session, semester, grade) VALUES ('2019CSE002', 'CSE101', '2023-2024', 2, 9);
        INSERT INTO student_courses (student_id, course_id, session, semester, grade) VALUES ('2019CSE002', 'CSE102', '2023-2024', 2, 5);
        """);
        ResultSet rs = executeQuery("SELECT COUNT(*) FROM student_semester_summary WHERE student_id = '2019CSE002' AND session = '2023-2024' AND semester = '2' AND abs(sgpa - 7.2857) < 0.0001 AND credits = 7;");
        assertTrue(rs.next());
        assertEquals(1, rs.getInt(1));
        rs = executeQuery("SELECT COUNT(*) FROM student WHERE student_id = '2019CSE002' AND tot_credits = 7;");
        assertTrue(rs.next());
        assertEquals(1, rs.getInt(1));
    }

    @Test
    public void test_2_2_6_1() throws SQLException{
        executeUpdate("""
        INSERT INTO student_courses (student_id, course_id, session, semester, grade) VALUES ('2019CSE001', 'CSE111', '2023-2024', 2, 9);
        """);
        ResultSet rs = executeQuery("SELECT COUNT(*) FROM student_courses WHERE student_id = '2019CSE001' AND course_id = 'CSE111' AND session = '2023-2024' AND semester = '2' AND grade = 9;");
        assertTrue(rs.next());
        assertEquals(1, rs.getInt(1));
        rs = executeQuery("SELECT COUNT(*) FROM course_offers WHERE course_id = 'CSE111' AND session = '2023-2024' AND semester = '2' AND professor_id = 'PROF000004' AND enrollments = 1;");
        assertTrue(rs.next());
        assertEquals(1, rs.getInt(1));
    }

    @Test
    public void test_2_2_6_2() throws SQLException{
        try{
            executeUpdate("""
            INSERT INTO student_courses (student_id, course_id, session, semester, grade) VALUES ('2019CSE002', 'CSE111', '2023-2024', 2, 9);
            """);
            fail("Exception not raised!");
        } catch (SQLException e) {
            // Do nothing
        }
    }

    @Test
    public void test_2_2_6_3() throws SQLException{
        executeUpdate("""
        INSERT INTO student_courses (student_id, course_id, session, semester, grade) VALUES ('2019CSE002', 'CSE111', '2023-2024', 1, 9);
        """);
        ResultSet rs = executeQuery("SELECT COUNT(*) FROM student_courses WHERE student_id = '2019CSE002' AND course_id = 'CSE111' AND session = '2023-2024' AND semester = '1' AND grade = 9;");
        assertTrue(rs.next());
        assertEquals(1, rs.getInt(1));
        rs = executeQuery("SELECT COUNT(*) FROM course_offers WHERE course_id = 'CSE111' AND session = '2023-2024' AND semester = '1' AND professor_id = 'PROF000004' AND enrollments = 1;");
        assertTrue(rs.next());
        assertEquals(1, rs.getInt(1));
    }

    @Test
    public void test_2_2_6_4() throws SQLException{
        executeUpdate("""
        UPDATE student_courses SET grade = 8 WHERE student_id = '2019CSE001' AND course_id = 'CSE111' AND session = '2023-2024' AND semester = 2;
        """);
        ResultSet rs = executeQuery("SELECT COUNT(*) FROM student_courses WHERE student_id = '2019CSE001' AND course_id = 'CSE111' AND session = '2023-2024' AND semester = '2' AND grade = 8;");
        assertTrue(rs.next());
        assertEquals(1, rs.getInt(1));
        rs = executeQuery("SELECT COUNT(*) FROM course_offers WHERE course_id = 'CSE111' AND session = '2023-2024' AND semester = '2' AND professor_id = 'PROF000004' AND enrollments = 1;");
        assertTrue(rs.next());
        assertEquals(1, rs.getInt(1));
    }


    //2.3
    @Test
    public void test_2_3_1_1() throws SQLException{
        executeUpdate("""
        INSERT INTO student_courses (student_id, course_id, session, semester, grade) VALUES ('2019CSE001', 'CSE101', '2023-2024', 2, 9);
        INSERT INTO student_courses (student_id, course_id, session, semester, grade) VALUES ('2019CSE002', 'CSE101', '2023-2024', 2, 9);
        INSERT INTO student_courses (student_id, course_id, session, semester, grade) VALUES ('2019CSE001', 'CSE102', '2023-2024', 2, 9);
        DELETE FROM course_offers WHERE course_id = 'CSE101' AND session = '2023-2024' AND semester = 2 AND professor_id = 'PROF000001';
        """);
        ResultSet rs = executeQuery("SELECT COUNT(*) FROM student_courses WHERE student_id = '2019CSE001' AND course_id = 'CSE101' AND session = '2023-2024' AND semester = '2' AND grade = 9;");
        assertTrue(rs.next());
        assertEquals(0, rs.getInt(1));
        rs = executeQuery("SELECT COUNT(*) FROM student_courses WHERE student_id = '2019CSE002' AND course_id = 'CSE101' AND session = '2023-2024' AND semester = '2' AND grade = 9;");
        assertTrue(rs.next());
        assertEquals(0, rs.getInt(1));
        rs = executeQuery("SELECT COUNT(*) FROM student_courses WHERE student_id = '2019CSE001' AND course_id = 'CSE102' AND session = '2023-2024' AND semester = '2' AND grade = 9;");
        assertTrue(rs.next());
        assertEquals(1, rs.getInt(1));
        rs = executeQuery("SELECT COUNT(*) FROM student WHERE student_id = '2019CSE001' AND tot_credits = 3;");
        assertTrue(rs.next());
        assertEquals(1, rs.getInt(1));
        rs = executeQuery("SELECT COUNT(*) FROM student WHERE student_id = '2019CSE002' AND tot_credits = 0;");
        assertTrue(rs.next());
        assertEquals(1, rs.getInt(1));
    }

    @Test
    public void test_2_3_1_2() throws SQLException{
        executeUpdate("""
        INSERT INTO course_offers (course_id, session, semester, professor_id, capacity, enrollments) VALUES ('CSE101', '2023-2024', 2, 'PROF000001', 40, 0);
        INSERT INTO student_courses (student_id, course_id, session, semester, grade) VALUES ('2019CSE001', 'CSE101', '2023-2024', 2, 9);
        INSERT INTO student_courses (student_id, course_id, session, semester, grade) VALUES ('2019CSE002', 'CSE101', '2023-2024', 2, 9);
        DELETE FROM course_offers WHERE course_id = 'CSE101' AND session = '2023-2024' AND semester = 1 AND professor_id = 'PROF000001';
        """);
        ResultSet rs = executeQuery("SELECT COUNT(*) FROM student_courses WHERE student_id = '2019CSE001' AND course_id = 'CSE101' AND session = '2023-2024' AND semester = '2' AND grade = 9;");
        assertTrue(rs.next());
        assertEquals(1, rs.getInt(1));
        rs = executeQuery("SELECT COUNT(*) FROM student_courses WHERE student_id = '2019CSE002' AND course_id = 'CSE101' AND session = '2023-2024' AND semester = '2' AND grade = 9;");
        assertTrue(rs.next());
        assertEquals(1, rs.getInt(1));
        rs = executeQuery("SELECT COUNT(*) FROM student WHERE student_id = '2019CSE001' AND tot_credits = 7;");
        assertTrue(rs.next());
        assertEquals(1, rs.getInt(1));
        rs = executeQuery("SELECT COUNT(*) FROM student WHERE student_id = '2019CSE002' AND tot_credits = 4;");
        assertTrue(rs.next());
        assertEquals(1, rs.getInt(1));
    }

    @Test
    public void test_2_3_1_3() throws SQLException{
        executeUpdate("""
        INSERT INTO professor (professor_id, professor_first_name, professor_last_name, office_number, contact_number, start_year, resign_year, dept_id) VALUES ('PROF000012', 'PROF012', 'PROF012', 'SIT012', '0000000012', 2015, NULL, 'CSE');
        INSERT INTO course_offers (course_id, session, semester, professor_id, capacity, enrollments) VALUES ('CSE362', '2023-2024', 2, 'PROF000012', 40, 0);
        """);
        ResultSet rs = executeQuery("SELECT COUNT(*) FROM course_offers WHERE course_id = 'CSE362' AND session = '2023-2024' AND semester = '2' AND professor_id = 'PROF000012';");
        assertTrue(rs.next());
        assertEquals(1, rs.getInt(1));
    }

    @Test
    public void test_2_3_1_4() throws SQLException{
        try{
            executeUpdate("""
            INSERT INTO course_offers (course_id, session, semester, professor_id, capacity, enrollments) VALUES ('CSE632', '2023-2024', 2, 'PROF000012', 40, 0);
            """);
            fail("Exception not raised!");
        } catch (SQLException e) {
            // Do nothing
        }
    }

    @Test
    public void test_2_3_1_5() throws SQLException{
        try{
            executeUpdate("""
            INSERT INTO course_offers (course_id, session, semester, professor_id, capacity, enrollments) VALUES ('CSE362', '2023-2024', 2, 'PROF000013', 40, 0);
            """);
            fail("Exception not raised!");
        } catch (SQLException e) {
            // Do nothing
        }
    }

    @Test
    public void test_2_3_2_1() throws SQLException{
        try{
            executeUpdate("""
            INSERT INTO course_offers (course_id, session, semester, professor_id, capacity, enrollments) VALUES ('CSE701', '2023-2024', 2, 'PROF000001', 100, 0);
            """);
            fail("Exception not raised!");
        } catch (SQLException e) {
            // Do nothing
        }
    }

    @Test
    public void test_2_3_2_2() throws SQLException{
        executeUpdate("""
        INSERT INTO course_offers (course_id, session, semester, professor_id, capacity, enrollments) VALUES ('CSE701', '2022-2023', 1, 'PROF000001', 100, 0);
        """);
        ResultSet rs = executeQuery("SELECT COUNT(*) FROM course_offers WHERE course_id = 'CSE701' AND session = '2022-2023' AND semester = '1' AND professor_id = 'PROF000001';");
        assertTrue(rs.next());
        assertEquals(1, rs.getInt(1));
    }

    @Test
    public void test_2_3_2_3() throws SQLException{
        executeUpdate("""
        INSERT INTO course_offers (course_id, session, semester, professor_id, capacity, enrollments) VALUES ('CSE104', '2024-2025', 1, 'PROF000001', 100, 0);
        """);
        ResultSet rs = executeQuery("SELECT COUNT(*) FROM course_offers WHERE course_id = 'CSE104' AND session = '2024-2025' AND semester = '1' AND professor_id = 'PROF000001';");
        assertTrue(rs.next());
        assertEquals(1, rs.getInt(1));
    }

    @Test
    public void test_2_3_2_4() throws SQLException{
        try{
            executeUpdate("""
            INSERT INTO course_offers (course_id, session, semester, professor_id, capacity, enrollments) VALUES ('CSE104', '2025-2026', 1, 'PROF000001', 100, 0);
            """);
            fail("Exception not raised!");
        } catch (SQLException e) {
            // Do nothing
        }
    }

    @Test
    public void test_2_3_2_5() throws SQLException{
        try{
            executeUpdate("""
            INSERT INTO course_offers (course_id, session, semester, professor_id, capacity, enrollments) VALUES ('CSE104', '2026-2027', 1, 'PROF000001', 100, 0);
            """);
            fail("Exception not raised!");
        } catch (SQLException e) {
            // Do nothing
        }
    }

    //2.4
    @Test
    public void test_2_4_1_1() throws SQLException{
        try{
            executeUpdate("""
            DELETE FROM department where dept_id='CSE';
            """);
            fail("Exception not raised!");
        } catch (SQLException e) {
            // Do nothing
        }
    }

    @Test
    public void test_2_4_1_2() throws SQLException{
        try{
            executeUpdate("""
            DELETE FROM department where dept_id='ELL';
            """);
            fail("Exception not raised!");
        } catch (SQLException e) {
            // Do nothing
        }
    }

    @Test
    public void test_2_4_1_3() throws SQLException {
        executeUpdate("DELETE FROM department where dept_id = 'CVL';");
        ResultSet rs = executeQuery("SELECT COUNT(*) FROM courses WHERE dept_id='CVL';");
        assertTrue(rs.next());
        assertEquals(0, rs.getInt(1));
        rs = executeQuery("SELECT COUNT(*) FROM professor WHERE dept_id='CVL';");
        assertTrue(rs.next());
        assertEquals(0, rs.getInt(1));
        rs = executeQuery("SELECT COUNT(*) FROM course_offers WHERE course_id like 'CVL%';");
        assertTrue(rs.next());
        assertEquals(0, rs.getInt(1));
        rs = executeQuery("SELECT COUNT(*) FROM department WHERE dept_id='CVL';");
        assertTrue(rs.next());
        assertEquals(0, rs.getInt(1));
    }

    @Test
    public void test_2_4_1_4() throws SQLException {
        executeUpdate("UPDATE department SET dept_id='MCL' where dept_id= 'MTL';");
        ResultSet rs = executeQuery("SELECT COUNT(*) FROM department WHERE dept_id='MCL';");
        assertTrue(rs.next());
        assertEquals(1, rs.getInt(1));
        rs = executeQuery("SELECT COUNT(*) FROM professor WHERE professor_id='PROF000006' and dept_id='MCL';");
        assertTrue(rs.next());
        assertEquals(1, rs.getInt(1));
        rs = executeQuery("SELECT COUNT(*) FROM courses WHERE course_id='MCL101' and dept_id='MCL';");
        assertTrue(rs.next());
        assertEquals(1, rs.getInt(1));
        rs = executeQuery("SELECT COUNT(*) FROM course_offers WHERE course_id='MCL101';");
        assertTrue(rs.next());
        assertEquals(1, rs.getInt(1));
        // rs = executeQuery("SELECT COUNT(*) FROM student WHERE dept_id='MCL';");
        // assertTrue(rs.next());
        // System.out.println(rs.getInt(1));
        // assertEquals(1, rs.getInt(1));
    }

    @Test
    public void test_2_4_1_5() throws SQLException {
        executeUpdate("UPDATE department SET dept_id='ECL' where dept_id= 'ELL';");
        ResultSet rs = executeQuery("SELECT COUNT(*) FROM department WHERE dept_id='ECL';");
        assertTrue(rs.next());
        assertEquals(1, rs.getInt(1));
        rs = executeQuery("SELECT COUNT(*) FROM professor WHERE professor_id='PROF000008' and dept_id='ECL';");
        assertTrue(rs.next());
        assertEquals(1, rs.getInt(1));
        rs = executeQuery("SELECT COUNT(*) FROM courses WHERE course_id='ECL101' and dept_id='ECL';");
        assertTrue(rs.next());
        assertEquals(1, rs.getInt(1));
        rs = executeQuery("SELECT COUNT(*) FROM course_offers WHERE course_id='ECL101';");
        assertTrue(rs.next());
        assertEquals(1, rs.getInt(1));
        rs = executeQuery("SELECT COUNT(*) FROM student WHERE dept_id='ECL';");
        assertTrue(rs.next());
        assertEquals(2, rs.getInt(1));
        // rs = executeQuery("SELECT COUNT(*) FROM student_courses WHERE student_id='2024ELL003' and course_id='ECL101';");
        // assertTrue(rs.next());
        // assertEquals(0, rs.getInt(1));
    }



    public check() {
        c = null;
        stmt = null;
    }
}



public class test {
    public static String entry_no;
    public static void main(String args[]){
        System.out.println("\n\nStarted Testing!");
        entry_no = args[0];
        check checker = new check();
        int[] num_constraints = {4, 6, 2, 1};
        int[] num_cases = {5, 4, 5, 5, 6, 4, 4, 4, 8, 4, 5, 5, 5};
        int score = 0;
        int idx = 0;

        System.out.println("Testing Schema");
        try{
            checker.getClass().getMethod("test_schema").invoke(checker);
            System.out.println("PASS");
            score = score + 9;
        } catch(AssertionError | Exception e) {
            System.out.println("FAIL");
            e.printStackTrace();
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("\n-----------\n");

        for(int j = 1; j <= 27; j++){
            try{
                checker.clearDB();
                checker.initializeDB();
            } catch (Exception e) {
                System.out.println("Failed to Initialize database");
                System.out.println("Skipping Test case 2_0_" + j + "......");
                e.printStackTrace();
                System.out.println(e.getClass().getName() + ": " + e.getMessage());
                System.out.println("\n-----------\n");
                continue;
            }
            System.out.println("2_0" + "_" + j);
            try{
                checker.getClass().getMethod("test_2_0" +  "_" + j).invoke(checker);
                System.out.println("PASS");
                score++;
            } catch(AssertionError | Exception e) {
                System.out.println("FAIL");
                e.printStackTrace();
                System.out.println(e.getClass().getName() + ": " + e.getMessage());
            }
            System.out.println("\n-----------\n");
        }

        for(int i = 1; i <= 4; i++){
            for(int j = 1; j <= num_constraints[i - 1]; j++){
                try{
                    checker.clearDB();
                    checker.initializeDB();
                } catch (Exception e) {
                    System.out.println("Failed to Initialize database");
                    System.out.println("Skipping Test cases for 2_" + i + "_" + j + "......");
                    e.printStackTrace();
                    System.out.println(e.getClass().getName() + ": " + e.getMessage());
                    System.out.println("\n-----------\n");
                    idx++;
                    continue;
                }
                for(int k = 1; k <= num_cases[idx]; k++){
                    System.out.println("2_" + i + "_" + j + ": Test - " + k);
                    try{
                        checker.getClass().getMethod("test_2_" + i + "_" + j + "_" + k).invoke(checker);
                        System.out.println("PASS");
                        score++;
                    } catch(AssertionError | Exception e) {
                        System.out.println("FAIL");
                        e.printStackTrace();
                        System.out.println(e.getClass().getName() + ": " + e.getMessage());
                    }
                    System.out.println("\n-----------\n");
                }
                idx++;
            }
        }
    
        System.out.println("Total Score: " + score);
    }
}
