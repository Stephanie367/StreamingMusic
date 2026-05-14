# Sistema de Streaming de Música

Projeto desenvolvido para a disciplina de Programação Orientada a Objetos na Universidade Braz Cubas, sob orientação do professor Davi de Oliveira. O sistema simula uma plataforma de streaming de música com usuários Free e Premium, desenvolvido ao longo de 6 checkpoints.

## Funcionalidades

- Cadastro e gerenciamento de músicas com validação de dados
- Dois tipos de usuário: Free (com limite de reproduções e anúncios) e Premium (alta qualidade, sem anúncios)
- Sistema de reprodução com comportamento diferente por tipo de usuário
- Histórico de reprodução por usuário
- Playlists personalizadas (Free: até 3, Premium: ilimitadas) e automáticas
- Playlists automáticas: Top 10, Recomendadas e Adicionadas Recentemente
- Sistema de download exclusivo para usuários Premium
- Busca por título, artista e filtro por gênero
- Estatísticas gerais do sistema

## Estrutura do Projeto

```
src/
└── br/
    └── com/
        └── streaming/
            ├── modelo/
            │   ├── ItemReproducao.java   (classe abstrata)
            │   ├── Musica.java
            │   ├── Playlist.java
            │   ├── PlaylistPersonalizada.java
            │   ├── PlaylistAutomatica.java
            │   ├── Usuario.java
            │   ├── UsuarioFree.java
            │   └── UsuarioPremium.java
            ├── servico/
            │   ├── Reproduzivel.java     (interface)
            │   ├── Baixavel.java         (interface)
            │   └── GeradorRecomendacoes.java
            ├── util/
            │   ├── Validador.java
            │   └── FormatadorTempo.java
            └── principal/
                └── StreamingMusica.java
```

## Conceitos de POO aplicados

| Conceito | Como foi aplicado |
|---|---|
| Encapsulamento | Atributos private/protected com getters e setters em todas as classes |
| Herança | UsuarioFree e UsuarioPremium estendem Usuario; PlaylistPersonalizada e PlaylistAutomatica estendem Playlist |
| Polimorfismo | reproduzirMusica() e getTipoUsuario() sobrescritos nas subclasses; reproduzir() sobrescrito nas playlists |
| Abstração | Classe abstrata ItemReproducao e interfaces Reproduzivel e Baixavel |
| Interfaces | Reproduzivel implementada por Musica e Playlist; Baixavel implementada por UsuarioPremium |
| instanceof / casting | Usado em exibirDetalhes(), exibirEstatisticas() e nos menus para acessar comportamentos específicos |

## Como Executar

Compilar:
```bash
javac -encoding UTF-8 -sourcepath src -d out $(find src -name "*.java")
```

Executar:
```bash
java -cp out br.com.streaming.principal.StreamingMusica
```

## Autor

- Nome: Stephanie Vitoria Bessa dos Santos
- RA: 45619930
- Universidade: Braz Cubas
- Professor: Davi de Oliveira

## Histórico de Checkpoints

| Checkpoint | Conteúdo |
|---|---|
| CP1 | Classes básicas, encapsulamento e construtores |
| CP2 | Herança com UsuarioFree e UsuarioPremium |
| CP3 | Polimorfismo com reproduzirMusica() e getTipoUsuario() |
| CP4 | Classes abstratas com ItemReproducao |
| CP5 | Coleções e playlists com ArrayList polimórfico |
| CP6 | Interfaces Reproduzivel e Baixavel, organização em pacotes e versão final |
