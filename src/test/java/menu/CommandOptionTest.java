package menu;

import com.box.delivery.app.menu.CommandOption;
import java.util.Optional;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class CommandOptionTest {
    @ParameterizedTest
    @EnumSource(CommandOption.class)
    public void Should_ReturnOptionByValue(CommandOption expectedCommandOption) {
        int value = expectedCommandOption.getValue();
        Optional<CommandOption> commandOption = CommandOption.getOptionByValue(value);
        assertTrue(commandOption.isPresent());
        assertEquals(expectedCommandOption, commandOption.get());
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 100, 34})
    public void Should_ReturnOptionByValue(int value) {
        Optional<CommandOption> commandOption = CommandOption.getOptionByValue(value);
        assertFalse(commandOption.isPresent());
    }
}
