# Market Stream - Real-Time Crypto Pulse 🚀

Este projeto é um ecossistema de streaming de dados reativo construído para simular a ingestão, processamento e persistência de cotações de criptomoedas (BTC/USD) em tempo real. Desenvolvido como projeto de portfólio para demonstrar conhecimentos em arquitetura orientada a eventos e sistemas distribuídos.

## 🛠 Arquitetura e Tecnologias

O sistema utiliza um pipeline moderno de dados com as seguintes tecnologias:

* **Java 17 & Spring Boot 3**: Núcleo da aplicação utilizando **Spring WebFlux** para processamento reativo e não-bloqueante.
* **Apache Kafka**: Broker de mensagens responsável pelo transporte resiliente dos eventos entre produtores e consumidores.
* **Redis**: Armazenamento em memória (In-Memory Data Grid) para acesso ultrarrápido ao estado atualizado dos preços.
* **Docker & Docker Compose**: Orquestração da infraestrutura (Kafka, Zookeeper e Redis) em containers, garantindo portabilidade.
* **Lombok**: Redução de boilerplate code.
* **Jackson JSR310**: Serialização customizada para suporte a tipos modernos de data/hora do Java.

## 📡 Fluxo de Funcionamento

1.  **Ingestão (Producer)**: Um serviço agendado gera variações de mercado a cada 1 segundo e publica no tópico `market-prices` do Kafka.
2.  **Processamento (Consumer)**: O consumidor ouve o tópico em tempo real, desserializa o JSON e armazena a cotação mais recente no Redis usando o padrão de chave `price:{symbol}`.
3.  **Exposição (API)**: Um endpoint REST reativo busca os dados diretamente do Redis para entrega ao cliente final com latência mínima.

## 🚀 Como Executar

### Pré-requisitos
* Docker Desktop instalado e rodando.
* JDK 17 ou superior.
* Maven.

### 1. Subir a Infraestrutura
Na raiz do projeto, suba os containers necessários:
```bash
docker-compose up -d

2. Rodar a Aplicação
Execute o projeto através da sua IDE ou via terminal:

Bash
mvn spring-boot:run

3. Acessar os Dados
Com a aplicação rodando, abra o navegador e acesse:
http://localhost:8080/price/BTC-USD
