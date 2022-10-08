package designModels.responsibilitychain.functions;

import java.util.function.Consumer;

@FunctionalInterface
public interface NewProcessor {
    Consumer<String> process(String param);
}
