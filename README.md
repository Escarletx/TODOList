# To-Do List CLI

Este projeto é uma aplicação de lista de tarefas (To-Do List) baseada em console, desenvolvida em **Java Puro** como parte do programa **Acelera ZG**. O objetivo principal é gerenciar atividades diárias com foco em organização, priorização e persistência de dados.

A solução foi estruturada seguindo os princípios de **Programação Orientada a Objetos (POO)** e o padrão de arquitetura **MVC (Model-View-Controller)** simplificado, garantindo uma separação clara de responsabilidades.

---

## Estrutura de Pastas do Projeto

```text
src/
└── br/com/escarlet/todolist/
    ├── controller/
    │   └── DataManager.java      # Gerenciamento de tarefas, lógica de filtros e categorias
    ├── model/
    │   ├── entities/
    │   │   └── Task.java         # Entidade principal com regras de Comparable (Prioridade)
    │   ├── enums/
    │   │   └── TaskStatus.java   # Estados da tarefa (TODO, DOING, DONE)
    │   └── dto/
    │       └── TaskDTO.java      # Record para transporte de dados formatados para a View
    ├── service/
    │   └── FileService.java      # Classe utilitária para persistência em arquivos .txt
    └── view/
        └── Main.java             # Ponto de entrada e interface de interação com o usuário
```
---

## Como Executar o Código

Como este é um projeto Java puro (sem frameworks como Spring ou Maven):

1. **Pré-requisitos:** Certifique-se de ter o **JDK 17** ou superior instalado e configurado no seu PATH.
2. **Via Terminal:**
   - Navegue até a pasta `src`.
   - Compile o projeto:
     ```bash
     javac br/com/escarlet/todolist/view/Main.java
     ```
   - Execute a aplicação:
     ```bash
     java br/com/escarlet/todolist/view/Main
     ```
3. **Via IDE (IntelliJ/Eclipse):**
   - Abra a pasta raiz do projeto.
   - Localize a classe `Main.java` em `src/br/com/escarlet/todolist/view/`.
   - Clique com o botão direito e selecione **Run 'Main.main()'**.

---

## Tecnologias Utilizadas

* <img align="center" alt="Java" height="20" width="20" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg"> **Java 17+**: Uso de `Records`, `Streams` e `Lambdas`.
* **Java I/O (File & BufferedWriter)**: Para a funcionalidade de persistência.
* **Padrão MVC**: Separação da lógica de visualização, controle e modelo.
* **SOLID**: Aplicação de princípios como Responsabilidade Única (SRP) e Segregação de Interfaces.

---

##  Comentários Sobre a Solução

* **Rebalanceamento Automático:** A entidade `Task` implementa a interface `Comparable`. Isso significa que o sistema realiza o rebalanceamento da ordem automaticamente por **prioridade (1-5)** sempre que uma nova tarefa é adicionada.
* **Uso de DTOs:** A aplicação utiliza `TaskDTO` para enviar dados para a camada de visualização. Isso evita que a `Main` manipule objetos de domínio diretamente, protegendo a lógica de negócio e facilitando a formatação de datas e textos.
* **Persistência desacoplada (FileService):** A funcionalidade de salvar em `.txt` foi extraída do controlador principal para uma classe de serviço. Isso segue o princípio de responsabilidade única e permite que o sistema seja facilmente expandido para outros formatos (CSV, JSON) no futuro.
* **Experiência do Usuário (UX) no Console:** Foram implementadas validações de entrada (Try-Catch) para garantir que o programa não feche inesperadamente caso o usuário digite um ID inválido ou uma data fora do padrão.

---

### 📌 Próximos Passos (Backlog)
- [ ] Implementar a edição de tarefas existentes.
- [ ] Adicionar filtro por intervalo de datas.
- [ ] Implementar leitura de arquivo para carregar tarefas salvas ao iniciar o programa.
- [ ] Refatorar DataManager para remover responsabilidades garantindo SRP.

---
Feito com 🍰 e 💻 por Escarlet Imopoco Lima. &copy; 2026
