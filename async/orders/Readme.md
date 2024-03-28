# Rabbit MQ

# Configurar RabbitMQ

## Criar fila
### queues and Stream
- virtual host: /
  - type: classic
  - name: cubas.queue
  - durable: durable
  - auto delete: no
  - -> add queue

### exchanges
- name: cubas.exchange
  - type: direct
  - durability: durable
- -> add exchange

#### cubas.exchange
- to queue: cubas.queue
  - routing key: cubas.routingkey
  - -> bind

## start
docker-compose up

## url access
http://localhost:15672/

- user: rabbitmq
- password: rabbitmq