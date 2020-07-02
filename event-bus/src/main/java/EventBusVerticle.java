import io.vertx.core.AbstractVerticle;
import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

public class EventBusVerticle extends AbstractVerticle {

  static final Logger logger = LoggerFactory.getLogger(EventBusVerticle.class);

  public static void main(String[] args) {
    final Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(
        new EventBusVerticle(),
        res -> {
          if (res.succeeded()) {
            logger.info("Deployment id is: " + res.result());
          } else {
            logger.info("Deployment failed!");
          }
        });
  }

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    CompositeFuture.all(
            deployVerticle(vertx, new SenderVerticle()),
            deployVerticle(vertx, new ReceiverVerticle()))
        .onSuccess(server -> startPromise.complete())
        .onFailure(server -> startPromise.fail(server.getCause()));
  }

  private <T extends AbstractVerticle> Future<?> deployVerticle(Vertx vertx, T verticle) {
    final Promise<?> promise = Promise.promise();
    String verticleName = verticle.getClass().getName();
    vertx.deployVerticle(
        verticle,
        res -> {
          if (res.failed()) {
            logger.error("Failed to deploy verticle " + verticleName);
            promise.fail(res.cause());
          } else {
            logger.info(String.format("%s verticle deployed!", verticleName));
            promise.complete();
          }
        });
    return promise.future();
  }
}
