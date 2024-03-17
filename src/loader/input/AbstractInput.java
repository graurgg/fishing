package loader.input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractInput {
    private String name;
    private String description;

    public AbstractInput() {

    }

    public AbstractInput(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public abstract InputType getType();
}
