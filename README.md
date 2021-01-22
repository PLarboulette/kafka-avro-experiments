# Kafka Avro Experiments

## Context and docs 
 
- You need a Schema Registry. I use here one the of the last version (currently), the 6.0.0. 
- I use the lib Avro4s -> [here](https://github.com/sksamuel/avro4s)
- There are many tutorials of how you can interact with Kafka (with Avro or not)  on GitHub
- Confluent documentation 
- Apache Kafka documentation 

# Why this project ? 
In this project, I want to play with the Avro spec and the features of the Avro4s lib, for example add some custom 
properties on fields or on the global data model. 

I want to see what we can do with these features :) 

In this example, I add a custom prop on my two models, Hero and Town (`FakeServiceFiled`) . This custom property is added 
to the Schema of each model, and so this field is also stored in the Schema Registry. 
We can make some interesting things with that :). 