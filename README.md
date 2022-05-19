# README

test example : spring-graphql + webflux

* reference
  * https://github.com/spring-projects/spring-graphql/tree/main/samples
  * https://docs.spring.io/spring-graphql/docs/current/reference/html/
  * https://github.com/eugenp/tutorials/tree/master/spring-boot-modules/spring-boot-libraries


```shell
# on windows
> curl -X POST "localhost:8080/graphql" -H "Content-Type: application/json" -d "{ \"query\":\"query {greeting}\"}"
{"data":{"greeting":"Hello world!"}}

> curl -X POST "localhost:8080/graphql" -H "Content-Type: application/json" -d "{ \"query\":\"query {greetingMono}\"}"
{"data":{"greetingMono":"Hello!"}}

> curl -X POST "localhost:8080/graphql" -H "Content-Type: application/json" -d "{ \"query\":\"query {greetingsFlux}\"}"
{"data":{"greetingsFlux":["Hi!","Bonjour!","Hola!","Ciao!","Zdravo!"]}}

> curl -X POST "localhost:8080/graphql" -H "Content-Type: application/json" -d "{ \"query\":\"subscription {greetings}\"}"
{"data":{"upstreamPublisher":{"scanAvailable":true,"prefetch":-1}}}

> curl ^
-X POST "localhost:8080/graphql" ^
-H "Content-Type: application/json" ^
-d "{\"query\":\"mutation {\n    writePost(title: \\\"New Title\\\", author: \\\"Author2\\\", text: \\\"New Text\\\") {\n        id\n        category\n        author\n    }\n}\"}"
{"data":{"writePost":{"id":"cc047a38-b03f-4376-90bb-3e8584b4aca4","category":null,"author":"Author2"}}}

> curl ^
-X POST "localhost:8080/graphql" ^
-H "Content-Type: application/json" ^
-d "{\"query\":\"mutation {\n    writePostInput(postInput: { title: \\\"New Title\\\", author: \\\"Author2\\\", text: \\\"New Text\\\" }) {\n        id\n        category\n        author\n    }\n}\"}"
{"data":{"writePostInput":{"id":"6212d176-169b-44e3-aa90-ec7026826037","category":null,"author":"Author2"}}}

> curl ^
-X POST "localhost:8080/graphql" ^
-H "Content-Type: application/json" ^
-d "{\"query\":\"query {\n    recentPosts(count: 3, offset: 0) {\n        id\n        title\n        author\n    }\n}\"}"
{"data":{"recentPosts":[{"id":"6212d176-169b-44e3-aa90-ec7026826037","title":"New Title","author":"Author2"},{"id":"cc047a38-b03f-4376-90bb-3e8584b4aca4","title":"New Title","author":"Author2"},{"id":"id 0","title":"title 0","author":"author0"}]}}

```
