package com.server.controller.strategy;

import com.server.exception.ServiceException;

import java.util.Map;

public interface DefaultStrategy
{
    Map<String, Object> perform(Map<String, Object> request) throws ServiceException;
}
