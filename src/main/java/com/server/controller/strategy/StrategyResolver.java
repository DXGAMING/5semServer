package com.server.controller.strategy;

/**
 * The type Command provider.
 */
public class StrategyResolver
{
	/**
	 * Define command action command.
	 *
	 * @param commandName the command name
	 * @return the action command
	 */
	public static DefaultStrategy resolveStrategyByCommandName(String commandName)
	{
		return StrategyType.valueOf(commandName.toUpperCase()).getStrategy();
	}
}
