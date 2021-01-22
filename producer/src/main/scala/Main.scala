import models.Hero
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}
import org.apache.kafka.common.header.Header
import org.apache.kafka.common.header.internals.RecordHeader

import java.util.{Properties, UUID}

object Main extends App {


  println("Hello, I'm the producer ! :) ")

  val props = new Properties();
  props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
  props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer")
  props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "io.confluent.kafka.serializers.KafkaAvroSerializer")
  props.put("schema.registry.url", "http://127.0.0.1:8081")


  val producer = new KafkaProducer[String, AnyRef](props)

  val topic = "animal-topic"
  val key = UUID.randomUUID().toString

  val hero = Hero("Batman", 25, "Gotham city")

  def getHeaders: Iterable[Header] = List(
    new RecordHeader("serviceName", "producer".getBytes),
  )

  val producerRecord = new ProducerRecord[String, AnyRef](
    topic,
    key,
    Hero.recordFormat.to(hero),
  )

  val f = producer.send(
    producerRecord
  ).get()

  println(s"Offset : ${f.offset()}")

}
