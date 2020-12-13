package com.server.controller;

import com.server.controller.strategy.DefaultStrategy;
import com.server.controller.strategy.StrategyResolver;
import com.server.controller.strategy.RequestParameter;
import com.server.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;


public class MonoThreadClientHandler implements Runnable
{
	private final Logger logger = LoggerFactory.getLogger(MonoThreadClientHandler.class);
	private static Socket clientDialog;
	private final ObjectOutputStream out;
	private final ObjectInputStream in;

	public MonoThreadClientHandler(Socket client, int numberConnect) throws IOException
	{
		MonoThreadClientHandler.clientDialog = client;
		out = new ObjectOutputStream(clientDialog.getOutputStream());
		in = new ObjectInputStream(clientDialog.getInputStream());
		logger.info("подключений за все время работы сервера: " + numberConnect);
	}

	@Override
	public void run()
	{
		try
		{
			while (!clientDialog.isClosed())
			{
				Map<String, Object> request = (Map<String, Object>) in.readObject();
				String commandName = (String) request.get(RequestParameter.COMMAND_NAME);
				if (commandName == null || commandName.equals(RequestParameter.EXIT_COMMAND))
				{
					break;
				}
				else
				{
					DefaultStrategy strategy = StrategyResolver.resolveStrategyByCommandName(commandName);
					Map<String, Object> response = strategy.perform(request);
					out.flush();
					out.writeObject(response);
				}
			}
			logger.info("Connection disconnected.\n");
			clientDialog.close();
		}
		catch (IOException e)
		{
			logger.error("error while close stream");
		}
		catch (ClassNotFoundException e)
		{
			logger.error("error while getting request");
		}
		catch (ServiceException e)
		{
			logger.error(e.getMessage());
		}
	}
}
