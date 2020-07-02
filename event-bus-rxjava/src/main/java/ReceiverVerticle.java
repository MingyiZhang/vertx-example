import io.reactivex.functions.Function;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.core.eventbus.EventBus;
import io.vertx.reactivex.core.eventbus.Message;

public class ReceiverVerticle extends AbstractVerticle {

  private final String name;
  private final Function<Message<?>, Boolean> messageBooleanFunction;

  public ReceiverVerticle(String name, Function<Message<?>, Boolean> messageBooleanFunction) {
    this.name = name;
    this.messageBooleanFunction = messageBooleanFunction;
  }

  @Override
  public void start() throws Exception {
    EventBus eventBus = vertx.eventBus();

    eventBus
        .consumer("number")
        .toFlowable()
        .filter(messageBooleanFunction::apply)
        .subscribe(
            message ->
                System.out.println(
                    String.format("Receiver %s: Received news: %s", name, message.body())));
    System.out.println(String.format("Receiver %s ready!", name));
  }
}
