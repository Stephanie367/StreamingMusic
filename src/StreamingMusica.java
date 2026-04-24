import java.util.ArrayList;
import java.util.Scanner;

public class StreamingMusica {
    static ArrayList<Musica> listaGeral = new ArrayList<>();
    static Usuario usuarioAtivo;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        inicializarDados();

        System.out.println("--- BEM-VINDO AO STREAMING ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.println("Tipo de conta: 1. Free | 2. Premium");
        int tipo = lerOpcao();

        if (tipo == 2) {

            System.out.println("Escolha o plano Premium:");
            System.out.println("1. Mensal   - R$ 19,90");
            System.out.println("2. Anual    - R$ 199,90");
            System.out.println("3. Familiar - R$ 29,90");
            System.out.print("Opcao: ");

            int planoOpcao = lerOpcao();
            String plano;

            switch (planoOpcao) {
                case 1:
                    plano = "Mensal";
                    break;
                case 2:
                    plano = "Anual";
                    break;
                case 3:
                    plano = "Familiar";
                    break;
                default:
                    System.out.println("Opcao invalida! Plano Mensal selecionado automaticamente.");
                    plano = "Mensal";
            }

            System.out.println("Plano escolhido: " + plano);

            usuarioAtivo = new UsuarioPremium(nome, email, plano);

        } else {
            usuarioAtivo = new UsuarioFree(nome, email);
        }

        int opcao;
        do {
            exibirMenu();
            opcao = lerOpcao();
            processarOpcao(opcao);
        } while (opcao != 0);
    }

    public static void exibirMenu() {
        System.out.println("\n--- MENU (" + usuarioAtivo.getNome() + ") ---");
        System.out.println("1. Cadastrar Musica | 2. Listar Musicas   | 3. Editar Musica");
        System.out.println("4. Reproduzir       | 5. Ver Historico    | 6. Criar Playlist");
        System.out.println("7. Gerenciar Playlists");

        if (usuarioAtivo instanceof UsuarioPremium) {
            System.out.println("8. Baixar Musica    | 9. Musicas Baixadas");
        } else {
            System.out.println("8. Upgrade para Premium");
        }
        System.out.println("0. Sair");
        System.out.print("Escolha: ");
    }

    public static void processarOpcao(int opcao) {
        switch (opcao) {
            case 1:
                cadastrarMusica();
                break;
            case 2:
                listarMusicas();
                break;
            case 3:
                editarMusica();
                break;
            case 4:
                reproduzir();
                break;
            case 5:
                usuarioAtivo.exibirHistorico();
                break;
            case 6:
                criarPlaylistLogica();
                break;
            case 7:
                gerenciarPlaylists();
                break;

            case 8:
                if (usuarioAtivo instanceof UsuarioPremium) baixar();
                else System.out.println("Faca seu upgrade para Premium no site!");
                break;

            case 9:
                if (usuarioAtivo instanceof UsuarioPremium) {
                    ((UsuarioPremium) usuarioAtivo).listarMusicasBaixadas();
                } else {
                    System.out.println("Opcao invalida para usuario Free.");
                }
                break;
        }
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

    public static void gerenciarPlaylists() {
        usuarioAtivo.listarPlaylists();
        if (usuarioAtivo.playlists.isEmpty()) {
            System.out.println("Voce nao tem playlists criadas.");
            return;
        }

        System.out.print("Escolha o numero da playlist: ");
        int idx = lerOpcao() - 1;
        Playlist p = usuarioAtivo.getPlaylist(idx);

        if (p != null) {
            System.out.println("\n1. Listar Musicas | 2. Adicionar Musica da Biblioteca | 0. Voltar");
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

    public static void criarPlaylistLogica() {
        System.out.print("Nome da playlist: ");
        String nomeP = scanner.nextLine();

        if (usuarioAtivo instanceof UsuarioFree) {
            ((UsuarioFree) usuarioAtivo).criarPlaylist(nomeP);
        } else {
            usuarioAtivo.playlists.add(new Playlist(nomeP));
            System.out.println("Playlist criada.");
        }
    }

    public static void reproduzir() {
        listarMusicas();
        if (listaGeral.isEmpty()) return;

        System.out.print("Numero da musica: ");
        int idx = lerOpcao() - 1;

        if (idx >= 0 && idx < listaGeral.size()) {
            usuarioAtivo.reproduzirMusica(listaGeral.get(idx));
        }
    }

    public static void baixar() {
        listarMusicas();
        System.out.print("Numero para baixar: ");
        int idx = lerOpcao() - 1;

        if (idx >= 0 && idx < listaGeral.size()) {
            ((UsuarioPremium) usuarioAtivo).baixarMusica(listaGeral.get(idx));
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

    public static int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            return -1;
        }
    }

    public static void inicializarDados() {
        listaGeral.add(new Musica("Musica A", "Artista 1", 180, "Pop"));
        listaGeral.add(new Musica("Musica B", "Artista 2", 210, "Rock"));
        listaGeral.add(new Musica("Musica C", "Artista 3", 200, "Jazz"));
        listaGeral.add(new Musica("Musica D", "Artista 4", 240, "Eletrônica"));
        listaGeral.add(new Musica("Musica E", "Artista 5", 190, "Hip-Hop"));
        listaGeral.add(new Musica("Musica F", "Artista 6", 300, "Clássica"));
    }
}