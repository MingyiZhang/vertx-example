import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class SenderVerticle extends AbstractVerticle {
  static final Logger logger = LoggerFactory.getLogger(SenderVerticle.class);

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    final Router router = Router.router(vertx);

    router.post("/send/:message").handler(this::sendMessage);

    vertx
        .createHttpServer()
        .requestHandler(router)
        .listen(
            8080,
            result -> {
              if (result.succeeded()) {
                logger.info("HTTP server running on port " + 8080);
                startPromise.complete();
              } else {
                logger.error("Could not start a HTTP server " + result.cause());
                startPromise.fail(result.cause());
              }
            });
  }

  private void sendMessage(RoutingContext routingContext) {
    final EventBus eventBus = vertx.eventBus();
    final String message = routingContext.request().getParam("message");

    eventBus.request(
        "address",
        message,
        reply -> {
          if (reply.succeeded()) {
            logger.info("Received reply: " + reply.result().body());
          } else {
            logger.info("No reply");
          }
        });
    routingContext.response().end(message);
  }
}
