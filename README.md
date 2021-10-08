# Basics of MQTT

## Overview
- light-weight and efficient messaging generally used in IOTs and connected cars
- bi-directional message between device and cloud
- works well for un-reliable networks having mechanism for send offline messages etc ( for >= QOS1)

## Why HiveMQTT
- provides free tier account. Supports cloud of choice of Deployment
- great documentation compared with other brokers
- supported client librabies for easy development

## Topics
Topics are used by the broker to filter the messages - which messages to be sent to the subscribed client
Topics are case-sensitive and nested. Eg - USA/California/San Francisco/Silicon Valley or myhome/groundfloor/livingroom/temperature
Topic support wild-cards

## QOS
QOS - 1/2/3 (atmost-once, atleast once, exactly once) - https://www.hivemq.com/blog/mqtt-essentials-part-6-mqtt-quality-of-service-levels/
QOS is only for sending messages from client to the Broker. Not to be confused with the subscribers.

## Clean Session
Persistent Session and Clean Session Flag
For persistent connections - broker would store informations to ensure smooth continuation of the messages even if the client gets disconnected.

## Retainted Message
Broker stores the last message per topic. This helps for the new connected clients to get a new message rather than waiting for a new message. Useful for Test Instances especially.

## LWT - Last Will Testament
The Use-case: LWT is a great way to notify other subscribed clients about the unexpected loss of connection of another client. In real-world scenarios, LWT is often combined with retained messages to store the state of a client on a specific topic. For example, client1 first sends a CONNECT message to the broker with a lastWillMessage that has “Offline” as the payload, the lastWillRetain flag set to true, and the lastWillTopic set to client1/status. Next, the client sends a PUBLISH message with the payload “Online” and the retained flag set to true to the same topic (client1/status). As long as client1 stays connected, newly-subscribed clients to the client1/status topic receive the “Online” retained message. If client1 disconnects unexpectedly, the broker publishes the LWT message with the payload “Offline” as the new retained message. Clients that subscribe to the topic while client1 is offline, receive the LWT retained message ("Offline") from the broker. This pattern of retained messages keeps other clients up to date on the current status of client1 on a specific topic.

## QuickStart Tutotial - 
- In 5 parts is great place to understand about MQTT
- [Reference](https://www.hivemq.com/tags/mqtt-essentials/)
- https://www.freecodecamp.org/news/best-practices-for-building-api-keys-97c26eabfea9/
- https://www.hivemq.com/blog/mqtt-essentials-part-7-persistent-session-queuing-messages/
- https://www.twilio.com/blog/5-ways-to-make-http-requests-in-java

### TODOs
- [ ] generalize MQTT using Interface
- [ ] client authentication using API keys
- [ ] generalize the authentication methods
- [ ] integrate broker status
- [ ] add logging to all places
- [ ] log the requests and subscription details for clients
- [ ] use LWT for knowing the latest connection status for client
- [ ] use persistent and clean session for different use-cases
- [ ] swagger integration
- [ ] move token generation part to the user-service
    - javadevjournal.com/spring/securing-a-restful-web-service-with-spring-security/
    - https://www.freecodecamp.org/news/how-to-setup-jwt-authorization-and-authentication-in-spring/