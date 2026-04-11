import java.util.ArrayList;
import java.util.Scanner;

public class StreamingMusicaAluno {
    
    static ArrayList<Musica> acervo = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static Usuario usuarioLogado; 
    
    public static void main(String[] args) {
        adicionarMusicasTeste();
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("🎵 BEM-VINDO AO SISTEMA DE STREAMING 🎵");
        System.out.println("=".repeat(50));
        System.out.print("Para começar, por favor, digite o seu nome: ");
        String nomeDigitado = scanner.nextLine();
        
        try {
            usuarioLogado = new Usuario(nomeDigitado);
            System.out.println("\n✅ Olá, " + usuarioLogado.getNome() + "! Conta acessada com sucesso.");
        } catch (IllegalArgumentException e) {
            System.out.println("\n⚠️ Nome inválido. Entrando com a conta padrão: Visitante.");
            usuarioLogado = new Usuario(); 
        }
        
        int opcao;
        do {
            exibirMenu();
            opcao = lerOpcao();
            processarOpcao(opcao);
        } while (opcao != 0);
        
        System.out.println("\n🎵 Obrigado por usar o Sistema de Streaming, " + usuarioLogado.getNome() + "! Até logo! 🎵");
        scanner.close();
    }
    
    public static void exibirMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("🎵 SISTEMA DE STREAMING - BEM-VINDO " + usuarioLogado.getNome().toUpperCase() + " 🎵");
        System.out.println("=".repeat(50));
        System.out.println("1. Cadastrar música");
        System.out.println("2. Listar todas as músicas");
        System.out.println("3. Buscar música");
        System.out.println("4. Criar playlist");
        System.out.println("5. Gerenciar playlists");
        System.out.println("6. Exibir estatísticas");
        System.out.println("0. Sair");
        System.out.println("=".repeat(50));
        System.out.print("Escolha uma opção: ");
    }
    
    public static int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    public static void processarOpcao(int opcao) {
        switch (opcao) {
            case 1: cadastrarMusica(); break;
            case 2: listarAcervo(); break;
            case 3: buscarMusica(); break;
            case 4: criarNovaPlaylist(); break;
            case 5: gerenciarPlaylists(); break;
            case 6: exibirEstatisticas(); break;
            case 0: break;
            default: System.out.println("❌ Opção inválida! Tente novamente.");
        }
    }
    
    public static void cadastrarMusica() {
        System.out.println("\n--- CADASTRAR MÚSICA ---");
        
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        
        System.out.print("Artista: ");
        String artista = scanner.nextLine();
        
        System.out.print("Duração (em segundos, menor que 3600): ");
        int duracao = lerOpcao();
        
        System.out.println("\nGêneros disponíveis:");
        for (int i = 0; i < Musica.GENEROS_VALIDOS.length; i++) {
            System.out.println((i + 1) + ". " + Musica.GENEROS_VALIDOS[i]);
        }
        System.out.print("Escolha o gênero (1-" + Musica.GENEROS_VALIDOS.length + "): ");
        int opcaoGenero = lerOpcao();
        
        String generoStr = "";
        if (opcaoGenero >= 1 && opcaoGenero <= Musica.GENEROS_VALIDOS.length) {
            generoStr = Musica.GENEROS_VALIDOS[opcaoGenero - 1];
        } else {
            generoStr = "Invalido"; 
        }
        
        try {
            Musica novaMusica = new Musica(titulo, artista, duracao, generoStr);
            acervo.add(novaMusica);
            System.out.println("✅ Música cadastrada com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Falha ao cadastrar música: " + e.getMessage());
        }
    }
    
    public static void listarAcervo() {
        System.out.println("\n--- MÚSICAS CADASTRADAS ---");
        if (acervo.isEmpty()) {
            System.out.println("Nenhuma música no acervo.");
            return;
        }
        
        for (int i = 0; i < acervo.size(); i++) {
            System.out.print((i + 1) + ". ");
            acervo.get(i).exibir();
        }
        System.out.println("\nTotal: " + acervo.size() + " música(s)");
    }
    
    public static void buscarMusica() {
        System.out.println("\n--- BUSCAR MÚSICA ---");
        System.out.print("Digite um título ou nome de artista para buscar: ");
        String termo = scanner.nextLine().trim();
        
        boolean encontrou = false;
        for (Musica m : acervo) {
            if (m.contemTitulo(termo) || m.contemArtista(termo)) {
                if (!encontrou) {
                    System.out.println("\nResultados:");
                    encontrou = true;
                }
                System.out.print("- ");
                m.exibir();
            }
        }
        
        if (!encontrou) {
            System.out.println("❌ Nenhuma música encontrada com esse termo.");
        }
    }
    
    public static void criarNovaPlaylist() {
        System.out.println("\n--- CRIAR PLAYLIST ---");
        System.out.print("Digite o nome da nova playlist: ");
        String nome = scanner.nextLine();
        
        usuarioLogado.criarPlaylist(nome);
    }
    
    public static void gerenciarPlaylists() {
        int opcaoSub;
        do {
            System.out.println("\n--- GERENCIAR PLAYLISTS ---");
            System.out.println("1. Listar minhas playlists");
            System.out.println("2. Adicionar música a uma playlist");
            System.out.println("3. Remover música de uma playlist");
            System.out.println("4. Exibir detalhes de uma playlist");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");
            
            opcaoSub = lerOpcao();
            
            switch (opcaoSub) {
                case 1: 
                    System.out.println("\nMinhas Playlists:");
                    usuarioLogado.listarPlaylists(); 
                    break;
                case 2: adicionarMusicaEmPlaylist(); break;
                case 3: removerMusicaDePlaylist(); break;
                case 4: exibirDetalhesPlaylist(); break;
                case 0: break;
                default: System.out.println("❌ Opção inválida.");
            }
        } while (opcaoSub != 0);
    }
    
    public static void adicionarMusicaEmPlaylist() {
        if (usuarioLogado.getQuantidadePlaylists() == 0) {
            System.out.println("❌ Você não possui playlists. Crie uma primeiro.");
            return;
        }
        
        System.out.println("\nEscolha a playlist:");
        usuarioLogado.listarPlaylists();
        System.out.print("Número da playlist: ");
        int idxPlaylist = lerOpcao() - 1;
        
        Playlist p = usuarioLogado.getPlaylist(idxPlaylist);
        if (p == null) {
            System.out.println("❌ Playlist não encontrada.");
            return;
        }
        
        System.out.println("\nEscolha a música do acervo para adicionar:");
        listarAcervo();
        System.out.print("Número da música: ");
        int idxMusica = lerOpcao() - 1;
        
        if (idxMusica >= 0 && idxMusica < acervo.size()) {
            try {
                p.adicionarMusica(acervo.get(idxMusica));
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Erro: " + e.getMessage());
            }
        } else {
            System.out.println("❌ Música não encontrada.");
        }
    }
    
    public static void removerMusicaDePlaylist() {
        if (usuarioLogado.getQuantidadePlaylists() == 0) {
            System.out.println("❌ Você não possui playlists.");
            return;
        }
        
        System.out.println("\nEscolha a playlist:");
        usuarioLogado.listarPlaylists();
        System.out.print("Número da playlist: ");
        int idxPlaylist = lerOpcao() - 1;
        
        Playlist p = usuarioLogado.getPlaylist(idxPlaylist);
        if (p == null) {
            System.out.println("❌ Playlist não encontrada.");
            return;
        }
        
        if (p.getQuantidadeMusicas() == 0) {
            System.out.println("❌ Esta playlist está vazia.");
            return;
        }
        
        System.out.println("\nMúsicas nesta playlist:");
        p.listarMusicas();
        System.out.print("Número da música para remover: ");
        int idxMusica = lerOpcao() - 1;
        
        try {
            p.removerMusica(idxMusica);
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Erro: " + e.getMessage());
        }
    }
    
    public static void exibirDetalhesPlaylist() {
        if (usuarioLogado.getQuantidadePlaylists() == 0) {
            System.out.println("❌ Você não possui playlists.");
            return;
        }
        
        System.out.println("\nEscolha a playlist:");
        usuarioLogado.listarPlaylists();
        System.out.print("Número da playlist: ");
        int idxPlaylist = lerOpcao() - 1;
        
        Playlist p = usuarioLogado.getPlaylist(idxPlaylist);
        if (p != null) {
            System.out.println("\n--- Detalhes: " + p.getNome() + " ---"); 
            System.out.println("Total de músicas: " + p.getQuantidadeMusicas());
            
            int durTotal = p.getDuracaoTotal();
            System.out.printf("Duração total: %d:%02d%n", durTotal / 60, durTotal % 60);
            
            System.out.println("\nFaixas:");
            p.listarMusicas();
        } else {
            System.out.println("❌ Playlist não encontrada.");
        }
    }

    public static void exibirEstatisticas() {
        System.out.println("\n--- ESTATÍSTICAS DO SISTEMA ---");
        
        if (acervo.isEmpty()) {
            System.out.println("Nenhuma música no acervo.");
            return;
        }
        
        int totalMusicas = acervo.size();
        int duracaoTotal = 0;
        int[] contadores = new int[Musica.GENEROS_VALIDOS.length];
        
        for (Musica m : acervo) {
            duracaoTotal += m.getDuracaoSegundos(); 
            
            for (int i = 0; i < Musica.GENEROS_VALIDOS.length; i++) {
                if (m.getGenero().equals(Musica.GENEROS_VALIDOS[i])) { 
                    contadores[i]++;
                    break;
                }
            }
        }
        
        int duracaoMedia = duracaoTotal / totalMusicas;
        
        int indiceMaior = 0;
        for (int i = 1; i < contadores.length; i++) {
            if (contadores[i] > contadores[indiceMaior]) {
                indiceMaior = i;
            }
        }
        
        System.out.println("Total de músicas: " + totalMusicas);
        System.out.printf("Duração total: %d:%02d%n", (duracaoTotal / 60), (duracaoTotal % 60));
        System.out.printf("Duração média: %d:%02d%n", (duracaoMedia / 60), (duracaoMedia % 60));
        System.out.println("Gênero mais cadastrado: " + Musica.GENEROS_VALIDOS[indiceMaior] + " (" + contadores[indiceMaior] + " músicas)");
    }
    
    public static void adicionarMusicasTeste() {
        try {
            acervo.add(new Musica("Bohemian Rhapsody", "Queen", 354, "Rock"));
            acervo.add(new Musica("Billie Jean", "Michael Jackson", 293, "Pop"));
            acervo.add(new Musica("Smells Like Teen Spirit", "Nirvana", 301, "Rock"));
        } catch (IllegalArgumentException e) {
            System.out.println("Falha ao inicializar banco de dados: " + e.getMessage());
        }
    }
}