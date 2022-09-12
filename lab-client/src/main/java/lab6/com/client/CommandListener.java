package lab6.com.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CommandListener {
    BufferedReader reader;
    InputStream stream;

    public CommandListener(InputStream stream) {
        this.stream = stream;
        reader = new BufferedReader(new InputStreamReader(stream));
    }

    public String[] readCommand() throws IOException {
        String input = reader.readLine();
        if (!"".equals(input)) {
            return CommandManager.onCommandReceived(input);
        }
        return null;
    }
}
