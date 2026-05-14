package br.com.streaming.principal;

import br.com.streaming.modelo.*;

import java.util.ArrayList;
import java.util.Scanner;

public class StreamingMusica {

    // lista polimórfica: guarda tanto UsuarioFree quanto UsuarioPremium
    static ArrayList<Usuario> usuarios = new ArrayList<>();
    static Usuario usuarioLogado = null;
    static ArrayList<Musica> listaGeral = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        inicializarDados();

        int opcao;
        do {
            exibirMenuPrincipal();
            opcao = lerOpcao();
            processarMenuPrincipal(opcao);
        } while (opcao != 0);

        System.out.println("Encerrando sistema...");
    }

    public static void exibirMenuPrincipal() {
        System.out.println("\n=== SISTEMA DE STREAMING ===");
        System.out.println("1. Criar novo usuario");
        System.out.println("2. Login");
        System.out.println("3. Listar usuarios");
        System.out.println("0. Sair");
        System.out.print("Escolha: ");
    }

    public static void processarMenuPrincipal(int opcao) {
        switch (opcao) {
            case 1: criarUsuario();   break;
            case 2: login();          break;
            case 3: listarUsuarios(); break;
            case 0: break;
            default: System.out.println("Opcao invalida.");
        }
    }

    public static void criarUsuario() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.println("\nTipo de conta:");
        System.out.println("1. Free");
        System.out.println("2. Premium");
        int tipo = lerOpcao();

        // dependendo da escolha, crio um tipo diferente de usuario
        // mas os dois ficam guardados no mesmo ArrayList<Usuario>
        if (tipo == 1) {
            usuarios.add(new UsuarioFree(nome, email));
        } else {
            System.out.println("Escolha o plano Premium:");
            System.out.println("1. Mensal   - R$ 19,90");
            System.out.println("2. Anual    - R$ 199,90");
            System.out.println("3. Familiar - R$ 29,90");
            System.out.print("Opcao: ");
            int planoOpcao = lerOpcao();
            String plano;

            switch (planoOpcao) {
                case 1: plano = "Mensal";   break;
                case 2: plano = "Anual";    break;
                case 3: plano = "Familiar"; break;
                default:
                    System.out.println("Opcao invalida! Plano Mensal selecionado automaticamente.");
                    plano = "Mensal";
            }

            System.out.println("Plano escolhido: " + plano);
            usuarios.add(new UsuarioPremium(nome, email, plano));
        }

        System.out.println("Usuario criado!");
    }

    public static void login() {
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuario cadastrado. Crie um usuario primeiro.");
            return;
        }

        System.out.println("\nUsuarios cadastrados:");
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario u = usuarios.get(i);
            // getTipoUsuario() é polimórfico: cada subclasse retorna o seu tipo
            System.out.println((i + 1) + ". " + u.getNome() + " (" + u.getTipoUsuario() + ")");
        }

        System.out.print("Escolha o usuario: ");
        int idx = lerOpcao() - 1;

        if (idx >= 0 && idx < usuarios.size()) {
            usuarioLogado = usuarios.get(idx);
            System.out.println("Login realizado: " + usuarioLogado.getNome() + " (" + usuarioLogado.getTipoUsuario() + ")");
            menuLogado();
        } else {
            System.out.println("Usuario invalido.");
        }
    }

    public static void listarUsuarios() {
        System.out.println("\n--- USUARIOS CADASTRADOS ---");
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuario cadastrado.");
            return;
        }
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario u = usuarios.get(i);
            System.out.println((i + 1) + ". " + u.getNome() + " (" + u.getTipoUsuario() + ")");
        }
    }

    public static void menuLogado() {
        int opcao;
        do {
            exibirMenuLogado();
            opcao = lerOpcao();
            processarOpcaoLogado(opcao);
        } while (opcao != 0);

        usuarioLogado = null;
        System.out.println("Logout realizado.");
    }

    public static void exibirMenuLogado() {
        System.out.println("\n--- MENU (" + usuarioLogado.getNome() + " - " + usuarioLogado.getTipoUsuario() + ") ---");
        System.out.println("1. Cadastrar Musica    | 2. Listar Musicas     | 3. Editar Musica");
        System.out.println("4. Reproduzir          | 5. Ver Historico      | 6. Criar Playlist");
        System.out.println("7. Gerenciar Playlists | 8. Playlists Automaticas");
        System.out.println("9. Estatisticas        | 10. Exibir Detalhes");

        // uso instanceof para mostrar opcoes diferentes dependendo do tipo de usuario
        if (usuarioLogado instanceof UsuarioPremium) {
            System.out.println("11. Baixar Musica      | 12. Musicas Baixadas");
        } else {
            System.out.println("11. Upgrade para Premium");
        }

        System.out.println("13. Buscar Musica");
        System.out.println("0. Logout");
        System.out.print("Escolha: ");
    }

    public static void processarOpcaoLogado(int opcao) {
        switch (opcao) {
            case 1:  cadastrarMusica();                    break;
            case 2:  listarMusicas();                      break;
            case 3:  editarMusica();                       break;
            case 4:  reproduzir();                         break;
            case 5:  usuarioLogado.exibirHistorico();      break;
            case 6:  criarPlaylistLogica();                break;
            case 7:  gerenciarPlaylists();                 break;
            case 8:  menuPlaylistsAutomaticas();           break;
            case 9:  exibirEstatisticas();                 break;
            case 10: exibirDetalhes(usuarioLogado);        break;

            case 11:
                if (usuarioLogado instanceof UsuarioPremium) baixar();
                else System.out.println("Faca seu upgrade para Premium no site!");
                break;

            case 12:
                if (usuarioLogado instanceof UsuarioPremium) {
                    ((UsuarioPremium) usuarioLogado).listarMusicasBaixadas();
                } else {
                    System.out.println("Opcao invalida para usuario Free.");
                }
                break;

            case 13: buscarMusica(); break;
            case 0: break;
            default: System.out.println("Opcao invalida.");
        }
    }

    // aqui uso instanceof para verificar o tipo real do usuario
    // e depois faço o casting para acessar os métodos específicos de cada subclasse
    public static void exibirDetalhes(Usuario usuario) {
        System.out.println("\n--- DETALHES DO USUARIO ---");
        System.out.println("Nome: " + usuario.getNome());

        if (usuario instanceof UsuarioPremium) {
            UsuarioPremium premium = (UsuarioPremium) usuario;
            System.out.println("Plano: " + premium.getPlano());
            System.out.println("Musicas baixadas: " + premium.getMusicasBaixadas().size());
        } else if (usuario instanceof UsuarioFree) {
            UsuarioFree free = (UsuarioFree) usuario;
            System.out.println("Tipo: Gratuito");
            System.out.println("Playlists: " + free.getPlaylists().size() + "/3");
        }
    }

    public static void menuPlaylistsAutomaticas() {
        System.out.println("\n=== PLAYLISTS AUTOMATICAS ===");
        System.out.println("1. Top 10 Mais Tocadas");
        System.out.println("2. Recomendadas para Voce");
        System.out.println("3. Adicionadas Recentemente");
        System.out.print("Escolha: ");

        int opcao = lerOpcao();
        String criterio;
        String nomePlaylist;

        switch (opcao) {
            case 1: criterio = "top";          nomePlaylist = "Top 10 Mais Tocadas";      break;
            case 2: criterio = "recomendadas"; nomePlaylist = "Recomendadas para Voce";   break;
            case 3: criterio = "recentes";     nomePlaylist = "Adicionadas Recentemente"; break;
            default:
                System.out.println("Opcao invalida.");
                return;
        }

        // crio a PlaylistAutomatica e chamo atualizar() para ela buscar as musicas certas
        PlaylistAutomatica pa = new PlaylistAutomatica(nomePlaylist, criterio);
        System.out.println("Gerando playlist \"" + nomePlaylist + "\"...");
        pa.atualizar(listaGeral);
        System.out.println("Playlist criada com " + pa.getQuantidadeMusicas() + " musicas!");

        usuarioLogado.adicionarPlaylist(pa);

        System.out.print("\nDeseja reproduzir agora? (1-Sim / outro-Nao): ");
        if (lerOpcao() == 1) {
            // polimorfismo: reproduzir() da PlaylistAutomatica é chamado automaticamente
            pa.reproduzir();
        }
    }

    // percorro o ArrayList de Usuario usando instanceof para separar os tipos
    // e calcular as estatísticas de cada um
    public static void exibirEstatisticas() {
        System.out.println("\n=== ESTATISTICAS DO SISTEMA ===");

        int totalFree = 0;
        int totalPremium = 0;
        int reproducoesFree = 0;
        int reproducoesPremium = 0;
        int anunciosExibidos = 0;

        for (Usuario u : usuarios) {
            if (u instanceof UsuarioFree) {
                UsuarioFree free = (UsuarioFree) u;
                totalFree++;
                reproducoesFree += free.getTotalReproducoes();
                anunciosExibidos += free.getContadorReproducoes() / 3;
            } else if (u instanceof UsuarioPremium) {
                totalPremium++;
                reproducoesPremium += u.getTotalReproducoes();
            }
        }

        int totalUsuarios = totalFree + totalPremium;
        int totalReproducoes = reproducoesFree + reproducoesPremium;

        System.out.println("Total de usuarios: " + totalUsuarios);
        System.out.println("- Free: " + totalFree + " usuarios");
        System.out.println("- Premium: " + totalPremium + " usuarios");
        System.out.println("\nReproducoes totais: " + totalReproducoes);

        if (totalReproducoes > 0) {
            int pctFree    = (reproducoesFree    * 100) / totalReproducoes;
            int pctPremium = (reproducoesPremium * 100) / totalReproducoes;
            System.out.println("- Free: "    + reproducoesFree    + " reproducoes (" + pctFree    + "%)");
            System.out.println("- Premium: " + reproducoesPremium + " reproducoes (" + pctPremium + "%)");
        } else {
            System.out.println("- Free: "    + reproducoesFree    + " reproducoes");
            System.out.println("- Premium: " + reproducoesPremium + " reproducoes");
        }

        System.out.println("\nAnuncios exibidos: " + anunciosExibidos);
    }

    public static void cadastrarMusica() {
        System.out.print("Titulo: ");
        String titulo = scanner.nextLine();
        System.out.print("Artista: ");
        String artista = scanner.nextLine();
        System.out.print("Duracao (seg): ");
        int duracao = lerOpcao();
        System.out.print("Genero: ");
        String genero = scanner.nextLine();

        Musica m = new Musica(titulo, artista, duracao, genero);

        if (m.getTitulo() != null && m.getArtista() != null && m.getGenero() != null) {
            listaGeral.add(m);
            System.out.println("Musica cadastrada com sucesso!");
        } else {
            System.out.println("\n[ERRO]: Dados invalidos ou genero nao permitido!");
        }
    }

    public static void listarMusicas() {
        System.out.println("\n--- Biblioteca Musical ---");
        if (listaGeral.isEmpty()) {
            System.out.println("Nenhuma musica cadastrada.");
            return;
        }
        for (int i = 0; i < listaGeral.size(); i++) {
            System.out.print((i + 1) + ". ");
            listaGeral.get(i).exibir();
        }
    }

    public static void editarMusica() {
        listarMusicas();
        if (listaGeral.isEmpty()) return;

        System.out.print("Numero da musica para editar: ");
        int indice = lerOpcao() - 1;

        if (indice >= 0 && indice < listaGeral.size()) {
            Musica m = listaGeral.get(indice);
            System.out.print("Novo Titulo (vazio para manter): ");
            String nt = scanner.nextLine();
            if (!nt.isEmpty()) m.setTitulo(nt);
            System.out.println("Edicao concluida!");
        }
    }

    public static void reproduzir() {
        listarMusicas();
        if (listaGeral.isEmpty()) return;

        System.out.print("Numero da musica: ");
        int idx = lerOpcao() - 1;

        if (idx >= 0 && idx < listaGeral.size()) {
            // polimorfismo em ação: cada tipo de usuario executa sua versão do método
            usuarioLogado.reproduzirMusica(listaGeral.get(idx));
        }
    }

    public static void baixar() {
        listarMusicas();
        System.out.print("Numero para baixar: ");
        int idx = lerOpcao() - 1;

        if (idx >= 0 && idx < listaGeral.size()) {
            // faço o casting pois sei que só usuario Premium chega aqui
            ((UsuarioPremium) usuarioLogado).baixarMusica(listaGeral.get(idx));
        }
    }

    public static void criarPlaylistLogica() {
        System.out.print("Nome da playlist: ");
        String nomeP = scanner.nextLine();

        if (usuarioLogado instanceof UsuarioFree) {
            ((UsuarioFree) usuarioLogado).criarPlaylist(nomeP);
        } else {
            usuarioLogado.adicionarPlaylist(new PlaylistPersonalizada(nomeP, usuarioLogado.getNome()));
            System.out.println("Playlist criada.");
        }
    }

    public static void gerenciarPlaylists() {
        usuarioLogado.listarPlaylists();
        if (usuarioLogado.getPlaylists().isEmpty()) {
            System.out.println("Voce nao tem playlists criadas.");
            return;
        }

        System.out.print("Escolha o numero da playlist: ");
        int idx = lerOpcao() - 1;
        Playlist p = usuarioLogado.getPlaylist(idx);

        if (p != null) {
            System.out.println("\n1. Listar Musicas | 2. Adicionar Musica da Biblioteca | 3. Reproduzir | 0. Voltar");
            int sub = lerOpcao();

            if (sub == 1) p.listarMusicas();

            if (sub == 2) {
                listarMusicas();
                System.out.print("Numero da musica da biblioteca para adicionar: ");
                int mIdx = lerOpcao() - 1;
                if (mIdx >= 0 && mIdx < listaGeral.size()) {
                    p.adicionarMusica(listaGeral.get(mIdx));
                    System.out.println("Musica adicionada a playlist!");
                }
            }

            if (sub == 3) {
                // polimorfismo: reproduzir() correto é chamado dependendo do tipo da playlist
                p.reproduzir();
            }
        }
    }

    public static int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            return -1;
        }
    }

    public static void buscarMusica() {
    System.out.println("\n=== BUSCAR MUSICA ===");
    System.out.println("1. Buscar por titulo");
    System.out.println("2. Buscar por artista");
    System.out.println("3. Filtrar por genero");
    System.out.print("Escolha: ");
    int opcao = lerOpcao();

    System.out.print("Digite a busca: ");
    String busca = scanner.nextLine();

    // percorro a biblioteca filtrando conforme a opcao escolhida
    ArrayList<Musica> encontradas = new ArrayList<>();
    for (Musica m : listaGeral) {
        if (opcao == 1 && m.contemTitulo(busca)) {
            encontradas.add(m);
        } else if (opcao == 2 && m.contemArtista(busca)) {
            encontradas.add(m);
        } else if (opcao == 3 && m.getGenero() != null &&
                m.getGenero().equalsIgnoreCase(busca)) {
            encontradas.add(m);
        }
    }

    // exibo os resultados
    System.out.println("\n--- RESULTADOS ---");
    if (encontradas.isEmpty()) {
        System.out.println("Nenhuma musica encontrada.");
    } else {
        System.out.println(encontradas.size() + " musica(s) encontrada(s):");
        for (int i = 0; i < encontradas.size(); i++) {
            System.out.print((i + 1) + ". ");
            encontradas.get(i).exibir();
        }
    }
}
    public static void inicializarDados() {
}}