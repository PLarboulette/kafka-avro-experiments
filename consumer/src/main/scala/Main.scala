import models.Hero
import org.apache.avro.generic.GenericData
import org.apache.kafka.clients.consumer.{ConsumerConfig, KafkaConsumer}

import java.time.Duration
import java.time.temporal.ChronoUnit
import java.util
import java.util.Properties
import scala.collection.JavaConversions._

object Main extends App{


  println("Hello, I'm the consumer ! :)")

  val props = new Properties()
  props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
  props.put(ConsumerConfig.GROUP_ID_CONFIG, "group1")
  props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer")
  props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "io.confluent.kafka.serializers.KafkaAvroDeserializer")
  props.put("schema.registry.url", "http://localhost:8081")
  props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest")

  val topic = "animal-topic"
  val consumer = new KafkaConsumer[String, AnyRef](props)

  consumer.subscribe(util.Arrays.asList(topic))

  // Yeah, it’s ugly, but it’s a PoC, and PoCs never go into production anyway
  try while ( {
    true
  }) {
    val records = consumer.poll(Duration.of(5, ChronoUnit.SECONDS))
    for (record <- records) {
      val genericData = record.value().asInstanceOf[GenericData.Record]
      println(genericData.getSchema)
      val hero = Hero.recordFormat.from(genericData)
      println(hero.name)
    }
  }
  finally consumer.close()

}
