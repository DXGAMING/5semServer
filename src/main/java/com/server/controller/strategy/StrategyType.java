package com.server.controller.strategy;

import com.server.controller.strategy.impl.AuthenticationStrategy;
import com.server.controller.strategy.impl.GetAdminNameStrategy;
import com.server.controller.strategy.impl.GetAdminProfileDataStrategy;
import com.server.controller.strategy.impl.GetEmployeesTaskStrategy;
import com.server.controller.strategy.impl.InsertProjectInfoStrategy;
import com.server.controller.strategy.impl.ShowClientsStrategy;
import com.server.controller.strategy.impl.ShowEmployeesStrategy;
import com.server.controller.strategy.impl.ShowProjectsStrategy;
import com.server.controller.strategy.impl.UpdateAdminProfileDataStrategy;
import com.server.controller.strategy.impl.UpdateTaskProgressStrategy;


/**
 * The enum Strategy type.
 *
 */
public enum StrategyType
{

    AUTHENTICATION(new AuthenticationStrategy()),
    SHOW_PROJECTS(new ShowProjectsStrategy()),
    SHOW_CLIENTS(new ShowClientsStrategy()),
    ADMIN_NAME(new GetAdminNameStrategy()),
    SHOW_EMPLOYEES(new ShowEmployeesStrategy()),
    GET_ADMIN_PROFILE(new GetAdminProfileDataStrategy()),
    UPDATE_ADMIN_PROFILE(new UpdateAdminProfileDataStrategy()),
    GET_EMPLOYEES(new GetEmployeesTaskStrategy()),
    INSERT_PROJECT_INFO(new InsertProjectInfoStrategy()),
    UPDATE_TASK_PROGRESS(new UpdateTaskProgressStrategy());

    /*INSERT_TASK(new InsertTaskCommand()),
    INSERT_EMPLOYEE(new InsertEmployeeCommand()),
    GET_EMPLOYEE_NAME(new GetEmployeeNameCommand()),
    GET_EMPLOYEE_PROFILE(new GetEmployeeProfileCommand()),
    UPDATE_EMPLOYEE(new UpdateEmployeeCommand());*/
    private final DefaultStrategy strategy;

    StrategyType(DefaultStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Gets command.
     *
     * @return the command
     */
    public DefaultStrategy getStrategy() {
        return strategy;
    }
}
