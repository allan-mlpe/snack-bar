# Snack Bar

### Execução da aplicação

#### 1. Instalação das dependências
    
- [Docker](https://docs.docker.com/get-docker/).
- [Docker Compose](https://docs.docker.com/compose/install/).

#### 2. Iniciar os containers

    docker-compose up

* Podemos também utilizar `docker-compose up -d` para rodar em background.

### Questões arquiteturais

#### 1. Tecnologias

- Backend (Java): requisito do problema. Como suporte, foram utilizados alguns frameworks e tecnologias populares do ecossistema da linguagem como o [Spring Boot](https://spring.io/projects/spring-boot) e o [Maven](). O primeiro foi utilizado para abstrair questões relacionadas a API Rest, injeção de dependências e o acesso aos dados. O Maven foi utilizado para gestão de dependências e construção do projeto. Uma outra dependência utilizada foi o [Flyway](https://flywaydb.org/) para vesionamento e gerenciamento da evolução do modelo de dados da aplicação por meio de migrações.

- Frontend (Angular): sugestão do problema. Além do Angular, foi utilizado o [Bootstrap](https://ng-bootstrap.github.io/#/home) para tornar a interface aplicação mais amigável ao usuário e um pouco mais interativa.

- Banco de dados (MySQL): embora não fosse um requisito do problema, foi utilizado um banco de dados relacional que pareceu mais adequado para construção de uma solução mais consistente do que seria um NoSQL. A opção pelo MySQL foi totalmente pessoal, justificada apenas por maior familiaridade.

- Questões relacioanas ao ambiente de execução e infraestrutura serão abordadas [aqui]().

### CI/CD

- Para executar os testes automáticos da aplicação foi configurado um `runner` do Github. A configuração desse estágio leva em consideração os commits feitos na branch `master`, de modo que a cada novo commit os testes são executados.

- De maneira análoga, os commits na `master` também disparam testes automáticos no Docker Hub, entretanto, de maneira levemente diferente. Lá estamos considerando apenas as modificações feitas no subdiretório `backend/`. Se todos os testes forem executados com sucesso, é feito o upload de uma nova versão da imagem `allanmlpe/snackbar-api`.

- Para a imagem `allanmlpe/snackbar-server` não foi possível utilizar os builds automáticos no Docker Hub. O Dockerfile dessa imagem utiliza [multi-stage](https://docs.docker.com/develop/develop-images/multistage-build/), que ainda não é suportado pelo Docker Hub. A solução encontrada foi cirar um novo estágio no runner do Github, que tem a responsabilidade de realizar o build dos estágios `builder` e `deploy` e fazer o upload da imagem resultante para o Docker Hub. 

### Ambiente

- O ambiente de execução da aplicação é composto por 3 containers Docker:

    - `db`: banco de dados MySQL 5.7 para persistência dos dados do sistema.

    - `api`: servidor rodando a API que contém as regras de negócio da aplicação intermedia as requisições do cliente e o acesso ao banco de dados.

    - `web`: servidor web Nginx que, além de prover os arquivos estáticos da aplicação construída em Angular, serve como um proxy reverso para o servidor da API nas rotas que tem um padrão com `http://<HOST_NAME>/api`.

- As imagens base `allanmlpe/snackbar-api` e `allanmlpe/snackbar-server` usadas pelos containers `api` e `web`, respetivamente, tem objetivo de otimizar o tempo do build inicial da aplicação. Deste modo não é preciso esperar o build do Maven e do Angular. 

- Há ainda, na raiz do projeto, um diretório chamado `docker-config` que contém arquivos utilizados na criação dos containers, como as variáveis de ambiente e a configuração do nginx.