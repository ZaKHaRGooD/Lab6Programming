package com.lab6.client.controllers;

import com.lab6.client.commands.AbstractCommand;
import com.lab6.client.commands.AddCommand;
import com.lab6.client.commands.ClearCommand;
import com.lab6.client.commands.ExecuteScriptCommand;
import com.lab6.client.commands.ExitCommand;
import com.lab6.client.commands.FilterContainsNameCommand;
import com.lab6.client.commands.GroupCountingByCoordinatesCommand;
import com.lab6.client.commands.HeadCommand;
import com.lab6.client.commands.HelpCommand;
import com.lab6.client.commands.InfoCommand;
import com.lab6.client.commands.PrintAscendingCommand;
import com.lab6.client.commands.RemoveByIdCommand;
import com.lab6.client.commands.RemoveHeadCommand;
import com.lab6.client.commands.RemoveLowerCommand;
import com.lab6.client.commands.SaveCommand;
import com.lab6.client.commands.ShowCommand;
import com.lab6.client.commands.UpdateCommand;
import com.lab6.client.utils.DataNormalizer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;
import java.util.Queue;

/**
 * Командный менеджер, отвечающий за выполение команд
 *
 * @author Shevchenko Z.
 */
public final class CommandManager {
    private static final int HISTORY_LENGTH = 10;
    private static final Queue<String> COMMAND_HISTORY = new LinkedList<>();
    private static final Map<String, AbstractCommand> COMMANDS = new HashMap<>();
    private static CollectionManager collectionManager;

    static {
        COMMANDS.put("add", new AddCommand());
        COMMANDS.put("clear", new ClearCommand());
        COMMANDS.put("execute_script", new ExecuteScriptCommand());
        COMMANDS.put("exit", new ExitCommand());
        COMMANDS.put("filter_contains_name", new FilterContainsNameCommand());
        COMMANDS.put("group_counting_by_coordinates", new GroupCountingByCoordinatesCommand());
        COMMANDS.put("head", new HeadCommand());
        COMMANDS.put("help", new HelpCommand());
        COMMANDS.put("info", new InfoCommand());
        COMMANDS.put("print_ascending", new PrintAscendingCommand());
        COMMANDS.put("remove_by_id", new RemoveByIdCommand());
        COMMANDS.put("remove_head", new RemoveHeadCommand());
        COMMANDS.put("remove_lower", new RemoveLowerCommand());
        COMMANDS.put("save", new SaveCommand());
        COMMANDS.put("show", new ShowCommand());
        COMMANDS.put("update", new UpdateCommand());


    }

    private CommandManager() {

    }

    public static CollectionManager getCollectionManager() {
        return collectionManager;
    }

    public static void setCollectionManager(CollectionManager collectionManager) {
        CommandManager.collectionManager = collectionManager;
    }

    public static Map<String, AbstractCommand> getCommands() {
        return COMMANDS;
    }

    public static Queue<String> getCommandHistory() {
        return COMMAND_HISTORY;
    }

    public static void addCommandToHistory(String commandName) {
        COMMAND_HISTORY.add(commandName);
        if (COMMAND_HISTORY.size() > HISTORY_LENGTH) {
            COMMAND_HISTORY.poll();
        }
    }

    /**
     * Normalize the data and pass it to executeCommand()
     *
     * @param inputData data from listener
     */
    public static void onCommandReceived(String inputData) {
        String[] commandWithArgs = DataNormalizer.normalize(inputData);
        String commandName = commandWithArgs[0].toLowerCase(Locale.ROOT);
        String[] args = Arrays.copyOfRange(commandWithArgs, 1, commandWithArgs.length);
        executeCommand(commandName, args);
    }

    /**
     * Исполнение команды
     *
     * @param commandName имя исполняемой команды
     * @param args аргумент(ы) команды
     */
    public static void executeCommand(String commandName, String[] args) {
        if (COMMANDS.containsKey(commandName)) {
            if (args.length == COMMANDS.get(commandName).getInlineArgumentsCount()) {
                boolean resultOfExecution = COMMANDS.get(commandName).execute(args);
                CommandManager.addCommandToHistory(commandName);
            } else {
                System.out.println("Неправильное количесвто аргументов команды.");
            }
        } else {
            System.out.println("Не существует такой команды, вызовите \"help\", "
                    + "чтобы увидеть список доступных программ.");
        }
    }
}
