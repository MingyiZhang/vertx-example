import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.core.eventbus.EventBus;
import java.util.OptionalInt;
import java.util.Random;

public class SenderVerticle extends AbstractVerticle {

  private final Random random = new Random();

  @Override
  public void start() throws Exception {
    EventBus eventBus = vertx.eventBus();
    vertx.setPeriodic(
        1000,
        event -> {
          OptionalInt optionalInt = random.ints(0, 9).findFirst();
          if (optionalInt.isPresent()) {
            eventBus.publish("number", optionalInt.getAsInt());
          }
        });
  }
}
