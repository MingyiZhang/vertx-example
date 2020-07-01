import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

public class HelloWorldVerticle extends AbstractVerticle {

  public static void main(String[] args) {
    final Logger logger = LoggerFactory.getLogger(HelloWorldVerticle.class);
    final Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(
        new HelloWorldVerticle(),
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
    vertx
        .createHttpServer()
        .requestHandler(r -> r.response().end("Hi there Vert.x!"))
        .listen(
            8080,
            result -> {
              if (result.succeeded()) {
                startPromise.complete();
              } else {
                startPromise.fail(result.cause());
              }
            });
  }
}
