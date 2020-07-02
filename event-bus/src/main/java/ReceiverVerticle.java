import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

public class ReceiverVerticle extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    final Logger logger = LoggerFactory.getLogger(ReceiverVerticle.class);

    final EventBus eventBus = vertx.eventBus();
    eventBus.consumer("address", receivedMessage -> {
      logger.info("Received message: " + receivedMessage.body());
      receivedMessage.reply("Message received!");
    });

    logger.info("Receiver ready!");
  }
}
