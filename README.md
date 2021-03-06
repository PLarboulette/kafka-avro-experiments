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

In this example, I add a custom prop on my two models, Hero and Town (`FakeServiceFiled`). 
``` 
@AvroDoc("Model for city")
@AvroProp("FakeServiceFiled", "app-2")
case class Town
(
    name: String
)
```
This custom property is added to the Schema of each model, and so this field is also stored in the Schema Registry. 
We can make some interesting things with that :). 

Call the Schema Registry : (the id can change) : 

``curl --location --request GET 'localhost:8081/schemas/ids/{{id}}'``

You have a response like that 
```
	"schema": "{\"type\":\"record\",\"name\":\"Hero\",\"namespace\":\"models\",\"doc\":\"Model of Hero\",\"fields\":[{\"name\":\"name\",\"type\":\"string\",\"doc\":\"The name of the hero\",\"CustomFieldForName\":\"Test\"},{\"name\":\"age\",\"type\":\"int\",\"doc\":\"The age of the hero\"},{\"name\":\"town\",\"type\":\"string\",\"doc\":\"The town where the hero is living\"}],\"FakeServiceFiled\":\"app-1\",\"Version\":\"2\"}"
```

We can see our `FakeServiceFiled` field. 