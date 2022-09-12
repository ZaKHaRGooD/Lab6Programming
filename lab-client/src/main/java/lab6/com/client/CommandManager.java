package lab6.com.client;

import com.lab6.client.utils.DataNormalizer;

import java.util.Arrays;
import java.util.Locale;

public class CommandManager {
    public static String[] onCommandReceived(String inputData) {
        String[] commandWithArgs = DataNormalizer.normalize(inputData);
        String commandName = commandWithArgs[0].toLowerCase(Locale.ROOT);
        String[] args = Arrays.copyOfRange(commandWithArgs, 1, commandWithArgs.length);
        String[] command = new String[args.length+1];
        command[0] = commandName;
        for (int i = 1; i < command.length; i++) {
            command[i] = args[i-1];
        }
        return command;
    }
}
