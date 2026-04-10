import java.util.ArrayList;
import java.util.Scanner;

public class StreamingMusica {
    static ArrayList<Musica> listaGeral = new ArrayList<>();
    static Usuario usuarioAtivo = new Usuario();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Nome do usuário: ");
        usuarioAtivo.setNome(scanner.nextLine());

        int opcao;
        do {
            exibirMenu();
            opcao = lerOpcao();
            processarOpcao(opcao);
        } while (opcao != 0);
    }

    public static void exibirMenu() {
        System.out.println("\n1. Cadastrar Música | 2. Listar Músicas | 3. Buscar");
        System.out.println("4. Criar Playlist | 5. Gerenciar Playlists | 6. Editar Música | 0. Sair");
        System.out.print("Escolha: ");
    }

    public static int lerOpcao() {
        try { return Integer.parseInt(scanner.nextLine()); } catch (Exception e) { return -1; }
    }

    public static void processarOpcao(int opcao) {
        switch (opcao) {
            case 1: cadastrarMusica(); break;
            case 2: listarMusicas(); break;
            case 3: buscarMusica(); break;
            case 4: criarPlaylist(); break;
            case 5: gerenciarPlaylists(); break;
            case 6: editarMusica(); break;
        }
    }

    public static void cadastrarMusica() {
        System.out.print("Título: "); String titulo = scanner.nextLine();
        System.out.print("Artista: "); String artista = scanner.nextLine();
        System.out.print("Duração (seg): "); int duracao = lerOpcao();
        System.out.print("Gênero: "); String genero = scanner.nextLine();

        Musica m = new Musica(titulo, artista, duracao, genero);

        if (m.getTitulo() != null && m.getArtista() != null && m.getGenero() != null) {
            listaGeral.add(m);
            System.out.println("Música cadastrada com sucesso!");
        } else {
            System.out.println("\n[ERRO]: Dados inválidos ou gênero não permitido!");
            System.out.println("Gêneros aceitos: Pop, Rock, Jazz, Eletrônica, Hip-Hop, Clássica.");
        }
    }

    public static void editarMusica() {
        listarMusicas();
        if (listaGeral.isEmpty()) return;

        System.out.print("Digite o número da música que deseja editar: ");
        int indice = lerOpcao() - 1;

        if (indice >= 0 && indice < listaGeral.size()) {
            Musica m = listaGeral.get(indice);

            System.out.println("Editando: " + m.getTitulo());
            System.out.print("Novo Título (deixe vazio para manter): ");
            String novoTitulo = scanner.nextLine();
            if (!novoTitulo.isEmpty()) m.setTitulo(novoTitulo);

            System.out.print("Novo Artista (deixe vazio para manter): ");
            String novoArtista = scanner.nextLine();
            if (!novoArtista.isEmpty()) m.setArtista(novoArtista);

            System.out.print("Nova Duração (0 para manter): ");
            int novaDuracao = lerOpcao();
            if (novaDuracao > 0) m.setDuracaoSegundos(novaDuracao);

            System.out.print("Novo Gênero (deixe vazio para manter): ");
            String novoGenero = scanner.nextLine();
            if (!novoGenero.isEmpty()) m.setGenero(novoGenero);

            System.out.println("Edição concluída! Verifique se os dados são válidos.");
        } else {
            System.out.println("Índice inválido!");
        }
    }

    public static void listarMusicas() {
        System.out.println("\n--- Lista Geral de Músicas ---");
        for (int i = 0; i < listaGeral.size(); i++) {
            System.out.print((i + 1) + ". ");
            listaGeral.get(i).exibir();
        }
    }

    public static void buscarMusica() {
        System.out.print("Buscar: ");
        String busca = scanner.nextLine();
        for (Musica m : listaGeral) {
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

            if (sub == 1) p.listarMusicas();

            if (sub == 2) {
                listarMusicas();
                System.out.print("Número da música da lista: ");
                int mIdx = lerOpcao() - 1;
                if (mIdx >= 0 && mIdx < listaGeral.size()) {
                    p.adicionarMusica(listaGeral.get(mIdx));
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
                System.out.println("Playlist: " + p.getNome());
                System.out.println("Total de músicas: " + p.getQuantidadeMusicas());
                System.out.println("Tempo total: " + p.getDuracaoTotal() + " segundos");
            }
        }
    }
}