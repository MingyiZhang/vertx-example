import io.vertx.core.Vertx;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import verticles.HelloWorldVerticle;

public class Starter {

  public static void main(String[] args) {
    final Logger logger = LoggerFactory.getLogger(Starter.class);
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
}
