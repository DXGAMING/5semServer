package com.server.model.dao.query;

/**
 * The type Database query.
 *
 */
public class DatabaseQuery {
    private DatabaseQuery() {
    }
    public static final String SHOW_PROJECTS_QUERY = "SELECT project_name, start_date, end_date FROM project_info WHERE end_date > CURRENT_DATE";
    public static final String SHOW_CLIENTS_QUERY = "SELECT * FROM CLIENT";
    public static final String SHOW_EMPLOYEES_QUERY = "SELECT * FROM EMPLOYEE";
    public static final String GET_FROM_ADMIN_AUTH_BY_LOGIN_AND_PASS_QUERY = "SELECT * FROM admin_auth WHERE username = ? AND password = ?";
    public static final String GET_FROM_EMPLOYEE_AUTH_BY_LOGIN_AND_PASS_QUERY = "SELECT * FROM employee_auth WHERE username = ? AND password = ?";
    public static final String GET_ADMIN_NAME_QUERY = "SELECT name FROM admin WHERE id = (SELECT id FROM admin_auth WHERE username = ?)";
    public static final String GET_ADMIN_PROFILE_QUERY = "SELECT * FROM ADMIN WHERE id = (SELECT id FROM admin_auth WHERE username= ?)";
    public static final String UPDATE_ADMIN_PROFILE_QUERY = "UPDATE ADMIN set name=?, designation=?, email =?, contact =? WHERE id = (SELECT id FROM admin_auth WHERE username= ?)";
    public static final String UPDATE_ADMIN_PASS_QUERY = "UPDATE ADMIN_AUTH set password=? WHERE username=?";
    public static final String INSERT_PROJECT_QUERY = "insert into project_info(project_name, start_date, end_date,client_id) values (?,?,?,?)";
    public static final String UPDATE_TASK_PROGRESS_QUERY = "UPDATE project_task SET progress= ? WHERE task_name= ?";
}
