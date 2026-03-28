import java.util.ArrayList;
import java.util.Scanner;

public class StreamingMusica {
    static ArrayList<Musica> acervoGeral = new ArrayList<>();
    static Usuario usuarioAtivo = new Usuario();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Nome do usuário: ");
        usuarioAtivo.nome = scanner.nextLine();

        int opcao;
        do {
            exibirMenu();
            opcao = lerOpcao();
            processarOpcao(opcao);
        } while (opcao != 0);
    }

    public static void exibirMenu() {
        System.out.println("\n1. Cadastrar Música | 2. Listar Músicas | 3. Buscar");
        System.out.println("4. Criar Playlist | 5. Gerenciar Playlists | 0. Sair");
        System.out.print("Escolha: ");
    }

    public static int lerOpcao() {
        try { return Integer.parseInt(scanner.nextLine()); } catch (Exception e) { return -1; }
    }

    public static void processarOpcao(int opcao) {
        switch (opcao) {
            case 1: cadastrarMusica(); break;
            case 2: listarAcervo(); break;
            case 3: buscarMusica(); break;
            case 4: criarPlaylist(); break;
            case 5: gerenciarPlaylists(); break;
        }
    }

    public static void cadastrarMusica() {
        Musica m = new Musica();
        System.out.print("Título: "); m.titulo = scanner.nextLine();
        System.out.print("Artista: "); m.artista = scanner.nextLine();
        System.out.print("Duração (seg): "); m.duracaoSegundos = lerOpcao();
        System.out.print("Gênero: "); m.genero = scanner.nextLine();
        acervoGeral.add(m);
    }

    public static void listarAcervo() {
        for (int i = 0; i < acervoGeral.size(); i++) {
            System.out.print((i + 1) + ". ");
            acervoGeral.get(i).exibir();
        }
    }

    public static void buscarMusica() {
        System.out.print("Buscar: ");
        String busca = scanner.nextLine();
        for (Musica m : acervoGeral) {
            if (m.contemTitulo(busca) || m.contemArtista(busca)) m.exibir();
        }
    }

    public static void criarPlaylist() {
        System.out.print("Nome da playlist: ");
        usuarioAtivo.criarPlaylist(scanner.nextLine());
    }

    public static void gerenciarPlaylists() {
        usuarioAtivo.listarPlaylists();
        System.out.print("Escolha o número da playlist: ");
        int idx = lerOpcao() - 1;
        Playlist p = usuarioAtivo.getPlaylist(idx);

        if (p != null) {
            System.out.println("\n1. Listar | 2. Adicionar | 3. Remover | 4. Detalhes | 0. Voltar");
            int sub = lerOpcao();

            if (sub == 1) {
                p.listarMusicas();
            }

            if (sub == 2) {
                listarAcervo();
                System.out.print("Número da música do acervo: ");
                int mIdx = lerOpcao() - 1;
                if (mIdx >= 0 && mIdx < acervoGeral.size()) {
                    p.adicionarMusica(acervoGeral.get(mIdx));
                    System.out.println("Música adicionada!");
                }
            }

            if (sub == 3) {
                p.listarMusicas();
                System.out.print("Número da música para remover: ");
                int rIdx = lerOpcao() - 1;
                p.removerMusica(rIdx);
                System.out.println("Música removida!");
            }

            if (sub == 4) {
                System.out.println("Playlist: " + p.nome);
                System.out.println("Total de músicas: " + p.musicas.size());
                int total = 0;
                for (Musica m : p.musicas) total += m.duracaoSegundos;
                System.out.println("Tempo total: " + total + " segundos");
            }
        }


    }}