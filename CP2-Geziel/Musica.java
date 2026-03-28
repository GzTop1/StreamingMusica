public class Musica {

    String titulo;
    String artista;
    int duracaoSegundos;
    String genero;

    void exibir() {
        System.out.println("Título: " + titulo + 
                           " | Artista: " + artista + 
                           " | Duração: " + getDuracaoFormatada() + 
                           " | Gênero: " + genero);
    }

    String getDuracaoFormatada() {
        int min = duracaoSegundos / 60;
        int seg = duracaoSegundos % 60;
        return String.format("%d:%02d", min, seg);
    }

    boolean contemTitulo(String busca) {
        if (titulo == null) return false;
        return titulo.toLowerCase().contains(busca.toLowerCase());
    }

    boolean contemArtista(String busca) {
        if (artista == null) return false;
        return artista.toLowerCase().contains(busca.toLowerCase());
    }
}