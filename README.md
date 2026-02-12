# TODO List

## Estrutura de pastas
```
src/
└── br.com.seunome.todolist/
    ├── model/          (Tarefa, Projeto, Prioridade, Status)
    ├── controller/     (GerenciadorDados)
    └── view/           (Main)
```

## Diagrama de classes

Diagram

    class Main {
        +main(String[] args)
        -mainMenu()
        -taskMenu()
        -categoryMenu()
    }

    class DataManager {
        -List~Task~ allTasks
        -List~Category~ allCategories
        +addTask(Task t)
        +addCategory(String name)
        +removeTask(UUID id)
        +getTasksFiltered(filterParams) List~Task~
        +getStatistics() Map
        -rebalance()
        +saveFile()
        +loadFile()
    }

    class Task {
        -UUID id
        -String name
        -String description
        -LocalDate dueDate
        -Priority priority
        -Category category (optional)
        -StatusTask status
        +getters/setters()
    }

    class Category {
        -UUID id
        -String name
        +toString() String
    }

    class Priority {
        <<enumeration>>
        MUITO_BAIXA (1)
        BAIXA (2)
        MEDIA (3)
        ALTA (4)
        URGENTE (5)
        +getLevel() int
        +getDescription() String

    }

    class StatusTask {
        <<enumeration>>
        TODO
        DOING
        DONE
    }

    Main --> DataManager
    DataManager "1" *-- "many" Task
    DataManager "1" *-- "many" Category
    Task --> Category : "opcional"
    Task --> Priority
    Task --> StatusTask
